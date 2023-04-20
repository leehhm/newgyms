package com.mycompany.newgyms.admin.product.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.mycompany.newgyms.admin.product.service.AdminProductService;
import com.mycompany.newgyms.product.vo.ProductVO;

@Controller("adminProductController")
@RequestMapping(value = "/admin/product")
public class AdminProductControllerImpl implements AdminProductController {
	@Autowired
	private AdminProductService adminProductService;
		
	// 전체 상품 목록
	@RequestMapping(value = "/adminProductList.do", method = RequestMethod.GET)
	public ModelAndView adminProductList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView mav = new ModelAndView();

		String chapter = request.getParameter("chapter");

		DateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());

		String secondDate = request.getParameter("secondDate");
		if (secondDate == "") {
			secondDate = nowdate.format(today.getTime());
		}
		String firstDate = request.getParameter("firstDate");
		if (firstDate == "") {
			today.add(Calendar.MONTH, -5);
			firstDate = nowdate.format(today.getTime());
		}
		String text_box = request.getParameter("text_box");
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("chapter", chapter);
		condMap.put("firstDate", firstDate);
		condMap.put("secondDate", secondDate);
		condMap.put("text_box", text_box);
		String maxnum = adminProductService.maxNumSelect(condMap);
		condMap.put("maxnum", maxnum);

		List<ProductVO> adminProductList = adminProductService.adminProductList(condMap);
		
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("firstDate", firstDate);
		mav.addObject("secondDate", secondDate);
		mav.addObject("text_box", text_box);
		mav.addObject("adminProductList", adminProductList);
		
		mav.setViewName("/admin/product/adminProductList");
		return mav;
	}
	
	// 상품 승인
	@RequestMapping(value = "/adminProductAccess.do", method = RequestMethod.POST)
	public @ResponseBody String adminProductAccess(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(product_id);
		String result = adminProductService.adminProductAccess(product_id);

		if(result=="true" || result.equals("true")){
			return "success";
		}else{
			return "false";
		}
		
	}
	
	// 상품 삭제
	@RequestMapping(value = "/adminRemoveProduct.do", method = RequestMethod.POST)
	public @ResponseBody String adminRemoveProduct(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		System.out.println(product_id);
		String result = adminProductService.adminRemoveProduct(product_id);
		
		if(result=="true" || result.equals("true")){
			return "success";
		}else{
			return "false";
		}
	}
}
