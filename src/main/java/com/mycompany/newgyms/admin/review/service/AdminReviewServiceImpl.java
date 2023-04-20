package com.mycompany.newgyms.admin.review.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.review.dao.AdminReviewDAO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Service("adminReviewService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminReviewServiceImpl implements AdminReviewService{
	@Autowired
	private AdminReviewDAO adminReviewDAO;
	
	// °ü¶óÀÚ ¸®ºä ÃÑ °³¼ö
		@Override
		public String adminReviewMaxNum(Map condMap) throws Exception{
			String adminReviewList = adminReviewDAO.selectAdminReviewMaxNum(condMap);
			return adminReviewList;
		}
		
	// °ü¸®ÀÚ ¸®ºä ÃÑ °³¼ö
		@Override
		public List<ReviewVO> adminReviewList(Map condMap) throws Exception{
			List<ReviewVO> adminReviewList = adminReviewDAO.selectAdminReviewList(condMap);
			return adminReviewList;
		}
		
		// ¸®ºä ÀÌ¹ÌÁö ÀüÃ¼
		@Override
		public List<ReviewVO> reviewImageList(Map condMap) throws Exception{
			List<ReviewVO> reviewImageList = adminReviewDAO.selectReviewImageList(condMap);
			return reviewImageList;
		}
		
	// ¸®ºä »èÁ¦
		@Override
		public void adminReviewDelete(int review_no) throws Exception{
			adminReviewDAO.selectadminReviewDelete(review_no);
		}
}
