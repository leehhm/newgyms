<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 총 적립 포인트 -->
<c:set var="total_product_point" value="0" />

<!-- 총 상품 금액 -->
<c:set var="total_product_price" value="0" />

<!-- 총 주문 금액 -->
<c:set var="total_order_price" value="0" />
<!-- 총 할인금액 -->
<c:set var="total_discount_price" value="0" />
<!-- 최종 결제 금액 -->
<c:set var="final_total_order_price" value="0" />

<!DOCTYPE html>
<html>
<head>
<link href="${contextPath}/resources/css/order.css?after" rel="stylesheet" type="text/css" media="screen">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

/* 구매자와 정보가 동일한지 체크 */
$(document).ready(function() {
   $("#sameInfo").click(function() {
   var member_name = $('#td_orderer_name').val();
   var member_hp1 = $('#td_orderer_hp1').val();
   var member_hp2 = $('#td_orderer_hp2').val();
   var member_hp3 = $('#td_orderer_hp3').val();
   
      if($("#sameInfo").is(":checked")) {
      $('#td_receiver_name').val(member_name);
       $('#td_receiver_hp1').val(member_hp1);
      $('#td_receiver_hp2').val(member_hp2);
      $('#td_receiver_hp3').val(member_hp3);
    
   }
   else {
      $('#td_receiver_name').val("");
       $('#td_receiver_hp1').val("");
      $('#td_receiver_hp2').val("");
      $('#td_receiver_hp3').val("");
       
   }
  });
 });
 
 /* 전액사용 체크박스 */
 $(document).ready(function() {
   $("#all_use").click(function() {
	   
      if($("#all_use").is(":checked")) {
    	  
	      var str_orderPoint = $('#td_orderPoint').val(); //보유 적립금
	      var orderPoint = Number(str_orderPoint); //숫자로 변환
	        $('#td_orderPoint_use').val(orderPoint); //사용 적립금에 넣기
	        
      } else {
        	var orderPoint = Number(0);
        	 $('#td_orderPoint_use').val("");
        	 
      }
      const option = {
             maximumFractionDigits: 0
         } 
       document.getElementById("total_discount_price").innerHTML = orderPoint.toLocaleString('ko-KR',  option) ;
      final_total_order_price();
  });
 });
 
 
 /* 사용적립금 출력 */
$(document).ready(function(){
   // 입력란에 입력을 하면 입력내용에 내용이 출력
   $("#td_orderPoint_use").keyup(function(){
      var orderPoint = $('#td_orderPoint').val(); //보유 적립금
      var str_orderPoint_use = $('#td_orderPoint_use').val(); //사용 적립금
      var orderPoint_use = Number(str_orderPoint_use);
      
      var total_discount_price = $('#total_discount_price').val(); //총 할인금액
      
      if(orderPoint_use > orderPoint) { //보유 적립금를 초과하는 금액을 입력한 경우
        let point_check = document.getElementById("point_check");
         point_check.style.color = "red";
         point_check.innerHTML="보유 적립금액 이내로 입력해주세요 :(";
         $("#total_discount_price").text(orderPoint);
         $("#all_use").prop("checked", true);
         
      } else if (orderPoint_use == orderPoint) {
         $("#total_discount_price").text(orderPoint);
         $("#all_use").prop("checked", true);
         point_check.innerHTML="";
    	  
      } else if (orderPoint_use == '') {
         $("#total_discount_price").text(0);
         point_check.innerHTML="";
         
      } else {
         $("#total_discount_price").text(orderPoint_use);
         $("#all_use").prop("checked", false);
         point_check.innerHTML="";
      }
      final_total_order_price();
   });
});

