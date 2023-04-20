package com.mycompany.newgyms.owner.review.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.owner.review.service.OwnerReviewService;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Controller("ownerReviewController")
@RequestMapping(value = "/owner/review")
public class OwnerReviewControllerImpl implements OwnerReviewController {
	@Autowired
	private OwnerReviewService ownerReviewService;
	
	
	// �궗�뾽�옄 由щ럭議고쉶
	@Override
	@RequestMapping(value = "/orderReviewList.do", method = RequestMethod.GET)
		public ModelAndView orderReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView();
			String center_name = request.getParameter("center_name");
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
			condMap.put("center_name", center_name);
			condMap.put("chapter", chapter);
			condMap.put("firstDate", firstDate);
			condMap.put("secondDate", secondDate);
			condMap.put("text_box", text_box);
			String maxnum = ownerReviewService.ownerReviewMaxNum(condMap);
			condMap.put("maxnum", maxnum);
			List<ReviewVO> ownerReviewList = ownerReviewService.ownerReviewList(condMap);
			List<ReviewVO> reviewImageList = ownerReviewService.reviewImageList(condMap);
			mav.addObject("maxnum", maxnum);
			mav.addObject("center_name", center_name);
			mav.addObject("chapter", chapter);
			mav.addObject("maxnum", maxnum);
			mav.addObject("firstDate", firstDate);
			mav.addObject("secondDate", secondDate);
			mav.addObject("text_box", text_box);
			mav.addObject("ownerReviewList", ownerReviewList);
			mav.addObject("reviewImageList", reviewImageList);
			mav.setViewName("/owner/review/orderReviewList");

			return mav;
		}
	
	// �궗�뾽�옄 �씠�슜�썑湲� �궘�젣
		@Override
		@RequestMapping(value = "/ownerReviewDetail.do", method = RequestMethod.GET)
		@ResponseBody
		public ResponseEntity ownerReviewDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			String message;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			String review_no = request.getParameter("review_no");
			String center_name = request.getParameter("center_name");
			try {
				ownerReviewService.ownerReviewDetail(review_no);

				message = "<script>";
				message += "alert('�씠�슜�썑湲곌� �궘�젣�릺�뿀�뒿�땲�떎.');";
				message += "location.href='" + request.getContextPath()
						+ "/owner/review/orderReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&center_name=" + center_name
						+ "';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			} catch (Exception e) {
				message = "<script>";
				message += "alert('�삤瑜�.');";
				message += "location.href = '" + request.getContextPath()
						+ "/owner/review/orderReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&center_name=" + center_name
						+ "';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}
			return resEnt;

		}
}
