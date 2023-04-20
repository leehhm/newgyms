package com.mycompany.newgyms.admin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.member.vo.MemberVO;

@Repository("adminMemberDAO")
public class AdminMemberDAOImpl implements AdminMemberDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList<MemberVO> memberList(Map condMap) throws DataAccessException {
		ArrayList<MemberVO> adminMemberList = (ArrayList)sqlSession.selectList("mapper.admin_member.memberList", condMap);
		return adminMemberList;
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.admin_member.maxNumSelect", condMap);
		return result;
	}

	@Override
	public MemberVO memberDetail(String member_id) throws DataAccessException {
		MemberVO memberBean = (MemberVO)sqlSession.selectOne("mapper.admin_member.memberDetail", member_id);
		return memberBean;
	}
	
	@Override
	public void modifyMemberInfo(Map modifyMap) throws DataAccessException {
		sqlSession.update("mapper.admin_member.updateMemberInfo", modifyMap);
	}
	
	@Override
	public void memberState(Map modifyMap) throws DataAccessException {
		sqlSession.update("mapper.admin_member.updateMemberState", modifyMap);
	}
}
