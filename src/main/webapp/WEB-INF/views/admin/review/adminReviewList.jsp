<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
<script src="${contextPath}/resources/jquery/owner_review.js"
	type="text/javascript"></script>
<script type="text/javascript">
	//수정창 열고 닫기
	function reviewPopup(type) {
		if (type == 'open') {
			$('#review_write_popup').attr('style', 'visibility:visible');

		} else if (type == 'close') {
			$('#review_write_popup').attr('style', 'visibility:hidden');
		}
	}
</script>
</head>
<body>
	<form action="${contextPath}/admin/review/orderReviewList.do"
		method="post" enctype="multipart/form-data">
		<input type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 관리자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />
					<div id="contain_right">
						<p id="mypage_order_title">이용후기 관리</p>
						<p style="font-size: 15px;">총 ${maxnum}건
						<!-- <a class="btn-open-popup" href="#modal1" style="font-size: 20px; float: right; margin-right: 10px;">조회하기</a></p> -->
											 	
						<div style="border-bottom: 1px solid #D8D8D8; margin-top: 13px;"></div>
						<c:choose>
							<c:when test="${empty adminReviewList}">
								<table id="article_detail">
									<tr>
										<th width="10%">번호</th>
										<th width="15%">상품명</th>
										<th width="40%" colspan="2">제목</th>
										<th width="15%">작성자</th>
										<th width="10%">등록일</th>
										<th width="10%">삭제</th>
									</tr>
									<tr>
										<td colspan="7" style="color: blue;">조회된 결제 내역이 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="article_detail" align=center>
									<tr>
										<th width="90px;">번호</th>
										<th width="200px;">상품명</th>
										<th width="200px;">제목</th>
										<th width="90px;">평점</th>
										<th width="90px;">작성자</th>
										<th width="90px;">작성일</th>
										<th width="90px;">삭제</th>
									</tr>
									<c:forEach var="item" items="${adminReviewList}"
										varStatus="status">
										<c:set var="i" value="${i+1}" />
										<tr class="owner_review_title" style="cursor: pointer;">
											<!-- 번호 -->
											<td width="10%" style="font-size: 14px;">${maxnum-i+1}</td>
											<!-- 상품명 -->
											<td width="25%" style="text-align: center;">${item.product_name }<br>
											</td>
											<!-- 제목 -->
											<td width="30%" style="font-size: 14px;">${item.review_title}</td>
											<!-- 별점 -->
											<td class="review_score"><c:choose>
													<c:when test="${item.review_score == 1  }">
														<div class="review_score">★☆☆☆☆</div>
													</c:when>
													<c:when test="${item.review_score == 2  }">
														<div class="review_score">★★☆☆☆</div>
													</c:when>
													<c:when test="${item.review_score == 3  }">
														<div class="review_score">★★★☆☆</div>
													</c:when>
													<c:when test="${item.review_score == 4  }">
														<div class="review_score">★★★★☆</div>
													</c:when>
													<c:otherwise>
														<div class="review_score">★★★★★</div>
													</c:otherwise>
												</c:choose></td>
											<!-- 작성자 -->
											<td width="15%" style="font-size: 14px;">${item.member_id}</td>
											<!-- 등록일 -->
											<td width="10%" style="font-size: 14px;">${item.review_write_date}</td>
											<!-- 삭제버튼 -->
											<td width="10%"><a
												href="${contextPath}/admin/review/adminReviewDelete.do?review_no=${item.review_no}"
												style="line-height: 32px;"><span id="admin_delete_btn">삭제</span></a> <br></td>
										</tr>
										<tr class="owner_review_hidden">
											<!-- 옵션 -->
											<td id="gray_color"
												style="font-size: 14px; text-align: center;" colspan="7">
												[옵션] ${item.product_option_name}(+<fmt:formatNumber
													value="${item.product_option_price}" type="number" />원) <!-- 내용 -->
												<br> ${item.review_contents} <br> <!-- 이미지 --> <c:forEach
													var="ril" items="${reviewImageList}" varStatus="status">
													<c:if test="${ril.review_no == item.review_no}">
														<img alt="이용후기 이미지"
															style="width: 250px; display: inline; margin: 0px auto;"
															src="${contextPath}/reviewImage.do?review_no=${item.review_no}&fileName=${ril.fileName}">
													</c:if>
												</c:forEach>
											</td>
										</tr>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div style="text-align: center;">

					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/5)}"
						step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a
							href="${contextPath}/admin/review/adminReviewList.do?chapter=${section_num}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">${section_num }</a>
					</c:forEach>

				</div>
			</div>
		</div>
	</form>
</body>
</html>