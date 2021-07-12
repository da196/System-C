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
<title>HRMS | Payroll Journal</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
.input-group-date .input-group-addon.select-input {
	width: 82px;
	padding: 0;
}

.input-group-date .input-group-addon.select-input select {
	border: none;
	height: 32px;
}

.input-group-date .input-group-full-width {
	width: 100%;
}
/* .table-condensed thead tr:nth-child(2),
.table-condensed tbody {
  display: none
} */
/* .fa-stop-circle{
	color:#ff0000;
} */
.container-run-payroll-journal {
	margin-bottom: 16px;
}

.container-run-payroll-journal-result {
	margin-top: 16px;
}

.run-payroll-journal-result-value {
	font-weight: bold;
	padding-left: 6px;
}

.run-payroll-journal-result-label {
	padding-left: 24px;
}

.btn-group-run-payroll-journal {
	width: 100%;
}

.btn-generate-payroll {
	width: 50%;
}

.btn-run-payroll-journal {
	width: 50%;
}

@media only screen and (min-width: 768px) {
	.ibox-run-payroll-journal-content {
		min-height: 432px;
	}
}

.header {
	text-align: center;
}
thead{
	background-color:#ccc;
}
@media print {
	.visible-print {
		display: inherit !important;
	}
	.hidden-print {
		display: none !important;
	}
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight p-xl">
		<div class="ibox-content p-xl">
			<div class="row">
				<div class="col-md-12">
					<div class="row container-run-payroll-journal">
						<div class="col-md-12" id="printable-journal">
							<div class="header">
								<p class="centered-text">THE UNITED REPUBLIC OF TANZANIA</p>
								<h3 class="font-bold m-b-xs centered-text">TANZANIA
									COMMUNICATION REGULATORY AUTHORITY</h3>
								<p class="centered-text">ISO 9001:2015 CERTIFIED</p>
								<p class="centered-text">
									<img src="<c:url value="/resources/img/logo2.png" />"
										width="100px">
								</p>
							</div>
							<div class="body centered-text">
								<br />
								<p class="text-center">SALARY SLIP</p>
								<br /> <br />
								<div class=" row identity justify-content-evenly">
									<div class="col-md-5"></div>
									<div class="col-md-4">
										<p>
											<span>Employee Name</span> <label
												class="form-label text-right">Edward Nchimbi</label>
										</p>
										<p>
											<span>Designation</span> <label class="form-label text-right">Senior
												ICT Officer II</label>
										</p>
										<p>
											<span class="text-left">Month :</span>&nbsp; <label
												class="form-label text-right">December</label> <span
												class="text-left">Year :</span>&nbsp; <label
												class="form-label text-right">2020</label>
										</p>
									</div>
									<div class="col-md-3"></div>
								</div>
								<br /> <br />
								<div class="row">
									<div class="col-md-3"></div>
									<div class="col-md-6">
										<div class="table-responsive">
											<table class="table table-responsive">
												<thead>
													<tr>
														<th>Earnings</th>
														<th>&nbsp;</th>
														<th>Deductions</th>
														<th>&nbsp;</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Basic Salary</td>
														<td>6,000,000</td>
														<td>Health insurance</td>
														<td>300,000</td>
													</tr>
													<tr>
														<td>Housing allowance</td>
														<td>1,000,000</td>
														<td>Pension  fund</td>
														<td>200,000</td>
													</tr>
													<tr>
														<td>Transport allowance</td>
														<td>1,000,000</td>
														<td>Workers Compensation Fund</td>
														<td>100,000</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>Salary advance</td>
														<td>1,000,000</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>HESLB Loan</td>
														<td>600,000</td>
													</tr>
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>TCRA SACCOS</td>
														<td>300,000</td>
													</tr>
												</tbody>
												<tfoot>
													<tr>
														<th><small>Total Earnings</small></th>
														<th>8,000,000</th>
														<th>Total Deductions</th>
														<th>2,500,000</th>
													</tr>
													<tr>
														<th>&nbsp;</th>
														<th>&nbsp;</th>
														<th>NET Salary</th>
														<th>5,500,000</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
									<div class="col-md-3"></div>
								</div>
							</div>
						</div>
					</div>
					<!-- ./run payroll journal result -->
				</div>
			</div>
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
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
	<script>
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
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
	<script
		src="<c:url value="/resources/js/validation/ui_validation_payroll_run.js" />"></script>
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
				singleDatePicker : true,
				showDropdowns : true,
				locale : {
					format : 'DD-MM-YYYY'
				},
				maxDate : moment().format('DD-MM-YYYY'),// a day before today
				drops : 'below',
				autoApply : true
			}, function(start, end, label) {
				var years = moment().diff(start, 'years');
			});
			// handle datestart selected
			$('#payrollcycle').on('apply.daterangepicker',
					function(ev, picker) {
						//$('#payrollcycle]').data('daterangepicker').setStartDate(picker.startDate.format('DD-MM-YYYY'));
					});
		});
		// common
		// util
		function isInfinity(n) {
			return (n == Infinity || isNaN(n));
		}
	</script>
	<script>
		function showDatePicker(target) {
			target.click();
		}
	</script>
	<script>
		$(document)
				.ready(
						function() {
							$('#container-run-payroll-journal-result').hide();
							$('#spinnerRunPayrollJournal').hide();
							$('#btnViewPayroll').attr('disabled', true);
							// listener
							$('#btnViewPayroll')
									.click(
											function() {
												var date = $('#payrollcycle')
														.val();
												if (date) {
													var dateObj = ddMMyyyyToDate(date);
													var year = dateObj.year;
													var month = dateObj.month;
													window.location.href = '${pageContext.request.contextPath }/payroll-run-details.htm/'
															+ year
															+ '/'
															+ month;
												} else {
													showToast("Payroll date not valid")
												}
											});
						});
	</script>
	 <script type="text/javascript">
        window.print();
    </script>
</body>
</html>