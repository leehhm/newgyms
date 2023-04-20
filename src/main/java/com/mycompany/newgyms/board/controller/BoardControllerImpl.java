package com.mycompany.newgyms.board.controller;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.board.service.BoardService;
import com.mycompany.newgyms.board.vo.ArticleVO;
import com.mycompany.newgyms.board.vo.ReplyVO;

@Controller("boardController")
@RequestMapping(value = "/board")
public class BoardControllerImpl implements BoardController {
   private static final String ARTICLE_IMAGE_REPO = "C:\\newgyms\\board\\article_image";
   
   @Autowired
   private BoardService boardService;
   @Autowired
   private ArticleVO articleVO;
   @Autowired
   private ReplyVO replyVO;
   
   // 자유게시판 목록
   @Override
   @RequestMapping(value="/listArticles.do", method={RequestMethod.GET, RequestMethod.POST})
   public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      List articlesList = boardService.listArticles();
      ModelAndView mav = new ModelAndView(viewName);
      mav.addObject("articlesList", articlesList);
      return mav;      
   }
   
   // 자유게시판 글 쓰기 페이지 이동
   @Override
   @RequestMapping(value="/articleForm.do")
   public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      ModelAndView mav = new ModelAndView(viewName);
      return mav;      
   }
   
   // 자유게시판 글 등록
   @Override
   @RequestMapping(value="/addNewArticle.do", method=RequestMethod.POST)
   public ResponseEntity addNewArticle(MultipartHttpServletRequest multipartRequest, 
         HttpServletResponse response) throws Exception {
      multipartRequest.setCharacterEncoding("utf-8");
      Map<String, Object> articleMap = new HashMap<String, Object>();
      Enumeration enu = multipartRequest.getParameterNames();
      while (enu.hasMoreElements()) {
         String name = (String)enu.nextElement();
         String value = multipartRequest.getParameter(name);
         articleMap.put(name, value);
      }
      
      String board_image = upload(multipartRequest);
      articleMap.put("parentNO",  0);
      articleMap.put("board_image", board_image);
      
      String message;
      ResponseEntity resEnt = null;
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
      try {
         int article_no = boardService.addNewArticle(articleMap);
         if(board_image != null && board_image.length() != 0) {
            File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
            File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + article_no);
            FileUtils.moveFileToDirectory(srcFile, destDir, true);
         }
         
         message = "<script>";
         message += "alert('새 글을 추가했습니다.');";
         message += "location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do'; ";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
         srcFile.delete();
      
         message = "<script>";
         message += "alert('오류가 발생했습니다. 다시 시도해주세요.');";
         message += "location.href = '" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
         e.printStackTrace();
      }
      return resEnt;
   }
   
   // 한개 이미지 업로드하기
      private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
         String board_image = null;
         Iterator<String> fileNames = multipartRequest.getFileNames();
         
         while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile mFile = multipartRequest.getFile(fileName);
            board_image = mFile.getOriginalFilename();
            File file = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
            
            if (mFile.getSize() != 0) { // File null Check
               if (!file.exists()) { // 경로 상에 파일이 존재하지 않을 경우
                  file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
                  mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image));
                  // 임시로 저장된 multipartFile을 실제 파일로 전송
               }
            }
         }
         return board_image;
      }   
      
   // 자유게시판 글 상세보기
   @Override
   @RequestMapping(value="/viewArticle.do", method=RequestMethod.GET)
   public ModelAndView viewArticle(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      
      articleVO = boardService.viewArticle(article_no);
      List<ReplyVO> replyList = boardService.replyList(article_no);
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      mav.addObject("replyList", replyList);
      mav.addObject("article", articleVO);
      return mav;
   }
   
    // 댓글 작성
    @Override
    @RequestMapping(value="/addReply.do", method = RequestMethod.POST)
	public @ResponseBody String addReply(@RequestParam("article_no") int article_no, @RequestParam("reply_id") String reply_id, @RequestParam("reply_content") String reply_content, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
    	Map<String, Object> replyMap = new HashMap<String, Object>();
    	replyMap.put("article_no", article_no);
    	replyMap.put("member_id", reply_id);
    	replyMap.put("reply_content", reply_content);
    	
    	String result = boardService.addReply(replyMap);
    	
    	if (result == "success" || result.equals("success")){
			return "success";
		} else{
			return "false";
		}
    }

    // 댓글 삭제
    @Override
    @RequestMapping(value="/removeReply.do", method = RequestMethod.POST)
	public @ResponseBody String removeReply(@RequestParam("reply_no") int reply_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String result = boardService.removeReply(reply_no);
    	
    	if (result == "success" || result.equals("success")){
			return "success";
		} else{
			return "false";
		}
    }
   
   // 자유게시판 글 수정 페이지로 이동하기
   @Override
   @RequestMapping(value="/modArticleForm.do", method=RequestMethod.POST)
   public ModelAndView modArticleForm(@RequestParam("article_no") int article_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      articleVO = boardService.viewArticle(article_no);
      ModelAndView mav = new ModelAndView();
      mav.setViewName(viewName);
      mav.addObject("article", articleVO);
      return mav;
   }
   
   // 자유게시판 글 수정하기
   @Override
   @RequestMapping(value="/updateArticle.do", method=RequestMethod.POST)
   public ResponseEntity updateArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
      multipartRequest.setCharacterEncoding("utf-8");
      Map<String, Object> articleMap = new HashMap<String, Object>();
      Enumeration enu = multipartRequest.getParameterNames();
      while (enu.hasMoreElements()) {
         String name = (String)enu.nextElement();
         String value = multipartRequest.getParameter(name);
         articleMap.put(name, value);
      }
      
      String board_image = upload(multipartRequest);
      String originalFileName = multipartRequest.getParameter("originalFileName");

      if (board_image == null || board_image.equals("")) {
    	  articleMap.put("board_image", originalFileName);    	  
      } else {
    	  articleMap.put("board_image", board_image);
      }
      
      String article_no = (String)articleMap.get("article_no");
      String message;
      ResponseEntity resEnt = null;
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
      
      try {
         boardService.modArticle(articleMap);
         if (board_image != null && board_image.length() != 0) {
            File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
            File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + article_no);
            FileUtils.moveFileToDirectory(srcFile, destDir, true);
            
            originalFileName = (String)articleMap.get("originalFileName");
            File oldFile = new File(ARTICLE_IMAGE_REPO + "\\" + article_no + "\\" + originalFileName);
            oldFile.delete();
         }
         
         message = "<script>";
         message += "alert('글을 수정했습니다.');";
         message += "location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?article_no="+article_no+"';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
         srcFile.delete();
         message = "<script>";
         message += "alert('오류가 발생했습니다. 다시 수정해주세요.');";
         message += "location.href = '" + multipartRequest.getContextPath() + "/board/viewArticle.do?article_no="+article_no+"';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      }
      return resEnt;
   }

   
   // 자유게시판 글 삭제하기
   @Override
   @RequestMapping(value="/removeArticle.do", method=RequestMethod.POST)
   @ResponseBody
   public ResponseEntity removeArticle(@RequestParam("article_no") int article_no, 
         HttpServletRequest request, HttpServletResponse response) throws Exception {
      response.setContentType("text/html; charset=UTF-8");
      String message;
      ResponseEntity resEnt = null;
      HttpHeaders responseHeaders = new HttpHeaders();
      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
      
      try {
         boardService.removeArticle(article_no);
         File destDir = new File(ARTICLE_IMAGE_REPO + "\\" + article_no);
         FileUtils.deleteDirectory(destDir);
         
         message = "<script>";
         message += "alert('글을 삭제했습니다.');";
         message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         message = "<script>";
         message += "alert('작업중 오류가 발생했습니다. 다시 시도해주세요.');";
         message += "location.href = '" + request.getContextPath() + "/board/listArticles.do';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
         e.printStackTrace();
      }
      return resEnt;
   }
}