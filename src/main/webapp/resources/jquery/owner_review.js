/* 결제 내역 조회 토글*/
    $(document).ready(function () { // 페이지 document 로딩 완료 후 스크립트 실행
       $('.owner_review_hidden').hide();
       $('.owner_review_title').on('click',function () { //제목 버튼 클릭 시 
           var currentRow = $(this).closest('tr'); //누른 곳의 tr값을 찾는다.
           var detail = currentRow.next('tr'); //누른 tr의 다음 tr을 찾는다
           detail.toggle();
       });
    });