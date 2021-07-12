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
<title>HRMS | Tax Settings</title>
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
								<li class="active">
									<a data-toggle="tab" href="#tab-tax-type">Tax Type</a>
								</li>
								<li>
									<a data-toggle="tab" href="#tab-tax-structure">Tax Structure</a>
								</li>
							</ul>
							<div class="tab-content">
								<!------------------------------------------ 
									Page data tables tabs starts
								---------------------------------------------->
								<div id="tab-tax-type" class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#addTaxType">+Add tax type</button>
											</p>
										</div>
										<div class="table-responsive">
											<table class="table table-sm table-striped table-bordered table-hover dataTables-taxTypes" id="dataTables-taxTypes">
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
													<c:if test="${not empty taxTypes}">
														<c:forEach var="taxType" items="${taxTypes}"
															varStatus="status">	
															<tr>
																<td width="30px">${status.count}</td>
																<td width="40px"><c:out value="${taxType.abbreviation}" /></td>
																<td><c:out value="${taxType.name}" /></td>
																<td><c:out value="${taxType.description}" /></td>
																<td>
																	<a href="#"
																		class="btn btn-xs btn-info btn-circle btn-outline"
																		type="button" title="Edit"><i class="fa fa-edit"></i>
																	</a>
																	&nbsp;&nbsp;
																	<a href="#"
																		class="btn btn-xs btn-danger btn-circle btn-outline"
																		type="button" title="Delete"><i class="fa fa-remove"></i>
																	</a>
																</td>
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
								<div id="tab-tax-structure" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#addTaxStructure">+Add tax structure</button>
											</p>
										</div>
										<div class="table-responsive">
											<table class="table table-sm table-striped table-bordered table-hover dataTables-taxStructure" id="dataTables-taxStructure">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Type</th>
														<th>Currency</th>														
														<th>Minimum</th>
														<th>Maximum</th>
														<th>Fixed</th>														
														<th>Rate&nbsp;%</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty taxStructure}">
														<c:forEach var="taxStructure" items="${taxStructure}"
															varStatus="status">	
															<tr>
																<td width="20px">${status.count}</td>
																<td width="30px"><c:out value="${taxStructure.taxtype}" /></td>
																<td width="30px"><c:out value="${taxStructure.currency}" /></td>
																<td class="text-right">
																<c:set var="amountMin" value="${taxStructure.amountmin}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${amountMin}" /> 
																</td>
																<td class="text-right">
																<c:set var="amountMax" value="${taxStructure.amountmax}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${amountMax}" />
																</td>																
																<td class="text-right">
																<c:set var="amount" value="${taxStructure.amount}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${amount}" />
																</td>
																<td class="text-right">
																<c:set var="rate" value="${taxStructure.rate}" /> 
																<fmt:formatNumber type="percent" value="${rate}" />
																</td>
																<td>
																	<small><c:out value="${taxStructure.description}" /></small>
																</td>
																<td>
																	<a href="#"
																		class="btn btn-xs btn-info btn-circle btn-outline"
																		type="button" title="Edit"><i class="fa fa-edit"></i>
																	</a>
																	&nbsp;&nbsp;
																	<a href="#"
																		class="btn btn-xs btn-danger btn-circle btn-outline"
																		type="button" title="Delete"><i class="fa fa-remove"></i>
																	</a>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Type</th>
														<th>Currency</th>														
														<th>Minimum</th>
														<th>Maximum</th>
														<th>Fixed</th>														
														<th>Rate&nbsp;%</th>
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
		// tax type
		$('#dataTables-taxTypes').DataTable({
			dom : '<"html5buttons"B>lTfgitp',
			buttons : [
				{extend : 'copy'},
				{extend : 'csv'},
				{extend : 'excel',title : 'Tax type'},
				{extend : 'pdf',title : 'Tax type'},
				{extend : 'print',customize : function(win) {
					$(win.document.body).addClass('white-bg');
					$(win.document.body).css('font-size','10px');
					$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
				}
			}]
		});
		// tax structure
		$('#dataTables-taxStructure').DataTable({
			dom : '<"html5buttons"B>lTfgitp',
			buttons : [
				{extend : 'copy'},
				{extend : 'csv'},
				{extend : 'excel',title : 'Tax structure'},
				{extend : 'pdf',title : 'Tax structure'},
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