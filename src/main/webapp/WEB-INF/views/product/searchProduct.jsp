<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<%
     //치환 변수 선언합니다.
      pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
      pageContext.setAttribute("br", "<br/>"); //br 태그
%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>상품 목록 조회</title>
<link href="${contextPath}/resources/css/product.css?after" rel="stylesheet" type="text/css" media="screen">
</head>
<body>

<div class="con-min-width">
<div class="con">

  <div id="searchProduct">
 	 <h1>상품검색</h1>
 
		<form name="frmSearch" action="${contextPath}/product/searchProductByCondition.do" >
			<div class="searchByKeyword">
			    <h2>검색조건</h2>
				<select class="search_option" name="searchOption" id="searchOption"  >
					<option  value="all">선택</option>
					<option value="product_name">상품명</option>
			        <option value="center_name">시설 이름</option>
			 	</select>
			  	<input class="search_input" name="searchWord"  type="text"  onKeyUp="keywordSearch()" value="${searchWord }" placeholder="검색어를 입력하세요"> 
			</div>
		  <div class="searchByPrice">
		    <h2>판매가격</h2>
		    <input name="minPrice" type="number" value="${minPrice }"> ~ <input name="maxPrice" type="number" value="${maxPrice }">
		  </div>
		  
		 	<input class="search_button"name="search"  type="submit" value="검색">
		</form>
  </div>
	
  <div class="productList">
		<h2 id="total_count">총 ${fn:length(productList)}건</h2>
		<div class="container">
			<c:choose>
			   <c:when test="${ empty productList  }" >
					<h1 style="padding:50px 0;">검색조건에 해당하는 상품이 없습니다.</h1>
			   </c:when>
			   <c:otherwise>
				<c:forEach var="item" items="${productList }"> 
				   <c:set  var="product_total_count" value="${product_count+1 }" />
				   
			        <div class="item">
			        <!-- 상품이미지 -->
			          <div class="product_image">
			            <a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">
						   <img alt="" src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
						</a>
						<!-- 찜하기 -->
						<div class="wish" ></div>
							<a id="wish" href="${contextPath }/wish/addWishList.do?product_id=${item.product_id}"><img src="${contextPath}/resources/image/heart.png" alt="찜하기"></a>
			   		</div>
			   		
					<div class="product_description">
						<!-- 상품명 -->
			  		    <h2><a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">${item.product_name }</a></h2>
			            <h3><a href="${contextPath}/owner/main/ownerPageIntroView.do?member_id=${item.member_id}">${item.center_name }</a></h3><!-- 사업장관리 페이지로 이동 추가예정-->
					</div>
					
					<!-- 상품가격 -->
					<div class="product_price">
			            <div class="discount_rate"><fmt:formatNumber  value="${(item.product_price - item.product_sales_price)/item.product_price}" type="percent" var="discount_rate" />${discount_rate }</div>
			            <div class="sales_price"><fmt:formatNumber  value="${item.product_sales_price}" type="number"/>원</div>
			            <div class="price"><fmt:formatNumber  value="${item.product_price}" type="number"/>원</div>					
					</div>
				   </div>
				  </c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
  </div>
		
</div>
</div>
</body>
</html>