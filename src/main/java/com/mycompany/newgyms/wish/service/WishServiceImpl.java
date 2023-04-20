package com.mycompany.newgyms.wish.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mycompany.newgyms.wish.vo.WishVO;
import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.wish.dao.WishDAO;
import com.mycompany.newgyms.wish.vo.WishVO;

@Service("wishService")
@Transactional(propagation=Propagation.REQUIRED)
public class WishServiceImpl implements WishService{
	
	private static final String String = null;
	@Autowired
	private WishDAO wishDAO;
	
	/*찜 목록*/
	@Override
	public Map<String ,List> myWishList(WishVO wishVO) throws Exception{
		Map<String,List> wishMap=new HashMap<String,List>();
		
		List<WishVO> myWishList=wishDAO.selectWishList(wishVO);
		if(myWishList.size()==0){ 
			System.out.println("장바구니가 비어있습니다.");
			return null;
		}
		
		//장바구니 상품 정보 가져오기
		List<ProductVO> myProductList=wishDAO.selectProductList(myWishList);
		wishMap.put("myWishList", myWishList);
		wishMap.put("myProductList",myProductList);
		return wishMap;
	}
	
	@Override
	public void addWishList(WishVO wishVO) throws Exception{
		wishDAO.insertWishList(wishVO);
	}
	
	@Override
	public boolean findWishProduct(WishVO wishVO) throws Exception{
		return wishDAO.selectCountInWish(wishVO);
	}	
	
	public int selectWishId(WishVO wishVO) throws Exception {
		int wish_id = wishDAO.selectWishId(wishVO);
		return wish_id;
	}
	
	@Override
	public void removeEachWishProduct(int wish_id) throws Exception{
		wishDAO.deleteEachWishProduct(wish_id);
	}
	
	@Override
	public void removeWishProduct(Map wishMap) throws Exception{
		wishDAO.deleteWishProduct(wishMap);
	}

}
