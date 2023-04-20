package com.mycompany.newgyms.owner.main.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface ownerPageController {
	
	/* 사업장 소개/관리 페이지 */
	public ModelAndView ownerPageIntroView(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
    public ModelAndView ownerPageIntroModifyForm(@RequestParam("member_id") String member_id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/* 사업장 관리 페이지 수정 */
	public @ResponseBody String ownerPageIntroModify(@RequestParam Map<String, String> modifyMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/* 사업자 회원정보 수정 탈퇴*/
	public ModelAndView ownerPageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ownerDetailInfo(@RequestParam Map<String, String> ownerpageMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView modifyMyInfo(@RequestParam Map<String, String> modifyMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView deleteMember(@RequestParam Map<String, String> deleteMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;

}
