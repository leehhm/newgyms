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
function checkOnlyOne(element) {
     const checkboxes = document.getElementsByName("checkonly");
     checkboxes.forEach((cb) => {
       cb.checked = false;
     })
     element.checked = true;
   }
  
      function checkOnlyTwo(checkbox) {
         const member_name = document.getElementById("member_name");
         member_name.disabled = false;
            const hp1 = document.getElementById("hp1");
            hp1.disabled = false;
            const hp2 = document.getElementById("hp2");
            hp2.disabled = false;
            const hp3 = document.getElementById("hp3");
            hp3.disabled = false;
            const ok = document.getElementById("ok1");
            ok.disabled = false;
      
            const member_name1 = document.getElementById("member_name1");
            member_name1.disabled = true;
              const email1 = document.getElementById("email1");
              email1.disabled = true;
              const email2 = document.getElementById("email2");
              email2.disabled = true;
              const ok1 = document.getElementById("ok");
              ok1.disabled = true;
}
     
    function checkOnlyThree(checkbox) {
       const member_name1 = document.getElementById("member_name1");
       member_name1.disabled = false;
            const email1 = document.getElementById("email1");
            email1.disabled = false;
            const email2 = document.getElementById("email2");
            email2.disabled = false;
            const ok1 = document.getElementById("ok");
            ok1.disabled = false;
      
      const member_name = document.getElementById("member_name");
      member_name.disabled = true;
      const hp1 = document.getElementById("hp1");
      hp1.disabled = true;
      const hp2 = document.getElementById("hp2");
      hp2.disabled = true;
      const hp3 = document.getElementById("hp3");
      hp3.disabled = true;
      const ok = document.getElementById("ok1");
      ok.disabled = true;
}
    
    function submit2(frm) {
        frm.action= 'sendEmailId.do';
        frm.submit();
        event.preventDefault();
        
        alert("인증번호가 발송되었습니다.")
        return true;
    }
  </script>
</head>
<body>
<div class="con-min-width">
<div class="con">

	<div id="contain" align=center>
	<p id="join_title" align="center">아이디 찾기</p>
  	<form action="${contextPath}/member/searchId.do" method="post"> 
      <table id="search_table">
         <tr>
         	<td colspan = "2">
         	    <input type ="hidden" name="ran" value="${ran}">
         		<input type ="checkbox" name = "checkonly" onClick='checkOnlyOne(this); checkOnlyTwo(this);'>회원 정보에 등록된 휴대전화로 인증
         	</td>
         </tr>
         
         <!-- 이름 -->
         <tr>
         	<td>
            	<p class="inline" class="join_textbox">이름 <span id="red_color">*</span></p>
            </td>
            <td>
            	<input name="member_name" id="member_name" size="24" maxlength="10" class="join_inputbox" disabled/>
            </td>
         </tr>
            
         <!-- 휴대폰번호 -->
         <tr>
          	<td>
          		<p class="inline" class="join_textbox">휴대폰번호 <span id="red_color">*</span></p>
          	</td>
            <td>
               <select name="hp1" id="hp1" class="join_inputbox" disabled>
               		<option value="010" selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
               	</select> - 
         		<input size="6" type="text" name="hp2" id="hp2" class="join_inputbox" disabled> - 
          		<input size="6" type="text" name="hp3" id="hp3" class="join_inputbox" disabled> 
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
           		<input type="text" size="24" name="ok1" id="ok1" class="join_inputbox" maxlength="6" placeholder="인증번호 숫자 6자리 입력" disabled/>
            </td>
         </tr> 
      </table>
       
	<div class="line-1" style="width:1200px; height:2px; background-color:#f4f2f2; margin-top:30px;"></div>
	      
	<table id="search_table">
		<tr>
			<td colspan = "2">
				<input type ="checkbox" name="checkonly" onClick='checkOnlyOne(this); checkOnlyThree(this);' checked>본인 확인 이메일로 인증
			</td>
		</tr>
		
		<!-- 이름 -->
        <tr>
        	<td>
        	   	<p class="inline" class="join_textbox">이름 <span id="red_color">*</span></p>
            </td>
            <td>
            	<input name="member_name" value="${member_name}" type="text" size="24" id="member_name1" class="join_inputbox" required title="이름을 입력해주세요." >
            </td>
        </tr>
        
        <!-- 이메일 -->
        <tr>
        	<td>
          		<p class="inline" class="join_textbox">이메일 <span id="red_color">*</span></p>
          	</td>
          	<td>
          		<input size="8" value="${email1}" type="text" name="email1" id="email1" class="join_inputbox" 
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
           		<input type="text" size="24" name="ok" id="ok" class="join_inputbox" maxlength="6" placeholder="인증번호 숫자 6자리 입력" 
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