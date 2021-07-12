<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


					<!-- Training Modal -->
					<!-- <div class="modal inmodal" id="Training" tabindex="-1" role="dialog" aria-hidden="true"> -->
						<div class="modal-dialog">
							<div class="modal-content animated bounceInRight">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">
										<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
									</button>
									
									
									
									<h4 class="modal-title">+ Edit Training</h4>
								</div>
								<form:form action="${pageContext.request.contextPath}/v1/training-update" role="form" method="post"
									modelAttribute="updateTraining" id="formUpdateTraining" enctype="multipart/form-data">
									<input type="hidden" id="id" name="id" />
									<div class="modal-body">
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Employee</label> <select class="form-control" name="employeeid" id="employeeidEdit"></select>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Course Name</label> <input type="text" name="name" id="name" placeholder="Course Name"
														class="form-control">
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Description</label>
											<textarea name="description" id="description" placeholder="Description" class="form-control"></textarea>
										</div>
										<!-- <div class="form-group">
																			<label>Institution</label> <input type="text" name="institution"
																				id="institutionEdit" placeholder="Institution" class="form-control">
																		</div> -->
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Institution</label> <input type="text" name="institution" id="institutionEdit"
														placeholder="Institution" class="form-control">
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Institution Address</label>
													<textarea name="institutionaddress" id="institutionaddressEdit" placeholder="Institution Address"
														class="form-control"></textarea>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Currency</label>
													<select class="form-control" name="currencyid"
																			id="currencyidEdit"></select>
													<%-- <select id="currencyIdEdit" name=currencyId class="form-control">
														<c:forEach var="currency" items="${currencies}">
															<option id="currencyIdEdit" value="${currency.id}">${currency.name}</option>
														</c:forEach>
													</select> --%>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Cost/Budget</label>
								
													<input type="text" name="trainingcost" id="trainingcostEdit" placeholder="Cost/Budget"
														class="form-control">
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Expected Start Date</label> <input type="date" name="dateexpectedstart"
														id="dateexpectedstartEdit" class="form-control">
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Expected End Date</label> <input type="date" name="date_expected_end"
														id="date_expected_end_edit" class="form-control"></input>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Financial Year</label> <select class="form-control" name="financialyearid"
														id="financialyearidEdit"></select>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Category</label> <select class="form-control" name="trainingcategoryid"
														id="trainingcategoryidEdit"></select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Type</label> <select class="form-control" name="trainingtypeid"
														id="trainingtypeidEdit"></select>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Initiator</label> <select class="form-control" name="traininginitiatorid"
														id="traininginitiatoridEdit"></select>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Sponsor</label><select class="form-control" name="trainingsponsorid"
														id="trainingsponsoridEdit"></select>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Attach File</label>
													<input type="file" class="form-control" id="feestructureattachment" name="feestructureattachment">
												</div>
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
										<button type="button" value="submit" class="btn btn-primary" id="updateTrainingBtn">Save
											changes</button>
								
									</div>
								</form:form>
							</div>
						</div>
					</<!-- div -->>
					<!-- ./ New Training Modal -->