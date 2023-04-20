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
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

/* 상품 승인하기 */
function fn_access_product(product_id) {
	
    $.ajax({
       type : "post",
       async : false,
       url : "${contextPath}/admin/product/adminProductAccess.do",
       data : {
          product_id:product_id
       },
       success : function(data, textStatus) {
       	  if(data.trim()=='success'){
       		 alert("상품 승인이 완료되었습니다."); 
			 location.reload();
          } else {
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
 
function fn_remove_product(product_id) {
	
    $.ajax({
       type : "post",
       async : false,
       url : "${contextPath}/admin/product/adminRemoveProduct.do",
       data : {
          product_id:product_id
       },
       success : function(data, textStatus) {
       	  if(data.trim()=='success'){
       		 alert("상품 삭제가 완료되었습니다."); 
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
</script>
</head>
<body>
	<form action="${contextPath}/admin/adminProductList.do" method="get">
		<input type="hidden" name="member_id" value="${member_id}"> <input
			type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 사업자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />
					<div id="contain_right">
						<p id="admin_product_title">상품 관리</p>
						<p style="font-size:15px;">총 ${maxnum}건</p>
						<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>

						<c:choose>
							<c:when test="${empty adminProductList}">
								<table id="product_detail" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="10%">등록일</th>
										<th width="25%" colspan="2">상품정보</th>
										<th width="15%">아이디</th>
										<th width="12%">가격</th>
										<th width="8%">판매상태</th>
										<th width="10%">선택</th>
									</tr>
									<tr>
										<td colspan="8" style="color: blue;">조회된 상품 내역이 없습니다. 😂</td>
									</tr>
								</table>

							</c:when>
							<c:otherwise>
								<table id="product_detail" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="10%">등록일</th>
										<th width="25%" colspan="2">상품정보</th>
										<th width="15%">아이디</th>
										<th width="12%">가격</th>
										<th width="8%">판매상태</th>
										<th width="10%">선택</th>
									</tr>

									<!-- 상품 리스트 -->
									<c:forEach var="item" items="${adminProductList}">
										<c:set var="i" value="${i+1}" />
										<tbody>
											<tr>
												<!-- 번호 -->
												<td>${item.product_id}</td>
												
												<!-- 등록일 -->
												<td style="font-size: 14px;">${item.product_entered_date}</td>
												
												<!-- 대표이미지 -->
												<td align=center>
													<div id="img_file">
														<img alt="img" width="100%" height="100%"
															src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
													</div>
												</td>
												
												<!-- 상품정보 -->
												<td style="text-align: left; line-height: 25px; font-size: 18px;">
													<a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}" style="line-height: 32px;">${item.product_name}</a>
												</td>
												
												<!-- 아이디 -->
												<td>
													${item.member_id}
												</td>
												
												<!-- 가격 -->
												<td>
													<s style="color:red;"><fmt:formatNumber value="${item.product_price}" type="number" />원</s><br>
													<fmt:formatNumber value="${item.product_sales_price}" type="number" />원
												</td>
														
												<!-- 상품 상태 -->
												<td>
													${item.product_state}
												</td>
														
												<!-- 수정 및 삭제 버튼 -->
												<td>
													<c:if test="${item.product_state == '승인대기'}">
														<a href="javascript:fn_access_product('${item.product_id}');" id="admin_modify_btn" style="margin:5px;">&nbsp;&nbsp;상품승인&nbsp;&nbsp;</a>
													</c:if>
													<a href="javascript:fn_remove_product('${item.product_id}');" id="admin_delete_btn" style="margin:5px;">&nbsp;&nbsp;상품삭제&nbsp;&nbsp;</a>
												</td>
											</tr>
										</tbody>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div style="text-align: center; margin-left:240px;">
					<a
						href="${contextPath}/admin/product/adminProductList.do?chapter=${chapter-1}"> &#60;</a>
					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/5)}" step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a href="${contextPath}/admin/product/adminProductList.do?chapter=${section_num}">${section_num}</a>
					</c:forEach>
					<a
						href="${contextPath}/admin/product/adminProductList.do?chapter=${chapter+1}"> &#62;</a>

				</div>
			</div>
		</div>
	</form>
</body>
</html>