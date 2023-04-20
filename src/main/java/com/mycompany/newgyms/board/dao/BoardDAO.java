package com.mycompany.newgyms.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.board.vo.ArticleVO;

public interface BoardDAO {
	public List selectAllArticlesList() throws DataAccessException;
	public int insertNewArticle(Map articleMap) throws DataAccessException;

	public ArticleVO selectArticle(int article_no) throws DataAccessException;
	public List selectReplyList(int article_no) throws DataAccessException;
	public String addReply(Map replyMap) throws DataAccessException;
	public String removeReply(int reply_no) throws DataAccessException;
	
	public void updateArticle(Map articleMap) throws DataAccessException;
	public void deleteArticle(int article_no) throws DataAccessException;
	
	public List selectImageFileList(int article_no) throws DataAccessException;
	public int selectNewArticleNO() throws DataAccessException;
	public int selectNewImageFileNO() throws DataAccessException;

}