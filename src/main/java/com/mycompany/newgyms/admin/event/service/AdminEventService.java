package com.mycompany.newgyms.admin.event.service;

import java.util.List;

import com.mycompany.newgyms.event.vo.EventVO;

public interface AdminEventService {
	// �̺�Ʈ ��� ��ȸ
	public List<EventVO> adminEventList() throws Exception;
	
	// �̺�Ʈ ����
	public void removeEvent(int event_no) throws Exception;
}