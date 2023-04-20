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
	<div id="community_sidebar">
		<ul>
			<li style="font-size:17px; color:#0F0573;">
				커뮤니티
			</li>
			<div id="community_hr"></div>
			
			<li>
				<a href="${contextPath}/board/listArticles.do">자유게시판</a>
			</li>
			<li>
				<a href="${contextPath }/review/reviewList.do">이용후기</a>
			</li>
		</ul>
	</div>
</body>
</html>