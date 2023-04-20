package com.mycompany.newgyms.owner.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.owner.review.dao.OwnerReviewDAO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Service("ownerReviewService")
@Transactional(propagation=Propagation.REQUIRED)
public class OwnerReviewServiceImpl implements OwnerReviewService {
	@Autowired
	private OwnerReviewDAO ownerReviewDAO;
	
	// 사업자 리뷰 총 개수
	@Override
	public String ownerReviewMaxNum(Map condMap) throws Exception{
		String ownerReviewList = ownerReviewDAO.selectOwnerReviewMaxNum(condMap);
		return ownerReviewList;
	}
	
	// 사업자 리뷰 조회
	@Override
	public List<ReviewVO> ownerReviewList(Map condMap) throws Exception{
		List<ReviewVO> ownerReviewList = ownerReviewDAO.selectOwnerReviewList(condMap);
		return ownerReviewList;
	}
	
	// 리뷰 이미지 전체
	@Override
	public List<ReviewVO> reviewImageList(Map condMap) throws Exception{
		List<ReviewVO> reviewImageList = ownerReviewDAO.selectReviewImageList(condMap);
		return reviewImageList;
	}
	
	// 리뷰 삭제
	@Override
	public void ownerReviewDetail(String review_no) throws Exception{
	 ownerReviewDAO.selectOwnerReviewDetail(review_no);
	}
}
