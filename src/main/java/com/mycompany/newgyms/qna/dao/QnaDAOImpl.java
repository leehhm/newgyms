package com.mycompany.newgyms.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.qna.vo.QnaVO;

@Repository("qnaDAO")
public class QnaDAOImpl implements QnaDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public ArrayList selectproductQuestionList(int product_id) throws DataAccessException{
		ArrayList questionList = new ArrayList();
		questionList=(ArrayList)sqlSession.selectList("mapper.qna.selectProductQuestionList",product_id);
		return questionList;
	}
	
	@Override
	public ArrayList selectproductAnswerList(int product_id) throws DataAccessException{
		ArrayList answerList = new ArrayList();
		answerList=(ArrayList)sqlSession.selectList("mapper.qna.selectProductAnswerList",product_id);
		return answerList;
	}
	 
	@Override
	public void insertQuestion(QnaVO qnaVO) throws DataAccessException {
		sqlSession.insert("mapper.qna.insertQuestion", qnaVO);
	}

}
