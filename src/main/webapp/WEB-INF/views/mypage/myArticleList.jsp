<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
</head>
<body>
	<form action="${contextPath}/mypage/myArticleList.do" method="get">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 마이페이지 사이드 메뉴 -->
					<div>
						<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
					</div>
					<div id="contain_right">
						<p id="mypage_order_title">게시글 관리</p>
						
						<div style="font-size: 20px; margin-right:50px; margin-bottom:15px; text-align:right;">
							작성한 게시글 : <span id="navy_color">${fn:length(myArticleList)}개</span>
						</div>
						<c:choose>
							<c:when test="${empty myArticleList}">
								<table id="article_detail">
									<tr>
										<th width="20%">번호</th>
										<th width="50%">제목</th>
										<th width="30%">작성일</th>
									</tr>
									<tr>
										<td colspan="3" style="color: blue;">작성한 게시글이 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="article_detail">
									<tr>
										<th width="20%">번호</th>
										<th width="50%">제목</th>
										<th width="30%">작성일</th>
									</tr>
									<c:forEach var="item" items="${myArticleList}" varStatus="j">
										<tr>
											<td>${item.article_no}</td>
											<td><a href="${contextPath}/board/viewArticle.do?article_no=${item.article_no}">${item.board_title}</a></td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.board_write_date}" /></td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>