package com.mycompany.newgyms.product.controller;

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

import com.mycompany.newgyms.member.service.MemberService;
import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.product.service.ProductService;
import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.qna.service.QnaService;
import com.mycompany.newgyms.qna.vo.QnaVO;
import com.mycompany.newgyms.review.service.ReviewService;
import com.mycompany.newgyms.wish.service.WishService;
import com.mycompany.newgyms.wish.vo.WishVO;

@Controller("productController")
@RequestMapping(value = "/product")
public class ProductControllerImpl implements ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private WishService wishService;
	
	@Autowired
	private WishVO wishVO;
	
	/* 카테고리별, 지역별 조회 */
	@RequestMapping(value = "/productList.do", method = RequestMethod.GET)
	public ModelAndView productList(@RequestParam("category") String product_sort,
			@RequestParam("address") String address, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		Map listMap = new HashMap();
		listMap.put("product_sort", product_sort);
		listMap.put("address", address);

		List<ProductVO> productList = productService.productList(listMap);

		mav.addObject("productList", productList);
		mav.addObject("productSort", product_sort);
		mav.addObject("address", address);
		return mav;
	}

	/* 정렬하여 조회 - 신상품/인기순/낮은가격/높은가격 */
	@RequestMapping(value = "/productSorting.do", method = RequestMethod.GET)
	public ModelAndView productSorting(@RequestParam("category") String product_sort,
			@RequestParam("address") String address, @RequestParam("sortBy") String sortBy, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		Map sortMap = new HashMap();
		sortMap.put("product_sort", product_sort);
		sortMap.put("address", address);
		sortMap.put("sortBy", sortBy);

		List<ProductVO> productList = productService.productSorting(sortMap);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("productList", productList);
		mav.addObject("productSort", product_sort);
		mav.addObject("address", address);

		return mav;
	}

	/* 상품검색 */
	@RequestMapping(value = "/searchProduct.do", method = RequestMethod.GET)
	public ModelAndView searchProduct(@RequestParam("searchWord") String searchWord, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List<ProductVO> productList = productService.searchProduct(searchWord);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("productList", productList);
		mav.addObject("searchWord", searchWord);

		return mav;

	}

	/* 상품 상세검색 */
	@RequestMapping(value = "/searchProductByCondition.do", method = RequestMethod.GET)
	public ModelAndView searchProductByCondition(@RequestParam("searchOption") String searchOption,
			@RequestParam("searchWord") String searchWord, @RequestParam("minPrice") String minPrice,
			@RequestParam("maxPrice") String maxPrice, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		if (minPrice.equals("")) {
			minPrice = "0";
		}
		if (maxPrice.equals("")) {
			maxPrice = "100000000";
		}
		Map searchMap = new HashMap();
		searchMap.put("searchOption", searchOption);
		searchMap.put("searchWord", searchWord);
		searchMap.put("minPrice", minPrice);
		searchMap.put("maxPrice", maxPrice);
		
		List<ProductVO> productList = productService.searchProductByCondition(searchMap);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("productList", productList);
		mav.addObject("searchWord", searchWord);
		mav.addObject("maxPrice", maxPrice);
		mav.addObject("minPrice", minPrice);

		return mav;

	}

	/* 상품 상세페이지 */
	@RequestMapping(value = "/productDetail.do", method = RequestMethod.GET)
	public ModelAndView productDetail(@RequestParam("product_id") int product_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView(viewName);
		
		/* 옵션 */
		List<ProductOptVO> productOptList = productService.selectProductOptionList(product_id);
		mav.addObject("productOptList", productOptList);

		/* 상품 상세정보, 프로그램 안내 */
		ProductVO productVO = productService.productDetail(product_id);
		mav.addObject("productVO", productVO);
		
		/* 프로그램 안내 이미지 */
		Map imageMap = productService.productImage(product_id);
		mav.addObject("imageMap", imageMap);

		/* 리뷰 */
		Map<String ,List> reviewMap = reviewService.productReviewList(product_id);
		mav.addObject("reviewMap", reviewMap);

		/* Q&A */
		/* 질문 목록 */
		List<QnaVO> questionList = qnaService.productQuestionList(product_id);
		mav.addObject("questionList", questionList);

		/* 답변 목록 */
		List<QnaVO> answerList = qnaService.productAnswerList(product_id);
		mav.addObject("answerList", answerList);


		/* 사업자 정보 */
		String member_id = productVO.getMember_id(); /* 사업자 아이디 */
		MemberVO memberVO = memberService.ownerDetail(member_id);
		mav.addObject("memberVO", memberVO);
		
		
		/* 현재 로그인된 ID */
		MemberVO memberVo = (MemberVO) session.getAttribute("memberInfo");
		if (memberVo != null && memberVo.getMember_id() != null) {
			
			String loginMember_id = memberVo.getMember_id();
			mav.addObject("loginMember_id", loginMember_id);
			
			wishVO.setMember_id(loginMember_id);
			wishVO.setProduct_id(product_id);
			
			/* 찜 목록 추가 여부 확인 */
			boolean isAreadyExisted=wishService.findWishProduct(wishVO);
			mav.addObject("isAreadyExisted", isAreadyExisted);
		}
		
		return mav;
		
	}
	
}
