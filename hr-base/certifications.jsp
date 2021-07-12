<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | Certifications</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<!-- Sweet Alert -->
<link href="<c:url value="/resources/css/plugins/sweetalert/sweetalert.css" />" rel="stylesheet">

<!-- PDF Viewer -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous" />

<style>
/********************************************* 
*******   Attribute PDF Viewer **********
*********************************************/
.pdfobject-container { height: 70rem; border: 1rem solid rgba(0,0,0,.1); }
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
									<div class="row m-t-sm">
										<div class="col-lg-12">
											<div class="panel blank-panel">
												<div class="panel-heading">
													<div class="panel-options">
														<ul class="nav nav-tabs">
															<li class="active"><a href="#tab-1" data-toggle="tab">All Employees Certifications <span class="label label-info" id="requests"></span></a></li>
															<li class=""><a href="#tab-2" data-toggle="tab">Pending for approval</a></li>
														</ul>
													</div>
												</div>

												<div class="panel-body">
													<div class="tab-content">
														<div class="tab-pane active" id="tab-1">
															<div class="feed-activity-list">
																<!-- Pending Leaves here -->
																<div class="table-responsive">
																	<table class="footable table table-stripped toggle-arrow-tiny table-hover employees-all-certifications" id="allEmployeesCertifications">
																		<thead>
																			<tr>
																				<th>ID</th>
																				<th data-hide="phone">Employee Name</th>
																				<th data-hide="phone">Course Name</th>
																				<th data-hide="phone,tablet">Start Year</th>
																				<th data-hide="phone,tablet">End Year</th>
																				<th data-hide="phone,tablet">Institution</th>
																				<th data-hide="phone,tablet">Country</th>
																				<th class="text-right">Action</th>
																			</tr>
																		</thead>
																		<tbody class="all-certifications">
																			
																		</tbody>
																	</table>
																</div>
															</div>

														</div>
														<div class="tab-pane" id="tab-2">
															<!-- Leaves Histories here -->
															<div class="table-responsive">
																<table
																	class="footable table table-stripped toggle-arrow-tiny table-hover pending-certifications">
																	<thead>
																		<tr>
																			<th>ID</th>
																				<th data-hide="phone">Employee Name</th>
																				<th data-hide="phone">Course Name</th>
																				<th data-hide="phone,tablet">Start Year</th>
																				<th data-hide="phone,tablet">End Year</th>
																				<th data-hide="phone,tablet">Institution</th>
																				<th data-hide="phone,tablet">Country</th>
																				<th class="text-right">Action</th>
																		</tr>
																	</thead>
																	<tbody class="non-approved-certifications">
																		
																	</tbody>
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
				
				<!--************************** Document (Certificates) Viewers Modal and Certificates reject forms ***************************-->
		
				<!-- **********************************************************
										1. Certifications 
				VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV-->
				
				<!--****************** Reject Certification Form ********************-->
				<div id="rejectCertificationXX" class="modal fade" aria-hidden="true" tabindex="9999" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-body">
								<div class="row">
									<div class="col-sm-12 rejectForm">
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				<div class="modal fade" id="largeModal" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
			      <div class="modal-dialog modal-lg">
			        <div class="modal-content">
			          <div class="modal-header">
			            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			            <h4 class="modal-title" id="myModalLabel">Document Viewer</h4>
			            <div class="rejectionComment"></div>
			          </div>
			          <div class="modal-body">
			            
			          </div>
			          <div class="modal-footer approvalOPtions">
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
	<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>
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
	<script src="<c:url value="/resources/js/modules/document-file-viewer.js" />"></script>
	<script src="<c:url value="/resources/js/pdfObject/pdfobject.js" />"></script>	

<script>
var applied = 0;
function applyTable2Certificate(){
	if(applied==0){
		$('.employees-all-certifications').DataTable({
	        dom: '<"html5buttons"B>lTfgitp',
	        buttons: [
	            { extend: 'copy'},
	            {extend: 'csv'},
	            {extend: 'excel', title: 'All Employee certifications'},
	            {extend: 'pdf', title: 'All Employee certifications'},

	            {extend: 'print',
	             customize: function (win){
	                    $(win.document.body).addClass('white-bg');
	                    $(win.document.body).css('font-size', '10px');

	                    $(win.document.body).find('table')
	                            .addClass('compact')
	                            .css('font-size', 'inherit');
	            }
	            }
	        ],
	        "pageLength": 25

	    });		
		applied = 1;
	}
}

