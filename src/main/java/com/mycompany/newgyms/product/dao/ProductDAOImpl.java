package com.mycompany.newgyms.product.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.mycompany.newgyms.member.vo.MemberVO;
import com.mycompany.newgyms.product.vo.ProductImageVO;
import com.mycompany.newgyms.product.vo.ProductOptVO;
import com.mycompany.newgyms.product.vo.ProductVO;

@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SqlSession sqlSession;

	/* ī�װ���, ������ ��ȸ */
	@Override
	public ArrayList selectProductList(Map listMap) throws DataAccessException {
		String product_sort = (String) listMap.get("product_sort");
		String address = (String) listMap.get("address");
		// ��ü����
		if (product_sort.equals("��ü����")) {
			ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectAllProductList", address);
			return list;
			// ī�װ���,������
		} else {
			ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectProductList", listMap);
			return list;
		}

	}
	
	/* �����Ͽ� ��ȸ - �Ż�ǰ/�α��/��������/�������� */
	public ArrayList selectSortedProduct(Map sortMap) throws DataAccessException {
		String product_sort = (String) sortMap.get("product_sort");
		String sortBy = (String) sortMap.get("sortBy");
		
		ArrayList list = new ArrayList();
		if (product_sort.equals("��ü����")) {
			if (sortBy.equals("popular")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByPopular", sortMap);
			} else if (sortBy.equals("lowPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByLowPrice", sortMap);
			} else if (sortBy.equals("highPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByHighPrice", sortMap);
			}
			return list;
			// ī�װ���,������
		} else {
			if (sortBy.equals("popular")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectproductSortByPopular", sortMap);
			} else if (sortBy.equals("lowPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectproductSortByLowPrice", sortMap);
			} else if (sortBy.equals("highPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectproductSortByHighPrice", sortMap);
			}
			return list;
		}
		
		
		
	}

	/* ��ǰ�˻� */
	@Override
	public ArrayList selectProductBySearchWord(String searchWord) throws DataAccessException {
		ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectProductBySearchWord", searchWord);
		return list;
	}
	
	/* ��ǰ �󼼰˻� */
	@Override
	public ArrayList searchProductByCondition(Map searchMap) throws DataAccessException {
		String searchOption = (String) searchMap.get("searchOption");

		ArrayList list = new ArrayList();
		if (searchOption.equals("all")) {
			list = (ArrayList) sqlSession.selectList("mapper.product.selectProductByAll", searchMap);
		} else if (searchOption.equals("product_name")) {
			list = (ArrayList) sqlSession.selectList("mapper.product.selectProductByProductName", searchMap);

		} else if (searchOption.equals("center_name")) {
			list = (ArrayList) sqlSession.selectList("mapper.product.selectProductByCenterName", searchMap);
		}
		return list;
	}
	
	/* ��ǰ �������� */	
	
	/* �ɼ� */
	@Override
	public List<ProductOptVO> selectProductOptionList(int product_id) throws DataAccessException {
		List<ProductOptVO> productOptList = (ArrayList) sqlSession.selectList("mapper.product.selectProductOptionList", product_id);
		return productOptList;
	}
	
	/* ��ǰ ������, ���α׷� �ȳ� */
	@Override
	public ProductVO selectProductDetail(int product_id) throws DataAccessException {
		ProductVO productVO = (ProductVO) sqlSession.selectOne("mapper.product.selectProductDetail", product_id);
		return productVO;
	}

	/* ���α׷� �ȳ� �̹��� */
	@Override
	public Map<String, Object> selectProductImage(int product_id) throws DataAccessException {
		/* ���α׷� ������ */
		List<ProductImageVO> detailImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductDetailImage", product_id);
		/* ���� ���� */
		List<ProductImageVO> priceImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductPriceImage", product_id);
		/* �ü� ���� */
		List<ProductImageVO> facilityImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductFacilityImage", product_id);
		
		Map imageMap = new HashMap();
		imageMap.put("detailImageList", detailImageList);
		imageMap.put("priceImageList", priceImageList);
		imageMap.put("facilityImageList", facilityImageList);

		return imageMap;
	}

}