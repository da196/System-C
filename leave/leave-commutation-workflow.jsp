<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Leave Commutation Workflow</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">

<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<style>
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
            <li class="active"><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Leave Commutation Workflow</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-3" class="tab-pane active">
                <div class="panel-body">
										<p align="right">
											<button type="button"
												class="btn btn-outline btn-primary btn-xs"
												data-toggle="modal" data-target="#addTrainingWorkflow">+ Add Leave Commutation
												Workflow</button>
										</p>
										<br>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-leaveTypes">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Workflow Name</th>
														<th>Workflow Code</th>
														<th>Description</th>
														<th>Supervisor Designation</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty workflow}">
														<c:forEach var="workflow"
															items="${workflow}" varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${workflow.name}" /></td>
																<td><c:out value="${workflow.code}" /></td>
																<td><c:out value="${workflow.description}" /></td>
																<td><c:out
																		value="${workflow.supervisordesignation}" /></td>
																<td>
																	<button onClick="showTraningDetails('${workflow.id}');"
																		class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details">
																		<i class="fa fa-eye"></i>
																	</button>

																	<button
																		onClick="deleteTraningDetails('${workflow.id}');"
																		class="btn btn-danger btn-circle btn-outline"
																		title="Delete" type="button">
																		<i class="fa fa-trash-o"></i>
																	</button>


																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Workflow Name</th>
														<th>Workflow Code</th>
														<th>Description</th>
														<th>Supervisor Designation</th>
														<th>Action</th>
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

	<!-- view and edit -->
	<div class="modal inmodal" id="addTrainingWorkflow"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="leave-commutation-workflow-add.jsp" />
		<!-- End Add Modal-->
	</div>
	
		<!-- view and edit -->
	<div class="modal inmodal" id="editTrainingWorkflow"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="leave-commutation-workflow-edit.jsp" />
		<!-- End Add Modal-->
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
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<script
src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
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
		

	<script src="<c:url value="/resources/js/validation/ui_validation_leave_commutation_workflow-add.js" />"></script>
	<script src="<c:url value="/resources/js/validation/ui_validation_leave_commutation_workflow-edit.js" />"></script>
<!-- Page-Level Scripts -->
<script>

$(document).ready(function() {

	
	$('#supervisordesignationid').select2({
		width : "100%",
		dropdownParent: $("#addTrainingWorkflow")
	});
	
	$('#supervisordesignationidEdit').select2({
		width : "100%",
		dropdownParent: $("#editTrainingWorkflow")
	});

});
    $(document).ready(function(){
        $('.dataTables-leaveTypes').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Leave Commutation Workflow'},
                {extend: 'pdf', title: 'Leave Commutation Workflow'},

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
        

    	// pull training category
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-supervisor-designation-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					//var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
                       // $("#ddlNationality").append($("<option></option>").val(value.CountryId).html(value.CountryName));  
    					$("#supervisordesignationid").append('<option value="'+item.id+'">'+item.name+'</option>');
    					$("#supervisordesignationidEdit").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
    });

    

    function showTraingDetailsModal(data){
    	//alert(data.dateexpectedstart);
    	$('#id').val(data.id);
    	$('#nameEdit').val(data.name);
    	$('#codeEdit').val(data.code);
    	$('#supervisordesignationidEdit').val(data.supervisordesignationid).trigger("change");
    	$('#descriptionEdit').val(data.description);
    
    	// show modal
    	$('#editTrainingWorkflow').modal();
    }
    // show details
    	function showTraningDetails(id){
    	
    		if(id){			

    			var $apiUrl = "${pageContext.request.contextPath}/v1/leave-commutation-workflow-view-details/"+id;
    			$.ajax({
    				url:$apiUrl,
    				type:'GET',
    				statusCode: {            	    	
            	    	208: function(responseObject, textStatus, jqXHR) {
            	            showError("Duplicate Leave Commutation Workflow details not allowed.");
            	        },
            	        401: function(responseObject, textStatus, jqXHR) {
            	        	showError("You are not authorized to peform this action.");
            	        },
            	        404: function(responseObject, textStatus, jqXHR) {
            	        	showError("You are Leave Commutation Workflow Details not found");
            	        },
            	        412: function(responseObject, textStatus, jqXHR) {
            	        	showError("Your request is not valid, please reveiew and submit again");
            	        },
            	        500: function(responseObject, textStatus, errorThrown) {
            	        	console.log("Server failed to process your request, please try again later");
            	        },
            	        503: function(responseObject, textStatus, errorThrown) {
            	        	showError("Leave Commutation Workflow service unavailable");
            	        }           
            	    },
    				success: function(data, textStatus, jqXHR){
    					console.log(JSON.stringify(data));
    					// update data
    					showTraingDetailsModal(data);
    				},
    				error:function(jqXHR, textStatus, errorThrown){
    					console.log("Failed to retrieve Leave Commutation Workflow details");
    				}
    			});
    		}
    	}
    
    
    	// show details
    	function deleteTraningDetails(id){
    		if(id){			

    			var $apiUrl = "${pageContext.request.contextPath}/v1/leave-commutation-workflow-delete/"+id;
    			
                $.confirm({
                    title: 'Warning!',
                    content: 'Please confirm to delete Leave Commutation Workflow.',
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
                            	            showError("Duplicate Leave Commutation Workflow details not allowed.");
                            	        },
                            	        401: function(responseObject, textStatus, jqXHR) {
                            	        	showError("You are not authorized to peform this action.");
                            	        },
                            	        404: function(responseObject, textStatus, jqXHR) {
                            	        	showError("You are Leave Commutation Workflow Details not found");
                            	        },
                            	        412: function(responseObject, textStatus, jqXHR) {
                            	        	showError("Your request is not valid, please reveiew and submit again");
                            	        },
                            	        500: function(responseObject, textStatus, errorThrown) {
                            	        	console.log("Server failed to process your request, please try again later");
                            	        },
                            	        503: function(responseObject, textStatus, errorThrown) {
                            	        	showError("Leave Commutation Workflow service unavailable");
                            	        }           
                            	    },
                    				success: function(data, textStatus, jqXHR){
                    					console.log(JSON.stringify(data));
                                        $.confirm({
                                            title: 'Congrats!',
                                            content: 'Leave Commutation Workflow successfully deleted.',
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
                    					console.log("Failed to retrieve Leave Commutation Workflow details");
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