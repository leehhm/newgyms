<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/customer.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
	
	// 사진 미리보기 경로
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#event_detail_preview').attr('src', e.target.result);
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
					<form name="frmEvent" method="post" action="${contextPath}"
						enctype="multipart/form-data">

						<table style="width: 100%">
							<tr>
								<!-- 글 제목 -->
								<td width="80%">
									<input type="text" 
									value="${event.event_title}" name="event_title" size="40"
									id="event_title2" style="margin-left: 15px;" disabled>
									<br></td>

								<!-- 작성일 -->
								<td align=right width="20%" style="padding-top: 90px;">
									<span id="gray_color"
									style="font-size: 12px; text-align: right; margin-top: 68px; margin-bottom: 5px;">작성시간
										&nbsp;<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${event.event_write_date}" />
								</span></td>
							</tr>
						</table>

						<table id="event_table2" align=center>
							<c:if test="${not empty event.event_image && event.event_image != 'null' }">
								<!-- 이미지 -->
								<tr>
									<td rowspan="5" width="15%" style="border:none;">
										<input type="hidden"
										name="originalFileName" value="${event.event_image}" /> <img
										src="${contextPath}/eventImage.do?event_no=${event.event_no}&event_image=${event.event_image}"
										id="event_detail_preview" /><br>
									</td>
								</tr>
							</c:if>
							
							<!-- 상품명 및 사업장명 -->
							<tr>
								<td width="8%">
									상품명
								</td> 
								<td width="12%">
									<a href="/newgyms/product/productDetail.do?product_id=${event.product_id}"><span id="navy_color" style="font-size:12px;">${event.product_name}</span></a>
								</td>
								<td width="7%">
									사업장명
								</td>
								<td width="13%">
									 <a href="#"><span id="navy_color" style="font-size:12px;">${event.center_name}</span></a>
								</td>
							</tr>	
							
							<!-- 이벤트 기간 -->							
							<tr>
								<td>
									이벤트 기간
								</td>
								<td colspan="3">
									<span id="gray_color" style="font-size:12px;"><fmt:formatDate pattern="yyyy-MM-dd" value="${event.event_start_date}" /> ~ <fmt:formatDate pattern="yyyy-MM-dd" value="${event.event_end_date}" /></span>
								</td>
							</tr>
							
							<!-- 이벤트 내용 -->
							<tr>
								<td style="border:none;">
									설명
								</td>
								<td colspan="3" align=left style="border:none;">${event.event_content}</td>
							</tr>

						</table>
						
						<!-- 게시글 작성자에게만 보이는 수정/삭제 버튼 -->
							<c:if test="${memberInfo.member_id == event.member_id}">
							<div id="align_center">
								<input type="button" id="modify_btn" value="수정하기"
									onclick="fn_modify_event('${contextPath}/event/modEventForm.do', ${event.event_no})">
									
								<input type="button" id="delete_btn" value="삭제하기"
								onClick="fn_remove_event('${contextPath}/event/removeEvent.do', ${event.event_no})">
							</div>
							</c:if>
						<div style="text-align:right; margin-top:15px;">
							<a href="${contextPath}/event/listEvents.do" style="line-height:32px;"><span id="btn_1">목록으로</span></a>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

