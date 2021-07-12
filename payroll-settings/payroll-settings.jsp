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
<title>HRMS | Payroll Settings</title>
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
									href="#tab-payroll-type">Payroll Type</a></li>
								<li><a data-toggle="tab" href="#tab-payroll-workflow">Payroll
										Workflow</a></li>
							</ul>
							<div class="tab-content">
								<!------------------------------------------ 
									Page data tables tabs starts
								---------------------------------------------->
								<div id="tab-payroll-type" class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addTypeMandatory">+Add
													payroll type</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-typesMandatory"
												id="dataTables-typesMandatory">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty payrollTypes}">
														<c:forEach var="payrollType" items="${payrollTypes}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${payrollType.name}" /></td>
																<td><c:out value="${payrollType.description}" /></td>
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
								<div id="tab-payroll-workflow" class="tab-pane">
									<div class="panel-body">
										<small>Payroll workflow e.g. approve payroll, approve
											deduction, approve contribution, approve loan, etc</small>
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
</body>
</html>