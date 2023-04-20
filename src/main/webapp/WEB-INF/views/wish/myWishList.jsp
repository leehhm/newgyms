<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="wishList" value="${wishMap.myWishList}" />
<c:set var="productList" value="${wishMap.myProductList}" />
<c:set var="optList" value="${productOptList}" />

<%
//치환 변수 선언합니다.
pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
pageContext.setAttribute("br", "<br/>"); //br 태그
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="${contextPath}/resources/css/wish.css?after" rel="stylesheet" type="text/css" media="screen">

<title>장바구니</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

	/* 전체 선택,해제 */
	$(document).ready(function() {
		$("#check_all").click(function() {
			if ($("#check_all").is(":checked")) {
				$("input[name=check_one]").prop("checked", true);
				
			}
			else {
				$("input[name=check_one]").prop("checked", false);
			}
				
		});
		
			$("input[name=check_one]").click(function() {
			var total = $("input[name=check_one]").length;
			var checked = $("input[name=check_one]:checked").length;

			if (total != checked) {
				$("#check_all").prop("checked", false);
			}
			else {
				$("#check_all").prop("checked", true);
			}
			
		}); 
		
	});

	
	/* 장바구니 팝업창 열기,닫기 */
	function cartPopup(type) {
		if (type == 'open') {
			$('#cart_popup').attr('style', 'visibility:visible');
			
		} else if (type == 'close') {
			$('#cart_popup').attr('style', 'visibility:hidden');
		}
	}
	
	/* 팝업창에 옵션 출력 */
	$(document).ready(function() {
		$('.add_cart_btn').on('click', function() { 
			var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
			var currentProduct_id = thisRow.find('#current_product_id').val();
			
			console.log("현재 product_id " + currentProduct_id);

			//선택된 product_id 값을 옵션 input에 넣기
			$('#currentProduct_id').val(currentProduct_id);

			$.ajax({
				type : "GET",
				async : false,
				url : "${contextPath}/cart/selectProductOption.do",
				data : {
					product_id : currentProduct_id,
				},
				dataType : 'json',
				contentType : "application/json;charset=UTF-8",
				success : function(data) {
					var data_length = Object.keys(data).length; //JSON 객체의 길이 구하기
					
					$('#wish_product_opt').empty(); //option 초기화
					$('#wish_product_opt').append("<option value=''>[필수] 옵션 선택</option>");
					
					for (var i=0;i<data_length;i++) {
						console.log(data[i]);
						var option_id = data[i].option_id;
						var product_option_name = data[i].product_option_name;
						var _product_option_price = data[i].product_option_price;
						
						const option = {
								 maximumFractionDigits: 0
						} 
						var product_option_price = _product_option_price.toLocaleString('ko-KR',  option) ;
						$('#wish_product_opt').append("<option value='"+option_id+"'>" + product_option_name +  " (+"+product_option_price + "원)" + "</option>");
					}
				},
				error : function(data) {
					alert("에러가 발생했습니다." + data);
				},
				complete : function(data) {
					//alert("작업을완료 했습니다");
				}

			});
		});
	});
		

	/*장바구니에 추가하기*/
	function add_cart_product(){
		var product_id = $('#currentProduct_id').val();
		
		//선택된 option_id
		var option_id = $("#wish_product_opt option:checked").val();
		
		if (option_id == '') {
			alert("옵션을 선택해주세요 :( ");
			return;
		} 
		
	   $.ajax({
	      type : "post",
	      async : false, //false인 경우 동기식으로 처리한다.
	      url : "${contextPath}/cart/addProductInCart.do",
	      data : {
	    	  product_id:product_id,
	    	  option_id:option_id
	      },
	      
	      success : function(data, textStatus) {
	         //alert(data);
	    	  if(data.trim()=='add_success'){
	       		var con_cart = confirm("장바구니에 추가되었습니다. 장바구니로 이동할까요?"); 
	       		if(con_cart==true) {
	       			location.href="${contextPath}/cart/myCartList.do";
	       		} 
	          }else if(data.trim()=='already_existed'){
	             alert("이미 장바구니에 추가된 상품입니다. :) ");   
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
	
	/* 선택 삭제 */
	function delete_select_wish_product() {
		//선택된 wish_id를 담을 배열 생성
		var wish_id_list = [];
		
		//선택된 checkbox의 값 저장 
		$("input[name=check_one]:checked").each(function(){
			wish_id_list.push($(this).val());
		});
		
		console.log("체크된 값 : " + wish_id_list);
		$.ajax({
		      type : "post",
		      async : false, //false인 경우 동기식으로 처리한다.
		      url : "${contextPath}/wish/removeWishProduct.do",
		      data : {wish_id : wish_id_list},
		     // dataType : "json",
		      success : function(data) {
		            alert("삭제가 완료되었습니다 :)");   
		            location.reload();
		      },
		      error : function(data) {
		         alert("에러가 발생했습니다." + data);
		      },
		      complete : function(data) {
		         //alert("작업을완료 했습니다");
		         
		      }
		   }); //end ajax
	}

</script>

</head>
<body>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="${contextPath}/resources/css/wish.css?after" rel="stylesheet" type="text/css" media="screen">

<title>찜한 목록</title>

</head>
<body>

	<div class="con-min-width">
		<div class="con">

			<div id="myWishList">
				<h1>찜한 목록</h1>

				<table class="wish_list">
					<div class="select_all">
						<input type="checkbox" id="check_all" checked /> 전체선택
					</div>
					<tbody>
						<c:choose>
							<c:when test="${ empty wishList  }">
								<tr>
									<td colspan="6" style="text-align: center; padding: 20px;">찜 목록이 비어있습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="wish" items="${wishList }">
									<c:forEach var="product" items="${productList }">
										<c:if test="${wish.product_id == product.product_id }">
										
											<tr class="wish_item">
												<td>
													<!-- wish_item의 product_id --> 
													<input id="current_product_id" type="hidden" value="${product.product_id}" />
													
												<input type="checkbox" name="check_one" value="${wish.wish_id}" checked >
												</td>
												<td><a href="${contextPath}/product/productDetail.do?product_id=${product.product_id}">
														<img alt="상품 이미지" src="${contextPath}/thumbnails.do?product_id=${wish.product_id}&fileName=${product.product_main_image}">
													</a>
												</td>
												<td>
													<div class="wish_title">
														<a href="${contextPath}/product/productDetail.do?product_id=${product.product_id}">${product.product_name}</a>
													</div>			
													
	                          			            <div class="wish_price"> 
	                          			            	<span class="discount_rate"><fmt:formatNumber  value="${(product.product_price - product.product_sales_price)/product.product_price}" type="percent" var="discount_rate" />${discount_rate }</span>
														<span class="product_total_price"><fmt:formatNumber value="${product.product_sales_price}" type="number" /></span>
														<span class="product_price"><fmt:formatNumber value="${product.product_price}" type="number" />${product_price }원</span>
	           										</div>
												</td>
												<td class="sel_btn">
													<a class="add_cart_btn" href="javascript:cartPopup('open', '.layer01');">장바구니 담기</a>
													<a class="del_wish_btn" href="${contextPath}/wish/removeEachWishProduct.do?wish_id=${wish.wish_id}">삭제하기</a>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
					
				<a href="javascript:delete_select_wish_product();">선택삭제</a>

				<!-- 장바구니 팝업창 -->
				<div id="cart_popup" style="visibility: hidden">
					<form>
						<a class="x_button" href="javascript:" onClick="javascript:cartPopup('close', '.layer01');">X</a>
						<h6>옵션선택</h6>
						<input type="hidden" id="currentProduct_id">
						<select id="wish_product_opt" name="wish_product_opt">
						
						</select>
						<a class="add_button" href="javascript:add_cart_product();">추가</a>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
</body>
</html>