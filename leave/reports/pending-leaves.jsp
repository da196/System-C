<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Pending Leaves</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   
	<!-- Date Picker CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />
	<style type="text/css">
		.sample-validation {
		}
	</style>
</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../../includes/sidebar.jsp" />		
		 <div id="page-wrapper" class="gray-bg">
		 
		 <!-- Top navigation Bar -->
		 <jsp:include page="../../includes/topbar.jsp" />
		 <!-- Top navigation bar ends -->
		 
	 <div class="wrapper wrapper-content">
		 <!-- Grid dashboard boxes starts -->
			<div class="row">
				<div class="col-lg-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>Filter Results for Pending Leaves</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <form role="form" class="form-inline" id="filterAttributes">
                                <div class="form-group col-sm-4">
									  <div style="position:relative">
									  	<div class="input-field col-sm-12">
								    		<input id="startDate" type="text" name="start-date" class="form-control" data-date-format="yyyy-mm-dd" placeholder="Select Start Date" autocomplete="off">
								    	</div>
									  </div>	  
								  </div>
								<div class="bdc" style="position:relative"></div>
                                <div class="form-group col-sm-4">
                                    <input id="endDate" type="text" name="start-date" class="form-control" data-date-format="yyyy-mm-dd" placeholder="Select End Date" autocomplete="off">
                                </div>
                                <button class="btn btn-success btn-sm" id="getPendingLeaves" type="button" value="submit"><i class="fa fa-filter"></i> Filter pending leaves</button>
                            </form>
                            <hr>
                            <!-- Pending leaves report -->
							<div class="table-responsive hideShow-status">
								<div class="table-header"></div>
								<table class="footable table table-stripped toggle-arrow-tiny table-hover pendingLeaves-datatable">
									<thead>
										<tr>
											<th>S/N</th>
											<th data-hide="phone">employee Name</th>
											<th data-hide="phone">Leave Type</th>
											<th data-hide="phone">Directorate/Unit</th>
											<th data-hide="phone">Planned Start date</th>
											<th data-hide="phone">Approver</th>
										</tr>
									</thead>
									<tbody class="pendingLeavesRows">
										
									</tbody>
								</table>
							</div>
                        </div>
                    </div>
                </div>
			</div>
			
			
		 <!-- Grid dashboard boxes ends -->
 		</div> 
 		
		 	<!-- Footer Include -->
			<jsp:include page="../../includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	</div>	
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<!-- Date Picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>

<script>
/****************** Date picker script *************************/
$(document).ready(function(){
	
	$('#startDate').datepicker({
		todayHighlight: true,
		 orientation: "bottom left",
		 format: "yyyy-mm-dd",
		container: ".bdc",
		autoclose: true
		//container: console.log($(this).datepicker())
	});
	
	$('#endDate').datepicker({
		todayHighlight: true,
		 orientation: "bottom left",
		 format: "yyyy-mm-dd",
		container: ".bdc",
		autoclose: true
		//container: console.log($(this).datepicker())
	});
	
	/************** Post/Add responsible by Ajax ***************/   	  	  
    $("#getPendingLeaves").click(function(){
    	var form = $('#filterAttributes');
        var action = 'post';            
        var formData = form.serialize();
        var date1 = $("#startDate").val()
        var date2 = $("#endDate").val()
        
        $.ajax({
    		url: '${pageContext.request.contextPath}/get-pending-leavesByAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{
    				$(".table-header").text("");
    				$(".pendingLeavesRows").text("");
    				if(date1 && date2){
    					$(".table-header").append('<p><h2><center> Pending Leaves that are to start from '+date1+' to '+date2+' </center></h2></p>');
    				}else{
    					$(".table-header").append('<p><h2><center> All Pending Leaves </center></h2></p>');
    				}
    				    				
    				$.each(data,function(i,item){
    					if(date1 && date2){
	    					if(item.startdate >= date1 && item.startdate <= date2){
	    						$(".pendingLeavesRows").append('<tr>'+
										'<td>'+ ++i +'</td>'+
										'<td>'+ item.firstname+' '+item.middlename+' '+item.lastname +'</td>'+
										'<td>'+ item.leavetypename +'</td>'+
										'<td>'+ item.employeeid +'</td>'+
										'<td>'+ item.startdate +'</td>'+
										'<td>'+ item.approverfullname +'</td>'+
									'</tr>');
	    					}
    					}else{
    						$(".pendingLeavesRows").append('<tr>'+
									'<td>'+ ++i +'</td>'+
									'<td>'+ item.firstname+' '+item.middlename+' '+item.lastname +'</td>'+
									'<td>'+ item.leavetypename +'</td>'+
									'<td>'+ item.employeeid +'</td>'+
									'<td>'+ item.startdate +'</td>'+
									'<td>'+ item.approverfullname +'</td>'+
								'</tr>');
    					}	
    					
    				});
    				$(".hideShow-status").show();
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});	
    	
    });  

});

$(function(){
    $(".hideShow-status").hide() // This hide table on page load
 });
 

</script>
</body>
</html>