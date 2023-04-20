<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/customer.css" rel="stylesheet" />
<script type="text/javascript">

//사진 미리보기 경로
function readURL(input) {
	if (input.files && input.files[0]) {
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#preview').attr('src', e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}
}

</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 커뮤니티 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/common/customerSide.jsp" />

				<div id="contain_right">
					<p id="event_title">이벤트</p>

					<span>&nbsp; 총 ${fn:length(eventsList)}건</span> <span id="gray_color"
						style="margin-left: 800px; font-size:12px;">
						<a href="${contextPath}/event/listEvents.do">전체</a> &nbsp; 
						<a href="${contextPath}/event/eventSorting.do?sort=ing">진행중</a> &nbsp; 
						<a href="${contextPath}/event/eventSorting.do?sort=end">종료</a>
						</span>

					<!-- 이벤트 리스트 -->
					<div id="event_list">
						<c:choose>
							<c:when test="${empty eventsList}">
								<p align="center">
									<b><span style="font-size: 18pt;">진행중인 이벤트가 없습니다!!
											😛😛</span></b>
								</p>
							</c:when>

							<c:when test="${eventsList != null}">
								<div id="event_grid">
									<c:forEach var="item" items="${eventsList}" varStatus="j">
										<div id="event_item">
											
											<!-- 이벤트 사진 -->
											<a href="${contextPath}/event/viewEvent.do?event_no=${item.event_no}">
												<img
												src="${contextPath}/eventImage.do?event_no=${item.event_no}&event_image=${item.event_image}"
												id="event_preview">
											</a> <br>
											
											<!-- 이벤트 제목 -->
											<div id="event_article">
											<a
												href="${contextPath}/event/viewEvent.do?event_no=${item.event_no}"><span
												style="font-size: 17px;">${item.event_title}</span></a><br>
											<!-- 이벤트 기간 -->
											<span id="gray_color" style="font-size: 11px;"> <fmt:formatDate
													pattern="yyyy-MM-dd" value="${item.event_start_date}" /> ~
												<fmt:formatDate pattern="yyyy-MM-dd"
													value="${item.event_end_date}" />
											</span><br>
											</div>
									
										</div>
									</c:forEach>
								</div>
							</c:when>
						</c:choose>
					</div>
<!--
					2. 타임세일타이머
					<div class="saleTimer">
						<hr style="margin-top: 2px;">
						<p class="timer-title">3/21 오늘의 할인가!</p>
						<div class="timer-text">마감임박!</div>
						<p class="runTimeCon font25">PM 09:00 ~ PM 09:00</p> 
              			<p class="font15 time-title">금일 마감까지 남은 시간</p>
						<div class="time">
							<span class="hours"></span> <span class="col">:</span> <span
								class="minutes"></span> <span class="col">:</span> <span
								class="seconds"></span>
						</div>
						<hr style="margin-bottom: 1px;">
					</div>
  -->
					<!-- 사업자 혹은 관리자만 글쓰기 가능 -->
					<c:choose>
						<c:when test="${memberInfo.join_type == '102'}">
							<div align=right style="margin-top: 10px;">
								<a href="${contextPath}/event/eventForm.do?member_id=${memberInfo.member_id}"
									style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
							</div>
						</c:when>
						<c:when test="${memberInfo.member_id == 'admin'}">
							<div align=right style="margin-top: 10px;">
								<a href="${contextPath}/event/eventForm.do?member_id=${memberInfo.member_id}"
									style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>