package com.mycompany.newgyms.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;

public interface MemberController {
	public ModelAndView login(@RequestParam Map<String, String> loginMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView joinCheck(@RequestParam Map<String, String> joinCheckMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity joinMember(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity joinOwner(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity overlappedId(@RequestParam("id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity overlappedEid(@RequestParam("eid") String eid,HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity kakaoJoin(@ModelAttribute("member") MemberVO member,
            HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView searchId(@RequestParam Map<String, String> searchidMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchPw(@RequestParam Map<String, String> searchidMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchId1(@RequestParam Map<String, String> searchidMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView newPw(@RequestParam Map<String, String> searchidMap,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView sendEmailId(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView sendEmailPw(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
