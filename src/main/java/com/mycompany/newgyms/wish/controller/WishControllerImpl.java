package com.mycompany.newgyms.wish.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.wish.service.WishService;
import com.mycompany.newgyms.wish.vo.WishVO;


@Controller("wishController")
@RequestMapping(value = "/wish")
public class WishControllerImpl implements WishController{
	@Autowired
	private WishService wishService;
	
	@Autowired
	private WishVO wishVO;
	
	/* �� ��� */
	
	@RequestMapping(value="/myWishList.do" ,method = RequestMethod.GET)
	public ModelAndView myWishMain(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT"); 
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0"); 
		response.setHeader("Pragma", "no-cache");
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		session.setAttribute("memberInfo", memberVO);
		
		//�α����� ���
		if (isLogOn != null && isLogOn == true && memberVO!= null && memberVO.getMember_id() != null) {
			
			String member_id=memberVO.getMember_id(); //�α����� member_id
			wishVO.setMember_id(member_id);
			/* mav.addObject("member_id", member_id); */
			
		//�α��� ���� ���� ���
		} else if (isLogOn == null || isLogOn == false || memberVO == null) {
			
			String member_id = "nonMember"; //session Id�� member_id�� ����
			wishVO.setMember_id(member_id);

		}
		
		Map<String ,List> wishMap=wishService.myWishList(wishVO);
		session.setAttribute("wishMap", wishMap);//��ٱ��� ��� ȭ�鿡�� ��ǰ �ֹ� �� ����ϱ� ���ؼ� ��ٱ��� ����� ���ǿ� �����Ѵ�.
		mav.addObject("wishMap", wishMap);
		return mav;
	}
	
	@RequestMapping(value = "/addWishList.do", method={RequestMethod.POST,RequestMethod.GET},produces = "application/text; charset=utf8")
	public @ResponseBody String addWishList(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		//�α����� ���
		if (isLogOn == true && memberVO!= null && memberVO.getMember_id() != null) {
					
			String member_id=memberVO.getMember_id(); //�α����� member_id		
			wishVO.setMember_id(member_id);
			
		//�α��� ���� ���� ���
		} else if (isLogOn == null || isLogOn == false || memberVO == null) {
			return "add_failed";
		}
		
		wishVO.setProduct_id(product_id);
		boolean isAreadyExisted=wishService.findWishProduct(wishVO);
		System.out.println("isAreadyExisted:"+isAreadyExisted);
		
		
		if (isAreadyExisted==true){
			int wish_id = wishService.selectWishId(wishVO);
			wishService.removeEachWishProduct(wish_id);
			return "already_existed";
		} else {
			wishService.addWishList(wishVO);
			return "add_success";
		}

	}
	
	@RequestMapping(value="/removeEachWishProduct.do" ,method = RequestMethod.GET)
	public ResponseEntity removeEachWishProduct(@RequestParam("wish_id") int wish_id, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			wishService.removeEachWishProduct(wish_id);
			message = "<script>";
			message += " alert('������ �Ϸ�Ǿ����ϴ�. :)');";
			message += " location.href='" + request.getContextPath() + "/wish/myWishList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ��� :( ');";
			message += " location.href='" + request.getContextPath() + "/wish/myWishList.do';";
			message += " </script>";
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	/* ��ٱ��� ���� ���� */
	@RequestMapping(value="/removeWishProduct.do" ,method = RequestMethod.POST)
	public @ResponseBody ResponseEntity removeWishProduct(@RequestParam(value="wish_id[]") String[] wish_id_list, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		Map<String, Object> wishMap = new HashMap<String, Object>();
		wishMap.put("wish_id_list", wish_id_list);
		System.out.println(wishMap);
		try {
			wishService.removeWishProduct(wishMap);
			message = "<script>";
			message += " alert('������ �Ϸ�Ǿ����ϴ�. :)');";
			message += " location.href='" + request.getContextPath() + "/wish/myWishList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('�۾� �� ������ �߻��߽��ϴ�. �ٽ� �õ��� �ּ��� :( ');";
			message += " location.href='" + request.getContextPath() + "/wish/myWishList.do';";
			message += " </script>";
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
}
