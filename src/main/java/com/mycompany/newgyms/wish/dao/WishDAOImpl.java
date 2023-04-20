package com.mycompany.newgyms.wish.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.wish.vo.WishVO;
import com.mycompany.newgyms.product.vo.ProductVO;
import com.mycompany.newgyms.wish.vo.WishVO;

@Repository("wishDAO")
public class WishDAOImpl implements WishDAO {
	@Autowired
	private SqlSession sqlSession;

	
	/*찜 목록*/
	@Override
	public List<WishVO> selectWishList(WishVO wishVO) throws DataAccessException {
		List<WishVO> wishList =(List)sqlSession.selectList("mapper.wish.selectWishList",wishVO);
		return wishList;
	}
	
	//찜 목록 상품정보 가져오기
	public List<ProductVO> selectProductList(List<WishVO> wishList) throws DataAccessException {
		List<ProductVO> myProductList;
		myProductList = sqlSession.selectList("mapper.wish.selectProductList",wishList);
		return myProductList;
	}


	@Override
	public void insertWishList(WishVO wishVO) throws Exception {
		sqlSession.insert("mapper.wish.insertWishList", wishVO);
	}
	
	@Override
	public boolean selectCountInWish(WishVO wishVO) throws DataAccessException {
		String  result =sqlSession.selectOne("mapper.wish.selectCountInWish",wishVO);
		return Boolean.parseBoolean(result);
	}
	
	@Override
	public int selectWishId(WishVO wishVO) throws Exception {
		int wish_id = sqlSession.selectOne("mapper.wish.selectWiShId",wishVO);
		return wish_id;
	}
	
	
	@Override
	public void deleteEachWishProduct(int wish_id) throws DataAccessException{
		sqlSession.delete("mapper.wish.deleteEachWishProduct",wish_id);
	}
	
	@Override
	public void deleteWishProduct(Map wishMap) throws DataAccessException {
		sqlSession.delete("mapper.wish.deleteWishProduct",wishMap);
	}
}
