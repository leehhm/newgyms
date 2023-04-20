package com.mycompany.newgyms.member.dao;

import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.member.vo.MemberVO;

public interface MemberDAO {
	public MemberVO login(Map loginMap) throws DataAccessException;
	public MemberVO joinCheck(Map joinCheckMap) throws DataAccessException;
	public void insertNewMember(MemberVO memberVO) throws DataAccessException;
	public void insertNewOwner(MemberVO memberVO) throws DataAccessException;
	public String selectOverlappedID(String id) throws DataAccessException;
	public String selectOverlappedEID(String eid) throws DataAccessException;
	public void insertKakaoMember(MemberVO memberVO) throws DataAccessException;
	public MemberVO kakaoLogin(String member_id) throws DataAccessException;
	public MemberVO searchId(Map searchidMap) throws DataAccessException;
	public MemberVO searchPw(Map searchidMap) throws DataAccessException;
	public MemberVO searchId1(Map searchidMap) throws DataAccessException;
	public void newPw(Map searchpwMap) throws DataAccessException;
	
	public MemberVO selectOwnerDetail(String member_id) throws DataAccessException;
}