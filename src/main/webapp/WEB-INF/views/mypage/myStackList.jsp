<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<script>
$(document).ready(function () {
const arr = [1, 2, 3];

const result = arr.reduce(function add(sum, currValue) {
  return sum + currValue;
}, 0);
});
</script>
</head>
<body>
	<form action="${contextPath}/mypage/myStackList.do" method="get">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 마이페이지 사이드 메뉴 -->
					<div>
						<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
					</div>
					<div id="contain_right">
						<p id="mypage_order_title">적립금 조회</p>
						
						<div style="font-size: 20px; margin-right:50px; margin-bottom:15px; text-align:right;">현재 적립금 : <span id="navy_color">${nowPoint}원</span></div>
						<c:choose>
							<c:when test="${empty myStackList}">
								<table id="stack_detail">
									<tr>
										<th width="10%">구분</th>
										<th width="10%">내용</th>
										<th width="10%">적립금</th>
										<th width="10%">일자</th>
									</tr>
									<tr>
										<td colspan="7" style="color: blue;">조회된 적립금이 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="stack_detail">
									<tr>
										<th width="10%">구분</th>
										<th width="10%">내용</th>
										<th width="10%">적립금</th>
										<th width="10%">일자</th>
									</tr>
									<c:forEach var="item" items="${myStackList}" varStatus="j">
										<tr>
											<c:choose>
											<c:when test="${item.point_state=='적립'}">
											<td style="color:#0101DF;">${item.point_state}</td>
											<td>${item.point_name}</td>
											<td>+${item.point_price}</td>
											</c:when>
											<c:otherwise>
											<td style="color:#DF0101;">${item.point_state}</td>
											<td>${item.point_name}</td>
											<td>-${item.point_price}</td>
											</c:otherwise>
											</c:choose>
											<td><fmt:formatDate pattern="yyyy-MM-dd"
													value="${item.point_date}" /></td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div style="text-align: center;">
					<a
						href="${contextPath}/mypage/myStackList.do?chapter=${chapter-1}&member_id=${member_id}">&#60;</a>
					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/5)}"
						step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a
							href="${contextPath}/mypage/myStackList.do?chapter=${section_num}&member_id=${member_id}">${section_num}</a>
					</c:forEach>
					<a
						href="${contextPath}/mypage/myStackList.do?chapter=${chapter+1}&member_id=${member_id}">&#62;</a>
				</div>
			</div>
		</div>
	</form>
</body>
</html>