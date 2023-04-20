<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="owner" value="${ownerPageVO} " />

<!-- member_id 세션에 담아서 컨트롤러로 이동 -->
<%-- <%
	request.setCharacterEncoding("UTF-8");
	String member_id = request.getParameter("member_id");
	session.setAttribute("member_id", member_id);
%> --%>
<!DOCTYPE html>
<html>
<head>
<script src="https://developers.kakao.com/sdk/js/kakao.js" charset="utf-8"></script>

<link href="${contextPath}/resources/css/mypage.css?after" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css?after" rel="stylesheet" />

<script type="text/javascript">
function ownerPageModify(member_id) {
	var modify = confirm("수정하시겠습니까?");
	
	if (modify == false) {
		return false;		
	}
	$('#intro_introduce').empty();
	var intro_introduce = $('#intro_introduce').val();
	var intro_notice = $('#intro_notice').val();
	var intro_time = $('#intro_time').val();
	var intro_program = $('#intro_program').val();
	var intro_service = $('#intro_service').val();
	
	console.log(intro_introduce)
	console.log(intro_notice)
	console.log(intro_time)
	console.log(intro_program)
	console.log(intro_service)
	console.log(member_id)		
	
	$.ajax({
		type : "post",
		async : false, //false인 경우 동기식으로 처리한다.
		url : "${contextPath}/owner/main/ownerPageIntroModify.do",
		data : {
			member_id:member_id,
			intro_introduce:intro_introduce,
			intro_notice:intro_notice,
			intro_time:intro_time,
			intro_program:intro_program,
			intro_service:intro_service
		},
		success : function(data, textStatus) {
			//alert(data);
			if(data.trim()=='success'){
				alert("글이 수정되었습니다.");
				location.reload();
			}else{
				alert("다시 시도해주세요.");	
			}
			
		},
		error : function(data, textStatus) {
			alert("에러가 발생했습니다."+data);
		},
		complete : function(data, textStatus) {
			//alert("작업을완료 했습니다");
		}
	}); //end ajax	
} 
</script>

<title>사업장 관리 수정 페이지</title>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
		<div id="contain">
				<!-- 사업자페이지 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
				<div id="contain_right" style="padding-left:20px;">
			     	 <input name="center_name" class="ownerPage_intro_title" type="text" size="10" value=" ${memberInfo.center_name}"  readonly disabled/>
				
				<!-- 사업장 정보 -->
				<table class="info_table">
					<tr>
						<td>
							<input name="center_address" type="text" class="center_address" value="&#128204; ${memberInfo.road_address} ${memberInfo.namuji_address}" readonly disabled/>
							<input name="center_tel" type="text" class="center_tel" value="&#128222; ${memberInfo.owner_tel1}-${memberInfo.owner_tel2}-${memberInfo.owner_tel3}" readonly disabled/>
						</td>
					</tr>
				</table>
				<div class="line" style="width:950px; height:1px; border:1px solid rgb(244,244,244);"></div>
				
			   <!-- 소개 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128588;&nbsp;소개</p>
			   <div>
			   		<textarea id="intro_introduce" name="intro_introduce" style="padding:5px;" rows="5" cols="90" maxlength="4000" placeholder="내용을 입력하세요.(최대 4000자)">${ownerPageVO.intro_introduce}</textarea>
			   </div>
			   
			   <!-- 공지사항 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128227;&nbsp;공지사항</p>
			   <div>
			   		<textarea id="intro_notice" name="intro_notice" style="padding:5px;" rows="5" cols="90" maxlength="4000" placeholder="내용을 입력하세요.(최대 4000자)">${ownerPageVO.intro_notice}</textarea>
			   </div>
			   
			   <!-- 운영시간 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128336;&nbsp;운영시간</p>
			   <div>
			   		<textarea id="intro_time" name="intro_time" style="padding:5px;" rows="5" cols="90" maxlength="4000" placeholder="내용을 입력하세요.(최대 4000자)">${ownerPageVO.intro_time}</textarea>
			   </div>
			   
			   <!-- 운영프로그램 -->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128203;&nbsp;운영프로그램</p>
			   <div>
			   		<textarea id="intro_program" name="intro_program" style="padding:5px;" rows="5" cols="90" maxlength="4000" placeholder="내용을 입력하세요.(최대 4000자)">${ownerPageVO.intro_program}</textarea>
			   </div>
			   
			   <!-- 부가 서비스-->
			   <p style="font-size:20px; color:#0F0573; margin:40px 0px 30px 0px;">&#128077;&nbsp;부가 서비스</p>
			   <div>
			   		<textarea id="intro_service" name="intro_service" style="padding:5px;" rows="5" cols="90" maxlength="4000" placeholder="내용을 입력하세요.(최대 4000자)">${ownerPageVO.intro_service}</textarea>
			   </div>
			   
			   <div>
				   <a href="javascript:ownerPageModify('${memberInfo.member_id}');" class="modify_btn">수정하기</a>
			   </div>
               
			</div><!-- contain_right END -->
		  </div><!-- contain END -->
		</div><!-- con END -->
	</div><!-- con-min-width END -->
</body>
</html>