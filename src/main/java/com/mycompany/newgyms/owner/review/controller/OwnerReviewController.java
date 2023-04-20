package com.mycompany.newgyms.owner.review.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface OwnerReviewController {
	public ModelAndView orderReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity ownerReviewDetail(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
