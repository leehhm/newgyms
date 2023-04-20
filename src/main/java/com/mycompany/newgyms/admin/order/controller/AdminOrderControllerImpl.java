package com.mycompany.newgyms.admin.order.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.admin.order.service.AdminOrderService;
import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

@Controller("adminOrderController")
@RequestMapping(value = "/admin/order")
public class AdminOrderControllerImpl implements AdminOrderController {
	@Autowired
	private AdminOrderService adminOrderService;
	@Autowired
	private OrderVO orderVO;
	@Autowired
	private RefundVO refundVO;

	// 결제내역 조회
	@Override
	@RequestMapping(value = "/adminOrderList.do", method = RequestMethod.GET)
	public ModelAndView adminOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String chapter = request.getParameter("chapter");
		String order_state = request.getParameter("order_state");

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
		condMap.put("order_state", order_state);
		condMap.put("firstDate", firstDate);
		condMap.put("secondDate", secondDate);
		condMap.put("text_box", text_box);
		String maxnum = adminOrderService.maxNumSelect(condMap);
		condMap.put("maxnum", maxnum);
		
		List<OrderVO> adminOrderList = adminOrderService.listAllOrders(condMap);
		List<OrderVO> orderMemberList = adminOrderService.orderMemberList(condMap);
		List<OrderVO> orderMember = adminOrderService.orderMember(condMap);
		
		for (int i=0; i<orderMemberList.size(); i++) {			
			System.out.print(orderMemberList.get(i));
		}
		
		int count = orderMember.size();
		mav.addObject("count", count);
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("orderMemberList", orderMemberList);
		mav.addObject("order_state", order_state);
		mav.addObject("firstDate", firstDate);
		mav.addObject("secondDate", secondDate);
		mav.addObject("text_box", text_box);
		mav.addObject("adminOrderList", adminOrderList);
		mav.addObject("orderMember", orderMember);
		mav.setViewName("/admin/order/adminOrderList");

		return mav;
	}

	// 결제내역 상세 조회
	@Override
	@RequestMapping(value = "/adminOrderDetail.do", method = RequestMethod.GET)
	public ModelAndView adminOrderDetail(@RequestParam("order_id") int order_id, @RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println(order_id);
		
		// 취소 선택한 상품 목록 받아오기
		/*
		String[] list = request.getParameterValues("order_seq_num");
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("list", list);
		
		for (int i=0; i<list.length; i++) { System.out.println(list[i]); }
		System.out.println(orderMap);
		 */

		System.out.println("order_seq_num : " + order_seq_num);
		HttpSession session = request.getSession();
		List<OrderVO> adminOrderDetail = adminOrderService.adminOrderDetail(order_id);
		RefundVO adminRefundDetail = adminOrderService.adminRefundDetail(order_seq_num);
		
		System.out.println(adminOrderDetail.size());
		System.out.println(adminRefundDetail);

		mav.addObject("adminOrderDetail", adminOrderDetail);
		mav.addObject("adminRefundDetail", adminRefundDetail);

		return mav;
	}

	// 결제취소 페이지로 이동
	@Override
	@RequestMapping(value = "/adminOrderCancel.do", method = RequestMethod.POST)
	public ModelAndView adminOrderCancel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		// 취소 선택한 상품 목록 받아오기
		String[] list = request.getParameterValues("order_seq_num");
		int order_seq_num = Integer.parseInt(request.getParameter("order_seq_num"));
		
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("list", list);

		System.out.println(orderMap);

		List<OrderVO> adminOrderDetail = adminOrderService.adminOrderCancel(orderMap);
		RefundVO adminRefundDetail = adminOrderService.adminRefundDetail(order_seq_num);

		System.out.println(adminOrderDetail.size());
		System.out.println(adminRefundDetail);

		mav.addObject("adminOrderDetail", adminOrderDetail);
		mav.addObject("adminRefundDetail", adminRefundDetail);
		mav.setViewName("/admin/order/adminOrderCancel");
		return mav;
	}

	// 결제취소 및 환불
	@Override
	@RequestMapping(value = "/adminOrderRefund.do", method = RequestMethod.POST)
	public ModelAndView adminOrderRefund(@RequestParam Map<String, Object> refundMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();

		// 결체 취소를 결정한 상품의 개별 주문번호를 받아옴
		String[] list = request.getParameterValues("order_seq_num");
		String order_id = request.getParameter("order_id");

		// 결제취소 신청한 개수만큼 반복
		for (int i = 0; i < list.length; i++) {
			refundMap.put("order_seq_num", list[i]);
			adminOrderService.adminOrderRefund(refundMap);
		}

		mav.setViewName("redirect:/admin/order/adminOrderList.do?chapter=1&order_state=&firstDate=&secondDate=&text_box=");

		return mav;
	}
}
