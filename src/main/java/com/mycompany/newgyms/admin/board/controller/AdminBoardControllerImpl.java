package com.mycompany.newgyms.admin.board.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.admin.board.service.AdminBoardService;
import com.mycompany.newgyms.board.vo.ArticleVO;

@Controller("adminBoardController")
@RequestMapping(value = "/admin/board")
public class AdminBoardControllerImpl implements AdminBoardController {
	private static final String ARTICLE_IMAGE_REPO = "C:\\newgyms\\board\\article_image";

	@Autowired
	private AdminBoardService adminBoardService;

	@Autowired
	private ArticleVO articleVO;

	// 전체 게시글 리스트 불러오기
	@Override
	@RequestMapping(value = "/adminArticleList")
	public ModelAndView adminArticleList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		List adminArticleList = adminBoardService.adminArticleList();
		mav.addObject("adminArticleList", adminArticleList);
		return mav;
	}

	// 자유게시판 글 삭제하기
	@Override
	@RequestMapping(value = "/removeArticle.do", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity removeArticle(@RequestParam("article_no") int article_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			adminBoardService.removeArticle(article_no);
			File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + article_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += "alert('글을 삭제했습니다.');";
			message += "location.href='" + request.getContextPath() + "/admin/board/adminArticleList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('작업중 오류가 발생했습니다. 다시 시도해주세요.');";
			message += "location.href = '" + request.getContextPath() + "/admin/board/adminArticleList.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}
