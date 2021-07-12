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
									
									<h4 class="modal-title">+ Edit Leave Recall Workflow</h4>
								</div>
								<form:form action="${pageContext.request.contextPath}/v1/leave-recall-workflow-update" role="form" method="post"
									modelAttribute="editTrainingWorkflow" id="formEditLeaveRecallWorkflow" >
									
									
									<input type="hidden" id="id" name="id">
									<div class="modal-body">
										<div class="row">
											<div class="col-sm-6">
												<div class="form-group">
													<label>Workflow Name</label> 
													<input type="text" id="nameEdit" name="name" class="form-control" placeholder="Workflow Name">
												</div>
											</div>
											<div class="col-sm-6">
												<div class="form-group">
													<label>Workflow Code</label> <input type="text" name="code" id="codeEdit" placeholder="Workflow Code"
														class="form-control">
												</div>
											</div>
										</div>
										<div class="form-group">
											<label>Supervisor Designation</label>
											 <select class="form-control input-square" name="supervisordesignationid" id="supervisordesignationidEdit"></select>
										</div>
										<div class="form-group">
											<label>Workflow Description</label>
											<textarea name="description" id="descriptionEdit" placeholder="Workflow Description" class="form-control"></textarea>
										</div>
	
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
										<button type="button" value="submit" class="btn btn-primary" id="editLeaveRecallWorkflowBtn">Save
											changes</button>
								
									</div>
								</form:form>
							</div>
						</div>
					</<!-- div -->>
					<!-- ./ New Training Modal -->