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
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	// 글 수정하기
	function fn_modify_notice(url, notice_no) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var notice_no_Input = document.createElement("input");
		notice_no_Input.setAttribute("type", "hidden");
		notice_no_Input.setAttribute("name", "notice_no");
		notice_no_Input.setAttribute("value", notice_no);
		
		form.appendChild(notice_no_Input);
		document.body.appendChild(form);
		form.submit();
	}
	
	// 글 삭제하기
	function fn_remove_notice(url, notice_no) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var notice_no_Input = document.createElement("input");
		notice_no_Input.setAttribute("type", "hidden");
		notice_no_Input.setAttribute("name", "notice_no");
		notice_no_Input.setAttribute("value", notice_no);

		form.appendChild(notice_no_Input);
		document.body.appendChild(form);
		form.submit();
	}

</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 고객센터 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />

				<div id="contain_right">
					<p id="notice_title">공지사항 관리</p>
					<p style="font-size:15px;">총 ${fn:length(adminNoticeList)}건</p>

					<!-- 공지사항 리스트 -->
					<table id="admin_notice_table" align=center>
						<tr>
							<th width="10%">번호</th>
							<th width="40%">제목</th>
							<th width="10%">작성자</th>
							<th width="20%">작성일</th>
							<th width="20%">선택</th>
						</tr>
						<c:choose>
							<c:when test="${empty adminNoticeList}">
								<tr>
									<td colspan="5" style="padding:50px;">
										<p align="center">
											<b><span style="font-size: 18pt;">등록된 공지사항이 없습니다. 😥😥 </span></b>
										</p>
									</td>
								</tr>
							</c:when>

							<c:when test="${adminNoticeList != null}">

								<c:forEach var="item" items="${adminNoticeList}" varStatus="j">
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
										
										<!-- 수정 및 삭제 버튼 -->
										<td>
											<input type="button" id="admin_modify_btn" value="수정하기"
												onclick="fn_modify_notice('${contextPath}/notice/modNoticeForm.do', ${item.notice_no})">
											<input type="button" id="admin_delete_btn" value="삭제하기"
												onClick="fn_remove_notice('${contextPath}/admin/notice/removeNotice.do', ${item.notice_no})">
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					
					<!-- 글 쓰기 버튼 -->
					<div align=right style="margin-top: 10px;">
						<a href="${contextPath}/notice/noticeForm.do" style="line-height: 32px;"> <span id="btn_1">글 쓰기</span></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

