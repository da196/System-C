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
<title>HRMS | Loan Settings</title>
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
									href="#tab-loan-type">Loan Type</a></li>
								<li><a data-toggle="tab" href="#tab-loan-frequency">Payment
										Frequency</a></li>
								<li><a data-toggle="tab" href="#tab-loan-lenders">Loan
										Lender</a></li>
							</ul>
							<div class="tab-content">
								<!------------------------------------------ 
									Page data tables tabs starts
								---------------------------------------------->
								<div id="tab-loan-type" class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addLoanType">+Add
													loan type</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-loanTypes"
												id="dataTables-loanTypes">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty loanTypes}">
														<c:forEach var="loanType" items="${loanTypes}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${loanType.abbreviation}" /></td>
																<td><c:out value="${loanType.name}" /></td>
																<td><c:out value="${loanType.description}" /></td>
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
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
								<div id="tab-loan-frequency" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addLoanFrequency">+Add
													payment frequency</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-loanFrequency"
												id="dataTables-loanFrequency">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty loanFrequency}">
														<c:forEach var="frequency" items="${loanFrequency}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td width="40px"><c:out
																		value="${frequency.abbreviation}" /></td>
																<td><c:out value="${frequency.name}" /></td>
																<td><c:out value="${frequency.description}" /></td>
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
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
										</div>
									</div>
								</div>
								<div id="tab-loan-lenders" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addLoanLender">+Add
													lender</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-loanLender"
												id="dataTables-loanLender">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty loanLender}">
														<c:forEach var="lender" items="${loanLender}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${lender.abbreviation}" /></td>																
																<td><c:out value="${lender.name}" /></td>																
																<td><c:out value="${lender.description}" /></td>
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
														<th>Abbreviation</th>
														<th>Name</th>
														<th>Description</th>
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

	<!-- Page-Level Scripts -->
	<script>
		$(document).ready(function(){
			// loan type
			$('#dataTables-loanTypes').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Loan type'},
					{extend : 'pdf',title : 'Loan type'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});
			// loan frequency
			$('#dataTables-loanFrequency').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Payment frequency'},
					{extend : 'pdf',title : 'Payment frequency'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});
			// loan lenders
			$('#dataTables-loanLenders').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Loan lenders'},
					{extend : 'pdf',title : 'Loan lenders'},
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