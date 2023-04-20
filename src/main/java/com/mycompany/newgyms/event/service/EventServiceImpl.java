package com.mycompany.newgyms.event.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.event.dao.EventDAO;
import com.mycompany.newgyms.event.vo.EventVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Service("eventService")
@Transactional(propagation = Propagation.REQUIRED)
public class EventServiceImpl implements EventService {
	@Autowired
	EventDAO eventDAO;
	
	// �̺�Ʈ ���
	@Override
	public List<EventVO> listEvents() throws Exception {
		List<EventVO> eventsList = eventDAO.selectAllEventsList();
		return eventsList;
	}
	
	// �̺�Ʈ ����
	public List<EventVO> sortEvent(String sort) throws Exception {
		List<EventVO> eventsList = eventDAO.selectSortEvent(sort);
		return eventsList;
	}
	
	// ��ǰ ��� ��������
	public List<ProductVO> productList(String member_id) throws Exception {
		List<ProductVO> productList = eventDAO.selectProductList(member_id);
		return productList;
	}

	
	// �̺�Ʈ �� ����
	@Override
	public int addNewEvent(Map eventMap) throws Exception {
		int event_no = eventDAO.selectNewEventNO();
		eventMap.put("event_no", event_no);
		eventDAO.insertNewEvent(eventMap);
		return event_no;
	}
	
	// �̺�Ʈ �󼼺���
	@Override
	public EventVO viewEvent(int event_no) throws Exception {
		EventVO eventVO = eventDAO.viewEvent(event_no);
		return eventVO;
	}
	
	// �̺�Ʈ �� �����ϱ�
	@Override
	public void modEvent(Map eventMap) throws Exception {
		eventDAO.updateEvent(eventMap);
	}
	
	// �̺�Ʈ �� �����ϱ�
	@Override
	public void removeEvent(int event_no) throws Exception {
		eventDAO.deleteEvent(event_no);
	}
}
