package com.mycompany.newgyms.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.product.dao.ProductDAO;
import com.mycompany.newgyms.product.vo.ProductImageVO;
import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.review.vo.ReviewVO;

@Service("productService")
@Transactional(propagation = Propagation.REQUIRED)
public class ProductServiceImpl implements ProductService {

	private static final String String = null;
	@Autowired
	private ProductDAO productDAO;
	
	/* ī�װ���, ������ ��ȸ */
	public List<ProductVO> productList(Map listMap) throws Exception {
		List productList = productDAO.selectProductList(listMap);
		return productList;
	}

	/* �����Ͽ� ��ȸ - �Ż�ǰ/�α��/��������/�������� */
	public List<ProductVO> productSorting(Map sortMap) throws Exception {
		List productList = productDAO.selectSortedProduct(sortMap);
		return productList;
	}
	
	/* ��ǰ�˻� */
	public List<ProductVO> searchProduct(String searchWord) throws Exception {
		List productList = productDAO.selectProductBySearchWord(searchWord);
		return productList;
	}
	
	/* ��ǰ �󼼰˻� */
	public List<ProductVO> searchProductByCondition(Map searchMap) throws Exception {
		List productList = productDAO.searchProductByCondition(searchMap);
		return productList;
	}
	
	/* ��ǰ �������� */	
	
	/* �ɼ� */
	public List<ProductOptVO> selectProductOptionList(int product_id) throws Exception {
		List<ProductOptVO> productOptList = productDAO.selectProductOptionList(product_id);
		return productOptList;
	}
	
	/* ��ǰ ������, ���α׷� �ȳ� */
	public ProductVO productDetail(int product_id) throws Exception {
		ProductVO productVO = productDAO.selectProductDetail(product_id);
		return productVO;
	}

	/* ���α׷� �ȳ� �̹��� */
	public Map productImage(int product_id) throws Exception {
		Map imageMap = productDAO.selectProductImage(product_id);
		return imageMap;
	}


}