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
		
	/*장바구니 목록*/
	public Map<String ,List> myCartList(String member_id) throws Exception{
		
		Map<String,List> cartMap=new HashMap<String,List>();
		
		//장바구니 목록 가져오기
		List<CartVO> myCartList=cartDAO.selectCartList(member_id);
		cartMap.put("myCartList", myCartList);
		
		if(myCartList.size()==0){ 
			return null;
		}
		
		//장바구니 상품 정보 가져오기
		List<ProductVO> myProductList=cartDAO.selectProductList(myCartList);
		cartMap.put("myProductList",myProductList);
		
		return cartMap;
	}
	
	/* 장바구니 추가 */
	public boolean findCartProduct(CartVO cartVO) throws Exception{
		return cartDAO.selectCountInCart(cartVO);
	}	
	public void addProductInCart(CartVO cartVO) throws Exception{
		cartDAO.insertProductInCart(cartVO);
	}
	
	/*장바구니 옵션 변경*/	
	public boolean modifyCartOption(CartVO cartVO) throws Exception{
		cartDAO.updateCartProductOption(cartVO);
		boolean result=true;
		return result;
	}

	/* 장바구니 삭제 */
	public void removeEachCartProduct(int cart_id) throws Exception{
		cartDAO.deleteEachCartProduct(cart_id);
	}
	public void removeSelectCartProduct(Map cartMap) throws Exception{
		cartDAO.deleteSelectCartProduct(cartMap);
	}
	
}
