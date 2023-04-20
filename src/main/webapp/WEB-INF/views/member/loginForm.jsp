<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />

<c:if test='${not empty message}'>
	<script>
		window.onload = function() {
			result();
		}

		function result() {
			alert("아이디나 비밀번호가 올바르지 않습니다.");
		}
	</script>
</c:if>

<script src="https://developers.kakao.com/sdk/js/kakao.js"
	charset="utf-8"></script>
<script type="text/javascript">
	Kakao.init("b72869301ce448407f587e4c23f08553");
	Kakao.isInitialized();

	function kakaoLogin() {
		Kakao.Auth.authorize({
			redirectUri : 'http://localhost:8080/newgyms/member/kakaoLogin.do',
		});
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<p id="join_title" align="center">로그인</p>
			<form action="${contextPath}/member/login.do" method="post">
				<div align=center>
					<table id="login_table">
					
						<!-- 회원구분 -->
						<tr>
							<td><input type="radio" name="join_type" value="101" checked />
								개인회원<span style="padding-left: 5px"></span> <input type="radio"
								name="join_type" value="102" /> 사업자회원<span
								style="padding-left: 5px"></span></td>
						</tr>
						<!-- 아이디 -->
						<tr>
							<td><input id="member_id" name="member_id"
								class="login_inputbox" type="text" size="26" maxlength="20"
								placeholder="아이디" required /></td>
						</tr>

						<!-- 비밀번호 -->
						<tr>
							<td><input id="member_pw" name="member_pw"
								class="login_inputbox" type="password" size="26" maxlength="20"
								placeholder="비밀번호" required /></td>
						</tr>
						
					</table>
					<br> <input type="submit" value="로그인" id="join_btn"
						align="center"> <br>
					<br>
					<div style="width: 300px; heigh: 40px;">
						<a id="kakao_join" href="javascript:kakaoLogin();"> <img
							src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_wide.png"
							alt="카카오 로그인">
						</a>
					</div>
					<br> <a href="${contextPath}/member/searchIdForm.do">아이디
						찾기</a> | <a href="${contextPath}/member/searchPwForm.do">비밀번호 찾기</a> |
					<a href="${contextPath}/member/joinCheck.do">회원가입</a>

				</div>
			</form>
		</div>
	</div>
</body>
</html>