package com.mycompany.newgyms.admin.notice.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.notice.vo.NoticeVO;

@Repository("adminNoticeDAO")
public class AdminNoticeDAOImpl implements AdminNoticeDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 공지사항 목록
	@Override
	public List selectAllNoticesList() throws DataAccessException {
		List<NoticeVO> adminNoticeList = sqlSession.selectList("mapper.admin_notice.selectAllNoticesList");
		return adminNoticeList;
	}
	
	// 공지사항 삭제
	@Override
	public void deleteNotice(int notice_no) throws DataAccessException {
		sqlSession.delete("mapper.admin_notice.deleteNotice", notice_no);
	}
}
