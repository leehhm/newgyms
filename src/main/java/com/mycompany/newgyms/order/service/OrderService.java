package com.mycompany.newgyms.order.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.order.vo.OrderVO;

public interface OrderService {
	public String orderPoint(String member_id) throws Exception;
	public List<OrderVO> selectOrderProductList(Map orderMap) throws Exception;
	public int addNewOrder(List<OrderVO> myOrderList) throws Exception;
	public int addNewNonMemOrder(List<OrderVO> myOrderList) throws Exception;
	public List<OrderVO> nonMemberOrderDetail(int order_id) throws Exception;
	public String orderInfo(Map orderMap) throws Exception;
	public List<OrderVO> nonMemberOrderCancel(Map orderMap) throws Exception;
}
