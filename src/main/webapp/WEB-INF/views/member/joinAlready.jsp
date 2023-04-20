<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />
</head>
<body>
<div class="con-min-width">
<div class="con">
	<div id="contain" align=center>
		<p id="join_title" align=center>회원가입</p>
		<p id="join_notice">이미 가입되어 있는 회원입니다.</p>
		<p id="notice_box"><span id="navy_color">${member_id}</span></p>
		<button class="join_idpw_btn" onClick="location.href='${contextPath}/member/loginForm.do'">로그인 하기</button>
		<button class="join_idpw_btn" onClick="location.href='${contextPath}/member/searchPwForm.do'">비밀번호 찾기</button>
	</div>
</div>
</div>
</body>
</html>