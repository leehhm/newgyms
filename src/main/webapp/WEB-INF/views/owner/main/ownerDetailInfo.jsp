<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css" rel="stylesheet" />
<script type="text/javascript">

function deleteMember() {
		
	if(confirm("회원탈퇴를 진행하시겠습니까?")){
		var form = document.createElement('form');
    	form.setAttribute('method','post');
    	form.setAttribute('action','${contextPath}/owner/main/deleteMemberForm.do?member_id=${memberInfo.member_id}');
    	document.body.appendChild(form);
    	form.submit();  
	 	return true;
	} else {
		alert("잘 생각하셨습니다.😘");
		return false;
	}
}


// 비밀번호 조합 검사 
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
            guideTextBox.style.display = 'none';

         } else if(data.autoJibunAddress) {
             var expJibunAddr = data.autoJibunAddress;
             document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
              guideTextBox.style.display = 'none';
         } else {
             document.getElementById('guide').innerHTML = '';
              guideTextBox.style.display = 'none';
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
<title>사업자 정보 수정 페이지</title>
<div class="con-min-width">
	<div class="con">
		<div id="contain" align=center>
			<!-- 사업자페이지 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
			<div id="contain_right">
				<p id="mypage_modify_title">사업자 회원정보 수정</p>
				<form
					action="${contextPath}/owner/main/modifyMyInfo.do" method="post">

					<table id="mypage_modify_table">
						<!-- 이름 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									이름 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_name" id="member_name"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.member_name}" size="25" maxlength="10"
								readonly required /></td>
						</tr>
						<!-- 사업장명 -->
						<tr>
							<td>
								<p class="inline" id="mypage_modify_textbox">
									사업장명 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="center_name" id="center_name"
								class="mypage_modify_inputbox" type="text" value="${memberInfo.center_name }" size="25" maxlength="20"
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
								class="mypage_modify_inputbox" type="text" value="${memberInfo.owner_eid }" size="25" maxlength="10"
								pattern="[0-9]{10}" readonly required >
							</td>
							<!-- <td><input type="button" class="btn" id="btnOverlappedEid"
								value="중복확인" onClick="fn_overlappedEid()" /></td> -->
						</tr>
						<!-- 아이디 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									아이디 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_id" id="member_id"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.member_id}" size="25" readonly required /></td>
						</tr>

						<!-- 비밀번호 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									비밀번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_pw" id="member_pw"
								class="mypage_modify_inputbox" type="password"
								value="${memberInfo.member_pw}" size="25" maxlength="20"
								required onkeyup="pwRegexp()" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="pw_help"></span></td>
						</tr>

						<!-- 비밀번호 확인 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									비밀번호 확인 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="member_pw_confirm" id="member_pw_confirm"
								class="mypage_modify_inputbox" type="password"
								value="${memberInfo.member_pw}" size="25" maxlength="20"
								required onkeyup="pwCheck()" /></td>
						</tr>
						<tr>
							<td colspan="3"><span id="pw_check"></span></td>
						</tr>

						<!-- 주소 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									주소 <span id="red_color">*</span>
								</p>
							</td>
							<td><input name="zipcode" id="zipcode"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.zipcode}" size="12" required readonly>
								&nbsp;&nbsp;<a href="javascript:execDaumPostcode()" class="btn">우편번호</a>
							</td>
						</tr>

						<!-- 도로명 OR 지번 주소 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox"></p>
							</td>
							<td><input name="road_address" id="roadAddress"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.road_address}" size="25" required> <input
								name="jibun_address" id="jibunAddress"
								class="mypage_modify_inputbox" type="hidden"
								value="${memberInfo.jibun_address}" size="25" required></td>
						</tr>

						<!-- 나머지 주소 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox"></p>
							</td>
							<td><input name="namuji_address" id="namujiAddress"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.namuji_address}" size="25" required></td>
						</tr>
						<!-- 사업장 전화번호 -->
						<tr>
							<td>
								<p class="inline" id="mypage_modify_textbox">
									사업장 전화번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><select name="owner_tel1" class="mypage_modify_inputbox" value="${memberInfo.owner_tel1 }" required>
									<option>02</option>
									<option>031</option>
									<option>032</option>
									<option>041</option>
									<option selected>042</option>
									<option>051</option>
									<option>052</option>
									<option>061</option>
									<option>062</option>
							</select> - &nbsp;<input name="owner_tel2" class="mypage_modify_inputbox"
								value="${memberInfo.owner_tel2 }" type="text" size="6" maxlength="3" pattern="[0-9]{3}" required
								title="중간번호 3자리를 숫자로 입력해주세요."> - &nbsp;<input
								name="owner_tel3" class="mypage_modify_inputbox" value="${memberInfo.owner_tel3 }" type="text" size="6"
								maxlength="4" pattern="[0-9]{4}" required
								title="뒷번호 4자리를 숫자로 입력해주세요."></td>
						</tr>

						<!-- 휴대폰번호 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									휴대폰번호 <span id="red_color">*</span>
								</p>
							</td>
							<td><select name="hp1" class="mypage_modify_inputbox"
								required>
									<option selected>010</option>
									<option>011</option>
									<option>017</option>
									<option>019</option>
							</select> - &nbsp; <input name="hp2" class="mypage_modify_inputbox"
								type="text" value="${memberInfo.hp2}" size="6" maxlength="4"
								pattern="[0-9]{4}" required title="중간번호 4자리를 숫자로 입력해주세요." /> -
								&nbsp; <input name="hp3" class="mypage_modify_inputbox"
								type="text" value="${memberInfo.hp3}" size="6" maxlength="4"
								pattern="[0-9]{4}" required title="뒷번호 4자리를 숫자로 입력해주세요." /></td>
							<td>
								<!-- SMS 수신 동의 --> <input type="checkbox" name="smssts_yn"
								value="Y" checked /><span id="mypage_subtext"> SMS 수신 동의</span>
							</td>
						</tr>

						<!-- 이메일 -->
						<tr>
							<td>
								<p class="inline" class="mypage_modify_textbox">
									이메일 <span id="red_color">*</span>
								</p>
							</td>
							<td colspan="2"><input name="email1"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.email1}" size="9" pattern="[a-z0-9]{3,20}"
								required title="이메일 주소를 입력해주세요."> @ <input name="email2"
								class="mypage_modify_inputbox" type="text"
								value="${memberInfo.email2}" size="12"> &nbsp; <select
								name="email_op" class="mypage_modify_inputbox" required>
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
							<td align=right><input type="checkbox" name="emailsts_yn"
								value="Y" checked /><span id="mypage_subtext"> 이메일 수신 동의</span></td>
						</tr>
					</table>
					<div id="align_center">
						<input type="submit" class="submit_btn" value="수정하기">
						<br>
						<a href="#" onclick="deleteMember()" style="color:red; text-align:right;">회원탈퇴를 하시려면 이곳을 눌러주세요...</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>
