package com.mycompany.newgyms.owner.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.review.vo.ReviewVO;

@Repository("ownerReviewDAO")
public class OwnerReviewDAOImpl implements OwnerReviewDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 사업자 리뷰 총 개수
	 @Override
	   public String selectOwnerReviewMaxNum(Map condMap) throws DataAccessException {
	      String result = sqlSession.selectOne("mapper.owner_review.selectOwnerReviewMaxNum", condMap);
	       return result;
	   }
	 
	// 사업자 리뷰 조회
	 @Override
	 public List<ReviewVO> selectOwnerReviewList(Map condMap) throws DataAccessException {
		 List<ReviewVO> result = sqlSession.selectList("mapper.owner_review.selectOwnerReviewList", condMap);
		 return result;
	 }
	 
	 // 리뷰 이미지 전체
	 @Override
	 public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException {
		 List<ReviewVO> result = sqlSession.selectList("mapper.owner_review.selectReviewImageList", condMap);
		 return result;
	 }
	 
	 // 리뷰 삭제
	 @Override
	 public void selectOwnerReviewDetail(String review_no) throws DataAccessException {
		sqlSession.delete("mapper.owner_review.selectOwnerReviewDetail", review_no);
		sqlSession.delete("mapper.owner_review.selectOwnerReviewImageDetail", review_no);
	 }
}
