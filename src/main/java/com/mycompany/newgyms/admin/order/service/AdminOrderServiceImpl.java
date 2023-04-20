package com.mycompany.newgyms.admin.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.order.dao.AdminOrderDAO;
import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;

@Service("adminOrderService")
@Transactional(propagation=Propagation.REQUIRED)
public class AdminOrderServiceImpl implements AdminOrderService {
	@Autowired
	private AdminOrderDAO adminOrderDAO;
	
	@Override
	public List<OrderVO> listAllOrders(Map condMap) throws Exception {
		return adminOrderDAO.selectAllOrderList(condMap);
	}
	
	@Override
	public List<OrderVO> orderMemberList(Map condMap) throws Exception {
		return adminOrderDAO.orderMemberListSelect(condMap);
	}
	
	@Override
	public List<OrderVO> orderMember(Map condMap) throws Exception {
		return adminOrderDAO.selectOrderMember(condMap);
	}
	
	@Override
	public List<OrderVO> adminOrderDetail(int order_id) throws Exception {
		return adminOrderDAO.selectAdminOrderDetail(order_id);
	}
	
	@Override
	public RefundVO adminRefundDetail(int order_seq_num) throws Exception {
		return adminOrderDAO.selectAdminRefundDetail(order_seq_num);
	}
	
	@Override
	public List<OrderVO> adminOrderCancel(Map orderMap) throws Exception {
		return adminOrderDAO.selectAdminOrderCancel(orderMap);
	}

	@Override
	public void adminOrderRefund(Map refundMap) throws Exception {
		adminOrderDAO.refundOrder(refundMap);
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws Exception {
		return adminOrderDAO.maxNumSelect(condMap);
	}
}
