<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="cartList" value="${cartMap.myCartList}" />
<c:set var="productList" value="${cartMap.myProductList}" />
<c:set var="optList" value="${productOptList}" />

<!-- 장바구니 총 상품금액 -->
<c:set var="cart_total_price" value="0" />

<%
//치환 변수 선언합니다.
pageContext.setAttribute("crcn", "\r\n"); //Space, Enter
pageContext.setAttribute("br", "<br/>"); //br 태그
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link href="${contextPath}/resources/css/cart.css?after" rel="stylesheet" type="text/css" media="screen">

<title>장바구니</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

	/* 전체 선택,해제 */
	$(document).ready(function() {
		$("#check_all").click(function() {
			if ($("#check_all").is(":checked")) {
				$("input[name=check_one]").prop("checked", true);
				fn_cart_total_price('all');
			}
			else {
				$("input[name=check_one]").prop("checked", false);
				fn_cart_total_price('none');
			}
		});
		
			$("input[name=check_one]").click(function() {
			var total = $("input[name=check_one]").length;
			var checked = $("input[name=check_one]:checked").length;

			if (total != checked) {
				$("#check_all").prop("checked", false);
				fn_cart_total_price('each');
			}
			else {
				$("#check_all").prop("checked", true);
				fn_cart_total_price('all');
			}
		}); 
		
	});

	/* 총 상품금액 */
	function fn_cart_total_price(check) {
		if(check == 'all') {
			var str_cart_total_price = $('#total_price').val();
			var cart_total_price = Number(str_cart_total_price);
			console.log(cart_total_price)
		} else if (check == 'each') {
			 $("#cart_total_price").empty();
	            var cart_total_price = Number(0);
	        
	        $("input[type=checkbox]:checked").each( function() {
	            var thisRow = $(this).closest('tr');
	            /* 상품 금액 */
	            var str_total_price = thisRow.find('#product_total_price').val();
	            var product_total_price = Number(str_total_price.replace(/,/g, ''));  //숫자로 변환
	            cart_total_price += product_total_price; //cart_total_price에 합산
	        });
		} else {
			var cart_total_price = 0;
		}
		const option = {
				 maximumFractionDigits: 0
			} 
	    document.getElementById("cart_total_price").innerHTML = cart_total_price.toLocaleString('ko-KR',  option) ;
		
	}
	
	/* 옵션변경 팝업창 열기,닫기 */
	function modifyPopup(type) {
		if (type == 'open') {
			$('#modify_popup').attr('style', 'visibility:visible');
			
		} else if (type == 'close') {
			$('#modify_popup').attr('style', 'visibility:hidden');
		}
	}
	
	/* 팝업창에 옵션 출력 */
	$(document).ready(function() {
		$('.modify_option_btn').on('click', function() { 
			var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
			var currentProduct_id = thisRow.find('#current_product_id').val();
			$('#currentProduct_id').val(currentProduct_id);	//선택된 product_id 값을 옵션 input에 넣기

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
					
					$('#cart_product_opt').empty(); //option 초기화
					$('#cart_product_opt').append("<option value=''>[필수] 옵션 선택</option>");
					
					for (var i=0;i<data_length;i++) {
						var option_id = data[i].option_id;
						var product_option_name = data[i].product_option_name;
						var _product_option_price = data[i].product_option_price;
						
						//숫자형으로 변환
						const option = {
								 maximumFractionDigits: 0
						} 
						var product_option_price = _product_option_price.toLocaleString('ko-KR',  option) ;
						
						$('#cart_product_opt').append("<option value='"+option_id+"'>" + product_option_name +  " (+"+product_option_price + "원)" + "</option>");
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

	/*변경한 옵션 저장하기*/
	function modify_cart_option(){
		
		//선택된 product_id
		var product_id = $('#currentProduct_id').val();
		
		//선택된 option_id
		var option_id = $("#cart_product_opt option:checked").val();
		
		if (option_id == '') {
			alert("옵션을 선택해주세요 :( ");
			return;
		} 
		
	   $.ajax({
	      type : "post",
	      async : false, //false인 경우 동기식으로 처리한다.
	      url : "${contextPath}/cart/modifyCartOption.do",
	      data : {
	    	  product_id:product_id,
	    	  option_id:option_id
	      },
	      
	      success : function(data, textStatus) {
	         //alert(data);
	         if(data.trim()=='modify_success'){
	            alert("옵션 변경이 완료되었습니다. :)");   
				location.reload();
	         }else{
	            alert("작업 중 오류가 발생했습니다. 다시 시도해 주세요 :()");   
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
	function delete_select_cart_product() {
		//선택된 cart_id를 담을 배열 생성
		var cart_id_list = [];
		
		//선택된 checkbox의 값 저장 
		$("input[name=check_one]:checked").each(function(){
			cart_id_list.push($(this).val());
		});
		
		$.ajax({
		      type : "post",
		      async : false, //false인 경우 동기식으로 처리한다.
		      url : "${contextPath}/cart/removeSelectCartProduct.do",
		      data : {cart_id : cart_id_list},
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
	<div class="con-min-width">
		<div class="con">

			<div id="myCartList">
				<h1>장바구니</h1>
		<h2 id="total_count">총 ${fn:length(cartList)}건</h2>
		<form action="${contextPath}/order/orderCartProduct.do" method="post">
				<table class="cart_list">
					<div class="select_all">
						<input type="checkbox" id="check_all" checked /> 전체선택
					</div>
					<tbody>
						<c:choose>
							<c:when test="${ empty cartList  }">
								<tr>
									<td colspan="6" style="text-align: center; padding: 20px;">장바구니가 비어있습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="cart" items="${cartList }">
									<c:forEach var="product" items="${productList }">
										<c:if test="${cart.product_id == product.product_id }">
										
											<tr class="cart_item">
												<td>
													<!-- cart_item의 product_id --> 
													<input id="current_product_id" type="hidden" value="${product.product_id}" />
													<input type="checkbox" name="check_one" value="${cart.cart_id}" checked >
												</td>
												<td>
													<a href="${contextPath}/product/productDetail.do?product_id=${product.product_id}">
														<img alt="상품 이미지" src="${contextPath}/thumbnails.do?product_id=${cart.product_id}&fileName=${product.product_main_image}">
													</a>
												</td>
												<td>
													<div class="cart_title">
														<a href="${contextPath}/product/productDetail.do?product_id=${product.product_id}">${product.product_name}</a>
													</div>
													<div class="cart_option">
														[옵션] ${cart.product_option_name} (+<fmt:formatNumber value="${cart.product_option_price}" type="number" />원)
													</div>
												<td class="modify_option_btn">
													<a href="javascript:modifyPopup('open', '.layer01');">옵션변경</a>
												</td>
												<td text-align="center">
													<a href="#">${product.center_name }</a>
												</td>
											
												<td class="product_price">
													<fmt:formatNumber value="${product.product_price}" type="number" var="product_price" />
													${product_price }원
												</td>
												<td class="product_total_price">
													<fmt:formatNumber value="${product.product_sales_price + cart.product_option_price}" type="number" var="product_total_price" />
													${product_total_price }원
													<!-- 장바구니 총 상품금액 구하기 위한 상품 금액 hidden처리 --> 
													<input id="product_total_price" type="hidden" value="${product_total_price }" >
												</td>

													<!-- 장바구니 총 상품금액 구하기 -->
													<fmt:parseNumber value="${product_total_price } " var ="product_total_price"/>
													<fmt:parseNumber value="${cart_total_price } " var ="cart_total_price"/>
													<c:set var="cart_total_price" value="${cart_total_price+product_total_price}" />
													
												<td class="x_button">
													<a href="${contextPath}/cart/removeEachCartProduct.do?cart_id=${cart.cart_id}">X</a>
												</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
					<div class="cart_total_price"> 총 상품금액 <span id="cart_total_price"><fmt:formatNumber  value="${cart_total_price}" type="number"/></span>원</div>
					<!-- 장바구니 총 상품금액 hidden처리 --> 
					<input id="total_price" value="${cart_total_price}" type="hidden" />
					
				<a href="javascript:delete_select_cart_product();">선택삭제</a>

				<div style="text-align: center">
 					<input class="order_button" name="order" type="submit" value="주문하기">
 					<a  class="back_button"  href="javascript:history.back();" >쇼핑 계속하기</a>
				</div>
		</form>			

		<!-- 옵션 변경 팝업창 -->
		<div id="modify_popup" style="visibility: hidden">
			<form>
				<a class="x_button" href="javascript:"
					onClick="javascript:modifyPopup('close', '.layer01');">X</a>
				<h6>옵션변경</h6>
				<input type="hidden" id="currentProduct_id">
				<select id="cart_product_opt" name="cart_product_opt">
				
				</select>
				<a class="modify_button" href="javascript:modify_cart_option();">저장</a>
			</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>