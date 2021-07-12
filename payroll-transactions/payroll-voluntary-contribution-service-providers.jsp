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
<title>HRMS | Service Providers</title>
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
.text-bold {
	font-weight: bold;
}

.tex-upper {
	text-transform: uppercase;
}

.padding-left {
	padding-left: 2%;
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
								<h5>Service Providers</h5>
								<div class="ibox-tools">
									<button class="btn btn-primary btn-xs" title="Add service provider"
										data-toggle="modal"
										data-target="#addVoluntaryContributionServiceProviderModal"> <i
										class="fa fa-plus"></i> <span>Add</span>
									</button> &nbsp;&nbsp; <a class="collapse-link"> <i
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
												class="table table-sm table-striped table-bordered table-hover dataTables-Providers"
												id="dataTables-Providers">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Name</th>
														<th>Abbreviation</th>
														<th>Description</th>
														<th>Status</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty serviceProviders}">
														<c:forEach var="provider" items="${providers}" varStatus="status">
															<tr>
																<td width="30px">${status.count}</td>
																<td><c:out value="${provider.name}" /></td>
																<td><c:out value="${provider.abbreviation}" /></td>
																<td><c:out value="${provider.description}" /></td>
																<td><c:out value="${provider.active}" /></td>
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
														<th>Name</th>
														<th>Abbreviation</th>
														<th>Description</th>
														<th>Status</th>
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
	<div class="modal inmodal" id="addVoluntaryContributionServiceProviderModal"
		tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Service Provider</h4>
					<small class="font-bold">Add employee voluntary
						contribution service provider.</small>
				</div>
				<div class="modal-body">
					<form:form
						action="${pageContext.request.contextPath}/payroll-add-voluntary-contribution-service-provider.htm"
						role="form" method="post" modelAttribute="voluntaryContributionServiceProvider"
						name="formAddVoluntaryContributionServiceProvider"
						id="formAddVoluntaryContributionServiceProviderId">						
						<div class="row">
							<div class="col-md-12">
								<div class="form-group row">
									<label for="name" class="col-md-4 col-form-label text-right">Name</label>
									<div class="col-md-8">
										 <input type="text" class="form-control form-control-sm"
											id="name" name="name"
											placeholder="TCRA SACCOS LTD"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group row">
									<label for="abbreviation" class="col-md-4 col-form-label text-right">Abbreviation name</label>
									<div class="col-md-8">
										 <input type="text" class="form-control form-control-sm"
											id="abbreviation" name="abbreviation"
											placeholder="TCRA SACCOS"/>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12">
								<div class="form-group row">
									<label for="description" class="col-md-4 col-form-label text-right">Description</label>
									<div class="col-md-8">
										 <input type="text" class="form-control form-control-sm"
											id="description" name="description"
											placeholder="TCRA SAVINGS AND CREDIT CO-OPERATIVE SOCIETY LTD"/>
									</div>
								</div>
							</div>
						</div>
					</form:form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="btnSaveServiceProvider">Save changes</button>
				</div>
			</div>
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