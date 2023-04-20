package com.mycompany.newgyms.admin.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AdminEventDAO {
	// �̺�Ʈ ��� ��ȸ
	public List selectAllEventList() throws DataAccessException;
	
	// �̺�Ʈ ����
	public void deleteEvent(int event_no) throws DataAccessException;
}
