package com.mycompany.newgyms.owner.qna.dao;

import java.util.ArrayList;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface OwnerQnaDAO {
	public ArrayList selectOwnerQuestionList(String member_id) throws DataAccessException;
	public ArrayList selectOwnerAnswerList(String member_id) throws DataAccessException;
	public void insertAnswer(QnaVO qnaVO) throws DataAccessException;
	public void updateAnswer(QnaVO qnaVO) throws DataAccessException;
	public void deleteQna(int qna_no) throws DataAccessException;
	public void deleteAnswer(QnaVO qnaVO) throws DataAccessException;
	public int selectCountAnswer(QnaVO qnaVO) throws DataAccessException;
}
