package com.mycompany.newgyms.owner.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.main.dao.ownerPageDAO;
import com.mycompany.newgyms.owner.main.vo.OwnerPageVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Service("ownerPageService")
@Transactional(propagation=Propagation.REQUIRED)
public class ownerPageServiceImpl implements ownerPageService {
	@Autowired
	private ownerPageDAO ownerPageDAO;
	@Autowired
	private MemberVO memberVO;
	
	/* 사업장 소개/관리 페이지 */
	@Override
	public List<ProductVO> productList(String member_id) throws Exception {
		List<ProductVO> productList = ownerPageDAO.selectProductList(member_id);
		return productList;
	}
	
	@Override
	public OwnerPageVO ownerPageIntroView(String member_id) throws Exception {
		OwnerPageVO ownerPageVO = ownerPageDAO.selectOwnerPageIntroView(member_id);
		return ownerPageVO;
	}
	@Override
	public MemberVO ownerPageIntroInfo(String member_id) throws Exception {
		MemberVO memberVO = ownerPageDAO.selectOwnerPageIntroInfo(member_id);
		return memberVO;
	}
	
	/* 사업장 관리 수정 */
	@Override
	public String ownerPageIntroModify(Map modifyMap) throws Exception {
		return ownerPageDAO.updateOwnerPage(modifyMap);
	}
	
	
	/* 사업자 회원정보 수정/탈퇴 */
	@Override
	public MemberVO ownerPageDetail(Map ownerpageMap) throws Exception{
		return ownerPageDAO.ownerPageDetail(ownerpageMap);
	}
	
	@Override 
	public MemberVO modifyMyInfo(Map modifyMap) throws Exception {
	    ownerPageDAO.updateMyInfo(modifyMap); 
		memberVO = ownerPageDAO.ownerPageDetail(modifyMap); return memberVO; 
	}
	
	@Override
	public void deleteMember(Map deleteMap) throws Exception {
		ownerPageDAO.deleteMember(deleteMap);
	}
	 
}
