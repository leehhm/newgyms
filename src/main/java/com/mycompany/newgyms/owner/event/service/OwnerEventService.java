package com.mycompany.newgyms.owner.event.service;

import java.util.List;

import com.mycompany.newgyms.event.vo.EventVO;

public interface OwnerEventService {
	// �̺�Ʈ ���� ��ȸ
	public List<EventVO> ownerEventList(String member_id) throws Exception;
	
	// �̺�Ʈ ����
	public void removeEvent(int event_no) throws Exception;
}