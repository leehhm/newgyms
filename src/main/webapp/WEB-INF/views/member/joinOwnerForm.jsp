<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/member.css" rel="stylesheet" />
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	   $("#check_all").click(function(){
	   if ($("#check_all").prop("checked")){
	       $("#check_all").prop("checked",true);
	       $("#check_1").prop("checked",true);
	       $("#check_2").prop("checked",true);
	       $("#check_3").prop("checked",true);
	       $("#check_4").prop("checked",true);
	   } else{
	       $("#check_all").prop("checked",false);
	       $("#check_1").prop("checked",false);
	       $("#check_2").prop("checked",false);
	       $("#check_3").prop("checked",false);
	       $("#check_4").prop("checked",false);
	   }
	   });
	});
	   
	function changeFn(){
	   if($("#check_1").prop("checked")){
	      $("#check_1").prop("checked",true);
	      $("#check_all").prop("checked",false);
	   }
	   if($("#check_2").prop("checked")){
	      $("#check_2").prop("checked",true);
	      $("#check_all").prop("checked",false);
	   }
	   if($("#check_3").prop("checked")){
	      $("#check_3").prop("checked",true);
	      $("#check_all").prop("checked",false);
	   }
	   if($("#check_4").prop("checked")){
		  $("#check_4").prop("checked",true);
		  $("#check_all").prop("checked",false);
		}
	}
	
function fn_overlappedId(){
    var _id=$("#member_id").val();
    if(_id==''){
       alert("아이디를 입력하세요");
       return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlappedId.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
               alert("사용할 수 있는 아이디입니다.");
              $('#id_check2').text("사용가능한 아이디입니다. :)");
            $('#id_check2').css("color","green");
          } else {
             alert("사용할 수 없는 아이디입니다.");
             $('#id_check2').text("사용중인 아이디입니다. ^^;;");
              $('#id_check2').css("color","red");
          }
       },
       error:function(data,textStatus){
          //alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax    
 }   
 
function fn_overlappedEid(){
    var _eid=$("#owner_eid").val();
    if(_eid==''){
       alert("사업자 등록번호를 입력하세요");
       return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlappedEid.do",
       dataType:"text",
       data: {eid:_eid},
       success:function (data,textStatus){
          if(data=='false'){
               alert("확인되었습니다.");
          } else {
           alert("올바르지 않은 등록번호입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을 완료 했습니다");
       }
    });  //end ajax    
 }
 
