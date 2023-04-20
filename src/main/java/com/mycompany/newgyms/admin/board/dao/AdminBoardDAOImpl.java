package com.mycompany.newgyms.admin.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ArticleVO;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ArticleVO;

@Repository("adminBoardDAO")
public class AdminBoardDAOImpl implements AdminBoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// �Խñ� ����
	@Override
	public List selectAllArticleList() throws DataAccessException {
		List<ArticleVO> adminArticleList = sqlSession.selectList("mapper.admin_board.selectAllArticleList");
		return adminArticleList;
	}
	
	// �Խñ� ����
	@Override
	public void deleteArticle(int article_no) throws DataAccessException {
		sqlSession.delete("mapper.admin_board.deleteArticle", article_no);
	}
}
