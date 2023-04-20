package com.mycompany.newgyms.mypage.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.mypage.service.MyPageService;
import com.mycompany.newgyms.mypage.vo.PointVO;
import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;
import com.mycompany.newgyms.owner.review.service.OwnerReviewService;
import com.mycompany.newgyms.qna.vo.QnaVO;
import com.mycompany.newgyms.review.vo.ReviewImageVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Controller("myPageController")
@RequestMapping(value = "/mypage")
public class MyPageControllerImpl implements MyPageController {
	private static final String REVIEW_IMAGE_REPO = "C:\\newgyms\\review";
	@Autowired
	private MyPageService myPageService;
	@Autowired
	private OwnerReviewService ownerReviewService;
	@Autowired
	private MemberVO memberVO;
	@Autowired
	private OrderVO orderVO;
	@Autowired
	private RefundVO refundVO;
	@Autowired
	private ArticleVO articleVO;
	@Autowired
	private QnaVO qnaVO;

	// 결제내역 조회	
	@Override
	   @RequestMapping(value = "/myOrderList.do", method = RequestMethod.GET)
	   public ModelAndView myOrderList(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ModelAndView mav = new ModelAndView();
	      String member_id = request.getParameter("member_id");
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
	      condMap.put("member_id", member_id);
	      condMap.put("chapter", chapter);
	      condMap.put("order_state", order_state);
	      condMap.put("firstDate", firstDate);
	      condMap.put("secondDate", secondDate);
	      condMap.put("text_box", text_box);
	      String maxnum = myPageService.maxNumSelect(condMap); //17
	      System.out.println("maxnum : "+maxnum);
	      List<OrderVO> orderMemberList = myPageService.orderMemberList(condMap); // 13
	      condMap.put("maxnum", maxnum);
	      condMap.put("orderMemberList", orderMemberList.size());
	      System.out.println("1111 : "+orderMemberList.size());
	      List<OrderVO> myOrderList = myPageService.listMyOrders(condMap); // 6
	      List<OrderVO> orderMember = myPageService.orderMember(condMap); // 8
	      int count = orderMember.size();
	      mav.addObject("count", count);
	      mav.addObject("member_id", member_id);
	      mav.addObject("chapter", chapter);
	      mav.addObject("maxnum", maxnum);
	      mav.addObject("orderMaxNum",orderMemberList.size());
	      mav.addObject("orderMemberList", orderMemberList);
	      mav.addObject("order_state", order_state);
	      mav.addObject("firstDate", firstDate);
	      mav.addObject("secondDate", secondDate);
	      mav.addObject("text_box", text_box);
	      mav.addObject("myOrderList", myOrderList);
	      mav.addObject("orderMember", orderMember);
	      mav.setViewName("/mypage/myOrderList");

	      return mav;
	   }
	
	
	// 여러개 이미지 업로드하기
		private List<ReviewImageVO> upload(MultipartHttpServletRequest multipartRequest) throws Exception {
			List<ReviewImageVO> review_image_list = new ArrayList<ReviewImageVO>();
			String fileName = null;
			List<MultipartFile> imageList = multipartRequest.getFiles("imagelist");

			for (MultipartFile image : imageList) {
				ReviewImageVO reviewImageVO = new ReviewImageVO();
				fileName = image.getOriginalFilename();
				if (fileName != null && fileName.length() != 0) {
					System.out.println("1fileName : "+fileName);
					reviewImageVO.setFileName(fileName);
					review_image_list.add(reviewImageVO);
					System.out.println(review_image_list.size());
					File file = new File(REVIEW_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
					if (image.getSize() != 0) { // File Null Check
						if (!file.exists()) {
							if (file.getParentFile().mkdirs()) {
								file.createNewFile();
							}
						}
						image.transferTo(new File(REVIEW_IMAGE_REPO + "\\" + "temp" + "\\" + fileName));
					}
				}
			}
			return review_image_list;
		}
	
	// 이용후기 글 작성하기
		@Override
		@RequestMapping(value = "/reviewUpLoad.do", method = RequestMethod.POST)
		public ResponseEntity reviewUpLoad(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
				throws Exception {
			multipartRequest.setCharacterEncoding("utf-8");
			Map<String, Object> reviewMap = new HashMap<String, Object>();
			Enumeration enu = multipartRequest.getParameterNames();
			String member_id = multipartRequest.getParameter("member_id");
			while (enu.hasMoreElements()) {
				String name = (String) enu.nextElement();
				String value = multipartRequest.getParameter(name);
				reviewMap.put(name, value);
			}

			String fileName = null;
			List<ReviewImageVO> review_image_list = upload(multipartRequest);
			for (ReviewImageVO reviewImageVO : review_image_list) {
				fileName = reviewImageVO.getFileName();
				System.out.println("2fileName : "+fileName);
			}
			
			reviewMap.put("review_image_list", review_image_list);
			
			String message;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			try {
				int review_no = myPageService.addNewReview(reviewMap);
				for (ReviewImageVO image : review_image_list) {
						fileName = image.getFileName();
						if (fileName != null && fileName.length() !=0) {
							System.out.println("fileName : "+fileName);
						File srcFile = new File(REVIEW_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
						File destDir = new File(REVIEW_IMAGE_REPO + "\\" + review_no);
						FileUtils.moveFileToDirectory(srcFile, destDir, true);
					}
				}

				message = "<script>";
				message += "alert('새 글을 추가했습니다.');";
				message += "location.href='" + multipartRequest.getContextPath()
						+ "/mypage/myOrderList.do?chapter=1&order_state=&firstDate=&secondDate=&text_box=&member_id="
						+ member_id + "';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);

			} catch (Exception e) {
				File srcFile = new File(REVIEW_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
				srcFile.delete();

				message = "<script>";
				message += "alert('오류가 발생했습니다. 다시 시도해주세요.');";
				message += "location.href='" + multipartRequest.getContextPath()
						+ "/mypage/myOrderList.do?chapter=1&order_state=&firstDate=&secondDate=&text_box=&member_id="
						+ member_id + "';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}
			return resEnt;
		}

	// 결제내역 상세조회
	@Override
	@RequestMapping(value = "/myOrderDetail.do", method = RequestMethod.GET)
	public ModelAndView myOrderDetail(@RequestParam("order_id") int order_id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberInfo");

		List<OrderVO> myOrderDetail = myPageService.myOrderDetail(order_id);

		mav.addObject("member", member);
		mav.addObject("myOrderDetail", myOrderDetail);

		return mav;
	}

	// 결제취소 페이지로 이동
	@Override
	@RequestMapping(value = "/myOrderCancel.do", method = RequestMethod.POST)
	public ModelAndView myOrderCancel(@RequestParam("total_price") int total_price, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("memberInfo");

		// 취소 선택한 상품 목록 받아오기
		String[] list = request.getParameterValues("cancel");

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("list", list);

		System.out.println(orderMap);

		List<OrderVO> myOrderDetail = myPageService.myOrderCancel(orderMap);

		request.setAttribute("total_price", total_price);
		mav.addObject("member", member);
		mav.addObject("myOrderDetail", myOrderDetail);
		mav.setViewName("/mypage/myOrderCancel");
		return mav;
	}

	// 결제취소
	@Override
	@RequestMapping(value = "/myOrderRefund.do", method = RequestMethod.POST)
	public ModelAndView myOrderRefund(@RequestParam Map<String, Object> refundMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();

		// 취소 선택한 상품 목록 받아오기
		String[] list = request.getParameterValues("cancel");
		String order_id = request.getParameter("order_id");

		// 받아온 상품 갯수만큼 반복
		for (int i = 0; i < list.length; i++) {
			refundMap.put("order_state", "결제취소");
			refundMap.put("order_seq_num", list[i]);
			myPageService.myOrderRefund(refundMap);
		}

		mav.setViewName("redirect:/mypage/myOrderDetail.do?order_id=" + order_id);

		return mav;
	}

	// 회원정보 수정 페이지 이동 (비밀번호 확인)
	@Override
	@RequestMapping(value = "/myPageModify.do", method = RequestMethod.GET)
	public ModelAndView myPageInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mypage/myPageModify");

		return mav;
	}

	// 회원정보 수정 페이지 이동
	@Override
	@RequestMapping(value = "/myDetailInfo.do", method = RequestMethod.POST)
	public ModelAndView myDetailInfo(@RequestParam Map<String, String> mypageMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();
		memberVO = myPageService.myPageDetail(mypageMap);

		if (memberVO != null && memberVO.getMember_id() != null) {
			HttpSession session = request.getSession();
			session = request.getSession();
			session.setAttribute("memberInfo", memberVO);
			mav.setViewName("/mypage/myDetailInfo");

		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 일치하지 않습니다.');</script>");
			out.flush();
			mav.setViewName("/mypage/myPageModify");
		}

		return mav;
	}

	// 회원정보 수정하는 페이지
	@Override
	@RequestMapping(value = "modifyMyInfo.do", method = RequestMethod.POST)
	public ModelAndView modifyMyInfo(@RequestParam Map<String, String> modifyMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView();

		memberVO = myPageService.modifyMyInfo(modifyMap);

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		session.setAttribute("memberInfo", memberVO);
		out.println("<script>alert('회원정보 수정이 완료되었습니다. :)');</script>");
		out.flush();

		mav.setViewName("/mypage/myPageModify");

		return mav;
	}

	// 회원 탈퇴 페이지로 이동
	@Override
	@RequestMapping(value = "/deleteMemberForm.do", method = RequestMethod.POST)
	public ModelAndView deleteMemberForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/mypage/deleteMemberForm");
		return mav;
	}

	// 회원 탈퇴
	@Override
	@RequestMapping(value = "/deleteMember.do", method = RequestMethod.POST)
	public ModelAndView deleteMember(@RequestParam Map<String, String> deleteMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		myPageService.deleteMember(deleteMap);

		mav.setViewName("redirect:/member/logout.do");
		return mav;
	}

	// 적립금 조회
	@Override
	@RequestMapping(value = "/myStackList.do", method = RequestMethod.GET)
	public ModelAndView myStackList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String member_id = request.getParameter("member_id");
		String chapter = request.getParameter("chapter");
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("member_id", member_id);
		condMap.put("chapter", chapter);
		String maxnum = myPageService.maxStack(condMap);
		condMap.put("maxnum", maxnum);
		List<PointVO> myStackList = myPageService.myStackList(condMap);
		String nowPoint = myPageService.nowPoint(member_id);

		mav.addObject("member_id", member_id);
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("nowPoint", nowPoint);
		mav.addObject("myStackList", myStackList);
		mav.setViewName("/mypage/myStackList");
		return mav;
	}

	// 게시글 관리
	@Override
	@RequestMapping(value = "/myArticleList.do", method = RequestMethod.GET)
	public ModelAndView myArticleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		String member_id = request.getParameter("member_id");
		List myArticleList = myPageService.myArticleList(member_id);
		mav.addObject("myArticleList", myArticleList);

		return mav;
	}

	// 이용후기 관리
	@Override
	@RequestMapping(value = "/myReviewList.do", method = RequestMethod.GET)
	public ModelAndView myReviewList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		String member_id = request.getParameter("member_id");
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
		condMap.put("member_id", member_id);
		condMap.put("chapter", chapter);
		condMap.put("firstDate", firstDate);
		condMap.put("secondDate", secondDate);
		condMap.put("text_box", text_box);
		String maxnum = myPageService.reviewMaxNum(condMap);
		condMap.put("maxnum", maxnum);
		List<ReviewVO> myReviewList = myPageService.listMyReviews(condMap);
		List<ReviewVO> reviewImageList = ownerReviewService.reviewImageList(condMap);
		mav.addObject("member_id", member_id);
		mav.addObject("chapter", chapter);
		mav.addObject("maxnum", maxnum);
		mav.addObject("firstDate", firstDate);
		mav.addObject("secondDate", secondDate);
		mav.addObject("text_box", text_box);
		mav.addObject("myReviewList", myReviewList);
		mav.addObject("reviewImageList", reviewImageList);
		mav.setViewName("/mypage/myReviewList");

		return mav;
	}
	
