package com.mycompany.newgyms.admin.notice.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.newgyms.admin.notice.service.AdminNoticeService;
import com.mycompany.newgyms.notice.vo.NoticeVO;

@Controller("adminNoticeController")
@RequestMapping(value = "/admin/notice")
public class AdminNoticeControllerImpl implements AdminNoticeController {
	private static final String NOTICE_IMAGE_REPO = "C:\\newgyms\\notice\\notice_image";
	
	@Autowired
	private AdminNoticeService adminNoticeService;
	@Autowired
	private NoticeVO noticeVO;
	
	// 공지사항 목록
	@Override
	@RequestMapping(value="/adminNoticeList.do", method={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView adminNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
	    List adminNoticeList = adminNoticeService.adminNoticeList();
	    mav.addObject("adminNoticeList", adminNoticeList);
	    
		return mav;
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
           adminNoticeService.removeNotice(notice_no);
           File destDir = new File(NOTICE_IMAGE_REPO + "\\" + notice_no);
           FileUtils.deleteDirectory(destDir);
           
           message = "<script>";
           message += "alert('글을 삭제했습니다.');";
           message += "location.href='" + request.getContextPath() + "/admin/notice/adminNoticeList.do';";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
           message = "<script>";
           message += "alert('작업중 오류가 발생했습니다. 다시 시도해주세요.');";
           message += "location.href = '" + request.getContextPath() + "/admin/notice/adminNoticeList.do';";
           message += "</script>";
           resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
           e.printStackTrace();
        }
        return resEnt;
     }

}
