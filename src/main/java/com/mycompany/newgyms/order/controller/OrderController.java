package com.mycompany.newgyms.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.order.vo.OrderVO;

public interface OrderController {
	public ModelAndView nonMemberOrder(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView orderEachProduct(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView orderCartProduct(HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView payToOrderProduct(@RequestParam Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response)  throws Exception;
	public ModelAndView nonMemberOrderInfo(@RequestParam Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView nonMemberOrderDetail(@RequestParam("order_id") int order_id, HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ModelAndView nonMemberOrderCancel(@RequestParam("total_price") int total_price, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
