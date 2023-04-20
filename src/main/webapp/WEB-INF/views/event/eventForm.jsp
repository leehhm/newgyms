<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<p id="event_title">이벤트 글 쓰기</p>
					<form name="eventForm" method="post"
						action="${contextPath}/event/addNewEvent.do"
						enctype="multipart/form-data">

						<table id="event_table" align=center>
							<!-- 제목 -->
							<tr>
								<td width="10%"><span id="event_textbox">제목</span></td>
								<td colspan="3" align=left><input type="text" size="91"
									class="event_inputbox" maxlength="50" name="event_title"
									placeholder="제목을 입력하세요." required /></td>
							</tr>

							<c:choose>
								<c:when test="${memberInfo.member_id != 'admin' }">
									<tr>
										<td><span id="event_textbox">아이디</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${memberInfo.member_id}" name="member_id" readonly /></td>
										<td width="10%" align=right><span id="event_textbox">상품명
												&nbsp;</span></td>
										<td align=left><c:choose>
												<c:when test="${not empty productList}">
													<select name="product_name" class="event_inputbox">
														<c:forEach var="item" items="${productList}" varStatus="j">
															<option value="${item.product_name}">${item.product_name}</option>
														</c:forEach>
													</select>
												</c:when>
												<c:otherwise>
													<span style="font-size: 12px; color: #0F0573;">사업자님~
														상품을 먼저 등록해주세요^^</span>
												</c:otherwise>
											</c:choose></td>
									</tr>

									<tr>
										<td><span id="event_textbox">사업장명</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${memberInfo.center_name}" name="center_name" readonly
											required /></td>
										<td align=right><span id="event_textbox">기간 &nbsp;</span></td>
										<td align=left><input type="date" name="event_start_date"
											class="event_inputbox" required> ~ <input type="date"
											name="event_end_date" class="event_inputbox" required>
										</td>

									</tr>
								</c:when>

								<c:otherwise>
									<tr>
										<td><span id="event_textbox">아이디</span></td>
										<td align=left><input type="text" size="10"
											class="event_inputbox" maxlength="20"
											value="${memberInfo.member_id}" name="member_id" readonly /></td>

										<td width="5%"><span id="event_textbox">기간</span>
										</td>
										<td align=left><input type="date"
											name="event_start_date" class="event_inputbox" required>
											~ <input type="date" name="event_end_date"
											class="event_inputbox" required></td>
									</tr>
								</c:otherwise>
							</c:choose>

							<!-- 내용 -->
							<tr>
								<td><span id="event_textbox">내용</span></td>
								<td colspan="3" align=left><textarea name="event_content"
										rows="20" cols="90" maxlength="4000"
										placeholder="내용을 입력하세요. (최대 4000자)"
										style="margin-top: 10px; margin-bottom: 20px; padding: 15px; border: 1px solid #D8D8D8"
										required></textarea></td>
							</tr>

							<!-- 이미지 첨부 -->
							<tr>
								<td><span id="event_textbox">사진</span></td>
								<td width="300px"><img id="preview"
									src="${contextPath}/resources/image/jss.png" /></td>
								<td align=left style="padding-left: 20px;" colspan="2"><input
									type="file" name="event_image" onchange="readURL(this);"></td>
							</tr>
						</table>
						<div align=center>
							<input type="submit" value="등록하기" class="submit_btn">
							<button class="submit_btn2" onclick="backToList(this.form)">목록으로</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

