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

	// 사진 미리보기 경로
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
					<form name="frmNotice" method="post" action="${contextPath}"
						enctype="multipart/form-data">

						<table style="width: 100%">
							<tr>
								<!-- 글 제목 -->
								<td align=left><input type="text" 
									value="${notice.notice_title}" name="notice_title"
									id="notice_title2" style="margin-left: 15px;" disabled>
									<br></td>

								<!-- 글쓴이 & 작성일 -->
								<td align=right style="padding-top: 90px;"><span
									id="gray_color" style="font-size: 12px;">작성자 &nbsp; <input
										type="text" value="${notice.member_id}" name="member_id"
										id="member_id" disabled
										style="color: #848484; font-size: 12px; background-color: white; border: none;"></span>
									<span id="gray_color"
									style="font-size: 12px; text-align: right; margin-top: 68px; margin-bottom: 5px;">작성시간
										&nbsp;<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${notice.notice_write_date}" />
								</span></td>
							</tr>
						</table>

						<table id="notice_table2" align=center>

							<!-- 글 내용 -->
							<tr>
								<td colspan="3" align=left>${notice.notice_content}</td>
							</tr>

							<!-- 사진 -->
							<c:if test="${not empty notice.notice_image && notice.notice_image != 'null' }">
								<tr>
									<td width="300px"><input type="hidden"
										name="originalFileName" value="${notice.notice_image}" /> <img
										src="${contextPath}/noticeImage.do?notice_no=${notice.notice_no}&notice_image=${notice.notice_image}"
										id="preview" /><br></td>
								</tr>
							</c:if>
							
							<!-- 관리자에게만 보이는 수정/삭제 버튼 -->
							<c:if test="${memberInfo.member_id == 'admin'}">
								<tr>
									<td width="50%" align=right>
										<input type="button" id="modify_btn" value="수정하기"
											onclick="fn_modify_notice('${contextPath}/notice/modNoticeForm.do', ${notice.notice_no})">
									</td>
									<td width="50%" align=left>
										<input type="button" id="delete_btn" value="삭제하기"
										onClick="fn_remove_notice('${contextPath}/notice/removeNotice.do', ${notice.notice_no})">
									</td>
								<tr>
							</c:if>

						</table>
						<div style="text-align:right; margin-top:15px;">
							<a href="${contextPath}/notice/listNotices.do" style="line-height:32px;"><span id="btn_1">목록으로</span></a>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

