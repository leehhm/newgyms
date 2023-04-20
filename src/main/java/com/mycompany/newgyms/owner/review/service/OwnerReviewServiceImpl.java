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
	
	// ����� ���� �� ����
	@Override
	public String ownerReviewMaxNum(Map condMap) throws Exception{
		String ownerReviewList = ownerReviewDAO.selectOwnerReviewMaxNum(condMap);
		return ownerReviewList;
	}
	
	// ����� ���� ��ȸ
	@Override
	public List<ReviewVO> ownerReviewList(Map condMap) throws Exception{
		List<ReviewVO> ownerReviewList = ownerReviewDAO.selectOwnerReviewList(condMap);
		return ownerReviewList;
	}
	
	// ���� �̹��� ��ü
	@Override
	public List<ReviewVO> reviewImageList(Map condMap) throws Exception{
		List<ReviewVO> reviewImageList = ownerReviewDAO.selectReviewImageList(condMap);
		return reviewImageList;
	}
	
	// ���� ����
	@Override
	public void ownerReviewDetail(String review_no) throws Exception{
	 ownerReviewDAO.selectOwnerReviewDetail(review_no);
	}
}
