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
	<div id="customer_sidebar">
		<ul>
			<li style="font-size:17px; color:#0F0573;">
				고객센터
			</li>
			<div id="customer_hr"></div>
			
			<li>
				<a href="${contextPath}/notice/listNotices.do">공지사항</a>
			</li>
			<li>
				<a href="${contextPath}/event/listEvents.do">이벤트</a>
			</li>
			<li>
				<a href="${contextPath}/qna/listQnas.do">Q & A</a>
			</li>
		</ul>
	</div>
</body>
</html>