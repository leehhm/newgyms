<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="owner" value="${ownerPageVO} " />
<c:set var="member"  value="${memberVO}"  />
 <%
   //치환 변수 선언합니다.
    //pageContext.setAttribute("crcn", "\r\n"); //개행문자
    pageContext.setAttribute("crcn" , "\n"); //Ajax로 변경 시 개행 문자 
    pageContext.setAttribute("br", "<br/>"); //br 태그
%> 
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<script src="https://developers.kakao.com/sdk/js/kakao.js" charset="utf-8"></script>

<link href="${contextPath}/resources/css/mypage.css?after" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css?after" rel="stylesheet" />
<link href="${contextPath}/resources/css/ownerPageIntroView.css?after" rel="stylesheet" />
<title>사업장 소개 페이지</title>

</head>
<body>
	<div class="con-min-width">
		<div class="con">
		<div class="contain">
				<div id="contain_right">
					<input class="ownerPage_intro_title" type="text" size="10" value=" ${member.center_name}" name="center_name" readonly disabled/>
				
				<!-- 사업장 정보 -->
				<table class="info_table">
					<tr>
						<td>
							<input type="text" class="center_address" value="&#128204; ${member.road_address} ${member.namuji_address}" name="center_address" readonly disabled/>
							<input type="text" class="center_tel" value="&#128222; ${member.owner_tel1}-${member.owner_tel2}-${member.owner_tel3}" name="center_tel" readonly disabled/>
						</td>
					</tr>
				</table>
				<div class="line" style="width:1200px; height:1px; border:0.5px solid rgb(244,244,244);"></div>
				
				<!-- 추천회원권 -->
				<div class="productList">   
			      <c:set  var="product_count" value="0" />   
			      		<p style="font-size:18px; color:#0F0573;">추천 회원권<a href="${contextPath}/product/productList.do?category=전체보기&address=대전" style="font-size:12px; color:#FFC300;"> > 더보기</a></p>	
			      <div class="container">
			              <c:choose>
		                  <c:when test="${ empty productList  }" >
		                     <div style="font-size:16px;">등록된 상품이 없습니다.</div>
		                  </c:when>
			               <c:otherwise>
			               <c:forEach var="item" items="${productList }" begin="0" end="3"> <!-- 화면에 상품 3개만 출력 -->
			                  <c:set  var="product_total_count" value="${product_count+1 }" />
			                    <div class="item">
			                      <div class="product_image">
			                        <a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">
			                        <img alt="" src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
			                     </a>
			                     <div class="wish" >
			                     </div>
			                        <a id="wish" href=""><img src="${contextPath}/resources/image/heart.png" alt="찜하기"></a>
			                     </div>
			                  <div class="product_description">
			                        <h2><a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">${item.product_name }</a></h2>
			               			<!-- 사업장관리 페이지로 이동 -->
			                        <h3><a href="#">${item.center_name }</a></h3>
			                  </div>
			                  <div class="product_price">
			                        <div class="discount_rate"><fmt:formatNumber  value="${item.product_sales_price/item.product_price}" type="percent" var="discount_rate" />${discount_rate }</div>
			                        <div class="sales_price"><fmt:formatNumber  value="${item.product_sales_price}" type="number"/>원</div>
			                        <div class="price"><fmt:formatNumber  value="${item.product_price}" type="number"/>원</div>               
			                  </div>
			                  </div>
			               </c:forEach>
			            </c:otherwise>
			            </c:choose>
			      </div>
			   </div>
			   <div class="line" style="width:1200px; height:1px; border:0.5px solid rgb(244,244,244);"></div>
			   
			   
			   <!-- 소개 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128588;&nbsp;소개</p>
			   <div style="margin-left:25px;">
			   		${fn:replace(ownerPageVO.intro_introduce, crcn, br)}
			   </div>
			   
			   <!-- 공지사항 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128227;&nbsp;공지사항</p>
			   <div style="margin-left:25px;">
			   		${fn:replace(ownerPageVO.intro_notice, crcn, br)}
			   </div>
			   
			   <!-- 운영시간 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128336;&nbsp;운영시간</p>
			   <div style="margin-left:25px;">
			   		${fn:replace(ownerPageVO.intro_time, crcn, br)}
			   </div>
			   
			   <!-- 운영프로그램 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128203;&nbsp;운영프로그램</p>
			   <div style="margin-left:25px;">
			   		${fn:replace(ownerPageVO.intro_program, crcn, br)}
			   </div>
			   
			   <!-- 부가 서비스-->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128077;&nbsp;부가 서비스</p>
			   <div style="margin-left:25px;">
			   		${fn:replace(ownerPageVO.intro_service, crcn, br)}
			   </div>
			   
              <!-- 지도 API -->        
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128205;&nbsp;위치 정보</p>
			   <div class="tab_title" style="margin-left:25px;">
                   
              <p>${product.product_location_details}</p>
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
                  //도로명 주소 가져오기
                  var road_address = '${member.road_address}';
                  // 주소-좌표 변환 객체를 생성합니다
                  var geocoder = new kakao.maps.services.Geocoder();
                  // 주소로 좌표를 검색합니다
                  geocoder.addressSearch(road_address, function(result, status) {
                      // 정상적으로 검색이 완료됐으면 
                       if (status === kakao.maps.services.Status.OK) {
                  
                          /*    마커 이미지 변경 */
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
                         /*    마커 이미지 변경 */
                  
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
			   
			</div><!-- contain_right END -->
		  </div><!-- contain END -->
		</div><!-- con END -->
	</div><!-- con-min-width END -->
</body>
</html>