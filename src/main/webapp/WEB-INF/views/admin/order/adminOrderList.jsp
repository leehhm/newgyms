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
<script src="${contextPath}/resources/jquery/order.js" type="text/javascript"></script>
</head>
<body>
	<form action="${contextPath}/admin/order/adminOrderList.do" method="get">
		<input type="hidden" name="member_id" value="${member_id}">
		<input type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 관리자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />
					<div id="contain_right">
						<p id="mypage_order_title">주문/결제 관리</p>
						<p style="font-size:15px;">총 ${maxnum}건</p>
						<div style="border-bottom: 1px solid #D8D8D8; margin-top:13px;"></div>
						
						<c:choose>
							<c:when test="${empty adminOrderList}">
								<table id="order_detail2" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="13%">결제일자</th>
										<th width="30%" colspan="2">상품정보</th>
										<th width="12%">주문자</th>
										<th width="15%">결제금액</th>
										<th width="15%">선택</th>
									</tr>
									<tr>
										<td colspan="6" style="color: blue;">조회된 결제 내역이 없습니다. 😂</td>
									</tr>
								</table>

							</c:when>
							<c:otherwise>
								<table id="order_detail2" align=center>
									<tr>
										<th width="4%">번호</th>
										<th width="13%">결제일자</th>
										<th width="25%" colspan="2">상품정보</th>
										<th width="12%">주문자 아이디</th>
										<th width="12%">결제금액</th>
										<th width="10%">선택</th>
									</tr>

									<!-- 결제내역 대표 -->
									<c:forEach var="list" items="${adminOrderList}">
										<c:set var="i" value="${i+1}" />
										<tbody>
											<tr>
												<!-- 번호 -->
												<td>${list.order_id}</td>
												
												<!-- 결제일자 -->
												<td style="font-size: 14px;" width="10%">${list.pay_order_time}</td>
												
												<!-- 대표이미지 -->
												<td align=center width="15%">
													<div id="img_file">
														<img alt="img" width="100%" height="100%"
															src="${contextPath}/download.do?product_id=${list.product_id}&fileName=${list.product_main_image}">
													</div>
												</td>
												
												<!-- 상품정보 -->
												<td style="text-align: left; line-height: 25px; font-size: 18px; cursor:pointer;" width="25%" class="ord_title">${list.product_name}
													<span style="color:#848484;">등 ${orderMemberList[i]}건</span>
													<span style="color:#0F0573; font-size:12px;"><br> &nbsp; 상세보기 ▽</span>
												</td>
												
												<!-- 주문자 아이디 -->
												<td>
													<c:choose>
														<c:when test="${fn:length(list.member_id) > 25}">
															비회원
														</c:when>
														<c:otherwise>
															${list.member_id}
														</c:otherwise>
													</c:choose>
												</td>
												
												<!-- 총 결제금액 -->
												<td><fmt:formatNumber value="${list.total_price}"
														type="number" />원</td>
														
												<!-- 상세조회 버튼 -->
												<td><a
													href="${contextPath}/admin/order/adminOrderDetail.do?order_id=${list.order_id}&order_seq_num=${list.order_seq_num}"
													style="line-height: 32px;"><span id="btn_1">상세조회</span></a>
													<br></td>
											</tr>
											
											<!-- 결제내역 전체 리스트 -->
											<tbody class="ord_hidden">
											<c:forEach var="member" items="${orderMember}" varStatus="status">
												<c:choose>
													<c:when test="${list.order_id == member.order_id}">
														<tr>
															<td style="border:none;" colspan="2"></td>
															
															<!-- 이미지 -->
															<td align=center style="border-left:1px solid rgb(244, 244, 244);">
																<div id="img_file">
																	<img alt="img" width="100%" height="100%"
																		src="${contextPath}/download.do?product_id=${member.product_id}&fileName=${member.product_main_image}">
																</div>
															</td>
															
															<!-- 상품명, 옵션 및 옵션가격 -->
															<td style="text-align: left; line-height: 25px;">
															<a href="/newgyms/product/productDetail.do?product_id=${member.product_id}">${member.product_name}</a><br>
																<span id="gray_color" style="font-size: 14px;">[옵션]
																	${member.product_option_name}(+<fmt:formatNumber
																		value="${member.product_option_price}" type="number" />원)
															</span>
															</td>
															
															<td></td>
															<!-- 결제금액 -->
															<td style="line-height:25px;">
																<s style="color:red;"><span id="gray_color">
																	<fmt:formatNumber value="${member.product_price+member.product_option_price}" type="number" />원</span></s>
																	<br>
																	<fmt:formatNumber value="${member.product_sales_price+member.product_option_price}"	type="number" />원</td>
															
															<!-- 주문상태 -->
															<td>
																${member.order_state}
															</td>
														</tr>
													</c:when>
												</c:choose>
											</c:forEach>
											
										</tbody>
									</c:forEach>
								</table>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div style="text-align: center; margin-left:240px;">
					<a
						href="${contextPath}/admin/order/adminOrderList.do?chapter=${chapter-1}&member_id=${member_id}&order_state=${order_state}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">&#60;</a>
					<c:forEach var="page" begin="1" end="${Math.ceil(maxnum/5)}"
						step="1">
						<c:set var="section_num" value="${section_num+1}" />
						<a
							href="${contextPath}/admin/order/adminOrderList.do?chapter=${section_num}&member_id=${member_id}&order_state=${order_state}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">${section_num}</a>
					</c:forEach>
					<a
						href="${contextPath}/admin/order/adminOrderList.do?chapter=${chapter+1}&member_id=${member_id}&order_state=${order_state}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">&#62;</a>

				</div>
			</div>
		</div>
	</form>
</body>
</html>