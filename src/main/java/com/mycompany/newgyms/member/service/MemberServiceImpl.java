package com.mycompany.newgyms.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.member.dao.MemberDAO;
import com.mycompany.newgyms.member.vo.MemberVO;

@Service("memberService")
@Transactional(propagation=Propagation.REQUIRED)
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO login(Map  loginMap) throws Exception{
		return memberDAO.login(loginMap);
	}
	
	@Override
	public MemberVO joinCheck(Map joinCheckMap) throws Exception{
		return memberDAO.joinCheck(joinCheckMap);
	}
	
	@Override
	public void joinMember(MemberVO memberVO) throws Exception{
		memberDAO.insertNewMember(memberVO);
	}
	
	@Override
	public void joinOwner(MemberVO memberVO) throws Exception{
		memberDAO.insertNewOwner(memberVO);
	}
	
	@Override
	public String overlappedId(String id) throws Exception{
		return memberDAO.selectOverlappedID(id);
	}
	
	@Override
	public String overlappedEid(String eid) throws Exception{
		return memberDAO.selectOverlappedEID(eid);
	}
	
	@Override
	public void kakaoJoin(MemberVO memberVO) throws Exception{
		memberDAO.insertKakaoMember(memberVO);
	}
	
	@Override
	public MemberVO kakaoLogin(String member_id) throws Exception {
		return memberDAO.kakaoLogin(member_id);
	}
	
	@Override
	public MemberVO searchId(Map searchidMap) throws Exception{
		return memberDAO.searchId(searchidMap);
	}
	@Override
	public MemberVO searchPw(Map searchidMap) throws Exception{
		return memberDAO.searchPw(searchidMap);
	}
	@Override
	public MemberVO searchId1(Map searchidMap) throws Exception{
		return memberDAO.searchId1(searchidMap);
	}
	
	@Override
	public void newPw(Map searchpwMap) throws Exception{
		memberDAO.newPw(searchpwMap);
	}
	

	/* 상품 상세페이지 사업자 정보 가져오기 */
	public MemberVO ownerDetail(String member_id) throws Exception {
		MemberVO memberVO = memberDAO.selectOwnerDetail(member_id);
		return memberVO;
	}

}
