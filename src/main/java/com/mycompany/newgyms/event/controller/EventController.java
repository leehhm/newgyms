package com.mycompany.newgyms.event.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface EventController {
	public ModelAndView listEvents(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView eventSorting(@RequestParam("sort") String sort, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView viewEvent(@RequestParam("event_no") int event_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity addNewEvent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ModelAndView eventForm(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modEventForm(@RequestParam("event_no") int event_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity updateEvent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception;
	public ResponseEntity removeEvent(@RequestParam("event_no") int event_no, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
