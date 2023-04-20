package com.mycompany.newgyms.admin.event.service;

import java.util.List;

import com.mycompany.newgyms.event.vo.EventVO;

public interface AdminEventService {
	// 이벤트 목록 조회
	public List<EventVO> adminEventList() throws Exception;
	
	// 이벤트 삭제
	public void removeEvent(int event_no) throws Exception;
}