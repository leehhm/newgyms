package com.mycompany.newgyms.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.order.dao.OrderDAO;
import com.mycompany.newgyms.order.vo.OrderVO;

@Service("orderService")
@Transactional(propagation=Propagation.REQUIRED)
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public String orderPoint(String member_id) throws Exception {
		return orderDAO.orderPoint(member_id);
	}
	
	@Override
	public List<OrderVO> selectOrderProductList(Map orderMap) throws Exception{
		List<OrderVO> myOrderList = orderDAO.selectOrderProductList(orderMap);
		return myOrderList;
	}
	
	@Override
	public int addNewOrder(List<OrderVO> myOrderList) throws Exception{
		int order_id = orderDAO.insertNewOrder(myOrderList);
		return order_id;
	}
	
	@Override
	public int addNewNonMemOrder(List<OrderVO> myOrderList) throws Exception{
		int order_id = orderDAO.insertNewNonMemOrder(myOrderList);
		return order_id;
	}
	
	@Override
	public List<OrderVO> nonMemberOrderDetail(int order_id) throws Exception {
		return orderDAO.selectNonMemberOrderDetail(order_id);
	}
	
	@Override
	public String orderInfo(Map orderMap) throws Exception {
		return orderDAO.selectOrderInfo(orderMap);
	}

	@Override
	public List<OrderVO> nonMemberOrderCancel(Map orderMap) throws Exception {
		return orderDAO.selectNonMemberOrderCancel(orderMap);
	}
}
