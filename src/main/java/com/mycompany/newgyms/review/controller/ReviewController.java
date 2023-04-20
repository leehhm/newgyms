package com.mycompany.newgyms.review.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface ReviewController {
	public ModelAndView reviewList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody Map<String, Object> viewReview(@RequestParam("review_no") int review_no, HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
}
