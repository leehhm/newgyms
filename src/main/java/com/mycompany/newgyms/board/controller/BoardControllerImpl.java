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
   
   // �����Խ��� ���
   @Override
   @RequestMapping(value="/listArticles.do", method={RequestMethod.GET, RequestMethod.POST})
   public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      List articlesList = boardService.listArticles();
      ModelAndView mav = new ModelAndView(viewName);
      mav.addObject("articlesList", articlesList);
      return mav;      
   }
   
   // �����Խ��� �� ���� ������ �̵�
   @Override
   @RequestMapping(value="/articleForm.do")
   public ModelAndView articleForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String viewName = (String)request.getAttribute("viewName");
      ModelAndView mav = new ModelAndView(viewName);
      return mav;      
   }
   
   // �����Խ��� �� ���
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
         message += "alert('�� ���� �߰��߽��ϴ�.');";
         message += "location.href='" + multipartRequest.getContextPath() + "/board/listArticles.do'; ";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
         srcFile.delete();
      
         message = "<script>";
         message += "alert('������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');";
         message += "location.href = '" + multipartRequest.getContextPath() + "/board/articleForm.do'; ";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
         e.printStackTrace();
      }
      return resEnt;
   }
   
   // �Ѱ� �̹��� ���ε��ϱ�
      private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
         String board_image = null;
         Iterator<String> fileNames = multipartRequest.getFileNames();
         
         while (fileNames.hasNext()) {
            String fileName = fileNames.next();
            MultipartFile mFile = multipartRequest.getFile(fileName);
            board_image = mFile.getOriginalFilename();
            File file = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
            
            if (mFile.getSize() != 0) { // File null Check
               if (!file.exists()) { // ��� �� ������ �������� ���� ���
                  file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
                  mFile.transferTo(new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image));
                  // �ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
               }
            }
         }
         return board_image;
      }   
      
   // �����Խ��� �� �󼼺���
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
   
    // ��� �ۼ�
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

    // ��� ����
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
   
   // �����Խ��� �� ���� �������� �̵��ϱ�
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
   
   // �����Խ��� �� �����ϱ�
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
         message += "alert('���� �����߽��ϴ�.');";
         message += "location.href='" + multipartRequest.getContextPath() + "/board/viewArticle.do?article_no="+article_no+"';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         File srcFile = new File(ARTICLE_IMAGE_REPO + "\\" + "temp" + "\\" + board_image);
         srcFile.delete();
         message = "<script>";
         message += "alert('������ �߻��߽��ϴ�. �ٽ� �������ּ���.');";
         message += "location.href = '" + multipartRequest.getContextPath() + "/board/viewArticle.do?article_no="+article_no+"';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      }
      return resEnt;
   }

   
   // �����Խ��� �� �����ϱ�
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
         message += "alert('���� �����߽��ϴ�.');";
         message += "location.href='" + request.getContextPath() + "/board/listArticles.do';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
      } catch (Exception e) {
         message = "<script>";
         message += "alert('�۾��� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');";
         message += "location.href = '" + request.getContextPath() + "/board/listArticles.do';";
         message += "</script>";
         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
         e.printStackTrace();
      }
      return resEnt;
   }
}