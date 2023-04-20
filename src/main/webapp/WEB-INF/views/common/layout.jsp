<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<%
  request.setCharacterEncoding("utf-8");
%>

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width">


 <link rel="shortcut icon" type="image/x-icon" href="${contextPath }/resources/image/icon.png">
 
<!-- 상품 -->
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="${contextPath}/resources/jquery/basic-jquery-slider.js" type="text/javascript"></script>
<link href="${contextPath}/resources/css/basic-jquery-slider.css?after" rel="stylesheet" type="text/css" media="screen">
<link href="${contextPath}/resources/css/header.css?after" rel="stylesheet" type="text/css" media="screen">
<link href="${contextPath}/resources/css/footer.css?after" rel="stylesheet" type="text/css" media="screen">
<link href="${contextPath}/resources/css/main.css?after" rel="stylesheet" type="text/css" media="screen">
<script src="${contextPath}/resources/jquery/carousel.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/jquery.easing.1.3.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/stickysidebar.jquery.js" type="text/javascript"></script>
<script src="${contextPath}/resources/jquery/product.js" type="text/javascript"></script>


<script>
   // 슬라이드 
   $(document).ready(function() {
      $('#ad_main_banner').bjqs({
         'width' : 1920,
         'height' : 700,
         'showMarkers' : true,
         'showControls' : false,
         'centerMarkers' : false
      });
   });
   // 스티키       
   $(function() {
      $("#sticky").stickySidebar({
         timer : 100,
         easing : "easeInBounce"
      });
   });
</script>
   <title><tiles:insertAttribute name="title" /></title>
   
</head>
<body>

         <header>
               <tiles:insertAttribute name="header" />
         </header>
         <div class="clear"></div>
         <article class="article">
             <tiles:insertAttribute name="body" />
         </article> 
         <div class="clear"></div>
         <footer>
              <tiles:insertAttribute name="footer" />
           </footer>
       <tiles:insertAttribute name="quickMenu" />
       <tiles:insertAttribute name="topButton" />
</body>      
        
        
        