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

</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">

			<div id="contain" align=center>
				<h2 id="join_title" align="center">요것만 입력하고 가실게요~~! 😊</h2>
				<form action="${contextPath}/member/kakaoJoin.do" method="post">
					<table id="join_table2">
						<!-- 이름, 아이디, 비밀번호, 이메일, 성별 -->
						<tr>
							<td><input name="member_name" type="hidden"
								value="${memberInfo.member_name}" /> <input name="member_id"
								type="hidden" value="${memberInfo.member_id}" /> <input
								name="member_pw" type="hidden" value="${memberInfo.member_pw}" />
								<input name="email1" type="hidden" value="${memberInfo.email1}" />
								<input name="email2" type="hidden" value="${memberInfo.email2}" />
								<input name="member_gender" type="hidden"
								value="${memberInfo.member_gender}" /> <input
								name="emailsts_yn" type="hidden" value="Y" /> <input
								name="join_type" type="hidden" value="101" /></td>
						</tr>

						<!-- 생년월일 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">생년월일 <span id="red_color">*</span></p>
							</td>
							<td>
								<select name="member_birth_y" class="join_inputbox" required>
									<option selected>1980</option>
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
							</select> <span id="join_subtext">년 &nbsp;</span>
							<select name="member_birth_m" class="join_inputbox" required>
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
							</select> <span id="join_subtext">월 &nbsp;</span>
							<select name="member_birth_d" class="join_inputbox" required>
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
							</select> <span id="join_subtext">일</span>
						</tr>

						<!-- 주소 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox">주소 <span id="red_color">*</span></p>
							</td>
							<td><input type="text" class="join_inputbox" id="zipcode"
								name="zipcode" size="12" readonly required> &nbsp;&nbsp;<a
								href="javascript:execDaumPostcode()" class="btn">우편번호</a></td>
						</tr>

						<!-- 도로명 OR 지번 주소 -->
						<tr>
							<td>
								<p class="inline" class="join_textbox"></p>
							</td>
							<td><input name="road_address" id="roadAddress"
								class="join_inputbox" type="text" size="25"> <input
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

						<tr>
							<td colspan="3">
								<p class="inline" class="join_textbox">
									<span id="red_color"
										style="margin-top: 20px; font-size: 12px; line-height: 60px; margin-left: 70px;">*
										주소 정보 미입력시 위치기반 서비스를 이용하실 수 없습니다.</span>
								</p>
							</td>
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
								title="중간번호 4자리를 숫자로 입력해주세요." /> - &nbsp;<input name="hp3"
								class="join_inputbox" type="text" size="6" maxlength="4"
								pattern="[0-9]{4}" required title="뒷번호 4자리를 숫자로 입력해주세요." /> <!-- SMS 수신 동의 -->
								&nbsp;&nbsp;<input type="checkbox" name="smssts_yn" value="Y"
								checked /><span id="join_subtext"> SMS 수신 동의</span></td>
						</tr>
					</table>


					<div class="checkbox_group">
						<input type="checkbox" id="check_all"> <label
							for="check_all" style="font-size: 20px; margin-bottom: 10px;">모두
							동의합니다.</label> <br>
						<div
							style="margin-top: 10px; width: 300px; border: 1px solid #D8D8D8; border-radius: 5px;"></div>
						<div id="join_agree">
							<!-- 이용약관동의 -->

							<input type="checkbox" id="check_1" class="normal"
								onclick="changeFn();" required> <label for="check_1"><a
								href="" style="color: blue">서비스 이용약관</a>에 동의합니다.<span
								id="red_color">(필수)</span></label> <br> <input type="checkbox"
								id="check_2" class="normal" onclick="changeFn();" required>
							<label for="check_2"><a href="" style="color: blue">개인정보
									수집/이용</a>에 동의합니다.<span id="red_color">(필수)</span></label> <br> <input
								type="checkbox" id="check_3" class="normal"
								onclick="changeFn();"> <label for="check_3"><a
								href="" style="color: blue">위치기반 서비스 이용약관</a>에 동의합니다.(선택)</label> <br>
							<input type="checkbox" id="check_4" class="normal"
								onclick="changeFn();"> <label for="check_4">뉴짐스에
								충성을 다 할것을 맹세합니다.(선택)</label>
						</div>
					</div>

					<input type="submit" value="가입하기" id="join_btn" align="center">
				</form>
			</div>
		</div>
	</div>
</body>
</html>