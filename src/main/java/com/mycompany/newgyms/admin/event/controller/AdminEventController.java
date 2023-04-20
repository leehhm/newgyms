package com.mycompany.newgyms.admin.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminEventController {
	// 이벤트 목록
	public ModelAndView adminEventList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 이벤트 삭제
	public ResponseEntity removeEvent(@RequestParam("event_no") int event_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}