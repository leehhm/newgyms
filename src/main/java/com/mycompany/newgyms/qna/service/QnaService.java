package com.mycompany.newgyms.qna.service;

import java.util.List;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface QnaService {
	public List<QnaVO> productQuestionList(int product_id) throws Exception;
	public List<QnaVO> productAnswerList(int product_id) throws Exception;
	public void addQuestion(QnaVO qnaVO) throws Exception;
}
