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
<script src="${contextPath}/resources/jquery/order.js" type="text/javascript"></script>
<script type="text/javascript">


//수정창 열고 닫기
function reviewPopup(type) {
if (type == 'open') {
   $('#review_write_popup').attr('style', 'visibility:visible');
   
} else if (type == 'close') {
   $('#review_write_popup').attr('style', 'visibility:hidden');
}
}

$(document).ready(function() {
   $('.review_write_btn').on('click', function() {
      var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
         var seq_num = thisRow.find('#order_seq_num').val();
         var product_main_image = thisRow.find('#product_main_image').text();
         var product_name = thisRow.find('#product_name').text();
         var product_option_name = thisRow.find('#product_option_name').text();
         var product_option_price = thisRow.find('#product_option_price').text();
         var center_name =  thisRow.find('#center_name').text();
         var product_id = thisRow.find('#product_id').text();
         
   
          $('#main_image').empty();
         $('#name').empty();
         $('#option_name').empty();
         $('#option_price').empty();
         $('#center').empty();
         $('#id').empty();

         // 값 대입
      
         $('#main_image').append("<img alt='상품 메인이미지' src='${contextPath}/thumbnails.do?product_id="+product_id+"&fileName="+product_main_image+"'>");
         $('#name').append(product_name);
         $('#option_name').append(product_option_name);
         $('#option_price').append(product_option_price);
         $('#center').append(center_name);
         $('#id').append(product_id);
         
         document.reviewform.order_seq_num.value = seq_num;
   });      
});

/* 이용후기 작성창 제목과 내용 썼는지 확인 */
function checkWrite() {
         var review_title = $("#review_title").val();
         var review_contents = $("#review_contents").val();
         
        if ((review_title == '' || review_contents == '') || (review_title == '' && review_contents == '')) {
            alert("제목과 내용은 꼭 입력해 주세요");           
            /* document.getElementById("review_title").focus(); */
         } else {
            document.getElementById('reviewform').submit();
         }
         return false;
      }
</script>


