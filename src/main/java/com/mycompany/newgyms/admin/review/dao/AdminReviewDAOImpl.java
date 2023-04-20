package com.mycompany.newgyms.admin.review.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.review.vo.ReviewVO;

@Repository("adminReviewDAO")
public class AdminReviewDAOImpl implements AdminReviewDAO{
	@Autowired
	private SqlSession sqlSession;
	
	// 관리자 리뷰 총 개수
		 @Override
		   public String selectAdminReviewMaxNum(Map condMap) throws DataAccessException {
		      String result = sqlSession.selectOne("mapper.admin_review.selectAdminReviewMaxNum", condMap);
		       return result;
		   }
		 
	// 관리자 리뷰 조회
		 @Override
		 public List<ReviewVO> selectAdminReviewList(Map condMap) throws DataAccessException {
			 List<ReviewVO> result = sqlSession.selectList("mapper.admin_review.selectAdminReviewList", condMap);
			 return result;
		 }
		 
	// 리뷰 이미지 전체
		 @Override
		 public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException {
			 List<ReviewVO> result = sqlSession.selectList("mapper.admin_review.selectReviewImageList", condMap);
			 return result;
		 }
		 
	// 리뷰 삭제
		 @Override
		 public void selectadminReviewDelete(int review_no) throws DataAccessException {
			sqlSession.delete("mapper.owner_review.selectadminReviewDelete", review_no);
			sqlSession.delete("mapper.owner_review.selectadminReviewImageDelete", review_no);
		 }
}
