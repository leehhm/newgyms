package com.mycompany.newgyms.owner.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface OwnerEventDAO {
	// 이벤트 내역 조회
	public List selectOwnerEventList(String member_id) throws DataAccessException;
	
	// 이벤트 삭제
	public void deleteEvent(int event_no) throws DataAccessException;
}