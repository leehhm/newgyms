package com.mycompany.newgyms.owner.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.mypage.vo.RefundVO;
import com.mycompany.newgyms.order.vo.OrderVO;
import com.mycompany.newgyms.owner.order.dao.OwnerOrderDAO;

@Service("ownerOrderService")
@Transactional(propagation=Propagation.REQUIRED)
public class OwnerOrderServiceImpl implements OwnerOrderService {
	@Autowired
	private OwnerOrderDAO ownerOrderDAO;
	
	// 결제내역 조회
	@Override
	public List<OrderVO> listOwnerOrders(Map condMap) throws Exception {
		return ownerOrderDAO.selectOwnerOrderList(condMap);
	}
	
	@Override
	public OrderVO ownerOrderDetail(int order_seq_num) throws Exception {
		return ownerOrderDAO.selectOwnerOrderDetail(order_seq_num);
	}
	
	@Override
	public RefundVO ownerOrderCancel(int order_seq_num) throws Exception {
		return ownerOrderDAO.selectOwnerOrderCancel(order_seq_num);
	}
	
	@Override
	public void ownerOrderRefund(int order_seq_num) throws Exception {
		ownerOrderDAO.refundOrder(order_seq_num);
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws Exception {
		return ownerOrderDAO.maxNumSelect(condMap);
	}

} 