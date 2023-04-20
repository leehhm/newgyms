package com.mycompany.newgyms.admin.product.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.product.vo.ProductVO;

public interface AdminProductService {
	public List<ProductVO> adminProductList(Map condMap) throws Exception;
	public String maxNumSelect(Map condMap) throws Exception;

	public String adminProductAccess(String product_id) throws Exception;
	public String adminRemoveProduct(String product_id) throws Exception;
}
