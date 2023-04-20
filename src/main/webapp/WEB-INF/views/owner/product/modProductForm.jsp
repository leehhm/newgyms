<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<c:set var="product"  value="${productVO}"  />
<c:set var="optList"  value="${productOptList }"  />

<c:set var="detailImageList"  value="${imageMap.detailImageList }"  />
<c:set var="priceImageList"  value="${imageMap.priceImageList }"  />
<c:set var="facilityImageList"  value="${imageMap.facilityImageList }"  />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${contextPath}/resources/css/mypage.css" rel="stylesheet" />
<link href="${contextPath}/resources/css/owner.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

	/* 메인 이미지 첨부 */
	function previewMainImage(input) {
		if (input.files && input.files[0]) {
			
			var fileName = input.files[0].name;
			
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            $('#preview_main_img').attr('src', e.target.result);
	            $('#product_main_image').val(fileName);	//fileName 넣어주기
	            
	        };
	        reader.readAsDataURL(input.files[0]);
		}
	}
	
		
		/* 옵션 추가*/
		   var opt = 0;
		   function fn_addOption(){
		      $("#product_option").append(
		            " <div class='add_product_option'>"
		            + "<table id='mod_option'><tr><td><input type='text' name='optionList["+opt+"].product_option_name' placeholder='옵션명'></td>  "
		            + "<td><input type='text' name='optionList["+opt+"].product_option_price' placeholder='옵션가격'> 원 &nbsp&nbsp</td>"
		            + "<td>&nbsp&nbsp<a id='del_option_btn' class='del_option_btn' href='javascript:fn_delOption();'>X</a></td></tr></table></div>");      
		      opt++;
		   }
		      
	   /* 옵션 삭제 */
	   function fn_delOption() {
	      var thisOption = document.getElementById("del_option_btn").closest('div');
	      thisOption.remove();
	   }

    $(document).ready(function() {
        $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
    })
 
    function addFile(type) {
    	
    	if (type == 'detail_image') {
    		
	        var file = "<div class='img_preview'><input style='border:none;' type='file' name='detail_image'  accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
	        $("#add_detail_image_list").append(file);    
	        
    	} else if (type == 'price_image') {
    		
	        var file = "<div class='img_preview'><input style='border:none;' type='file' name='price_image'  accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
	        $("#add_price_image_list").append(file);
	        
    	} else if (type == 'facility_image') {
    		
	        var file = "<div class='img_preview'><input style='border:none;' type='file' name='facility_image' accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
	        $("#add_facility_image_list").append(file);
    	} 
    	
        $("a[name='file-delete']").on("click", function(e) {
            e.preventDefault();
            deleteFile($(this));
        });
    }
 
    function deleteFile(obj) {
        obj.parent().remove();
    }
	   
   function fn_delImage(product_id, image_id, fileName) {
	   
		   var i_Image_id = "<input type='hidden' name='del_image_id' value="+image_id+">";
		   var i_fileName = "<input type='hidden' name='del_fileName' value="+fileName+">";
		   
		   $("#deleteImageList").append(i_Image_id);
		   $("#deleteImageList").append(i_fileName);
        
	   }
	   
