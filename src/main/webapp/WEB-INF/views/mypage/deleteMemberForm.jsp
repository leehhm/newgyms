<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
</head>
<div class="con-min-width">
	<div class="con">
		<div id="contain" align=center>
			<!-- 마이페이지 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
			<div id="contain_right">
				<p id="mypage_modify_title">회원탈퇴 안내</p>
				<form action="${contextPath}/mypage/deleteMember.do" method="post">

					<table id="mypage_delete_table">
						<!-- 회원탈퇴 안내 -->
						<tr>
							<td width="20%">
								<p class="inline" style="font-size:20px;">회원탈퇴 안내</p>
							</td>
							<td width="80%"><p class="inline" id="navy_color">탈퇴 시 아래 사항을 숙지해주시기 바랍니다.</td>
						</tr>

						<!-- 내용 -->
						<tr>
							<td></td>
							<td style="padding-top:10px;">
								1. 회원 탈퇴 시 고객님의 정보는 전자상거래 등에서 소비자 보호에 관한 <br>
								&nbsp;&nbsp;&nbsp;법률에 의거한 고객정보 보호정책에 따라 관리됩니다.<br>
								2. 탈퇴 시 고객님께서 보유하셨던 <span id="red_color">쿠폰 및 적립금은 모두 삭제됩</span>니다.<br>
								3. 회원 탈퇴 후 <span id="red_color">3개월간 재가입이 불가능</span>합니다.<br><br>
								
								불편하셨던 점이나 불만사항을 알려주시면 적극 반영해서 이용하시는 고객님들의 불편함을 해결하려 노력하는
								 <span id="navy_color">NEW</span><span style="color:#F9C200;">GYM</span><span id="navy_color">S</span>가 되겠습니다.
							</td>
						</tr>
	
						<!-- 아이디 & 비밀번호 -->
						<tr>
							<td style="padding-top:50px; padding-bottom:30px;">
								<p class="inline" style="font-size:20px;"> 비밀번호 입력</p>
							</td>
							<td style="padding-top:50px; padding-bottom:30px;">
							<input name="member_id" id="member_id" type="hidden" value="${memberInfo.member_id}">
							<input name="member_pw" id="member_pw"
								class="mypage_modify_inputbox" type="password" size="25" maxlength="20" required placeholder="현재 비밀번호를 입력해주세요." /></td>
						</tr>
					</table>

					<div style="padding-right: 165px;">
						<input type="submit" class="submit_btn" value="탈퇴하기" onclick="javascript:alert('지금까지 NEWGYMS를 이용해주셔서 감사합니다.');" >
					</div>

				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
