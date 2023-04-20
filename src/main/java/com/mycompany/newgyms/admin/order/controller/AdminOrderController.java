package com.mycompany.newgyms.admin.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public interface AdminOrderController {
	// ��ü �������� ��ȸ
	public ModelAndView adminOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// �������� �� ��ȸ
	public ModelAndView adminOrderDetail(@RequestParam("order_id") int order_id, @RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// �������
	public ModelAndView adminOrderCancel(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// ȯ��
	public ModelAndView adminOrderRefund(@RequestParam Map<String, Object> refundMap, HttpServletRequest request, HttpServletResponse response) throws Exception;

}
