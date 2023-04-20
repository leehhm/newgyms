package com.mycompany.newgyms.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.notice.dao.NoticeDAO;
import com.mycompany.newgyms.notice.vo.NoticeVO;

@Service("noticeService")
@Transactional(propagation = Propagation.REQUIRED)
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	NoticeDAO noticeDAO;
	
	// 공지사항 목록
	@Override
	public List<NoticeVO> listNotices() throws Exception {
		List<NoticeVO> noticesList = noticeDAO.selectAllNoticesList();
		return noticesList;
	}
	
	// 공지사항 글 쓰기
	@Override
	public int addNewNotice(Map noticeMap) throws Exception {
		int notice_no = noticeDAO.selectNewNoticeNO();
		noticeMap.put("notice_no", notice_no);
		noticeDAO.insertNewNotice(noticeMap);
		return notice_no;
	}

	
	// 공지사항 상세보기
	@Override
	public NoticeVO viewNotice(int notice_no) throws Exception {
		NoticeVO noticeVO = noticeDAO.viewNotice(notice_no);
		return noticeVO;
	}
	
	@Override
	public void modNotice(Map noticeMap) throws Exception {
		noticeDAO.updateNotice(noticeMap);
	}
	
	@Override
	public void removeNotice(int notice_no) throws Exception {
		noticeDAO.deleteNotice(notice_no);
	}
}
