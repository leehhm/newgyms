<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<c:set var="product"  value="${productVO}"  />
<c:set var="optList"  value="${productOptList }"  />

<c:set var="detailImageList"  value="${imageMap.detailImageList }"  />
<c:set var="priceImageList"  value="${imageMap.priceImageList }"  />
<c:set var="facilityImageList"  value="${imageMap.facilityImageList }"  />

<c:set var="reviewList" value="${reviewMap.reviewList}" />
<c:set var="reviewImageList" value="${reviewMap.reviewImageList}" />

<c:set var="questionList"  value="${questionList }"  />
<c:set var="answerList"  value="${answerList }"  />
<c:set var="member"  value="${memberVO}"  />
 <%
   //치환 변수 선언합니다.
    //pageContext.setAttribute("crcn", "\r\n"); //개행문자
    pageContext.setAttribute("crcn" , "\n"); //Ajax로 변경 시 개행 문자 
    pageContext.setAttribute("br", "<br/>"); //br 태그
%>  
<!DOCTYPE html>
<html>
<head>
<script src="https://developers.kakao.com/sdk/js/kakao.js" charset="utf-8"></script>
<meta charset="EUC-KR">
<title>제품 상세페이지</title>
<link href="${contextPath}/resources/css/product.css?after" rel="stylesheet" type="text/css" media="screen">
<script type="text/javascript">


	function fn_check_productState() {
		if(${product.product_state} != '판매중') {
			alert('판매중인 상품이 아닙니다 :(');
			history.back();
		}
	}

	/* 옵션 선택 시 총 상품금액 출력*/ 
	$(document).ready(function(){
		$('#order_product_opt').on("change", function(){
		    
			//할인가
		    var product_sales_price = ${product.product_sales_price};
		    var sales_price = Number(product_sales_price);
		    
		    //선택된 옵션가격
		    var product_option = $("#order_product_opt option:checked").text(); //옵션명 (+가격원)
			var product_option_spl = product_option.split(' (+'); 
			var product_option_spl = product_option_spl[1].split('원)');
			var str_product_option_price = product_option_spl[0]; 
		    var option_price =  Number(str_product_option_price.replace(/,/g, '')); //숫자로 변환
		    
		    //총 상품금액
			var total_price = sales_price + option_price;
			
			const option = {
				 maximumFractionDigits: 0
			}
		    document.getElementById("total_price").innerHTML = total_price.toLocaleString('ko-KR',  option) ;
		});
	});
	
	/* 찜 */
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
			       		} else {
				             location.reload();
			       		} 
		          }else if(data.trim()=='already_existed'){
		             alert("찜 목록에서 삭제되었습니다 :) ");   
		             location.reload();
	   
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

	
	/* 구매하기 */
	function order_each_product(product_id,product_name, product_main_image, product_price, product_sales_price, center_name) {
		// option_id 가져오기
		var option_id = $("#order_product_opt option:checked").val(); 
		
		if (option_id == '') {
			alert("옵션을 선택해주세요 :( ");
			return;
		} 
		
		var product_point = ${product.product_point};
		
	    //선택된 옵션
	    var product_option = $("#order_product_opt option:checked").text(); //옵션명 (+가격원)
		var product_option_spl = product_option.split(' (+'); 

	    var product_option_name = product_option_spl[0]; 		/*옵션명*/
		var product_option_spl = product_option_spl[1].split('원)');
	    
		var str_product_option_price = product_option_spl[0];  	/*옵션가격*/
	    var product_option_price =  Number(str_product_option_price.replace(/,/g, '')); //숫자로 변환

		var formObj=document.createElement("form");
		var i_product_id = document.createElement("input"); 
	    var i_product_name = document.createElement("input");
	    var i_product_main_image=document.createElement("input");
	    var i_product_price=document.createElement("input");
	    var i_product_sales_price=document.createElement("input");
	    var i_product_option_name=document.createElement("input");
	    var i_product_option_price=document.createElement("input");
	    var i_product_point=document.createElement("input");
	    var i_center_name=document.createElement("input");
		
	    i_product_id.name="product_id";
	    i_product_name.name="product_name";
	    i_product_main_image.name="product_main_image";
	    i_product_price.name="product_price";
	    i_product_sales_price.name="product_sales_price";
	    i_product_option_name.name="product_option_name";
	    i_product_option_price.name="product_option_price";
	    i_product_point.name="product_point";
	    i_center_name.name="center_name";
	    
	    i_product_id.value=product_id;
	    i_product_name.value=product_name;
	    i_product_main_image.value=product_main_image;
	    i_product_price.value=product_price;
	    i_product_sales_price.value=product_sales_price;
	    i_product_option_name.value=product_option_name;
	    i_product_option_price.value=product_option_price;
	    i_product_point.value=product_point;
	    i_center_name.value=center_name;
	    
	    formObj.appendChild(i_product_id);
	    formObj.appendChild(i_product_name);
	    formObj.appendChild(i_product_main_image);
	    formObj.appendChild(i_product_price);
	    formObj.appendChild(i_product_sales_price);
	    formObj.appendChild(i_product_option_name);
	    formObj.appendChild(i_product_option_price);
	    formObj.appendChild(i_product_point);
	    formObj.appendChild(i_center_name);

	    document.body.appendChild(formObj); 
	    formObj.method="post";
	    formObj.action="${contextPath}/order/orderEachProduct.do";
	    formObj.submit();
	 }	 

	/* 장바구니 담기 */
	function add_cart(product_id) {
		
		/* option_id 가져오기 */
		var option_id = $("#order_product_opt option:checked").val(); 
		
		if (option_id == '') {
			alert("옵션을 선택해주세요 :( ");
			return;
		} 
		
	    $.ajax({
	       type : "post",
	       async : false,
	       url : "${contextPath}/cart/addProductInCart.do",
	       data : {
	          product_id:product_id,
	          option_id:option_id
	       },
	       success : function(data, textStatus) {
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
	
	/* 문의글 작성 팝업 */
	function qnaPopup(isLogOn, loginForm, type) {
		var product_id = ${product.product_id};
		if (isLogOn == '' || isLogOn == 'false') {
			alert("로그인 후 글쓰기가 가능합니다.");
			location.href = loginForm;
		} else {
			if (type == 'open') {
				$('#qna_write_popup').attr('style', 'visibility:visible');
				
			} else if (type == 'close') {
				$('#qna_write_popup').attr('style', 'visibility:hidden');
			}
		}
	}

</script>
</head>
<body onload="fn_check_productState()">
<div class="con-min-width">
<div class="con">

  <div id="productDetail">
	<div class="product_image">
	   <img alt="" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${product.product_main_image}">			  
	</div>
	<div class="product_description">
		<h1>${product.product_name }</h1>
		<h2><a href="${contextPath}/owner/main/ownerPageIntroView.do?member_id=${item.member_id}">${member.center_name }</a></h2>
		
		<div class="product_price">         
			<div class="sales_price" id="sales_price"><fmt:formatNumber value="${product.product_sales_price}" type="number"/>원</div>
		    <div class="price"><fmt:formatNumber  value="${product.product_price}" type="number"/>원</div>
	        <div class="discount_rate"><fmt:formatNumber  value="${(product.product_price - product.product_sales_price)/product.product_price}" type="percent" var="discount_rate" />${discount_rate }</div>
		</div>
	
		<div class="point"><h3>회원 구매 시 <fmt:formatNumber  value="${product.product_point}" type="number"/>P 적립</h3></div>
		<div class="option">
			<h1>개월/횟수</h1>
			<select id="order_product_opt" name="order_product_opt">
				<option value="">[필수] 옵션 선택</option>
					<c:forEach var="opt" items="${optList }">
						<option value="${opt.option_id}">${opt.product_option_name} (+<fmt:formatNumber  value="${opt.product_option_price}" type="number"/>원)</option>
					</c:forEach>
	 		</select>
		</div>
		
		<div class="total_price"> 총 상품금액 <span id="total_price"><fmt:formatNumber  value="${product.product_sales_price}" type="number"/></span>원</div>
		
		<div class="buyCartWish">
	      <ul>
	      <c:choose>
		      <c:when test="${isAreadyExisted ==true }">
				<li><a class="wish" href="javascript:add_wishList('${product.product_id}');"><img src="https://cdn-icons-png.flaticon.com/512/833/833472.png" alt="찜하기"></a></li>
		      </c:when>
	      	<c:otherwise>
				<li><a class="wish" href="javascript:add_wishList('${product.product_id}');"><img src="${contextPath}/resources/image/heart.png" alt="찜하기"></a></li>
	      	</c:otherwise>
	      </c:choose>
	         <li><a class="buy" href="javascript:order_each_product('${product.product_id}','${product.product_name }','${product.product_main_image}','${product.product_price}','${product.product_sales_price}','${member.center_name }');">구매하기 </a></li>
	         <li><a class="cart" href="javascript:add_cart('${product.product_id}');" >장바구니</a></li>
			</ul>
		</div>
	</div>

	<!-- 제품 상세페이지 내용 들어 가는 곳 -->
	<div class="product_detail">
		<ul class="tabs">
			<li><a href="#tab1">프로그램 안내</a></li>
			<li><a href="#tab2">이용후기</a></li>
			<li><a href="#tab3">Q&A</a></li>
			<li><a href="#tab4">환불 안내</a></li>
			<li><a href="#tab5">사업자 정보</a></li>
		</ul>
		<div class="tab_container">
			<div class="tab_content" id="tab1">
		        <div class="tab_title">
				      프로그램 상세정보
		          <p>${fn:replace(product.product_program_details ,crcn,br)}</p>
		          <c:if test="${not empty detailImageList}">
					<c:forEach var="image" items="${detailImageList }">
			          <img alt="프로그램 상세정보 이미지" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${image.fileName}">
					</c:forEach>
		          </c:if>
		        </div>

				<div class="tab_title">
		     		   가격 정보     
		          <p>${fn:replace(product.product_price_details ,crcn,br)}</p>
		          <c:if test="${not empty priceImageList}">
			          <c:forEach var="image" items="${priceImageList }">
			          <img alt="가격 정보 이미지" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${image.fileName}">
			          </c:forEach>
		          </c:if>
		   	     </div>

		        <div class="tab_title">
		    		  시설 정보
		          <p>${fn:replace(product.product_facility_details ,crcn,br)}</p>
		          <c:if test="${not empty facilityImageList}">
			          <c:forEach var="image" items="${facilityImageList }">
			          <img alt="시설 정보 이미지" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${image.fileName}">
			          </c:forEach>
		          </c:if>
		        </div>

		        <div class="tab_title">
				        위치 정보
		          <p>${fn:replace(product.product_location_details ,crcn,br)}</p>
		        
				<!-- 카카오 지도 API -->        
			        <div id="map" style="width:1000px;height:600px;"></div>
			
					<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=de674c4e967f5ef6143551099c5edf72&libraries=services"></script>
					<script>
						var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
						    mapOption = {
						        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
						        level: 3 // 지도의 확대 레벨
						    };  
						
						// 지도를 생성합니다    
						var map = new kakao.maps.Map(mapContainer, mapOption); 
						// 회원 도로명 주소 가져오기
						var road_address = '${member.road_address}';
						// 주소-좌표 변환 객체를 생성합니다
						var geocoder = new kakao.maps.services.Geocoder();
						// 주소로 좌표를 검색합니다
						geocoder.addressSearch(road_address, function(result, status) {
						    // 정상적으로 검색이 완료됐으면 
						     if (status === kakao.maps.services.Status.OK) {
						
						        /* 	마커 이미지 변경 */
						    	var imageSrc = 'https://cdn-icons-png.flaticon.com/512/8059/8059174.png', // 마커이미지의 주소입니다    
						        imageSize = new kakao.maps.Size(64, 69), // 마커이미지의 크기입니다
						        imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
						          
						    	// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
						    	var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption),
						    	    markerPosition = new kakao.maps.LatLng(result[0].y, result[0].x); // 마커가 표시될 위치입니다
						    	
						    	// 마커를 생성합니다
						    	var marker = new kakao.maps.Marker({
						    	    position: markerPosition, 
						    	    image: markerImage // 마커이미지 설정 
						    	});
						    	
						    	// 마커가 지도 위에 표시되도록 설정합니다
						    	marker.setMap(map); 
						    	/* 	마커 이미지 변경 */
						
						        // 인포윈도우로 장소에 대한 설명을 표시합니다
						        var infowindow = new kakao.maps.InfoWindow({
						            content: '<div class="infoWindow">${member.center_name }</div>'
						        });
						        infowindow.open(map, marker);
						
						        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
						        map.setCenter(markerPosition);
						    } 
						});    
					</script>		
		        </div>
			</div>

			<div class="tab_content" id="tab2">
				<div class="tab_title">이용후기</div>
	        	<table class="review_list">
	      	    <tbody>
		            <c:choose>
					   <c:when test="${ empty reviewList  }" >
						   <tr>
								<td style="width:1200px; text-align:center; border:none;">등록된 이용후기가 없습니다.</td>
					 	   </tr>
					   </c:when>
					   <c:otherwise>
					        	<h2 class="total_count">총 ${fn:length(reviewList)}건</h2>
					            <tr>
					              <th>번호</th>
					              <th colspan="2">제목</th>
					              <th>평점</th>
					              <th>작성자</th>
					              <th>작성일</th>              
					            </tr>
					
					  		 <c:set  var="review_count" value="0" />
					            <c:forEach var="review" items="${reviewList }"> 
					            <c:set  var="review_count" value="${review_count+1 }" /> 
						          <!-- 리뷰 제목 -->
						          <tr class="review_title ">
						            <td>
						              ${review_count }
						            </td>
						            <td colspan="2">
						              <div class="toggle_show" style="cursor:pointer;">
						              	${review.review_title}
						              </div>
						            </td>
						            <td class="review_score">
						            	<c:choose>
						            		<c:when test="${review.review_score == 1  }">
						            			<div class="review_score">★☆☆☆☆</div>
						            		</c:when>
						            		<c:when test="${review.review_score == 2  }">
						            			<div class="review_score">★★☆☆☆</div>
						            		</c:when>
						            		<c:when test="${review.review_score == 3  }">
						            			<div class="review_score">★★★☆☆</div>
						            		</c:when>
						            		<c:when test="${review.review_score == 4  }">
						            			<div class="review_score">★★★★☆</div>
						            		</c:when>
            							   <c:otherwise>
						            			<div class="review_score">★★★★★</div>
            							   </c:otherwise>
						            	</c:choose>
						            </td>
						          
						            <td>                          
						              <div>${review.member_id}</div>
						            </td>
						            <td>
						              <div>${review.review_write_date}</div>
						            </td>
						          </tr>
						          <!-- 리뷰 내용 -->
						          <tr class="toggle_hidden">
							          <td></td>
							          <td colspan="5">
							              <div class="review_option">[옵션] ${review.product_option_name} (+<fmt:formatNumber  value="${review.product_option_price }" type="number"/>원)</div> 
							              <div class="review_contents">${fn:replace(review.review_contents ,crcn,br)}</div>
							              <div class="review_imageList">
							              <c:forEach var="image" items="${reviewImageList }">
											<c:if test="${review.review_no == image.review_no}">
			    	      			            <img class="review_image" alt="이용후기 이미지" src="${contextPath}/reviewImage.do?review_no=${image.review_no}&fileName=${image.fileName}">												
											</c:if>
										</c:forEach>
										
							              </div>
							          </td>
					              </tr>
							 </c:forEach>
					</c:otherwise>
				</c:choose>
	    	 </tbody>
	     	 </table>

          </div>

			<div class="tab_content" id="tab3">
				<div class="tab_title">Q&A</div>
			        <a class="qna_write" href="javascript:qnaPopup('${isLogOn}', '${contextPath}/member/loginForm.do','open', '.layer01');">문의하기</a>
		          <table class="qna_list">
		          <tbody>      
					 <c:choose>
					   <c:when test="${ empty questionList  }" >
						   <tr>
								<td>등록된 Q&A가 없습니다.</td>
					 		</tr>
					   </c:when>
					   <c:otherwise>
				        	<h2 class="total_count">총 ${fn:length(questionList)}건</h2>
				
				            <tr>
				              <th>번호</th>
				              <th>답변상태</th>
				              <th>제목</th>
				              <th>작성자</th>
				              <th>작성일</th>              
				            </tr>
				
				  		 <c:set  var="qna_count" value="0" />   
				            <c:forEach var="question" items="${questionList }" > 
							<c:set  var="qna_count" value="${qna_count+1 }" /> 
					          <tr class="qna_item">
					            <td>
					            ${qna_count }
					            </td>	   
					            <td>
					              ${question.qna_answer_state }
					            </td>
									<c:choose>
										<c:when test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
											<c:choose>
												<c:when test="${question.member_id ==loginMember_id  or product.member_id ==loginMember_id or loginMember_id == 'admin'}">  <!-- 작성자와 로그인한 사람이 같거나 사업자 또는 관리자로 로그인 한 경우 -->
										            <td class="toggle_show" style="cursor:pointer;">
										            	<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
										            	${question.qna_title}                           
										            </td>
												</c:when>
											
											<c:otherwise> <!-- 작성자와 로그인한 사람이 다르거나 로그인하지 않은 경우 -->
									            	<td class="toggle_show" style="cursor:pointer;">                        
									            		<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
									            	비밀글입니다.
									         	   </td>
											</c:otherwise>
											
											</c:choose>
										 </c:when>
										<c:otherwise> <!-- 공개글인 경우 -->
								            <td class="toggle_show" style="cursor:pointer;">
								            	${question.qna_title}                           
								            </td>
										</c:otherwise>
								   </c:choose>
					            <td class="qna_writer">
					              ${question.member_id}
					            </td>
					            <td class="qna_writeDate">
					              ${question.qna_write_date}
					            </td>
					          </tr>
				         
					          <tr class="toggle_hidden">
								    <!-- QnA 질문 내용, 답변 제목, 내용 -->     
								   	<c:choose>
										<c:when test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
											<c:choose>
											<c:when test="${question.member_id ==loginMember_id  or product.member_id ==loginMember_id or loginMember_id == 'admin'}"> <!-- 작성자와 로그인한 사람이 같거나 사업자 또는 관리자로 로그인 한 경우 -->
									            <td class="qna_contents" colspan="5">
									            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
									            <c:forEach var="answer" items="${answerList }" >             	
									            	<c:choose>
										            	<c:when test="${question.qna_no == answer.qna_parent_no }"> 
											            	<p><span class="A_mark">A</span> ${answer.qna_title} <p> <!-- 답글 제목 -->
											            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
									            		</c:when>
									            	</c:choose>
									            	</c:forEach>                   
									            </td>
											</c:when>
											<c:otherwise> <!-- 작성자와 로그인한 사람이 같은 경우 -->
								            	<td colspan="5" class="qna_contents" style="padding-left:450px;">                        
								            	비밀글은 작성자만 조회할 수 있습니다.
								         	   </td>
											</c:otherwise>
											</c:choose>
								        </c:when>
								        
								        <c:otherwise> <!-- 공개글인 경우 -->
								            <td colspan="5" class="qna_contents"  >
								            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
								            <c:forEach var="answer" items="${answerList }" > 
								            	<c:choose>
									            	<c:when test="${question.qna_no == answer.qna_parent_no }"> 
										            	<p><span class="A_mark">A</span> ${answer.qna_title} </p> <!-- 답글 제목 -->
										            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
								            		</c:when>
								            	</c:choose>
								            	</c:forEach>                     
								            </td>
								        </c:otherwise>
									  </c:choose>
					    	      </tr>
				              </c:forEach>
					     </c:otherwise>
					</c:choose>
		        </tbody>
		      </table>
			</div>
			
			<div class="tab_content" id="tab4">
				<div class="tab_title">환불 안내</div>        
				<h5>취소 및 환불 규정</h5>
				 <p>${fn:replace(product.product_refund_1 ,crcn,br)}</p> 
				<h5>취소 및 환불 불가한 경우</h5>
				 <p>${fn:replace(product.product_refund_2 ,crcn,br)}</p> 
			</div>

			<div class="tab_content" id="tab5">
				<div class="tab_title">사업자 정보</div>        
				<h5>상호명 : ${member.center_name }</h5>
				<h5>대표자 : ${member.member_name }</h5>
				<h5>연락처 : ${member.hp1 } - ${member.hp2 } - ${member.hp3 }</h5>
				<h5>사업자등록번호 : ${member.owner_eid }</h5>
				<h5>사업장 소재지 : ${member.road_address } ${member.namuji_address}</h5>
				<h5>E-mail : ${member.email1}@${member.email2}</h5>
			</div>
		</div>
	</div>
	
	<!-- Q&A 문의하기 팝업 -->
			<div id="qna_write_popup"  style="visibility: hidden" >
				<p style="float:left">상품 문의하기</p>
				<a style="float:right" class="x_button"  href="javascript:" onClick="javascript:qnaPopup('${isLogOn}','${contextPath}/member/loginForm.do','close', '.layer01');">X</a>		
				<form action="${contextPath }/qna/addQuestion.do" method="post">
					<div class="product_info">
						<input type="hidden" name="product_id" value="${product.product_id }">
						<div id="product_main_image">
						   <img alt="" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${product.product_main_image}">			  
						</div>
							<p>${product.product_name }</p>
							<p>${member.center_name }</p>
					</div>
				
					<div class="qna_text">
						<p class="qna_title">제목 <input name="qna_title" type="text" required title="제목을 입력해주세요."></p>
						<p class="qna_contents">내용 <textarea name="qna_contents" cols="50" rows="10" required title="내용을 입력해주세요."></textarea></p>
					</div>
				
					<div style="float:right; padding:5px 20px;">
						<input type="checkbox" name="secret"><span style="font-size:14px; margin-left:5px">비밀글</span>
					</div>
				
					<div>
						<input class="qna_write" type="submit" value="등록하기">
					</div>
				</form>
			</div>
			
		</div>
	</div>
  </div>
</div>
</div>
</body>
</html>