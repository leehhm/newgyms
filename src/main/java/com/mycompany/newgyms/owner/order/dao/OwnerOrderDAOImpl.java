package com.mycompany.newgyms.owner.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

@Repository("ownerOrderDAO")
public class OwnerOrderDAOImpl implements OwnerOrderDAO {
	@Autowired
	private SqlSession sqlSession;
	
	// 결제내역 조회
	@Override
	public List<OrderVO> selectOwnerOrderList(Map condMap) throws DataAccessException {
		List<OrderVO> orderList = (List) sqlSession.selectList("mapper.owner_order.selectOwnerOrderList", condMap);
		return orderList;
	}
	
	@Override
	public OrderVO selectOwnerOrderDetail(int order_seq_num) throws DataAccessException {
		OrderVO order = sqlSession.selectOne("mapper.owner_order.selectOwnerOrderDetail", order_seq_num);
		return order;
	}
	
	@Override
	public RefundVO selectOwnerOrderCancel(int order_seq_num) throws DataAccessException {
		RefundVO refund = sqlSession.selectOne("mapper.owner_order.selectOwnerOrderCancel", order_seq_num);
		return refund;
	}
	
	@Override
	public void refundOrder(int order_seq_num) throws DataAccessException {
		sqlSession.update("mapper.owner_order.updateRefundState", order_seq_num);
		sqlSession.update("mapper.owner_order.updateOrderState", order_seq_num);
	}

	
	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.owner_order.maxNumSelect", condMap);
		return result;
	}
}
