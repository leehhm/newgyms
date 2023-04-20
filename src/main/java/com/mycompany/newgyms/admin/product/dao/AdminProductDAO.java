package com.mycompany.newgyms.admin.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.product.vo.ProductVO;

public interface AdminProductDAO {
	
	public List<ProductVO> selectAllProductList(Map condMap) throws DataAccessException;
	public String maxNumSelect(Map condMap) throws DataAccessException;
	public String productAccess(String product_id) throws DataAccessException;
	public String removeProduct(String product_id) throws DataAccessException;
}
