package com.mycompany.newgyms.owner.order.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

public interface OwnerOrderDAO {
	// 결제내역 조회
	public List<OrderVO> selectOwnerOrderList(Map condMap) throws DataAccessException;
	public OrderVO selectOwnerOrderDetail(int order_seq_num) throws DataAccessException;
	public RefundVO selectOwnerOrderCancel(int order_seq_num) throws DataAccessException;
	public void refundOrder(int order_seq_num) throws DataAccessException;

	public String maxNumSelect(Map condMap) throws DataAccessException;
}
