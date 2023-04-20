package com.mycompany.newgyms.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.qna.dao.QnaDAO;
import com.mycompany.newgyms.qna.vo.QnaVO;

@Service("qnaService")
@Transactional(propagation=Propagation.REQUIRED)
public class QnaServiceImpl implements QnaService{
	private static final String String = null;
	@Autowired
	private QnaDAO qnaDAO;
	
	@Override
	public List<QnaVO> productQuestionList(int product_id) throws Exception{
		List questionList= qnaDAO.selectproductQuestionList(product_id);
		return questionList;
	}
	
	@Override
	public List<QnaVO> productAnswerList(int product_id) throws Exception{
		List answerList= qnaDAO.selectproductAnswerList(product_id);
		return answerList;
	}
	
	@Override
	public void addQuestion(QnaVO qnaVO) throws Exception {
		qnaDAO.insertQuestion(qnaVO);
	}
	
}
