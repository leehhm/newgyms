package com.mycompany.newgyms.owner.product.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mycompany.newgyms.owner.product.dao.OwnerProductDAO;
import com.mycompany.newgyms.product.vo.ProductImageVO;
import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Service("ownerProductService")
@Transactional(propagation = Propagation.REQUIRED)
public class OwnerProductServiceImpl implements OwnerProductService {
	@Autowired
	private OwnerProductDAO ownerProductDAO;
	
	@Override
	public List<ProductVO> ownerProductList(Map condMap) throws Exception {
		List ownerProductList = ownerProductDAO.selectOwnerProductList(condMap);
		return ownerProductList;
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws Exception {
		return ownerProductDAO.maxNumSelect(condMap);
	}
	
	@Override
	public int addNewProduct(Map newProductMap) throws Exception{
		int product_id = ownerProductDAO.insertNewProduct(newProductMap);
		
		/* 옵션 */
		ArrayList<ProductOptVO> optionList = (ArrayList)newProductMap.get("optionList");
		for(ProductOptVO productOptVO : optionList) {
			productOptVO.setProduct_id(product_id);
		}
		ownerProductDAO.insertProductOption(optionList);
		
		/* 이미지 */
		ArrayList<ProductImageVO> imageList = (ArrayList)newProductMap.get("imageList");
		for(ProductImageVO productImageVO : imageList) {
			productImageVO.setProduct_id(product_id);
		}
		ownerProductDAO.insertProductImage(imageList);
		
		return product_id;
	}
	
	@Override
	public void addNewProductOption(List<ProductOptVO> optionList) throws Exception{
		ownerProductDAO.insertProductOption(optionList);
	}
	
	@Override
	public void modifyProduct(Map productMap) throws Exception {
		ownerProductDAO.updateProduct(productMap);
		
		int product_id = Integer.parseInt((String)productMap.get("product_id"));
		
		/* 옵션 */
		ArrayList<ProductOptVO> optionList = (ArrayList)productMap.get("optionList");
		if(optionList!= null && optionList.size()!=0) {
			for(ProductOptVO productOptVO : optionList) {
				productOptVO.setProduct_id(product_id);
			}
			ownerProductDAO.insertProductOption(optionList);
		}
		
		/* 이미지 */
		ArrayList<ProductImageVO> imageList = (ArrayList)productMap.get("imageList");
		if(imageList!= null && imageList.size()!=0) {
			for(ProductImageVO productImageVO : imageList) {
				productImageVO.setProduct_id(product_id);
			}
		ownerProductDAO.insertProductImage(imageList);
		}
	}

	//상품 이미지 삭제하기
	@Override
	public void removeProductImage(String[] delImageIdList) throws Exception{
		ownerProductDAO.deleteProducImage(delImageIdList);
	}
	
    // 상품 삭제하기
	@Override
	public String removeProduct(int product_id) throws Exception {
		return ownerProductDAO.deleteProduct(product_id);
	}

}
