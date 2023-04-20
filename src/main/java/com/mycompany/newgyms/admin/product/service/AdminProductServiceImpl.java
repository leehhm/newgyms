package com.mycompany.newgyms.admin.product.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.admin.product.dao.AdminProductDAO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Service("adminProductService")
@Transactional(propagation = Propagation.REQUIRED)
public class AdminProductServiceImpl implements AdminProductService {
	@Autowired
	private AdminProductDAO adminProductDAO;
	
	public List<ProductVO> adminProductList(Map condMap) throws Exception {
		List adminProductList = adminProductDAO.selectAllProductList(condMap);
		return adminProductList;
	} 
	
	@Override
	public String maxNumSelect(Map condMap) throws Exception {
		return adminProductDAO.maxNumSelect(condMap);
	}
	
	@Override
	public String adminProductAccess(String product_id) throws Exception {
		return adminProductDAO.productAccess(product_id);
	}
	
	@Override
	public String adminRemoveProduct(String product_id) throws Exception {
		return adminProductDAO.removeProduct(product_id);
	}
}
