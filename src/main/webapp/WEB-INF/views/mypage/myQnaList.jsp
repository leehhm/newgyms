<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="questionList"  value="${questionList }"  />
<c:set var="answerList"  value="${answerList }"  />
 <%
   //치환 변수 선언합니다.
    pageContext.setAttribute("crcn" , "\n"); //Ajax로 변경 시 개행 문자 
    pageContext.setAttribute("br", "<br/>"); //br 태그
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

/* 문의글 작성 팝업 */
function qnaPopup(type) {
	if (type == 'open') {
		$('#qna_modify_popup').attr('style', 'visibility:visible');
		
	} else if (type == 'close') {
		$('#qna_modify_popup').attr('style', 'visibility:hidden');
	}
}

/* 팝업창에 문의내용 출력 */
$(document).ready(function() {
	$('.modify_qna_btn').on('click', function() { 
		$("#modify_qna_secret").prop("checked", false);
		
		var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
		
		var currentQnaNo = thisRow.find('#current_qna_no').val();
		$('#currentQna_no').val(currentQnaNo);	//선택된 qna_no 값을 옵션 input에 넣기
		
		var currentQnaTitle = thisRow.find('#current_qna_title').val();
		$('#modify_qna_title').val(currentQnaTitle);	//현재 qna_title 값을 옵션 input에 넣기
		
		var currentQnaContents = thisRow.find('#current_qna_contents').val();
		$('#modify_qna_contents').val(currentQnaContents);	//현재 qna_contents 값을 옵션 textarea에 넣기
		
		var currentQnaSecret = thisRow.find('#current_qna_secret').val(); //비밀글인 경우 비밀글 checkbox 체크하기
		if (currentQnaSecret == 1) {
			$("#modify_qna_secret").prop("checked", true);
		}
		
	});
});

</script>
</head>
<body>
<div class="con-min-width">
	<div class="con">
		<div id="contain">
			<!-- 마이페이지 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
			<div id="contain_right">
			<p id="mypage_Qna_title">Q&A 관리</p>

			<div style="font-size: 20px; margin-right:50px; margin-bottom:15px; text-align:right;">
				작성한 게시글 : <span id="navy_color">${fn:length(questionList)}개</span>
			</div>

      <table class="qna_list">
          <tbody>      
		            <tr>
		              <th>번호</th>
		              <th></th>
		              <th>제목</th>
		              <th>답변상태</th>
		              <th>작성일</th>              
		            </tr>
		
			 <c:choose>
			   <c:when test="${ empty questionList  }" >
				   <tr>
						<td colspan="5" align="center" style="color: blue;">등록된 Q&A가 없습니다. 😂</td>
			 		</tr>
			   </c:when>
			   <c:otherwise>
		  		 <c:set  var="qna_count" value="0" />   
		            <c:forEach var="question" items="${questionList }" >
					<c:set  var="qna_count" value="${qna_count+1 }" /> 
			          <tr class="qna_item">
			            <td>
							<input id="current_qna_no" type="hidden" value="${question.qna_no}" />
				            ${question.qna_no }
			            </td>
			            <td class="product_info">
				            <c:if test="${question.product_id != 0 }"> 
								<div id="product_main_image">
									<img alt="상품 이미지" src="${contextPath}/thumbnails.do?product_id=${question.product_id}&fileName=${question.product_main_image}">
								</div>
								<div>
									<a class="product_name" href="${contextPath}/product/productDetail.do?product_id=${question.product_id}"><span id="product_name">${question.product_name }</span></a> 
								</div>
								<div>
								<a class="center_name" href="#"> <span id="center_name"></span>${question.center_name }</a>
								</div>
				            </c:if>
			            </td>
			            <td class="toggle_show" style="cursor:pointer; ">
							<c:if test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
					            	<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
							 </c:if>
					            	${question.qna_title}                  
			            </td>
						<td>
			              ${question.qna_answer_state }
			            </td>
						   
			            <td class="qna_writeDate">
			              ${question.qna_write_date}
			            </td>
	            		<td>
			            	<div class="btn">
								<!-- 수정창에서 이용하기 위해  -->            
				            	<input id="current_qna_title" type="hidden" value="${question.qna_title}"> 
				            	<input id="current_qna_contents" type="hidden" value="${question.qna_contents}"> 
				            	<input id="current_qna_secret" type="hidden" value="${question.qna_secret}"> 
			            	
			            		<c:if test="${question.qna_answer_state == '답변대기' }">
					    	      <a class="modify_qna_btn btn1" href="javascript:qnaPopup('open', '.layer01');">수정하기</a>
			            		</c:if>
				    	      <a class="btn2" href="${contextPath}/mypage/removeQna.do?qna_no=${question.qna_no}">삭제하기</a>
			            	</div>
	            		</td>
			          </tr>
		         
			          <tr class="toggle_hidden">
				          
						    <!-- QnA 질문 내용, 답변 제목, 내용 -->     
				            <td colspan="6" class="qna_contents"  >
				            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
				            	
				            <c:forEach var="answer" items="${answerList }" > 
					            	<c:if test="${question.qna_no == answer.qna_parent_no }"> 
						            	<p><span class="A_mark">A</span> ${answer.qna_title} </p> <!-- 답글 제목 -->
						            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
					            	</c:if>
				            </c:forEach>                     
				            	
				            </td>
			    	      </tr>
		              </c:forEach>
			     </c:otherwise>
			</c:choose>
        </tbody>
      </table>
			
			<!-- Q&A 수정하기 팝업 -->
			<div id="qna_modify_popup"  style="visibility: hidden" >
				<p style="float:left">Q&A 수정하기</p>
				<a style="float:right" class="x_button"  href="javascript:" onClick="javascript:qnaPopup('close', '.layer01');">X</a>		
				<form action="${contextPath }/mypage/modifyQuestion.do" method="post">
					<div>
						<input type="hidden" id="currentQna_no" name="qna_no">				
					</div>
					<div class="qna_text">
						<div class="qna_title">제목 <input id="modify_qna_title" name="qna_title" type="text" required title="제목을 입력해주세요."></div>
						<div class="qna_contents">내용 <textarea id="modify_qna_contents" name="qna_contents" cols="50" rows="10" required title="내용을 입력해주세요."></textarea></div>
					</div>
				
					<div style="float:right; padding:5px 20px;">
						<input type="checkbox" id="modify_qna_secret" name="secret" value="1"><span style="font-size:14px; margin-left:5px">비밀글</span>
					</div>
				
					<div>
						<input class="qna_modify" type="submit" value="저장하기">
					</div>
				</form>
			</div>
			
			</div>
		</div>
	</div>
</div>
</body>
</html>