package com.mycompany.newgyms.admin.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.owner.qna.dao.OwnerQnaDAO;
import com.mycompany.newgyms.qna.vo.QnaVO;

@Repository("adminQnaDAO")
public class AdminQnaDAOImpl implements AdminQnaDAO  {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList selectAdminQuestionList() throws DataAccessException{
		ArrayList questionList = new ArrayList();
		questionList=(ArrayList)sqlSession.selectList("mapper.admin_qna.selectAdminQuestionList");
		return questionList;
	}
	
	@Override
	public ArrayList selectAdminAnswerList() throws DataAccessException{
		ArrayList answerList = new ArrayList();
		answerList=(ArrayList)sqlSession.selectList("mapper.admin_qna.selectAdminAnswerList");
		return answerList;
	}
	
	@Override
	public void insertAnswer(QnaVO qnaVO) throws DataAccessException {
		sqlSession.insert("mapper.admin_qna.insertAnswer", qnaVO);
		sqlSession.update("mapper.admin_qna.updateAnswerState", qnaVO);
	}
	
	@Override
	public void updateAnswer(QnaVO qnaVO) throws DataAccessException{
		sqlSession.update("mapper.admin_qna.updateAnswer",qnaVO);
	}
	
	@Override
	public void deleteQna(int qna_no) throws DataAccessException {
		sqlSession.delete("mapper.admin_qna.deleteQna", qna_no);
	}
	
	@Override
	public void deleteAnswer(QnaVO qnaVO) throws DataAccessException {
		sqlSession.delete("mapper.admin_qna.deleteAnswer", qnaVO);
		
		int CountAnswer = selectCountAnswer(qnaVO);
		System.out.println(CountAnswer);
		if (CountAnswer == 0) {
			qnaVO.setQna_answer_state("답변대기");
		} else {
			qnaVO.setQna_answer_state("답변완료");
		}
		sqlSession.update("mapper.admin_qna.updateAnswerState", qnaVO);

	}

	public int selectCountAnswer(QnaVO qnaVO) throws DataAccessException {
		int CountAnswer = sqlSession.selectOne("mapper.admin_qna.selectCountAnswer", qnaVO);
		return CountAnswer;
	}
}
