package com.mycompany.newgyms.owner.review.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.review.vo.ReviewVO;

public interface OwnerReviewDAO {
	// 사업자 리뷰 총 개수
	public String selectOwnerReviewMaxNum(Map condMap) throws DataAccessException;
	// 사업자 리뷰 조회
	public List<ReviewVO> selectOwnerReviewList(Map condMap) throws DataAccessException;
	// 리뷰 이미지 전체
	public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException;
	// 리뷰 삭제
	public void selectOwnerReviewDetail(String review_no) throws DataAccessException;
}
