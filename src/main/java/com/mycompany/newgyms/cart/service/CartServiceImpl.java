package com.mycompany.newgyms.cart.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.cart.dao.CartDAO;
import com.mycompany.newgyms.cart.vo.CartVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Service("cartService")
@Transactional(propagation=Propagation.REQUIRED)
public class CartServiceImpl  implements CartService{
	@Autowired
	private CartDAO cartDAO;
		
	/*��ٱ��� ���*/
	public Map<String ,List> myCartList(String member_id) throws Exception{
		
		Map<String,List> cartMap=new HashMap<String,List>();
		
		//��ٱ��� ��� ��������
		List<CartVO> myCartList=cartDAO.selectCartList(member_id);
		cartMap.put("myCartList", myCartList);
		
		if(myCartList.size()==0){ 
			return null;
		}
		
		//��ٱ��� ��ǰ ���� ��������
		List<ProductVO> myProductList=cartDAO.selectProductList(myCartList);
		cartMap.put("myProductList",myProductList);
		
		return cartMap;
	}
	
	/* ��ٱ��� �߰� */
	public boolean findCartProduct(CartVO cartVO) throws Exception{
		return cartDAO.selectCountInCart(cartVO);
	}	
	public void addProductInCart(CartVO cartVO) throws Exception{
		cartDAO.insertProductInCart(cartVO);
	}
	
	/*��ٱ��� �ɼ� ����*/	
	public boolean modifyCartOption(CartVO cartVO) throws Exception{
		cartDAO.updateCartProductOption(cartVO);
		boolean result=true;
		return result;
	}

	/* ��ٱ��� ���� */
	public void removeEachCartProduct(int cart_id) throws Exception{
		cartDAO.deleteEachCartProduct(cart_id);
	}
	public void removeSelectCartProduct(Map cartMap) throws Exception{
		cartDAO.deleteSelectCartProduct(cartMap);
	}
	
}
