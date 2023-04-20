package com.mycompany.newgyms.admin.board.service;

import java.util.List;

import com.mycompany.newgyms.board.vo.ArticleVO;

public interface AdminBoardService {
	// 게시글 목록
	public List<ArticleVO> adminArticleList() throws Exception;
	
	// 게시글 삭제
	public void removeArticle(int article_no) throws Exception;
}