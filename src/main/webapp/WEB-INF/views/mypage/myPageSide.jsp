<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div id="mypage_sidebar">
		<ul>
			<li style="font-size:17px; color:#0F0573;">
				마이페이지
			</li>
			<div id="mypage_hr"></div>
			
			<li>
				<a href="${contextPath}/mypage/myOrderList.do?member_id=${memberInfo.member_id}&chapter=1&order_state=&firstDate=&secondDate=&text_box=">결제내역 조회</a>
			</li>
			<li>
				<a href="${contextPath}/mypage/myStackList.do?chapter=1&member_id=${memberInfo.member_id}">적립금 조회</a>
			</li>
			<li>
				<a href="${contextPath}/mypage/myArticleList.do?&member_id=${memberInfo.member_id}">게시글 관리</a>
			</li>
			<li>
				<a href="${contextPath}/mypage/myReviewList.do?member_id=${memberInfo.member_id}&chapter=1&firstDate=&secondDate=&text_box=">이용후기 관리</a>
			</li>
			<li>
				<a href="${contextPath}/mypage/myQnaList.do">Q&A 관리</a>
			</li>
			<li>
				<a href="${contextPath}/mypage/myPageModify.do?member_id=${memberInfo.member_id}">회원정보 수정</a>
			</li>
		</ul>
	</div>
</body>
</html>