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
<title>HRMS | Send Payslip</title>
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
<style>
.input-group-date .input-group-addon.select-input {
	width:82px;
	padding: 0;
}

.input-group-date .input-group-addon.select-input select {
	border: none;
	height: 32px;
}
.input-group-date .input-group-full-width{
	width:100%;
}
/* .table-condensed thead tr:nth-child(2),
.table-condensed tbody {
  display: none
} */
/* .fa-stop-circle{
	color:#ff0000;
} */
.container-run-payroll{
	margin-bottom:16px;
}
.container-run-payroll-result{
	margin-top:16px;
}
.run-payroll-result-value{
	font-weight:bold;
	padding-left:6px;
}
.run-payroll-result-label{
	padding-left:24px;
}
.btn-group-run-payroll{
	width:100%;
}
.btn-generate-payroll{
	width:50%;
}
.btn-run-payroll{
	width:50%;
}

@media only screen and (min-width: 768px){	
	.ibox-run-payroll-content{
		min-height:432px;
	}
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
						<div class="ibox float-e-margins ibox-run-payroll">
							<div class="ibox-title custom-ibox-title">
								<h5>Send Payslip</h5>
								<div class="ibox-tools">
									<button class="btn btn-primary btn-xs hide hidden"> <i
										class="fa fa-stop-circle"></i> <span>Exit send payslip</span>
									</button> &nbsp;&nbsp; <a class="collapse-link"> <i
										class="fa fa-chevron-up"></i>
									</a> <a class="close-link-x"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content ibox-run-payroll-content">
								<div class="row container-run-payroll">
									<div class="col-md-9">
										<form:form id="formSendPayslip"
										action="${pageContext.request.contextPath}/v1/payroll-send-payslip"
										modelAttribute="payslipSend" method="POST"
										enctype="multipart/form-data">
											<div class="row">
												<div class="col-md-12">
													<div class="form-group row">
														<label for="payrollcycle"
															class="col-md-5 col-form-label text-right">Payroll date</label>
														<div class="col-md-7">
															<div class="input-group date input-group-date">
																<span class="input-group-addon" onClick="showDatePicker(payrollcycle)"><i class="fa fa-calendar"></i></span>
																<input type="text"
																	class="form-control text-right" id="payrollcycle"
																	name="payrollcycle" placeholder="11/20/2021" />
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group row">
														<label for="unitid"
															class="col-md-5 col-form-label text-right">Directorate/Unit/
															Section</label>
														<div class="col-md-7">
															<select class="form-control form-control-sm" id="unitid"
																name="unitid">
																<option value="">All</option>
																<c:if test="${not empty units}">
																	<c:forEach var="unit" items="${units}"
																		varStatus="status">
																		<option value="${unit.id}">
																			<c:out
																				value="${unit.name}" /></option>
																	</c:forEach>
																</c:if>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<div class="form-group row">
														<label for="employeeid"
															class="col-md-5 col-form-label text-right">Employee(s)</label>
														<div class="col-md-7">
															<select class="form-control form-control-sm" id="employeeid"
																name="employeeid[]" multiple="multiple">
																<option value="" disabled>All</option>
																<c:if test="${not empty employees}">
																	<c:forEach var="employee" items="${employees}"
																		varStatus="status">
																		<option value="${employee.id}">
																			<c:out
																				value="${employee.firstName} ${employee.middleName} ${employee.lastName}" /></option>
																	</c:forEach>
																</c:if>
															</select>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-7 col-md-offset-5">
													<div class="btn-group btn-group-run-payroll" role="group">
													  <button type="button" class="btn btn-primary btn-sm btn-send-payslip" id="btnSendPayslip">Send payslip</button>
													</div>
													<div class="sk-spinner sk-spinner-circle" id="spinnerSendPayslip">
					                                    <div class="sk-circle1 sk-circle"></div>
					                                    <div class="sk-circle2 sk-circle"></div>
					                                    <div class="sk-circle3 sk-circle"></div>
					                                    <div class="sk-circle4 sk-circle"></div>
					                                    <div class="sk-circle5 sk-circle"></div>
					                                    <div class="sk-circle6 sk-circle"></div>
					                                    <div class="sk-circle7 sk-circle"></div>
					                                    <div class="sk-circle8 sk-circle"></div>
					                                    <div class="sk-circle9 sk-circle"></div>
					                                    <div class="sk-circle10 sk-circle"></div>
					                                    <div class="sk-circle11 sk-circle"></div>
					                                    <div class="sk-circle12 sk-circle"></div>
					                                </div>
												</div>
											</div>
										</form:form>
									</div>
									<div class="col-md-3"><span class="hide hidden">Progress</span></div>
								</div>	
								<!-- ./run payroll -->	
								<br/><br/><br/><br/><br/>											
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
	<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
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
	<script src="<c:url value="/resources/js/validation/ui_validation_payslip_send.js" />"></script>	
	<script src="<c:url value="/resources/js/utils/DateUtils.js" />"></script>
	<script>
		//----------------------------------------------------------
		// add UI config
		// ----------------------------------------------------------
		$(function() {
			/*
			select2 input-group
			https://stackoverflow.com/questions/56707134/select2-with-bootstrap-theme-not-honoring-input-group-class
			 */
			// employee
			$('#employeeid').select2({
				width : "100%"
			});
			// unit
			$('#unitid').select2({
				width : "100%"
			});
			
			$('#payrollcycle').daterangepicker({
				 singleDatePicker: true,
				 showDropdowns: true,
				 locale:{
				 	format:'DD-MM-YYYY'
				 },
				 maxDate: moment().format('DD-MM-YYYY'),// a day before today
				 	drops: 'below',
				    autoApply:true
				 },
				 function(start, end, label) {
					var years = moment().diff(start, 'years');		                                            		    
			});
			// handle datestart selected
			$('#payrollcycle').on('apply.daterangepicker', function(ev, picker) {
				//$('#payrollcycle]').data('daterangepicker').setStartDate(picker.startDate.format('DD-MM-YYYY'));
			});
		});
		// common
		// util
		function isInfinity(n){
			return (n==Infinity || isNaN(n));
		}
	</script>
	<script>
	function showDatePicker(target){
		target.click();
	}
	</script>
	<script>
	$(document).ready(function(){
		$('#container-run-payroll-result').hide();
		$('#spinnerSendPayslip').hide();
	});
	</script>
</body>
</html>