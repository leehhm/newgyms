package com.mycompany.newgyms.admin.board.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AdminBoardDAO {
	// �Խñ� ����
	public List selectAllArticleList() throws DataAccessException;
	
	// �Խñ� ����
	public void deleteArticle(int article_no) throws DataAccessException;
}