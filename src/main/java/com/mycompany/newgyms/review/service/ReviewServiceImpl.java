package com.mycompany.newgyms.review.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.review.dao.ReviewDAO;
import com.mycompany.newgyms.review.vo.ReviewImageVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Service("reviewService")
@Transactional(propagation=Propagation.REQUIRED)
public class ReviewServiceImpl implements ReviewService{
	private static final String String = null;
	@Autowired
	private ReviewDAO reviewDAO;
	
	
	public Map<String ,List> productReviewList(int product_id) throws Exception{
		Map<String,List> reviewMap=new HashMap<String,List>();
		
		List<ReviewVO> reviewList= reviewDAO.selectproductReviewList(product_id);
		reviewMap.put("reviewList", reviewList);
		
		if(reviewList.size()!=0){ 
			List<ReviewImageVO> reviewImageList=reviewDAO.selectProductReviewImageList(reviewList);
			reviewMap.put("reviewImageList",reviewImageList);
		}

		return reviewMap;

	}
	
	public List<ReviewVO> reviewList() throws Exception {
		List reviewList = reviewDAO.selectReviewList();
		return reviewList;
	}
	
	public ReviewVO viewReview(int review_no) throws Exception {
		ReviewVO reviewVO = reviewDAO.selectReviewDetail(review_no);
		return reviewVO;
	}
	
	public List<ReviewImageVO> reviewImageList(int review_no) throws Exception {
		List reviewImageList = reviewDAO.selectReviewImageList(review_no);
		return reviewImageList;
	}
	
}
