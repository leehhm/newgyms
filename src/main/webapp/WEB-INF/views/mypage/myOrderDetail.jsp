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
</head>
<body>
	<form action="${contextPath}/mypage/myOrderCancel.do?total_price=${myOrderDetail[0].total_price}" method="post">

		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 마이페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />


					<div id="contain_right">
						<p id="mypage_order_title">주문/결제 상세 조회</p>
						<table style="margin-bottom: 3px; padding: 3px;">
							<tr>
								<td>&nbsp;주문번호&nbsp; <span id="gray_color">${myOrderDetail[0].order_id}</span>
								</td>
								<td style="padding-left: 704px;">결제상태 &nbsp; <span
									id="gray_color">${myOrderDetail[0].order_state}</span>
								</td>
							</tr>
						</table>

						<table id="order_detail">
							<!-- 상품 정보 -->
							<tr>
								<td colspan="7"><span id="mypage_info_title">상품 정보</span></td>
							</tr>

							<!-- 1건의 주문에 대한 상품이 2개 이상일 경우 여러개 표시 -->
							<c:forEach var="item" items="${myOrderDetail}" varStatus="j">
								<tr>
									<!-- 체크박스 -->
									<td width="3%" align=center>
										<!-- 결제취소 및 환불완료 상태일때는 결제취소 선택 불가능 -->
										<c:if test="${item.order_state == '결제완료'}">
											<input type="checkbox" name="cancel" value="${item.order_seq_num}">
										</c:if>
									</td>
									
									<!-- 상품 이미지 -->
									<td width="15%">
										<div id="img_file">
											<img alt="img" width="100%" height="100%"
												src="${contextPath}/download.do?product_id=${item.product_id}&fileName=${item.product_main_image}">
										</div>
									</td>
									
									<!-- 상품명 & 옵션명 -->
									<td style="width: 30%; line-height: 25px; padding-left: 10px; font-size:15px;">${item.product_name}<br>
										<span id="gray_color" style="font-size:13px;">[옵션]
											${item.product_option_name}
											(+<fmt:formatNumber	value="${item.product_option_price}" type="number" />원)</span>
									</td>
									
									<!-- 사업장명 -->
									<td width="15%" align=center>
										<span id="navy_color" style="font-size:12px;">${item.center_name}</span>
									</td>

									<!-- 주문상태 -->
									<td width="10%" align=center>
										${item.order_state}
									</td>
									
									<!-- 구매 가격 (상품가격 + 옵션가격) -->
									<td width="10%" align=center>구매가</td>	
									<td width="15%" align=center style="line-height:25px;">
										<fmt:formatNumber value="${item.product_sales_price+item.product_option_price}" type="number" />원
									</td>
							</c:forEach>
						</table>

						<div id="align_center" style="margin-bottom:20px;">
							<input type="submit" class="submit_btn" value="주문취소" onclick="return confirm('주문 취소를 진행하시겠습니까?')" >
						</div>

						<table id="order_detail">
							<!-- 주문자 정보 -->
							<tr>
								<td colspan="6"><span id="mypage_info_title">주문자 정보</span></td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;구매자</td>
								<td colspan="5">이름 <span id="gray_color">&nbsp;
										${myOrderDetail[0].orderer_name}</span> <br> 전화번호 <span
									id="gray_color">&nbsp;
										${myOrderDetail[0].orderer_hp1}-${myOrderDetail[0].orderer_hp2}-${myOrderDetail[0].orderer_hp3}</span>
								</td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;이용자</td>
								<td colspan="5">이름 <span id="gray_color">&nbsp;
										${myOrderDetail[0].receiver_name}</span> <br> 전화번호 <span
									id="gray_color">&nbsp;
										${myOrderDetail[0].receiver_hp1}-${myOrderDetail[0].receiver_hp2}-${myOrderDetail[0].receiver_hp3}</span>
								</td>
							</tr>

							<!-- 결제 정보 -->
							<tr>
								<td colspan="6"><span id="mypage_info_title">결제 정보</span></td>
							</tr>

							<tr>
								<td width="10%" style="padding-left: 10px;">
									주문금액 <br>
									결제수단
								</td>
								
								<td width="20%">
									<span id="gray_color">&nbsp; <fmt:formatNumber value="${myOrderDetail[0].total_price}" type="number" />원</span> <br>
									<span id="gray_color">&nbsp; ${myOrderDetail[0].pay_method}</span>
								</td>
								
								<td width="10%" align=right>
									은행 <br> 
									할부기간
								</td>
								
								<td width="20%">
									<span id="gray_color">&nbsp; ${myOrderDetail[0].card_com_name}</span> <br>
									<span id="gray_color">&nbsp; ${myOrderDetail[0].card_pay_month}</span>
								</td>
								
								<!-- 포인트 사용 내역 -->
                        
                        		<td width="15%" align=right>
										사용한 적립금<br>
										받은 적립금
                        		</td>
		                        
                        		<td width="20%">
                           				<span id="gray_color">&nbsp; <fmt:formatNumber value="${myOrderDetail[0].point_price}" type="number" />원</span><br>
										<span id="gray_color">&nbsp; <fmt:formatNumber value="${myOrderDetail[0].new_point}" type="number" />원</span>
                        		</td>
							</tr>
						</table>
						
						<div style="text-align:center; margin-top:20px;">
							<a href="${contextPath}/mypage/myOrderList.do?member_id=${memberInfo.member_id}&chapter=1&order_state=&firstDate=&secondDate=&text_box=" style="line-height:32px;">
							<span class="submit_btn2" style="padding:10px 95px 10px 95px;">목록으로</span></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>

</body>
</html>
