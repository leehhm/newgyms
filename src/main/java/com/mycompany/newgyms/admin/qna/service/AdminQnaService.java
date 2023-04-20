package com.mycompany.newgyms.admin.qna.service;

import java.util.List;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface AdminQnaService {
	public List<QnaVO> adminQuestionList() throws Exception;
	public List<QnaVO> adminAnswerList() throws Exception;
	public void addAnswer(QnaVO qnaVO) throws Exception;
	public void modifyAnswer(QnaVO qnaVO) throws Exception;
	public void removeQna(int qna_no) throws Exception;
	public void removeAnswer(QnaVO qnaVO) throws Exception;
}
