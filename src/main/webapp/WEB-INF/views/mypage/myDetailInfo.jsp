<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<style>
@charset "UTF-8";

@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

* {
	font-family: 'GmarketSansMedium';
	margin: 0px;
	padding: 0px;
}

body {
	margin: 0;
	padding: 0;
}

#contain {
	display: flex;
}

#contain_right {
	width: 1000px;
}

#mypage_order_title {
	margin-top: 30px;
	margin-bottom: 20px;
	padding: 5px;
	font-size: 25px;
	text-align: center;
}

#mypage_info_title {
	font-size: 20px;
	color: #0F0573;
}

#order_detail {
	width: 100%;
	padding: 10px;
	line-height: 40px;
	border-top: 1px solid rgb(244, 244, 244);
	border-bottom: 1px solid rgb(244, 244, 244);
}

#order_detail td {
	border-bottom: 1px solid rgb(244, 244, 244);
}

#order_detail2 {
	width: 100%;
	padding: 10px;
	line-height: 40px;
}

#order_detail2 th {
	border-bottom: 1px solid rgb(244, 244, 244);
	text-align: center;
}

#order_detail2 td {
	border-bottom: 1px solid rgb(244, 244, 244);
	text-align: center;
}

#detail_image {
	width: 80px;
	height: 80px;
	border-radius: 5px;
}

#red_color {
	color: red;
}

#gray_color {
	color: #848484;
}

#navy_color {
	color: #0F0573;
}

.submit_btn {
	margin: 20px 5px 10px 5px;
	border: none;
	width: 250px;
	padding: 10px;
	text-align: center;
	font-size: 15px;
	color: #FFFFFF;
	background-color: #0F0573;
	text-decoration: none;
	border-radius: 5px;
}

.submit_btn:hover {
	cursor: pointer;
}

.submit_btn2 {
	margin: 20px 5px 10px 5px;
	border: 1px solid #0F0573;
	width: 250px;
	padding: 10px;
	text-align: center;
	font-size: 15px;
	color: #0F0573;
	background-color: #FFFFFF;
	text-decoration: none;
	border-radius: 5px;
}

.submit_btn2:hover {
	cursor: pointer;
}

#mypage_sidebar {
	width: 200px;
	height: 265px;
	padding: 10px;
	margin-right: 15px;
	margin-top: 83px;
	font-size: 17px;
	text-align: center;
	line-height: 40px;
	border: 1px solid #D8D8D8;
	border-radius: 5px;
}

#mypage_hr {
	border-bottom: 1px solid #D8D8D8;
}

#mypage_sidebar>ul>li {
	font-size: 15px;
}

#mypage_sidebar>ul>li:hover>a {
	text-decoration: underline;
	text-underline-position: under;
	color: #F9C200;
}

.cancel_inputbox {
	margin-left: 5px;
	padding: 5px;
	border: none;
	border-bottom: 1px solid #D8D8D8;
}

#upper_menu {
	width: 100%;
	display: flex;
	justify-content: space-evenly;
	margin-bottom: 20px;
}

#upper_menu ul li {
	display: inline;
}

#search_button {
	margin-left: 5px;
	border: 1px solid #0F0573;
	width: 60px;
	height: 30px;
	text-align: center;
	font-size: 12px;
	color: #0F0573;
	background-color: #FFFFFF;
	border-radius: 5px;
}

#text_box {
	height: 25px;
	text-align: center;
	font-size: 11px;
}

#img_file {
	margin: 5px;
	border: 1px solid #D8D8D8;
	width: 100px;
	height: 100px;
	border-radius: 5px;
}

#btn_1 {
	display: inline-block;
	border: none;
	width: 60px;
	height: 30px;
	text-align: center;
	font-size: 12px;
	color: #FFFFFF;
	background-color: #0F0573;
	border-radius: 5px;
}

input#btn1:hover {
	cursor: pointer;
}

#btn_2 {
	margin-top: 5px;
	border: 1px solid black;
	width: 60px;
	height: 30px;
	text-align: center;
	font-size: 12px;
	color: #0F0573;
	background-color: #FFFFFF;
	border-radius: 5px;
}

button:hover {
	cursor: pointer;
}

.mypage_inputbox {
	border: none;
	border-bottom: 1px solid #D8D8D8;
}

.mypage_textbox {
	width: 15px;
	text-align: left;
	padding: 10px;
}

