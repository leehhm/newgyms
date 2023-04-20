package com.mycompany.newgyms.admin.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.product.vo.ProductVO;

@Repository("adminProductDAO")
public class AdminProductDAOImpl implements AdminProductDAO {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<ProductVO> selectAllProductList(Map condMap) throws DataAccessException {
		List adminProductList = (List) sqlSession.selectList("mapper.admin_product.selectAllProductList", condMap);
		return adminProductList;
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.admin_product.maxNumSelect", condMap);
		return result;
	}
	
	@Override
	public String productAccess(String product_id) throws DataAccessException {
		sqlSession.update("mapper.admin_product.productAccess", product_id);
		String result = "true";
		return result;
	}
	
	@Override
	public String removeProduct(String product_id) throws DataAccessException {
		sqlSession.delete("mapper.admin_product.removeProduct", product_id);
		String result = "true";
		return result;
	}
}