/* 최종 결제 금액 */
   function final_total_order_price() {
      var str_total_product_price = $('#total_product_price').text(); //총 상품금액
      var total_product_price =  Number(str_total_product_price.replace(/,/g, ''));  //숫자로 변환 
      
      var str_total_discount_price =  $('#total_discount_price').text(); //총 할인금액
      var total_discount_price = Number(str_total_discount_price.replace(/,/g, ''));  //숫자로 변환 
       
      var final_total_order_price = total_product_price - total_discount_price;
       document.myOrder.total_price.value = final_total_order_price;

      const option = {
             maximumFractionDigits: 0
         } 
       document.getElementById("final_total_order_price").innerHTML = final_total_order_price.toLocaleString('ko-KR',  option) ;
}

   function checkcard() {
	      var selected = $("#card_com_name option:checked").val();
	      if (selected == "선택") {
	         alert("카드사를 선택해주세요.");
	         document.getElementById("card_com_name").focus();
	      } else {
	         document.getElementById('op_form').submit();
	      }
	      return false;
	   }
   
   //상품을 선택하지 않고 주문하기를 누른 경우
   $(function(){
         var responseMessage = "<c:out value="${message}" />";
         if(responseMessage != ""){
             alert(responseMessage);
             location.href="${contextPath}/cart/myCartList.do";
         }
     }) 

