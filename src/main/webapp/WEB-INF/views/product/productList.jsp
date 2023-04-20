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
<script>
function add_wishList(product_id) {
    $.ajax({
	       type : "post",
	       async : false,
	       url : "${contextPath}/wish/addWishList.do",
	       data : {
	          product_id:product_id
	       },
	       success : function(data, textStatus) {
	    	   if(data.trim()=='add_success'){
		       		var con_wish = confirm("찜 목록에 추가되었습니다. 찜 목록으로 이동할까요?"); 
		       		if(con_wish==true) {
		       			location.href="${contextPath}/wish/myWishList.do";
		       		} 
	          }else if(data.trim()=='already_existed'){
	             alert("이미 찜 목록에 추가된 상품입니다. :) ");   
   
	          }else if(data.trim()=='add_failed'){
	             alert("로그인이 필요합니다. :) ");   
	          }
	          
	       },
	       error : function(data, textStatus) {
	          alert("에러가 발생했습니다."+data);
	       },
	       complete : function(data, textStatus) {
	          //alert("작업을완료 했습니다");
	       }
	    }); //end ajax   
}

</script>
</head>
<body>
<div class="con-min-width">
<div class="con">
	<div class="event_img"><img src="${contextPath}/resources/image/event.png" alt="이벤트이미지"></div> 
	
	<!-- 지역별 조회 메뉴 -->
	<div class="address_menu">
	    <ul>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=대전">전체</a></li>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=유성구">유성구</a></li>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=대덕구">대덕구</a></li>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=서구">서구</a></li>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=중구">중구</a></li>
	         <li><a href="${contextPath}/product/productList.do?category=${productSort}&address=동구">동구</a></li>
	     </ul>
	</div>

  <div class="productList">

   <c:set  var="product_count" value="0" />   
	<h1 id="current_menu">${productSort} > ${address }</h1>
	<h2 id="total_count">총 ${fn:length(productList)}건</h2>
		
		<div id="sorting">
			<ul>
				<li><a href="${contextPath}/product/productList.do?category=${productSort}&address=${address}">신상품순</a></li>
				<li><a href="${contextPath}/product/productSorting.do?category=${productSort}&address=${address}&sortBy=popular">인기순</a></li>
				<li><a href="${contextPath}/product/productSorting.do?category=${productSort}&address=${address}&sortBy=lowPrice">낮은가격순</a></li>
				<li><a style="border: currentColor; border-image: none;" href="${contextPath}/product/productSorting.do?category=${productSort}&address=${address}&sortBy=highPrice">높은가격</a></li>
			</ul>
		</div>
	
	<div class="container">
		<c:choose>
		   <c:when test="${ empty productList  }" >
				<h1 style="padding:50px 0;">등록된 상품이 없습니다.</h1>
		   </c:when>
		   <c:otherwise>
			<c:forEach var="item" items="${productList }"> 
			   <c:set var="product_total_count" value="${product_count+1 }" />
			   
		       	 <div class="item">
		       	 
			          <div class="product_image">
			            <a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">
						   <img alt="" src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
						</a>
						<div class="wish" ></div>
							<a id="wish" href="javascript:add_wishList('${item.product_id}');"><img src="${contextPath}/resources/image/heart.png" alt="찜하기"></a>
			   		 </div>
			   		 
					<div class="product_description">
			  		    <h2><a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">${item.product_name }</a></h2>
			            <h3><a href="${contextPath}/owner/main/ownerPageIntroView.do?member_id=${item.member_id}">${item.center_name}</a></h3> <!-- 사업장관리 페이지로 이동 -->
					</div>
					
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