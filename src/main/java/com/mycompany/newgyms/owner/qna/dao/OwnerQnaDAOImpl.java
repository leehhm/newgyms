package com.mycompany.newgyms.owner.qna.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.qna.vo.QnaVO;

@Repository("ownerQnaDAO")
public class OwnerQnaDAOImpl implements OwnerQnaDAO  {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public ArrayList selectOwnerQuestionList(String member_id) throws DataAccessException{
		ArrayList questionList = new ArrayList();
		questionList=(ArrayList)sqlSession.selectList("mapper.owner_qna.selectOwnerQuestionList",member_id);
		return questionList;
	}
	
	@Override
	public ArrayList selectOwnerAnswerList(String member_id) throws DataAccessException{
		ArrayList answerList = new ArrayList();
		answerList=(ArrayList)sqlSession.selectList("mapper.owner_qna.selectOwnerAnswerList",member_id);
		return answerList;
	}
	
	@Override
	public void insertAnswer(QnaVO qnaVO) throws DataAccessException {
		sqlSession.insert("mapper.owner_qna.insertAnswer", qnaVO);
		sqlSession.update("mapper.owner_qna.updateAnswerState", qnaVO);
	}
	
	@Override
	public void updateAnswer(QnaVO qnaVO) throws DataAccessException{
		sqlSession.update("mapper.owner_qna.updateAnswer",qnaVO);
	}
	
	@Override
	public void deleteQna(int qna_no) throws DataAccessException {
		sqlSession.delete("mapper.owner_qna.deleteQna", qna_no);
	}
	
	@Override
	public void deleteAnswer(QnaVO qnaVO) throws DataAccessException {
		sqlSession.delete("mapper.owner_qna.deleteAnswer", qnaVO);
		
		int CountAnswer = selectCountAnswer(qnaVO);
		System.out.println(CountAnswer);
		if (CountAnswer == 0) {
			qnaVO.setQna_answer_state("답변대기");
		} else {
			qnaVO.setQna_answer_state("답변완료");
		}
		sqlSession.update("mapper.owner_qna.updateAnswerState", qnaVO);

	}

	public int selectCountAnswer(QnaVO qnaVO) throws DataAccessException {
		int CountAnswer = sqlSession.selectOne("mapper.owner_qna.selectCountAnswer", qnaVO);
		return CountAnswer;
	}
	
}

