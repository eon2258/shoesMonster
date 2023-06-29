<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="../include/header.jsp"%>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
.selected {
	background-color: #ccc;
}
</style>

<script type="text/javascript">

//팝업으로 열었을 때
function popUp() {
	var queryString = window.location.search;
	var urlParams = new URLSearchParams(queryString);
	
	var isPop = urlParams.get("input");
	
	if(isPop==="null") {
		isPop = null;
	}
		
	$('#pagination a').each(function(){
		
   		var prHref = $(this).attr("href");
   			
			var newHref = prHref + "&input=" + isPop;
			$(this).attr("href", newHref);
			
	}); //페이징 요소	
	
	$('#input').val(isPop);
			
	if(isPop) {
    	
//     	$('#add').hide();
//     	$('#modify').hide();
//     	$('#delete').hide();
//     	$('#save').hide();
    	
   		$('table tr:not(:first-child)').click(function(){
   			
   			$(this).css('background', '#ccc');
    			
    		var empId = $(this).find('#empId').text();
    			
    		$('#'+isPop, opener.document).val(lineCode);
    			
    		window.close();
    	}); //테이블에서 누른 행 부모창에 자동입력하고 창 닫기
    		
     		
		} else {
			console.log("팝업아님");
	} //if(팝업으로 열었을 때)
		
} //popUp()

// 제이쿼리
	$(function() {
		popUp();
		
	});
 
 // 서치 기능
 $(document).ready(function () {
	
	 $('#searchButton').click(function(event) {
		
		 event.preventDefault(); // 페이지 이동 막기
		 
		 var searchCode = $('#searchCode').val().toLowerCase();
		 var searchName = $('#searchName').val().toLowerCase();
		 var searchdepartment = $('#searchdepartment').val().toLowerCase();
		 
		 $('#EmployeesTable tr').each(function () {
			
			 var code = $(this).find('td:nth-child(2)').text().toLowerCase();
			 var name = $(this).find('td:nth-child(3)').text().toLowerCase();
			 var department = $(this).find('td:nth-child(4)').text().toLowerCase();
			 
			 if(code.includes(searchCode)
				 && name.includes(searchName)
				 && place.includes(searchdepartment)
				 ){
				 $(this).show();
				 
			 }else{
				$(this).hide(); 
			 }
		});
	});
	 
}); // document
 
 
</script>

<!-- page content -->
<div class="right_col" role="main">


	<h2>사원 관리</h2>

	<input type="hidden" name="input" id="input" value="${input}">
	
	<label>사원 번호</label> <input type="text" id="searchCode"
		placeholder=" ( 10자리 )"> <label>사원 명</label> <input
		type="text" id="searchName" placeholder=""> <label>부서</label>
	<input type="text" id="searchdepartment" placeholder=""> <input
		type="button" id="searchButton" value="조회"> <br>
	<br>

	<table border="1" id="lineTable">
		<thead>
			<tr>
				<th></th>
				<th>사원번호</th>
				<th>사원명</th>
				<th>부서</th>
				<th>직책</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>입사일자</th>
				<th>재직구분</th>
				<th></th>
			</tr>
		</thead>

		<c:forEach var="vo" items="${empList }" varStatus="i">
			<tr>
				<td>${i.count}</td>
				<td id="empId">${vo.emp_id}</td>
				<td>${vo.emp_name}</td>
				<td>${vo.emp_department}</td>
				<td>${vo.emp_position}</td>
				<td>${vo.emp_email}</td>
				<td>${vo.emp_phone}</td>
				<td>${vo.emp_hiredate}</td>
				<td>${vo.emp_work}</td>
				<td>
					<button class="details-btn" data-id="${emp_id }">상세보기</button>
				</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<br>

	<div id="employees-details"></div>

	<script>
		$(document).ready(function() {
			$('.details-btn').click(function() {
				var employeeId = $(this).data('id');
				$.ajax({
					url : '/employee/' + employeeId,
					type : 'GET',
					success : function(response) {
						$('#employee-details').html(response);
					}
				});
			});
		});
	</script>


</div>
<!-- /page content -->
<%@ include file="../include/footer.jsp"%>
