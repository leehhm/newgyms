<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 마이페이지 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
				<div id="contain_right">
					<p id="mypage_order_title">회원정보 수정</p>
					<form action="${contextPath}/mypage/myDetailInfo.do" method="post">
						<div style="margin-top: 70px; text-align:center;">
							<span id="gray_color">회원님의 정보를 안전하게 보호하기 위해 비밀번호를 입력해주세요.</span>
						</div>

							<table id="mypage_info_table">

								<!-- 아이디 -->
								<tr>
									<td width="50%" align=right>
										<p class="inline">아이디 &nbsp;&nbsp;</p>
									</td>
									<td width="50%" align=left><input name="member_id" id="member_id"
										class="mypage_inputbox" type="text" size="15" maxlength="20"
										value="${memberInfo.member_id}" required readonly></td>
								</tr>

								<!-- 비밀번호 -->
								<tr>
									<td align=right>
										<p class="inline">비밀번호 &nbsp;&nbsp;</p>
									</td>
									<td><input name="member_pw" id="member_pw"
										class="mypage_inputbox" type="password" size="15"
										maxlength="20" required /></td>
								</tr>

							</table>
						<br>
						<div id="align_center">
							<input type="submit" value="확인" class="submit_btn" align="center">
							<br>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>