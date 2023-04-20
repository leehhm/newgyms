package com.mycompany.newgyms.admin.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.event.dao.AdminEventDAO;
import com.mycompany.newgyms.event.vo.EventVO;

@Service("adminEventService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminEventServiceImpl implements AdminEventService {
	@Autowired
	private AdminEventDAO adminEventDAO;
	
	// 이벤트 목록 조회
	@Override
	public List<EventVO> adminEventList() throws Exception {
		List<EventVO> adminEventList = adminEventDAO.selectAllEventList();
		return adminEventList;
	}
		
	// 이벤트 글 삭제하기
	@Override
	public void removeEvent(int event_no) throws Exception {
		adminEventDAO.deleteEvent(event_no);
	}
}
