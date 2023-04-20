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
	
	// ������ ���� �� ����
		 @Override
		   public String selectAdminReviewMaxNum(Map condMap) throws DataAccessException {
		      String result = sqlSession.selectOne("mapper.admin_review.selectAdminReviewMaxNum", condMap);
		       return result;
		   }
		 
	// ������ ���� ��ȸ
		 @Override
		 public List<ReviewVO> selectAdminReviewList(Map condMap) throws DataAccessException {
			 List<ReviewVO> result = sqlSession.selectList("mapper.admin_review.selectAdminReviewList", condMap);
			 return result;
		 }
		 
	// ���� �̹��� ��ü
		 @Override
		 public List<ReviewVO> selectReviewImageList(Map condMap) throws DataAccessException {
			 List<ReviewVO> result = sqlSession.selectList("mapper.admin_review.selectReviewImageList", condMap);
			 return result;
		 }
		 
	// ���� ����
		 @Override
		 public void selectadminReviewDelete(int review_no) throws DataAccessException {
			sqlSession.delete("mapper.owner_review.selectadminReviewDelete", review_no);
			sqlSession.delete("mapper.owner_review.selectadminReviewImageDelete", review_no);
		 }
}
