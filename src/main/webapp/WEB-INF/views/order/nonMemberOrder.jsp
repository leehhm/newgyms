<%@ page language="java" contentType="text/html; charset=utf-8"
   pageEncoding="utf-8"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html >
<html>
<head>
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<div class="con-min-width">
<div class="con">
   <p id="join_title" align="center">비회원 결제내역 조회</p>
   <form action="${contextPath}/order/nonMemberOrderInfo.do" method="post">
      <div align = center>
   <table id="login_table">   
      <!-- 주문번호 -->
      <tr>
         <td>
            <input id="order_id" name="order_id" class="login_inputbox" type="text" size="26" maxlength="20" placeholder="주문번호" required />
         </td>
      </tr>

      <!-- 비회원 주문 비밀번호 -->
      <tr>
         <td>
            <input id="nonmember_pw" name="nonmember_pw" class="login_inputbox" type="password" size="26" maxlength="20" placeholder="비회원 주문 비밀번호" required />
         </td>
      </tr>
   </table>
      <br>
      <input type="submit" value="조회" id="check_btn" align="center"> <br><br><br>
      <div>혹시... 회원이신가요?</div>  
      <div id="join_btn" style="margin-top:5px;"><a href="${contextPath}/member/loginForm.do" align="center" style="width:300px; height:40px;">로그인하기</a></div> <br><br>
    </div></form>    
</div>
</div>
</body>
</html>