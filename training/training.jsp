<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HRMS | Training</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/dist/css/formValidation.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<link href="<c:url value="/resources/css/style.custom.css" />" rel="stylesheet">
<style>
.input-group-addon.select-input {
	width:82px;
	padding: 0;
}

.input-group-addon.select-input select {
	border: none;
	height: 32px;
}
.input-group-full-width{
	width:100%;
}
</style>

<style>
.td-large-x{
	min-width:220px;
}
.td-large{
	min-width:172px;
}
.td-medium{
	min-width:102px;
}
.btn-circle-size{
	width:18px;
	height:18px;
}

.select2-close-mask {
	z-index: 2099;
}

.select2-dropdown {
	z-index: 3051;
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
																    <button onClick="showTraningDetails('${training.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="deleteTraningDetails('${training.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>
																
																	
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

										<!-- End Data table -->
										</div>
										<!-- Training Modal -->
										<div class="modal inmodal" id="Training" tabindex="-1" role="dialog" aria-hidden="true">
											<div class="modal-dialog">
												<div class="modal-content animated bounceInRight">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">
															<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
														</button>
														<h4 class="modal-title">+ Add Training</h4>
													</div>
													<form:form action="${pageContext.request.contextPath}/v1/training-add" role="form" method="post"
														modelAttribute="training" id="formAddTraining" enctype="multipart/form-data">
														
														<div class="modal-body">
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Employee</label> 
																		<select class="form-control input-square" name="employeeid"
																			id="employeeid"></select>																			
															
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Course Name</label> <input type="text" name="name" placeholder="Course Name"
																			class="form-control">
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label>Description</label>
																<textarea name="description" id="" placeholder="Description" class="form-control"></textarea>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Institution</label> <input type="text" name="institution" id="institution"
																			placeholder="Institution" class="form-control">
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Institution Address</label>
																		<textarea name="Institution Address" id="institutionaddress"
																			placeholder="Institution Address" class="form-control"></textarea>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Currency</label>
																		
																		<select class="form-control" name="currencyid"
																			id="currencyid"></select>
																		<%-- <select name=currencyid class="form-control">
										
																			<c:forEach var="currency" items="${currencies}">
																				<option id="currencyid" value="${currency.id}">${currency.name}</option>
																			</c:forEach>
																		</select> --%>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Cost/Budget</label>
										
																		<input type="text" name="trainingcost" id="trainingcost" placeholder="Cost/Budget"
																			class="form-control">
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Expected Start Date</label> <input type="date" name="dateexpectedstart"
																			id="dateexpectedstart" class="form-control">
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Expected End Date</label> <input type="date" name="date_expected_end"
																			id="date_expected_end" class="form-control"></input>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Financial Year</label> <select class="form-control" name="financialyearid"
																			id="financialyearid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Category</label> 
																		<select class="form-control" name="trainingcategoryid"
																			id="trainingcategoryid"></select>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Type</label> <select class="form-control" name="trainingtypeid"
																			id="trainingtypeid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Initiator</label> <select class="form-control" name="traininginitiatorid"
																			id="traininginitiatorid"></select>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Sponsor</label><select class="form-control" name="trainingsponsorid"
																			id="trainingsponsorid"></select>
																	</div>
																</div>
																<div class="col-sm-6">
																	<div class="form-group">
																		<label>Attach File</label>
																		<input type="file" class="form-control" id="feestructureattachment"
																			name=feestructureattachment>
																	</div>
																</div>
															</div>
										
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
															<button type="button" value="submit" class="btn btn-primary" id="submitTrainingBtn">Save
																changes</button>
										
														</div>
													</form:form>
												</div>
											</div>
										</div>
										<!-- ./ New Training Modal -->
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
				</div>
				<!-- Page body ends -->
			
			
				<!-- Modals -->
	<!-- view and edit -->
	<div class="modal inmodal" id="TrainingDetailsModal"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="training-details.jsp" />
		<!-- End Add Modal-->
	</div>
		</div>
	</div>
	</div>
			
		
		<!-- /.page-wrapper -->

<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<script src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>

		<script
			src="<c:url value="/resources/dist/js/plugins/Bootstrap.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/plugins/StartEndDate.min.js" />"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
		<!-- date and date picker -->
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
			<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>
		

	
	<script src="<c:url value="/resources/js/validation/ui_validation_training_add.js" />"></script>
	
		<script src="<c:url value="/resources/js/validation/ui_validation_training_update.js" />"></script>
	<script>
	$.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name]) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	
	</script>
