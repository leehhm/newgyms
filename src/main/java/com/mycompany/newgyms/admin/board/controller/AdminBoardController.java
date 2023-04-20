package com.mycompany.newgyms.admin.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminBoardController {
	// 게시글 관리
	public ModelAndView adminArticleList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeArticle(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}