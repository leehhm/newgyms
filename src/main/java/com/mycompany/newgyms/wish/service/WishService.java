package com.mycompany.newgyms.wish.service;

import java.util.List;
import java.util.Map;

import com.mycompany.newgyms.wish.vo.WishVO;

public interface WishService {
	public Map<String ,List> myWishList(WishVO wishVO) throws Exception;
	public void addWishList(WishVO wishVO) throws Exception;
	public boolean findWishProduct(WishVO wishVO) throws Exception;
	
	public int selectWishId(WishVO wishVO) throws Exception;
	
	public void removeEachWishProduct(int wish_id) throws Exception;
	public void removeWishProduct(Map wishMap) throws Exception;
}
