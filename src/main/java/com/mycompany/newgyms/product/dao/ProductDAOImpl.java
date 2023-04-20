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

	/* 카테고리별, 지역별 조회 */
	@Override
	public ArrayList selectProductList(Map listMap) throws DataAccessException {
		String product_sort = (String) listMap.get("product_sort");
		String address = (String) listMap.get("address");
		// 전체보기
		if (product_sort.equals("전체보기")) {
			ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectAllProductList", address);
			return list;
			// 카테고리별,지역별
		} else {
			ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectProductList", listMap);
			return list;
		}

	}
	
	/* 정렬하여 조회 - 신상품/인기순/낮은가격/높은가격 */
	public ArrayList selectSortedProduct(Map sortMap) throws DataAccessException {
		String product_sort = (String) sortMap.get("product_sort");
		String sortBy = (String) sortMap.get("sortBy");
		
		ArrayList list = new ArrayList();
		if (product_sort.equals("전체보기")) {
			if (sortBy.equals("popular")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByPopular", sortMap);
			} else if (sortBy.equals("lowPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByLowPrice", sortMap);
			} else if (sortBy.equals("highPrice")) {
				list = (ArrayList) sqlSession.selectList("mapper.product.selectAllproductSortByHighPrice", sortMap);
			}
			return list;
			// 카테고리별,지역별
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

	/* 상품검색 */
	@Override
	public ArrayList selectProductBySearchWord(String searchWord) throws DataAccessException {
		ArrayList list = (ArrayList) sqlSession.selectList("mapper.product.selectProductBySearchWord", searchWord);
		return list;
	}
	
	/* 상품 상세검색 */
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
	
	/* 상품 상세페이지 */	
	
	/* 옵션 */
	@Override
	public List<ProductOptVO> selectProductOptionList(int product_id) throws DataAccessException {
		List<ProductOptVO> productOptList = (ArrayList) sqlSession.selectList("mapper.product.selectProductOptionList", product_id);
		return productOptList;
	}
	
	/* 상품 상세정보, 프로그램 안내 */
	@Override
	public ProductVO selectProductDetail(int product_id) throws DataAccessException {
		ProductVO productVO = (ProductVO) sqlSession.selectOne("mapper.product.selectProductDetail", product_id);
		return productVO;
	}

	/* 프로그램 안내 이미지 */
	@Override
	public Map<String, Object> selectProductImage(int product_id) throws DataAccessException {
		/* 프로그램 상세정보 */
		List<ProductImageVO> detailImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductDetailImage", product_id);
		/* 가격 정보 */
		List<ProductImageVO> priceImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductPriceImage", product_id);
		/* 시설 정보 */
		List<ProductImageVO> facilityImageList = (ArrayList) sqlSession.selectList("mapper.product.selectProductFacilityImage", product_id);
		
		Map imageMap = new HashMap();
		imageMap.put("detailImageList", detailImageList);
		imageMap.put("priceImageList", priceImageList);
		imageMap.put("facilityImageList", facilityImageList);

		return imageMap;
	}

}