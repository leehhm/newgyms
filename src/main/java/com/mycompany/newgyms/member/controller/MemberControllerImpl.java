package com.mycompany.newgyms.member.controller;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.common.base.BaseController;
import com.mycompany.newgyms.member.service.KakaoService;
import com.mycompany.newgyms.member.service.MemberService;
import com.mycompany.newgyms.member.vo.MemberVO;

@Controller("memberController")
@RequestMapping(value = "/member")
public class MemberControllerImpl extends BaseController implements MemberController {
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private KakaoService kakaoService;

	@Override
	   @RequestMapping(value = "/login.do", method = RequestMethod.POST)
	   public ModelAndView login(@RequestParam Map<String, String> loginMap, HttpServletRequest request,
	         HttpServletResponse response) throws Exception {
	      response.setContentType("text/html; charset=UTF-8");
	      request.setCharacterEncoding("utf-8");
	      ModelAndView mav = new ModelAndView();
	      memberVO = memberService.login(loginMap);
	      
	      if (memberVO.getDel_yn().equals("Y")) {
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('탈퇴한 회원입니다.');</script>");
	         out.flush();
	         mav.setViewName("/member/loginForm");
	      } else if (memberVO != null && memberVO.getMember_id() != null) {
	         HttpSession session = request.getSession();
	         session = request.getSession();
	         session.setAttribute("isLogOn", true);
	         session.setAttribute("memberInfo", memberVO);

	         String action = (String) session.getAttribute("action");
	         if (action != null && action.equals("/order/orderEachGoods.do")) {
	            mav.setViewName("forward:" + action);
	         } else {
	            mav.setViewName("redirect:/main/main.do");
	         }
	      } else {
	         PrintWriter out = response.getWriter();
	         out.println("<script>alert('아이디나 비밀번호가 올바르지 않습니다.');</script>");
	         out.flush();
	         mav.setViewName("/member/loginForm");
	      }
	      return mav;
	   }

