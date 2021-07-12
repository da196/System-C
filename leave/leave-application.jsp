<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Leave Application</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/plugins/datapicker/datepicker3.css" />" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   
   <!-- date picker -->
   <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../includes/sidebar.jsp" />		
		 <div id="page-wrapper" class="gray-bg">
		 
		 <!-- Top navigation Bar -->
		 <jsp:include page="../includes/topbar.jsp" />
		 <!-- Top navigation bar ends -->
		 
	 <div class="wrapper wrapper-content">
		 <!-- Grid dashboard boxes starts -->
				<div class="row">
					<div class="col-lg-12">
		                    <div class="ibox float-e-margins">
		                        <div class="ibox-title">
		                            <h5>Employee Leave Application Form</h5>
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
		                        	<div class="row">
			                        	<div class="col-sm-8 b-r">
				                            <form:form action="${pageContext.request.contextPath}/create-leave-request.htm" 
						                            role="form" method="post"  modelAttribute="leaveRequest" name="newLeaveRequest" class="form-horizontal">
				                                <div class="form-group"><label class="col-lg-2 control-label">Employee</label>
				
				                                    <div class="col-lg-10"><select class="employees-via-ajax" name="employeeid" style="width: 100%"></select></div>
				                                </div>
				                                <div class="form-group"><label class="col-lg-2 control-label">Leave Type</label>
				
				                                    <div class="col-lg-10"><select class="leave-types-via-ajax" name="leavetypeid" style="width: 100%"></select></div>
				                                </div>
				                                <div class="form-group"><label class="col-lg-2 control-label">Date From</label>
				
				                                    <div class="col-lg-10"><input type="date" name="startdate" placeholder="Date From" class="form-control"></div>
				                                </div>
				                                <div class="form-group"><label class="col-lg-2 control-label">Date To</label>
				
				                                    <div class="col-lg-10"><input type="date" name="enddate" placeholder="Date To" class="form-control"></div>
				                                </div>
				                                <div class="form-group"><label class="col-lg-2 control-label">Acting</label>
				
				                                    <div class="col-lg-10"><select class="employees-via-ajax" name="acting" style="width: 100%"></select></div>
				                                </div>
				                                <div class="form-group"><label class="col-lg-2 control-label">Contact Address</label>
				
				                                    <div class="col-lg-10"><input type="text" name="contactaddress" placeholder="Contact Address" class="form-control"></div>
				                                </div>				                                		                                
				                                <div class="form-group">
				                                    <div class="col-lg-offset-2 col-lg-10">
				                                        <button class="btn btn-sm btn-primary btn-block" type="submit" value="submit" id="submitLeaveRequest">Submit Leave Request</button>
				                                    </div>
				                                </div>
				                            </form:form>
			                            </div>
			                            <div class="col-sm-4">
			                                <div class="widget lazur-bg p-xl">
						                        <h2>Applicable Leaves</h2>
						                        <ul class="list-unstyled m-t-md">
						                            <li>
						                                <span class="fa fa-ambulance m-r-xs"></span>
						                                <label>Sick Leave:</label>
						                                (For Specified Days)
						                            </li>
						                            <li>
						                                <span class="fa fa-child m-r-xs"></span>
						                                <label>Maternity Leave:</label>
						                                (1 Child - 84 Days, Twins - 100 Days)
						                            </li>
						                        </ul>
						                    </div>
			                            </div>
		                            </div>
		                        </div>
		                    </div>
		                </div>
				</div>
		 <!-- Grid dashboard boxes ends -->
 		</div> 
		 	<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	</div>	
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/datapicker/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/plugins/validate/jquery.validate.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<!-- Date picker -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>

<script>
$(document).ready(function(){
	//----------------------------------------------//
	// 	      Employees list Select2 Ajax
	//----------------------------------------------//
	$(".employees-via-ajax").select2();
	$(".employees-via-ajax").append('<option value="0">Select employee</option>');
	// pull employees
	$.ajax({
		url: '${pageContext.request.contextPath}/get-employees-listAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{								
				$.each(data,function(i,item){	
					var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
					$(".employees-via-ajax").append('<option value="'+item.id+'">'+fullName+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	//----------------------------------------------//
	// 	      Leave Types Select2 Ajax
	//----------------------------------------------//
	$(".leave-types-via-ajax").select2();
	$(".leave-types-via-ajax").append('<option value="0">Select Leave Type</option>');
	// pull employees
	$.ajax({
		url: '${pageContext.request.contextPath}/get-leave-typesAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{	
				$.each(data,function(i,item){
					if(item.id == 2 || item.id == 4){
						$(".leave-types-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					}
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
})
</script>

<script>
$(function() {
  $('input[name="birthday"]').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    autoclose: true,
    minYear: 1900,
    maxYear: parseInt(moment().format('YYYY'),10)
  });
});
</script>

</body>
</html>