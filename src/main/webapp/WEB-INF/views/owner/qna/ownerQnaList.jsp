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
<link href="${contextPath}/resources/css/owner.css" rel="stylesheet" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script>

/* 문의글 작성 팝업 */
function qnaPopup(type, layer) {
	if (type == 'open') {
		if(layer == 'write') {
			$('#qna_modify_popup').attr('style', 'visibility:hidden');
			$('#qna_write_popup').attr('style', 'visibility:visible');
		}
		else if (layer == 'modify') {
			$('#qna_write_popup').attr('style', 'visibility:hidden');
			$('#qna_modify_popup').attr('style', 'visibility:visible');
		}
	} else if (type == 'close') {
		if(layer == 'write') {
			$('#qna_write_popup').attr('style', 'visibility:hidden');
		}
		else if (layer == 'modify') {
			$('#qna_modify_popup').attr('style', 'visibility:hidden');
		}
	}
}

//답변 작성
$(document).ready(function() {
	$('.qna_write_btn').on('click', function() { 
		
		var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
		
		var currentQnaNo = thisRow.find('#current_qna_no').val();
		$('#currentQna_no').val(currentQnaNo);	//선택된 qna_no 값을 옵션 input에 넣기
		
		var currentProductId = thisRow.find('#current_product_id').val();
		$('#currentProduct_id').val(currentProductId);	//선택된 product_id 값을 옵션 input에 넣기
		
		var currentQnaSecret = thisRow.find('#current_qna_secret').val(); //비밀글인 경우 
		$('#qna_secret').val(currentQnaSecret);	//선택된 product_id 값을 옵션 input에 넣기
		
	});
});

//답변 수정
/* 팝업창에 문의내용 출력 */
$(document).ready(function() {
	$('.answer_modify_btn').on('click', function() { 
		
		var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
		
		var currentQnaNo = thisRow.find('#currentQnaNo').val();
		$('#modifyQna_no').val(currentQnaNo);	//선택된 qna_no 값을 옵션 input에 넣기
		
		var currentAnswerTitle = thisRow.find('#currentAnswerTitle').val();
		$('#modify_qna_title').val(currentAnswerTitle);	//현재 qna_title 값을 옵션 input에 넣기
		console.log(currentAnswerTitle)
		var currentAnswerContents = thisRow.find('#currentAnswerContents').val();
		$('#modify_qna_contents').val(currentAnswerContents);	//현재 qna_contents 값을 옵션 textarea에 넣기
	});
});

</script>
</head>
<body>
<div class="con-min-width">
	<div class="con">
		<div id="contain">
			<!-- 사업자 페이지 사이드 메뉴 -->
			<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
			
			<div id="contain_right">
			<p id="owner_Qna_title">Q&A 관리</p>

			<div style="font-size: 20px; margin-right:50px; margin-bottom:15px; text-align:right;">
				문의글 : <span id="navy_color">${fn:length(questionList)}개</span>
			</div>

      <table class="qna_list">
          <tbody>      
		            <tr>
		              <th>번호</th>
		              <th>답변상태</th>
		              <th>제목</th>
		              <th>작성자</th>              
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
				            ${question.qna_no }
			            </td>
			            
			            <td>
			              ${question.qna_answer_state }
			            </td>
			            <td class="toggle_show" style="cursor:pointer; ">
			            
							<c:if test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
					            	<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
							 </c:if>
					            	${question.qna_title}                  
			            </td>
					   