//아이디 조합 검사 
function idRegexp() {
   let member_id = document.getElementById("member_id").value;
   let id_check2 = document.getElementById("id_check2");
   
   let num = member_id.search(/[0-9]/); // 아이디 중 0부터 9까지 num에 넣음
   let eng = member_id.search(/[a-z]/); // 아이디 중 a부터 z까지 eng에 넣음
   let kor = member_id.search(/[가-힣]/); // 아이디 중 가부터 힣까지 kor에 넣음
   let spe = member_id.search(/[`~!@#$%^&*|\\\'\":;\/?]/);
   // 아이디 중 특수문자를 spe에 넣음

   if (member_id.length < 5 || member_id.length > 20) {
      id_check2.style.color = "red";
      id_check2.innerHTML="아이디는 5자리에서 20자리 이내로 입력해주세요. ^^"
      return false;
   } 
   if (member_id.search(/\s/) != -1) {
      id_check2.style.color = "red";
      id_check2.innerHTML="아이디에 공백은 입력할 수 없습니다. :("
      return false;
   } 
   if (kor != -1 || spe != -1) {
      id_check2.style.color = "red";
      id_check2.innerHTML="한글과 특수문자는 입력할 수 없습니다. :("
      return false;
   } 
   if (eng == -1 || num == -1) {
      id_check2.style.color = "red";
      id_check2.innerHTML="아이디는 영문자와 숫자로 입력해주세요. :("
      return false;
   } 
   if (eng != -1 && num != -1 && kor == -1 && spe == -1) {
      id_check2.style.color = "green";
      id_check2.innerHTML="그렇지~~! 잘하셨습니다. ^^"
      return true;
   }
}

//비밀번호 조합 검사 
function pwRegexp() {
   let member_pw = document.getElementById("member_pw").value;
   let pw_help2 = document.getElementById("pw_help2");
   
   let num = member_pw.search(/[0-9]/); // 비밀번호 중 0부터 9까지 num에 넣음
   let eng = member_pw.search(/[a-z]/); // 비밀번호 중 a부터 z까지 eng에 넣음
   let spe = member_pw.search(/[`~!@#$%^&*|\\\'\":;\/?]/);
   // 비밀번호 중 특수문자를 spe에 넣음
   
   if (member_pw.length < 8 || member_pw.length > 20) {
      pw_help2.style.color = "red";
      pw_help2.innerHTML="비밀번호는 8자리에서 20자리 이내로 입력해주세요. :("
      return false;
   } else if (member_pw.search(/\s/) != -1) {
      pw_help2.style.color = "red";
      pw_help2.innerHTML="비밀번호에 공백은 입력할 수 없습니다. :("
      return false;
   } else if (num == -1 || eng == -1 || spe == -1) {
      pw_help2.style.color = "red";
      pw_help2.innerHTML="특수문자, 영문자, 숫자를 혼합하여 입력해주세요. :("
      return false;
   } else {
      pw_help2.style.color = "green";
      pw_help2.innerHTML="사용 가능한 비밀번호입니다. :)"
      return true;
   }
}

// 비밀번호와 비밀번호 확인이 동일한지 확인
function pwCheck() {
   let member_pw = document.getElementById("member_pw").value;
   let member_pw_confirm = document.getElementById("member_pw_confirm").value;
   let pw_check2 = document.getElementById("pw_check2");
   
   // 비밀번호와 비밀번호 확인란이 같지 않으면
   if (member_pw != member_pw_confirm) {
      pw_check2.style.color = "red";
      pw_check2.innerHTML="비밀번호가 다릅니다. :(";
      return false;
   } else {
      pw_check2.style.color = "green";
      pw_check2.innerHTML="비밀번호가 일치합니다. :)";
      return true;
   }
}
 
function execDaumPostcode() {
     new daum.Postcode({
       oncomplete: function(data) {
         // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

         // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
         // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
         var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
         var extraRoadAddr = ''; // 도로명 조합형 주소 변수

         // 법정동명이 있을 경우 추가한다. (법정리는 제외)
         // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
         if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
           extraRoadAddr += data.bname;
         }
         // 건물명이 있고, 공동주택일 경우 추가한다.
         if(data.buildingName !== '' && data.apartment === 'Y'){
           extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
         }
         // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
         if(extraRoadAddr !== ''){
           extraRoadAddr = ' (' + extraRoadAddr + ')';
         }
         // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
         if(fullRoadAddr !== ''){
           fullRoadAddr += extraRoadAddr;
         }

         // 우편번호와 주소 정보를 해당 필드에 넣는다.
         document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
         document.getElementById('roadAddress').value = fullRoadAddr;
         document.getElementById('jibunAddress').value = data.jibunAddress;

         return true;

         // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
         if(data.autoRoadAddress) {
           //예상되는 도로명 주소에 조합형 주소를 추가한다.
           var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
           document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

         } else if(data.autoJibunAddress) {
             var expJibunAddr = data.autoJibunAddress;
             document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
         } else {
             document.getElementById('guide').innerHTML = '';
         }
         
        
       }
     }).open();
   }
   
$(function(){   
      $(document).ready(function(){
         $("select[name=email_op]").on("change", function(){
             var $addr = $('input[name=email2]');
             if ($(this).val() == "etc") {
                $addr.val('');
                $addr.prop("readonly",false);
         
             } else {
                $addr.val($(this).val());
                $addr.prop("readonly",true);
             }
         });
      });
   });
</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">

			<div id="contain" align=center>
				<h2 id="join_title" align="center">사업자 회원가입</h2>
				<form
					action="${contextPath}/member/joinOwner.do?member_name=${member_name}"
					method="post">
					<table id="join_table2">
						<!-- 이름 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">이름</p>
							</td>
							<td><input name="member_name" class="join_inputbox"
								type="text" value="${member_name}" size="25" maxlength="10"
								disabled /></td>
						</tr>

						<!-- 사업장명 -->
						<tr>
							<td>
								<p class="inline" id="join_textbox">
									사업장명 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="center_name" id="center_name"
								class="join_inputbox" type="text" size="25" maxlength="20"
								pattern="[a-zA-Z가-힣]{1,20}" required
								title="사업장명을 영문 또는 한글로 입력해주세요."></td>
						</tr>

						<!-- 사업자 등록번호 -->
						<tr>
							<td>
								<p class="inline" id="join_textbox">
									사업자 등록번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="owner_eid" id="owner_eid"
								class="join_inputbox" type="text" size="25" maxlength="10"
								pattern="[0-9]{10}" required title="사업자 등록번호 10자리를 숫자로 입력해주세요.">
							</td>
							<td><input type="button" class="btn" id="btnOverlappedEid"
								value="중복확인" onClick="fn_overlappedEid()" /></td>
						</tr>

						<!-- 아이디 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									아이디 <span id="red_color">*</span>
								</p>
							</td>
							<td>
								<!-- <input name="_member_id" class="join_inputbox" type="text" id="_member_id" size="25" maxlength="20" /> -->
								<input name="member_id" id="member_id" class="join_inputbox"
								type="text" size="25" maxlength="20" pattern="[a-z0-9]{5,20}"
								required title="아이디는 영문자(소문자)와 숫자로 이루어진 5~20 자리를 입력해주세요."
								onkeyup="idRegexp()" placeholder="영문, 숫자 혼합 5자리 이상"> <br>
							</td>
							<td><input type="button" class="btn" id="btnOverlappedId"
								value="중복확인" onClick="fn_overlappedId()" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="id_check2"></span></td>
						</tr>

						<!-- 비밀번호 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									비밀번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_pw" id="member_pw"
								class="join_inputbox" type="password" size="25" maxlength="20"
								onkeyup="pwRegexp()" placeholder="영문, 숫자, 특수문자 혼합 8자리 이상" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="pw_help2"></span></td>
						</tr>

						<!-- 비밀번호 확인 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									비밀번호 확인 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_pw_confirm" id="member_pw_confirm"
								class="join_inputbox" type="password" size="25" maxlength="20"
								onkeyup="pwCheck()" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="pw_check2"></span></td>
						</tr>

						<!-- 주소 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									주소 <span id="red_color">*</span>
								</p>
							</td>
							<td><input type="text" class="join_inputbox" required
								id="zipcode" name="zipcode" size="12" readonly>
								&nbsp;&nbsp;<a href="javascript:execDaumPostcode()" class="btn">우편번호</a>
							</td>
						</tr>

						<!-- 도로명 OR 지번 주소 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox"></p>
							</td>
							<td><input name="road_address" id="roadAddress"
								class="join_inputbox" type="text" size="25" required> <input
								name="jibun_address" id="jibunAddress" class="join_inputbox"
								type="hidden" size="25" required></td>
						</tr>

						<!-- 나머지 주소 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox"></p>
							</td>
							<td><input name="namuji_address" id="namujiAddress"
								class="join_inputbox" type="text" size="25" required></td>
						</tr>

						<!-- 사업장 전화번호 -->
						<tr>
							<td>
								<p class="inline" id="join_textbox">
									사업장 전화번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><select name="owner_tel1" class="join_inputbox" required>
									<option>02</option>
									<option>031</option>
									<option>032</option>
									<option>041</option>
									<option selected>042</option>
									<option>051</option>
									<option>052</option>
									<option>061</option>
									<option>062</option>
							</select> - &nbsp;<input name="owner_tel2" class="join_inputbox"
								type="text" size="6" maxlength="3" pattern="[0-9]{3}" required
								title="중간번호 3자리를 숫자로 입력해주세요."> - &nbsp;<input
								name="owner_tel3" class="join_inputbox" type="text" size="6"
								maxlength="4" pattern="[0-9]{4}" required
								title="뒷번호 4자리를 숫자로 입력해주세요."></td>
						</tr>

						<!-- 휴대폰번호 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									휴대폰번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><select name="hp1" class="join_inputbox" required>
									<option selected>010</option>
									<option>011</option>
									<option>017</option>
									<option>019</option>
							</select> - &nbsp;<input name="hp2" class="join_inputbox" type="text"
								size="6" maxlength="4" pattern="[0-9]{4}" required
								title="중간번호 4자리를 숫자로 입력해주세요."> - &nbsp;<input
								name="hp3" class="join_inputbox" type="text" size="6"
								maxlength="4" pattern="[0-9]{4}" required
								title="뒷번호 4자리를 숫자로 입력해주세요."></td>
							<td>
								<!-- SMS 수신 동의 --> <input type="checkbox" name="smssts_yn"
								value="Y" checked /><span id="join_subtext"> SMS 수신 동의</span>
							</td>
						</tr>

						<!-- 이메일 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">
									이메일 <span id="red_color">*</span>
								</p>
							</td>
							<td colspan="2"><input name="email1"
								class="join_inputbox" type="text" size="9"
								pattern="[a-z0-9]{3,20}" required title="이메일 주소를 입력해주세요.">
								@ <input name="email2" class="join_inputbox" type="text"
								size="12"> &nbsp; <select name="email_op"
								class="join_inputbox" required>
									<option value="etc">직접입력</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
									<option value="nate.com">nate.com</option>
									<option value="kakao.com">kakao.com</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="nate.com">nate.com</option>
							</select></td>
						</tr>

						<!-- 성별 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">성별</p>
							</td>
							<td><input type="radio" name="member_gender" value="M"
								checked /> 남자 <input type="radio" name="member_gender"
								value="W" /> 여자</td>
						</tr>

						<!-- 주민등록번호 & 회원유형 -->
						<tr>
							<td><input name="member_rrn1" type="hidden"
								value="${member_rrn1}" size="10" maxlength="6" /> <input
								name="member_rrn2" type="hidden" value="${member_rrn2}"
								size="10" maxlength="7" /> <input name="join_type"
								type="hidden" value="${join_type}" size="10" maxlength="3" /></td>
						</tr>
					</table>

					<div class="checkbox_group">
                  <input type="checkbox" id="check_all"> <label
                     for="check_all" style="font-size: 20px; margin-bottom: 10px;">모두
                     동의합니다. </label> <br>

                  <div
                     style="margin-top: 10px; width: 300px; border: 1px solid #D8D8D8; border-radius: 5px;"></div>
                  <!-- 이용약관동의 -->
                  <div id="join_agree">

                     <input type="checkbox" id="check_1" class="normal"
                        onclick="changeFn();" required> <label for="check_1">
                        <a href="#popup1" style="color: blue">서비스 이용약관</a>에 동의합니다. <span
                        id="red_color">(필수)</span>
                     </label> <br> <input type="checkbox" id="check_2" class="normal"
                        onclick="changeFn();" required> <label for="check_2">
                        <a href="#popup2" style="color: blue">개인정보 수집/이용</a>에 동의합니다. <span
                        id="red_color">(필수)</span>
                     </label> <br> <input type="checkbox" id="check_3" class="normal"
                        onclick="changeFn();"> <label for="check_3"> <a
                        href="#popup3" style="color: blue">위치기반 서비스 이용약관</a>에 동의합니다.(선택)
                     </label> <br> <input type="checkbox" id="check_4" class="normal"
                        onclick="changeFn();"> <label for="check_4">뉴짐스에
                        충성을 다할것을 맹세합니다.(선택) </label>

                  </div>
               </div>

               <input type="submit" value="가입하기" id="join_btn" align="center">
            </form>
         </div>
      </div>
   </div>

   <!-- 약관동의 모달 -->
   <div class="popup" id="popup1">
      <div class="popup-inner">
         <div class="popup__photo">
            <img src="${contextPath}/resources/image/newgyms.png" alt="NEWGYMS">
         </div>
         <div class="popup__text">
            <h2 style="color:#0F0573; padding:5px;">서비스 이용약관</h2>
            <p>이 약관은 NEWGYMS 주식회사 ("회사" 또는 "뉴짐스")가 제공하는 뉴짐스 및 뉴짐스 관련 제반 서비스의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
         </div>
         <a class="popup__close" href="#">X</a>
      </div>
   </div>
   
   <div class="popup" id="popup2">
      <div class="popup-inner">
         <div class="popup__photo">
            <img src="${contextPath}/resources/image/newgyms.png" alt="NEWGYMS">
         </div>
         <div class="popup__text">
            <h2 style="color:#0F0573; padding:5px;">개인정보 수집/이용</h2>
            <p>본인확인 및 회원관리 서비스 제공을 위하여 아래와 같이 회원의 개인정보를 수집, 활용합니다.</p>
            <table>
               <tr>
                  <th colspan="2">이용목적</th>
                  <th>수집항목</th>
                  <th>보유 기간</th>
               </tr>
               
               <tr>
                  <td rowspan="2" width="20%">회원가입 및 관리</td>
                  <td width="10%">본인확인</td>
                  <td width="30%">[필수]휴대폰 번호, 이메일</td>
                  <td width="40%">확인 후 즉시 파기</td>
               </tr>
               
               <tr>
                  <td>회원관리</td>
                  <td>[필수] 성명, 휴대폰 번호, 이메일 주소, 아이디, 비밀번호   </td>
                  <td>회원탈퇴 후 파기<br>다만 관계법령에 의해 보존할 경우 그 의무기간 동안 별도 보관되며 불, 편법 행위의 방지 및 대응의 목적으로 6개월 보관됩니다.</td>
               </tr>
            </table>
         </div>
         <a class="popup__close" href="#">X</a>
      </div>      
   </div>
   
   <div class="popup" id="popup3">
      <div class="popup-inner">
         <div class="popup__photo">
            <img src="${contextPath}/resources/image/newgyms.png" alt="NEWGYMS">
         </div>
         <div class="popup__text">
            <h2 style="color:#0F0573; padding:5px;">위치기반 서비스 이용약관</h2>
            <p>이 약관은 NEWGYMS 주식회사 ("회사" 또는 "뉴짐스")가 제공하는 위치기반서비스와 관련하여 회사와 개인위치정보주체와의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
         </div>
         <a class="popup__close" href="#">X</a>
      </div>      
   </div>
</body>
</html>