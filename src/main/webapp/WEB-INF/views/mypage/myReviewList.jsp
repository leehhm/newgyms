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
<script src="${contextPath}/resources/jquery/review.js"
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

$(document).ready(function() {
	$('.review_emtify_btn').on('click', function() {
		var thisRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
			var review_no = thisRow.find('#review_no').val();
			var product_name = thisRow.find('#product_name').text();
			var product_option_name = thisRow.find('#product_option_name').text();
			var product_option_price = thisRow.find('#product_option_price').text();
			var review_score = document.getElementById('review_score').value;
			var review_title = thisRow.find('#review_title').val();
			var review_contents = thisRow.find('#review_contents').val();

			
			$('#name').empty();
			$('#option_name').empty();
			$('#option_price').empty();

			// 값 대입
			$.ajax({
				type : "GET",
				async : false,
				url : "${contextPath}/mypage/selectReviewContentsImages.do",
				data : {
					review_no : review_no
				},
				dataType : 'json',
				contentType : "application/json;charset=UTF-8",
				success : function(data) {
					var data_length = Object.keys(data).length;
					for (var i=0;i<data_length;i++) {
						var fileName = data[i].fileName;
						$('#selected_image').append("<div style='display:inline-block;position:relative;width:95px;height:95px;margin:3px;border:1px solid #FFFFFF;z-index:1'>"+
						"<img alt='이용후기 이미지' style='width:95px;height:95px;z-index:none' src='${contextPath}/reviewImage.do?review_no="+review_no+"&fileName="+fileName+"'>"+
						"<input type='button' delfile="+fileName+" value='X' style='width:30px;height:30px;position:absolute; font-size:24px; right:0px; bottom:0px; z-index:999; background-color:#00ff0000;"+
						"color:#f00;border:1px solid #00ff0000;'></div>");
					}
				},
				error : function(data) {
					alert("에러가 발생했습니다." + data);
				}
			});
			
			$('#name').append(product_name);
			$('#option_name').append(product_option_name);
			$('#option_price').append(product_option_price);
			$('#score').append(review_score);
			$('input[name=review_no]').attr('value',review_no);
			$('input[name=review_title]').attr('value',review_title);
			$(".review_contents").html(review_contents);
	});		
});
</script>
</head>
<body>
	<form action="${contextPath}/mypage/myReviewList.do" method="get">
		<input type="hidden" name="member_id" value="${member_id}"> <input
			type="hidden" name="chapter" value="1">
		<div class="con-min-width">
			<div class="con">
				<div id="contain">
					<!-- 마이페이지 사이드 메뉴 -->
					<jsp:include page="/WEB-INF/views/mypage/myPageSide.jsp" />
					<div id="contain_right">
						<p id="mypage_order_title">이용후기 조회</p>

						<!-- 조회된 이용후기 -->
						<c:choose>
							<c:when test="${empty myReviewList}">
								<table id="order_detail2" align=center>
									<tr>
										<th width="15%">번호</th>
										<th width="45%">상품명</th>
										<th width="15%">제목</th>
										<th width="15%">등록일</th>
										<th width="10%">선택</th>
									</tr>
									<tr>
										<td colspan="7" style="color: blue;">작성하신 이용후기가 없습니다. 😂</td>
									</tr>
								</table>
							</c:when>
							<c:otherwise>
								<table id="order_detail2" align=center>
									<tr>
										<th width="70px;">번호</th>
										<th width="200px;">상품명</th>
										<th width="50px;" colspan="2">제목</th>
										<th width="100px;">등록일</th>
										<th width="100px;">선택</th>
									</tr>
									<!-- 이용후기 대표 -->
									<c:forEach var="item" items="${myReviewList}"
										varStatus="status">
										<c:set var="i" value="${i+1}" />
										<tr class="title_reiew" style="cursor: pointer;">
											<!-- 번호 -->
											<td width="70px;" style="font-size: 14px;"><input
												id="review_title" type="hidden" value="${item.review_title}">
												<input id="review_contents" type="hidden"
												value="${item.review_contents}"> <input
												id="review_no" type="hidden" value="${item.review_no}">
												<input id="order_seq_num" type="hidden" value="${item.order_seq_num}">${maxnum-(chapter-1)*5-i+1}</td>
											<!-- 상품명 -->
											<td width="230px;" style="text-align: left;font-size: 14px;" >
											<div>
												<a  class="product_name" ><span id="product_name">${item.product_name }</span></a>
											</div>
											<div>
												<a class="center_name" >[옵션] 
												<span id="product_option_name">${item.product_option_name}</span>(+<span id="product_option_price"><fmt:formatNumber value="${item.product_option_price}" type="number" /></span>원)
											</a> 
											</div>
											<input type="hidden" id="review_score" value="${item.review_score}">
											<div class="review_score"">
													<c:choose>
														<c:when test="${item.review_score == 1  }">
															<a class="review_score">★☆☆☆☆</a>
														</c:when>
														<c:when test="${item.review_score == 2  }">
															<a class="review_score">★★☆☆☆</a>
														</c:when>
														<c:when test="${item.review_score == 3  }">
															<a class="review_score">★★★☆☆</a>
														</c:when>
														<c:when test="${item.review_score == 4  }">
															<a class="review_score">★★★★☆</a>
														</c:when>
														<c:otherwise>
															<a class="review_score">★★★★★</a>
														</c:otherwise>
													</c:choose>
												</div>
											</td>
											<!-- 제목 -->
											<td width="50px;" colspan="2" style="line-height: 25px;">${item.review_title}</td>
											<!-- 등록일 -->
											<td width="100px;" style="font-size: 14px;">${item.review_write_date}</td>
											<!-- 선택 -->
											<td width="100px;">
												<%-- <div class="review_emtify_btn"
													onClick="javascript:reviewPopup('open');"
													style="line-height: 32px;">
													<span id="btn_1">수정하기</span> <input type="hidden"
														id="review_no" value="${item.review_no}">
												</div>  --%>
												<a href="${contextPath}/mypage/myReviewDelete.do?review_no=${item.review_no}&member_id=${member_id}"
												style="line-height: 32px;"><span id="review_btn">삭제하기</span></a>
											</td>

											<td style="display: none;" id="review_contents_images">
												<c:forEach var="ril" items="${reviewImageList}"
													varStatus="status">
													<c:if test="${ril.review_no == item.review_no}">
														<div>${ril.fileName}</div>
													</c:if>
												</c:forEach>
											</td>

										</tr>

										<!-- 내용 및 이미지들 -->
										<tr class="review_hidden">
											<!-- 내용 -->
											<td id="gray_color"
												style="font-size: 14px; text-align: center;" colspan="7">${item.review_contents}
												<br> <!-- 이미지 --> <c:forEach var="ril"
													items="${reviewImageList}" varStatus="status">
													<c:if test="${ril.review_no == item.review_no}">
														<img alt="이용후기 이미지"
															style="width: 120px; display: inline; margin: 0px auto;"
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
							href="${contextPath}/mypage/myReviewList.do?chapter=${section_num}&member_id=${member_id}&firstDate=${firstDate}&secondDate=${secondDate}&text_box=${text_box}">${section_num }</a>
					</c:forEach>
				</div>
			</div>
		</div>

		<!-- 이용후기 수정창 -->
		<form name="reviewform" id="reviewform"
			action="${contextPath}/mypage/reviewModify.do"
			enctype="multipart/form-data" method="post">
			<input type="hidden" name="member_id" value="${member_id}">
			<div id="review_write_popup" style="visibility: hidden">
				<div class="modify_body">
					<h2>
						이용후기 수정창
						<button type="button"
							onClick="javascript:reviewPopup('close'); window.location.reload()"
							style="float: right; background-color: #FFFFFF; border: none; font-size: 25px;">X</button>
					</h2>
					<input type="hidden" name="review_no" value="">

					<div>
						<a id="name"></a> <a class="product_option"><br>[옵션] <span
							id="option_name"></span> (+<span id="option_price"> <fmt:formatNumber
									value="" type="number" /></span>원)</a>
					</div>
					<div class="mb-3" name="myform" id="myform">
						<fieldset>
							<input type="radio" name="review_score" value="5" id="rate1">
								<label for="rate1">★</label> 
							<input type="radio" name="review_score" value="4" id="rate2">
								<label for="rate2">★</label> 
							<input type="radio" name="review_score" value="3" id="rate3">
								<label for="rate3">★</label> 
							<input type="radio" name="review_score" value="2" id="rate4">
								<label for="rate4">★</label> 
							<input type="radio" name="review_score" value="1" id="rate5" checked>
								<label for="rate5">★</label>
						</fieldset>
					</div>
					<div style="margin-top: 40px;">
						제목 : <input type="text" style="width: 400px;" name="review_title"
							id="review_title"> <br> 내용 :
						<textarea
							style="width: 400px; height: 150px; vertical-align: top;"
							name="review_contents" class="review_contents"></textarea>
						<br>
						<!-- 업로드 했던 이미지 보여주기 -->
						<div>현재 업로드 된 이미지</div>
						<div style="height: 101px;" id="selected_image"></div>


						<label className="input-file-button" for="btnAtt">이미지 추가<span
							style="color: gray; font-size: 12px;"> (다중이미지는 드래그 해주세요)</span></label> 
						<input type='file' id='btnAtt' class="review_image" name="imagelist" multiple="multiple" accept="image/*" style="display: none;"/>
						<div id="att_zone" style="height: 101px;"></div>
						<!-- 등록 버튼 -->
						<div style="text-align: center;">
							<input type="submit" value="글 수정" id="review_ok_btn">
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
    var chk_style = 'width:30px;height:30px;position:absolute; font-size:24px; right:0px; bottom:0px;'+
    ' z-index:999; background-color:#00ff0000;color:#f00;border:1px solid #00ff0000;';
  
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
    
    /*첨부된 이미리즐을 배열에 넣고 미리보기 */
    imageLoader = function(file){
      sel_files.push(file);
      var reader = new FileReader();
      reader.onload = function(ee){
        let img = document.createElement('img')
        img.setAttribute('style', img_style)
        img.src = ee.target.result;
        attZone.appendChild(makeDiv(img, file));
      }
      console.log(sel_files);
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