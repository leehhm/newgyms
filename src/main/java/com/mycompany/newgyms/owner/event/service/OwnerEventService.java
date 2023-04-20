package com.mycompany.newgyms.owner.event.service;

import java.util.List;

import com.mycompany.newgyms.event.vo.EventVO;

public interface OwnerEventService {
	// 이벤트 내역 조회
	public List<EventVO> ownerEventList(String member_id) throws Exception;
	
	// 이벤트 삭제
	public void removeEvent(int event_no) throws Exception;
}