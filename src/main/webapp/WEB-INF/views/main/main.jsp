<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"   isELIgnored="false"
   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("UTF-8");
%> 
<link href="${contextPath}/resources/css/main.css?after" rel="stylesheet" type="text/css"/>
<!-- 이용후기 슬라이드 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />
<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>

<script>
<!-- 타임세일타이머  -->
function remaindTime() {
     var now = new Date();
     var end = new Date(
       now.getFullYear(),
       now.getMonth(),
       now.getDate(),
       21,
       00,
       00
     );
     var open = new Date(
       now.getFullYear(),
       now.getMonth(),
       now.getDate(),
       09,
       00,
       00
     );

     var nt = now.getTime();
     var ot = open.getTime();
     var et = end.getTime();

     if (nt < ot) {
       $(".time").fadeIn();
       $("p.time-title").html("금일 오픈까지 남은 시간");
       sec = parseInt(ot - nt) / 1000;
       day = parseInt(sec / 60 / 60 / 24);
       sec = sec - day * 60 * 60 * 24;
       hour = parseInt(sec / 60 / 60);
       sec = sec - hour * 60 * 60;
       min = parseInt(sec / 60);
       sec = parseInt(sec - min * 60);
       if (hour < 10) {
         hour = "0" + hour;
       }
       if (min < 10) {
         min = "0" + min;
       }
       if (sec < 10) {
         sec = "0" + sec;
       }
       $(".hours").html(hour);
       $(".minutes").html(min);
       $(".seconds").html(sec);
     } else if (nt > et) {
       $("p.time-title").html("금일 마감");
       $(".time").fadeOut();
     } else {
       $(".time").fadeIn();
       $("p.time-title").html("금일 마감까지 남은 시간");
       sec = parseInt(et - nt) / 1000;
       day = parseInt(sec / 60 / 60 / 24);
       sec = sec - day * 60 * 60 * 24;
       hour = parseInt(sec / 60 / 60);
       sec = sec - hour * 60 * 60;
       min = parseInt(sec / 60);
       sec = parseInt(sec - min * 60);
       if (hour < 10) {
         hour = "0" + hour;
       }
       if (min < 10) {
         min = "0" + min;
       }
       if (sec < 10) {
         sec = "0" + sec;
       }
       $(".hours").html(hour);
       $(".minutes").html(min);
       $(".seconds").html(sec);
     }
   }
   setInterval(remaindTime, 1000);
</script>

<script>
<!-- 이용후기 슬라이더 -->
$(function() {
   var swiper = new Swiper(".mySwiper", {
      slidesPerView: 5,
         slidesPerGroup : 5,
         centerSlides:false,
         spaceBetween: 10,
         //그룹수가 맞지 않을 경우 빈칸으로 메우기
         loopFillGroupWithBlank : true,
         loop : true, //무한반복
         
         pagination: {
           el: ".swiper-pagination",
           type: "fraction",
         },
         navigation: {
              nextEl: ".swiper-button-next",
              prevEl: ".swiper-button-prev"
            }
   })
   
});   
</script>
   
  


 <!-- 1. 메인슬라이드 -->
  <div id="ad_main_banner" style="margin-top:7px;">
    <ul class="bjqs">       
       <li><img width="1920" height="700" src="${contextPath}/resources/image/main_slide1.png"></li>
       <li><img width="1920" height="700" src="${contextPath}/resources/image/main_slide2.png"></li>
       <li><img width="1920" height="700" src="${contextPath}/resources/image/main_slide3.png"></li> 
       <li><img width="1920" height="700" src="${contextPath}/resources/image/main_slide4.png"></li> 
    </ul>
 </div> 
 <div class="con-min-width" >
   <div class="con"> 
      <!-- 이런분들에게 추천합니다 -->
       <%-- <div style="text-align:center; margin-top:70px;">
         <div class="section-1">
            <img src="${contextPath}/resources/image/recommand.png" alt="이런분들에게 추천합니다!" style="width:600px; height:548px;">
        </div>
      </div>  --%>
        
      <!-- 2. 타임세일타이머 -->
      <div class="saleTimer">
      <hr style="margin-top:2px;">
      
      <c:set var="ymd" value="<%=new java.util.Date()%>" />
         <p class="timer-title"><fmt:formatDate value="${ymd}" pattern="MM/dd" /> 오늘의 할인가!</p>
         <div class="timer-text">마감임박!</div>
         <!-- <p class="runTimeCon font25">PM 09:00 ~ PM 09:00</p> 
         <p class="font15 time-title">금일 마감까지 남은 시간</p> -->
         <div class="time">
            <span class="hours"></span>
            <span class="col">:</span> 
            <span class="minutes"></span>
            <span class="col">:</span> 
            <span class="seconds"></span>
         </div>
         <hr style="margin-bottom:1px;">
      </div> 

     <!-- 3. 인기이용권 -->
  <div class="productList">     
      <h2 class="pop_title">이런 이용권 어때요?</h2>
      <div class="pop"><a href="${contextPath}/product/productList.do?category=전체보기&address=대전">더 다양한 이용권을 만나보세요!</a></div>
      <c:set  var="product_count" value="0" />   
      <div class="container">
               <c:choose>
                  <c:when test="${ empty productList  }" >
                     <div style="font-size:16px;">등록된 상품이 없습니다.</div>
                  </c:when>
               <c:otherwise>
               <c:forEach var="item" items="${productList }" begin="0" end="7"> 
                  <c:set  var="product_total_count" value="${product_count+1 }" />
                    <div class="item">
                      <div class="product_image">
                        <a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">
                        <img alt="" src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
                     </a>
                     <div class="wish" >
                     </div>
 	                    <a id="wish" href="${contextPath}/wish/addWishList.do?product_id=${item.product_id}"><img src="${contextPath}/resources/image/heart.png" alt="찜하기"></a>
                     </div>
                  <div class="product_description">
                        <h2><a href="${contextPath}/product/productDetail.do?product_id=${item.product_id}">${item.product_name }</a></h2>
                        
               <!-- 사업장관리 페이지로 이동 -->
                        <h3><a href="${contextPath}/owner/main/ownerPageIntroView.do?member_id=${item.member_id}">${item.center_name}</a></h3>
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
        <!-- 4. 이용후기 -->
        <!-- <div class="tab_content" id="tab2"> -->
        <h2 class="review_title">뉴짐스 고객후기</h2>
        <div class="review1"><a href="${contextPath}/review/reviewList.do">더 많은 고객 후기가 궁금하시나요?</a></div>
        <!-- 슬라이드 -->
        <div class="con1">
        <div class="swiper mySwiper">
          <div class="swiper-wrapper">
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-1.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-2.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-3.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-4.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-5.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-3.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-4.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-1.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-6.jpg"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-2.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-2.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-1.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-6.jpg"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-4.png"></div>
            <div class="swiper-slide"><img src="${contextPath}/resources/image/review-3.png"></div>
          </div>
          <!-- navigation buttons -->
          <div class="swiper-button-prev"></div>
          <div class="swiper-button-next"></div>
        </div>
        <div class="swiper-pagination"></div>
      </div>
         </div> 
       </div>
      
      <div class="line-1" style="width:1920px; height: 2px; background-color:#f4f2f2; margin-top:150px;"></div>

   
   