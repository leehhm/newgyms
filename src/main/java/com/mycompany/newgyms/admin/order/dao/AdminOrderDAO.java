package com.mycompany.newgyms.admin.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

public interface AdminOrderDAO {
	public List<OrderVO> selectAllOrderList(Map condMap) throws DataAccessException;

	public List<OrderVO> orderMemberListSelect(Map condMap) throws DataAccessException;

	public List<OrderVO> selectOrderMember(Map condMap) throws DataAccessException;

	public List<OrderVO> selectAdminOrderDetail(int order_id) throws DataAccessException;
	
	public RefundVO selectAdminRefundDetail(int order_seq_num) throws DataAccessException;

	public List<OrderVO> selectAdminOrderCancel(Map orderMap) throws DataAccessException;

	public void refundOrder(Map refundMap) throws DataAccessException;

	public int selectNewRefundId() throws DataAccessException;

	public String maxNumSelect(Map condMap) throws DataAccessException;
}
