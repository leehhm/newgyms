package com.mycompany.newgyms.owner.product.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.product.vo.ProductImageVO;
import com.mycompany.newgyms.product.vo.ProductOptVO;

@Repository("ownerProductDAO")
public class OwnerProductDAOImpl implements OwnerProductDAO {
	@Autowired
	private SqlSession sqlSession;
	
	
	// 상품 목록
	@Override
	public List selectOwnerProductList(Map condMap) throws DataAccessException {
		List ownerProductList = (List) sqlSession.selectList("mapper.owner_product.selectOwnerProductList", condMap);
		return ownerProductList;
	}
	
	@Override
	public String maxNumSelect(Map condMap) throws DataAccessException {
		String result = sqlSession.selectOne("mapper.owner_product.maxNumSelect", condMap);
		return result;
	}
	
	private int selectProductID() throws DataAccessException{
		int product_id = sqlSession.selectOne("mapper.owner_product.selectProductID");
		return product_id;
		
	}
	
	@Override
	public int insertNewProduct(Map newProductMap) throws DataAccessException {
		int product_id = selectProductID();
		newProductMap.put("product_id", product_id);
		sqlSession.insert("mapper.owner_product.insertNewProduct",newProductMap);
		return product_id;
	}
	
	//상품수정
	@Override
	public void updateProduct(Map productMap) throws DataAccessException {
		sqlSession.update("mapper.owner_product.updateProduct", productMap);
	}
	
	
	@Override
	public void insertProductOption(List<ProductOptVO> optionList)  throws DataAccessException {
		
		for(int i=0; i<optionList.size();i++){
			ProductOptVO productOptVO =(ProductOptVO)optionList.get(i);
			sqlSession.insert("mapper.owner_product.insertNewProductOption",productOptVO);
		}
	}
	
	@Override
	public void insertProductImage(List imageList)  throws DataAccessException {
		for(int i=0; i<imageList.size();i++){
			ProductImageVO productImageVO=(ProductImageVO)imageList.get(i);
			sqlSession.insert("mapper.owner_product.insertProductImage",productImageVO);
		}
	}
	
	@Override
	public void deleteProducImage(String[] delImageIdList) throws DataAccessException{
		sqlSession.delete("mapper.owner_product.deleteProducImage",delImageIdList);
	}
	
	//상품 삭제
	@Override
	public String deleteProduct(int product_id) throws DataAccessException {
		sqlSession.delete("mapper.owner_product.deleteProduct", product_id);
		String result = "true";
		return result;
	}

}
