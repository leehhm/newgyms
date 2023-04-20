<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	// 상품 삭제하기
	function fn_remove_product(product_id) {
		if(confirm("선택한 상품을 삭제 하시겠습니까?")){
		    $.ajax({
		       type : "post",
		       async : false,
		       url : "${contextPath}/owner/product/removeProduct.do",
		       data : {
		          product_id:product_id
		       },
		       success : function(data, textStatus) {
		       	  if(data.trim()=='success'){
		       		 alert("상품이 삭제되었습니다."); 
					 location.reload();
		          }else {
		             alert("오류가 발생했습니다.");   
		          }
		       },
		       error : function(data, textStatus) {
		          alert("오류가 발생했습니다."+data);
		       },
		       complete : function(data, textStatus) {
		          //alert("작업을완료 했습니다");
		       }
		    }); //end ajax   
		}
	}

</script>
</head>
<body>
		<input type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 사업자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
					<div id="contain_right">
						<p id="mypage_order_title">상품 관리</p>
						<p style="font-size:15px;">총 ${maxnum}건</p>
						<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>

						<c:choose>
							<c:when test="${empty ownerProductList}">
								<table id="order_detail2" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="13%">등록일</th>
										<th width="25%" colspan="2">상품정보</th>
										<th width="12%">정가</th>
										<th width="12%">할인가</th>
										<th width="8%">판매상태</th>
										<th width="10%">선택</th>
									</tr>
									<tr>
										<td colspan="8" style="color: blue;">조회된 상품 내역이 없습니다. 😂</td>
									</tr>
								</table>

							</c:when>
							<c:otherwise>
								<table id="order_detail2" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="13%">등록일</th>
										<th width="25%" colspan="2">상품정보</th>
										<th width="12%">정가</th>
										<th width="12%">할인가</th>
										<th width="8%">판매상태</th>
										<th width="10%">선택</th>
									</tr>

									<!-- 상품 리스트 -->
									<c:forEach var="list" items="${ownerProductList}">
										<c:set var="i" value="${i+1}" />
										<tbody>
											<tr>
												<!-- 번호 -->
												<td>${list.product_id}</td>
												
												<!-- 등록일 -->
												<td style="font-size: 14px;">${list.product_entered_date}</td>
												
												<!-- 대표이미지 -->
												<td align=center>
													<div id="img_file">
														<img alt="img" width="100%" height="100%"
															src="${contextPath}/download.do?product_id=${list.product_id}&fileName=${list.product_main_image}">
													</div>
												</td>
												
												<!-- 상품정보 -->
												<td style="text-align: left; line-height: 25px; font-size: 18px;">
													<a href="${contextPath}/product/productDetail.do?product_id=${list.product_id}" style="line-height: 32px;">${list.product_name}</a>
												</td>
												
												<!-- 정가 -->
												<td>
													<fmt:formatNumber value="${list.product_price}" type="number" />원
												</td>
												
												<!-- 할인가 -->
												<td>
													<fmt:formatNumber value="${list.product_sales_price}" type="number" />원
												</td>
														
												<!-- 상품 상태 -->
												<td>
													${list.product_state}
												</td>
														
												<!-- 수정 및 삭제 버튼 -->
												<td>
													<a id="owner_modify_btn" href="${contextPath}/owner/product/ProductModifyForm.do?product_id=${list.product_id}">수정하기</a><br>
													<a id="owner_delete_btn" href="javascript:fn_remove_product('${list.product_id}');">삭제하기</a>
<%-- 													<a id="owner_delete_btn" href="${contextPath}/owner/product/removeProduct.do?product_id=${list.product_id}">삭제하기</a> --%>
												</td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
						
						<div align=right style="margin-top: 10px;">
								<a href="${contextPath}/owner/product/addProductForm.do" style="line-height: 32px;"> <span id="btn_1">상품등록</span></a>
						</div>
					</div>
				</div>
				<div style="text-align: center; margin-left:240px;">
					<a
						href="${contextPath}/owner/product/ownerProductList.do?member_id=${memberInfo.member_id}&chapter=${chapter-1}"> &#60;</a>
					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/5)}" step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a href="${contextPath}/owner/product/ownerProductList.do?member_id=${memberInfo.member_id}&chapter=${section_num}">${section_num}</a>
					</c:forEach>
					<a
						href="${contextPath}/owner/product/ownerProductList.do?member_id=${memberInfo.member_id}&chapter=${chapter+1}"> &#62;</a>

				</div>
			</div>
		</div>
</body>
</html>