<!-- Page-Level Scripts -->
<script>
$(document).ready(function() {

	$('#employeeid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});
	
	$('#trainingcategoryid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});
	
	$('#currencyid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});
	
	$('#trainingtypeid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});
	
	$('#trainingsponsorid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});

	$('#financialyearid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});

	$('#traininginitiatorid').select2({
		width : "100%",
		dropdownParent: $("#Training")
	});
	
	//Edit
	$('#employeeidEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});
	$('#trainingcategoryidEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});
	
	$('#currencyidEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});
	
	$('#trainingtypeidEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});
	
	$('#trainingsponsoridEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});

	$('#financialyearidEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});

	$('#traininginitiatoridEdit').select2({
		width : "100%",
		dropdownParent: $("#TrainingDetailsModal")
	});
	
});


function showTraingDetailsModal(data){
	$('#id').val(data.id);
	$('#employeeidEdit').val(data.employeeid).trigger("change");
	$('#name').val(data.name);
	$('#description').val(data.description);
	$('#institutionEdit').val(data.institution);
	$('#dateexpectedstartEdit').val(data.dateexpectedstart);
	$('#date_expected_end_edit').val(data.dateexpectedend);
	$('#financialyearidEdit').val(data.financialyearid).trigger("change");
	$('#trainingcategoryidEdit').val(data.trainingcategoryid).trigger("change");
	$('#trainingtypeidEdit').val(data.trainingtypeid).trigger("change");
	$('#traininginitiatoridEdit').val(data.traininginitiatorid).trigger("change");
	$('#trainingsponsoridEdit').val(data.trainingsponsorid).trigger("change");
	
	$('#institutionaddressEdit').val(data.institutionaddress);
	$('#currencyidEdit').val(data.currencyid).trigger("change");
	$('#trainingcostEdit').val(data.trainingcost);
	//$('#trainingsponsoridEdit').val(data.trainingsponsorid);

	// show modal
	$('#TrainingDetailsModal').modal();
}
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
    					$("#employeeidEdit").append('<option value="'+item.id+'">'+fullName+'</option>');
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
    					$("#trainingcategoryidEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
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
    					$("#traininginitiatoridEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
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
    					$("#trainingtypeidEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    	
    	// pull currency
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-currency-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#currencyid").append('<option value="'+item.id+'">'+item.name+'</option>');
    					$("#currencyidEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
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
    					$("#trainingsponsoridEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
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
    					$("#financialyearidEdit").append('<option value="'+item.id+'">'+item.description+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});  	
    	// Get All the Training of Current Finacial Year   	
    });
    
    //************************************
	// show details
	function showTraningDetails(trainingId){
		if(trainingId){			
			//var baseUrl = getBaseUrl();
			//var contextPath = getContextPath();
			//var $apiUrl = baseUrl.concat(contextPath).concat("/v1/payroll-loan-heslb-by-loan/").concat(loanId);
			var $apiUrl = "${pageContext.request.contextPath}/v1/training-view-details/"+trainingId;
			$.ajax({
				url:$apiUrl,
				type:'GET',
				statusCode: {            	    	
        	    	208: function(responseObject, textStatus, jqXHR) {
        	            showError("Duplicate Training details not allowed.");
        	        },
        	        401: function(responseObject, textStatus, jqXHR) {
        	        	showError("You are not authorized to peform this action.");
        	        },
        	        404: function(responseObject, textStatus, jqXHR) {
        	        	showError("You are Training Details not found");
        	        },
        	        412: function(responseObject, textStatus, jqXHR) {
        	        	showError("Your request is not valid, please reveiew and submit again");
        	        },
        	        500: function(responseObject, textStatus, errorThrown) {
        	        	console.log("Server failed to process your request, please try again later");
        	        },
        	        503: function(responseObject, textStatus, errorThrown) {
        	        	showError("Loan service unavailable");
        	        }           
        	    },
				success: function(data, textStatus, jqXHR){
					console.log(JSON.stringify(data));
					// update data
					showTraingDetailsModal(data);
				},
				error:function(jqXHR, textStatus, errorThrown){
					console.log("Failed to retrieve Training details");
				}
			});
		}
	}
	
	// show details
	function deleteTraningDetails(trainingId){
		if(trainingId){			

			var $apiUrl = "${pageContext.request.contextPath}/v1/training-delete/"+trainingId;
			
            $.confirm({
                title: 'Warning!',
                content: 'Please confirm to delete Training.',
                type: 'red',
                buttons: {
                    ok: {
                        text: "ok",
                        btnClass: 'btn-danger',
                        keys: ['enter'],
                        action: function(){
                			$.ajax({
                				url:$apiUrl,
                				type:'GET',
                				statusCode: {            	    	
                        	    	208: function(responseObject, textStatus, jqXHR) {
                        	            showError("Duplicate Training details not allowed.");
                        	        },
                        	        401: function(responseObject, textStatus, jqXHR) {
                        	        	showError("You are not authorized to peform this action.");
                        	        },
                        	        404: function(responseObject, textStatus, jqXHR) {
                        	        	showError("You are Training Details not found");
                        	        },
                        	        412: function(responseObject, textStatus, jqXHR) {
                        	        	showError("Your request is not valid, please reveiew and submit again");
                        	        },
                        	        500: function(responseObject, textStatus, errorThrown) {
                        	        	console.log("Server failed to process your request, please try again later");
                        	        },
                        	        503: function(responseObject, textStatus, errorThrown) {
                        	        	showError("Loan service unavailable");
                        	        }           
                        	    },
                				success: function(data, textStatus, jqXHR){
                					console.log(JSON.stringify(data));
                                    $.confirm({
                                        title: 'Congrats!',
                                        content: 'Training successfully deleted.',
                                        type: 'green',
                                        buttons: {
                                            ok: {
                                                text: "ok",
                                                btnClass: 'btn-primary',
                                                keys: ['enter'],
                                                action: function(){
                                                    // close dialog
                                                   // hideEditHeslbLoanModal();
                                                    // reload
                                                    window.location.reload(true);
                                                }
                                            },
                                        }
                                    });	
                                    },
                				error:function(jqXHR, textStatus, errorThrown){
                					console.log("Failed to retrieve Training details");
                				}
                			});
                        }
                    },  cancel: {
                        text: "Cancel",
                        btnClass: 'btn-info',
                        keys: ['enter'],
                        action: function(){
                        	 window.location.reload(true);
                        }
                    }
                }
            });	

		}
	}
	

</script>


</body>
</html>