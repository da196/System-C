<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | Salary &amp; Allowances Settings</title>
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
<script
	src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
<!-- ajax -->

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
								<li class="active"><a data-toggle="tab" href="#tab-3">
										<i class="fa fa-map-marker"></i> Salary Scales
								</a></li>
								<li><a data-toggle="tab" href="#tab-4"> <i
										class="fa fa-map-marker"></i> Salary Scales Notches
								</a></li>
								<li><a data-toggle="tab" href="#tab-5"> <i
										class="fa fa-map-marker"></i> Salary Structures
								</a></li>
								<li><a data-toggle="tab" href="#tab-6"> <i
										class="fa fa-map-marker"></i> Allowances
								</a></li>
								<li><a data-toggle="tab" href="#tab-7"> <i
										class="fa fa-map-marker"></i> Allowances Types
								</a></li>
							</ul>
							<div class="tab-content">							
							<!------------------------------------------ 
									Page data tables tabs starts
							---------------------------------------------->
								
								<!-- 1. Salary Scales -->
								<div id="tab-3" class="tab-pane active">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newSalScale">+Add
													Scale</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-scales">
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
													<c:if test="${not empty salScales}">
														<c:forEach var="salScale" items="${salScales}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${salScale.abbreviation}" /></td>
																<td><c:out value="${salScale.name}" /></td>
																<td><c:out value="${salScale.description}" /></td>
																<td><a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a>
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
								
								<!-- 2. Salary Notches -->
								<div id="tab-4" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newNotch">+Add
													Notch</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-notches">
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
													<c:if test="${not empty notches}">
														<c:forEach var="notch" items="${notches}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${notch.abbreviation}" /></td>
																<td><c:out value="${notch.name}" /></td>
																<td><c:out value="${notch.description}" /></td>
																<td><a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a>
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
								
								<!-- 3. Salary Structures -->
								<div id="tab-5" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newSalStructure">+Add
													Structure</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-structures">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Currency</th>
														<th>Salary Scale</th>
														<th>Scale Notch</th>
														<th>Min. Salary</th>
														<th>Max. Salary</th>
														<th>Basic Salary</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty salStructures}">
														<c:forEach var="salStructure" items="${salStructures}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td width="30px">${salStructure.currency}</td>
																<td><c:out value="${salStructure.scalename}" /></td>
																<td><c:out value="${salStructure.notch}" /></td>
																<td>
																	<c:set var="basicsalaryMin" value="${salStructure.basicsalaryMin}" /> 
																	<fmt:formatNumber type="number" pattern="###,###,###.###" value="${basicsalaryMin}" />
																</td>
																<td>
																	<c:set var="basicsalaryMax" value="${salStructure.basicsalaryMax}" /> 
																	<fmt:formatNumber type="number" pattern="###,###,###.###" value="${basicsalaryMax}" />
																</td>
																<td>
																	<c:set var="basicSalary" value="${salStructure.basicSalary}" /> 
																	<fmt:formatNumber type="number" pattern="###,###,###.###" value="${basicSalary}" />
																</td>
																<td><c:out value="${salStructure.description}" /></td>
																<td><a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Currency</th>
														<th>Salary Scale</th>
														<th>Scale Notch</th>
														<th>Min. Salary</th>
														<th>Max. Salary</th>
														<th>Basic Salary</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
										</div>										
									</div>
								</div>
								
								<!-- 4. Allowances table -->
								<div id="tab-6" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newAllowance">+Add
													new allowance</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-allowances">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Allowance Type</th>
														<th>Amount</th>
														<th>Designation</th>
														<th>Salary Structure</th>
														<th>Employment Category</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty allowances}">
														<c:forEach var="allowance" items="${allowances}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${allowance.allowancetypeid}" /></td>
																<td><c:out value="${allowance.amount}" /></td>
																<td><c:out value="${allowance.designationid}" /></td>
																<td><c:out value="${allowance.salarystructureid}" /></td>
																<td><c:out value="${allowance.employmentcategoryid}" /></td>																
																<td><c:out value="${allowance.description}" /></td>
																<td><a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a>
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Allowance Type</th>
														<th>Amount</th>
														<th>Designation</th>
														<th>Salary Structure</th>
														<th>Employment Category</th>
														<th>Description</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
										</div>										
									</div>
								</div>
								
								<!-- 5. Allowances Types table -->
								<div id="tab-7" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newAllowanceType">+Add
													allowance Type</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-allowanceTypes">
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
													<c:if test="${not empty allowancetypes}">
														<c:forEach var="allowancetype" items="${allowancetypes}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${allowancetype.abbreviation}" /></td>
																<td><c:out value="${allowancetype.name}" /></td>
																<td><c:out value="${allowancetype.description}" /></td>
																<td><a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a>
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
								
								<!------------------------------------------ 
									./ Page data tables tabs ends
								---------------------------------------------->
							</div>
						</div>
					</div>
				</div>
				<!------------------------------------------ 
						Page modal forms starts
				---------------------------------------------->
				
					<!-- 1. New salary scale Modal -->
					<div class="modal inmodal" id="newSalScale" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add Salary Scale</h4>
									<small class="font-bold">Fill the form to add new
										scale.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/salScale-create.htm"
									role="form" method="post" modelAttribute="salScale"
									name="scaleCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="submit" value="submit"
											class="btn btn-primary" id="submitCountry">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New salary scale Modal -->
					
					<!-- 2. New notch Modal -->
					<div class="modal inmodal" id="newNotch" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add Salary Notch</h4>
									<small class="font-bold">Fill the form to add new
										notch.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/notch-create.htm"
									role="form" method="post" modelAttribute="notches"
									name="notchCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="submit" value="submit"
											class="btn btn-primary" id="submitCountry">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New notch Modal -->
					
					<!-- 3. New Salary structure Modal -->
					<div class="modal inmodal" id="newSalStructure" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add Salary Structure</h4>
									<small class="font-bold">Fill the form to add new
										structure.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/structure-create.htm"
									role="form" method="post" modelAttribute="salStructure"
									name="structureCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Salary Scale *</label>
											<div class="input-group">
												<select id="salaryScale" name='scaleId'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="scale" items="${salScales}">
														<option id="scaleId" value="${scale.id}">${scale.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Salary Notch *</label>
											<div class="input-group">
												<select id="salaryScale" name='notchId'
													data-placeholder="Select Notch..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="notch" items="${notches}">
														<option id="notchId" value="${notch.id}">${notch.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Basic Salary*</label> <input id="basicSalary"
												name="basicSalary" type="number"
												class="form-control required">
										</div>
										<div class="form-group">
											<label>Minimum Salary*</label> <input
												name="basicsalaryMin" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Maximum Salary*</label> <input
												name="basicsalaryMax" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="submit" value="submit"
											class="btn btn-primary" id="submitCountry">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New Salary structure Modal -->
					
					<!-- 4. New Allowance Modal -->
					<div class="modal inmodal" id="newAllowance" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add new allowance</h4>
									<small class="font-bold">Fill the form to add new
										allowance.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/create-allowance.htm"
									role="form" method="post" modelAttribute="salStructure"
									name="structureCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Allowance Type*</label> <input id="allowancetypeid"
												name="basicSalary" type="text"
												class="form-control required">
										</div>
										<div class="form-group">
											<label>Amount*</label> <input
												name="amount" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Salary Structure*</label> <input
												name="salarystructureid" type="text"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Designation*</label> <input
												name="designationid" type="text"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Employment Category*</label> <input
												name="employmentcategoryid" type="text"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="submit" value="submit"
											class="btn btn-primary" id="submitCountry">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New Allowance Modal -->
					
					<!-- 5. New allowance type modal -->
					<div class="modal inmodal" id="newAllowanceType" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add Allowance Type</h4>
									<small class="font-bold">Fill the form to add new
										allowance type.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/create-allowance-type.htm"
									role="form" method="post" modelAttribute="Allowancetype"
									name="scaleCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="submit" value="submit"
											class="btn btn-primary" id="submitCountry">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New allowance type modal -->
					
				<!--------------------------------------------
				 			./ Page modal forms ends
				 ------------------------------------------>
				 
				<!-- Page body ends -->
			</div>
			<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
		</div>
		<!-- /.page-wrapper -->
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
		$(document)
				.ready(
						function() {
							$('.dataTables-scales')
									.DataTable(
											{
												dom : '<"html5buttons"B>lTfgitp',
												buttons : [
														{
															extend : 'copy'
														},
														{
															extend : 'csv'
														},
														{
															extend : 'excel',
															title : 'Salary Scales'
														},
														{
															extend : 'pdf',
															title : 'Salary Scales'
														},

														{
															extend : 'print',
															customize : function(
																	win) {
																$(
																		win.document.body)
																		.addClass(
																				'white-bg');
																$(
																		win.document.body)
																		.css(
																				'font-size',
																				'10px');

																$(
																		win.document.body)
																		.find(
																				'table')
																		.addClass(
																				'compact')
																		.css(
																				'font-size',
																				'inherit');
															}
														} ]

											});

							$('.dataTables-notches')
									.DataTable(
											{
												dom : '<"html5buttons"B>lTfgitp',
												buttons : [
														{
															extend : 'copy'
														},
														{
															extend : 'csv'
														},
														{
															extend : 'excel',
															title : 'Salary Notches'
														},
														{
															extend : 'pdf',
															title : 'Salary Notches'
														},

														{
															extend : 'print',
															customize : function(
																	win) {
																$(
																		win.document.body)
																		.addClass(
																				'white-bg');
																$(
																		win.document.body)
																		.css(
																				'font-size',
																				'10px');

																$(
																		win.document.body)
																		.find(
																				'table')
																		.addClass(
																				'compact')
																		.css(
																				'font-size',
																				'inherit');
															}
														} ]

											});

							$('.dataTables-structures')
									.DataTable(
											{
												dom : '<"html5buttons"B>lTfgitp',
												buttons : [
														{
															extend : 'copy'
														},
														{
															extend : 'csv'
														},
														{
															extend : 'excel',
															title : 'Salary Structures'
														},
														{
															extend : 'pdf',
															title : 'Salary Structures'
														},

														{
															extend : 'print',
															customize : function(
																	win) {
																$(
																		win.document.body)
																		.addClass(
																				'white-bg');
																$(
																		win.document.body)
																		.css(
																				'font-size',
																				'10px');

																$(
																		win.document.body)
																		.find(
																				'table')
																		.addClass(
																				'compact')
																		.css(
																				'font-size',
																				'inherit');
															}
														} ]

											});

							/* Init DataTables */
							var oTable = $('#editable').DataTable();

							/* Apply the jEditable handlers to the table */
							oTable
									.$('td')
									.editable(
											'../example_ajax.php',
											{
												"callback" : function(sValue, y) {
													var aPos = oTable
															.fnGetPosition(this);
													oTable.fnUpdate(sValue,
															aPos[0], aPos[1]);
												},
												"submitdata" : function(value,
														settings) {
													return {
														"row_id" : this.parentNode
																.getAttribute('id'),
														"column" : oTable
																.fnGetPosition(this)[2]
													};
												},

												"width" : "90%",
												"height" : "100%"
											});

						});

		function fnClickAddRow() {
			$('#editable').dataTable()
					.fnAddData(
							[ "Custom row", "New row", "New row", "New row",
									"New row" ]);

		}
	</script>
</body>
</html>