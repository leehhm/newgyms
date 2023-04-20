package com.mycompany.newgyms.qna.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.qna.service.QnaService;
import com.mycompany.newgyms.qna.vo.QnaVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Controller("qnaController")
@RequestMapping(value = "/qna")
public class QnaControllerImpl {
	
	@Autowired
	private QnaService qnaService;

	
	
	/* 고객센터 - Q&A */
	 
	@RequestMapping(value = "/listQnas.do", method = RequestMethod.GET)
	public ModelAndView listQnas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//사이트 Qna인 경우 상품이 없으므로 0
		int product_id = 0;
		/* 질문 목록 */
		List<QnaVO> questionList = qnaService.productQuestionList(product_id);
		mav.addObject("questionList", questionList);
		
		/* 답변 목록 */
		List<QnaVO> answerList = qnaService.productAnswerList(product_id);
		mav.addObject("answerList", answerList);
		
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");

		/* 현재 로그인된 ID */
		if (memberVO != null && memberVO.getMember_id() != null) {
			String loginMember_id = memberVO.getMember_id();
			mav.addObject("loginMember_id", loginMember_id);
		}
		
		return mav;
	}
	

	@RequestMapping(value = "/addQuestion.do", method = RequestMethod.POST)
	public ResponseEntity addQuestion(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		
		qnaVO.setQna_parent_no(0);
		qnaVO.setQna_answer_state("답변대기");
		qnaVO.setMember_id(member_id);
		
		String secret = request.getParameter("secret");
		
		if(secret != null) {
		    qnaVO.setQna_secret("1"); //비밀글을 선택한 경우
		  } else {
			  qnaVO.setQna_secret("0");
		  }
		
		int product_id = qnaVO.getProduct_id();
		
		try {
			qnaService.addQuestion(qnaVO);
			message = "<script>";
			message += " alert('문의글이 등록되었습니다.. :)');";
			
			if (product_id != 0) { //상품 문의글인 경우 
				message += " location.href='" + request.getContextPath() + "/product/productDetail.do?product_id= " + product_id +"';";
			} else { //고객센터 문의글인 경우
				message += " location.href='" + request.getContextPath()  + "/qna/listQnas.do';";
			}
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			if (product_id != 0) {
				message += " location.href='" + request.getContextPath() + "/product/productDetail.do?product_id= " + product_id +"';";
			} else {
				message += " location.href='" + request.getContextPath()  + "/qna/listQnas.do';";
			}
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
}
