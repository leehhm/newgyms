package com.mycompany.newgyms.admin.notice.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface AdminNoticeDAO {
	public List selectAllNoticesList() throws DataAccessException;
	public void deleteNotice(int notice_no) throws DataAccessException;
}