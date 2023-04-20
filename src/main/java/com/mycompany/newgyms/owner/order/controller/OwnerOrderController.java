package com.mycompany.newgyms.owner.order.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface OwnerOrderController {
	// 林巩/搬力 包府
	public ModelAndView ownerOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ownerOrderDetail(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ownerOrderCancel(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView ownerOrderRefund(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request, HttpServletResponse response) throws Exception;

}