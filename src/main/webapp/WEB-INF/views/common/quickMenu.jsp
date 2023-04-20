<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<script>
	var array_index=0;
	var SERVER_URL="${contextPath}/thumbnails.do";
	function fn_show_next_goods(){
		var img_sticky=document.getElementById("img_sticky");
		var cur_goods_num=document.getElementById("cur_goods_num");
		var _h_product_id=document.frm_sticky.h_product_id;
		var _h_goods_fileName=document.frm_sticky.h_goods_fileName;
		if(array_index <_h_product_id.length-1)
			array_index++;
		 	
		var product_id=_h_product_id[array_index].value;
		var fileName=_h_goods_fileName[array_index].value;
		img_sticky.src=SERVER_URL+"?product_id="+product_id+"&fileName="+fileName;
		cur_goods_num.innerHTML=array_index+1;
	}


 function fn_show_previous_goods(){
	var img_sticky=document.getElementById("img_sticky");
	var cur_goods_num=document.getElementById("cur_goods_num");
	var _h_product_id=document.frm_sticky.h_product_id;
	var _h_goods_fileName=document.frm_sticky.h_goods_fileName;
	
	if(array_index >0)
		array_index--;
	
	var product_id=_h_product_id[array_index].value;
	var fileName=_h_goods_fileName[array_index].value;
	img_sticky.src=SERVER_URL+"?product_id="+product_id+"&fileName="+fileName;
	cur_goods_num.innerHTML=array_index+1;
}

function goodsDetail(){
	var cur_goods_num=document.getElementById("cur_goods_num");
	arrIdx=cur_goods_num.innerHTML-1;
	
	var img_sticky=document.getElementById("img_sticky");
	var h_product_id=document.frm_sticky.h_product_id;
	var len=h_product_id.length;
	
	if(len>1){
		product_id=h_product_id[arrIdx].value;
	}else{
		product_id=h_product_id.value;
	}
	
	
	var formObj=document.createElement("form");
	var i_product_id = document.createElement("input"); 
    
	i_product_id.name="product_id";
	i_product_id.value=product_id;
	
    formObj.appendChild(i_product_id);
    document.body.appendChild(formObj); 
    formObj.method="get";
    formObj.action="${contextPath}/goods/goodsDetail.do?product_id="+product_id;
    formObj.submit();
	
	
}
</script>  
 
<body>    
</body>
</html>
 
