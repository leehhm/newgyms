package com.mycompany.newgyms.admin.member.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.member.vo.MemberVO;

public interface AdminMemberDAO {
	public ArrayList<MemberVO> memberList(Map condMap) throws DataAccessException;
	public String maxNumSelect(Map condMap) throws DataAccessException;

	public MemberVO memberDetail(String member_id) throws DataAccessException;
	public void modifyMemberInfo(Map modifyMap) throws DataAccessException;
	public void memberState(Map modifyMap) throws DataAccessException;
}
