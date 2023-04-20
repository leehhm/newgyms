package com.mycompany.newgyms.board.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.board.vo.ReplyVO;

public interface BoardService {
	public List<ArticleVO> listArticles() throws Exception;
	public int addNewArticle(Map articleMap) throws Exception;
	public ArticleVO viewArticle(int article_no) throws Exception;
	public List<ReplyVO> replyList(int article_no) throws Exception;
	public String addReply(Map replyMap) throws Exception;
	public String removeReply(int reply_no) throws Exception;
	public void modArticle(Map articleMap) throws Exception;
	public void removeArticle(int article_no) throws Exception;
}
