package com.mycompany.newgyms.admin.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.notice.dao.AdminNoticeDAO;
import com.mycompany.newgyms.notice.vo.NoticeVO;

@Service("adminNoticeService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminNoticeServiceImpl implements AdminNoticeService {
	@Autowired
	AdminNoticeDAO adminNoticeDAO;
	
	// �������� ���
	@Override
	public List<NoticeVO> adminNoticeList() throws Exception {
		List<NoticeVO> adminNoticeList = adminNoticeDAO.selectAllNoticesList();
		return adminNoticeList;
	}
	
	// �������� ����
	@Override
	public void removeNotice(int notice_no) throws Exception {
		adminNoticeDAO.deleteNotice(notice_no);
	}
}
