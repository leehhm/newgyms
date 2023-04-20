package com.mycompany.newgyms.qna.dao;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface QnaDAO {
	public ArrayList selectproductQuestionList(int product_id) throws DataAccessException;
	public ArrayList selectproductAnswerList(int product_id) throws DataAccessException;
	public void insertQuestion(QnaVO qnaVO) throws DataAccessException;
}
