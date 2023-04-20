package com.mycompany.newgyms.admin.order.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

public interface AdminOrderService {

	public List<OrderVO> listAllOrders(Map condMap) throws Exception;

	public List<OrderVO> orderMemberList(Map condMap) throws Exception;

	public List<OrderVO> orderMember(Map<String, Object> condMap) throws Exception;

	public List<OrderVO> adminOrderDetail(int order_id) throws Exception;
	
	public RefundVO adminRefundDetail(int order_seq_num) throws Exception;

	public List<OrderVO> adminOrderCancel(Map orderMap) throws Exception;

	public void adminOrderRefund(Map refundMap) throws Exception;

	public String maxNumSelect(Map condMap) throws Exception;
}
