<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />
<script type="text/javascript">

//비밀번호 조합 검사 
function pwRegexp() {
	let member_pw = document.getElementById("member_pw").value;
	let pw_help = document.getElementById("pw_help");
	
	let num = member_pw.search(/[0-9]/); // 비밀번호 중 0부터 9까지 num에 넣음
	let eng = member_pw.search(/[a-z]/); // 비밀번호 중 a부터 z까지 eng에 넣음
	let spe = member_pw.search(/[`~!@#$%^&*|\\\'\":;\/?]/);
	// 비밀번호 중 특수문자를 spe에 넣음
	
	if (member_pw.length < 8 || member_pw.length > 20) {
		pw_help.style.color = "red";
		pw_help.innerHTML="비밀번호는 8자리에서 20자리 이내로 입력해주세요. :("
		return false;
	} else if (member_pw.search(/\s/) != -1) {
		pw_help.style.color = "red";
		pw_help.innerHTML="비밀번호에 공백은 입력할 수 없습니다. :("
		return false;
	} else if (num == -1 || eng == -1 || spe == -1) {
		pw_help.style.color = "red";
		pw_help.innerHTML="특수문자, 영문자, 숫자를 혼합하여 입력해주세요. :("
		return false;
	} else {
		pw_help.style.color = "green";
		pw_help.innerHTML="사용 가능한 비밀번호입니다. :)"
		return true;
	}
}

// 비밀번호와 비밀번호 확인이 동일한지 확인
function pwCheck() {
	let member_pw = document.getElementById("member_pw").value;
	let member_pw_confirm = document.getElementById("member_pw_confirm").value;
	let pw_check = document.getElementById("pw_check");
	
	// 비밀번호와 비밀번호 확인란이 같지 않으면
	if (member_pw != member_pw_confirm) {
		pw_check.style.color = "red";
		pw_check.innerHTML="비밀번호가 다릅니다. :(";
		return false;
	} else {
		pw_check.style.color = "green";
		pw_check.innerHTML="비밀번호가 일치합니다. :)";
		return true;
	}
}

</script>
</head>
<body>
<div class="con-min-width">
<div class="con">

	<div id="contain" align=center>
		<p id="join_title" align=center>비밀번호 재설정</p>
		<form action="${contextPath}/member/newPw.do" method="post">
			<p id="join_notice">새로운 비밀번호를 입력해주세요.</p>
			
			<table id="join_table">
				<!-- 비밀번호 -->
				<tr>
					<td>	
						<p class="inline" class="join_textbox">비밀번호 <span id="red_color">*</span></p>
					</td>
					<td>
						<input name="member_id" id="member_id" value="${member_id}" class="join_inputbox" type="hidden" size="25" maxlength="20" />			
						<input name="member_pw" id="member_pw" class="join_inputbox" type="password" size="25" maxlength="20" onkeyup="pwRegexp()" placeholder="영문, 숫자, 특수문자 혼합 8자리 이상" required />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<span id="pw_help"></span>
					</td>
				</tr>
		
				<!-- 비밀번호 확인 -->
				<tr>
					<td>	
						<p class="inline" class="join_textbox">비밀번호 확인 <span id="red_color">*</span></p>
					</td>
					<td>
						<input name="member_pw_confirm" id="member_pw_confirm" class="join_inputbox" type="password" size="25" maxlength="20" onkeyup="pwCheck()" required />
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<span id="pw_check"></span>
					</td>
				</tr>
			</table>
			    <input type="submit" id="join_btn" value="제출하기">
		</form>
	</div>
</div>
</div>
</body>
</html>