</head>
<body>
   <form action="${contextPath}/mypage/myOrderList.do" method="get">
      <input type="hidden" name="member_id" value="${member_id}"> 
      <input type="hidden" name="chapter" value="1">
      <div class="con-min-width">
         <div class="con">
            <div id="contain">
               <!-- 마이페이지 사이드 메뉴 -->
               <jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
               <div id="contain_right">
                  <p id="mypage_order_title">결제 내역 조회</p>
                  <!-- <a class="btn-open-popup" href="#modal1">조회하기</a> -->

                  <!-- 조회된 결제 내역 -->
                  <c:choose>
                     <c:when test="${empty myOrderList}">
                        <table id="order_detail2" align=center>
                           <tr>
                              <th width="15%">결제일자</th>
                              <th width="45%" colspan="2">상품정보</th>
                              <th width="15%">결제금액</th>
                              <th width="10%">선택</th>
                           </tr>
                           <tr>
                              <td colspan="7" style="color: blue;">조회된 결제 내역이 없습니다. 😂</td>
                           </tr>
                        </table>
                     </c:when>

                     <c:otherwise>
                        <table id="order_detail2" align=center>
                           <tr>
                              <th width="15%">결제일자</th>
                              <th width="45%" colspan="2">상품정보</th>
                              <th width="15%">결제금액</th>
                              <th width="10%">선택</th>
                           </tr>

                           <!-- 결제내역 대표 -->
                           <c:forEach var="list" items="${myOrderList}">
                              <c:set var="i" value="${i+1}" />
                              <tbody>
                                 <tr>
                                    <!-- 결제일자 -->
                                    <td width="15%" style="font-size: 14px;">${list.pay_order_time}</td>

                                    <!-- 대표이미지 -->
                                    <td width="15%" align=center>
                                       <div id="img_file">
                                          <img alt="img" width="100%" height="100%"
                                             src="${contextPath}/download.do?product_id=${list.product_id}&fileName=${list.product_main_image}">
                                       </div>
                                    </td>

                                    <!-- 상품정보 -->
                                    <td width="30%" class="ord_title"
                                       style="cursor: pointer; text-align: left; line-height: 25px; font-size: 18px;">${list.product_name}


                                       <span style="color: #848484;"> (총 <c:forEach
                                             var="omlist" items="${orderMemberList}"
                                             varStatus="status">
                                             <c:choose>
                                                <c:when test="${list.order_id == omlist.order_id}">
                                       ${omlist.countnum}
                                       </c:when>
                                             </c:choose>
                                          </c:forEach> 건)
                                    </span> <span style="color: #0F0573; font-size: 12px;"><br>
                                          &nbsp; 상세보기 ▽</span>
                                    </td>

                                    <!-- 총 결제금액 -->
                                    <td width="15%"><fmt:formatNumber
                                          value="${list.total_price}" type="number" />원</td>

                                    <!-- 상세조회 버튼 -->
                                    <td width="10%"><a href="${contextPath}/mypage/myOrderDetail.do?order_id=${list.order_id}" style="line-height: 32px;"><span id="btn_1">상세조회</span></a>
                                    </td>
                                 </tr>
                              </tbody>

                              <!-- 결제내역 전체 리스트 -->
                              <tbody class="ord_hidden">
                                 <c:forEach var="member" items="${orderMember}"
                                    varStatus="status">
                                    <c:choose>
                                       <c:when test="${list.order_id == member.order_id}">
                                          <tr>
                                             <!-- 주문상태 -->
                                             <td>${member.order_state}<input type="hidden" value="${member.order_seq_num}" id="order_seq_num"></td>
                                             <td class="hidepoint" id="product_main_image">${member.product_main_image}</td>
                                             <td class="hidepoint" id="product_id">${member.product_id}</td>
                                             <td class="hidepoint" id="center_name">${member.center_name}</td>

                                             <!-- 이미지 -->
                                             <td align=center
                                                style="border-left: 1px solid rgb(244, 244, 244);">
                                                <div id="img_file">
                                                   <img alt="img" width="100%" height="100%"
                                                      src="${contextPath}/download.do?product_id=${member.product_id}&fileName=${member.product_main_image}">
                                                </div>
                                             </td>

                                             <!-- 상품명, 옵션 및 옵션가격 -->
                                             <td style="text-align: left; line-height: 25px;"><span
                                                id="product_name">${member.product_name}</span> <br>
                                                <div style="font-size: 14px;">
                                                   [옵션] <span id="product_option_name">${member.product_option_name}</span>
                                                   (+<span id="product_option_price"> <fmt:formatNumber value="${member.product_option_price}" type="number" /></span>원)
                                             </div></td>

                                             <!-- 결제금액 -->
                                             <td style="line-height: 25px;">
                                             	<br> <fmt:formatNumber value="${member.product_sales_price+member.product_option_price}" type="number" />원</td>

                                             <!-- 이용후기 작성 버튼 -->
                                             <td class="review_write_btn" onClick="javascript:reviewPopup('open');" style="line-height: 32px;"><a id="review_btn" href="#">후기작성</a>
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
            <div style="text-align: center;">
				<c:forEach var="page" begin="1" end="${Math.ceil(orderMaxNum/5)}"  step="1">
                  <c:set var="section_num" value="${section_num+1}" />
                  <a
                     href="${contextPath}/mypage/myOrderList.do?chapter=${section_num}&member_id=${member_id}&order_state=${order_state}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">${section_num }</a>
               </c:forEach>

            </div>
         </div>
      </div>
   </form>


   <!-- 이용후기 작성 -->
   <form name="reviewform" id="reviewform"
      action="${contextPath}/mypage/reviewUpLoad.do"
      enctype="multipart/form-data" method="post" onsubmit="return false">
      <input type="hidden" name="member_id" value="${member_id}">
      <div id="review_write_popup" style="visibility: hidden">
         <div class="modify_body">
            <h2>
               이용후기 작성창
               <button type="button"
                  onClick="javascript:reviewPopup('close'); window.location.reload()"
                  style="float: right; background-color: #FFFFFF; border: none; font-size: 25px;">X</button>
            </h2>
            <input type="hidden" name="order_seq_num" value="">
            <div>
               <div id="main_image" style="width: 90px; float: left; margin: 5px;"></div>
               <a id="name"></a> <a class="product_option"><br>[옵션] <span
                  id="option_name"></span> (+<span id="option_price"> <fmt:formatNumber
                        value="" type="number" /></span>원)</a>
            </div>
            <div id="center"></div>
            <div class="mb-3" name="myform" id="myform">
               <fieldset>
                  <input type="radio" name="review_score" value="5" id="rate1"><label
                     for="rate1">★</label> <input type="radio" name="review_score"
                     value="4" id="rate2"><label for="rate2">★</label> <input
                     type="radio" name="review_score" value="3" id="rate3"><label
                     for="rate3">★</label> <input type="radio" name="review_score"
                     value="2" id="rate4"><label for="rate4">★</label> <input
                     type="radio" name="review_score" value="1" id="rate5" checked><label
                     for="rate5">★</label>
               </fieldset>
            </div>
            <div style="margin-top: 40px;">
               제목 : <input type="text" style="width: 400px;" name="review_title"
                  id="review_title"> <br> 내용 :
               <textarea style="width: 400px; height: 150px; vertical-align: top;"
                  name="review_contents" id="review_contents"></textarea>
               <br> <label className="input-file-button" for="btnAtt"
                  style="margin-left: 40px;">이미지 첨부<span
                  style="color: gray; font-size: 12px;"> (다중이미지는 드래그 해주세요)</span></label> 
                  <input type='file' id='btnAtt' class="review_image" name="imagelist"
                  multiple="multiple" accept="image/*" style="display: none;" />
               <!-- <input type="hidden" value="" name="imageNameList" /> -->
               <div id="att_zone" style="height: 101px;"></div>
               <!-- 완료 버튼 -->
               <div style="text-align: center;">
                  <button type="submit" id="review_ok_btn"
                     onclick="javascript:checkWrite();">등록하기</button>
               </div>
            </div>
         </div>
      </div>
   </form>

   <script>
 /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */
     (imageView = function imageView(att_zone, btn){
    var attZone = document.getElementById(att_zone);
    var btnAtt = document.getElementById(btn)
    var sel_files = [];
    // 이미지와 체크 박스를 감싸고 있는 div 속성
    var div_style = 'display:inline-block;position:relative;'
                  + 'width:95px;height:95px;margin:3px;border:1px solid #FFFFFF;z-index:1';
    // 미리보기 이미지 속성
    var img_style = 'width:95px;height:95px;z-index:none';
    // 이미지안에 표시되는 체크박스의 속성
    var chk_style = 'width:30px;height:30px;position:absolute; font-size:24px; right:0px; bottom:0px; z-index:999; background-color:#00ff0000;color:#f00;border:1px solid #00ff0000;';
  
    btnAtt.onchange = function(e){
      var files = e.target.files;
      var fileArr = Array.prototype.slice.call(files)
      for(f of fileArr){
        imageLoader(f);
      }
    }
  
    // 탐색기에서 드래그앤 드롭 사용
    attZone.addEventListener('dragenter', function(e){
      e.preventDefault();
      e.stopPropagation();
    }, false)
    
    attZone.addEventListener('dragover', function(e){
      e.preventDefault();
      e.stopPropagation();
    }, false)
  
    attZone.addEventListener('drop', function(e){
      var files = {};
      e.preventDefault();
      e.stopPropagation();
      var dt = e.dataTransfer;
      files = dt.files;
      for(f of files){
        imageLoader(f);
      }
    }, false)
    /*첨부된 이미지를 배열에 넣고 미리보기 */
    imageLoader = function(file){
      sel_files.push(file);
      var reader = new FileReader();
      reader.onload = function(ee){
        let img = document.createElement('img')
        img.setAttribute('style', img_style)
        img.src = ee.target.result;
        attZone.appendChild(makeDiv(img, file));
      }
      reader.readAsDataURL(file);
    }
    
    /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
    makeDiv = function(img, file){
      var div = document.createElement('div')
      div.setAttribute('style', div_style)
      
      var btn = document.createElement('input')
      btn.setAttribute('type', 'button')
      btn.setAttribute('value', 'x')
      btn.setAttribute('delFile', file.name);
      btn.setAttribute('style', chk_style);
      btn.onclick = function(ev){
        var ele = ev.srcElement;
        var delFile = ele.getAttribute('delFile');
        for(var i=0 ;i<sel_files.length; i++){
          if(delFile== sel_files[i].name){
            sel_files.splice(i, 1);
          }
        }
        
        dt = new DataTransfer();
        for(f in sel_files) {
          var file = sel_files[f];
          dt.items.add(file);
        }
        btnAtt.files = dt.files;
        console.log(btnAtt.files);
        var p = ele.parentNode;
        attZone.removeChild(p)
      }
      div.appendChild(img)
      div.appendChild(btn)
      return div
    }
  }
)('att_zone', 'btnAtt')

      
      const body = document.querySelector('body');
      const modal = document.querySelector('.modal');
      const btnOpenPopup = document.querySelector('.btn-open-popup');

      btnOpenPopup.addEventListener('click', () => {
        modal.classList.toggle('show');

        if (modal.classList.contains('show')) {
          body.style.overflow = 'hidden';
        }
      });

      modal.addEventListener('click', (event) => {
        if (event.target === modal) {
          modal.classList.toggle('show');

          if (!modal.classList.contains('show')) {
            body.style.overflow = 'auto';
          }
        }
      });
    </script>
</body>
</html>