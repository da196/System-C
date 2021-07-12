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

<title>HRMS | Salary & Allowances Settings</title>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/dist/css/formValidation.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<link href="<c:url value="/resources/css/style.custom.css" />" rel="stylesheet">

<style>
.select2-close-mask {
	z-index: 2099;
}

.select2-dropdown {
	z-index: 3051;
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
								<li><a data-toggle="tab" href="#tab-8"> <i
										class="fa fa-map-marker"></i> Employee Salary Structures
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
																<td>
																    <button onClick="show('${salScale.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="del('${salScale.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>
																	
																	<!-- <a href="#"
																	class="btn btn-info btn-circle btn-outline"
																	type="button" title="Edit"><i class="fa fa-edit"></i></a> -->
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
																<td> 
																	<button onClick="showNotch('${notch.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="delNotch('${notch.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>
																	
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
																<td><c:out value="${salStructure.scalename}" /></td>
																<td><c:out value="${salStructure.notch}" /></td>
																
																<td class="no-overflow-sm"><c:set
																				var="amount" value="${salStructure.basicsalaryMin}" />
																			<fmt:formatNumber type="number"
																				pattern="###,###,###.###" value="${amount}" /></td>
																<td class="no-overflow-sm"><c:set
																				var="amount" value="${salStructure.basicsalaryMax}" />
																			<fmt:formatNumber type="number"
																				pattern="###,###,###.###" value="${amount}" /></td>
																<td class="no-overflow-sm"><c:set
																				var="amount" value="${salStructure.basicSalary}" />
																			<fmt:formatNumber type="number"
																				pattern="###,###,###.###" value="${amount}" /></td>
																<td><c:out value="${salStructure.description}" /></td>
																<td>
																	<button onClick="showStructure('${salStructure.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="delStructure('${salStructure.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>		
																</td>
															</tr>
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
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
												class="table table-striped table-bordered table-hover dataTables-structures">
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
																<td><c:out value="${allowance.allowancetypename}" /></td>
																<%-- <td><c:out value="${allowance.amount}" /></td> --%>
																<td class="no-overflow-sm"><c:set
																				var="amount" value="${allowance.amount}" />
																			<fmt:formatNumber type="number"
																				pattern="###,###,###.###" value="${amount}" />
																		</td>
																
																<td><c:out value="${allowance.designationname}" /></td>
																<td><c:out value="${allowance.salarystructurename}" /></td>
																<td><c:out value="${allowance.employmentcategoryname}" /></td>																
																<td><c:out value="${allowance.description}" /></td>
																<td>
																	<button onClick="showAllowance('${allowance.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="delAllowance('${allowance.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>		
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
																<td>
																	<button onClick="showAllowanceType('${allowancetype.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="delAllowanceType('${allowancetype.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>		
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
								
								<!-- 6. Employees Salary Structures -->
								<div id="tab-8" class="tab-pane">
									<div class="panel-body">
										<div class="ibox-tools">
											<p>
												<button type="button"
													class="btn btn-outline btn-primary btn-xs"
													data-toggle="modal" data-target="#newEmpSalaryStructure">+Add
													Employee Salary Structure</button>
											</p>
										</div>
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered table-hover dataTables-empSalStructures">
												<thead>
													<tr>
														<th>S/N</th>
														<th>Employee</th>
														<th>Salary Structure</th>
														<th>Currency</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:if test="${not empty structuresDetails}">
														<c:forEach var="structures" items="${structuresDetails}"
															varStatus="status">
															<tr class="gradeX">
																<td width="30px">${status.count}</td>
																<td><c:out value="${structures.employeefullname}" /></td>
																<td><c:out value="${structures.salaryScale}" /></td>
																<td><c:out value="${structures.currency}" /></td>
																<td>
																	<button onClick="showEmpSalaryStructure('${structures.id}');"
																		 class="btn btn-info btn-circle btn-outline"
																		type="button" title="View details"><i class="fa fa-eye"></i>
																	</button>
																	
																	<button onClick="delEmpSalaryStructure('${structures.id}');"
																		class="btn btn-danger btn-circle btn-outline" title="Delete"
																		type="button"><i class="fa fa-trash-o"></i>
																	</button>		
																</td>
															</tr>
															<!-- Employee Salary Structure edit -->
															
														</c:forEach>
													</c:if>
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>
														<th>Employee</th>
														<th>Salary Structure</th>
														<th>Currency</th>
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
					
					
					<div class="modal inmodal" id="editSalScale" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit Salary Scale</h4>
									<small class="font-bold">Fill the form to edit
										scale.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/update-salary-scale"
									role="form" method="post" modelAttribute="salaryScale"
									name="formSalaryScale" id="formSalaryScale" enctype="multipart/form-data">

									<input type="hidden" name="id" id="id">
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation" id="abbreviation"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name" id="name"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description" id="description"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="salaryScaleBtn">Save
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
					
					<div class="modal inmodal" id="editNotch" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit Salary Notch</h4>
									<small class="font-bold">Fill the form to edit
										notch.</small>
								</div>
									
									<form:form
									action="${pageContext.request.contextPath}/v1/update-notch-scale"
									role="form" method="post" modelAttribute="generalSettings"
									name="formSalaryScaleUpdate" id="formSalaryScaleUpdate" enctype="multipart/form-data">
									
									
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation" id="abbreviationEdit"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name" id="nameEdit"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description" id="descriptionEdit"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="updateNotchBtn">Save
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
												<select id="scaleId" name='scaleId'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="scale" items="${salScales}">
														<option value="${scale.id}">${scale.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Salary Notch *</label>
											<div class="input-group">
												<select id="notchId" name='notchId'
													data-placeholder="Select Notch..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="notch" items="${notches}">
														<option value="${notch.id}">${notch.name}</option>
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
					
					<div class="modal inmodal" id="editSalStructure" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit Salary Structure</h4>
									<small class="font-bold">Fill the form to edit salary
										structure.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/update-salary-structure"
									role="form" method="post" modelAttribute="salStructure"
									name="formSalaryStructureUpdate" id="formSalaryStructureUpdate" 
									enctype="multipart/form-data">
									<input type="hidden" id="id" name="id">
									<div class="modal-body">
										<div class="form-group">
											<label>Salary Scale *</label>
											<div class="input-group">
												<select id="scaleIdEdit" name='scaleId'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="scale" items="${salScales}">
														<option value="${scale.id}">${scale.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Salary Notch *</label>
											<div class="input-group">
												<select id="notchIdEdit" name='notchId'
													data-placeholder="Select Notch..."
													class="chosen-select" style="width: 540px;">
													<option value="">Select</option>
													<c:forEach var="notch" items="${notches}">
														<option value="${notch.id}">${notch.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Basic Salary*</label> <input id="basicSalaryEdit"
												name="basicSalary" type="number"
												class="form-control required">
										</div>
										<div class="form-group">
											<label>Minimum Salary*</label> <input
												name="basicsalaryMin" id="basicsalaryMinEdit" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Maximum Salary*</label> <input
												name="basicsalaryMax" id="basicsalaryMaxEdit" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description" id="descriptionEdit"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="updateSalaryStructureBtn">Save
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
									role="form" method="post" modelAttribute="Allowance"
									name="structureCreate">
									<div class="modal-body">
										<div class="form-group">
											<label>Allowance Type*</label> 
											<div class="input-group">
												<select id="allowancetypeid" name='allowancetypeid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="allowancetypes" items="${allowancetypes}">
														<option value="${allowancetypes.id}">${allowancetypes.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Amount*</label> <input
												name="amount" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Salary Structure*</label> 
											<div class="input-group">
												<select id="salarystructureid" name='salarystructureid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="structuresDetails" items="${structuresDetails}">
														<option value="${structuresDetails.salarystructureId}">${structuresDetails.salaryScale}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Designation*</label> 
											<div class="input-group">
												<select id="designationid" name='designationid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="designation" items="${designation}">
														<option value="${designation.id}">${designation.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Employment Category*</label> 
												<div class="input-group">
												<select id="employmentcategoryid" name='employmentcategoryid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employmentCategory" items="${employmentCategory}">
														<option value="${employmentCategory.id}">${employmentCategory.name}</option>
													</c:forEach>
												</select>
											</div>
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
					
					
					<div class="modal inmodal" id="editAllowance" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit new allowance</h4>
									<small class="font-bold">Fill the form to edit
										allowance.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/allowance-update"
									role="form" method="post" modelAttribute="Allowance"
									name="formAUpdateAllowance" id="formAUpdateAllowance">
									<input type="hidden" id="id" name="id">
									<div class="modal-body">
										<div class="form-group">
											<label>Allowance Type*</label> 
											<div class="input-group">
												<select id="allowancetypeidAllowanceEdit" name='allowancetypeid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="allowancetypes" items="${allowancetypes}">
														<option value="${allowancetypes.id}">${allowancetypes.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Amount*</label> <input id="amountAllowanceEdit"
												name="amount" type="number"
												class="form-control required" />
										</div>
										<div class="form-group">
											<label>Salary Structure*</label> 
											<div class="input-group">
												<select id="salarystructureidAllowanceEdit" name='salarystructureid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="structuresDetails" items="${structuresDetails}">
														<option value="${structuresDetails.salarystructureId}">${structuresDetails.salaryScale}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Designation*</label> 
											<div class="input-group">
												<select id="designationidAllowanceEdit" name='designationid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="designation" items="${designation}">
														<option value="${designation.id}">${designation.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Employment Category*</label> 
												<div class="input-group">
												<select id="employmentcategoryidAllowanceEdit" name='employmentcategoryid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employmentCategory" items="${employmentCategory}">
														<option value="${employmentCategory.id}">${employmentCategory.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description" id="descriptionAllowanceEdit"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="updateAllowanceBtn">Save
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
					
					<div class="modal inmodal" id="editlAllowaceType" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit Allowance Type</h4>
									<small class="font-bold">Fill the form to update
										allowance type.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/allowance-type-update"
									role="form" method="post" modelAttribute="Allowancetype"
									name="formAUpdateAllowanceType" id="formAUpdateAllowanceType">
									<div class="modal-body">
										<div class="form-group">
											<label>Abbreviation</label> <input name="abbreviation" id="abbreviationAllowanceTypeEdit"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Name</label> <input name="name" id="nameAllowanceTypeEdit"
												class="form-control" />
										</div>
										<div class="form-group">
											<label>Description</label> <input name="description" id="descriptionAllowanceTypeEdit"
												type="text" class="form-control" />
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="allowanceTypeBtn">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ New allowance type modal -->				
					
					<!-- 6. New Employee Salary Structure modal -->
					<div class="modal inmodal" id="newEmpSalaryStructure" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Add Employee Salary Structure</h4>
									<small class="font-bold">Fill the form to add new
										Employee Salary Structure.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/add-employee-salary-structure"
									role="form" method="post" modelAttribute="salaryStructure"
									name="formAddEmpSalaryStructure" id="formAddEmpSalaryStructure">
									<div class="modal-body">
										<div class="form-group">
											<label>Employee</label> 
											<div class="input-group">
												<select id="employeeid" name='employeeid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employees" items="${employees}">
														<option value="${employees.id}"> ${employees.firstName} ${employees.middleName} ${employees.lastName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Salary Scale</label>
											<div class="input-group">
												<select id="empsalarystructureId" name='salarystructureId'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employeesSalStructures" items="${employeesSalStructures}">
														<option value="${employeesSalStructures.id}">${employeesSalStructures.scalename}-${employeesSalStructures.notch}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Currency</label> 
											<div class="input-group">
												<select id="currencyId" name='currencyId'
													class="chosen-select" style="width: 540px;">
													<c:forEach var="currencies" items="${currencies}">
														<option value="${currencies.id}">${currencies.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="addEmployeeSalaryBtn">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					
					<div class="modal inmodal" id="editEmpSalaryStructure" tabindex="-1"
						role="dialog" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span
											class="sr-only">Close</span>
									</button>
									<h4 class="modal-title">Edit Employee Salary Structure</h4>
									<small class="font-bold">Fill the form to update
										employee salary structure.</small>
								</div>
								<form:form
									action="${pageContext.request.contextPath}/v1/update-employee-salary-structure"
									role="form" method="post" modelAttribute="salaryStructure"
									name="formUpdateEmpSalaryStructure" id="formUpdateEmpSalaryStructure">
									<input type="hidden" id="id" name="id">
									<input type="hidden" id="assignedbyId" name="assignedbyId">
									<div class="modal-body">
										<div class="form-group">
											<label>Employee</label> 
											<div class="input-group">
												<select id="employeeidEdit" name='employeeid'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employees" items="${employees}">
														<option value="${employees.id}"> ${employees.firstName} ${employees.middleName} ${employees.lastName}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Salary Scale</label>
											<div class="input-group">
												<select id="salarystructureIdEdit" name='salarystructureId'
													data-placeholder="Select Scale..."
													class="chosen-select" style="width: 540px;">													
													<c:forEach var="employeesSalStructures" items="${employeesSalStructures}">
														<option value="${employeesSalStructures.id}">${employeesSalStructures.scalename}-${employeesSalStructures.notch}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label>Currency</label> 
											<div class="input-group">
												<select id="currencyIdEdit" name='currencyId'
													class="chosen-select" style="width: 540px;">
													<c:forEach var="currencies" items="${currencies}">
														<option value="${currencies.id}">${currencies.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white"
											data-dismiss="modal">Close</button>
										<button type="button" value="submit"
											class="btn btn-primary" id="updateEmployeeSalaryBtn">Save
											changes</button>
									</div>
								</form:form>
							</div>
						</div>
					</div>
					<!-- ./ Employee Salary Structure modal -->				
					
					
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
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<script src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>

		<script
			src="<c:url value="/resources/dist/js/plugins/Bootstrap.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/plugins/StartEndDate.min.js" />"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
		<!-- date and date picker -->
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
			<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>

		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_salary_scale.js" />"></script>
		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_salary_notch.js" />"></script>
		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_salary_structure.js" />"></script>
		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_allowance_type.js" />"></script>
		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_employee_salary.js" />"></script>
		<script	src="<c:url value="/resources/js/validation/ui_validation_hr_base_allowance.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	// Employee Salary 
	$('#employeeid').select2({
		dropdownParent: $("#newEmpSalaryStructure")
	});
	$('#empsalarystructureId').select2({
		dropdownParent: $("#newEmpSalaryStructure")
	});
	$('#currencyId').select2({
		dropdownParent: $("#newEmpSalaryStructure")
	});
	
	$('#employeeidEdit').select2({
		dropdownParent: $("#editEmpSalaryStructure")
	});
	$('#salarystructureIdEdit').select2({
		dropdownParent: $("#editEmpSalaryStructure")
	});
	$('#currencyIdEdit').select2({
		dropdownParent: $("#editEmpSalaryStructure")
	});
	
	// Salary Structure
	$('#scaleId').select2({
		dropdownParent: $("#newSalStructure")
	});
	$('#notchId').select2({
		dropdownParent: $("#newSalStructure")
	});
	$('#scaleIdEdit').select2({
		dropdownParent: $("#editSalStructure")
	});	
	$('#notchIdEdit').select2({
		dropdownParent: $("#editSalStructure")
	});
	
	// Allowance
	$('#allowancetypeid').select2({
		dropdownParent: $("#newAllowance")
	});
	$('#salarystructureId').select2({
		dropdownParent: $("#newAllowance")
	});
	$('#designationid').select2({
		dropdownParent: $("#newAllowance")
	});	
	$('#employmentcategoryid').select2({
		dropdownParent: $("#newAllowance")
	});
	
	$('#allowancetypeidAllowanceEdit').select2({
		dropdownParent: $("#editAllowance")
	});
	$('#salarystructureidAllowanceEdit').select2({
		dropdownParent: $("#editAllowance")
	});
	$('#employmentcategoryidAllowanceEdit').select2({
		dropdownParent: $("#editAllowance")
	});	
	$('#designationidAllowanceEdit').select2({
		dropdownParent: $("#editAllowance")
	});
	
}); 

function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}

</script>
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
				
		$('.dataTables-empSalStructures')
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