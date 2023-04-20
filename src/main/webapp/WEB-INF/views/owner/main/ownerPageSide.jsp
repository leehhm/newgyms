<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>
   <div class="owner_sidebar">
      <ul>
         <li style="font-size:17px; color:#0F0573;">
            사업자페이지
         </li>
         <div class="ownerpage_hr"></div>
         
         <li>
            <a href="${contextPath}/owner/main/ownerPageIntroModifyForm.do?member_id=${memberInfo.member_id}">사업장 관리</a>
         </li>
         <li>
            <a href="${contextPath}/owner/product/ownerProductList.do?member_id=${memberInfo.member_id}&chapter=1">상품 관리</a>
         </li>
         <li>
            <a href="${contextPath}/owner/order/ownerOrderList.do?center_name=${memberInfo.center_name}&chapter=1">주문/결제 관리</a>
         </li>
         <li>
             <a href="${contextPath}/owner/review/orderReviewList.do?center_name=${memberInfo.center_name}&chapter=1&firstDate=&secondDate=&text_box=">이용후기 관리</a>
         </li>
         <li>
            <a href="${contextPath}/owner/qna/ownerQnaList.do?member_id=${memberInfo.member_id}">Q&A 관리</a>
         </li>
         <li>
            <a href="${contextPath}/owner/event/ownerEventList.do?member_id=${memberInfo.member_id}">이벤트 관리</a>
         </li>
         <li>
            <a href="${contextPath}/owner/main/ownerPageModify.do">회원정보 수정</a>
         </li>
      </ul>
   </div>
</body>
</html>