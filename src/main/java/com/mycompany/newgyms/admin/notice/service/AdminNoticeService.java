package com.mycompany.newgyms.admin.notice.service;

import java.util.List;

import com.mycompany.newgyms.notice.vo.NoticeVO;

public interface AdminNoticeService {
	// �������� ���
	public List<NoticeVO> adminNoticeList() throws Exception;
	
	// �������� ����
	public void removeNotice(int notice_no) throws Exception;
}