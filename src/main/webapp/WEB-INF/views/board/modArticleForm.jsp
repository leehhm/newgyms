<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/community.css" rel="stylesheet" />
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
		obj.action = "${contextPath}/board/listArticles.do";
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
				<jsp:include page="/WEB-INF/views/common/communitySide.jsp" />

				<div id="contain_right">
					<p id="board_title">글 수정</p>
					<form method="post" action="${contextPath}/board/updateArticle.do"
						enctype="multipart/form-data">
						<!-- 글 번호 -->
						<input type="hidden" name="article_no" value="${article.article_no}">
						<table id="article_table" align=center>
							<!-- 제목 -->
							<tr>
								<td width="10%"><span id="board_textbox">제목</span></td>
								<td colspan="3" align=left><input type="text" size="91"
									class="board_inputbox" maxlength="50" name="board_title"
									placeholder="제목을 입력하세요." value="${article.board_title}" /></td>
							</tr>

							<!-- 작성자 아이디 -->
							<tr>
								<td><span id="board_textbox">아이디</span></td>
								<td colspan="3" align=left><input type="text" size="10"
									class="board_inputbox" maxlength="20"
									value="${memberInfo.member_id}" name="member_id" readonly /></td>
							</tr>

							<!-- 내용 -->
							<tr>
								<td><span id="board_textbox">내용</span></td>
								<td colspan="3" align=left><textarea name="board_content"
										rows="20" cols="90" maxlength="4000"
										placeholder="내용을 입력하세요. (최대 4000자)"
										style="margin-top: 10px; margin-bottom: 20px; padding: 15px; border: 1px solid #D8D8D8">${article.board_content}</textarea></td>
							</tr>

							<!-- 이미지 첨부 -->
							<tr>
								<td><span id="board_textbox">사진</span></td>
								<td width="300px">
								<c:choose>
									<c:when
										test="${not empty article.board_image && article.board_image != 'null' }">
										<img id="preview"
											src="${contextPath}/boardImage.do?article_no=${article.article_no}&board_image=${article.board_image}" />
									</c:when>
									<c:otherwise>
									<img id="preview"
											src="${contextPath}/resources/image/jss.png" />
									</c:otherwise>
								</c:choose>
								</td>

								<td align=left style="padding-left: 20px;">
									<input type="file" name="board_image" onchange="readURL(this);" accept="image/*">
									<input type="hidden" name="originalFileName" value="${article.board_image}">
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

