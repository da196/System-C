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
<title>HRMS | Add Loan</title>
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
								<h5>Add Loan</h5>
								<div class="ibox-tools">
									<a class="btn btn-primary btn-xs"
										href="<c:url value="/payroll-loans.htm" />"> <i
										class="fa fa-plus"></i> <span>View Loans</span>
									</a> &nbsp;&nbsp; <a class="collapse-link"> <i
										class="fa fa-chevron-up"></i>
									</a> <a class="close-link-x"> <i class="fa fa-times"></i>
									</a>
								</div>
							</div>
							<div class="ibox-content">
								<form:form id="formAddLoan"
									action="${pageContext.request.contextPath}/payroll-add-loan.htm"
									modelAttribute="loan" method="POST"
									enctype="multipart/form-data">
									<div class="row">
										<div class="col-md-6">
											<div class="form-group row">
												<label for="employeeid"
													class="col-md-4 col-form-label text-right">Borrower</label>
												<div class="col-md-8">
													<select class="form-control form-control-sm"
														id="employeeid" name="employeeid">
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
										<div class="col-md-6">
											<div class="form-group row">
												<label for="lenderid"
													class="col-md-4 col-form-label text-right">Lender</label>
												<div class="col-md-8">
													<select class="form-control form-control-sm" id="lenderid"
														name="lenderid">
														<option value="">Select Lender</option>
														<c:if test="${not empty loanLender}">
															<c:forEach var="lender" items="${loanLender}"
																varStatus="status">
																<option value="${lender.id}">
																	<c:out value="${lender.name}" /></option>
															</c:forEach>
														</c:if>
													</select>
												</div>
											</div>
										</div>
									</div>
									<!-- ./END ROW -->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group row">
												<label for="loantypeid"
													class="col-md-4 col-form-label text-right">Loan
													type</label>
												<div class="col-md-8">
													<select class="form-control form-control-sm"
														id="loantypeid" name="loantypeid">
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group row">
												<label for="amountdebt"
													class="col-md-4 col-form-label text-right">Loan
													amount</label>
												<div class="col-md-8">
													<div class="input-group input-group-full-width">
														<div class="input-group-addon select-input">
															<select class="selectpicker form-control" name="currencyid" id="currencyid">
																<option value="4">TZS</option>
																<option value="10">USD</option>
															</select>
														</div>
														<input type="text" class="form-control form-control-sm"
															id="amountdebt" name="amountdebt" placeholder="24000000" />
													</div>
												</div>
											</div>
										</div>
									</div>
									<!-- ./END ROW -->
									<div class="row">										
										<div class="col-md-6">
											<div class="form-group row">												
												<label for="repaymentmodeid"
													class="col-md-4 col-form-label text-right">Repayment
													cycle</label>
												<div class="col-md-8">
													<select class="form-control form-control-sm"
														id="loanfrequencyid" name="loanfrequencyid">
														<c:if test="${not empty loanFrequency}">
															<c:forEach var="frequency" items="${loanFrequency}"
																varStatus="status">
																<option value="${frequency.id}">
																	<c:out value="${frequency.name}" /></option>
															</c:forEach>
														</c:if>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group row">
												<label for="amountprincipal"
													class="col-md-4 col-form-label text-right">
													Monthly payment</label>
												<div class="col-md-8">
													<div class="input-group">
														<span class="input-group-addon currency-label" id="currency-principal">TZS</span> <input
															type="text" class="form-control form-control-sm"
															id="amountprincipal" name="amountprincipal"
															placeholder="2000000" disabled />
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="row">										
										<div class="col-md-6">
											<div class="form-group row">
												<label for="repaymentmodeid"
													class="col-md-4 col-form-label text-right">Repayment
													mode</label>
												<div class="col-md-8">
													<select class="selectpicker form-control" name="repaymentmodeid" id="repaymentmodeid">
														<option value="1">Period</option>
														<option value="2">Installment</option>
													</select>													
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group row">												
												<label for="duration"
													class="col-md-4 col-form-label text-right">Loan
													period</label>
												<div class="col-md-8">
													<div class="input-group">
														<input type="text" class="form-control form-control-sm text-right"
															id="duration" name="duration" placeholder="12" />
														<span class="input-group-addon">Month</span>
													</div>
												</div>
											</div>
										</div>
									</div>									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group row">
												<label for="dateissued"
													class="col-md-4 col-form-label text-right">Loan
													release date</label>
												<div class="col-md-8">
													<div class="input-group date">
														<span class="input-group-addon" onClick="showDatePicker(dateissued)"><i class="fa fa-calendar"></i></span>
														<input type="text"
															class="form-control text-right" id="dateissued"
															name="dateissued" placeholder="11/20/2021" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group row">
												<label for="daterepaymentstart"
													class="col-md-4 col-form-label text-right">Repayment
													start date</label>
												<div class="col-md-8">
													<div class="input-group date">
														<span class="input-group-addon" onClick="showDatePicker(daterepaymentstart)"><i class="fa fa-calendar"></i></span>
														<input type="text"
															class="form-control" id="daterepaymentstart"
															name="daterepaymentstart" placeholder="11/20/2020" />														
													</div>
												</div>
											</div>
										</div>										
									</div>
									<div class="row">
										<div class="col-md-6">
											<div class="form-group row">
												<label for="status"
													class="col-md-4 col-form-label text-right">Loan
													status</label>
												<div class="col-md-8">
													<select class="form-control form-control-sm" id="status"
														name="status" disabled>
														<option value="0">Pending</option>
														<option value="1">Open</option>
														<option value="2">Paid</option>
														<option value="3">Closed</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group row">												
												<label for="approved"
													class="col-md-4 col-form-label text-right">Approve
													loan now?</label>
												<div class="col-md-8">
													<label class="radio-inline"> <input type="radio"
														name="approved" value="0" checked>No
													</label> <label class="radio-inline"> <input type="radio"
														name="approved" value="1">Yes
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-12">
											<div class="form-group row">
												<label for="description"
													class="col-md-2 col-form-label text-right">Loan
													description</label>
												<div class="col-md-10">
													<textarea class="form-control form-control-sm"
														id="description" name="description"
														placeholder="Salary advance"></textarea>
												</div>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-4 col-md-offset-2">
											<button type="submit" id="btnAddLoan"
												class="btn btn-primary btn-sm">Submit Loan</button>
										</div>
									</div>
								</form:form>
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
	<div class="modal inmodal" id="addHeslbLoanDetailsModal"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="payroll-loans-details-heslb-add.jsp" />
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
	<script>
	$.fn.serializeObject = function() {
		// enable disabled
		var inputs = this.find(':disabled');
        inputs.prop('disabled', false);        
	    var o = {};
	    var a = this.serializeArray();
	 	// re-enable disabled
	 	inputs.prop('disabled', true);
	 	
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
			// lender
			$('#lenderid').select2({
				width : "100%"
			});
			// loantypeid
			$('#loantypeid').select2({
				width : "100%"
			});
			// loanfrequencyid
			$('#loanfrequencyid').select2({
				width : "100%"
			});
		});
		// common
		// util
		function isInfinity(n){
			return (n==Infinity || isNaN(n));
		}
		// update loan type
		function updateLoanType(lenderId){
			$.ajax({
				type: 'GET',
				url: '${pageContext.request.contextPath}/loan-type-by-lender.html/' + lenderId,
				success: function(loanTypes) {				
					var options = '';
					for(var i = 0; i < loanTypes.length; i++) {
						options += '<option value="' + loanTypes[i].id + '">' + loanTypes[i].name + '</option>';
					}
					// update options
					$('#loantypeid').html(options);
					$('#loantypeid').trigger('change'); 
					
					//console.log("DONE"+JSON.stringify(loanTypes));
				},
	    	    error: function(jqXHR, textStatus, errorThrown) {
	    	        var errorMessage = jqXHR.responseText;
	    	        if (errorMessage.length > 0) {
	    	            alert(errorMessage);
	    	        } 
	    	        $.alert({
	    	            title: 'Error!',
	    	            content: 'Failed to retrieve loan type',
	    	        });
	    	    }
			}); 
		}
		// toggle principal amount UI
		function updateUIPrincipal(disable){
			var disabled = disable>0?true:false;
			$('#amountprincipal').attr("disabled", disabled);
		}
		// toggle duration UI
		function updateUIPeriod(disable){
			var disabled = disable>0?true:false;
			$('#duration').attr("disabled", disabled);
		}
		// calculate load period
		function updateLoanPeriod(payment){
			var monthlyPayment = payment; 
			var debt = $('#amountdebt').val();
			var duration = debt/monthlyPayment;
			duration = isInfinity(duration)?0:duration;
			//console.log("Duration="+duration+",Payment="+monthlyPayment+",Debt="+debt);
			$('#duration').val(duration);
		}
		function calculateLoanPeriod(debt){
			var monthlyPayment = $('#amountprincipal').val();
			var duration = debt/monthlyPayment;
			duration = isInfinity(duration)?0:duration;
			//console.log("Duration="+duration+",Payment="+monthlyPayment+",Debt="+debt);
			$('#duration').val(duration);			
		}
		// calculate loan payment
		function updateLoanPayment(duration){
			var debt = $('#amountdebt').val();
			var monthlyPayment = debt/duration;
			monthlyPayment = isInfinity(monthlyPayment)?0:monthlyPayment;
			//console.log("Duration="+duration+",Payment="+monthlyPayment+",Debt="+debt);	
			$('#amountprincipal').val(monthlyPayment);
		}
		function calculateLoanPayment(debt){
			var duration = $('#duration').val();
			var monthlyPayment = debt/duration;
			monthlyPayment = isInfinity(monthlyPayment)?0:monthlyPayment;
			//console.log("Duration="+duration+",Payment="+monthlyPayment+",Debt="+debt);	
			$('#amountprincipal').val(monthlyPayment);
		}
		
		// event listener
		$(function(){
			// lender
			var activeVendorId = 0;
			$('#lenderid').on("select2:select", function(e) {
				var selectedValue = e.params.data.id;
				var selectedText = e.params.data.text;
				if(selectedValue){
					if(activeVendorId!=selectedValue){
				    	activeVendorId = selectedValue;
				    	//alert("Loand type id = "+selectedValue);
				    	updateLoanType(selectedValue);
				    }
				}
			});	
			// currency
			$( "#currencyid" ).change(function () {
			    $("#currencyid option:selected" ).each(function() {
				    var selectedText = $( this ).text();
				    var selectedValue = $( this ).val();
			    	$( "#currency-principal" ).text(selectedText);
			    });			   
			});
			// repayment mode
			$("#repaymentmodeid" ).change(function () {
				var selectedText = $(this).text();
			    var selectedValue = $(this).val();
			    if(selectedValue==1){
			    	 updateUIPrincipal(1); // disable
			    	 updateUIPeriod(0); // enable
			    }else{
			    	updateUIPrincipal(0); // enable
			    	updateUIPeriod(1); // disable
			    }
			});
			// amount debt change
			$("#amountdebt").on("input", function(){
				var debt = $(this).val();
				// calculateLoan
				//console.log("Debt = "+debt);
				$("#repaymentmodeid option:selected").each(function() {
					var selectedValue = $(this ).val();
					if(selectedValue==1){	
						 calculateLoanPayment(debt);
					}else{
						calculateLoanPeriod(debt);
					}
				});				   
			});
			// monthly payment change
			$("#amountprincipal").on("input", function(){
				var monthlyPayment = $(this).val();
				// calculateLoan
				//console.log("Payment when monthly pay changed = "+monthlyPayment);
				$("#repaymentmodeid option:selected").each(function() {
					var selectedValue = $(this).val();
					if(selectedValue==2){	
						updateLoanPeriod(monthlyPayment);
					}
				});				   
			});
			// monthly payment change
			$("#duration").on("input", function(){
				var duration = $(this).val();
				// calculateLoan
				//console.log("Payment when period changed = "+duration);
				$("#repaymentmodeid option:selected").each(function() {
					var selectedValue = $(this).val();
					if(selectedValue==1){	
						updateLoanPayment(duration);
					}
				});				   
			});
			
		});
		// date picker
		$(function(){
			$('#dateissued').daterangepicker({
				 singleDatePicker: true,
				 showDropdowns: true,
				 locale:{
				 	format:'DD-MM-YYYY'
				 },
				 maxDate: moment().subtract(1, 'd').format('DD-MM-YYYY'),// a day before today
				 	drops: 'up',
				    autoApply:true
				 }, 
				 function(start, end, label) {
				    var years = moment().diff(start, 'years');		                                            		    
				});
				 // handle datestart selected
			$('#dateissued').on('apply.daterangepicker', function(ev, picker) {
				alert(picker.startDate.format('DD-MM-YYYY'));
				$('#daterepaymentstart').data('daterangepicker').minDate = picker.startDate;
				$('#daterepaymentstart').data('daterangepicker').startDate = picker.startDate;
				$('#daterepaymentstart').val(picker.startDate.format('DD-MM-YYYY'));
			});
		});
		// date repayment start
		$(function() {
			$('#daterepaymentstart').daterangepicker({
				singleDatePicker: true,
			    showDropdowns: true,
			    locale:{
			    	format:'DD-MM-YYYY'
			    },
			    //minDate:moment($('input[name="datestart"]').val(), "DD-MM-YYYY"),
			    minDate: moment().add(1, 'd').format('DD-MM-YYYY'),// tomorrow
			    drops: 'up',
			    autoApply:true	
			},
			function(start, end, label) {
				var years = moment().diff(start, 'years');
			});
		  	// handle datestart selected
			$('#daterepaymentstart').on('apply.daterangepicker', function(ev, picker) {
				//$('#dateissued').data('daterangepicker').setEndDate(picker.startDate.format('DD-MM-YYYY'));
				$('#dateissued').data('daterangepicker').maxDate = picker.startDate;
				// shold reset start date?
				var dateStart = moment($('#dateissued').val(), "DD-MM-YYYY");
				var dateEnd = moment($('#daterepaymentstart').val(), "DD-MM-YYYY");
				if(dateEnd<dateStart){
				  // reset
				  $('#dateissued').val("");
				}
			});
		  	
			$('#dateissued').on('apply.daterangepicker', function(ev, picker) {
				//$('#dateissued').data('daterangepicker').setEndDate(picker.startDate.format('DD-MM-YYYY'));
				$('#daterepaymentstart').data('daterangepicker').minDate = picker.startDate; // 15+
			});
		  	
		});
	</script>
	<script>
	function showDatePicker(target){
		target.click();
	}
	</script>
	<script src="<c:url value="/resources/js/validation/ui_validation_loan_add.js" />"></script>
</body>
</html>