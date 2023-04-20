package com.mycompany.newgyms.admin.notice.service;

import java.util.List;

import com.mycompany.newgyms.notice.vo.NoticeVO;

public interface AdminNoticeService {
	// 공지사항 목록
	public List<NoticeVO> adminNoticeList() throws Exception;
	
	// 공지사항 삭제
	public void removeNotice(int notice_no) throws Exception;
}