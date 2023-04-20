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
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

//글 삭제하기
function fn_remove_article(url, article_no) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", url);
	var article_no_Input = document.createElement("input");
	article_no_Input.setAttribute("type", "hidden");
	article_no_Input.setAttribute("name", "article_no");
	article_no_Input.setAttribute("value", article_no);

	form.appendChild(article_no_Input);
	document.body.appendChild(form);
	form.submit();
}

</script>
</head>
<body>
	<form action="${contextPath}/mypage/adminArticleList.do" method="get">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 마이페이지 사이드 메뉴 -->
					<div>
						<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />
					</div>
					<div id="contain_right">
						<p id="mypage_order_title">자유게시판 관리</p>
						
						<p style="font-size:15px;">총 ${fn:length(adminArticleList)}건</p>
						<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>
						
						<c:choose>
							<c:when test="${empty adminArticleList}">
								<table id="article_detail">
									<tr>
										<th width="10%">번호</th>
										<th width="35%">제목</th>
										<th width="20%">작성자</th>
										<th width="20%">작성일</th>
										<th width="10%">선택</th>
									</tr>
									<tr>
										<td colspan="5" style="color: blue;">작성한 게시글이 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="article_detail">
									<tr>
										<th width="10%">번호</th>
										<th width="35%">제목</th>
										<th width="20%">작성자</th>
										<th width="20%">작성일</th>
										<th width="10%">선택</th>
									</tr>
									<c:forEach var="item" items="${adminArticleList}" varStatus="j">
										<tr>
											<td>${item.article_no}</td>
											<td><a href="${contextPath}/board/viewArticle.do?article_no=${item.article_no}">${item.board_title}</a></td>
											<td>${item.member_id}</td>
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.board_write_date}" /></td>
											<td>
												<input type="button" id="admin_delete_btn" value="삭제하기"
												onClick="fn_remove_article('${contextPath}/admin/board/removeArticle.do', ${item.article_no})">
											</td>
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