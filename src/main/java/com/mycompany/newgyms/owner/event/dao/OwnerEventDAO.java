package com.mycompany.newgyms.owner.event.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface OwnerEventDAO {
	// �̺�Ʈ ���� ��ȸ
	public List selectOwnerEventList(String member_id) throws DataAccessException;
	
	// �̺�Ʈ ����
	public void deleteEvent(int event_no) throws DataAccessException;
}