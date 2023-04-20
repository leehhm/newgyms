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
	   var opt = 1;
	   function fn_addOption(){
	      $("#product_option").append(
	            " <div class='add_product_option'>"
	            + "<table id='mod_option'><tr><td><input type='text' name='optionList["+opt+"].product_option_name' placeholder='옵션명'></td>  "
	            + "<td><input type='text' name='optionList["+opt+"].product_option_price' placeholder='옵션가격'> 원 &nbsp;&nbsp;  </td>"
	            + "<td>&nbsp;&nbsp;  <a id='del_option_btn' class='del_option_btn' href='javascript:fn_delOption();'>X</a></td></tr></table></div>");      
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
	    		
		        var file = "<div class='img_preview'><input style='border:none;' type='file' name='detail_image' accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
		        $("#detail_image_list").append(file);
	    		
	    	} else if (type == 'price_image') {
	    		
		        var file = "<div class='img_preview'><input style='border:none;' type='file' name='price_image' accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
		        $("#price_image_list").append(file);
		        
	    	} else if (type == 'facility_image') {
	    		
		        var file = "<div class='img_preview'><input style='border:none;' type='file' name='facility_image' accept='image/*' required><a href='#this' name='file-delete'>X</a></div>";
		        $("#facility_image_list").append(file);
	    	}
	    	
	        $("a[name='file-delete']").on("click", function(e) {
	            e.preventDefault();
	            deleteFile($(this));
	        });
	    }
	 
	    function deleteFile(obj) {
	        obj.parent().remove();
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
				<form action="${contextPath}/owner/product/addNewProduct.do" method="post"  enctype="multipart/form-data">
					<p id="owner_product_title">상품 등록</p>

					<br><br>
					<span class="add_product_title">기본정보</span>
					<table id="product_info_table">
						<tr>
							<td>카테고리 <span style="color:red">*</span></td>
							<td colspan="3">
							<select id="product_sort" name="product_sort" style="padding: 8px;" required>
								<option value="">[필수] 카테고리 선택</option>
								<option value="헬스/PT">헬스/PT</option>
								<option value="요가/필라테스">요가/필라테스</option>
								<option value="스피닝">스피닝</option>
								<option value="크로스핏">크로스핏</option>
								<option value="기타">기타</option>
							</select>
							</td>
						</tr>
						<tr>
							<td>상품명 <span style="color:red">*</span></td>
							<td colspan="3"><input type="text" name="product_name" required></td>
						</tr>
						<tr>
							<td>대표 이미지 <span style="color:red">*</span></td>
							<td colspan="3"> 
								<div  class="img_preview">
									<img id="preview_main_img" src="#" />
									<input id="product_main_image" type="hidden" name="product_main_image">
									<input style="border:none;" type="file" name="product_main_image" onchange="previewMainImage(this);" required/>
								</div>
							</td>
						</tr>
						<tr>
							<td>정가 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_price" required> 원
							</td>
							
							<td style="text-align:center;">할인가 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_sales_price" required> 원
							</td>
						</tr>
						<tr>
							<td>구매 시 적립금 <span style="color:red">*</span></td>
							<td>
								<input type="text" pattern="[0-9]{*}" name="product_point"  required> 원
							</td>
						</tr>
						<tr>
							<td>옵션 <span style="color:red">*</span></td>
							<td colspan="3">
 							 <input type="text" name="optionList[0].product_option_name" placeholder="옵션명"  required>        
		 			         <input type="text" name="optionList[0].product_option_price" placeholder="옵션가격"  required> 원 &nbsp;&nbsp;       
				 			&nbsp;&nbsp;<a class="add_option_btn" href="javascript:fn_addOption();">옵션 추가</a>
 							</td>
						</tr>
						</table>
						
								<div id="product_option" style="padding-left:210px;">
							 </div>
<!-- 						
 -->								 
						<br><br>
						<span class="add_product_title">프로그램 정보</span>
						<table id="product_detail_table">					
						<tr>
							<td>프로그램 상세정보</td>
							<td>
								<textarea name="product_program_details" required> </textarea>
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
							 <div id="detail_image_list"> </div>
							</td>
						</tr>
						<tr>
							<td>가격 정보</td>
							<td>
								<textarea name=" product_price_details" required> </textarea>
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
						        <div id="price_image_list"></div>
							</td>
						</tr>
						<tr>
							<td>시설 정보</td>
							<td>
								<textarea name="product_facility_details" required> </textarea>
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
						        <div id="facility_image_list"></div>
							</td>
						</tr>
						<tr>
							<td>위치 정보</td>
							<td>	
								<textarea name=" product_location_details"> </textarea>
							</td>
						</tr>
						</table>
						
						<br><br>
						<span class="add_product_title">환불 정보</span>
						<table id="product_refund_table">					
						<tr>
							<td>취소 및 환불 규정 <span style="color:red">*</span></td>
							<td>
								<textarea name="product_refund_1" required> </textarea>
							</td>
						</tr>
						<tr>
							<td>취소 및 환불 불가한 경우 <span style="color:red">*</span></td>
							<td>
								<textarea name="product_refund_2" required> </textarea>
							</td>
						</tr>
				</table>
				
					<div align=center>
						<input type="submit" value="등록하기" class="confirm_btn">
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