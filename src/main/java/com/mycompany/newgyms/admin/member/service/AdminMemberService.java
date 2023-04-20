package com.mycompany.newgyms.admin.member.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mycompany.newgyms.member.vo.MemberVO;

public interface AdminMemberService {
	public ArrayList<MemberVO> memberList(Map condMap) throws Exception;
	public String maxNumSelect(Map condMap) throws Exception;

	public MemberVO memberDetail(String member_id) throws Exception;
	public void modifyMemberInfo(Map modifyMap) throws Exception;
	public void memberState(Map modifyMap) throws Exception;
}
