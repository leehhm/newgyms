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
   <div class="admin_sidebar">
      <ul>
         <li style="font-size:17px; color:#0F0573;">
            관리자페이지
         </li>
         <div class="adminpage_hr"></div>
         
         <li>
            <a href="${contextPath}/admin/member/adminMemberList.do?chapter=1">회원 관리</a>
         </li>
         <li>
            <a href="${contextPath}/admin/product/adminProductList.do?chapter=1">상품 관리</a>
         </li>
         <li>
            <a href="${contextPath}/admin/order/adminOrderList.do?chapter=1&order_state=&firstDate=&secondDate=&text_box=">주문/결제 관리</a>
         </li>
         
         <li>
            <a href="${contextPath}/admin/board/adminArticleList.do">자유게시판 관리</a>
         </li>
         <li>
        	 <a href="${contextPath}/admin/review/adminReviewList.do?&chapter=1&firstDate=&secondDate=&text_box=">이용후기 관리</a>
         </li>
         <li>
            <a href="${contextPath}/admin/notice/adminNoticeList.do">공지사항 관리</a>
         </li>
         <li>
            <a href="${contextPath}/admin/event/adminEventList.do">이벤트 관리</a>
         </li>
         <li>
            <a href="${contextPath}/admin/qna/adminQnaList.do">Q&A 관리</a>
         </li>
      </ul>
   </div>
</body>
</html>