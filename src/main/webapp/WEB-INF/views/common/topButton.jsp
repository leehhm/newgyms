<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="euc-kr"
	isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<%
  request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<style>
	.btn_gotop {
		display:none;
		position:fixed;
		bottom:60px;
		right:30px;
		z-index:999;
		outline:none;
		cursor:pointer;
	}
	
	.topButton-img {
		width:50px;
		heigth:50px;
	}
</style>

<script>
 
	 $(window).scroll(function(){
			if ($(this).scrollTop() > 200){
				$('.btn_gotop').fadeIn();
			} else{
				$('.btn_gotop').fadeOut();
			}
		});
	 
	 $('.btn_gotop').click(function(){
			$('html, body').animate({scrollTop:0}, 400);
			return false;
		}); 
	
	/*   자바스크립트 
	const $topBtn = document.querySelector('.moveTopBtn');
	
	//버튼 클릭 시 맨 위로 이동
	$topBtn.onclick = () => {
	window.scrollTo({ top: 0, behavior: 'smooth' });  
	} */ 

</script>

<head>
<meta charset="UTF-8">
<title>탑이동버튼</title>
</head>
<body>
	  <a href="#" class="btn_gotop"><img class="topButton-img" src="${contextPath}/resources/image/topButton.png"></a> 
	  
	  <%-- <a href="#" class="moveTopBtn"><img class="topButton-img" src="${contextPath}/resources/image/topButton.png"></a> --%>
</body>
</html>