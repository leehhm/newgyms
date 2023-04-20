<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html >
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/community.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	// 글 수정하기
	function fn_modify_article(url, article_no) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var article_no_Input = document.createElement("input");
		article_no_Input.setAttribute("type", "hidden");
		article_no_Input.setAttribute("name", "article_no");
		article_no_Input.setAttribute("value", article_no);
		
		form.appendChild(article_no_Input);
		document.body.appendChild(form);
		form.submit();
	}
	
	// 글 삭제하기
	function fn_remove_article(url, article_no) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var article_no_Input = document.createElement("input");
		article_no_Input.setAttribute("type", "hidden");
		article_no_Input.setAttribute("name", "article_no");
		article_no_Input.setAttribute("value", article_no);

		form.appendChild(article_no_Input);
		document.body.appendChild(form);
		form.submit();
	}
	
	// 답글 달기
	function fn_reply_form(url, parent_no) {
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		var parent_no_Input = document.createElement("input");
		parent_no_Input.setAttribute("type", "hidden");
		parent_no_Input.setAttribute("name", "parent_no");
		parent_no_Input.setAttribute("value", article_no);

		form.appendChild(parent_no_Input);
		document.body.appendChild(form);
		form.submit();
	}

	// 사진 미리보기 경로
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	// 댓글 달기
	function fn_add_reply(article_no) {
		var reply_id = $('#reply_id').val();
		var reply_content = $('#reply_content').val();
		console.log(reply_id)
		console.log(reply_content)
		console.log(article_no)
		
		if (reply_content == '') {
			alert("내용을 입력해주세요 :( ");
			return;
		} 

	    $.ajax({
		       type : "post",
		       async : false,
		       url : "${contextPath}/board/addReply.do",
		       data : {
		          article_no:article_no,
		          reply_id:reply_id,
		          reply_content:reply_content
		       },
		       success : function(data, textStatus) {
		       		if(data.trim()=='success'){
			      		alert("댓글이 등록되었습니다."); 
				  		location.reload();
		       		} else {
			          	alert("오류가 발생했습니다. :) ");   
		          		location.reload();
		       		}	
		       },
		       error : function(data, textStatus) {
		          alert("에러가 발생했습니다."+data);
		       },
		       complete : function(data, textStatus) {
		          //alert("작업을완료 했습니다");
		       }
		    }); //end ajax    */
		}
	
	// 댓글 삭제
	function fn_remove_reply(reply_no) {
	    $.ajax({
		       type : "post",
		       async : false,
		       url : "${contextPath}/board/removeReply.do",
		       data : {
		          reply_no:reply_no
		       },
		       success : function(data, textStatus) {
		       		if(data.trim()=='success'){
			      		alert("댓글이 삭제되었습니다."); 
				  		location.reload();
		       		} else {
			          	alert("오류가 발생했습니다. :) ");   
		          		location.reload();
		       		}	
		       },
		       error : function(data, textStatus) {
		          alert("에러가 발생했습니다."+data);
		       },
		       complete : function(data, textStatus) {
		          //alert("작업을완료 했습니다");
		       }
		    }); //end ajax    */
		}
