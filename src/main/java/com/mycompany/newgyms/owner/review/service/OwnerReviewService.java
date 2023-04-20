package com.mycompany.newgyms.owner.review.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.review.vo.ReviewVO;

public interface OwnerReviewService {
	// 사업자 리뷰 총 개수
	public String ownerReviewMaxNum(Map condMap) throws Exception;
	// 사업자 리뷰 조회
	public List<ReviewVO> ownerReviewList(Map condMap) throws Exception;
	// 리뷰 이미지 전체
	public List<ReviewVO> reviewImageList(Map condMap) throws Exception;
	// 리뷰 이미지 전체
	public void ownerReviewDetail(String review_no) throws Exception;
}
