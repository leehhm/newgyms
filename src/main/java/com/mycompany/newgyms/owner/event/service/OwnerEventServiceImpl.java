package com.mycompany.newgyms.owner.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.event.vo.EventVO;
import com.mycompany.newgyms.owner.event.dao.OwnerEventDAO;

@Service("ownerEventService")
@Transactional(propagation=Propagation.REQUIRED)
public class OwnerEventServiceImpl implements OwnerEventService {
	@Autowired
	private OwnerEventDAO ownerEventDAO;
	
	// 이벤트 내역 조회
	@Override
	public List<EventVO> ownerEventList(String member_id) throws Exception {
		List<EventVO> ownerEventList = ownerEventDAO.selectOwnerEventList(member_id);
		return ownerEventList;
	}
	
	// 이벤트 글 삭제하기
	@Override
	public void removeEvent(int event_no) throws Exception {
		ownerEventDAO.deleteEvent(event_no);
	}
}
