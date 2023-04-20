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
<link href="${contextPath}/resources/css/community.css" rel="stylesheet" />
<script>

/* 이용후기 팝업창 열기,닫기 */
function reviewPopup(type) {
	if (type == 'open') {
		$('#review_popup').attr('style', 'visibility:visible');
		
	} else if (type == 'close') {
		$('#review_popup').attr('style', 'visibility:hidden');
	}
}

/* 이용후기 조회 */
$(document).ready(function() {
	$('.view_review_btn').on('click', function() { 
		var review_no = $(this).find('#review_no').val(); //누른 review의 review_no 가져오기
		
		$.ajax({
			type : "GET",
			async : false,
			url : "${contextPath}/review/viewReview.do",
			data : {
				review_no : review_no,
			},
			dataType : 'json',
			contentType : "application/json;charset=UTF-8",
			success : function(data) {
			var reviewVO = data.reviewVO
			var imageList = data.ImageList
			
			var review_no = reviewVO.review_no;
			var product_id = reviewVO.product_id;
			var product_main_image = reviewVO.product_main_image;
			var product_name = reviewVO.product_name;
			var center_name = reviewVO.center_name;
			var member_id = reviewVO.member_id;
			var review_write_date = reviewVO.review_write_date;
			var product_option_name = reviewVO.product_option_name;
			var product_option_price = Number(reviewVO.product_option_price.replace(/,/g, '')); 
			var review_score = reviewVO.review_score;
			var review_title = reviewVO.review_title;
			var review_contents = reviewVO.review_contents;
			
			//초기화
				$('#product_id').empty();
				$('#product_main_image').empty();
				$('#product_name').empty();
				$('#center_name').empty();
				$('#member_id').empty();
				$('#review_write_date').empty();
				$('#product_option_name').empty();
				$('#product_option_price').empty();
				$('#review_score').empty();
				$('#review_title').empty();
				$('#review_contents').empty();
				$('#review_imageList').empty();
				
			// 값 대입
				$('#product_id').append(product_id);
				$('#product_main_image').append("<img alt='상품 메인이미지' src='${contextPath}/thumbnails.do?product_id="+product_id+"&fileName="+product_main_image+"'>");
				$('#product_name').append(product_name);
				$('#center_name').append(center_name);
				$('#member_id').append(member_id);
				$('#review_write_date').append(review_write_date);
				$('#product_option_name').append(product_option_name);
				$('#product_option_price').append(product_option_price);
				
				if(review_score == 1) {
					$('#review_score').append("<div class='review_score'>★☆☆☆☆</div>");
				} else if (review_score == '2') {
					$('#review_score').append("<div class='review_score'>★★☆☆☆</div>");
				} else if (review_score == '3') {
					$('#review_score').append("<div class='review_score'>★★★☆☆</div>");
				} else if (review_score == '4') {
					$('#review_score').append("<div class='review_score'>★★★★☆</div>");
				} else if (review_score == '5') {
					$('#review_score').append("<div class='review_score'>★★★★★</div>");
				}

				$('#review_title').append(review_title);
				$('#review_contents').append(review_contents);
				
			var imageList_length = Object.keys(imageList).length;
				
			for (var i=0;i<imageList_length;i++) {
				var fileName = imageList[i].fileName;
				$('#review_imageList').append("<li><img class='review_image' alt='이용후기 이미지' src='${contextPath}/reviewImage.do?review_no="+review_no+"&fileName="+fileName+"'></li>");
			}
				
			},
			error : function(data) {
				alert("에러가 발생했습니다." + data);
			},
			complete : function(data) {
				//alert("작업을완료 했습니다");
			}
		});
		

		/* 이용후기 이미지 슬라이드 */		
		  var imgs;
		  var img_count;
		  var img_position = 1;

		  imgs = $(".slide ul");
		  img_count = imgs.children().length;

		  //버튼을 클릭했을 때 함수 실행
		  $('#back').click(function () {
		    back();
		  });
		  $('#next').click(function () {
		    next();
		  });

		  function back() {
		    if(1<img_position){
		      imgs.animate({
		        left:'+=400px'
		      });
		      img_position--;
		    }
		  }
		  function next() {
		    if(img_count>img_position){
		      imgs.animate({
		        left:'-=400px'
		      });
		      img_position++;
		    }
		  }
	});
});		