<%-- 					   <td class="product_info">

							<div id="product_main_image">
								<img alt="상품 이미지" src="${contextPath}/thumbnails.do?product_id=${question.product_id}&fileName=${question.product_main_image}">
							</div>
							<div>
								<a class="product_name" href="${contextPath}/product/productDetail.do?product_id=${question.product_id}"><span id="product_name">${question.product_name }</span></a> 
							</div>
							<div>
							<a class="center_name" href="#"> <span id="center_name"></span>${question.center_name }</a>
							</div>
			            </td>
 --%>					   
						<td>
			              ${question.member_id }
			            </td>
						   
			            <td class="qna_writeDate">
			              ${question.qna_write_date}
			            </td>
	            
            	      <td>
			    	      <a class="btn2" href="${contextPath}/owner/qna/removeQna.do?qna_no=${question.qna_no}">삭제하기</a>
		    	      </td>
	            
			          </tr>
		         
			          <tr class="toggle_hidden">
				          <td>
				          </td>
						    <!-- QnA 질문 내용, 답변 제목, 내용 -->     
				            <td colspan="5" class="qna_contents"  >
				            
				            <div id="product_main_image">
								<img alt="상품 이미지" src="${contextPath}/thumbnails.do?product_id=${question.product_id}&fileName=${question.product_main_image}">
							</div>
							<div>
								<a class="product_name" href="${contextPath}/product/productDetail.do?product_id=${question.product_id}"><span id="product_name">${question.product_name }</span></a> 
							</div>
							<div>
							<a class="center_name" href="#"> <span id="center_name"></span>${question.center_name }</a>
							</div>
				            
				            
				            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
				            	<c:choose>
				            	<c:when test="${question.qna_answer_state == '답변대기' }">
						            	
           					            <!-- 답글작성창에 띄우기 위해 hidden 처리 -->
			   							<input id="current_product_id" type="hidden" value="${question.product_id}" />
			   							<input id="current_qna_no" type="hidden" value="${question.qna_no}" />
			   							<input id="current_qna_secret" type="hidden" value="${question.qna_secret}" />
				            		 <a class="qna_write_btn btn1" href="javascript:qnaPopup('open', 'write');">답변하기</a>
				            	 </c:when>
				            	 <c:otherwise>
						            <c:forEach var="answer" items="${answerList }" > 
							            	<c:if test="${question.qna_no == answer.qna_parent_no }"> 
								            	<p><span class="A_mark">A</span> ${answer.qna_title}</p> <!-- 답글 제목 -->
								            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
									            	<!-- 답글수정창에서 이용하기 위해 hidden 처리 -->
									            	<input id="currentQnaNo" type="hidden" value="${answer.qna_no}">
									            	<input id="currentAnswerTitle" type="hidden" value="${answer.qna_title}">
									            	<input id="currentAnswerContents" type="hidden" value="${answer.qna_contents}">
									            	
				       				    	      <a class="answer_modify_btn btn1" href="javascript:qnaPopup('open', 'modify');">답변 수정</a>
				       				    	      <a class="qna_delete_btn btn2" href="${contextPath}/owner/qna/removeAnswer.do?qna_no=${answer.qna_no}&qna_parent_no=${answer.qna_parent_no}">답변 삭제</a>
							            	</c:if>
						            </c:forEach> 
				            	 </c:otherwise>
				            	</c:choose>
				            </td>
			    	      </tr>
		              </c:forEach>
			     </c:otherwise>
			</c:choose>
        </tbody>
      </table>
			
			<!-- Q&A 답글달기 팝업 -->
			<div id="qna_write_popup"  style="visibility: hidden" >
				<p style="float:left">Q&A 답변 등록하기</p>
				<a style="float:right" class="x_button"  href="javascript:" onClick="javascript:qnaPopup('close', 'write');">X</a>		
				<form action="${contextPath }/owner/qna/addAnswer.do" method="post">
					<div>
						<input type="hidden" id="currentQna_no" name="qna_parent_no">				
						<input type="hidden" id="currentProduct_id" name="product_id">				
						<input type="hidden" id="qna_secret" name="qna_secret">				
					</div>
					<div class="qna_text">
						<div class="qna_title">제목 <input name="qna_title" type="text" required title="제목을 입력해주세요."></div>
						<div class="qna_contents">내용 <textarea name="qna_contents" cols="50" rows="10" required title="내용을 입력해주세요."></textarea></div>
					</div>
				
					<div>
						<input style="margin-top:20px;" class="qna_write" type="submit" value="등록하기">
					</div>
				</form>
			</div>
			
			<!-- Q&A 수정하기 팝업 -->
			<div id="qna_modify_popup"  style="visibility: hidden" >
				<p style="float:left">Q&A 답변 수정하기</p>
				<a style="float:right" class="x_button"  href="javascript:" onClick="javascript:qnaPopup('close', 'modify');">X</a>		
				<form action="${contextPath }/owner/qna/modifyAnswer.do" method="post">
					<div>
						<input type="hidden" id="modifyQna_no" name="qna_no">				
					</div>
					<div class="qna_text">
						<div class="qna_title">제목 <input id="modify_qna_title" name="qna_title" type="text" required title="제목을 입력해주세요."></div>
						<div class="qna_contents">내용 <textarea id="modify_qna_contents" name="qna_contents" cols="50" rows="10" required title="내용을 입력해주세요."></textarea></div>
					</div>
				
					<div>
						<input style="margin-top:20px;" class="qna_modify" type="submit" value="저장하기">
					</div>
				</form>
			</div>
			
			</div>
		</div>
	</div>
</div>
</body>
</html>