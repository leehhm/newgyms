package com.mycompany.newgyms.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.notice.vo.NoticeVO;

public interface NoticeDAO {
	public List selectAllNoticesList() throws DataAccessException;
	public int insertNewNotice(Map noticeMap) throws DataAccessException;
	
	public NoticeVO viewNotice(int notice_no) throws DataAccessException;
	public void updateNotice(Map noticeMap) throws DataAccessException;
	public void deleteNotice(int notice_no) throws DataAccessException;
	
	public List selectImageFileList(int notice_no) throws DataAccessException;
	public int selectNewNoticeNO() throws DataAccessException;
	public int selectNewImageFileNO() throws DataAccessException;
}
