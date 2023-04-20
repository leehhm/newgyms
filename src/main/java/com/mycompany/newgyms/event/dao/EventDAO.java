package com.mycompany.newgyms.event.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.event.vo.EventVO;

public interface EventDAO {
	public List selectAllEventsList() throws DataAccessException;
	public List selectSortEvent(String sort) throws DataAccessException;
	public List selectProductList(String member_id) throws DataAccessException;
	public int insertNewEvent(Map eventMap) throws DataAccessException;
	
	public EventVO viewEvent(int event_no) throws DataAccessException;
	public void updateEvent(Map eventMap) throws DataAccessException;
	public void deleteEvent(int event_no) throws DataAccessException;
	
	public List selectImageFileList(int event_no) throws DataAccessException;
	public int selectNewEventNO() throws DataAccessException;
	public int selectNewImageFileNO() throws DataAccessException;
}