#mypage_info {
	margin-top: 60px;
	width : 100%;
	font-size: 20px;
	text-align: center;
	padding: 50px;
}

#mypage_info_table {
	width: 100%;
	line-height: 50px;
	padding: 30px;
}

#mypage_info_table td {
	padding: 10px;
}

#align_center {
	text-align: center;
}

#mypage_modify_title {
	margin-top: 30px;
	margin-bottom: 20px;
	padding: 5px;
	font-size: 25px
}

#mypage_modify_table {
	margin-top: 50px;
	line-height: 60px;
}

.mypage_modify_inputbox {
	padding: 3px;
	border: none;
	border-bottom: 1px solid #D8D8D8;
}

.mypage_modify_textbox {
	width: 15px;
	text-align: left;
	padding: 10px;
}

#pw_check, #pw_help {
	font-size: 10px;
	line-height: 0;
	padding-left: 115px;
}

#mypage_subtext {
	color: #848484;
	font-size: 12px;
}

#mypage_delete_table {
	width: 830px;
	margin-top: 50px;
	padding-right: 123px;
	line-height: 30px;
}

#btn_1 {
	display: inline-block;
	border: none;
	width: 80px;
	height: 30px;
	text-align: center;
	font-size: 13px;
	color: #FFFFFF;
	background-color: #0F0573;
	border-radius: 5px;
}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">

function deleteMember() {
		
	if(confirm("회원탈퇴를 진행하시겠습니까?")){
		var form = document.createElement('form');
    	form.setAttribute('method','post');
    	form.setAttribute('action','${contextPath}/mypage/deleteMemberForm.do?member_id=${memberInfo.member_id}');
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
<div class="con-min-width">
	<div class="con">
		<div id="contain" align=center>
			<!-- 마이페이지 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
			<div id="contain_right">
				<p id="mypage_modify_title">회원정보 수정</p>
				<form
					action="${contextPath}/mypage/modifyMyInfo.do" method="post">

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

						<!-- 생년월일 -->
						<tr>
							<td>
								<p class="inline" class="mypage_textbox">
									생년월일 <span id="red_color">*</span>
								</p>
							</td>
							<td><select name="member_birth_y" class="mypage_inputbox"
								required>
									<option>1980</option>
									<option>1981</option>
									<option>1982</option>
									<option>1983</option>
									<option>1984</option>
									<option>1985</option>
									<option>1986</option>
									<option>1987</option>
									<option>1988</option>
									<option>1989</option>
									<option>1990</option>
									<option>1991</option>
									<option>1992</option>
									<option>1993</option>
									<option>1994</option>
									<option>1995</option>
									<option>1996</option>
									<option>1997</option>
									<option>1998</option>
									<option>1999</option>
									<option>2000</option>
									<option>2001</option>
									<option>2002</option>
									<option>2003</option>
									<option>2004</option>
									<option>2005</option>
									<option>2006</option>
									<option>2007</option>
									<option>2008</option>
									<option>2009</option>
									<option>2010</option>
									<option>2011</option>
									<option>2012</option>
									<option>2013</option>
									<option>2014</option>
									<option>2015</option>
									<option>2016</option>
									<option>2017</option>
									<option>2018</option>
									<option>2019</option>
									<option>2020</option>
									<option>2021</option>
									<option>2022</option>
									<option>2023</option>
							</select> <span id="mypage_subtext">년 &nbsp;</span> <select
								name="member_birth_m" class="mypage_inputbox" required>
									<option>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
									<option>9</option>
									<option>10</option>
									<option>11</option>
									<option>12</option>
							</select> <span id="mypage_subtext">월 &nbsp;</span> <select
								name="member_birth_d" class="mypage_inputbox" required>
									<option selected>1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
									<option>5</option>
									<option>6</option>
									<option>7</option>
									<option>8</option>
									<option>9</option>
									<option>10</option>
									<option>11</option>
									<option>12</option>
									<option>13</option>
									<option>14</option>
									<option>15</option>
									<option>16</option>
									<option>17</option>
									<option>18</option>
									<option>19</option>
									<option>20</option>
									<option>21</option>
									<option>22</option>
									<option>23</option>
									<option>24</option>
									<option>25</option>
									<option>26</option>
									<option>27</option>
									<option>28</option>
									<option>29</option>
									<option>30</option>
									<option>31</option>
							</select> <span id="mypage_subtext">일</span>
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
