package com.mycompany.newgyms.admin.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.member.dao.AdminMemberDAO;
import com.mycompany.newgyms.member.vo.MemberVO;

@Service("adminMemberService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminMemberServiceImpl implements AdminMemberService {
	@Autowired
	private AdminMemberDAO adminMemberDAO;
	
	@Override
	public ArrayList<MemberVO> memberList(Map condMap) throws Exception{
		return adminMemberDAO.memberList(condMap);
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws Exception {
		return adminMemberDAO.maxNumSelect(condMap);
	}

	@Override
	public MemberVO memberDetail(String member_id) throws Exception{
		 return adminMemberDAO.memberDetail(member_id);
	}
	
	@Override
	public void modifyMemberInfo(Map modifyMap) throws Exception{
		 adminMemberDAO.modifyMemberInfo(modifyMap);
	}
	
	@Override
	public void memberState(Map modifyMap) throws Exception {
		adminMemberDAO.memberState(modifyMap);
	}
}