var pendingCert = 0;
function pendingEducations(){
	if(pendingCert==0){
		$('.pending-certifications').DataTable({
	        dom: '<"html5buttons"B>lTfgitp',
	        buttons: [
	            { extend: 'copy'},
	            {extend: 'csv'},
	            {extend: 'excel', title: 'Pending certifications for Approval'},
	            {extend: 'pdf', title: 'Pending certifications for Approval'},

	            {extend: 'print',
	             customize: function (win){
	                    $(win.document.body).addClass('white-bg');
	                    $(win.document.body).css('font-size', '10px');

	                    $(win.document.body).find('table')
	                            .addClass('compact')
	                            .css('font-size', 'inherit');
	            }
	            }
	        ],
	        "pageLength": 25

	    });		
		pendingCert = 1;
	}
}


$(document).ready(function(){	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-certificationsAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){
					var approvalUrl = '${pageContext.request.contextPath}/approve-certification.htm/'+item.employeeid+'/'+item.id;
					$(".all-certifications").append('<tr>'
													+'<td>'+ ++i +'</td>'
													+'<td>'+ item.employeename +'</td>'
													+'<td>'+ item.certificationcategoryname +'</td>'
													+'<td>'+ item.datestart +'</td>'
													+'<td>'+ item.datend +'</td>'
													+'<td>'+ item.institution +'</td>'
													+'<td>'+ item.countryname +'</td>'
													+'<td><button class="btn btn-xs btn-success" onClick="documentViewer(\''+item.uri+'\','+item.approved+',\''+item.approvalComment+'\',\''+approvalUrl+'\','+item.employeeid+','+item.id+')"><i class="fa fa-file-pdf-o"></i> View</button></td>'
												 +'</tr>');
					
				});	
				// apply style
				applyTable2Certificate();
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-noApproved-certificationsAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){
					var approvalUrl = '${pageContext.request.contextPath}/approve-certification.htm/'+item.employeeid+'/'+item.id;
					$(".non-approved-certifications").append('<tr>'
													+'<td>'+ ++i +'</td>'
													+'<td>'+ item.employeename +'</td>'
													+'<td>'+ item.certificationcategoryname +'</td>'
													+'<td>'+ item.datestart +'</td>'
													+'<td>'+ item.datend +'</td>'
													+'<td>'+ item.institution +'</td>'
													+'<td>'+ item.countryname +'</td>'
													+'<td><button class="btn btn-xs btn-warning" onClick="documentViewer(\''+item.uri+'\','+item.approved+',\''+item.approvalComment+'\',\''+approvalUrl+'\','+item.employeeid+','+item.id+')"><i class="fa fa-file-pdf-o"></i> Approve</button></td>'
												 +'</tr>');
					
				});	
				// apply style
				pendingEducations();
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
})
</script>
<script>
/******************* Certification viewer and reject functions **********************/


function documentViewer(url,approved,comment,approvalUrl,userId,certificateId){	
	$(".rejectionComment").html('');
	$(".approvalOPtions").html('');
	var PDFRenderingView = $('#largeModal').find('.modal-body');
	var PDFUrl = documentRepositoryURL+url;
	PDFObject.embed(PDFUrl, PDFRenderingView);
	if(approved ==-1 && comment != null){
		$(".rejectionComment").append('<p><strong style="color: red;">Reasons for Rejection:</strong> '+comment+'</p>')
		$(".approvalOPtions").append('<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>');
	}
	
	if(approved == 1){
		$(".approvalOPtions").append('<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>');
	}
	
	if(approved ==0){
		$(".approvalOPtions").append('<a href="<c:url value="'+approvalUrl+'" />" class="btn btn-primary" title="Approve Certification"><i class="fa fa-check"></i> Approve</a>'
				+'<button onClick="rejectCertification('+userId+','+certificateId+')"  class="btn btn-danger">Reject</button>'
				+'<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>');
	} 	
	$('#largeModal').modal('show');
};

function rejectCertification(userId,certificateId){
	$('#largeModal').modal('hide');
	$(".rejectForm").html('');
	var apiUrl = '${pageContext.request.contextPath}/reject-certification.htm/'+userId+'/'+certificateId;
	console.log(apiUrl);
	$(".rejectForm").append(
			'<h3 class="m-t-none m-b">Reject Employee Certification.</h3>'
			+'<form action="'+apiUrl+'" role="form" method="post" modelAttribute="rejectCertification" name="employeeRejectCertification">'
				+'<div class="form-group">'
					+'<label>Reasons *</label> <input type="text" name="comment" placeholder="Write Reasons for rejection" class="form-control required">'
				+'</div>'
				+'<div>'
					+'<button class="btn btn-sm btn-primary pull-right m-t-n-xs" type="submit"> <strong>Submit</strong> </button>'
					+'<button type="button" class="btn btn-sm btn-white m-t-n-xs" data-dismiss="modal">Cancel</button>'
				+'</div>'
			+'</form>'		
	);
	$('#rejectCertificationXX').modal('show');
};
</script>

</body>
</html>