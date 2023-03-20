package com.kh.mybatis.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.mybatis.common.template.Template;
import com.kh.mybatis.member.model.dao.MemberDao;
import com.kh.mybatis.member.model.vo.Member;

public class MemberServiceImpl implements MemberService {
	
	private MemberDao mDao = new MemberDao();
	
	@Override
	public int insertMember(Member m) {
		
		/*
		 * Connection conn = JDBCTemplate.getConnection();
		 * int result = new MemberDao().insertMember(conn, m);
		 * 
		 * if(result > 0) {
		 *  	// 커밋
		 * }else{
		 * 		// 롤백
		 * }
		 */
		
		// 이제부터는 마이바티스 객체 사용할꺼임
		// 기존에 커넥션 객체 생성하는걸 이 코드로 대신
		SqlSession sqlSession = Template.getSqlSession();
		// 이때 mybatis-config.xml 문서 읽어들임
		
		int result = mDao.insertMember(sqlSession, m);
		
		if(result > 0) {
			sqlSession.commit();
		}
		
		// 단건만 처리할때는 사실 롤백안해도됨... => autocommit 꺼져있을때
		// 연쇄적으로 처리할 때만 롤백 작성
		
		sqlSession.close();
		
		return result;
		
	}

	@Override
	public Member loginMember(Member m) {
		
		SqlSession sqlSession = Template.getSqlSession();
		
		Member loginMember = mDao.loginMember(sqlSession, m);
		
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
