package com.mycompany.newgyms.mypage.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.mypage.vo.PointVO;
import com.mycompany.newgyms.order.vo.OrderVO;
import com.mycompany.newgyms.qna.vo.QnaVO;
import com.mycompany.newgyms.review.vo.ReviewImageVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Repository("myPageDAO")
public class MyPageDAOImpl implements MyPageDAO {
	@Autowired
	private SqlSession sqlSession;


	// 결제내역 조회
	@Override
	public List<OrderVO> selectMyOrderList(Map condMap) throws DataAccessException {
		List<OrderVO> orderList = (List) sqlSession.selectList("mapper.mypage.selectMyOrderList", condMap);
		return orderList;
	}
	@Override
	public List<OrderVO> orderMemberListSelect(Map condMap) throws DataAccessException {
		List<OrderVO> orderMemberList = (List) sqlSession.selectList("mapper.mypage.orderMemberListSelect", condMap);
		return orderMemberList;
	}

	@Override
	public List<OrderVO> selectOrderMember(Map condMap) throws DataAccessException {
		List<OrderVO> orderMember = (List) sqlSession.selectList("mapper.mypage.selectOrderMember", condMap);
		return orderMember;
	}
	
	@Override
	public int insertNewReview(Map reviewMap) throws DataAccessException {
		int review_no = sqlSession.selectOne("mapper.mypage.selectNewReviewNO");
		reviewMap.put("review_no", review_no);
		sqlSession.insert("mapper.mypage.insertNewReview", reviewMap);
		return review_no;
	}
	
	@Override
	public void insertReviewImage(List<ReviewImageVO> review_image_list) throws DataAccessException {
		for(int i=0; i<review_image_list.size();i++){
			ReviewImageVO reviewImageVO=(ReviewImageVO)review_image_list.get(i);
			sqlSession.insert("mapper.mypage.insertReviewImage",reviewImageVO);
		}
		
	}

	@Override
	public List<OrderVO> selectMyOrderDetail(int order_id) throws DataAccessException {
		List<OrderVO> orderList = (List) sqlSession.selectList("mapper.mypage.selectMyOrderDetail", order_id);
		return orderList;
	}

	@Override
	public List<OrderVO> selectMyOrderCancel(Map orderMap) throws DataAccessException {
		List<OrderVO> orderList = (List) sqlSession.selectList("mapper.mypage.selectMyOrderCancel", orderMap);
		return orderList;
	}

	@Override
	public void refundMyOrder(Map refundMap) throws DataAccessException {
		// 환불(취소) 번호 부여 
		int refund_id = selectNewRefundId();
		refundMap.put("refund_id", refund_id);
		sqlSession.insert("mapper.mypage.refundMyOrder", refundMap);
		
		// 주문 테이블내의 주문번호를 바탕으로 결제상태 갱신
		sqlSession.update("mapper.mypage.updateOrderState", refundMap);
	}
	
	@Override
	public int selectNewRefundId() throws DataAccessException {
		return sqlSession.selectOne("mapper.mypage.selectNewRefundId");
	}

	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.mypage.maxNumSelect", condMap);
		return result;
	}

	// 적립금 조회
	@Override
	public List<PointVO> selectMyStackList(Map condMap) throws DataAccessException {
	    List<PointVO> pointList = (List) sqlSession.selectList("mapper.mypage.selectMyStackList", condMap);
	    return pointList;
	}
	
	@Override
	public String nowPointSelect(String member_id) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.mypage.nowPointSelect", member_id);
	    return result;
	}
	
	@Override
	public void addPoint(Map pointMap) throws DataAccessException {
		sqlSession.insert("mapper.mypage.pointLog", pointMap);
	}
	
	@Override
	public void usePoint(Map pointMap) throws DataAccessException {
		sqlSession.insert("mapper.mypage.pointLog", pointMap);
	}
	
	@Override
	public String maxStackSelect(Map condMap) throws DataAccessException {
	    String result = sqlSession.selectOne("mapper.mypage.maxStackSelect", condMap);
	    return result;
	}
	
	// 게시글 관리
	@Override
	public List selectMyArticleList(String member_id) throws DataAccessException {
		List<ArticleVO> myArticleList = sqlSession.selectList("mapper.mypage.selectMyArticleList", member_id);
		return myArticleList;
	}
	
	// 이용후기 관리
	@Override
	public String reviewMaxNum(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.mypage.reviewMaxNum", condMap);
		return result;
	}

	@Override
	public List<ReviewVO> listMyReviews(Map condMap) throws DataAccessException {
		List<ReviewVO> reviewList = (List) sqlSession.selectList("mapper.mypage.listMyReviews", condMap);
		return reviewList;
	}
	
	@Override
	public List<ReviewImageVO> selectReviewContentsImages(int review_no) throws DataAccessException {
		List<ReviewImageVO> reviewList = (List) sqlSession.selectList("mapper.mypage.selectReviewContentsImages", review_no);
		return reviewList;
	}

	@Override
	public void deleteReview(Map condMap) throws DataAccessException {
		sqlSession.delete("mapper.mypage.deleteReview", condMap);
	}


	//QnA 관리
	@Override
	public ArrayList selectMyQuestionList(String member_id) throws DataAccessException{
		ArrayList questionList = new ArrayList();
		questionList=(ArrayList)sqlSession.selectList("mapper.mypage.selectMyQuestionList",member_id);
		return questionList;
	}
	
	@Override
	public ArrayList selectMyAnswerList(String member_id) throws DataAccessException{
		ArrayList answerList = new ArrayList();
		answerList=(ArrayList)sqlSession.selectList("mapper.mypage.selectMyAnswerList",member_id);
		return answerList;
	}
	
	public void updateQna(QnaVO qnaVO) throws DataAccessException{
		sqlSession.insert("mapper.mypage.updateQna",qnaVO);
	}
	
	@Override
	public void deleteQna(int qna_no) throws DataAccessException{
		sqlSession.delete("mapper.mypage.deleteQna",qna_no);
	}
	
	// 회원정보 수정/탈퇴
	@Override
	public MemberVO myPageDetail(Map mypageMap) throws DataAccessException {
		MemberVO member = (MemberVO) sqlSession.selectOne("mapper.mypage.myPageDetail", mypageMap);
		return member;
	}

	@Override
	public void updateMyInfo(Map modifyMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.updateMyInfo", modifyMap);
	}

	@Override
	public void deleteMember(Map deleteMap) throws DataAccessException {
		sqlSession.update("mapper.mypage.deleteMember", deleteMap);
	}
}
