package com.mycompany.newgyms.cart.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.cart.vo.CartVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Repository("cartDAO")
public class CartDAOImpl  implements  CartDAO{
	@Autowired
	private SqlSession sqlSession;
	
	/*��ٱ��� ���*/
	public List<CartVO> selectCartList(String member_id) throws DataAccessException {
		List<CartVO> myCartList =(List)sqlSession.selectList("mapper.cart.selectCartList",member_id);
		return myCartList;
	}
	
	//��ٱ��� ��ǰ ������ ��������
	public List<ProductVO> selectProductList(List<CartVO> myCartList) throws DataAccessException {
		List<ProductVO> myProductList = sqlSession.selectList("mapper.cart.selectProductList",myCartList);
		return myProductList;
	}
	
	/* ��ٱ��� �߰� */
	public boolean selectCountInCart(CartVO cartVO) throws DataAccessException {
		String  result =sqlSession.selectOne("mapper.cart.selectCountInCart",cartVO);
		return Boolean.parseBoolean(result);
	}
	
	public void insertProductInCart(CartVO cartVO) throws DataAccessException{
		int cart_id=selectMaxCartId();
		cartVO.setCart_id(cart_id);
		sqlSession.insert("mapper.cart.insertProductInCart",cartVO);
	}
	
	private int selectMaxCartId() throws DataAccessException{
		int cart_id =sqlSession.selectOne("mapper.cart.selectMaxCartId");
		return cart_id;
	}
	
	/*��ٱ��� �ɼ� ����*/
	public void updateCartProductOption(CartVO cartVO) throws DataAccessException{
		sqlSession.insert("mapper.cart.updateCartProductOption",cartVO);
	}
	
	/* ��ٱ��� ���� */
	public void deleteEachCartProduct(int cart_id) throws DataAccessException{
		sqlSession.delete("mapper.cart.deleteEachCartProduct",cart_id);
	}
	public void deleteSelectCartProduct(Map cartMap) throws DataAccessException {
		sqlSession.delete("mapper.cart.deleteSelectCartProduct",cartMap);
	}

}
