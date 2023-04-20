package com.mycompany.newgyms.member.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.member.vo.MemberVO;

@Repository("memberDAO")
public class MemberDAOImpl  implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;	
	
	@Override
	public MemberVO login(Map loginMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.login",loginMap);
	   return member;
	}
	
	@Override
	public MemberVO joinCheck(Map joinCheckMap) throws DataAccessException{
		MemberVO member = sqlSession.selectOne("mapper.member.joinCheck", joinCheckMap);
		return member;
	}
	
	@Override
	public void insertNewMember(MemberVO memberVO) throws DataAccessException{
		sqlSession.insert("mapper.member.insertNewMember", memberVO);
	}
	
	@Override
	public void insertNewOwner(MemberVO memberVO) throws DataAccessException{
		sqlSession.insert("mapper.member.insertNewOwner", memberVO);
	}

	@Override
	public String selectOverlappedID(String id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedID", id);
		return result;
	}
	
	@Override
	public String selectOverlappedEID(String eid) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.member.selectOverlappedEID", eid);
		return result;
	}
	
	@Override
	public void insertKakaoMember(MemberVO memberVO) throws DataAccessException{
		sqlSession.insert("mapper.member.insertKakaoMember", memberVO);
	}

	public MemberVO kakaoLogin(String member_id) throws DataAccessException {
		MemberVO member = (MemberVO)sqlSession.selectOne("mapper.member.kakaoLogin", member_id);
		return member;
	}
	
	@Override
	public MemberVO searchId(Map searchidMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.searchId",searchidMap);
	   return member;
	}
	@Override
	public MemberVO searchPw(Map searchidMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.searchPw",searchidMap);
		return member;
	}
	@Override
	public MemberVO searchId1(Map searchidMap) throws DataAccessException{
		MemberVO member=(MemberVO)sqlSession.selectOne("mapper.member.searchId1",searchidMap);
		return member;
	}
	@Override
	public void newPw(Map searchpwMap) throws DataAccessException{
		sqlSession.update("mapper.member.newPw",searchpwMap);
	}

	/* 상품 상세페이지 사업자 정보 가져오기 */
	@Override
	public MemberVO selectOwnerDetail(String member_id) throws DataAccessException {
		MemberVO memberVO = (MemberVO) sqlSession.selectOne("mapper.member.selectOwnerDetail", member_id);
		return memberVO;
	}

}
