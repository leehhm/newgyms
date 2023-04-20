<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/admin.css" rel="stylesheet" />
</head>
<body>
	<form action="${contextPath}/admin/order/adminOrderRefund.do" method="post">

		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 사업자 페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/admin/common/adminPageSide.jsp" />

					<div id="contain">
						<div id="contain_right">
							<p id="mypage_order_title">결제취소 및 환불</p>
							<table style="margin-bottom: 3px; padding: 3px;">
								<tr>
									<td>&nbsp;환불번호&nbsp; <span id="gray_color">${adminRefundDetail.refund_id}</span>
									</td>
									<td style="padding-left: 704px;">결제상태 &nbsp; <span
										id="gray_color">${adminOrderDetail[0].order_state}</span>
									</td>
								</tr>
							</table>

							<table id="order_detail">
								<!-- 상품 정보 -->
								<tr>
									<td colspan="7"><span id="mypage_info_title">상품 정보</span></td>
								</tr>

									<!-- 1건의 주문에 대한 상품이 2개 이상일 경우 여러개 표시 -->
								<c:forEach var="item" items="${adminOrderDetail}" varStatus="j">
									<tr>
										<td width="15%"><input type="hidden" name="order_seq_num"
											value="${item.order_seq_num}">
											<div id="img_file">
												<img alt="img" width="100%" height="100%"
													src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
											</div></td>
										<td colspan="2"
											style="width: 40%; line-height: 25px; padding-left: 10px; font-size: 15px;">${item.product_name}<br>
											<span id="gray_color" style="font-size: 13px;">[옵션]
												${item.product_option_name}</span>
										</td>
										<td width="15%" align=center><span id="navy_color"
											style="font-size: 12px;">${item.center_name}</span></td>

										<td width="10%" align=center>구매가</td>
										<td width="15%" align=center style="line-height: 25px;">
											<s style="color: red;"><span id="gray_color"><fmt:formatNumber
														value="${item.product_price+item.product_option_price}"
														type="number" />원</span></s><br> <fmt:formatNumber
												value="${item.total_price}"
												type="number" />원
										</td>
									</tr>
								</c:forEach>
							</table>

							<table id="order_detail">
								<!-- 주문자 정보 -->
								<tr>
									<td colspan="8"><span id="mypage_info_title">주문자 정보</span></td>
								</tr>

								<tr>
									<td>&nbsp;&nbsp;구매자</td>
									<td colspan="7">이름 <span id="gray_color">&nbsp;
											${adminOrderDetail[0].orderer_name}</span> <br> 전화번호 <span
										id="gray_color">&nbsp;
											${adminOrderDetail[0].orderer_hp1}-${adminOrderDetail[0].orderer_hp2}-${adminOrderDetail[0].orderer_hp3}</span>
									</td>
								</tr>

								<tr>
									<td>&nbsp;&nbsp;이용자</td>
									<td colspan="7">이름 <span id="gray_color">&nbsp;
											${adminOrderDetail[0].receiver_name}</span> <br> 전화번호 <span
										id="gray_color">&nbsp;
											${adminOrderDetail[0].receiver_hp1}-${adminOrderDetail[0].receiver_hp2}-${adminOrderDetail[0].receiver_hp3}</span>
									</td>
								</tr>

								<!-- 결제 정보 -->
								<tr>
									<td colspan="8"><span id="mypage_info_title">결제 정보</span></td>
								</tr>

								<tr>
									<td width="10%" style="padding-left: 10px;">주문금액 <br>
										결제수단
									</td>
									<td width="20%"><span id="gray_color">&nbsp;
											<fmt:formatNumber value="${adminOrderDetail[0].total_price}" type="number" />원</span> <br> <span id="gray_color">&nbsp;
											${adminOrderDetail[0].pay_method}</span>
									<td width="10%" align=right>은행 <br> 할부기간
									</td>
									<td colspan="3"><span id="gray_color">&nbsp;
											${adminOrderDetail[0].card_com_name}</span> <br> <span
										id="gray_color">&nbsp;
											${adminOrderDetail[0].card_pay_month}</span></td>
									<!-- 포인트 사용 내역 -->

									<td width="15%" align=right>사용한 적립금<br> 받은 적립금
									</td>

									<td width="20%"><span id="gray_color">&nbsp; <fmt:formatNumber value="${adminOrderDetail[0].point_price}" type="number" />원</span><br>
										<span id="gray_color">&nbsp; <fmt:formatNumber value="${adminOrderDetail[0].new_point}" type="number" />원</span></td>
								</tr>

								<!-- 환불 정보 -->
								<tr>
									<td colspan="8"><span id="mypage_info_title">환불 정보</span></td>
								</tr>

								<tr>
									<td colspan="2" style="padding-left: 10px;">
										예금주 &nbsp;&nbsp;&nbsp; <span id="gray_color">${adminRefundDetail.account_holder}</span> <br>
										취소사유 &nbsp; <span id="gray_color">${adminRefundDetail.cancel_reason}</span>
									</td>

									<td colspan="3" style="padding-left: 20px;">&nbsp;&nbsp;
										환불은행 &nbsp; <span id="gray_color">${adminRefundDetail.account_bank}</span> <br>
										 &nbsp;&nbsp; 계좌번호 &nbsp; <span id="gray_color">${adminRefundDetail.account_number}</span>
									</td>

									<td align=right colspan="3" style="padding-right: 10px; font-size: 18px;">환불
										예상금액 <span id="red_color">&nbsp; <fmt:formatNumber
												value="${adminRefundDetail.refund_price}" type="number" />원
									</span> 
								</tr>
							</table>

							<!-- 주문 번호 -->
							<input type="hidden" name="order_id"
								value="${adminOrderDetail[0].order_id}">

							<div id="align_center">
								<input type="submit" class="submit_btn" value="환불 승인"
									onclick="alert('환불 승인이 완료되었습니다.')">

								<div style="margin-top: 10px; display: inline-block;">
									<a
										href="${contextPath}/admin/order/adminOrderDetail.do?order_id=${adminOrderDetail[0].order_id}"
										style="line-height: 32px;"> <span class="submit_btn2"
										style="padding: 10px 95px 10px 95px;">돌아가기</span></a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
