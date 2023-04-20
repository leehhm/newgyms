package com.mycompany.newgyms.wish.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.wish.vo.WishVO;


public interface WishDAO {
	public List<WishVO> selectWishList(WishVO wishVO) throws DataAccessException;
	public List<ProductVO> selectProductList(List<WishVO> wishList) throws DataAccessException;
	public void insertWishList(WishVO wishVO) throws Exception;
	public boolean selectCountInWish(WishVO wishVO) throws DataAccessException;
	
	public int selectWishId(WishVO wishVO) throws Exception;
	
	public void deleteEachWishProduct(int wish_id) throws DataAccessException;
	public void deleteWishProduct(Map wishMap) throws DataAccessException;
}