</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 커뮤니티 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/common/communitySide.jsp" />

				<div id="contain_right">
					<form name="frmArticle" method="post" action="${contextPath}"
						enctype="multipart/form-data">

						<table style="width: 100%">
							<tr>
								<!-- 글 제목 -->
								<td align=left><input type="text" 
									value="${article.board_title}" name="board_title"
									id="article_title" style="margin-left: 15px;" disabled>
									<br></td>

								<!-- 글쓴이 & 작성일 -->
								<td align=right style="padding-top: 90px;"><span
									id="gray_color" style="font-size: 12px;">작성자 &nbsp; <input
										type="text" value="${article.member_id}" name="member_id"
										id="member_id" disabled
										style="color: #848484; font-size: 12px; background-color: white; border: none;"></span>
									<span id="gray_color"
									style="font-size: 12px; text-align: right; margin-top: 68px; margin-bottom: 5px;">작성시간
										&nbsp;<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
											value="${article.board_write_date}" />
								</span></td>
							</tr>
						</table>

						<table id="article_table" align=center>

							<!-- 글 내용 -->
							<tr>
								<td colspan="3" align=left>${article.board_content}</td>
							</tr>

							<!-- 사진 -->
							<c:if test="${not empty article.board_image && article.board_image != 'null' }">
								<tr>
									<td width="300px"><input type="hidden"
										name="originalFileName" value="${article.board_image}" /> <img
										src="${contextPath}/boardImage.do?article_no=${article.article_no}&board_image=${article.board_image}"
										id="preview" /><br></td>
								</tr>
							</c:if>
							
							<!-- 게시글 작성자에게만 보이는 수정/삭제 버튼 -->
							<c:if test="${memberInfo.member_id == article.member_id || memberInfo.member_id == 'admin'}">
								<tr>
									<td width="50%" align=right>
										<input type="button" id="modify_btn" value="수정하기"
											onclick="fn_modify_article('${contextPath}/board/modArticleForm.do', ${article.article_no})">
									</td>
									<td width="50%" align=left>
										<input type="button" id="delete_btn" value="삭제하기"
										onClick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.article_no})">
									</td>
								<tr>
							</c:if>

						</table>
						
						<!-- 댓글 목록 -->
						<c:if test="${not empty replyList}">
							<p style="font-size:13px; color:#848484; margin-top:15px;">댓글 ${fn:length(replyList)}개</p>
							<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>
							
							<c:forEach var="item" items="${replyList}" varStatus="j">
								<table id="reply_list" align=center>
								<!-- 아이디 -->
								<tr>
									<td align=left style="font-size:14px;">
									<c:choose>
										<c:when test="${item.member_id == 'admin'}">
                  							<div class="admin_icon" style="color:red;">관리자</div>
										</c:when>
										<%-- <c:when test="${item.join_type == '102'}">
											<div class="owner_icon" style="color:#F9C200;">사업자</div>
										</c:when>
										<c:otherwise>
											<div class="member_icon">일반</div>
										</c:otherwise> --%>
									</c:choose>
										${item.member_id}
									</td>
									<td rowspan="3" width="10%" align=right>
										<c:if test="${memberInfo.member_id == item.member_id || memberInfo.member_id == 'admin'}">
											<a href="javascript:fn_remove_reply('${item.reply_no}');">x</a>
										</c:if>
									</td>
								</tr>
	
								<!-- 내용 -->
								<tr>
									<td>${item.reply_content}</td>
								</tr>
								
								<!-- 댓글 작성 시간 -->
								<tr>
									<td><span id="gray_color" style="font-size:10px;"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${item.reply_write_date}" /></span></td>
								</tr>
								</table>
							</c:forEach>
						</c:if>
						
						<c:choose>
							<c:when test="${isLogOn == true}">
						<input type="hidden" id="reply_id" name="reply_id" value="${memberInfo.member_id}" class="reply_inputbox">
						<table id="reply_write_table" align=center>
							<!-- 아이디 -->
							<tr>
								<td align=left style="font-size:12px;"><b>${memberInfo.member_id}</b></td>
							</tr>

							<!-- 내용 -->
							<tr>
								<td width="80%" align=left>
									<textarea id="reply_content" name="reply_content" cols="105" rows="3" placeholder="😁 뉴짐스가 더 훈훈해지는 댓글 부탁드립니다. 바른말 고운말 쓰기 !! 😁" style="padding:5px; max-width:100%;" ></textarea>
								</td>
							</tr>
							<tr>
								<td align=right>
									<a href="javascript:fn_add_reply('${article.article_no}');"><span id="reply_submit_btn">등록하기</span></a>
								</td>
							</tr>
						</table>
							</c:when>
							<c:otherwise>
							<table id="reply_write_table" align=center>

							<!-- 내용 -->
							<tr>
								<td width="80%" align=left>
									<textarea id="reply_content" name="reply_content" cols="105" rows="3" placeholder="😁 댓글을 작성하려면 로그인을 먼저 해주세요. 😁" style="text-align:center; padding-top:30px; max-width:100%;" readonly></textarea>
								</td>
							</tr>
						</table>
							</c:otherwise>
						</c:choose>
						
						<div style="text-align:right; margin-top:15px;">
							<a href="${contextPath}/board/listArticles.do" style="line-height:32px;"><span id="btn_1">목록으로</span></a>
						</div>
					</form>

				</div>
			</div>
		</div>
	</div>
</body>
</html>

