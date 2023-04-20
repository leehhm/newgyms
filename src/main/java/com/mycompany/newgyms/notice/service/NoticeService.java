package com.mycompany.newgyms.notice.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.notice.vo.NoticeVO;

public interface NoticeService {
	public List<NoticeVO> listNotices() throws Exception;
	public int addNewNotice(Map noticeMap) throws Exception;
	public NoticeVO viewNotice(int notice_no) throws Exception;
	public void modNotice(Map noticeMap) throws Exception;
	public void removeNotice(int notice_no) throws Exception;
}
