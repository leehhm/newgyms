package com.mycompany.newgyms.mypage.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface MyPageController {
	
	// 결제내역 조회
	public ModelAndView myOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity reviewUpLoad(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView myOrderDetail(@RequestParam("order_id") int order_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myOrderCancel(@RequestParam("total_price") int total_price, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myOrderRefund(@RequestParam Map<String, Object> refundMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 적립금 조회
	public ModelAndView myStackList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 게시글 관리
	public ModelAndView myArticleList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 이용후기 관리
	public ModelAndView myReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity myReviewDelete(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity myreviewModify(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	
	// QnA 관리
	public ModelAndView myQnaList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ResponseEntity modifyQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 회원정보 수정/탈퇴
	public ModelAndView myPageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView myDetailInfo(@RequestParam Map<String, String> mypageMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modifyMyInfo(@RequestParam Map<String, String> modifyMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteMember(@RequestParam Map<String, String> deleteMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
}