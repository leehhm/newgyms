package com.mycompany.newgyms.owner.event.controller;

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

import com.mycompany.newgyms.event.vo.EventVO;
import com.mycompany.newgyms.owner.event.service.OwnerEventService;

@Controller("ownerEventController")
@RequestMapping(value = "/owner/event")
public class OwnerEventControllerImpl implements OwnerEventController {
	private static final String EVENT_IMAGE_REPO = "C:\\newgyms\\event\\event_image";

	@Autowired
	private OwnerEventService ownerEventService;
	@Autowired
	private EventVO eventVO;
	
	// 이벤트 목록
	@Override
	@RequestMapping(value = "/ownerEventList.do", method = RequestMethod.GET)
	public ModelAndView ownerEventList(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);

		List<EventVO> ownerEventList = ownerEventService.ownerEventList(member_id);
		mav.addObject("ownerEventList", ownerEventList);

		return mav;
	}
	
	// 이벤트 글 삭제하기
		@Override
		@RequestMapping(value = "/removeEvent.do", method = RequestMethod.POST)
		public ResponseEntity removeEvent(@RequestParam("event_no") int event_no, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			response.setContentType("text/html; charset=UTF-8");
			String message;
			ResponseEntity resEnt = null;
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");

			try {
				ownerEventService.removeEvent(event_no);
				File destDir = new File(EVENT_IMAGE_REPO + "\\" + event_no);
				FileUtils.deleteDirectory(destDir);

				message = "<script>";
				message += "alert('글을 삭제했습니다.');";
				message += "location.href='" + request.getContextPath() + "/owner/event/ownerEventList.do';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			} catch (Exception e) {
				message = "<script>";
				message += "alert('작업중 오류가 발생했습니다. 다시 시도해주세요.');";
				message += "location.href = '" + request.getContextPath() + "/owner/event/ownerEventList.do';";
				message += "</script>";
				resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
				e.printStackTrace();
			}
			return resEnt;
		}
}
