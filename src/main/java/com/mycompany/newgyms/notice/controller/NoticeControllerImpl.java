package com.mycompany.newgyms.notice.controller;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.notice.service.NoticeService;
import com.mycompany.newgyms.notice.vo.NoticeVO;

@Controller("noticeController")
@RequestMapping(value = "notice")
public class NoticeControllerImpl implements NoticeController {
	private static final String NOTICE_IMAGE_REPO = "C:\\newgyms\\notice\\notice_image";
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private NoticeVO noticeVO;
	
	// 공지사항 목록
	@Override
	@RequestMapping(value="/listNotices.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView listNotices(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
	    List noticesList = noticeService.listNotices();
	    ModelAndView mav = new ModelAndView(viewName);
	    mav.addObject("noticesList", noticesList);
	    
		return mav;
	}
	
	// 공지사항 상세보기
	@Override
	@RequestMapping(value="/viewNotice.do", method=RequestMethod.GET)
	public ModelAndView viewNotice(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
	    noticeVO = noticeService.viewNotice(notice_no);
	    ModelAndView mav = new ModelAndView(viewName);
	    mav.addObject("notice", noticeVO);
		
		return mav;
	}
		
	// 한개 이미지 업로드하기
    private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
       String notice_image = null;
       Iterator<String> fileNames = multipartRequest.getFileNames();
       
       while (fileNames.hasNext()) {
          String fileName = fileNames.next();
          MultipartFile mFile = multipartRequest.getFile(fileName);
          notice_image = mFile.getOriginalFilename();
          File file = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);
          
          if (mFile.getSize() != 0) { // File null Check
             if (!file.exists()) { // 경로 상에 파일이 존재하지 않을 경우
                file.getParentFile().mkdirs(); // 경로에 해당하는 디렉토리들을 생성
                mFile.transferTo(new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + notice_image));
                // 임시로 저장된 multipartFile을 실제 파일로 전송
             }
          }
       }
       return notice_image;
    }

    // 공지사항 글 쓰기 페이지 이동
    @Override
	@RequestMapping(value="/noticeForm.do")
    public ModelAndView noticeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String viewName = (String)request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }
    
    // 공지사항 글 작성하기
    @Override
    @RequestMapping(value="/addNewNotice.do", method=RequestMethod.POST)
	public ResponseEntity addNewNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
    	multipartRequest.setCharacterEncoding("utf-8");
        Map<String, Object> noticeMap = new HashMap<String, Object>();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
           String name = (String)enu.nextElement();
           String value = multipartRequest.getParameter(name);
           noticeMap.put(name, value);
        }
        
        String notice_image = upload(multipartRequest);
        noticeMap.put("notice_image", notice_image);
        
        String message;
        ResponseEntity resEnt = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        try {
           int article_no = noticeService.addNewNotice(noticeMap);
           if(notice_image != null && notice_image.length() != 0) {
              File srcFile = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + notice_image);
              File destDir = new File(NOTICE_IMAGE_REPO + "\\" + article_no);
              FileUtils.moveFileToDirectory(srcFile, destDir, true);
           }
           
           message = "<script>";
           message += "alert('새 글을 추가했습니다.');";
           message += "location.href='" + multipartRequest.getContextPath() + "/notice/listNotices.do'; ";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
           File srcFile = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + notice_image);
           srcFile.delete();
        
           message = "<script>";
           message += "alert('오류가 발생했습니다. 다시 시도해주세요.');";
           message += "location.href = '" + multipartRequest.getContextPath() + "/notice/noticeForm.do'; ";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
           e.printStackTrace();
        }
        return resEnt;
    }

    
    // 공지사항 글 수정하기 페이지 이동
    @Override
    @RequestMapping(value="/modNoticeForm.do", method=RequestMethod.POST)
	public ModelAndView modNoticeForm(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String viewName = (String)request.getAttribute("viewName");
        noticeVO = noticeService.viewNotice(notice_no);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(viewName);
        mav.addObject("notice", noticeVO);
        return mav;
	}
    
    // 공지사항 글 수정하기
    @Override
    @RequestMapping(value="/updateNotice.do", method=RequestMethod.POST)
	public ResponseEntity updateNotice(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
	      Map<String, Object> noticeMap = new HashMap<String, Object>();
	      Enumeration enu = multipartRequest.getParameterNames();
	      while (enu.hasMoreElements()) {
	         String name = (String)enu.nextElement();
	         String value = multipartRequest.getParameter(name);
	         noticeMap.put(name, value);
	      }

	      String notice_image = upload(multipartRequest);
	      String originalFileName = multipartRequest.getParameter("originalFileName");

	      if (notice_image == null || notice_image.equals("")) {
	    	  noticeMap.put("notice_image", originalFileName);    	  
	      } else {
		      noticeMap.put("notice_image", notice_image);
	      }
	      
	      String notice_no = (String)noticeMap.get("notice_no");
	      String message;
	      ResponseEntity resEnt = null;
	      HttpHeaders responseHeaders = new HttpHeaders();
	      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
	      
	      try {
	         noticeService.modNotice(noticeMap);
	         if (notice_image != null && notice_image.length() != 0) {
	            File srcFile = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + notice_image);
	            File destDir = new File(NOTICE_IMAGE_REPO + "\\" + notice_no);
	            FileUtils.moveFileToDirectory(srcFile, destDir, true);
	            
	            originalFileName = (String)noticeMap.get("originalFileName");
	            File oldFile = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName);
	            oldFile.delete();
	         }
	         
	         message = "<script>";
	         message += "alert('글을 수정했습니다.');";
	         message += "location.href='" + multipartRequest.getContextPath() + "/notice/viewNotice.do?notice_no="+notice_no+"';";
	         message += "</script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      } catch (Exception e) {
	         File srcFile = new File(NOTICE_IMAGE_REPO + "\\" + "temp" + "\\" + notice_image);
	         srcFile.delete();
	         message = "<script>";
	         message += "alert('오류가 발생했습니다. 다시 수정해주세요.');";
	         message += "location.href = '" + multipartRequest.getContextPath() + "/notice/viewNotice.do?notice_no="+notice_no+"';";
	         message += "</script>";
	         resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	      }
	      return resEnt;
	}
    
    // 공지사항 글 삭제하기
    @Override
    @RequestMapping(value="/removeNotice.do", method=RequestMethod.POST)
	public ResponseEntity removeNotice(@RequestParam("notice_no") int notice_no, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	response.setContentType("text/html; charset=UTF-8");
        String message;
        ResponseEntity resEnt = null;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");
        
        try {
           noticeService.removeNotice(notice_no);
           File destDir = new File(NOTICE_IMAGE_REPO + "\\" + notice_no);
           FileUtils.deleteDirectory(destDir);
           
           message = "<script>";
           message += "alert('글을 삭제했습니다.');";
           message += "location.href='" + request.getContextPath() + "/notice/listNotices.do';";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
           message = "<script>";
           message += "alert('작업중 오류가 발생했습니다. 다시 시도해주세요.');";
           message += "location.href = '" + request.getContextPath() + "/notice/listNotices.do';";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
           e.printStackTrace();
        }
        return resEnt;
     }
}
