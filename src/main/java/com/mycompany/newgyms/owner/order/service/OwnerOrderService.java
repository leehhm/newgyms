package com.mycompany.newgyms.owner.order.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

public interface OwnerOrderService {

	// 결제내역 조회
	public List<OrderVO> listOwnerOrders(Map condMap) throws Exception;
	public OrderVO ownerOrderDetail(int order_seq_num) throws Exception;
	public RefundVO ownerOrderCancel(int order_seq_num) throws Exception;
	public void ownerOrderRefund(int order_seq_num) throws Exception;
	public String maxNumSelect(Map condMap) throws Exception;

}
