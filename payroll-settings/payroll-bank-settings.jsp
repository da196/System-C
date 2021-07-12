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
<title>HRMS | Bank Settings</title>
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
	.panel-body{
		min-height:380px;
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
								<li class="active"><a data-toggle="tab"
									href="#tab-payroll-bank">Banks</a></li>
								<li><a data-toggle="tab" href="#tab-payroll-bank-branches">Branches</a></li>
							</ul>
							<div class="tab-content">
								<!------------------------------------------ 
									Page data tables tabs starts
								---------------------------------------------->
								<div id="tab-payroll-bank" class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addBank">+Add
													bank</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-Banks"
												id="dataTables-Banks">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty banks}">
														<c:forEach var="bank" items="${banks}" varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${bank.name}" /></td>
																<td><c:out value="${bank.description}" /></td>
																<td><a href="#"
																	class="btn btn-xs btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i>
																</a> &nbsp;&nbsp; <a href="#"
																	class="btn btn-xs btn-danger btn-circle btn-outline"
																	type="button" title="Delete"><i
																		class="fa fa-remove"></i> </a></td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
								<div id="tab-payroll-bank-branches" class="tab-pane">
									<div class="panel-body">										
										<div class="row">
											<div class="col-md-12">
												<div class="form-group row">
													<label for="bankid"
														class="col-md-3 col-form-label text-right">Retrieve branches for</label>
													<div class="col-md-6">
														<select class="form-control form-control-sm"
															id="bankid" name="bankid">
															<c:if test="${not empty banks}">
																<option value="">Select Bank</option>
																<c:forEach var="bank" items="${banks}"
																	varStatus="status">
																	<option value="${bank.id}">
																		<c:out value="${bank.name}" /></option>
																</c:forEach>
															</c:if>
														</select>
													</div>
													<div class="col-md-3">
														<div class="ibox-tools">
															<p>
																<button type="button"
																	class="btn btn-outline btn-primary btn-xs"
																	data-toggle="modal" data-target="#addBankBranch">+Add
																	branch</button>
															</p>
														</div>
													</div>
												</div>
											</div>
										</div>
										<!-- banks list -->		
										<div  class="row">
											<div class="col-md-12">
												<div class="sk-spinner sk-spinner-fading-circle" id="progressBarBranches">
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
									            <div id="operationStatusMessage" class="alert alert-warning text-center"><span id="branchBankName">{BANK}</span> branches not found</div>
											</div>
										</div>								
										<div class="row" id="tableContainerBranches">
											<div class="col-md-12">																				
												<div class="table-responsive">
													<table
														class="table table-sm table-striped table-bordered table-hover dataTables-BankBranches"
														id="dataTables-BankBranches">
														<thead>
															<tr>
																<th>S/N</th>
																<th>Name</th>
																<th>Sort&nbsp;Code</th>
																<th>Physical&nbsp;Address</th>
																<th>Postal&nbsp;Code</th>
																<th>Telephone</th>
																<th>Action</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
														<tfoot>
															<tr>
																<th>S/N</th>
																<th>Name</th>
																<th>Sort&nbsp;Code</th>
																<th>Physical&nbsp;Address</th>
																<th>Postal&nbsp;Code</th>
																<th>Telephone</th>
																<th>Action</th>
															</tr>
														</tfoot>
													</table>
												</div>
											</div>
										</div>
										<!-- branches -->
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<!-- Page-Level Scripts -->
	<script>
		$(document).ready(function(){
			$('#tableContainerBranches').hide();
			$('#operationStatusMessage').hide();
			// loan type
			$('#dataTables-Banks').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Banks'},
					{extend : 'pdf',title : 'Banks'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});

			// apply datatable
			$('#dataTables-BankBranches').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Branches'},
					{extend : 'pdf',title : 'Branches'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});
		});
		//----------------------------------------------------------
		// utils
		// ----------------------------------------------------------
		// data retriever
		function updateBankBranch(bankId,bankName){
			$.ajax({
				type: 'GET',
				url: '${pageContext.request.contextPath }/bank-branch-by-bank.html/' + bankId,
				success: function(branches) {
					if($.trim(branches)){
						 var table = $('#dataTables-BankBranches');
						 if(table){
							 table.find('tbody').find("tr").remove(); //remove all previous rows if needed
						 }
						 $.each(branches, function (i, branch) {
						 	var tr = $("<tr></tr>");
						    table.append(tr);
						    // status
						    var td = $('<td width="30px">' + (i+1) + '</td>');
						    tr.append(td);
						    var td = $("<td>" + branch.name + "</td>");
						    tr.append(td);
						    var td = $("<td>" + branch.sortcode + "</td>");
						    tr.append(td);
						    var td = $("<td>" + branch.physicaladdress + "</td>");
						    tr.append(td);
						    var td = $("<td>" + branch.postaladdress + "</td>");
						    tr.append(td);
						    var td = $("<td>" + branch.telephone + "</td>");
						    tr.append(td);
						    // action
						    var td = $('<td>'
						    +'<a href="#" class="btn btn-xs btn-info" type="button" title="Edit"><i class="fa fa-edit"></i></a>' 
						    +'&nbsp;&nbsp;'
						    +'<a href="#" class="btn btn-xs btn-danger" type="button" title="Delete"><i class="fa fa-remove"></i></a>'
						    +'</td>');
						    tr.append(td);
						});
						// show table						
						$('#progressBarBranches').hide(1000);
						$('#operationStatusMessage').hide(1000);
						$('#tableContainerBranches').show(1000);
					}else{
						$('#tableContainerBranches').hide();
						$('#branchBankName').html(bankName)						
						$('#operationStatusMessage').show();
						/* $.alert({
			    	    	title: 'Not Found!',
			    	        content: 'No bank branches found',
			    		}); */
					}
					console.log("Branches="+JSON.stringify(branches));
				},
				error: function(jqXHR, textStatus, errorThrown) {
		    	    var errorMessage = jqXHR.responseText;
		    	    if (errorMessage.length > 0) {
		    	     	alert(errorMessage);
		    	    } 
		    	    $('#tableContainerBranches').hide();
					$('#branchBankName').html(bankName)						
					$('#operationStatusMessage').show();
		    	    /* $.alert({
		    	    	title: 'Error!',
		    	        content: 'Failed to retrieve bank branches',
		    		}); */
		        }
			}); 
		}
		//----------------------------------------------------------
		// add UI config
		// ----------------------------------------------------------
		$(function() {
			/*
			select2 input-group
			https://stackoverflow.com/questions/56707134/select2-with-bootstrap-theme-not-honoring-input-group-class
			 */
			// bank
			$('#bankid').select2({
				width : "100%"
			});			
			// event listener
			// bank
			var activeBankId = 0;
			$('#bankid').on("select2:select", function(e) {
				var selectedValue = e.params.data.id;
				var selectedText = e.params.data.text;
				if(selectedValue){
					if(activeBankId!=selectedValue){
						activeBankId = selectedValue;
				    	//alert("Bank id = "+selectedValue);
				    	updateBankBranch(selectedValue,selectedText);
				    }
				}
			});	
		});
	</script>
</body>
</html>