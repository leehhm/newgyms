package com.mycompany.newgyms.admin.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface AdminProductController {
	public ModelAndView adminProductList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String adminProductAccess(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public @ResponseBody String adminRemoveProduct(@RequestParam("product_id") String product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;	
}
