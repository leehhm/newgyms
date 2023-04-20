package com.mycompany.newgyms.admin.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminNoticeController {
	public ModelAndView adminNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeNotice(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}