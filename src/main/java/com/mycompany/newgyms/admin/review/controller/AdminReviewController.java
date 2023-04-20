package com.mycompany.newgyms.admin.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminReviewController {
	public ModelAndView adminReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity adminReviewDelete(@RequestParam int review_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
