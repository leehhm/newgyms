<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"    
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<body>

<div class="con-min-width">
   <div class="con">
      <footer class="footer-1">
    <div class="footer-1__1" style="padding-left:60px;">
        <div style="font-size:20px; margin-bottom:15px;">고객만족센터</div>
        <div style="font-size: 25px; margin-bottom:15px;">1544-1005
            <span style="font-size:15px;">월~토요일 오전 7시-오후6시</span>
        </div>
        <div style="margin-bottom:15px;">
            <button>카카오톡 문의</button>
            <span>월~토요일 | 오전 7시-오후6시</span>
        </div>
        <div>
            <button>1:1문의</button>
            <span>고객센터 운영시간에 순차적으로 답변드리겠습니다.</span>
        </div>
    </div>
    <div class="footer-1__2" style="padding-left:70px;">
        <div>대표 : 뉴짐스</div>
        <div>사업자 번호 : 375-82-00029</div>
        <div>주소 : 대전광역시 서구 오라클빌딩 10층</div>
        <div>전화 : 1544-1005</div>
        <div>고객문의 : ng@newgyms.com</div><br>
        <div style="font-size:15px; font-weight: bold;">제휴문의 | 광고문의</div><br>
        <div style="font-size:15px;">(c)Negyms CORP. All Rights Reserved</div>
    </div>
</footer>
<footer class="footer-2">
    <div class="footer-2__1" style="display:flex; padding-left:60px;">
        <div style="margin-right: 50px;"><a href="#">서비스 이용약관</a></div>
        <div><a href="#">개인정보 처리방침</a></div>
    </div>
    <div class="footer-2__2" style="display:flex; padding-left:70px;">
        <a href="https://www.naver.com/" target="_blank"><img src="${contextPath}/resources/image/naver.png" alt="naver"></a>
        <a href="https://www.kakaocorp.com/page/" target="_blank"><img src="${contextPath}/resources/image/kakao.png" alt="kakao"></a>
        <a href="https://www.facebook.com/" target="_blank"><img src="${contextPath}/resources/image/facebook.png" alt="facebook"></a>
        <a href="https://www.instagram.com/" target="_blank"><img src="${contextPath}/resources/image/instagram.png" alt="instagram"></a>
    </div>
</footer>
   </div>
</div>




</body>