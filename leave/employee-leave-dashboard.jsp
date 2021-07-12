<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | Employee Leave Dashboard</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<!-- Sweet Alert -->
<link href="<c:url value="/resources/css/plugins/sweetalert/sweetalert.css" />" rel="stylesheet">

<!-- PDF Viewer -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous" />


<style>
* {
	margin: 0;
	padding: 0;
}

.top-bar {
	background: #333;
	color: #fff;
	padding: 1rem;
}

.page-info {
	margin-left: 1rem;
}

.error {
	background: orangered;
	color: #fff;
	padding: 1rem;
}

.centered-text {
	text-align: center;
}
</style>

<style media="print">
 @page {
  size: auto;
  /* margin-top: 0;
  margin-bottom: 0; */
       }
</style>


</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../includes/sidebar.jsp" />
		<div id="page-wrapper" class="gray-bg">

			<!-- Top navigation Bar -->
			<jsp:include page="../includes/topbar.jsp" />
			<!-- Top navigation bar ends -->

			<div class="wrapper wrapper-content animated fadeInUp">
				<!-- Grid dashboard boxes starts -->
				<div class="row">
					<div class="col-lg-12">
						<div class="wrapper wrapper-content animated fadeInUp">
							<div class="ibox">
								<div class="ibox-content">
									<div class="row">
										<div class="col-lg-12">
											<div class="m-b-md">
												<a href="<c:url value="/leaves.htm" />"
													class="btn btn-white btn-xs pull-right"><i
													class="fa fa-arrow-left"></i> Back to Leaves</a>
												<h2>Employee Leaves Dashboard
													(${employeeDetails.firstName} ${employeeDetails.middleName}
													${employeeDetails.lastName})</h2>
													<input type="hidden" id="employeeId" value="${employeeDetails.id}">
											</div>
										</div>
									</div>
									<div class="row m-t-sm">
										<div class="col-lg-12">
											<div class="panel blank-panel">
												<div class="panel-heading">
													<div class="panel-options">
														<ul class="nav nav-tabs">
															<li class="active"><a href="#tab-1" data-toggle="tab">Pending Leave Requests <span class="label label-info" id="requests"></span></a></li>
															<li class=""><a href="#tab-2" data-toggle="tab">Leave History</a></li>
															<li class=""><a href="#tab-3" data-toggle="tab">Leave Balances</a></li>
														</ul>
													</div>
												</div>

												<div class="panel-body">

													<div class="tab-content">
														<div class="tab-pane active" id="tab-1">
															<div class="feed-activity-list">
																<!-- Pending Leaves here -->
																<div class="table-responsive">
																	<table class="footable table table-stripped toggle-arrow-tiny table-hover leaves-datatable" id="pendingRequests">
																		<thead>
																			<tr>
																				<th>ID</th>
																				<th data-hide="phone">Leave Type</th>
																				<th data-hide="phone">Date From</th>
																				<th data-hide="phone,tablet">Date To</th>
																				<th class="text-right">Action</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:if test="${not empty employeeLeaveApplications}">
																				<c:forEach var="employeeLeaveApplication"
																					items="${employeeLeaveApplications}"
																					varStatus="status">
																					<c:if
																						test="${employeeLeaveApplication.approved == 0}">
																						<tr>
																							<td>${status.count}</td>
																							<td><c:out
																									value="${employeeLeaveApplication.leavetypename}" /></td>
																							<td><c:out
																									value="${employeeLeaveApplication.startdate}" /></td>
																							<td><c:out
																									value="${employeeLeaveApplication.enddate}" /></td>
																							<td class="text-right">
																								<div class="btn-group">
																									<button class="btn-info btn btn-xs"
																										data-toggle="modal"
																										data-target="#requestDetails${status.count}">Details
																										& Options</button>
																								</div>
																							</td>
																						</tr>
																					</c:if>
																					<!--*************** Leave request details & approve details *****************-->
																					<div class="modal inmodal"
																						id="requestDetails${status.count}" tabindex="-1"
																						role="dialog" aria-hidden="true">
																						<div class="modal-dialog">
																							<div class="modal-content animated flipInY">
																								<div class="modal-header">
																									<button type="button" class="close"
																										data-dismiss="modal">
																										<span aria-hidden="true">&times;</span><span
																											class="sr-only">Close</span>
																									</button>
																									<h4 class="modal-title">Leave Request</h4>
																								</div>
																								<div class="modal-body">
																									<div id="contact-1" class="tab-pane active">
																										<div class="row m-b-lg">
																											<div class="col-lg-4 text-center">
																												<div class="m-b-sm">
																													<img alt="image" class="img-circle"
																														src="<c:url value="/resources/img/nodp.png" />"
																														style="width: 62px">
																												</div>
																											</div>
																											<div class="col-lg-8">
																												<h2>${employeeDetails.firstName}
																													${employeeDetails.middleName}
																													${employeeDetails.lastName}</h2>
																											</div>
																										</div>
																										<strong>Request Details</strong>

																										<ul class="list-group clear-list">
																											<li class="list-group-item fist-item"><span class="pull-right DesignationAjaxP"> </span> Designation </li>
																											<li class="list-group-item"><span class="pull-right DirectorateAjaxP"> </span> Directorate/Unit</li>
																											<li class="list-group-item"><span class="pull-right"> <strong>${employeeLeaveApplication.leavetypename}</strong>  </span> Leave Type</li>
																											<li class="list-group-item"><span class="pull-right EmploymentDateAjaxP"> </span> Employment Date</li>
																											<li class="list-group-item"><span class="pull-right"> <strong>${employeeLeaveApplication.startdate}</strong>  </span> Date From</li>
																											<li class="list-group-item"><span class="pull-right"> <strong>${employeeLeaveApplication.enddate}</strong>  </span> Date To</li>
																											<li class="list-group-item"><span class="pull-right"> <strong>${employeeLeaveApplication.numberofdays}</strong>  </span> Number Of Days</li>
																											<li class="list-group-item"><span class="pull-right"> <strong>${employeeLeaveApplication.actingfullname}</strong>  </span> Acting</li>
																										</ul>
																										<strong>Last Leave taken</strong>
																										<div class="vertical-timeline-block">
																											<div class="vertical-timeline-icon gray-bg">
																												<i class="fa fa-briefcase"></i>
																											</div>
																											<div class="vertical-timeline-content">
																												<p>Annual Leave</p>
																												<span class="vertical-date small text-muted">
																													20 May 2019 - 12 June 2019 </span>
																											</div>
																										</div>
																										<hr />
																									</div>
																									<div>
																										<form:form
																											action="${pageContext.request.contextPath}/approve-leave-request/${employeeLeaveApplication.id}"
																											role="form" method="post"
																											modelAttribute="approveLeave"
																											name="LeaveApproval">
																											<button
																												class="btn btn-sm btn-primary pull-right m-t-n-xs leaveRequestsOptions"
																												type="submit">
																												<strong></strong>
																											</button>
																											<button
																												class="btn btn-sm btn-danger m-t-n-xs"
																												type="submit" value="submit">
																												<strong></strong>
																											</button>
																										</form:form>
																									</div>
																								</div>
																								<div class="modal-footer">
																									<button type="button" class="btn btn-white"
																										data-dismiss="modal">Close</button>
																								</div>
																							</div>
																						</div>
																					</div>
																				</c:forEach>
																			</c:if>
																		</tbody>
																		<tfoot>
																			<tr>
																				<td colspan="7">
																					<ul class="pagination pull-right"></ul>
																				</td>
																			</tr>
																		</tfoot>
																	</table>
																</div>
															</div>

														</div>
														<div class="tab-pane" id="tab-2">
															<!-- Leaves Histories here -->
															<div class="table-responsive">
																<table
																	class="footable table table-stripped toggle-arrow-tiny table-hover leaves-datatable">
																	<thead>
																		<tr>
																			<th>ID</th>
																			<th data-hide="phone">Leave Type</th>
																			<th data-hide="phone">Date From</th>
																			<th data-hide="phone,tablet">Date To</th>
																			<th data-hide="phone">Status</th>
																			<th class="text-right">Action</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:if test="${not empty employeeLeaveApplications}">
																			<c:forEach var="employeeLeaveApplication"
																				items="${employeeLeaveApplications}"
																				varStatus="status">
																				<tr>
																					<td>${status.count}</td>
																					<td><c:out value="${employeeLeaveApplication.leavetypename}" /></td>
																					<td><c:out value="${employeeLeaveApplication.startdate}" /></td>
																					<td><c:out value="${employeeLeaveApplication.enddate}" /></td>
																					<td>
																						<c:if test="${employeeLeaveApplication.approved == 0}">
																								<span class="label label-info">Pending</span>
																						</c:if> 
																						<c:if test="${employeeLeaveApplication.approved == 1}">
																								<span class="label label-success">Approved</span>
																						</c:if> 
																						<c:if test="${employeeLeaveApplication.approved == 2}">
																								<span class="label label-danger">Rejected</span>
																						</c:if> 
																						<c:if test="${employeeLeaveApplication.approved == 3}">
																							<span class="label label-warning">System Cancelled</span>
																						</c:if>
																						<c:if test="${employeeLeaveApplication.approved == 4}">
																							<span class="label label-primary">Recalled</span>
																						</c:if>
																					</td>
																					<td class="text-right">
																						<c:if test="${employeeLeaveApplication.approved == 1}">
																							<div class="btn-group">
																								<button class="btn-info btn btn-xs" data-toggle="modal" data-target="#leaveDocument${status.count}">
																									Leave Document
																								</button>
																							</div>
																						</c:if>
																						<c:if test="${employeeLeaveApplication.approved != 1}">
																							<div class="btn-group">
																								<button class="btn-white btn btn-xs"">
																									Request Details
																								</button>
																							</div>
																						</c:if>
																					</td>
																				</tr>

																				<!--*************** Leave request document *****************-->
																				<div class="modal inmodal"
																					id="leaveDocument${status.count}" tabindex="-1"
																					role="dialog" aria-hidden="true">
																					<div class="modal-dialog modal-lg">
																						<div class="modal-content animated flipInY">
																							<div class="modal-header">
																								<button type="button" class="close"
																									data-dismiss="modal">
																									<span aria-hidden="true">&times;</span><span
																										class="sr-only">Close</span>
																								</button>
																								<h4 class="modal-title">Leave Application Document</h4>
																							</div>
																							<div class="modal-body">

																								<div class="row" id="printableArea">
																									<div class="col-md-12">
																										<p class="centered-text">
																											THE UNITED REPUBLIC OF TANZANIA
																											</hp>
																										<h3 class="font-bold m-b-xs centered-text">
																											TANZANIA COMMUNICATION REGULATORY AUTHORITY</h3>
																										<p class="centered-text">ISO 9001:2015
																											CERTIFIED</p>
																										<p class="centered-text">
																											<img src="<c:url value="/resources/img/logo2.png" />" width="100px">
																										</p>
																										<h4 class="centered-text">TCRA/HR/FORM
																											No. 8A</h4>
																										<h2 class="centered-text">LEAVE
																											APPLICATION FORM</h2>
																										<hr>

																										<h4 class="centered-text">SECTION A:</h4>
																										<div class="RequestContents" style="margin-left:30px; margin-right:30px;">
																											<p>1. Full Name:  <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">${employeeDetails.firstName} ${employeeDetails.middleName} ${employeeDetails.lastName}</strong></p>
																											<p class="DesignationAjax">2. Designation: </p>
																											<p class="DirectorateAjax">3. Directorate/Unit: </p>
																											<p>4. Type of Leave: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">${employeeLeaveApplication.leavetypename}</strong></p>
																											<p class="EmploymentDateAjax">5. Employment Date: </p>
																											<p>6. Any Other leave: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">N/A</strong></p>
																											<p>7. Leave start date: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">${employeeLeaveApplication.startdate}</strong></p>
																											<p>8. Leave end date: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">${employeeLeaveApplication.enddate}</strong></p>
																											<p>9. Leave address: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">${employeeLeaveApplication.contactaddress}</strong></p>
																										</div>
																										
																										<h4 class="centered-text">SECTION B:</h4>
																										<h5 class="centered-text"><i>(Completed by HoD)</i></h5>
																										<p>10. It is hereby certified that information provided in Section A is correct and <strong style="text-align:center; border-bottom: 1px dotted;">&nbsp;&nbsp;&nbsp; ${employeeLeaveApplication.numberofdays} &nbsp;&nbsp;&nbsp;   </strong> days is/are recommended</p>
																										<h5 class="centered-text"><i>Signed by HoD</i></h5>
																										<p> Signature: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted"></strong></p>
																										<p> HoD/Unit: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted"></strong></p>
																										<p> Date: <strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted"></strong></p>
																									
																										<hr>
																									</div>
																								</div>


																							</div>
																							<div class="modal-footer">
																								<button class="btn btn-primary btn-sm download-pdf" onclick="printDiv('printableArea')">
																									<i class="fa fa-file-pdf-o"></i> Print Form
																								</button>
																								<button type="button" class="btn btn-white"
																									data-dismiss="modal">Close</button>
																							</div>
																						</div>
																					</div>
																				</div>
																			</c:forEach>
																		</c:if>
																	</tbody>
																	<tfoot>
																		<tr>
																			<td colspan="7">
																				<ul class="pagination pull-right"></ul>
																			</td>
																		</tr>
																	</tfoot>
																</table>
															</div>
														</div>
														
														<div class="tab-pane" id="tab-3">
															<!-- Leaves Balances -->
															<div class="table-responsive">
																<table
																	class="footable table table-stripped toggle-arrow-tiny table-hover leaves-datatable">
																	<thead>
																		<tr>
																			<th>S/N</th>
																			<th data-hide="phone">Leave Type</th>
																			<th data-hide="phone">Total Days</th>
																			<th data-hide="phone,tablet">Days Taken</th>
																			<th data-hide="phone">Days Left</th>
																		</tr>
																	</thead>
																	<tbody class="leaveBalancesTable">
																		
																	</tbody>
																	<tfoot>
																		<tr>
																			<td colspan="7">
																				<ul class="pagination pull-right"></ul>
																			</td>
																		</tr>
																	</tfoot>
																</table>
															</div>
														</div>
													</div>
												</div>

											</div>
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
	<script
		src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>

	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>

	<!-- jQuery UI -->
	<script
		src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

	<!-- Sweet Alert -->
	<script
		src="<c:url value="/resources/js/plugins/sweetalert/sweetalert.min.js" />"></script>

	<!-- PDF Viewer -->
	<script src="https://mozilla.github.io/pdf.js/build/pdf.js"></script>
	
	<!-- jsPDF -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.2.0/jspdf.umd.min.js"></script>
