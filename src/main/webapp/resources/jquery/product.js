
/* 탭 메뉴 */
	$(document).ready(function() {
	
		//When page loads...
		$(".tab_content").hide(); //Hide all content
		$("ul.tabs li:first").addClass("active").show(); //Activate first tab
		$(".tab_content:first").show(); //Show first tab content
	
		//On Click Event
		$("ul.tabs li").click(function() {
	
			$("ul.tabs li").removeClass("active"); //Remove any "active" class
			$(this).addClass("active"); //Add "active" class to selected tab
			$(".tab_content").hide(); //Hide all tab content
	
			var activeTab = $(this).find("a").attr("href"); //Find the href attribute value to identify the active tab + content
			$(activeTab).fadeIn(); //Fade in the active ID content
			return false;
		});
	
	});


/* QnA 목록 토글*/
    $(document).ready(function () { // 페이지 document 로딩 완료 후 스크립트 실행
    	$('.toggle_hidden').hide();
    	$('.toggle_show').on('click',function () { //제목 버튼 클릭 시 
        	var currentRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다. 
        	var detail = currentRow.next('tr'); //누른 tr의 다음 tr을 찾는다
        	detail.toggle();
 		});
    });
    



