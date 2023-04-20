package com.mycompany.newgyms.owner.product.service;

import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;

import java.util.List;
import java.util.Map;

public interface OwnerProductService {
	public List<ProductVO> ownerProductList(Map condMap) throws Exception;
	public String maxNumSelect(Map condMap) throws Exception;

	public int addNewProduct(Map newProductMap) throws Exception;
	public void addNewProductOption(List<ProductOptVO> optionList) throws Exception;
	public void modifyProduct(Map productMap) throws Exception;
	public void removeProductImage(String[] delImageIdList) throws Exception;
	public String removeProduct(int product_id) throws Exception;
}
