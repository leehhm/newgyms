<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/customer.css" rel="stylesheet" />
<script type="text/javascript">
	/* 문의글 작성 팝업 */
	function qnaPopup(isLogOn, loginForm, type) {
		if (isLogOn == '' || isLogOn == 'false') {
			alert("로그인 후 글쓰기가 가능합니다.");
			location.href = loginForm;
		} else {
			if (type == 'open') {
				$('#qna_write_popup').attr('style', 'visibility:visible');
				
			} else if (type == 'close') {
				$('#qna_write_popup').attr('style', 'visibility:hidden');
			}
		}
	}

</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 커뮤니티 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/common/customerSide.jsp" />

				<div id="contain_right">
					<p id="qna_title">Q&A</p>

					<span>&nbsp; 총 ${fn:length(questionList)}건</span> 
		        
		          <table class="qna_list">
		          <tbody>      
					 <c:choose>
					   <c:when test="${ empty questionList  }" >
						   <tr>
								<td>등록된 Q&A가 없습니다.</td>
					 		</tr>
					   </c:when>
					   <c:otherwise>
				
				            <tr>
				              <th>번호</th>
				              <th>답변상태</th>
				              <th>제목</th>
				              <th>작성자</th>
				              <th>작성일</th>              
				            </tr>
				
				  		 <c:set  var="qna_count" value="0" />   
				            <c:forEach var="question" items="${questionList }" > 
							<c:set  var="qna_count" value="${qna_count+1 }" /> 
					          <tr class="qna_item">
					            <td>
					            ${qna_count }
					            </td>	   
					            <td>
					              ${question.qna_answer_state }
					            </td>
									<c:choose>
										<c:when test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
											<c:choose>
												<c:when test="${question.member_id ==loginMember_id  or loginMember_id == 'admin'}"> <!-- 작성자와 로그인한 사람이 같거나 관리자로 로그인 한 경우 -->
										            <td class="toggle_show" style="cursor:pointer;">
										            	<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
										            	${question.qna_title}                           
										            </td>
												</c:when>
											
											<c:otherwise> <!-- 작성자와 로그인한 사람이 다르거나 로그인하지 않은 경우 -->
									            	<td class="toggle_show" style="cursor:pointer;">                        
									            	<img style="width:24px;height:24px;display:inline;text-align: center"src="https://iconmonstr.com/wp-content/g/gd/makefg.php?i=../releases/preview/2012/png/iconmonstr-lock-4.png&r=0&g=0&b=0" alt="비밀글">
									            	비밀글입니다.
									         	   </td>
											</c:otherwise>
											</c:choose>
										 </c:when>
										<c:otherwise> <!-- 공개글인 경우 -->
								            <td class="toggle_show" style="cursor:pointer;">
								            	${question.qna_title}                           
								            </td>
										</c:otherwise>
								   </c:choose>
					            <td class="qna_writer">
					              ${question.member_id}
					            </td>
					            <td class="qna_writeDate">
					              ${question.qna_write_date}
					            </td>
					          </tr>
				         
					          <tr class="toggle_hidden">
								    <!-- QnA 질문 내용, 답변 제목, 내용 -->     
								   	<c:choose>
										<c:when test="${question.qna_secret==1}"> <!-- 비밀글인 경우 -->
											<c:choose>
											<c:when test="${question.member_id ==loginMember_id  or loginMember_id == 'admin'}"> <!-- 작성자와 로그인한 사람이 같거나 관리자로 로그인 한 경우 -->
									            <td class="qna_contents" colspan="5">
									            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
									            <c:forEach var="answer" items="${answerList }" >             	
									            	<c:choose>
										            	<c:when test="${question.qna_no == answer.qna_parent_no }"> 
											            	<p><span class="A_mark">A</span> ${answer.qna_title} <p> <!-- 답글 제목 -->
											            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
									            		</c:when>
									            	</c:choose>
									            	</c:forEach>                   
									            </td>
											</c:when>
											<c:otherwise> <!-- 작성자와 로그인한 사람이 다르거나 로그인하지 않은 경우 -->
								            	<td colspan="5" class="qna_contents">                        
								            	 <p>비밀글은 작성자만 조회할 수 있습니다.</p>
								         	   </td>
											</c:otherwise>
											</c:choose>
								        </c:when>
								        
								        <c:otherwise> <!-- 공개글인 경우 -->
								            <td colspan="5" class="qna_contents"  >
								            	<p><span class="Q_mark">Q</span> ${fn:replace(question.qna_contents ,crcn,br)} </p>  <!-- 질문 내용 -->
								            <c:forEach var="answer" items="${answerList }" > 
								            	<c:choose>
									            	<c:when test="${question.qna_no == answer.qna_parent_no }"> 
										            	<p><span class="A_mark">A</span> ${answer.qna_title} </p> <!-- 답글 제목 -->
										            	<p style="padding-left:40px;">${fn:replace(answer.qna_contents ,crcn,br)} <p> <!-- 답글 내용 -->
								            		</c:when>
								            	</c:choose>
								            	</c:forEach>                     
								            </td>
								        </c:otherwise>
									  </c:choose>
					    	      </tr>
				              </c:forEach>
					     </c:otherwise>
					</c:choose>
		        </tbody>
		      </table>
		      
		        <a class="qna_write" href="javascript:qnaPopup('${isLogOn}', '${contextPath}/member/loginForm.do','open', '.layer01');">문의하기</a>
		      
		      
	      	<!-- Q&A 문의하기 팝업 -->
			<div id="qna_write_popup"  style="visibility: hidden" >
				<p style="float:left">문의하기</p>
				<a style="float:right" class="x_button"  href="javascript:" onClick="javascript:qnaPopup('${isLogOn}','${contextPath}/member/loginForm.do','close', '.layer01'); window.location.reload()">X</a>		
				<form action="${contextPath }/qna/addQuestion.do" method="post">
					<div class="product_info">
						<input type="hidden" name="product_id" value="0">
					</div>
				
					<div class="qna_text">
						<div class="qna_title">제목 <input name="qna_title" type="text" required title="제목을 입력해주세요."></div>
						<div class="qna_contents">내용 <textarea name="qna_contents" cols="50" rows="10" style="width: 482px; vertical-align: top;" required  title="내용을 입력해주세요."></textarea></div>
					</div>
				
					<div style="float:right; padding:5px 20px;">
						<input type="checkbox" name="secret"><span style="font-size:14px; margin-left:5px">비밀글</span>
					</div>
					<div>
						<input class="qna_write" type="submit" value="등록하기">
					</div>
				</form>
			</div>
			
				</div>
			</div>
		</div>
	</div>
</body>
</html>