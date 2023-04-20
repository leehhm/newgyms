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
<link href="${contextPath}/resources/css/customer.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css" rel="stylesheet" />
<script type="text/javascript">

// 글 수정하기
function fn_modify_event(url, event_no) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", url);
	var event_no_Input = document.createElement("input");
	event_no_Input.setAttribute("type", "hidden");
	event_no_Input.setAttribute("name", "event_no");
	event_no_Input.setAttribute("value", event_no);
	
	form.appendChild(event_no_Input);
	document.body.appendChild(form);
	form.submit();
}

// 글 삭제하기
function fn_remove_event(url, event_no) {
	var form = document.createElement("form");
	form.setAttribute("method", "post");
	form.setAttribute("action", url);
	var event_no_Input = document.createElement("input");
	event_no_Input.setAttribute("type", "hidden");
	event_no_Input.setAttribute("name", "event_no");
	event_no_Input.setAttribute("value", event_no);

	form.appendChild(event_no_Input);
	document.body.appendChild(form);
	form.submit();
}

</script>
</head>
<body>
	<form action="${contextPath}/owner/event/ownerEventList.do" method="get">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 사업자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
					
					<div id="contain_right">
						<p id="mypage_order_title">이벤트 관리</p>
						
						<div style="font-size: 20px; margin-right:50px; margin-bottom:15px; text-align:right;">
							게시한 이벤트 : <span id="navy_color">${fn:length(ownerEventList)}개</span>
						</div>
						<c:choose>
							<c:when test="${empty ownerEventList}">
								<table id="article_detail">
									<tr>
										<th width="10%">번호</th>
										<th width="35%">제목</th>
										<th width="10%">상태</th>
										<th width="20%">등록일</th>
										<th width="20%">선택</th>
									</tr>
									<tr>
										<td colspan="5" style="color: blue;">진행중인 이벤트가 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="article_detail">
									<tr>
										<th width="10%">번호</th>
										<th width="35%">제목</th>
										<th width="10%">상태</th>
										<th width="15%">등록일</th>
										<th width="25%">선택</th>
									</tr>
									<c:forEach var="item" items="${ownerEventList}" varStatus="j">
										<tr>
											<td>${item.event_no}</td>
											
											<td>
												<a href="${contextPath}/event/viewEvent.do?event_no=${item.event_no}">${item.event_title}</a> <br>
												<p id="gray_color" style="font-size: 12px; line-height:10px; padding-bottom:10px;">
												<fmt:formatDate	pattern="yyyy-MM-dd" value="${item.event_start_date}" /> ~ <fmt:formatDate pattern="yyyy-MM-dd"	value="${item.event_end_date}" />
											</p>
											</td>
											
											<td>
												${item.event_ing}
											</td>
											
											<td><fmt:formatDate pattern="yyyy-MM-dd" value="${item.event_write_date}" /></td>
											
											<td>
												<!-- 수정 -->
												<input type="button" id="owner_modify_btn" value="수정하기"
													onclick="fn_modify_event('${contextPath}/event/modEventForm.do', ${item.event_no})">
												<!-- 삭제 -->
												<input type="button" id="owner_delete_btn" value="삭제하기"
													onClick="fn_remove_event('${contextPath}/owner/event/removeEvent.do', ${item.event_no})">
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
						<div align=right style="margin-top: 10px;">
								<a href="${contextPath}/event/eventForm.do?member_id=${memberInfo.member_id}"
									style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>