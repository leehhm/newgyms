package com.mycompany.newgyms.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface ProductController {
	public ModelAndView productList(@RequestParam("category") String productSort,@RequestParam("address") String address, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView productSorting(@RequestParam("category") String productSort,@RequestParam("address") String address,@RequestParam("sortBy") String sortBy,  HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView productDetail(@RequestParam("product_id") int product_id,HttpServletRequest request, HttpServletResponse response) throws Exception;	
	
	/*
	
	public ModelAndView productByAddress(@RequestParam("address") String address, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public @ResponseBody String keywordSearch(@RequestParam("keyword") String keyword,HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView searchProduct(@RequestParam("searchWord") String searchWord,HttpServletRequest request, HttpServletResponse response) throws Exception;
	 */
}

