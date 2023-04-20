package com.mycompany.newgyms.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.mypage.service.MyPageService;
import com.mycompany.newgyms.order.service.OrderService;
import com.mycompany.newgyms.order.vo.OrderVO;

@Controller("orderController")
@RequestMapping(value="/order")
public class OrderControllerImpl implements OrderController {
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderVO orderVO;
	@Autowired
	private MemberVO memberVO;
	
	@Autowired
	private MyPageService myPageService;
	
	
	@Override
	@RequestMapping(value="/nonMemberOrder.do", method=RequestMethod.GET)
	public ModelAndView nonMemberOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
        mav.setViewName("/order/nonMemberOrder");
        return mav;
	}
	
	@RequestMapping(value="/orderEachProduct.do" ,method = RequestMethod.POST)
	public ModelAndView orderEachProduct(@ModelAttribute("orderVO") OrderVO orderVO, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		request.setCharacterEncoding("utf-8");
		
		//로그인 정보 가져오기
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		memberVO=(MemberVO)session.getAttribute("memberInfo");
		mav.addObject("orderer", memberVO);
		
		//회원
		if (isLogOn == true && memberVO!= null && memberVO.getMember_id() != null) {
			
			String member_id = memberVO.getMember_id();
			//보유한 적립금(주문/결제 페이지)
			String orderPoint = orderService.orderPoint(member_id);
			mav.addObject("orderPoint", orderPoint);
			
		}
		
		List myOrderList=new ArrayList<OrderVO>();
		myOrderList.add(orderVO);
		
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberVO);
		
		return mav;
	}
	
	@RequestMapping(value="/orderCartProduct.do" ,method = RequestMethod.POST)
	public ModelAndView orderCartProduct(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String viewName=(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		
		String[] cart_id_list = request.getParameterValues("check_one");
		
		if(cart_id_list == null) {
			
		ModelAndView modelAndView = new ModelAndView(viewName);
	    modelAndView.addObject("message", "주문할 상품을 선택해주세요 :)");
	    return modelAndView;
	    
		} else {
			
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("cart_id_list", cart_id_list);

		//로그인 정보 가져오기
		HttpSession session = request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
		
		//회원
		if (isLogOn == true && memberVO!= null && memberVO.getMember_id() != null) {
			String member_id = memberVO.getMember_id(); //로그인한 member_id
			
			//보유한 적립금(주문/결제 페이지)
			String orderPoint = orderService.orderPoint(member_id);
			mav.addObject("orderPoint", orderPoint);
			
		} 
		
		List<OrderVO> myOrderList = orderService.selectOrderProductList(orderMap);
		session.setAttribute("myOrderList", myOrderList);
		session.setAttribute("orderer", memberVO);
		
		return mav;

		}	
	}

	// 주문/결제
   @RequestMapping(value="/payToOrderProduct.do", method = RequestMethod.POST)
   public ModelAndView payToOrderProduct(@RequestParam Map<String, String> orderMap,
                                HttpServletRequest request, HttpServletResponse response)  throws Exception{
      ModelAndView mav = new ModelAndView();
      HttpSession session=request.getSession();
      String message = null;
      
      orderMap.put("order_state", "결제완료");
            
      // 상품정보 받아와서 리스트에 넣음
      List<OrderVO> myOrderList = (List<OrderVO>)session.getAttribute("myOrderList");
      
      System.out.println(myOrderList);
      
      
      Boolean isLogOn = (Boolean)session.getAttribute("isLogOn");
      if (isLogOn == true && orderMap.get("member_id") != null) { // 회원일 경우
         int total_price = Integer.parseInt(orderMap.get("total_price"));
         System.out.println(total_price);
      
         // 적립금을 사용했을 경우
         if (orderMap.get("point_price") != null || Integer.parseInt(orderMap.get("point_price"))>0) {
            int point_price = Integer.parseInt(orderMap.get("point_price"));
            String member_id = orderMap.get("member_id");

            Map<String, Object> pointMap = new HashMap<String, Object>();
            pointMap.put("point_state", "사용");
            pointMap.put("point_name", "적립금 사용");
            pointMap.put("point_price", point_price);
            pointMap.put("member_id", member_id);
            
            myPageService.usePoint(pointMap);
         }
         
         // 적립금 쌓기
         int new_point = Integer.parseInt(orderMap.get("new_point"));
         String point_name = orderMap.get("product_name") + " 구매 적립";
         String member_id = orderMap.get("member_id");
         
         Map<String, Object> pointMap = new HashMap<String, Object>();
         pointMap.put("point_state", "적립");
         pointMap.put("point_name", point_name);
         pointMap.put("point_price", new_point);
         pointMap.put("member_id", member_id);
         
         myPageService.addPoint(pointMap);
         
         // 중복되는 정보들 입력
         for (int i=0; i<myOrderList.size(); i++) {
            OrderVO orderVO = (OrderVO)myOrderList.get(i);
            orderVO.setMember_id(orderMap.get("member_id"));
            orderVO.setOrderer_name(orderMap.get("orderer_name"));
            orderVO.setOrderer_hp1(orderMap.get("orderer_hp1"));
            orderVO.setOrderer_hp2(orderMap.get("orderer_hp2"));
            orderVO.setOrderer_hp3(orderMap.get("orderer_hp3"));
            orderVO.setReceiver_name(orderMap.get("receiver_name"));
            orderVO.setReceiver_hp1(orderMap.get("receiver_hp1"));
            orderVO.setReceiver_hp2(orderMap.get("receiver_hp2"));
            orderVO.setReceiver_hp3(orderMap.get("receiver_hp3"));
            orderVO.setPay_method(orderMap.get("pay_method"));
            orderVO.setCard_com_name(orderMap.get("card_com_name"));
            orderVO.setCard_pay_month(orderMap.get("card_pay_month"));
            orderVO.setOrder_state(orderMap.get("order_state"));
            orderVO.setNew_point(Integer.parseInt(orderMap.get("new_point")));
            orderVO.setPoint_price(Integer.parseInt(orderMap.get("point_price")));
            orderVO.setTotal_price(Integer.parseInt(orderMap.get("total_price")));
            myOrderList.set(i, orderVO);
         }
         int order_id = orderService.addNewOrder(myOrderList);
         session.setAttribute("order_id", order_id);
                  
         mav.setViewName("redirect:/mypage/myOrderDetail.do?order_id="+order_id);
         
      } else { // 비회원일 경우
         String member_id = session.getId(); //session Id를 member_id에 저장
         // 중복되는 정보들 입력
         for (int i = 0; i < myOrderList.size(); i++) {
            OrderVO orderVO = (OrderVO) myOrderList.get(i);
            int product_sales_price = orderVO.getProduct_sales_price();
            int product_option_price = Integer.parseInt(orderVO.getProduct_option_price());
            int total_price = product_sales_price + product_option_price;
            
            orderVO.setNonmember_pw(orderMap.get("nonmember_pw"));
            orderVO.setMember_id(member_id);
            orderVO.setOrderer_name(orderMap.get("orderer_name"));
            orderVO.setOrderer_hp1(orderMap.get("orderer_hp1"));
            orderVO.setOrderer_hp2(orderMap.get("orderer_hp2"));
            orderVO.setOrderer_hp3(orderMap.get("orderer_hp3"));
            orderVO.setReceiver_name(orderMap.get("receiver_name"));
            orderVO.setReceiver_hp1(orderMap.get("receiver_hp1"));
            orderVO.setReceiver_hp2(orderMap.get("receiver_hp2"));
            orderVO.setReceiver_hp3(orderMap.get("receiver_hp3"));
            orderVO.setPay_method(orderMap.get("pay_method"));
            orderVO.setCard_com_name(orderMap.get("card_com_name"));
            orderVO.setCard_pay_month(orderMap.get("card_pay_month"));
            orderVO.setOrder_state(orderMap.get("order_state"));
            orderVO.setTotal_price(total_price);
            myOrderList.set(i, orderVO);
         }
         int order_id = orderService.addNewNonMemOrder(myOrderList);
         session.setAttribute("order_id", order_id);
                  
         mav.setViewName("redirect:/order/nonMemberOrderDetail.do?order_id="+order_id);
      }
      
      return mav;
   }
	
	// 비회원 주문/결제 조회
	@RequestMapping(value = "/nonMemberOrderInfo.do", method = RequestMethod.POST)
	public ModelAndView nonMemberOrderInfo(@RequestParam Map<String, String> orderMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		// 주문번호 및 비회원 비밀번호 확인
		try {
			String result = orderService.orderInfo(orderMap);
			if (result == "true" || result.equals("true")) {
				int order_id = Integer.parseInt(orderMap.get("order_id"));
				List<OrderVO> myOrderDetail = orderService.nonMemberOrderDetail(order_id);
				mav.addObject("myOrderDetail", myOrderDetail);
				mav.setViewName("/order/nonMemberOrderDetail");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mav;
	}
	
	
	// 비회원 주문/결제 상세조회
	@Override
	@RequestMapping(value = "/nonMemberOrderDetail.do", method = RequestMethod.GET)
	public ModelAndView nonMemberOrderDetail(@RequestParam("order_id") int order_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		System.out.println(order_id);
		HttpSession session = request.getSession();

		List<OrderVO> myOrderDetail = orderService.nonMemberOrderDetail(order_id);

		mav.addObject("myOrderDetail", myOrderDetail);

		return mav;
	}
	
	// 비회원 주문 취소 페이지
	@Override
	@RequestMapping(value = "/nonMemberOrderCancel.do", method = RequestMethod.POST)
	public ModelAndView nonMemberOrderCancel(@RequestParam("total_price") int total_price, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();

		String[] list = request.getParameterValues("cancel");

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("list", list);

		System.out.println(orderMap);

		List<OrderVO> myOrderDetail = orderService.nonMemberOrderCancel(orderMap);
		System.out.println(myOrderDetail.size());

		request.setAttribute("total_price", total_price);
		mav.addObject("myOrderDetail", myOrderDetail);
		mav.setViewName("/order/nonMemberOrderCancel");
		return mav;
	}
}