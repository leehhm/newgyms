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
</head>
<body>
<div class="con-min-width">
<div class="con">

   <div id="contain" align=center>
      <p id="join_title" align=center>아이디 찾기</p>
      <p id="join_notice">고객님의 정보와 일치하는 아이디 목록입니다.</p>
      <p id="notice_box">
         <c:choose>
            <c:when test="${not empty member_id}">
               <span id="navy_color">${member_id}</span>
            </c:when>
            <c:otherwise>
               <span>회원정보를 찾을 수 없습니다. 😥</span>
            </c:otherwise>
         </c:choose>
      </p>
      <button class="join_idpw_btn" onClick="location.href='${contextPath}/member/loginForm.do'">로그인 하기</button>
      <button class="join_idpw_btn" onClick="location.href='${contextPath}/member/searchPwForm.do'">비밀번호 찾기</button>
   </div>
</div>
</div>   
   
</body>
</html>