</script>
</head>
<body>
<div class="con-min-width">
<div class="con">
			<div id="contain">
			<!-- 커뮤니티 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/common/communitySide.jsp" />
				<div id="contain_right">
			<div id="reviewList"> 
			<p id="board_title">이용후기</p>
			   <c:set  var="review_count" value="0" />   
				<p>총 ${fn:length(reviewList)}건</p>
				<div style="border-bottom: 1px solid #D8D8D8; margin-top:6px;"></div>
				<div class="container">
				<c:choose>
				   <c:when test="${ empty reviewList  }" >
						<h1>등록된 이용후기가 없습니다.</h1>
				   </c:when>
			   <c:otherwise>
				<c:forEach var="item" items="${reviewList }"> 
			   <c:set  var="review_total_count" value="${review_count+1 }" />
		        <div class="item">
			        
			        <div class="view_review_btn">
			        	<input id="review_no" type="hidden"  value="${item.review_no }">
				          <div class="review_image">
				            <a href="javascript:reviewPopup('open', '.layer01');">
				            <img alt="이용후기 이미지" src="${contextPath}/reviewImage.do?review_no=${item.review_no}&fileName=${item.review_main_image}">
							</a>
			  		    <p>${item.review_title }</p>
				   		</div>
					</div>
					<div class="review_description">			
					
			            <div class="review_score">
			            	<c:choose>
			            		<c:when test="${item.review_score == 1  }">
			            			<div class="review_score">★☆☆☆☆</div>
			            		</c:when>
			            		<c:when test="${item.review_score == 2  }">
			            			<div class="review_score">★★☆☆☆</div>
			            		</c:when>
			            		<c:when test="${item.review_score == 3  }">
			            			<div class="review_score">★★★☆☆</div>
			            		</c:when>
			            		<c:when test="${item.review_score == 4  }">
			            			<div class="review_score">★★★★☆</div>
			            		</c:when>
         							   <c:otherwise>
			            			<div class="review_score">★★★★★</div>
         							   </c:otherwise>
			            	</c:choose>
			            </div>
						<div>

	            		   <img alt="상품 메인이미지" src="${contextPath}/thumbnails.do?product_id=${item.product_id}&fileName=${item.product_main_image}">			  
							<div class="product_name"><a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">${item.product_name }</a> </div>
							<div class="center_name"><a>${item.center_name}</a></div>
						</div>	
					</div>
					
				   </div>
				</c:forEach>
			</c:otherwise>
			</c:choose>
			</div>
			
			<!-- 이용후기 조회 팝업창 -->
			<div id="review_popup" style="visibility: hidden" >
			
				<div class="review_image">
	            	   <div class="slide" >
					     <span id="back">&lang;</span>  
					      <ul id="review_imageList">
					      <li><img src="https://sitem.ssgcdn.com/08/52/53/item/1000270535208_i1_1100.jpg" alt=""></li>
					      <li><img src="https://sitem.ssgcdn.com/03/52/53/item/1000270535203_i1_1100.jpg" alt=""></li>
					      <li><img src="http://openimage.interpark.com/goods_image_big/7/1/2/8/8791137128_l.jpg" alt=""></li>
					      <li><img src="https://image.yes24.com/goods/104804469/XL" alt=""></li>
					      <li><img src="http://image.babosarang.co.kr/product/detail/FRS/2111011427461092/_600.jpg" alt=""></li>
					      <li><img src="https://usefulguide.net/wp-content/uploads/2022/08/img_theme5.jpg" alt=""></li>
					      </ul>
					      <span id="next">&rang;</span>
					    </div>
				</div>
				<div class="review_text">
					<div class="product_info"> 
						<div id="product_main_image"></div>
						<div>
							<a class="product_name" href="${contextPath}/product/productDetail.do?product_id=${review.product_id}"><span id="product_name">${review.product_name }</span></a> 
						</div>
						<div>
						<a class="center_name" href="#"> <span id="center_name"></span></a>
						</div>
					</div>
					
					<div class="review_info"> 
						<p> 작성자  <span id="member_id"></span> </p> <p> 작성일  <span id="review_write_date"></span> </p>
					
						<div>
							<p class="product_option">[옵션] <span id="product_option_name"></span> (+<span id="product_option_price"><fmt:formatNumber value="" type="number"/></span>원) </p>
							<div id="review_score" style="clear:both; font-size:15px"></div>
						</div>		
					</div>
					
					<div class="review_detail">
						<p class="review_title">제목 : <span id="review_title"></span></p>
						<p class="review_contents">내용 : <span id="review_contents"></span></p>
					</div>
					
					<div>
						<a  class="x_button"  href="javascript:" onClick="javascript:reviewPopup('close', '.layer01');">목록으로</a>		
					</div>
  				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>
</body>
</html>