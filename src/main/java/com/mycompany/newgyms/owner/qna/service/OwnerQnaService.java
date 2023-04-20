package com.mycompany.newgyms.owner.qna.service;

import java.util.List;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface OwnerQnaService {
	public List<QnaVO> ownerQuestionList(String member_id) throws Exception;
	public List<QnaVO> ownerAnswerList(String member_id) throws Exception;
	public void addAnswer(QnaVO qnaVO) throws Exception;
	public void modifyAnswer(QnaVO qnaVO) throws Exception;
	public void removeQna(int qna_no) throws Exception;
	public void removeAnswer(QnaVO qnaVO) throws Exception;
}