<script>
var table = document.getElementById("pendingRequests");
var rows = table.tBodies[0].rows.length;
$("#requests").append(rows);
</script>
<script>
function printPDF() {
    var printDoc = new jsPDF();
    printDoc.fromHTML($('#printableAreaXXX').get(0), 10, 10, {'width': 180});
    printDoc.autoPrint();
    printDoc.output("dataurlnewwindow"); // this opens a new popup,  after this the PDF opens the print window view but there are browser inconsistencies with how this is handled
}
</script>
<script>
	$(document).ready(function() {
		$('.leaveRequestsOptions').click(function() {
			swal({
				title : "Success",
				text : "Request has been Approved!",
				type : "success"
			});
			location.reload();
		});
	});
</script>
<script>
function printDiv(printableAreaXXX) {
    var printContents = document.getElementById(printableAreaXXX).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
</script>
<script>
$(document).ready(function(){
	//----------------------------------------------//
	// 	      Employees details by Id Ajax
	//----------------------------------------------//
	var empID = document.getElementById("employeeId").value;
	$.ajax({
		url: '${pageContext.request.contextPath}/get-employeesDetailsByIdAjax/'+empID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$(".DesignationAjaxP").append('<strong>'+data.designation+'</strong>');
				$(".DirectorateAjaxP").append('<strong>'+data.unit+'</strong>');
				$(".EmploymentDateAjaxP").append('<strong>'+data.dateofemployment+'</strong>');
				
				$(".DesignationAjax").append('<strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">'+data.designation+'</strong>');
				$(".DirectorateAjax").append('<strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">'+data.unit+'</strong>');
				$(".EmploymentDateAjax").append('<strong class="col-md-9 pull-right" style="text-align:left; border-bottom: 1px dotted">'+data.dateofemployment+'</strong>');
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-leaveBalancesByEmployeeIdAjax/'+empID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{					
				$.each(data,function(i,item){
					$(".leaveBalancesTable").append('<tr><td>'+ ++i +'</td><td>'+ item.leavetypename +'</td><td>'+ item.daystotal +'</td><td>'+ item.daystaken +'</td><td>'+ item.daysremaining +'</td></tr>');
					
				});			
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
})
</script>

</body>
</html>