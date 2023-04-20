package com.mycompany.newgyms.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.board.dao.BoardDAO;
import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.board.vo.ReplyVO;

@Service("boardService")
@Transactional(propagation = Propagation.REQUIRED)
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDAO boardDAO;
	
	public List<ArticleVO> listArticles() throws Exception {
		List<ArticleVO> articlesList = boardDAO.selectAllArticlesList();
		return articlesList;
	}
	
	// �Խ��� �� ����
	@Override
	public int addNewArticle(Map articleMap) throws Exception {
		int article_no = boardDAO.selectNewArticleNO();
		articleMap.put("article_no", article_no);
		boardDAO.insertNewArticle(articleMap);
		return article_no;
	}
	
	// �Խñ� �󼼺���
	@Override
	public ArticleVO viewArticle(int article_no) throws Exception {
		ArticleVO articleVO = boardDAO.selectArticle(article_no);
		return articleVO;
	}
	
	// ��� �ҷ�����
	@Override
	public List<ReplyVO> replyList(int article_no) throws Exception {
		List<ReplyVO> replyList = boardDAO.selectReplyList(article_no);
		return replyList;
	}
	
	// ��� �ۼ��ϱ�
	@Override
	public String addReply(Map replyMap) throws Exception {
		return boardDAO.addReply(replyMap);
	}
	
	// ��� �����ϱ�
	@Override
	public String removeReply(int reply_no) throws Exception {
		return boardDAO.removeReply(reply_no);
	}
	
	@Override
	public void modArticle(Map articleMap) throws Exception {
		boardDAO.updateArticle(articleMap);
	}
	
	@Override
	public void removeArticle(int article_no) throws Exception {
		boardDAO.deleteArticle(article_no);
	}
}
