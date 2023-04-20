package com.mycompany.newgyms.owner.review.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.review.vo.ReviewVO;

public interface OwnerReviewDAO {
	// ����� ���� �� ����
	public String selectOwnerReviewMaxNum(Map condMap) throws DataAccessException;
	// ����� ���� ��ȸ
	public List<ReviewVO> selectOwnerReviewList(Map condMap) throws DataAccessException;
	// ���� �̹��� ��ü
	public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException;
	// ���� ����
	public void selectOwnerReviewDetail(String review_no) throws DataAccessException;
}
