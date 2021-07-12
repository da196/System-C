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
<title>HRMS | Pension Fund</title>
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
 <link rel="stylesheet" href="https://unpkg.com/tachyons@4.10.0/css/tachyons.min.css">
 <link rel="stylesheet" href="<c:url value="/resources/dist/css/formValidation.min.css" />" >
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
.text-bold {
	font-weight: bold;
}

.tex-upper {
	text-transform: uppercase;
}

.padding-left {
	padding-left: 2%;
}

.td-width-large {
	min-width: 210px;
}

.td-width-small {
	min-width: 110px;
}

.input-group-addon.select-input {
	width: 82px;
	padding: 0;
}

.input-group-addon.select-input select {
	border: none;
	height: 32px;
}

.input-group-full-width {
	width: 100%;
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
					<div class="col-md-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title custom-ibox-title">
								<h5>Pension Fund</h5>
								<div class="ibox-tools">
									<button class="btn btn-primary btn-xs"
										title="Add an employee to pension contribution"
										data-toggle="modal"
										data-target="#addPensionContributionModal">
										<i class="fa fa-plus"></i> <span>Add</span>
									</button>&nbsp;&nbsp; <a class="collapse-link"> <i
										class="fa fa-chevron-up"></i>
									</a> <a class="close-link-x"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<div class="row">
									<div class="col-md-12">&nbsp;</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-Pension"
												id="dataTables-Pension">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Employee&nbsp;</th>
														<th>Pension&nbsp;Fund&nbsp;</th>
														<th>Membership&nbsp;No.</th>
														<th>Joining</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty pensions}">
														<c:forEach var="pension" items="${pensions}" varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${pension.fullName}" /></td>
																<td><c:out value="${pension.serviceprovider}" /></td>
																<td><c:out value="${pension.socialsecurityschemenumber}" /></td>
																<td>
																<fmt:parseDate value="${pension.datestart}" var="datestartParsed" pattern="yyyy-MM-dd" />
																<fmt:formatDate value="${datestartParsed}" type="date" pattern="dd-MM-yyyy"/>
																</td>
																<td><a href="#"
																	class="btn btn-xs btn-info"
																	type="button" title="Edit"><i class="fa fa-edit"></i>
																</a> &nbsp;&nbsp; <a href="#"
																	class="btn btn-xs btn-danger"
																	type="button" title="Delete"><i
																		class="fa fa-remove"></i> </a></td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Employee&nbsp;</th>
														<th>Pension&nbsp;Fund&nbsp;</th>
														<th>Membership&nbsp;No.</th>
														<th>Joining</th>
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
			</div>
			<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
		</div>
	</div>
	<!-- Modals -->
	<div class="modal inmodal" id="addPensionContributionModal"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="payroll-contribution-mandatory-pension-add.jsp" />
		<!-- End Add Modal-->
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/es6-shim/0.35.3/es6-shim.min.js"></script>
	<script
		src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
	<%-- <script
		src="<c:url value="/resources/dist/js/plugins/Tachyons.min.js" />"></script> --%>
	<!-- date and date picker -->
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
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
		// utils
		// voluntary contribution service providers
		var SPIN_TOGGLE_TRANSLATION_DURATION = 1000;
		function GetPensionContributionServiceProviders(){
			$.ajax({
				type: 'GET',
				url: '${pageContext.request.contextPath }/v1/payroll-pension-service-providers.json',
				success: function(providers) {				
					var options = '';
					for(var i = 0; i < providers.length; i++) {
						var name =  providers[i].name;
						options += '<option value="' + providers[i].id + '">' + name + '</option>';
					}
					// update options
					$('#serviceproviderid').html(options);
					$('#serviceproviderid').trigger('change'); 
					$('#loaderServiceProviders').hide(SPIN_TOGGLE_TRANSLATION_DURATION); // hide loader
					//console.log("DONE"+JSON.stringify(providers));
				},
	    	    error: function(jqXHR, textStatus, errorThrown) {
	    	        var errorMessage = jqXHR.responseText;
	    	        if (errorMessage.length > 0) {
	    	            alert(errorMessage);
	    	        } 
	    	        $.alert({
	    	            title: 'Error!',
	    	            content: 'Failed to retrieve pension service providers',
	    	        });
	    	    }
			}); 
		}
		// get employees
		function GetEmployees(){
			$.ajax({
				type: 'GET',
				url: '${pageContext.request.contextPath }/v1/employees.json',
				success: function(employees) {				
					var options = '';
					for(var i = 0; i < employees.length; i++) {
						var fullName =  employees[i].firstName + " "+employees[i].middleName+" "+employees[i].lastName;
						options += '<option value="' + employees[i].id + '">' + fullName + '</option>';
					}
					// update options
					$('#employeeid').html(options);
					$('#employeeid').trigger('change'); 
					$('#loaderEmployees').hide(SPIN_TOGGLE_TRANSLATION_DURATION);// hide loader
					//console.log("DONE"+JSON.stringify(employees));
				},
	    	    error: function(jqXHR, textStatus, errorThrown) {
	    	        var errorMessage = jqXHR.responseText;
	    	        if (errorMessage.length > 0) {
	    	            alert(errorMessage);
	    	        } 
	    	        $.alert({
	    	            title: 'Error!',
	    	            content: 'Failed to retrieve employees',
	    	        });
	    	    }
			}); 
		}		
	</script>
	<script>
		//----------------------------------------------------------
		// handle modal events
		// ----------------------------------------------------------
		$(function(){
			$('#addPensionContributionModal').on('show.bs.modal', function () {
				  //alert("Show");
			});
			$('#addPensionContributionModal').on('shown.bs.modal', function () {
				//alert("Shown")
				// retrieve employees
				GetEmployees();
				// Get providers
				GetPensionContributionServiceProviders();
			});
		});
	</script>
	<script>
		$(function(){
			// employee
			/* $('#employeeid').select2({
				dropdownParent: $('#addVoluntaryContributionModal'),
				width : "100%",
			}); */
			$('#joiningdate').daterangepicker({
				singleDatePicker: true,
			    showDropdowns: true,
			    locale:{
			    	format:'DD-MM-YYYY'
			    },
			    //minDate: moment().endOf('month') .format('DD-MM-YYYY'),// end of month
			    maxDate: moment().format('DD-MM-YYYY'),// no later than today
			    drops: 'up',
			    autoApply:true
			},
			function(start, end, label) {
				var years = moment().diff(start, 'years');
			});
		});
	</script>
	<script>
		$(document).ready(function(){
			$('#dataTables-Pension').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Employees Pension Fund'},
					{extend : 'pdf',title : 'Employees Pension Fund'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});
		});
	</script>
</body>
</html>