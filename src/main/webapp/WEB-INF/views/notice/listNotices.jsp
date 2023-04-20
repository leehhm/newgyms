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
<link href="${contextPath}/resources/css/customer.css" rel="stylesheet" />
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 고객센터 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/common/customerSide.jsp" />

				<div id="contain_right">
					<p id="notice_title">공지사항</p>

					<p>총 ${fn:length(noticesList)}건</p>

					<!-- 공지사항 리스트 -->
					<table id="notice_table" align=center>
						<tr>
							<th width="10%">번호</th>
							<th width="40%">제목</th>
							<th width="25%">작성자</th>
							<th width="25%">작성일</th>
						</tr>
						<c:choose>
							<c:when test="${empty noticesList}">
								<tr>
									<td colspan="4" style="padding:50px;">
										<p align="center">
											<b><span style="font-size: 18pt;">등록된 공지사항이 없습니다. 😥😥 </span></b>
										</p>
									</td>
								</tr>
							</c:when>

							<c:when test="${noticesList != null}">

								<c:forEach var="item" items="${noticesList}" varStatus="j">
									<c:set var="i" value="${i+1}" />
									<tr>
										<!-- 글 번호 -->
										<td>${item.notice_no}</td>

										<!-- 제목 -->
										<td>
											<a href="${contextPath}/notice/viewNotice.do?notice_no=${item.notice_no}">${item.notice_title}</a>
										</td>

										<!-- 작성자 -->
										<td>${item.member_id}</td>

										<!-- 작성일 -->
										<td><fmt:formatDate pattern="yyyy-MM-dd"
												value="${item.notice_write_date}" /></td>

									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					
					<!-- 관리자만 글쓰기 가능 -->
					<c:if test="${memberInfo.member_id == 'admin'}">					
					<div align=right style="margin-top: 10px;">
						<a href="${contextPath}/notice/noticeForm.do" style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
					</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

