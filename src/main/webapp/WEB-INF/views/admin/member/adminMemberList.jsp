<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
</head>
<body>
	<form action="${contextPath}/admin/member/adminMemberList.do" method="get">
		<input type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 관리자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />
					<div id="contain_right">
						<p id="admin_member_title">회원 관리</p>
						<p style="font-size:15px;">총 ${maxnum}건</p>
						<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>

						<c:choose>
							<c:when test="${empty adminMemberList}">
								<table id="admin_member_table" align=center>
									<tr>
										<th width="5%">번호</th>
										<th width="10%">구분</th>
										<th width="25%">아이디</th>
										<th width="15%">이름</th>
										<th width="15%">휴대폰번호</th>
										<th width="15%">가입일</th>
										<th width="10%">상태</th>
									</tr>
									<tr>
										<td colspan="8" style="color: blue;">가입한 회원이 없습니다. 😂</td>
									</tr>
								</table>

							</c:when>
							<c:otherwise>
								<table id="admin_member_table" align=center>
									<tr>
										<th width="10%">구분</th>
										<th width="25%">아이디</th>
										<th width="15%">이름</th>
										<th width="15%">휴대폰번호</th>
										<th width="20%">가입일</th>
										<th width="10%">상태</th>
									</tr>

									<c:forEach var="member" items="${adminMemberList}">
										<tbody>
											<tr>
												<!-- 구분 -->
												<td>
													<c:choose>
														<c:when test="${member.join_type == '101'}"><span style="color:#130875;">일반</span></c:when>
														<c:when test="${member.join_type == '102' && member.member_id != 'admin'}"><span style="color:#F9C200;">사업자</span></c:when>
														<c:otherwise><span style="color:red;">관리자</span></c:otherwise>
													</c:choose>
												</td>
												
												<!-- 아이디 -->
												<td>
													<a href="${contextPath}/admin/member/memberDetail.do?member_id=${member.member_id}">${member.member_id}</a>
												</td>
												
												<!-- 이름 -->
												<td>${member.member_name}</td>
												
												<!-- 휴대폰 번호 -->
												<td>${member.hp1}-${member.hp2}-${member.hp3}</td>
														
												<!-- 가입일 -->
												<td><fmt:formatDate pattern="yyyy-MM-dd" value="${member.join_date}" /></td>
												
												<!-- 상태 -->
												<td>
													<c:choose>
														<c:when test="${member.del_yn == 'N'}">사용중</c:when>
														<c:otherwise>탈퇴</c:otherwise>
													</c:choose>
												</td>
											</tr>
											
										</tbody>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div style="text-align: center; margin-left:240px;">
					<a
						href="${contextPath}/admin/product/adminProductList.do?chapter=${chapter-1}"> &#60;</a>
					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/10)}" step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a href="${contextPath}/admin/member/adminMemberList.do?chapter=${section_num}">${section_num}</a>
					</c:forEach>
					<a
						href="${contextPath}/admin/member/adminMemberList.do?chapter=${chapter+1}"> &#62;</a>

				</div>
			</div>
		</div>
	</form>
</body>
</html>