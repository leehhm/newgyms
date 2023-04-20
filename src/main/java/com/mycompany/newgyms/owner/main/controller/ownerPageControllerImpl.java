package com.mycompany.newgyms.owner.main.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.owner.main.service.ownerPageService;
import com.mycompany.newgyms.owner.main.vo.OwnerPageVO;
import com.mycompany.newgyms.product.service.ProductService;
import com.mycompany.newgyms.product.vo.ProductVO;

@Controller("ownerPageController")
@RequestMapping(value = "/owner/main")
public class ownerPageControllerImpl implements ownerPageController {
	  @Autowired
	  private MemberVO memberVO;
	  @Autowired
	  private ownerPageService ownerPageService;
	  @Autowired
	  private ProductService productService;
	  
	  /* 사업장 소개/관리 페이지 */
	  @Override
	  @RequestMapping(value = "/ownerPageIntroView.do", method = RequestMethod.GET)
	  public ModelAndView ownerPageIntroView(@RequestParam("member_id") String member_id,
			  HttpServletRequest request, HttpServletResponse response) throws Exception {
		  ModelAndView mav = new ModelAndView(); 
		  
		  List<ProductVO> productList=ownerPageService.productList(member_id);
		  mav.addObject("productList", productList);
		  
		  OwnerPageVO ownerPageVO = ownerPageService.ownerPageIntroView(member_id);
		  mav.addObject("ownerPageVO", ownerPageVO);
		  
		  MemberVO memberVO = ownerPageService.ownerPageIntroInfo(member_id);
		  mav.addObject("memberVO", memberVO);
		
		  mav.setViewName("/owner/main/ownerPageIntroView");
		  
		  return mav; 
	  }
	  
	  /* 사업장 관리 페이지 */
	  @Override
	  @RequestMapping(value = "/ownerPageIntroModifyForm.do", method = RequestMethod.GET)
	  public ModelAndView ownerPageIntroModifyForm(@RequestParam("member_id") String member_id,HttpServletRequest request, HttpServletResponse response) throws Exception {
		  response.setContentType("text/html; charset=UTF-8");
		  request.setCharacterEncoding("utf-8");
		  ModelAndView mav = new ModelAndView(); 
		  
		  OwnerPageVO ownerPageVO = ownerPageService.ownerPageIntroView(member_id);
		  mav.addObject("ownerPageVO", ownerPageVO);
		  
		  mav.setViewName("/owner/main/ownerPageIntroModifyForm");
		  return mav;
	  }
	  
	  /* 사업장 관리 페이지 수정 */
	  @Override
	  @RequestMapping(value = "/ownerPageIntroModify.do", method = RequestMethod.POST)
	  public @ResponseBody String ownerPageIntroModify(@RequestParam Map<String, String> modifyMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		  String result = ownerPageService.ownerPageIntroModify(modifyMap);
		  
		  return result;
	  }
	  
	  
	  /* 사업자 회원정보 수정 탈퇴  */
	  @Override
	  @RequestMapping(value = "/ownerPageModify.do", method = RequestMethod.GET)
	  public ModelAndView ownerPageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  ModelAndView mav = new ModelAndView(); 
		  mav.setViewName("/owner/main/ownerPageModify");
	  
		  return mav; 
	  }
	  
	   @Override
	   @RequestMapping(value = "/ownerDetailInfo.do", method = RequestMethod.POST)
	   public ModelAndView ownerDetailInfo(@RequestParam Map<String, String> ownerpageMap, 
			   HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();
			memberVO = ownerPageService.ownerPageDetail(ownerpageMap);
	
			if (memberVO != null && memberVO.getMember_id() != null) {
				HttpSession session = request.getSession();
				session = request.getSession();
				session.setAttribute("memberInfo", memberVO);
				mav.setViewName("/owner/main/ownerDetailInfo");
	
			} else {
				PrintWriter out = response.getWriter();
				out.println("<script>alert('비밀번호가 일치하지 않습니다.');</script>");
				out.flush();
				mav.setViewName("/owner/main/ownerPageModify");
			}
	
			return mav;
		}
	   
	   @Override
		@RequestMapping(value = "modifyMyInfo.do", method = RequestMethod.POST)
		public ModelAndView modifyMyInfo(@RequestParam Map<String, String> modifyMap, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			request.setCharacterEncoding("utf-8");
			ModelAndView mav = new ModelAndView();

			memberVO = ownerPageService.modifyMyInfo(modifyMap);
			System.out.println(memberVO);

			HttpSession session = request.getSession();
			PrintWriter out = response.getWriter();
			session.setAttribute("memberInfo", memberVO);
			out.println("<script>alert('회원정보 수정이 완료되었습니다. :)');</script>");
			out.flush();

			mav.setViewName("/owner/main/ownerPageModify");

			return mav;
		}
	   
	   @Override
		@RequestMapping(value = "/deleteMemberForm.do", method = RequestMethod.POST)
		public ModelAndView deleteMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/owner/main/deleteMemberForm");
			return mav;
		}
	   
	   @Override
		@RequestMapping(value = "/deleteMember.do", method = RequestMethod.POST)
		public ModelAndView deleteMember(@RequestParam Map<String, String> deleteMap, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			ownerPageService.deleteMember(deleteMap);

			mav.setViewName("redirect:/member/logout.do");
			return mav;
		}
}
