package com.mycompany.newgyms.review.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.review.vo.ReviewImageVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

public interface ReviewDAO {
	public List<ReviewVO> selectproductReviewList(int product_id) throws DataAccessException;
	public List<ReviewImageVO> selectProductReviewImageList(List<ReviewVO>reviewList) throws DataAccessException;
	
	public ArrayList selectReviewList() throws DataAccessException;
	public ReviewVO selectReviewDetail(int review_no)  throws DataAccessException;
	public ArrayList selectReviewImageList(int review_no) throws DataAccessException;
}