	@RequestMapping(value = "/selectReviewContentsImages.do", method = RequestMethod.GET)
	public @ResponseBody List<ReviewImageVO> selectReviewContentsImages(@RequestParam("review_no") int review_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<ReviewImageVO> reviewContentsImagesList = myPageService.selectReviewContentsImages(review_no);
		return reviewContentsImagesList;
	}

	// 이용후기 삭제
	@Override
	@RequestMapping(value = "/myReviewDelete.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity myReviewDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String member_id = request.getParameter("member_id");
		String review_no = request.getParameter("review_no");
		try {
			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("member_id", member_id);
			condMap.put("review_no", review_no);
			myPageService.deleteReview(condMap);

			message = "<script>";
			message += "alert('이용후기가 삭제되었습니다.');";
			message += "location.href='" + request.getContextPath()
					+ "/mypage/myReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&member_id=" + member_id
					+ "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('에러');";
			message += "location.href = '" + request.getContextPath()
					+ "/mypage/myReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&member_id=" + member_id
					+ "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;

	}
	
	// 리뷰수정창
	@Override
	@RequestMapping(value = "/myreviewModify.do", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity myreviewModify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String member_id = request.getParameter("member_id");
		String review_no = request.getParameter("review_no");
		System.out.println("1 : "+member_id);
		System.out.println("2 : "+review_no);
		try {
			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("member_id", member_id);
			condMap.put("review_no", review_no);
			
			message = "<script>";
			message += "alert('해당 이용후기가 수정되었습니다.');";
			message += "location.href='" + request.getContextPath()
			+ "/mypage/myReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&member_id=" + member_id
			+ "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('에러');";
			message += "location.href = '" + request.getContextPath()
			+ "/mypage/myReviewList.do?chapter=1&firstDate=&secondDate=&text_box=&member_id=" + member_id
			+ "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;

	}
	
	
	/* 고객센터 - Q&A */
	 
	@RequestMapping(value = "/myQnaList.do", method = RequestMethod.GET)
	public ModelAndView myQnaList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		//로그인 정보 가져오기
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		String member_id = memberVO.getMember_id(); //로그인한 member_id
		
		/* 질문 목록 */
		List<QnaVO> questionList = myPageService.myQuestionList(member_id);
		mav.addObject("questionList", questionList);
		
		/* 답변 목록 */
		List<QnaVO> answerList = myPageService.myAnswerList(member_id);
		mav.addObject("answerList", answerList);
		
		return mav;
	}
	
	
	@Override
	@RequestMapping(value = "modifyQuestion.do", method = RequestMethod.POST)
	public ResponseEntity modifyQuestion(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		//로그인 정보 가져오기
		HttpSession session=request.getSession();
		Boolean isLogOn=(Boolean)session.getAttribute("isLogOn");
		MemberVO memberVO=(MemberVO)session.getAttribute("memberInfo");
		
		String member_id = memberVO.getMember_id(); //로그인한 member_id
		qnaVO.setMember_id(member_id);
		
		int qna_no = Integer.parseInt(request.getParameter("qna_no"));
		String qna_title = request.getParameter("qna_title");
		String qna_contents = request.getParameter("qna_contents");
		String qna_secret = request.getParameter("secret");
		if (qna_secret == null) {
			qna_secret = "0";
		}
		System.out.println(qna_secret);
		
		qnaVO.setQna_no(qna_no);
		qnaVO.setQna_title(qna_title);
		qnaVO.setQna_contents(qna_contents);
		qnaVO.setQna_secret(qna_secret);
		
		try {
			myPageService.modifyQna(qnaVO);
			message = "<script>";
			message += " alert('문의글이 수정되었습니다. :)');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요 :( ');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQnaList.do';";
			message += " </script>";
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	
	@Override
	@RequestMapping(value="/removeQna.do" ,method = RequestMethod.GET)
	public ResponseEntity removeQna(@RequestParam("qna_no") int qna_no, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String message = null;
		ResponseEntity resEntity = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		try {
			myPageService.removeQna(qna_no);
			message = "<script>";
			message += " alert('삭제가 완료되었습니다. :)');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQnaList.do';";
			message += " </script>";
		} catch (Exception e) {
			message = "<script>";
			message += " alert('작업 중 오류가 발생했습니다. 다시 시도해 주세요 :( ');";
			message += " location.href='" + request.getContextPath() + "/mypage/myQnaList.do';";
			message += " </script>";
		}
		resEntity = new ResponseEntity(message, responseHeaders, HttpStatus.OK);
		return resEntity;
	}
	

}
