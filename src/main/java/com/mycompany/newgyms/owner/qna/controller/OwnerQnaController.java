package com.mycompany.newgyms.owner.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface OwnerQnaController {
	public ModelAndView ownerQnaList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addAnswer(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity modifyAnswer(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeAnswer(@RequestParam("qna_no") int qna_no, @RequestParam("qna_parent_no") int qna_parent_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
