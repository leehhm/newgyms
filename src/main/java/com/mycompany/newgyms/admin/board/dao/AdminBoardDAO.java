package com.mycompany.newgyms.admin.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AdminBoardDAO {
	// 게시글 관리
	public List selectAllArticleList() throws DataAccessException;
	
	// 게시글 삭제
	public void deleteArticle(int article_no) throws DataAccessException;
}