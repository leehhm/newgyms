package com.mycompany.newgyms.owner.order.controller;

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

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;
import com.mycompany.newgyms.owner.order.service.OwnerOrderService;

@Controller("ownerOrderController")
@RequestMapping(value = "/owner/order")
public class OwnerOrderControllerImpl implements OwnerOrderController {
	@Autowired
	private OwnerOrderService ownerOrderService;
	@Autowired
	private OrderVO orderVO;
	@Autowired
	private RefundVO refundVO;

	// ����� �ֹ�/���� ���
	@Override
	@RequestMapping(value = "/ownerOrderList.do", method = RequestMethod.GET)
	public ModelAndView ownerOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String center_name = request.getParameter("center_name");
		String chapter = request.getParameter("chapter");
		
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("center_name", center_name);
		String maxnum = ownerOrderService.maxNumSelect(condMap);
		condMap.put("chapter", chapter);
		condMap.put("maxnum", maxnum);
		
		List<OrderVO> ownerOrderList = ownerOrderService.listOwnerOrders(condMap);
		System.out.println(ownerOrderList);
		
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("ownerOrderList", ownerOrderList);
		mav.setViewName("/owner/order/ownerOrderList");

		return mav;
	}

	// ����� �ֹ�/���� �󼼺���
	@Override
	@RequestMapping(value = "/ownerOrderDetail.do", method = RequestMethod.GET)
	public ModelAndView ownerOrderDetail(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberInfo");
		OrderVO ownerOrderDetail = ownerOrderService.ownerOrderDetail(order_seq_num);
		RefundVO ownerRefundDetail = ownerOrderService.ownerOrderCancel(order_seq_num);

		mav.addObject("member", member);
		mav.addObject("ownerOrderDetail", ownerOrderDetail);
		mav.addObject("ownerRefundDetail", ownerRefundDetail);

		return mav;
	}

	// ����� ������� ������
	@Override
	@RequestMapping(value = "/ownerOrderCancel.do", method = RequestMethod.POST)
	public ModelAndView ownerOrderCancel(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		OrderVO ownerOrderDetail = ownerOrderService.ownerOrderDetail(order_seq_num);
		RefundVO ownerRefundDetail = ownerOrderService.ownerOrderCancel(order_seq_num);

		mav.addObject("ownerOrderDetail", ownerOrderDetail);
		mav.addObject("ownerRefundDetail", ownerRefundDetail);
		mav.setViewName("/owner/order/ownerOrderCancel");
		return mav;
	}

	// ����� ȯ�ҽ���
	@Override
	@RequestMapping(value = "/ownerOrderRefund.do", method = RequestMethod.POST)
	public ModelAndView ownerOrderRefund(@RequestParam("order_seq_num") int order_seq_num, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		ownerOrderService.ownerOrderRefund(order_seq_num);
		mav.setViewName("redirect:/owner/order/ownerOrderDetail.do?order_seq_num=" + order_seq_num);

		return mav;
	}
}
