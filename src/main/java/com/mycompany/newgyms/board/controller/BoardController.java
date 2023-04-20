package com.mycompany.newgyms.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface BoardController {
	public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewArticle(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String addReply(@RequestParam("article_no") int article_no, @RequestParam("reply_id") String reply_id, @RequestParam("reply_content") String reply_content, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String removeReply(@RequestParam("reply_no") int reply_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modArticleForm(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity updateArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity removeArticle(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}