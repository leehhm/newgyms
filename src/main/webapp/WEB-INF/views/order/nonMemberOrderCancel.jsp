<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/nonMemberOrder.css" rel="stylesheet" />
<title>비회원 주문결제취소 페이지</title>
</head>
<body>
   <form action="${contextPath}/mypage/myOrderRefund.do" method="post">

      <div class="con-min-width">
         <div class="con">
            <div id="contain">
               <div id="contain">
                  <div id="contain_right">
                     <p id="mypage_order_title">비회원 결제취소 및 환불</p>
                     <table style="margin-bottom: 3px; padding: 3px;">
                        <tr>
                           <td>&nbsp;주문번호&nbsp; <span id="gray_color">${myOrderDetail[0].order_seq_num}</span>
                           </td>
                           <td style="padding-left: 880px;">결제상태 &nbsp; <span
                              id="gray_color">${myOrderDetail[0].order_state}</span>
                           </td>
                        </tr>
                     </table>

                     <table id="order_detail">
                        <!-- 상품 정보 -->
                        <tr>
                           <td colspan="7"><span id="mypage_info_title">상품 정보</span></td>
                        </tr>

                        <!-- 1건의 주문에 대한 상품이 2개 이상일 경우 여러개 표시 -->
                        <c:forEach var="item" items="${myOrderDetail}" varStatus="j">
                           <tr>
                              <td width="15%">
                              <input type="hidden" name="cancel" value="${item.order_seq_num}">
                                 <div id="img_file">
                                    <img alt="img" width="100%" height="100%"
                                       src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
                                 </div>
                              </td>
                              <td colspan="2"
                                 style="width: 45%; line-height: 25px; padding-left: 10px; font-size: 15px;">${item.product_name}<br>
                                 <span id="gray_color" style="font-size: 13px;">[옵션]
                                    ${item.product_option_name}</span>
                              </td>
                              <td width="10%" align=center><span id="navy_color"
                                 style="font-size: 12px;">${item.center_name}</span></td>

                              <td width="10%" align=center>구매가</td>
                              <td width="15%" align=center style="line-height: 25px;">
                                 <s style="color: red;"><span id="gray_color">
                                 <fmt:formatNumber value="${item.product_price+item.product_option_price}" type="number" />원</span></s> 
                                 <fmt:formatNumber value="${item.product_sales_price+item.product_option_price}" type="number" />원
                              </td>
                        </c:forEach>
                     </table>

                     <table id="order_detail">
                        <!-- 주문자 정보 -->
                        <tr>
                           <td colspan="6"><span id="mypage_info_title">주문자 정보</span></td>
                        </tr>

                        <tr>
                           <td>&nbsp;&nbsp;구매자</td>
                           <td colspan="5">이름 <span id="gray_color">&nbsp;
                                 ${myOrderDetail[0].orderer_name}</span> <br> 전화번호 <span
                              id="gray_color">&nbsp;
                                 ${myOrderDetail[0].orderer_hp1}-${myOrderDetail[0].orderer_hp2}-${myOrderDetail[0].orderer_hp3}</span>
                           </td>
                        </tr>

                        <tr>
                           <td>&nbsp;&nbsp;이용자</td>
                           <td colspan="5">이름 <span id="gray_color">&nbsp;
                                 ${myOrderDetail[0].receiver_name}</span> <br> 전화번호 <span
                              id="gray_color">&nbsp;
                                 ${myOrderDetail[0].receiver_hp1}-${myOrderDetail[0].receiver_hp2}-${myOrderDetail[0].receiver_hp3}</span>
                           </td>
                        </tr>

                        <!-- 결제 정보 -->
                        <tr>
                           <td colspan="6"><span id="mypage_info_title">결제 정보</span></td>
                        </tr>

                        <tr>
                           <td width="10%" style="padding-left: 10px;">주문금액 <br>
                              결제수단
                           </td>
                           <td width="20%"><span id="gray_color">&nbsp;
                                 ${total_price}원</span> <br> <span
                              id="gray_color">&nbsp; ${myOrderDetail[0].pay_method}</span>
                           <td width="10%" align=right>은행 <br> 할부기간
                           </td>
                           <td colspan="3"><span id="gray_color">&nbsp;
                                 ${myOrderDetail[0].card_com_name}</span> <br> <span
                              id="gray_color">&nbsp;
                                 ${myOrderDetail[0].card_pay_month}</span></td>
                        </tr>

                        <!-- 환불 정보 -->
                        <tr>
                           <td colspan="6"><span id="mypage_info_title">환불 정보</span></td>
                        </tr>

                        <tr>
                           <td colspan="2" style="padding-left: 10px;">예금주 &nbsp; <input type="text"
                              size="13" name="account_holder" class="cancel_inputbox"
                              required> <br> 취소사유 <select name="cancel_reason"
                              class="cancel_inputbox" required>
                                 <option selected>단순변심</option>
                                 <option>응대미흡/불친절</option>
                                 <option>서비스부족</option>
                                 <option>가격변동</option>
                                 <option>판매자책임</option>
                                 <option>기타</option>
                           </select>
                           </td>

                           <td colspan="3" style="padding-left:20px;">&nbsp;&nbsp; 환불은행
                              <select name="account_bank"
                              class="cancel_inputbox" required>
                                 <option selected>선택</option>
                                 <option>신한은행</option>
                                 <option>KB국민은행</option>
                                 <option>IBK기업은행</option>
                                 <option>NH농협은행</option>
                                 <option>우리은행</option>
                                 <option>KDB산업은행</option>
                                 <option>KEB하나은행</option>
                                 <option>SH수협은행</option>
                                 <option>SC제일은행</option>
                                 <option>한국씨티은행</option>
                                 <option>우체국</option>
                                 <option>토스뱅크</option>
                                 <option>카카오뱅크</option>
                                 <option>케이뱅크</option>
                           </select> <br> &nbsp;&nbsp; 계좌번호 <input type="text" size="15"
                              name="account_number" class="cancel_inputbox"
                              placeholder="-없이 숫자로만 입력" required>
                           </td>

                           <td align=right style="padding-right:10px; font-size:18px;">환불 예상금액 <span id="red_color">&nbsp;
                                 <fmt:formatNumber value="${myOrderDetail[0].refund_price}" type="number" />원</span>
                                 <input type="hidden" name="refund_price" value="${myOrderDetail[0].refund_price}">
                           </td>
                        </tr>
                     </table>
                     
                     <!-- 주문 번호 -->
                     <input type="hidden" name="order_id" value="${myOrderDetail[0].order_id}">
                     
                     <div id="align_center">
                       <input type="submit" class="submit_btn" value="환불신청" onclick="alert('환불 신청이 완료되었습니다.')" >

                        <div style="margin-top:10px; display:inline-block;">
                           <a href="${contextPath}/mypage/myOrderDetail.do?order_id=${myOrderDetail[0].order_id}" style="line-height:32px;">
                           <span class="submit_btn2" style="padding:10px 95px 10px 95px;">돌아가기</span></a>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </form>
</body>
</html>