package com.mycompany.newgyms.admin.event.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.event.vo.EventVO;

@Repository("adminEventDAO")
public class AdminEventDAOImpl implements AdminEventDAO {
	@Autowired
	private SqlSession sqlSession;

	// �̺�Ʈ ���� ��ȸ
	@Override
	public List selectAllEventList() throws DataAccessException {
		List<EventVO> adminEventList = sqlSession.selectList("mapper.admin_event.selectAllEventList");
		return adminEventList;
	}

	// �̺�Ʈ �� �����ϱ�
	@Override
	public void deleteEvent(int event_no) throws DataAccessException {
		sqlSession.delete("mapper.admin_event.deleteEvent", event_no);
	}
}
