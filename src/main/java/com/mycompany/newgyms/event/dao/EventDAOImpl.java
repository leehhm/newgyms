package com.mycompany.newgyms.event.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.board.vo.ImageVO;
import com.mycompany.newgyms.event.vo.EventVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
	// �̺�Ʈ �� ��� �ҷ�����
	@Override
	public List selectAllEventsList() throws DataAccessException {
		List<EventVO> eventsList = sqlSession.selectList("mapper.event.selectAllEventsList");
		return eventsList;
	}
	
	// �̺�Ʈ �� ����
	public List selectSortEvent(String sort) throws DataAccessException {
		List<EventVO> eventsList = sqlSession.selectList("mapper.event.selectAllEventsList");
		
		if (sort == "ing" || sort.equals("ing")) { // ������
			eventsList = sqlSession.selectList("mapper.event.selectIngSortEvent");	
		} else if (sort == "end" || sort.equals("end")) { // ����
			eventsList = sqlSession.selectList("mapper.event.selectEndSortEvent");
		}
		
		return eventsList;
	}
	
	// ��ǰ ��� ��������
	public List selectProductList(String member_id) throws DataAccessException {
		List<ProductVO> productList = sqlSession.selectList("mapper.event.selectProductList", member_id);
		return productList;
	}
	
	// �̺�Ʈ �� ����
	@Override
	public int insertNewEvent(Map eventMap) throws DataAccessException {
		int event_no = selectNewEventNO();
		eventMap.put("event_no", event_no);
		sqlSession.insert("mapper.event.insertNewEvent", eventMap);
		return event_no;
	}
	
	// �̺�Ʈ �� �󼼺���
	@Override
	public EventVO viewEvent(int event_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.event.viewEvent", event_no);
	}
	
	// �̺�Ʈ �� �����ϱ�
	@Override
	public void updateEvent(Map eventMap) throws DataAccessException {
		sqlSession.update("mapper.event.updateEvent", eventMap);
	}
	
	// �̺�Ʈ �� �����ϱ�
	@Override
	public void deleteEvent(int event_no) throws DataAccessException {
		sqlSession.delete("mapper.event.deleteEvent", event_no);
	}
	
	// �̹��� ���� ����Ʈ �ҷ�����
	@Override
	public List selectImageFileList(int event_no) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.event.selectImageFileList", event_no);
		return imageFileList;
	}
	
	// �̺�Ʈ �� ��ȣ �����ϱ�
	@Override
	public int selectNewEventNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.event.selectNewEventNO");
	}
	
	// �̺�Ʈ ���� ���� ��ȣ �����ϱ�
	@Override
	public int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.event.selectNewImageFileNO");
	}
}
