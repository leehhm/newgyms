package com.mycompany.newgyms.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface NoticeController {
	public ModelAndView listNotices(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewNotice(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView noticeForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modNoticeForm(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity updateNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity removeNotice(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
