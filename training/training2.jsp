<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | Training Form</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />"
	type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/animate.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- formvalidation io -->
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
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
				<!-- Page Body starts -->
				<div class="row">
					<div class="col-lg-12">
						<div class="tabs-container">
							<ul class="nav nav-tabs">
								<li class="active"><a data-toggle="tab" href="#tab-3">
										<i class="fa fa-map-marker"></i> Training Form
								</a></li>
							</ul>
							<div class="tab-content">
								<div id="tab-3" class="tab-pane active">
									<div class="panel-body">
										<p align="right">
											<button type="button"
												class="btn btn-outline btn-primary btn-xs"
												data-toggle="modal" data-target="#Training">+ Add
												Training</button>
										</p>

										<!-- Data Table Body -->
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-training">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Course Name</th>
														<th>Institution</th>
														<th>Expected Start Date</th>
														<th>Expected End Date</th>
														<th>Category</th>
														<th>Type</th>
														<th>Actions</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty training}">
														<c:forEach var="training" items="${training}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${training.employeeFullName}" /></td>
																<td><c:out value="${training.name}" /></td>
																<td><c:out value="${training.institution}" /></td>
																<td><c:out value="${training.dateexpectedstart}" /></td>
																<td><c:out value="${training.dateexpectedend}" /></td>
																<td><c:out value="${training.trainingcategory}" /></td>
																<td><c:out value="${training.trainingtype}" /></td>
																<td>
																	<button class="btn btn-info btn-circle btn-outline"
																		type="button" title="Edit Details">
																		<i class="fa fa-edit"></i>
																	</button> <a
																	href="<c:url value="/delete-designation.htm/${training.id}" />"
																	class="btn btn-danger btn-circle btn-outline"
																	title="Delete"><i class="fa fa-trash-o"></i></a>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Course Name</th>
														<th>Institution</th>
														<th>Expected Start Date</th>
														<th>Expected End Date</th>
														<th>Category</th>
														<th>Type</th>
														<th>Actions</th>
													</tr>
												</tfoot>
											</table>


										</div>
										<!-- Training Modal -->
										<div class="modal inmodal" id="Training" tabindex="-1"
											role="dialog" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content animated bounceInRight">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span
																class="sr-only">Close</span>
														</button>
														<h4 class="modal-title">+ Add Training</h4>
													</div>
														<form:form id="formAddTraining" 
														action="${pageContext.request.contextPath}/v1/training-add"
															modelAttribute="training" method="POST" enctype="multipart/form-data">
<%-- modelAttribute="training" action="${pageContext.request.contextPath}/v1/training-add" method="POST" enctype="multipart/form-data"--%>
														<%--  <form:form action="${pageContext.request.contextPath}/designation-create.htm" 
			                    role="form" method="post"  modelAttribute="designation" name="designationCreate"> --%>
														<div class="modal-body">
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Employee</label> <select class="form-control"
																			name="employeeid" id="employeeid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Course Name</label> <input type="text"
																			name="name" placeholder="Course Name"
																			class="form-control">
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label>Description</label>
																<textarea name="description" placeholder="Description"
																	class="form-control"></textarea>
															</div>
															<div class="form-group">
																<label>Institution</label> <input type="text"
																	name="Institution" placeholder="Institution"
																	class="form-control">
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Expected Start Date</label> <input type="date"
																			name="dateexpectedstart" id="dateexpectedstart"
																			class="form-control">
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Expected End Date</label> <input type="date"
																			name="date_expected_end" id="date_expected_end"
																			class="form-control"></input>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Financial Year</label> <select
																			class="form-control" name="financialyearid"
																			id="financialyearid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Category</label> <select class="form-control"
																			name="trainingcategoryid" id="trainingcategoryid"></select>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Type</label> <select class="form-control"
																			name="trainingtypeid" id="trainingtypeid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Initiator</label> <select class="form-control"
																			name="traininginitiatorid" id="traininginitiatorid"></select>
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label>Sponsor</label><select class="form-control"
																	name="trainingsponsorid" id="trainingsponsorid"></select>
															</div>

														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-white"
																data-dismiss="modal">Close</button>
															<button type="submit" value="submit"
																class="btn btn-primary" id="submitTraining">Save
																changes</button>

														</div>
													</form:form>
												</div>
											</div>
										</div>
										<!-- ./ New Designation Modal -->
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- Page body ends -->
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
	<script
		src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>
	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>
	<!-- jQuery UI -->
	<script
		src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
	<!-- select2 -->
	<script
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
	<!-- formvalidation.io -->
	<script
			src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
	<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>	
	<!-- date and date picker -->
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>	
<script
			src="<c:url value="/resources/js/validation/ui_validation_add_training.js" />"></script>	
<!-- Page-Level Scripts -->
<script>
    $(document).ready(function(){
        $('.dataTables-training').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Training'},
                {extend: 'pdf', title: 'Training'},

                {extend: 'print',
                 customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                                .addClass('compact')
                                .css('font-size', 'inherit');
                }
                }
            ]

        });

        
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
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#employeeid").append('<option value="'+item.id+'">'+fullName+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});

    	// pull training category
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-category-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#trainingcategoryid").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// pull training Initiator
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-initiation-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#traininginitiatorid").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// pull training Type
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-type-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#trainingtypeid").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// pull training sponsor
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-sponsor-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#trainingsponsorid").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// pull financial year sponsor
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-financial-year-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#financialyearid").append('<option value="'+item.id+'">'+item.description+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// Get All the Training of Current Finacial Year
    	
    });


</script>
</body>
</html>