package com.mycompany.newgyms.owner.product.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;

public interface OwnerProductDAO {
	public List<ProductVO> selectOwnerProductList(Map condMap) throws DataAccessException;
	public String maxNumSelect(Map condMap) throws DataAccessException;
	public int insertNewProduct(Map newProductMap) throws DataAccessException;
	public void insertProductOption(List<ProductOptVO> optionList)  throws DataAccessException;
	public void insertProductImage(List fileList)  throws DataAccessException;
	
	public void updateProduct(Map productMap) throws DataAccessException;
	public void deleteProducImage(String[] delImageIdList) throws DataAccessException;
	public String deleteProduct(int product_id) throws DataAccessException;
}
