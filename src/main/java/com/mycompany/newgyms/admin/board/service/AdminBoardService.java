package com.mycompany.newgyms.admin.board.service;

import java.util.List;

import com.mycompany.newgyms.board.vo.ArticleVO;

public interface AdminBoardService {
	// �Խñ� ���
	public List<ArticleVO> adminArticleList() throws Exception;
	
	// �Խñ� ����
	public void removeArticle(int article_no) throws Exception;
}