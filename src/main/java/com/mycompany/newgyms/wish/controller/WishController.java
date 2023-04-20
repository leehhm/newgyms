package com.mycompany.newgyms.wish.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public interface WishController {
	public ModelAndView myWishMain(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public @ResponseBody String addWishList(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ResponseEntity removeEachWishProduct(@RequestParam("wish_id") int wish_id, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public @ResponseBody ResponseEntity removeWishProduct(@RequestParam(value="wish_id[]") String[] wish_id_list, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
