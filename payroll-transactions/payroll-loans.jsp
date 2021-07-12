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
<title>HRMS | Loans</title>
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
	                           <h5>Loans </h5>
	                           <div class="ibox-tools">	
								   <a class="btn btn-primary btn-xs"
								   	  href="<c:url value="/payroll-add-loan.htm" />">
	                                   <i class="fa fa-plus"></i>
	                                   <span>Add Loan</span>
	                               </a>
									&nbsp;&nbsp;
									<a class="collapse-link">
	                                   <i class="fa fa-chevron-up"></i>
	                               </a>
	                               <a class="close-link-x">
	                                   <i class="fa fa-times"></i>
	                               </a>
	                           </div>
	                       </div>
	                       <div class="ibox-content">
	                       		<div class="row hidden hide">
	                       			<div class="col-md-3">
	                       				<span>Filter by</span>
	                       			</div>
	                       			<div class="col-md-3">
	                       				<span>Department/Unit ...</span>
	                       			</div>
	                       			<div class="col-md-3">
	                       				<span>Date range ...</span>
	                       			</div>
	                       			<div class="col-md-3">
	                       				<span>Status ...</span>
	                       			</div>
	                       		</div>
	                       		<div class="row">
	                       			<div class="col-md-12">
	                       				<div class="table-responsive">
	                       					<table
												class="table table-sm table-striped table-bordered table-hover dataTables-loans"
												id="dataTables-loans">
												<thead>
													<tr>
														<th>S/N</th>													
														<th>Borrower</th>
														<th>Loan&nbsp;#</th>
														<th>Type</th>
														<th>Released</th>
														<th>Maturity</th>
														<th>Repayment</th>
														<th>Payment</th>
														<th>Interest</th>
														<th>Penalty</th>	
														<th>Due</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>	
														<th>Lender</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty loans}">
														<c:forEach var="loan" items="${loans}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td class="td-large-x"><c:out value="${loan.employeeFullname}" /></td>
																<td>
																<fmt:formatNumber pattern="00000000" value="${loan.id}" /> 
																</td>
																<td class="td-large">
																<c:out value="${loan.loantype}" />&nbsp;
																<c:if test="${loan.loantypeid==2}">
																	<button onClick="showDetailHESLB('${loan.id}','${loan.employeeFullname}','${loan.loantype}');"
																		class="btn btn-xs btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																</c:if>
																</td>
																<td>
																<c:out value="${loan.dateissued}" />
																</td>
																<td><c:out value="${loan.daterepaymentend}" /></td>
																<td><c:out value="${loan.loanfrequency}" /></td>
																<td>
																<c:set var="principal" value="${loan.amountprincipal}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${principal}" />
																</td>
																<td>
																<c:set var="rate" value="${loan.interestrate}" /> 
																<fmt:formatNumber type="percent" value="${rate}" />
																</td>
																<td>
																<c:set var="penalty" value="${loan.amountpenalty}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${penalty}" />
																</td>
																<td>
																<c:set var="debt" value="${loan.amountdebt}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${debt}" />
																</td>
																<td>
																<!-- <c:set var="paid" value="${loan.amountpaid}" />  -->
																<c:set var="paid" value="${loan.amountpaid}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${paid}" />
																</td>
																<td>
																<c:set var="balance" value="${loan.amountloanbalance}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${balance}" /></td>
																<td>
																<c:set var="status" value="${loan.status}" />
																<c:choose>
																	<c:when test="${status==0}">
																	<span>Pending</span>
																	</c:when>
																	<c:when test="${status==1}">
																	<span>Ongoing</span>
																	</c:when>
																	<c:when test="${status==2}">
																	<span>Paid</span>
																	</c:when>
																</c:choose>																
																</td>
																<td class="td-large"><c:out value="${loan.lender}" /></td>
																<td>
																	<a href="#"
																		class="btn btn-xs btn-info btn-circle-size-x"
																		type="button" title="Edit"><i class="fa fa-edit"></i>
																	</a>&nbsp;&nbsp;
																	<button onClick="deleteLoan(${loan.id})"
																		class="btn btn-xs btn-danger btn-circle-size-x"
																		type="button" title="Delete"><i class="fa fa-remove"></i>
																	</button>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>													
														<th>Borrower</th>
														<th>Loan&nbsp;#</th>
														<th>Type</th>
														<th>Released</th>
														<th>Maturity</th>
														<th>Repayment</th>
														<th>Payment</th>
														<th>Interest</th>
														<th>Penalty</th>	
														<th>Due</th>
														<th>Paid</th>
														<th>Balance</th>
														<th>Status</th>	
														<th>Lender</th>
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
	<!-- view and edit -->
	<div class="modal inmodal" id="heslbLoanDetailsModal"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="payroll-loans-details-heslb.jsp" />
		<!-- End Add Modal-->
	</div>
	<!-- add -->
	<div class="modal inmodal" id="addHeslbLoanDetailsModal404"
			tabindex="99999" role="dialog" aria-hidden="true">
		<!-- Add Modal -->
		<jsp:include page="payroll-loans-details-heslb-404-add.jsp" />
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
	//--------------------------------------------
	//- utils
	//--------------------------------------------
	function getContextPath() {
	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	function getBaseUrl() {
		   return window.location.origin;
	}
	function addLoanHeslbDetails(loanid){
		console.log("LoanID - "+loanid);
		$('#404loanid').val(loanid);
		console.log("LoanID after -"+$('#404loanid').val());
		// show modal
		$('#addHeslbLoanDetailsModal404').modal();
	}
	function showLoanHeslbDetails(data){
		$('#id').val(data.id);
		$('#loanid').val(data.loanid);
		$('#cseeindexnumber').val(data.cseeindexnumber);
		$('#firstname').val(data.firstname);
		$('#middlename').val(data.middlename);
		$('#lastname').val(data.lastname);
		// show modal
		$('#heslbLoanDetailsModal').modal();
	}
	// show details
	function showDetailHESLB(loanId,employeeName,loantype){
		if(loanId){			
			var baseUrl = getBaseUrl();
			var contextPath = getContextPath();
			//var $apiUrl = baseUrl.concat(contextPath).concat("/v1/payroll-loan-heslb-by-loan/").concat(loanId);
			var $apiUrl = "${pageContext.request.contextPath}/v1/payroll-loan-heslb-by-loan/"+loanId;
			//alert($apiUrl);
			// use ajax to retrieve 
			// 1. loan HESLB data by loan ID
			// 2. add DOM data to UI
			// 3. show modal
			$.ajax({
				url:$apiUrl,
				type:'GET',
				statusCode: {            	    	
        	    	208: function(responseObject, textStatus, jqXHR) {
        	            showError("Duplicate loan details not allowed.");
        	        },
        	        401: function(responseObject, textStatus, jqXHR) {
        	        	showError("You are not authorized to peform this action.");
        	        },
        	        404: function(responseObject, textStatus, jqXHR) {
        	        	$.confirm({
        	    		    title: '',
        	    		    content: employeeName+' - '+loantype+' loan details could not be found. Want to add now? Click OK to add, NO to exit.',
        	    		    buttons: {
        	    		        ok: function () {
        	        	        	// add loan details
        	    					addLoanHeslbDetails(loanId);
        	    		        },
        	    		        no: function () {
        	    		            //$.alert('Canceled!');
        	    		        }
        	    		    }
        	    		});
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
					showLoanHeslbDetails(data);
				},
				error:function(jqXHR, textStatus, errorThrown){
					console.log("Failed to retrieve HESLB loan details");
				}
			});
		}
	}
	function deleteLoan(id){
		console.log("Loan ID to delete = "+id);
		$.confirm({
		    title: 'Confirm!',
		    content: 'Are you sure you want to delete this loan? Click YES to confirm deletion, NO to cancel.',
		    buttons: {
		        confirm: function () {
		            $.alert('Deleting ...');
		        },
		        cancel: function () {
		            $.alert('Canceled!');
		        }
		    }
		});
	}
	</script>
	<script>
		$(document).ready(function(){
			// loan type
			$('#dataTables-loans').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Loans'},
					{extend : 'pdf',title : 'Loans'},
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