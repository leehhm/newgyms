package com.mycompany.newgyms.owner.qna.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.qna.service.OwnerQnaService;
import com.mycompany.newgyms.qna.vo.QnaVO;

@Controller("ownerQnaController")
@RequestMapping(value = "/owner/qna")
public class OwnerQnaControllerImpl implements OwnerQnaController{


	@Autowired
	private OwnerQnaService ownerQnaService;
	@Autowired
	private QnaVO qnaVO;

	@Override
	@RequestMapping(value = "/ownerQnaList.do", method = RequestMethod.GET)
	public ModelAndView ownerQnaList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//�α��� ���� ��������
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id(); //�α����� member_id
		
		/* ���� ��� */
		List<QnaVO> questionList = ownerQnaService.ownerQuestionList(member_id);
		mav.addObject("questionList", questionList);
		
		/* �亯 ��� */
		List<QnaVO> answerList = ownerQnaService.ownerAnswerList(member_id);
		mav.addObject("answerList", answerList);
		
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/addAnswer.do", method = RequestMethod.POST)
	public ResponseEntity addAnswer(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		
		HttpSession session=request.getSession();
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id();
		qnaVO.setMember_id(member_id);
		
		qnaVO.setQna_answer_state("�亯�Ϸ�");
		
		try {
			ownerQnaService.addAnswer(qnaVO);
			message = "<script>";
			message += " alert('����� ��ϵǾ����ϴ� :)');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value = "modifyAnswer.do", method = RequestMethod.POST)
	public ResponseEntity modifyAnswer(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		//�α��� ���� ��������
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		String member_id = memberVO.getMember_id(); //�α����� member_id
		qnaVO.setMember_id(member_id);

		try {
			ownerQnaService.modifyAnswer(qnaVO);
			message = "<script>";
			message += " alert('����� �����Ǿ����ϴ� :)');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		
		return resEntity;
	}
	
	@Override
	@RequestMapping(value = "/removeQna.do", method = RequestMethod.GET)
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		HttpSession session=request.getSession();

		try {
			ownerQnaService.removeQna(qna_no);
			message = "<script>";
			message += " alert('���Ǳ��� �����Ǿ����ϴ� :)');";
			
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@RequestMapping(value = "/removeAnswer.do", method = RequestMethod.GET)
	public ResponseEntity removeAnswer(@RequestParam("qna_no") int qna_no, @RequestParam("qna_parent_no") int qna_parent_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		HttpSession session=request.getSession();

		qnaVO.setQna_no(qna_no);
		qnaVO.setQna_parent_no(qna_parent_no);
		try {
			ownerQnaService.removeAnswer(qnaVO);
			message = "<script>";
			message += " alert('����� �����Ǿ����ϴ� :)');";
			
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ���');";
			message += " location.href='" + request.getContextPath()  + "/owner/qna/ownerQnaList.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
}
