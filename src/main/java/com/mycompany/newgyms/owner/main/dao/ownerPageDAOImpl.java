package com.mycompany.newgyms.owner.main.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.main.vo.OwnerPageVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Repository("ownerPageDAO")
public class ownerPageDAOImpl implements ownerPageDAO {
	@Autowired
	private SqlSession sqlSession;
	
	/* 사업장 소개/관리 페이지 */
	@Override
	public List<ProductVO> selectProductList(String member_id) throws DataAccessException {
		List<ProductVO> productVO = (List)sqlSession.selectList("mapper.owner_main.selectCenterProductList", member_id);
		return productVO;
	}
	
	@Override
	public OwnerPageVO selectOwnerPageIntroView(String member_id) throws DataAccessException {
		OwnerPageVO ownerPageVO = (OwnerPageVO)sqlSession.selectOne("mapper.owner_main.selectOwnerPageIntroView", member_id);
		return ownerPageVO;
	}
	
	@Override
	public MemberVO selectOwnerPageIntroInfo(String member_id) throws DataAccessException {
		MemberVO memberVO = (MemberVO)sqlSession.selectOne("mapper.owner_main.selectOwnerPageIntroInfo", member_id);
		return memberVO;
	}
	
	/* 사업장 관리 수정 */
	@Override
	public String updateOwnerPage(Map modifyMap) throws DataAccessException {
		sqlSession.update("mapper.owner_main.updateOwnerPage", modifyMap);
		String result = "success";
		return result;
	}
	
	/* 사업자 회원정보 수정/탈퇴 */
	@Override
	public MemberVO ownerPageDetail(Map ownerpageMap) throws DataAccessException {
		MemberVO member = (MemberVO) sqlSession.selectOne("mapper.owner_main.ownerPageDetail", ownerpageMap);
		return member;
	}
	
	@Override
	public void updateMyInfo(Map modifyMap) throws DataAccessException {
		sqlSession.update("mapper.owner_main.updateMyInfo", modifyMap);
	}
	
	@Override
	public void deleteMember(Map deleteMap) throws DataAccessException {
		sqlSession.update("mapper.owner_main.deleteMember", deleteMap);
	}
}