	@Override
	@RequestMapping(value = "/logout.do", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		session.setAttribute("isLogOn", false);
		session.removeAttribute("memberInfo");
		mav.setViewName("redirect:/main/main.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/joinCheck.do", method = RequestMethod.POST)
	public ModelAndView joinCheck(@RequestParam Map<String, String> joinCheckMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		memberVO = memberService.joinCheck(joinCheckMap);
		String member_name = request.getParameter("member_name");
		String member_rrn1 = request.getParameter("member_rrn1");
		String member_rrn2 = request.getParameter("member_rrn2");
		String join_type = request.getParameter("join_type");
		HttpSession session = request.getSession();
		session = request.getSession();
		session.setAttribute("member_name", member_name);
		session.setAttribute("member_rrn1", member_rrn1);
		session.setAttribute("member_rrn2", member_rrn2);
		session.setAttribute("join_type", join_type);

		if (memberVO != null && memberVO.getMember_id() != null) {
			// 이미 만들어진 계정(아이디)가 있으면 알림 페이지로
			String member_id = memberVO.getMember_id();
			System.out.println(member_id);
			session.setAttribute("member_id", member_id);
			mav.setViewName("forward:/member/joinAlready.do");

		} else {
			// 중복 조회된 계정(아이디)가 없으면 회원가입 페이지로
			if (join_type.equals("101")) { // join_type 101 == 일반회원
				mav.setViewName("redirect:/member/joinMember.do");
			} else { // join_type 102 == 사업자 회원
				mav.setViewName("redirect:/member/joinOwner.do");
			}

		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/joinMember.do", method = RequestMethod.POST)
	public ResponseEntity joinMember(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String member_rrn1 = request.getParameter("member_rrn1");
		String member_birth_y = member_rrn1.substring(0, 2);
		String member_birth_m = member_rrn1.substring(2, 4);
		String member_birth_d = member_rrn1.substring(4, 6);
		
		
		// 1900 or 2000 년도 붙이기
		if (Integer.parseInt(member_birth_y) >= 30) { // 1930 ~ 1999
			member_birth_y = "19" + member_birth_y;
		} else if (Integer.parseInt(member_birth_y) < 30) { // 2000 ~ 2029
			member_birth_y = "20" + member_birth_y;
		}
		
		// 1 ~ 9월 한자리로 자르기
		member_birth_m = member_birth_m.replace("0", "");
		
		_memberVO.setMember_birth_y(member_birth_y);
		_memberVO.setMember_birth_m(member_birth_m);
		_memberVO.setMember_birth_d(member_birth_d);

		try {
			memberService.joinMember(_memberVO);
			message = "<script>";
			message += " alert('뉴짐스의 회원이 되신것을 환영합니다. :)');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/joinMember.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/joinOwner.do", method = RequestMethod.POST)
	public ResponseEntity joinOwner(@ModelAttribute("memberVO") MemberVO _memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		String member_rrn1 = request.getParameter("member_rrn1");
		String member_birth_y = member_rrn1.substring(0, 2);
		String member_birth_m = member_rrn1.substring(2, 4);
		String member_birth_d = member_rrn1.substring(4, 6);
		_memberVO.setMember_birth_y(member_birth_y);
		_memberVO.setMember_birth_m(member_birth_m);
		_memberVO.setMember_birth_d(member_birth_d);

		try {
			memberService.joinOwner(_memberVO);
			message = "<script>";
			message += " alert('뉴짐스의 회원이 되신것을 환영합니다. :)');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";

		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/joinAdmin.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/overlappedId.do", method = RequestMethod.POST)
	public ResponseEntity overlappedId(@RequestParam("id") String id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlappedId(id);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/overlappedEid.do", method = RequestMethod.POST)
	public ResponseEntity overlappedEid(@RequestParam("eid") String eid, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseEntity resEntity = null;
		String result = memberService.overlappedEid(eid);
		resEntity = new ResponseEntity(result, HttpStatus.OK);
		return resEntity;
	}

	@Override
	@RequestMapping(value = "/kakaoLogin.do")
	public ModelAndView kakaoLogin(@RequestParam("code") String code, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		// 접속토큰 get
		String access_token = kakaoService.getReturnAccessToken(code);
		Map<String, Object> userInfo = kakaoService.getUserInfo(access_token);
		System.out.println("userInfo : " + userInfo);

		String member_id = (String) userInfo.get("id");
		String member_name = (String) userInfo.get("nickname");
		String email = (String) userInfo.get("email");
		String gender = (String) userInfo.get("gender");
		String member_gender;
		// 성별 진단 male = M(남성), 그 외 = W(여성)
		if (gender.equals("male")) {
			member_gender = "M";
		} else {
			member_gender = "W";
		}
		String member_pw = member_id;

		memberVO = memberService.kakaoLogin(member_id);
		
		// 카카오 계정으로 받은 아이디로 가입되어 있는지 확인 후 있으면 로그인 진행
		if (memberVO != null && memberVO.getMember_id() != null) {
			System.out.println("카카오 로그인을 진행합니다.");
			session.setAttribute("isLogon", true);
			session.setAttribute("access_token", access_token);

			try {
				// 아이디를 바탕으로 회원 정보를 가져옴
				session.setAttribute("memberInfo", memberVO);
				session.setAttribute("isLogOn", true);
				mav.setViewName("redirect:/main/main.do");
			} catch (Exception e) {
				e.printStackTrace();
				mav.setViewName("redirect:/main/main.do");
			}

		} else { // 없을 시 카카오 회원가입(추가입력) 페이지로 이동
			System.out.println("카카오 회원가입을 진행합니다.");
			String result = String.valueOf(memberService.overlappedId(member_id));
			System.out.println(result);
			if (result.equals("false")) {
				String email1 = email.substring(0, email.indexOf("@"));
				String email2 = email.substring(email.indexOf("@") + 1);
				System.out.println(email1);
				System.out.println(email2);
				
				MemberVO memberVO = new MemberVO();
				memberVO.setEmail1(email1);
				memberVO.setEmail2(email2);
				memberVO.setMember_id(member_id);
				memberVO.setMember_pw(member_pw);
				memberVO.setMember_name(member_name);
				memberVO.setMember_gender(member_gender);

				session.setAttribute("memberInfo", memberVO);
				mav.setViewName("redirect:/member/kakaoJoinForm.do");
			} else {
				mav.setViewName("redirect:/main/main.do");
			}
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/kakaoJoin.do", method = RequestMethod.POST)
	public ResponseEntity kakaoJoin(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			memberService.kakaoJoin(memberVO);
			message = "<script>";
			message += " alert('뉴짐스의 회원이 되신것을 환영합니다. :)');";
			message += " location.href='" + request.getContextPath() + "/member/loginForm.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요');";
			message += " location.href='" + request.getContextPath() + "/member/kakaoJoinForm.do';";
			message += " </script>";
			e.printStackTrace();
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}

	// 핸드폰 아이디 조회
	@Override
	@RequestMapping(value = "/searchId.do", method = RequestMethod.POST)
	public ModelAndView searchId(@RequestParam Map<String, String> searchidMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = memberService.searchId(searchidMap);
		String ran = (String) searchidMap.get("ran");
		String ok = (String) searchidMap.get("ok");
		System.out.println(ran);
		System.out.println(ok);
		if (ran.equals(ok)) {
			if (memberVO != null) {
				String member_id = memberVO.getMember_id();
				String member_pw = memberVO.getMember_pw();
				session.setAttribute("member_id", member_id);
				session.setAttribute("member_pw", member_pw);
				mav.setViewName("forward:/member/foundIdForm.do");
			} else {
				mav.setViewName("forward:/member/searchId1.do");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('인증번호가 올바르지 않습니다.');</script>");
			out.flush();
			mav.setViewName("forward:/member/searchIdForm.do");
		}
		return mav;
	}

	// 이메일 아이디 조회
	@Override
	@RequestMapping(value = "/searchId1.do", method = RequestMethod.POST)
	public ModelAndView searchId1(@RequestParam Map<String, String> searchidMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = memberService.searchId1(searchidMap);
		if (memberVO != null) {
			String member_id = memberVO.getMember_id();
			session.setAttribute("member_id", member_id);
			mav.setViewName("forward:/member/foundIdForm.do");
		} else {
			String message = "회원정보를 찾을 수 없습니다.";
			mav.addObject("member_id", message);
			mav.setViewName("redirect:/member/foundIdForm.do");
		}
		return mav;
	}

	// 비밀번호 조희
	@Override
	@RequestMapping(value = "/searchPw.do", method = RequestMethod.POST)
	public ModelAndView searchPw(@RequestParam Map<String, String> searchpwMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		memberVO = memberService.searchPw(searchpwMap);
		String ran_num = searchpwMap.get("ran");
		String ok1_num = searchpwMap.get("ok1");
		System.out.println(ran_num);
		System.out.println(ok1_num);

		if (ran_num.equals(ok1_num)) {
			if (memberVO != null) {
				String member_id = memberVO.getMember_id();
				session.setAttribute("member_id", member_id);
				mav.setViewName("forward:/member/foundPwForm.do");
			} else {
				mav.setViewName("forward:/member/searchPwForm.do");
			}
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('인증번호가 올바르지 않습니다.');</script>");
			out.flush();
			mav.setViewName("forward:/member/searchPwForm.do");
		}
		return mav;
	}

	// 새로운 비밀번호
	@Override
	@RequestMapping(value = "/newPw.do", method = RequestMethod.POST)
	public ModelAndView newPw(@RequestParam Map<String, String> searchpwMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		String member_pw = (String) searchpwMap.get("member_pw");
		String member_pw_confirm = (String) searchpwMap.get("member_pw_confirm");
		if (member_pw.equals(member_pw_confirm)) {
			memberService.newPw(searchpwMap);
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 변경이 완료되었습니다.');</script>");
			out.flush();
			mav.setViewName("/member/loginForm");
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 다릅니다.');</script>");
			out.flush();
			mav.setViewName("forward:/member/foundPwForm.do");
		}
		return mav;
	}

	@Override
	@RequestMapping(value = "/sendEmailId.do", method = RequestMethod.POST)
	public ModelAndView sendEmailId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		int ran = (int) (Math.random() * 999999) + 1;
		String member_name = (String) request.getParameter("member_name");
		String email1 = (String) request.getParameter("email1");
		String email2 = (String) request.getParameter("email2");
		String email = email1 + "@" + email2;
		String host = "smtp.naver.com";
		final String username = "leehm0311@naver.com";
		final String password = "asdfzxcv1!";
		int port = 587;
		String subject = "newGym's 아이디 인증번호입니다.";
		String body = "인증번호는 " + ran + "입니다.";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smpt.ssl.trust", host);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("leehm0311@naver.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);

		mav.addObject("ran", ran);
		mav.addObject("member_name", member_name);
		mav.addObject("email1", email1);
		mav.setViewName("forward:/member/searchIdForm.do");
		return mav;
	}

	@Override
	@RequestMapping(value = "/sendEmailPw.do", method = RequestMethod.POST)
	public ModelAndView sendEmailPw(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		int ran = (int) (Math.random() * 999999) + 1;
		String member_id = (String) request.getParameter("member_id");
		String member_name = (String) request.getParameter("member_name");
		String email1 = (String) request.getParameter("email1");
		String email2 = (String) request.getParameter("email2");
		String email = email1 + "@" + email2;
		String host = "smtp.naver.com";
		final String username = "leehm0311";
		final String password = "asdfzxcv1!";
		int port = 587;
		String subject = "newGym's 비밀번호 인증번호입니다.";
		String body = "인증번호는 " + ran + "입니다.";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smpt.ssl.trust", host);
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un = username;
			String pw = password;

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(true);
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress("leehm0311@naver.com"));
		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(body);
		Transport.send(mimeMessage);

		mav.addObject("ran", ran);
		mav.addObject("member_id", member_id);
		mav.addObject("member_name", member_name);
		mav.addObject("email1", email1);
		mav.setViewName("forward:/member/searchPwForm.do");
		return mav;
	}
}