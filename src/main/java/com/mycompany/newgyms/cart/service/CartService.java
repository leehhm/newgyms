package com.mycompany.newgyms.cart.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.cart.vo.CartVO;

public interface CartService {
	public Map<String ,List> myCartList(String member_id) throws Exception;
	public boolean findCartProduct(CartVO cartVO) throws Exception;
	public void addProductInCart(CartVO cartVO) throws Exception;
	public boolean modifyCartOption(CartVO cartVO) throws Exception;
	public void removeEachCartProduct(int cart_id) throws Exception;
	public void removeSelectCartProduct(Map cartMap) throws Exception;
}
