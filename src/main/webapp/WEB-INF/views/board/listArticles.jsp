<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/community.css" rel="stylesheet" />
<script>
	function fn_articleForm(isLogOn, articleForm, loginForm) {
		if (isLogOn != '' && isLogOn != 'false') {
			location.href = articleForm;
		} else {
			alert("로그인 후 글쓰기가 가능합니다.");
			location.href = loginForm + '?action=/board/articleForm.do';
		}
	}
</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 커뮤니티 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/common/communitySide.jsp" />

				<div id="contain_right">
					<p id="board_title">자유게시판</p>

					<p>총 ${fn:length(articlesList)}건</p>

					<!-- 자유게시판 리스트 -->
					<table id="board_table" align=center>
						<tr>
							<th width="10%">번호</th>
							<th width="40%">제목</th>
							<th width="25%">작성자</th>
							<th width="25%">작성일</th>
						</tr>
						<c:choose>
							<c:when test="${empty articlesList}">
								<tr>
									<td colspan="4" style="padding:50px;">
										<p align="center">
											<b><span style="font-size: 18pt;">등록된 글이 없습니다. 😥😥 </span></b>
										</p>
									</td>
								</tr>
							</c:when>

							<c:when test="${articlesList != null}">

								<c:forEach var="item" items="${articlesList}" varStatus="j">
									<c:set var="i" value="${i+1}" />
									<tr>
										<!-- 글 번호 -->
										<td>${item.article_no}</td>

										<!-- 제목 -->
										<td>
											<a href="${contextPath}/board/viewArticle.do?article_no=${item.article_no}">${item.board_title}</a>
										</td>

										<!-- 작성자 -->
										<td>${item.member_id}</td>

										<!-- 작성일 -->
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${item.board_write_date}" /></td>

									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					<div align=right style="margin-top: 10px;">
						<a
							href="javascript:fn_articleForm('${isLogOn}', '${contextPath}/board/articleForm.do','${contextPath}/member/loginForm.do')"
							style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

