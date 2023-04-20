package com.mycompany.newgyms.owner.review.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.review.vo.ReviewVO;

public interface OwnerReviewService {
	// ����� ���� �� ����
	public String ownerReviewMaxNum(Map condMap) throws Exception;
	// ����� ���� ��ȸ
	public List<ReviewVO> ownerReviewList(Map condMap) throws Exception;
	// ���� �̹��� ��ü
	public List<ReviewVO> reviewImageList(Map condMap) throws Exception;
	// ���� �̹��� ��ü
	public void ownerReviewDetail(String review_no) throws Exception;
}
