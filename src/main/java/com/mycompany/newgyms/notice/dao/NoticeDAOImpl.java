package com.mycompany.newgyms.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ImageVO;
import com.mycompany.newgyms.notice.vo.NoticeVO;

@Repository("noticeDAO")
public class NoticeDAOImpl implements NoticeDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List selectAllNoticesList() throws DataAccessException {
		List<NoticeVO> noticesList = sqlSession.selectList("mapper.notice.selectAllNoticesList");
		return noticesList;
	}
	
	@Override
	public int insertNewNotice(Map noticeMap) throws DataAccessException {
		int notice_no = selectNewNoticeNO();
		noticeMap.put("notice_no", notice_no);
		sqlSession.insert("mapper.notice.insertNewNotice", noticeMap);
		return notice_no;
	}
	
	@Override
	public NoticeVO viewNotice(int notice_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.notice.viewNotice", notice_no);
	}
	
	@Override
	public void updateNotice(Map noticeMap) throws DataAccessException {
		sqlSession.update("mapper.notice.updateNotice", noticeMap);
	}
	
	@Override
	public void deleteNotice(int notice_no) throws DataAccessException {
		sqlSession.delete("mapper.notice.deleteNotice", notice_no);
	}
	
	@Override
	public List selectImageFileList(int notice_no) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.notice.selectImageFileList", notice_no);
		return imageFileList;
	}
	
	@Override
	public int selectNewNoticeNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.notice.selectNewNoticeNO");
	}

	@Override
	public int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.notice.selectNewImageFileNO");
	}
}
