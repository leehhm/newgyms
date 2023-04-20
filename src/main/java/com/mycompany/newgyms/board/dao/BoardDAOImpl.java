package com.mycompany.newgyms.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.board.vo.ImageVO;
import com.mycompany.newgyms.board.vo.ReplyVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
		return articlesList;
	}
	
	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int article_no = selectNewArticleNO();
		articleMap.put("article_no", article_no);
		sqlSession.insert("mapper.board.insertNewArticle", articleMap);
		return article_no;
	}
	
	@Override
	public ArticleVO selectArticle(int article_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectArticle", article_no);
	}
	
	@Override
	public List selectReplyList(int article_no) throws DataAccessException {
		List<ReplyVO> replyList = sqlSession.selectList("mapper.board.selectReplyList", article_no);
		return replyList;
	}
	
	@Override
	public String addReply(Map replyMap) throws DataAccessException {
		sqlSession.insert("mapper.board.addReply", replyMap);
		String result = "success";
		return result;
	}
	
	@Override
	public String removeReply(int reply_no) throws DataAccessException {
		sqlSession.delete("mapper.board.removeReply", reply_no);
		String result = "success";
		return result;
	}
	
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}
	
	@Override
	public void deleteArticle(int article_no) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", article_no);
	}
	
	@Override
	public List selectImageFileList(int article_no) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.board.selectImageFileList", article_no);
		return imageFileList;
	}
	
	@Override
	public int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}

	@Override
	public int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewImageFileNO");
	}
}
