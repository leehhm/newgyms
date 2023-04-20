package com.mycompany.newgyms.owner.event.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.event.vo.EventVO;

@Repository("ownerEventDAO")
public class OwnerEventDAOImpl implements OwnerEventDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 이벤트 내역 조회
	@Override
	public List selectOwnerEventList(String member_id) throws DataAccessException {
		List<EventVO> ownerEventList = sqlSession.selectList("mapper.owner_event.selectOwnerEventList", member_id);
		return ownerEventList;
	}
	
	// 이벤트 글 삭제하기
	@Override
	public void deleteEvent(int event_no) throws DataAccessException {
		sqlSession.delete("mapper.owner_event.deleteEvent", event_no);
	}
}
