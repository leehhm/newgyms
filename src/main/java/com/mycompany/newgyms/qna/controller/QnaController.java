package com.mycompany.newgyms.qna.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.mycompany.newgyms.qna.vo.QnaVO;

public interface QnaController {
	public ResponseEntity addQuestion(@ModelAttribute("qnaVO") QnaVO qnaVO, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
