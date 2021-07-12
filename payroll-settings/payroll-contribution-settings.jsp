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
<title>HRMS | Contribution Settings</title>
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
<style>
.text-left-margin{
	margin-left:50px;
}
.text-bold{
	font-weight:bold;
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
									href="#tab-contribution-type-mandatory">Mandatory Type</a></li>
								<li><a data-toggle="tab"
									href="#tab-contribution-type-voluntary">Voluntary Type</a></li>
								<li><a data-toggle="tab"
									href="#tab-contribution-mandatory">Mandatory Contribution</a></li>
								<li><a data-toggle="tab"
									href="#tab-contribution-voluntary">Voluntary Contribution</a></li>
							</ul>
							<div class="tab-content">
								<!------------------------------------------ 
									Page data tables tabs starts
								---------------------------------------------->
								<div id="tab-contribution-type-mandatory"
									class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addTypeMandatory">+Add
													mandatory type</button>
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
													<c:if test="${not empty mandatoryTypes}">
														<c:forEach var="mandatoryType" items="${mandatoryTypes}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${mandatoryType.name}" /></td>
																<td><c:out value="${mandatoryType.description}" /></td>
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
								<div id="tab-contribution-type-voluntary" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#addTypeVoluntary">+Add
													voluntary type</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-sm table-striped table-bordered table-hover dataTables-typesVoluntary"
												id="dataTables-typesVoluntary">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty voluntaryTypes}">
														<c:forEach var="voluntaryType" items="${voluntaryTypes}"
															varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${voluntaryType.name}" /></td>
																<td><c:out value="${voluntaryType.description}" /></td>
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
								<div id="tab-contribution-mandatory"
									class="tab-pane">
									<div class="panel-body" style="background:#f7f7f7;">
										<div class="row">
											<div class="col-md-6">
												<c:if test="${not empty contributionMandatoryPension}">
													<c:forEach var="pension"
														items="${contributionMandatoryPension}" varStatus="status">
														<div class="ibox">
															<div class="ibox-title">
																<span class="pull-right"><a href="#"
																	class="btn btn-xs btn-outline btn-primary"><i
																		class="fa fa-edit"></i> Update </a></span>
																<h5>
																	<c:out value="${pension.serviceprovider}" />
																	&nbsp;Contribution
																</h5>
															</div>
															<div class="ibox-content">
																<div>
																	<h4>Employer</h4>
																	<p class="text-left-margin">
																		<c:set var="rateemployer"
																			value="${pension.rateemployer}" />
																		<span class="text-bold"><fmt:formatNumber type="percent"
																			value="${rateemployer}" /></span>
																		&nbsp;of basic salary
																	</p>
																</div>
																<div>
																	<h4>Employee</h4>
																	<p class="text-left-margin">
																		<c:set var="rateemployee"
																			value="${pension.rateemployee}" />
																		<span class="text-bold"><fmt:formatNumber type="percent"
																			value="${rateemployee}" /></span>																		
																		&nbsp;of basic salary
																	</p>
																</div>
															</div>
														</div>
													</c:forEach>
												</c:if>
											</div>
											<div class="col-md-6">
												<c:if test="${not empty contributionMandatoryInsurance}">
													<c:forEach var="insurance"
														items="${contributionMandatoryInsurance}" varStatus="status">
														<div class="ibox">
															<div class="ibox-title">
																<span class="pull-right"><a href="#"
																	class="btn btn-xs btn-outline btn-primary"><i
																		class="fa fa-edit"></i> Update </a></span>
																<h5>
																	<c:out value="${insurance.serviceprovider}" />
																	&nbsp;Contribution
																</h5>
															</div>
															<div class="ibox-content">
																<div>
																	<h4>Employer</h4>
																	<p class="text-left-margin">
																		<c:set var="rateemployer"
																			value="${insurance.rateemployer}" />
																		<span class="text-bold">
																		<fmt:formatNumber type="percent"
																			value="${rateemployer}" /></span>
																		&nbsp;of basic salary
																	</p>
																</div>
																<div>
																	<h4>Employee</h4>
																	<p class="text-left-margin">
																		<c:set var="rateemployee"
																			value="${insurance.rateemployee}" />
																		<span class="text-bold"><fmt:formatNumber type="percent"
																			value="${rateemployee}" /></span>																		
																		&nbsp;of basic salary
																	</p>
																</div>
															</div>
														</div>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
								</div>
								<div id="tab-contribution-voluntary"
									class="tab-pane">
									<div class="panel-body">
										<small>Voluntary contribution settings is done directly to individual employee</small>
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