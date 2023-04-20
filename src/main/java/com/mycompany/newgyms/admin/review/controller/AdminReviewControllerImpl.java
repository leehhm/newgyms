package com.mycompany.newgyms.admin.review.controller;

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

import com.mycompany.newgyms.admin.review.service.AdminReviewService;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Controller("adminReviewController")
@RequestMapping(value = "/admin/review")
public class AdminReviewControllerImpl implements AdminReviewController {
	@Autowired
	private AdminReviewService adminReviewService;
	
	// 관리자 리뷰조회
		@Override
		@RequestMapping(value = "/adminReviewList.do", method = RequestMethod.GET)
			public ModelAndView adminReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
				ModelAndView mav = new ModelAndView();
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
				condMap.put("chapter", chapter);
				condMap.put("firstDate", firstDate);
				condMap.put("secondDate", secondDate);
				condMap.put("text_box", text_box);
				String maxnum = adminReviewService.adminReviewMaxNum(condMap);
				condMap.put("maxnum", maxnum);
				List<ReviewVO> adminReviewList = adminReviewService.adminReviewList(condMap);
				List<ReviewVO> reviewImageList = adminReviewService.reviewImageList(condMap);
				mav.addObject("maxnum", maxnum);
				mav.addObject("chapter", chapter);
				mav.addObject("maxnum", maxnum);
				mav.addObject("firstDate", firstDate);
				mav.addObject("secondDate", secondDate);
				mav.addObject("text_box", text_box);
				mav.addObject("adminReviewList", adminReviewList);
				mav.addObject("reviewImageList", reviewImageList);
				mav.setViewName("/admin/review/adminReviewList");

				return mav;
			}
		
		// 관리자 이용후기 삭제
				@Override
				@RequestMapping(value = "/adminReviewDelete.do", method = RequestMethod.GET)
				@ResponseBody
				public ResponseEntity adminReviewDelete(@RequestParam int review_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
					response.setContentType("text/html; charset=UTF-8");
					String message;
					ResponseEntity resEnt = null;
					HttpHeaders responseHeaders = new HttpHeaders();
					responseHeaders.add("Content-Type", "text/html; charset=utf-8");
					System.out.println(review_no);
					try {
						adminReviewService.adminReviewDelete(review_no);

						message = "<script>";
						message += "alert('이용후기가 삭제되었습니다.');";
						message += "location.href='" + request.getContextPath()
								+ "/admin/review/adminReviewList.do?chapter=1&firstDate=&secondDate=&text_box=';";
						message += "</script>";
						resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
					} catch (Exception e) {
						message = "<script>";
						message += "alert('오류.');";
						message += "location.href = '" + request.getContextPath()
								+ "/admin/review/adminReviewList.do?chapter=1&firstDate=&secondDate=&text_box=';";
						message += "</script>";
						resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
						e.printStackTrace();
					}
					return resEnt;

				}
}
