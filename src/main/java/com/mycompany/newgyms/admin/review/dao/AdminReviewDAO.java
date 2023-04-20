package com.mycompany.newgyms.admin.review.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.review.vo.ReviewVO;

public interface AdminReviewDAO {
	 public String selectAdminReviewMaxNum(Map condMap) throws DataAccessException;
	 public List<ReviewVO> selectAdminReviewList(Map condMap) throws DataAccessException;
	 public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException;
	 public void selectadminReviewDelete(int review_no) throws DataAccessException;
}
