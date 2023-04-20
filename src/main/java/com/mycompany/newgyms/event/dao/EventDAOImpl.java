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
	
	
	// 이벤트 글 목록 불러오기
	@Override
	public List selectAllEventsList() throws DataAccessException {
		List<EventVO> eventsList = sqlSession.selectList("mapper.event.selectAllEventsList");
		return eventsList;
	}
	
	// 이벤트 글 정렬
	public List selectSortEvent(String sort) throws DataAccessException {
		List<EventVO> eventsList = sqlSession.selectList("mapper.event.selectAllEventsList");
		
		if (sort == "ing" || sort.equals("ing")) { // 진행중
			eventsList = sqlSession.selectList("mapper.event.selectIngSortEvent");	
		} else if (sort == "end" || sort.equals("end")) { // 종료
			eventsList = sqlSession.selectList("mapper.event.selectEndSortEvent");
		}
		
		return eventsList;
	}
	
	// 상품 목록 가져오기
	public List selectProductList(String member_id) throws DataAccessException {
		List<ProductVO> productList = sqlSession.selectList("mapper.event.selectProductList", member_id);
		return productList;
	}
	
	// 이벤트 글 쓰기
	@Override
	public int insertNewEvent(Map eventMap) throws DataAccessException {
		int event_no = selectNewEventNO();
		eventMap.put("event_no", event_no);
		sqlSession.insert("mapper.event.insertNewEvent", eventMap);
		return event_no;
	}
	
	// 이벤트 글 상세보기
	@Override
	public EventVO viewEvent(int event_no) throws DataAccessException {
		return sqlSession.selectOne("mapper.event.viewEvent", event_no);
	}
	
	// 이벤트 글 수정하기
	@Override
	public void updateEvent(Map eventMap) throws DataAccessException {
		sqlSession.update("mapper.event.updateEvent", eventMap);
	}
	
	// 이벤트 글 삭제하기
	@Override
	public void deleteEvent(int event_no) throws DataAccessException {
		sqlSession.delete("mapper.event.deleteEvent", event_no);
	}
	
	// 이미지 파일 리스트 불러오기
	@Override
	public List selectImageFileList(int event_no) throws DataAccessException {
		List<ImageVO> imageFileList = null;
		imageFileList = sqlSession.selectList("mapper.event.selectImageFileList", event_no);
		return imageFileList;
	}
	
	// 이벤트 글 번호 생성하기
	@Override
	public int selectNewEventNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.event.selectNewEventNO");
	}
	
	// 이벤트 글의 사진 번호 생성하기
	@Override
	public int selectNewImageFileNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.event.selectNewImageFileNO");
	}
}
