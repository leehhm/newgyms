/* 결제 내역 조회 토글*/
    $(document).ready(function () { // 페이지 document 로딩 완료 후 스크립트 실행
       $('.ord_hidden').hide();
       $('.ord_title').on('click',function () { //제목 버튼 클릭 시 
           var currentRow = $(this).closest('tbody'); //누른 곳의 tr값을 찾는다.
           var detail = currentRow.next('tbody'); //누른 tr의 다음 tr을 찾는다
           detail.toggle();
       });
    });