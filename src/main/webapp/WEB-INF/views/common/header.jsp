<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<link href="${contextPath}/resources/css/header.css?after"
	rel="stylesheet" type="text/css" />

<body>
	<div class="con-min-width">
		<div class="con">
			<header class="flex-col">
				<!-- 로그인 -->
         <div class="login flex">
            <ul class="login_menu_1 flex">
            <c:choose>
               <c:when test="${isLogOn==true and memberInfo.join_type =='102' and memberInfo.member_id != 'admin'}">
                  <div class="owner_btn">사업자</div>
                  <h5 style="color:#201D1D; margin-right:10px;">환영합니다. <span style="color:#F9C200;">${memberInfo.member_name}</span>님!</h5>
                   <li><a href="${contextPath}/member/logout.do" style="color: rgb(51, 51, 51);">로그아웃</a></li>
               </c:when>
               <c:when test="${isLogOn==true and memberInfo.member_id =='admin'}">
                  <div class="admin_btn">관리자</div>
                  <h5 style="color:#201D1D; margin-right:10px;">환영합니다. <span style="color:#F9C200;">${memberInfo.member_name}</span>님!</h5>
                   <li><a href="${contextPath}/member/logout.do" style="color: rgb(51, 51, 51);">로그아웃</a></li>
               </c:when>
               <c:when test="${isLogOn==true and not empty memberInfo}">
                  <div class="member_btn">일반</div>
                  <h5 style="color:#201D1D; margin-right:10px;">환영합니다. <span style="color:#F9C200;">${memberInfo.member_name}</span>님!</h5>
                   <li><a href="${contextPath}/member/logout.do" style="color: rgb(51, 51, 51);">로그아웃</a></li>
               </c:when>
               <c:otherwise>
                  <li><a href="${contextPath}/member/loginForm.do">로그인</a></li>
                  <li><a href="${contextPath}/member/joinCheck.do">회원가입</a></li>
               </c:otherwise>
            </c:choose>
              </ul>
         </div>

				<div class="center flex">
					<!-- 메인로고 -->
					<div class="logo">
						<a href="${contextPath}/main/main.do"><img src="${contextPath}/resources/image/logo.png" alt="newgyms"></a>
					</div>
					<div class="flex-grow"></div>

					<!-- 검색창 -->
					<div class="search">
						<form name="frmSearch" action="${contextPath}/product/searchProduct.do">
							<input class="search_input" name="searchWord" type="text" placeholder="검색어를 입력하세요"> 
							<input class="search_img" type="image" src="${contextPath}/resources/image/search.png" alt="search" style="width: 20px; height: 20px;" name="search">
						</form>
					</div>

					<!-- 아이콘's -->
					<div class="flex-grow"></div>
					<div>
						<ul class="icons">
							<c:choose>
								<c:when test="${isLogOn==true and memberInfo.join_type =='101'}">
									<li><a href="${contextPath}/mypage/myOrderList.do?member_id=${memberInfo.member_id}&chapter=1&order_state=&firstDate=&secondDate=&text_box="><img style="width: 33px; height: 33px;" src="${contextPath}/resources/image/person.png" alt="회원"></a></li>
								</c:when>
								<c:when test="${isLogOn==true and memberInfo.join_type =='102' and memberInfo.member_id != 'admin'}">
									<li><a href="${contextPath}/owner/main/ownerPageIntroModifyForm.do?member_id=${memberInfo.member_id}"><img style="width: 33px; height: 33px;" src="${contextPath}/resources/image/person.png" alt="사업자"></a></li>
								</c:when>
								<c:when test="${isLogOn==true and memberInfo.member_id =='admin'}">
									<li><a href="${contextPath}/admin/member/adminMemberList.do?chapter=1"><img style="width: 33px; height: 33px;" src="${contextPath}/resources/image/person.png" alt="관리자"></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${contextPath}/order/nonMemberOrder.do"><img style="width: 33px; height: 33px;" src="${contextPath}/resources/image/person.png" alt="비회원"></a></li>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${isLogOn==true and not empty memberInfo }">
									<li><a href="${contextPath}/wish/myWishList.do""><img style="width: 26px; height: 26px;" src="${contextPath}/resources/image/heart.png" alt="회원찜"></a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${contextPath}/member/loginForm.do" onclick="javascript:alert('로그인이 필요합니다.');"><img style="width: 26px; height: 26px;" src="${contextPath}/resources/image/heart.png" alt="로그인 전"></a></li>
								</c:otherwise>
							</c:choose>

							<li><a href="${contextPath}/cart/myCartList.do"><img style="width: 30px; height: 30px;" src="${contextPath}/resources/image/cart.png" alt="회원장바구니"></a></li>
						</ul>
					</div>
				</div>
				
				<!-- 네비게이션바 -->
				<nav class="main-menu_1">
					<ul class="nav_bar flex">
						<li><a
							href="${contextPath}/product/productList.do?category=전체보기&address=대전">전체보기</a></li>
						<li><a
							href="${contextPath}/product/productList.do?category=헬스/PT&address=대전">헬스/PT</a></li>
						<li><a
							href="${contextPath}/product/productList.do?category=요가/필라테스&address=대전">요가/필라테스</a></li>
						<li><a
							href="${contextPath}/product/productList.do?category=스피닝&address=대전">스피닝</a></li>
						<li><a
							href="${contextPath}/product/productList.do?category=크로스핏&address=대전">크로스핏</a></li>
						<li><a
							href="${contextPath}/product/productList.do?category=기타&address=대전">기타</a></li>
						<li><a href="#">커뮤니티</a>
							<ul class="menu-box_menu-2">
								<li><a href="${contextPath }/board/listArticles.do">자유게시판</a></li>
								<li><a href="${contextPath }/review/reviewList.do">이용후기</a></li>
							</ul></li>
						<li><a href="#">고객센터</a>
							<ul class="menu-box_menu-1">
								<li><a href="${contextPath}/notice/listNotices.do">공지사항</a></li>
								<li><a href="${contextPath}/event/listEvents.do">이벤트</a></li>
								<li><a href="${contextPath}/qna/listQnas.do">Q&A</a></li>
							</ul></li>
					</ul>
				</nav>
			</header>
		</div>

	</div>
</body>
</html>