<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />
<!-- 카카오 회원가입 -->
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
</head>
<body>
	<div class="con-min-width">
		<div class="con">

			<div id="contain" align=center>
				<p id="join_title" align="center">회원가입</p>
				<form action="${contextPath}/member/joinCheck.do" method="post">
					<table id="join_table">
						<!-- 회원구분 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									회원구분 <span id="red_color">*</span>
								</p>
							</td>
							<td><input type="radio" name="join_type" value="101" checked />
								개인회원<span style="padding-left: 5px"></span> <input type="radio"
								name="join_type" value="102" /> 사업자회원<span
								style="padding-left: 5px"></span></td>
						</tr>

						<!-- 이름 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									이름 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_name" class="join_inputbox"
								type="text" size="19" maxlength="10" id="member_name"
								pattern="[가-힣]{2,10}" required title="이름은 공백없이 한글로 입력해주세요." />
							</td>
						</tr>

						<!-- 주민등록번호 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									주민등록번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_rrn1" class="join_inputbox"
								type="text" size="8" maxlength="6" id="member_rrn1"
								pattern="[0-9]{6}" required title="주민등록번호 앞 6자리를 숫자로 입력해주세요." />
								<span> - </span> <input name="member_rrn2" class="join_inputbox"
								type="password" size="8" maxlength="7" id="member_rrn2"
								pattern="[0-9]{7}" required title="주민등록번호 뒤 7자리를 숫자로 입력해주세요." />
							</td>
						</tr>
					</table>
					<input type="submit" value="회원가입" id="join_btn" align="center">
					<br>
					<br>
					<div style="width: 300px; heigh: 40px;">
						<a id="kakao_join" href="javascript:kakaoLogin();"> <img
							src="https://developers.kakao.com/tool/resource/static/img/button/kakaosync/complete/ko/kakao_login_medium_wide.png"
							alt="카카오 로그인">
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>