</script>
</head>
<body>
   <div class="con-min-width">
      <div class="con">
         <div class="orderPay"><h1>주문/결제</h1></div>
        <h2 id="total_count">총 ${fn:length(myOrderList)}건</h2>
         <form action="${contextPath}/order/payToOrderProduct.do" method="post" name="myOrder" onsubmit="return false" id="op_form">
         <!-- 상품 정보   -->
         <div class="prodInfo">상품 정보</div>
         <table style="">
            <thead>
               <tr>
                  <td></td>
                  <th>상품명</th>
                  <th>판매자</th>
                  
                  <c:if test="${isLogOn=true and not empty memberInfo}">
                     <th>적립금</th>
                  </c:if>
                  
                  <th>구매가</th>
               </tr>
            </thead>
            <tbody style="text-align: center;">
               <c:forEach var="item" items="${myOrderList }">
                  <tr>
                     <td style="width:150px;"><img
                        style="margin-left: 40px; width: 100px; height: 100px; border: 1px solid #d8d8d8; border-radius: 10px;" alt="상품 이미지"
                        src="${contextPath}/thumbnails.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
                     </td>
                     <td style="text-align: left; padding-left:50px;">
                        <div style="font-weight:bold; margin-bottom:10px; ">${item.product_name}</div>
                        <div style="color:rgb(100,100,100); font-size:14px;">[옵션] ${item.product_option_name} (+<fmt:formatNumber value="${item.product_option_price}" type="number" var="product_option_price" />${product_option_price }원)</div>
                     </td>
                     <td>${item.center_name}</td>
                     
                     <c:if test="${isLogOn=true and not empty memberInfo}">
                        <td>
                          <div><fmt:formatNumber value="${item.product_point }" type="number"/>원 </div>
                           <c:set var="total_product_point" value="${total_product_point + item.product_point}" />
                        </td>
                     </c:if>
                     
                     <td>
                        <div style="text-decoration: line-through; color:#969696;"><fmt:formatNumber value="${item.product_price}" type="number" var="product_price" /></div><!-- 정가 -->
                        <div><fmt:formatNumber value="${item.product_sales_price + item.product_option_price}" type="number" var="product_total_price" />${product_total_price }원</div><!-- 할인가 + 옵션가격 -->
                        <c:set var="total_product_price" value="${total_product_price + item.product_sales_price + item.product_option_price}" />
                     </td>
                  </tr>
               </c:forEach>
            </tbody>
         </table>
         <!-- 사용자 정보  -->
         <div class="memberInfo">사용자 정보</div>
         <table class="memberInfoTable">
            <tbody>
               <tr>
                  <td>구매자</td>
                  <td>
                     이름&nbsp;&nbsp;<input id="td_orderer_name" class="orderName" type="text" value="${orderer.member_name}" name="orderer_name" type="text" size="19" maxlength="10" pattern="[가-힣]{2,10}" required title="이름은 공백없이 한글로 입력해주세요." />
                  <br><br>
                     전화번호&nbsp;&nbsp;
                     <input id="td_orderer_hp1" name="orderer_hp1" class="orderPhoneNum" type="text" size="6" maxlength="3" pattern="[0-9]{3}" required title="앞번호 3자리를 숫자로 입력해주세요." value="${orderer.hp1}" />
                      - &nbsp;<input id="td_orderer_hp2" name="orderer_hp2" class="orderPhoneNum" type="text" size="6" maxlength="4" pattern="[0-9]{4}" required title="중간번호 4자리를 숫자로 입력해주세요." value="${orderer.hp2}" />
                       - &nbsp;<input id="td_orderer_hp3" name="orderer_hp3" class="orderPhoneNum"type="text" size="6" maxlength="4" pattern="[0-9]{4}" required title="뒷번호 4자리를 숫자로 입력해주세요." value="${orderer.hp3}"/>
                  </td>
                  <td></td>
                  </tr>
                  <tr>
                  <td>이용자</td>
                  <td> 
                        이름&nbsp;&nbsp; <input id="td_receiver_name" class="receiverName" type="text" value="" name="receiver_name" type="text" size="19" maxlength="10" pattern="[가-힣]{2,10}" required title="이름은 공백없이 한글로 입력해주세요." />
                        <br><br>
                     전화번호&nbsp;&nbsp;
                     <input id="td_receiver_hp1" name="receiver_hp1" class="receiverPhoneNum" type="text" size="6" maxlength="3" pattern="[0-9]{3}" required title="앞번호 3자리를 숫자로 입력해주세요." />
                      - &nbsp;<input id="td_receiver_hp2" name="receiver_hp2" class="receiverPhoneNum" type="text" size="6" maxlength="4" pattern="[0-9]{4}" required title="중간번호 4자리를 숫자로 입력해주세요." />
                       - &nbsp;<input id="td_receiver_hp3"name="receiver_hp3"  class="receiverPhoneNum"type="text" size="6" maxlength="4" pattern="[0-9]{4}" required title="뒷번호 4자리를 숫자로 입력해주세요." />
                    </td>
                  <td style="text-align:left;" >
                     <input type="checkbox" style="width:20px; height:20px; margin-right:5px;" id="sameInfo">구매자와 동일
                  </td>
               </tr>
             <c:if test="${isLogOn==false}">  
               <tr>
                     <td width="200px">비회원 주문조회 비밀번호  </td>
                     <td colspan="2">
                     <input id="nonmember_pw" class="nonMemberPw"  type="password" value="" name="nonmember_pw" style="width: 200px;" required>
                     </td>
               </tr>
          </c:if>
            </tbody>
         </table>
         
        <c:if test="${isLogOn==true and not empty memberInfo }">  
                      
         <!-- 적립금 사용   -->
         <div class="pointInfo">적립금 사용</div>
         <table style="margin-bottom: 30px;">
            <tr>
               <td style="width:150px;">보유 적립금</td>
               <td><input id="td_orderPoint" type="hidden" value="${orderPoint}"><fmt:formatNumber value="${orderPoint}" type="number" />원</td>
               <td colspan="3"></td>

            </tr>
            <tr>
               <td style="width:150px;">사용 적립금</td>
               <td style="width:200px;">
                  <input id="td_orderPoint_use" class="pointInput" type="text" pattern="[0-9]{*}" style="margin-right: 5px;" value="0" name="point_price" >원
               </td>
               <td style="width:150px;">
                  <input id="all_use" type="checkbox" class="all_use_btn"> 전액사용    
               </td>
            <td style="text-align:left;">               
                  <span id="point_check"> </span>
               </td>
            </tr>
         </table>
         </c:if>
         
    <!-- 최종주문결제 -->
         <div class="totalInfo">결제상세</div>
         <table>
            <tr>
               <td>총 상품금액</td>
               <td>총 할인금액</td>
               
               <td>최종 결제금액</td>
               <c:if test="${isLogOn==true and not empty memberInfo }">  
                     <td>총 예상 적립금</td>
               </c:if>
            </tr>
            <tr>
                 <td><span id="total_product_price"><fmt:formatNumber value="${total_product_price}" type="number" /></span>원</td>
                 <td><span id="total_discount_price">0</span>원</td> <!-- 총 할인 금액 -->
                  <td><span id="final_total_order_price"><fmt:formatNumber value="${total_product_price}" type="number" /></span>원</td>  <!-- 최종 결제 금액 -->

              <c:if test="${isLogOn==true and not empty memberInfo }">  
                  <td>
                     <fmt:formatNumber value="${total_product_point }" type="number" />원
                  </td> <!-- 해당상품 적립금 -->
              </c:if>
            </tr>
         </table>
         
         <!-- 결제수단   -->
         <div class="payInfo">결제수단</div>
         <table>
            <tbody>
              <tr>
               <td><input name="pay_method" id="pay_method" type="radio" value="신용카드" onClick="fn_pay_card()" checked>신용카드</td>
               <td><input name="pay_method" id="pay_method" type="radio" value="에스크로(실시간 계좌이체)">에스크로(실시간 계좌이체)</td>
               <td><input name="pay_method" id="pay_method" type="radio" value="무통장 입금">무통장 입금</td>
               <td><input name="pay_method" id="pay_method" type="radio" value="카카오 페이">카카오 페이(간편결제)</td>
               <td><input name="pay_method" id="pay_method" type="radio" value="페이코(간편결제)">페이코(간편결제)</td>
              </tr>
            </tbody>
         </table>
         <div ></div>
         <div style="margin-top:20px; margin-left:40px;">
            <span>카드 선택:</span> 
               <select id="card_com_name" name="card_com_name" class="cardComName">
                  <option selected>선택</option>
                  <option value='신한은행'>신한은행</option>
                  <option value='KB국민은행'>KB국민은행</option>
                  <option value='IBK기업은행'>IBK기업은행</option>
                  <option value='NH농협은행'>NH농협은행</option>
                  <option value='우리은행'>우리은행</option>
                  <option value='KDB산업은행'>KDB산업은행</option>
                  <option value='KEB하나은행'>KEB하나은행</option>
                  <option value='SH수협은행'>SH수협은행</option>
                  <option value='SC제일은행'>SC제일은행</option>
                  <option value='한국씨티은행'>한국씨티은행</option>
                  <option value='우체국'>우체국</option>
                  <option value='토스뱅크'>토스뱅크</option>
                  <option value='카카오뱅크'>카카오뱅크</option>
                  <option value='케이뱅크'>케이뱅크</option>
               </select> 
               <br><br>
               <span>할부 기간:</span> 
               <select id="card_pay_month" name="card_pay_month" class="cardPayMonth">
                  <option value="일시불" selected>일시불</option>
                  <option value="2개월">2개월</option>
                  <option value="3개월">3개월</option>
                  <option value="4개월">4개월</option>
                  <option value="5개월">5개월</option>
                  <option value="6개월">6개월</option>
               </select>
            </div>  
            <div></div>
            
             <!-- 보낼정보 -->
            <input type="hidden" name="member_id" value="${memberInfo.member_id}">

            <c:forEach var="item" items="${myOrderList}">
               <input type="hidden" name="product_id" value="${item.product_id}">
               <input type="hidden" name="product_main_image"
                  value="${item.product_main_image}">
               <input type="hidden" name="product_name"
                  value="${item.product_name}">
               <input type="hidden" name="product_option_name"
                  value="${item.product_option_name}">
               <input type="hidden" name="product_option_price"
                  value="${item.product_option_price}">
               <input type="hidden" name="center_name" value="${item.center_name}">
               <input type="hidden" name="product_price"
                  value="${item.product_price}">
               <input type="hidden" name="product_sales_price"
                  value="${item.product_sales_price}">
            </c:forEach>
               <input type="hidden" name="total_price" value="${total_product_price}"> 
               <input type="hidden" name="new_point" value="${total_product_point}">
               

            <!-- 버튼 -->
         <div class="submit">
            <button class="submit_btn1" type="submit" value="결제하기" id="order_btn" onclick="javascript:checkcard();">결제하기</button>
            <a class="submit_btn2" href="${contextPath}/main/main.do" >취소하기</a>
         </div>
        </form>
        </div>
   </div>
</body>
</html>