</script>
</head>
<body>
	<div class="con-min-width">
		<div class="con">
			<div id="contain">
				<!-- 사업자 페이지 사이드 메뉴 -->
				<jsp:include page="/WEB-INF/views/owner/main/ownerPageSide.jsp" />
				<div id="contain_right">
				<div id="addProductForm">
				<form action="${contextPath}/owner/product/modifyProduct.do" method="post"  enctype="multipart/form-data">
					<p id="owner_product_title">상품 수정</p>

					<input type="hidden" name="product_id" value="${product.product_id}" >

					<br><br>
					<span class="add_product_title">기본정보</span>
					<table id="product_info_table">
						<tr>
							<td>카테고리 <span style="color:red">*</span></td>
							<td colspan="3">
								<select id="product_sort" name="product_sort" style="padding: 8px;" required> 
									<option value="">[필수] 카테고리 선택</option>
									<option value="헬스/PT" ${product.product_sort == '헬스/PT' ? 'selected="selected"' : '' }>헬스/PT</option>
									<option value="요가/필라테스" ${product.product_sort == '요가/필라테스' ? 'selected="selected"' : '' }>요가/필라테스</option>
									<option value="스피닝" ${product.product_sort == '스피닝' ? 'selected="selected"' : '' }>스피닝</option>
									<option value="크로스핏" ${product.product_sort == '크로스핏' ? 'selected="selected"' : '' }>크로스핏</option>
									<option value="기타" ${product.product_sort == '기타' ? 'selected="selected"' : '' }>기타</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>상품명 <span style="color:red">*</span></td>
							<td colspan="3"><input type="text" name="product_name" value="${product.product_name }" required></td>
						</tr>
						<tr>
							<td>대표 이미지 <span style="color:red">*</span></td>
							<td colspan="3"> 
								<div  class="img_preview">

										<input type="hidden" name="originalFileName" value="${product.product_main_image}" /> 
										<input id="product_main_image" type="hidden" name="product_main_image" value="${product.product_main_image}">
								        <img id="preview_main_img" alt="메인 이미지" src="${contextPath}/download.do?product_id=${product.product_id}&fileName=${product.product_main_image}">
									        ${product.product_main_image}
									    <br>
									    수정하기<input id="add_file_btn" style="border:none;" type="file" name="product_main_image" accept="image/*" onchange="previewMainImage(this);"/>
								</div>
							</td>
						</tr>
						<tr>
							<td>정가 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_price" value="${product.product_price}" required> 원
							</td>
							
							<td style="text-align:center;">할인가 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_sales_price" value="${product.product_sales_price}" required> 원
							</td>
						</tr>
						<tr>
							<td>구매 시 적립금 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_point" value="${product.product_point}" required> 원
							</td>
						</tr>
						<tr>
							<td>옵션 <span style="color:red">*</span></td>
							<td colspan="3">
							<a class="add_option_btn" href="javascript:fn_addOption();">옵션 추가</a>
							</td>
						</tr>
						</table>
								<div id="product_option" style="padding-left:200px;">
								<c:forEach var="opt" items="${optList }">
									 <input type="hidden" value="${opt.option_id }" >
									 <input type="text" value="${opt.product_option_name}" placeholder="옵션명" readonly>
									 <input type="text" pattern="[0-9]{*}" value="${opt.product_option_price}" placeholder="옵션가격" readonly> 원&nbsp;&nbsp;<br>
								</c:forEach>
								 </div>
								 
						<br><br>		
						<span class="add_product_title">프로그램 정보</span>
						<table id="product_detail_table">					
						<tr>
							<td>프로그램 상세정보</td>
							<td>
								<textarea name="product_program_details"> ${product.product_program_details}</textarea>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>이미지 첨부
						        <a class="add_option_btn" href="#this" onclick="addFile('detail_image')">파일추가</a>
 							</td>
						</tr>
						<tr>
							<td></td>
							<td>
					         <c:if test="${not empty detailImageList}">
								<c:forEach var="image" items="${detailImageList }">
								 <div id="detail_image_list">
									<div id="preview_detail_img" class="img_preview">
							          <img alt="프로그램 상세정보 이미지" src="${contextPath}/download.do?product_id=${image.product_id}&fileName=${image.fileName}">
								        ${image.fileName}
							            <a href='#this' name='file-delete' onClick="fn_delImage('${image.product_id}','${image.image_id}','${image.fileName}')">X</a>
									</div>
							     </div>
								</c:forEach>
					         </c:if>
   						        <div id="add_detail_image_list"  class="img_preview"></div>
							</td>
						</tr>
						<tr>
							<td>가격 정보</td>
							<td>
								<textarea name=" product_price_details"> ${product.product_price_details} </textarea>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>이미지 첨부
						        <a class="add_option_btn" href="#this" onclick="addFile('price_image')">파일추가</a>
 							</td>
						</tr>
						<tr>
							<td></td>
							<td>
					         <c:if test="${not empty priceImageList}">
								<c:forEach var="image" items="${priceImageList }">
								 <div id="price_image_list">
									<div id="preview_price_img" class="img_preview">
							          <img alt="가격 정보 이미지" src="${contextPath}/download.do?product_id=${image.product_id}&fileName=${image.fileName}">
								        ${image.fileName}
							            <a href='#this' name='file-delete' onClick="fn_delImage('${image.product_id}','${image.image_id}','${image.fileName}')">X</a>
									</div>
							     </div>
								</c:forEach>
					         </c:if>
   						        <div id="add_price_image_list"  class="img_preview"></div>
							</td>
						</tr>						
						<tr>
							<td>시설 정보</td>
							<td>
								<textarea name=" product_facility_details"> ${product.product_facility_details} </textarea>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>이미지 첨부
						        <a class="add_option_btn" href="#this" onclick="addFile('facility_image')">파일추가</a>
							</td>
						</tr>
						<tr>
							<td></td>
							<td>
					         <c:if test="${not empty facilityImageList}">
								<c:forEach var="image" items="${facilityImageList }">
								 <div id="facility_image_list">
									<div id="preview_facility_img" class="img_preview">
							          <img alt="가격 정보 이미지" src="${contextPath}/download.do?product_id=${image.product_id}&fileName=${image.fileName}">
								        ${image.fileName}
							            <a href='#this' name='file-delete' onClick="fn_delImage('${image.product_id}','${image.image_id}','${image.fileName}')">X</a>
									</div>
							     </div>
								</c:forEach>
					         </c:if>
   						        <div id="add_facility_image_list"  class="img_preview"></div>
							</td>
						</tr>												
						<tr>
							<td>위치 정보</td>
							<td>	
								<textarea name=" product_location_details"> ${product.product_location_details} </textarea>
							</td>
						</tr>
						</table>
						
						<br><br>
						<span class="add_product_title">환불 정보</span>
						<table id="product_refund_table">					
						<tr>
							<td>취소 및 환불 규정 <span style="color:red">*</span></td>
							<td>
								<textarea name="product_refund_1" required> ${fn:replace(product.product_refund_1 ,crcn,br)} </textarea>
							</td>
						</tr>
						<tr>
							<td>취소 및 환불 불가한 경우 <span style="color:red">*</span></td>
							<td>
								<textarea name="product_refund_2" required>${fn:replace(product.product_refund_2 ,crcn,br)}  </textarea>
							</td>
						</tr>
				</table>
					<!-- 삭제하는 이미지 정보 저장 -->
					<div id="deleteImageList">
					
					</div>
					<div align=center>
						<input type="submit" value="저장하기" class="confirm_btn">
						<a class="back_btn" href="${contextPath}/owner/product/ownerProductList.do?member_id=${memberInfo.member_id}&chapter=1">목록으로</a>
					</div>

				</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>