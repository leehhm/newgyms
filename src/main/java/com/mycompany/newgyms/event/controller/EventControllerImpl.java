package com.mycompany.newgyms.event.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.mycompany.newgyms.event.service.EventService;
import com.mycompany.newgyms.event.vo.EventVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Controller("eventController")
@RequestMapping(value = "event")
public class EventControllerImpl implements EventController {
	private static final String EVENT_IMAGE_REPO = "C:\\newgyms\\event\\event_image";

	@Autowired
	private EventService eventService;
	@Autowired
	private EventVO eventVO;

	// �̺�Ʈ ���
	@Override
	@RequestMapping(value = "/listEvents.do", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listEvents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List eventsList = eventService.listEvents();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("eventsList", eventsList);

		return mav;
	}

	// �̺�Ʈ �����ϱ� (��ü, ������, ����)
	@Override
	@RequestMapping(value = "/eventSorting.do", method = RequestMethod.GET)
	public ModelAndView eventSorting(@RequestParam("sort") String sort, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();

		DateFormat nowdate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());

		// ������ (today >= startDate AND today <= endDate), ���� (today > endDate)
		List eventsList = eventService.sortEvent(sort);

		mav.addObject("eventsList", eventsList);
		mav.setViewName("/event/listEvents");
		
		return mav;
	}

	// �̺�Ʈ �󼼺���
	@Override
	@RequestMapping(value = "/viewEvent.do", method = RequestMethod.GET)
	public ModelAndView viewEvent(@RequestParam("event_no") int event_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		eventVO = eventService.viewEvent(event_no);
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("event", eventVO);

		return mav;
	}

	// �̺�Ʈ �� ���� ������ �̵�
	@Override
	@RequestMapping(value = "/eventForm.do")
	public ModelAndView eventForm(@RequestParam("member_id") String member_id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView(viewName);
		
		List<ProductVO> productList = eventService.productList(member_id);
		
		mav.addObject("productList", productList);
		return mav;
	}

	// �������� �� �ۼ��ϱ�
	@Override
	@RequestMapping(value = "/addNewEvent.do", method = RequestMethod.POST)
	public ResponseEntity addNewEvent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> eventMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			eventMap.put(name, value);
		}

		String event_image = upload(multipartRequest);
		eventMap.put("event_image", event_image);

		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		try {
			int event_no = eventService.addNewEvent(eventMap);
			if (event_image != null && event_image.length() != 0) {
				File srcFile = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + event_image);
				File destDir = new File(EVENT_IMAGE_REPO + "\\" + event_no);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}

			message = "<script>";
			message += "alert('�� ���� �߰��߽��ϴ�.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/event/listEvents.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + event_image);
			srcFile.delete();

			message = "<script>";
			message += "alert('������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');";
			message += "location.href = '" + multipartRequest.getContextPath() + "/event/eventForm.do'; ";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

	// �Ѱ� �̹��� ���ε��ϱ�
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {
		String event_image = null;
		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			event_image = mFile.getOriginalFilename();
			File file = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + fileName);

			if (mFile.getSize() != 0) { // File null Check
				if (!file.exists()) { // ��� �� ������ �������� ���� ���
					file.getParentFile().mkdirs(); // ��ο� �ش��ϴ� ���丮���� ����
					mFile.transferTo(new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + event_image));
					// �ӽ÷� ����� multipartFile�� ���� ���Ϸ� ����
				}
			}
		}
		return event_image;
	}

	// �̺�Ʈ �� �����ϱ� ������ �̵�
	@Override
	@RequestMapping(value = "/modEventForm.do", method = RequestMethod.POST)
	public ModelAndView modEventForm(@RequestParam("event_no") int event_no, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		eventVO = eventService.viewEvent(event_no);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		mav.addObject("event", eventVO);
		return mav;
	}

	// �������� �� �����ϱ�
	@Override
	@RequestMapping(value = "/updateEvent.do", method = RequestMethod.POST)
	public ResponseEntity updateEvent(MultipartHttpServletRequest multipartRequest, HttpServletResponse response)
			throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		Map<String, Object> eventMap = new HashMap<String, Object>();
		Enumeration enu = multipartRequest.getParameterNames();
		while (enu.hasMoreElements()) {
			String name = (String) enu.nextElement();
			String value = multipartRequest.getParameter(name);
			eventMap.put(name, value);
		}

		String event_image = upload(multipartRequest);
		String originalFileName = multipartRequest.getParameter("originalFileName");
		
		if (event_image == null || event_image.equals("")) {
			eventMap.put("event_image", originalFileName);    	  
	      } else {
	    	eventMap.put("event_image", event_image);
	      }

		String event_no = (String) eventMap.get("event_no");
		String message;
		ResponseEntity resEnt = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");

		try {
			eventService.modEvent(eventMap);
			if (event_image != null && event_image.length() != 0) {
				File srcFile = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + event_image);
				File destDir = new File(EVENT_IMAGE_REPO + "\\" + event_no);
				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				originalFileName = (String) eventMap.get("originalFileName");
				File oldFile = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + originalFileName);
				oldFile.delete();
			}

			message = "<script>";
			message += "alert('���� �����߽��ϴ�.');";
			message += "location.href='" + multipartRequest.getContextPath() + "/event/viewEvent.do?event_no="
					+ event_no + "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			File srcFile = new File(EVENT_IMAGE_REPO + "\\" + "temp" + "\\" + event_image);
			srcFile.delete();
			message = "<script>";
			message += "alert('������ �߻��߽��ϴ�. �ٽ� �������ּ���.');";
			message += "location.href = '" + multipartRequest.getContextPath() + "/event/viewEvent.do?event_no="
					+ event_no + "';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		}
		return resEnt;
	}

	// �̺�Ʈ �� �����ϱ�
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
			eventService.removeEvent(event_no);
			File destDir = new File(EVENT_IMAGE_REPO + "\\" + event_no);
			FileUtils.deleteDirectory(destDir);

			message = "<script>";
			message += "alert('���� �����߽��ϴ�.');";
			message += "location.href='" + request.getContextPath() + "/event/listEvents.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
		} catch (Exception e) {
			message = "<script>";
			message += "alert('�۾��� ������ �߻��߽��ϴ�. �ٽ� �õ����ּ���.');";
			message += "location.href = '" + request.getContextPath() + "/event/listEvents.do';";
			message += "</script>";
			resEnt = new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
			e.printStackTrace();
		}
		return resEnt;
	}

}