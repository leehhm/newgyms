package com.mycompany.newgyms.admin.order.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

@Repository("adminOrderDAO")
public class AdminOrderDAOImpl implements AdminOrderDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<OrderVO> selectAllOrderList(Map condMap) throws DataAccessException {
		List<OrderVO> adminOrderList = (List) sqlSession.selectList("mapper.admin_order.selectAllOrderList", condMap);
		return adminOrderList;
	}
	@Override
	public List<OrderVO> orderMemberListSelect(Map condMap) throws DataAccessException {
		List<OrderVO> orderMemberList = (List) sqlSession.selectList("mapper.admin_order.orderMemberListSelect", condMap);
		return orderMemberList;
	}

	@Override
	public List<OrderVO> selectOrderMember(Map condMap) throws DataAccessException {
		List<OrderVO> orderMember = (List) sqlSession.selectList("mapper.admin_order.selectOrderMember", condMap);
		return orderMember;
	}

	@Override
	public List<OrderVO> selectAdminOrderDetail(int order_id) throws DataAccessException {
		List<OrderVO> orderList = (List) sqlSession.selectList("mapper.admin_order.selectAdminOrderDetail", order_id);
		return orderList;
	}
	
	@Override
	public RefundVO selectAdminRefundDetail(int order_seq_num) throws DataAccessException {
		RefundVO adminRefundDetail = sqlSession.selectOne("mapper.admin_order.selectAdminRefundDetail", order_seq_num);
		return adminRefundDetail;
	}


	@Override
	public List<OrderVO> selectAdminOrderCancel(Map orderMap) throws DataAccessException {
		List<OrderVO> adminOrderList = (List) sqlSession.selectList("mapper.admin_order.selectAdminOrderCancel", orderMap);
		return adminOrderList;
	}

	@Override
	public void refundOrder(Map refundMap) throws DataAccessException {
		sqlSession.update("mapper.admin_order.updateRefundState", refundMap);
		sqlSession.update("mapper.admin_order.updateOrderState", refundMap);
	}
	
	@Override
	public int selectNewRefundId() throws DataAccessException {
		return sqlSession.selectOne("mapper.admin_order.selectNewRefundId");
	}

	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.admin_order.maxNumSelect", condMap);
		return result;
	}
}
