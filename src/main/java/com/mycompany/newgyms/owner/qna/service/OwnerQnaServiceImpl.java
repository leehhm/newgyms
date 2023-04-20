package com.mycompany.newgyms.owner.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.owner.qna.dao.OwnerQnaDAO;
import com.mycompany.newgyms.qna.vo.QnaVO;

@Service("ownerQnaService")
@Transactional(propagation=Propagation.REQUIRED)
public class OwnerQnaServiceImpl implements OwnerQnaService  {
	@Autowired
	private OwnerQnaDAO ownerQnaDAO;

	@Override
	public List<QnaVO> ownerQuestionList(String member_id) throws Exception{
		List questionList= ownerQnaDAO.selectOwnerQuestionList(member_id);
		return questionList;
	}
	
	@Override
	public List<QnaVO> ownerAnswerList(String member_id) throws Exception{
		List answerList= ownerQnaDAO.selectOwnerAnswerList(member_id);
		return answerList;
	}	
	
	@Override
	public void addAnswer(QnaVO qnaVO) throws Exception {
		ownerQnaDAO.insertAnswer(qnaVO);
	}
	
	@Override
	public void modifyAnswer(QnaVO qnaVO) throws Exception{
		ownerQnaDAO.updateAnswer(qnaVO);
	}
	
	@Override
	public void removeQna(int qna_no) throws Exception {
		ownerQnaDAO.deleteQna(qna_no);
	}
		
	@Override
	public void removeAnswer(QnaVO qnaVO) throws Exception {
		ownerQnaDAO.deleteAnswer(qnaVO);
	}

	
}
