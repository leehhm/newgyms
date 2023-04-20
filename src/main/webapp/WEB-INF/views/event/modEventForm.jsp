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
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function backToList(obj) {
		obj.action = "${contextPath}/event/listEvents.do";
		obj.submit();
	}

	var cnt = 1;
	function fn_addFile() {
		$("#d_file.").append(
				"<br>" + "<input type='file' name='file"+cnt+"' />");
		cnt++;
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
					<p id="event_title">이벤트 글 수정하기</p>
					<form method="post" action="${contextPath}/event/updateEvent.do"
						enctype="multipart/form-data">
						<!-- 글 번호 -->
						<input type="hidden" name="event_no" value="${event.event_no}">
						<table id="event_table" align=center>
							<!-- 제목 -->
							<tr>
								<td><span id="event_textbox">제목</span></td>
								<td colspan="3" align=left><input type="text" size="91"
									class="event_inputbox" maxlength="50" name="event_title"
									placeholder="제목을 입력하세요." value="${event.event_title}" /></td>
							</tr>

							<c:choose>
								<c:when test="${memberInfo.member_id != 'admin' }">
									<tr>
										<td><span id="event_textbox">아이디</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${event.member_id}" name="member_id" readonly /></td>

										<td width="10%" align=right><span id="event_textbox">상품명
												&nbsp;</span></td>
										<td align=left><span class="event_inputbox">${event.product_name}</span></td>
									</tr>

									<tr>
										<td><span id="event_textbox">사업장명</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${memberInfo.center_name}" name="center_name" readonly
											required /></td>
										<td align=right><span id="event_textbox">기간 &nbsp;</span></td>
										<td align=left>
											<span class="event_inputbox"> <fmt:formatDate
													pattern="yyyy-MM-dd" value="${event.event_start_date}" />
												~ <fmt:formatDate pattern="yyyy-MM-dd"
													value="${event.event_end_date}" />
										</span></td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr>
										<td><span id="event_textbox">아이디</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${event.member_id}" name="member_id" readonly /></td>

										<td width="5%" rowspan="2"><span id="event_textbox">기간</span>
										</td>
										<td align=left rowspan="2"><span class="event_inputbox">
												<fmt:formatDate pattern="yyyy-MM-dd"
													value="${event.event_start_date}" /> ~ <fmt:formatDate
													pattern="yyyy-MM-dd" value="${event.event_end_date}" />
										</span></td>
									</tr>

									<tr>
										<td><span id="event_textbox">사업장명</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${memberInfo.center_name}" name="center_name" readonly
											required /></td>
									</tr>
								</c:otherwise>
							</c:choose>

							<!-- 내용 -->
							<tr>
								<td><span id="event_textbox">내용</span></td>
								<td colspan="3" align=left><textarea name="event_content"
										rows="20" cols="90" maxlength="4000"
										placeholder="내용을 입력하세요. (최대 4000자)"
										style="margin-top: 10px; margin-bottom: 20px; padding: 15px; border: 1px solid #D8D8D8">${event.event_content}</textarea></td>
							</tr>

							<!-- 이미지 첨부 -->
							<tr>
								<td><span id="event_textbox">사진</span></td>
								<td width="300px"><c:choose>
										<c:when
											test="${not empty event.event_image && event.event_image != 'null' }">
											<img id="preview"
												src="${contextPath}/eventImage.do?event_no=${event.event_no}&event_image=${event.event_image}" />
										</c:when>
										<c:otherwise>
											<img id="preview"
												src="${contextPath}/resources/image/jss.png" />
										</c:otherwise>
									</c:choose></td>

								<td colspan="2" align=left style="padding-left: 20px;">
									<input type="file" name="event_image" onchange="readURL(this);">
									<input type="hidden" name="originalFileName" value="${event.event_image}">
								</td>
							</tr>
						</table>
						<div align=center>
							<input type="submit" value="수정하기" class="submit_btn">
							<button class="submit_btn2" onclick="backToList(this.form)">돌아가기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

