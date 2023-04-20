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
	
	// 이벤트 목록
	@Override
	public List<EventVO> listEvents() throws Exception {
		List<EventVO> eventsList = eventDAO.selectAllEventsList();
		return eventsList;
	}
	
	// 이벤트 정렬
	public List<EventVO> sortEvent(String sort) throws Exception {
		List<EventVO> eventsList = eventDAO.selectSortEvent(sort);
		return eventsList;
	}
	
	// 상품 목록 가져오기
	public List<ProductVO> productList(String member_id) throws Exception {
		List<ProductVO> productList = eventDAO.selectProductList(member_id);
		return productList;
	}

	
	// 이벤트 글 쓰기
	@Override
	public int addNewEvent(Map eventMap) throws Exception {
		int event_no = eventDAO.selectNewEventNO();
		eventMap.put("event_no", event_no);
		eventDAO.insertNewEvent(eventMap);
		return event_no;
	}
	
	// 이벤트 상세보기
	@Override
	public EventVO viewEvent(int event_no) throws Exception {
		EventVO eventVO = eventDAO.viewEvent(event_no);
		return eventVO;
	}
	
	// 이벤트 글 수정하기
	@Override
	public void modEvent(Map eventMap) throws Exception {
		eventDAO.updateEvent(eventMap);
	}
	
	// 이벤트 글 삭제하기
	@Override
	public void removeEvent(int event_no) throws Exception {
		eventDAO.deleteEvent(event_no);
	}
}
