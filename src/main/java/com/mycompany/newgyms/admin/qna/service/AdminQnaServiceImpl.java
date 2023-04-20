package com.mycompany.newgyms.admin.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.qna.dao.AdminQnaDAO;
import com.mycompany.newgyms.qna.vo.QnaVO;

@Service("adminQnaService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminQnaServiceImpl implements AdminQnaService {
	@Autowired
	private AdminQnaDAO adminQnaDAO;

	@Override
	public List<QnaVO> adminQuestionList() throws Exception{
		List questionList= adminQnaDAO.selectAdminQuestionList();
		return questionList;
	}
	
	@Override
	public List<QnaVO> adminAnswerList() throws Exception{
		List answerList= adminQnaDAO.selectAdminAnswerList();
		return answerList;
	}	
	
	@Override
	public void addAnswer(QnaVO qnaVO) throws Exception {
		adminQnaDAO.insertAnswer(qnaVO);
	}
	
	@Override
	public void modifyAnswer(QnaVO qnaVO) throws Exception{
		adminQnaDAO.updateAnswer(qnaVO);
	}
	
	@Override
	public void removeQna(int qna_no) throws Exception {
		adminQnaDAO.deleteQna(qna_no);
	}
		
	@Override
	public void removeAnswer(QnaVO qnaVO) throws Exception {
		adminQnaDAO.deleteAnswer(qnaVO);
	}
}
