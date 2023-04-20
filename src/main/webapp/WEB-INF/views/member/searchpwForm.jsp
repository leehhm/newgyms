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

<script>
function submit2(frm) {
    frm.action= 'sendEmailPw.do';
    frm.submit();
    alert("인증번호가 발송되었습니다.")

    return true;
}
</script>
</head>
<body>
<div class="con-min-width">
<div class="con">

<div id="contain" align=center>
	<p id="join_title" align="center">비밀번호 찾기</p>
  	<form action="${contextPath}/member/searchPw.do" method="post"> 
  	<input type="hidden" value="${ran}" name="ran">
	<table id="search_table">
	
		<!-- 아이디 -->
        <tr>
        	<td>
        	   	<p class="inline" class="join_textbox">아이디 <span id="red_color">*</span></p>
            </td>
            <td>
            	<input name="member_id" value="${member_id}" type="text" size="24" id="member_id" class="join_inputbox" required title="아이디를 입력해주세요" >
            </td>
        </tr>

		<!-- 이름 -->
        <tr>
        	<td>
        	   	<p class="inline" class="join_textbox">이름 <span id="red_color">*</span></p>
            </td>
            <td>
            	<input name="member_name" value="${member_name}" type="text" size="24" id="member_name" class="join_inputbox" required title="이름을 입력해주세요.">
            </td>
        </tr>
        
        <!-- 이메일 -->
        <tr>
        	<td>
          		<p class="inline" class="join_textbox">이메일 <span id="red_color">*</span></p>
          	</td>
          	<td>
          		<input size="8" type="text" name="email1" id="email1" value="${email1}" class="join_inputbox" 
          		pattern="[a-z0-9]{3,20}" required title="이메일을 입력해주세요." > @ 
                <select name="email2" id="email2" class="join_inputbox">
                    <option value="naver.com" selected>naver.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                	<option value="yahoo.co.kr">yahoo.co.kr</option>
               		<option value="hotmail.com">hotmail.com</option>
                	<option value="paran.com">paran.com</option>
                	<option value="nate.com">nate.com</option>
                	<option value="google.com">google.com</option>
                  	<option value="gmail.com">gmail.com</option>
                	<option value="empal.com">empal.com</option>
                	<option value="korea.com">korea.com</option>
                	<option value="freechal.com">freechal.com</option>
                </select>
          	</td>
          	<td>
          	    <input type="button" class="btn2" value="인증번호 받기" onClick="return submit2(this.form);">
          	</td>
        </tr>
        
        <!-- 인증번호 -->
         <tr>
         	<td>
          		<p class="inline" class="join_textbox">인증번호 <span id="red_color">*</span></p>
          	</td>
           	<td colspan="2">
           		<input type="text" size="24" name="ok1" id="ok1" class="join_inputbox" maxlength="6" placeholder="인증번호 숫자 6자리 입력" 
           		pattern="[0-9]{6}" required title="인증번호 숫자 6자리를 입력해주세요.">
            </td>
         </tr> 
     </table>
            <input type="submit" id="join_btn" value="다음">
	</form>
</div>
</div>
</div>
</body>
</html>