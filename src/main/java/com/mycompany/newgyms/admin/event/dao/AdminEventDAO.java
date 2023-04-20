package com.mycompany.newgyms.admin.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AdminEventDAO {
	// 이벤트 목록 조회
	public List selectAllEventList() throws DataAccessException;
	
	// 이벤트 삭제
	public void deleteEvent(int event_no) throws DataAccessException;
}
