package com.mycompany.newgyms.owner.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.main.vo.OwnerPageVO;
import com.mycompany.newgyms.product.vo.ProductVO;

public interface ownerPageDAO {
	
	/* 사업장 소개/관리 페이지 */
	public List<ProductVO> selectProductList(String member_id) throws DataAccessException;
	
	public OwnerPageVO selectOwnerPageIntroView(String member_id) throws DataAccessException;
	public MemberVO selectOwnerPageIntroInfo(String member_id) throws DataAccessException;
	
	/* 사업장 관리 페이지 수정 */
	public String updateOwnerPage(Map modifyMap) throws DataAccessException;
	
	/* 사업자 회원정보 수정/탈퇴 */
	public MemberVO ownerPageDetail(Map ownerpageMap) throws DataAccessException;
	public void updateMyInfo(Map modifyMap) throws DataAccessException;
	public void deleteMember(Map deleteMap) throws DataAccessException;

	
}
