package com.mycompany.newgyms.admin.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.board.dao.AdminBoardDAO;
import com.mycompany.newgyms.board.vo.ArticleVO;

@Service("adminBoardService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminBoardServiceImpl implements AdminBoardService {
	@Autowired
	private AdminBoardDAO adminBoardDAO;
	
	// 게시글 관리
	@Override
	public List<ArticleVO> adminArticleList() throws Exception {
		List<ArticleVO> adminArticleList = adminBoardDAO.selectAllArticleList();
		return adminArticleList;
	}

	
	// 게시글 삭제
	@Override
	public void removeArticle(int article_no) throws Exception {
		adminBoardDAO.deleteArticle(article_no);
	}
}
