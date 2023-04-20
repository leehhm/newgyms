package com.mycompany.newgyms.admin.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminMemberController {
	public ModelAndView adminMemberList(@RequestParam Map<String, String> dateMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView adminMemberDetail(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView modifyMemberInfo(@RequestParam Map<String, String> modifyMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView deleteMember(@RequestParam ("member_id") String member_id, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView rollbackMember(@RequestParam ("member_id") String member_id, HttpServletRequest request, HttpServletResponse response)  throws Exception;
}
