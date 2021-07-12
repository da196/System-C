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

			<h4 class="modal-title">+ Add Leave Workflow Step</h4>
		</div>
		<form:form
			action="${pageContext.request.contextPath}/v1/update-leave-workflow-step"
			role="form" method="post" modelAttribute="addTrainingWorkflowStep"
			id="formUpdateLeaveWorkflowStep">
			
			<input type="hidden" id="id" name="id" />
			
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label>Workflow Name</label> <select class="form-control"
								name="workflowid" id="workflowidEdit"></select>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label>Approval Name</label> <select class="form-control"
								name="approverdesignationid" id="approverdesignationidEdit">
								<option value="0"></option>
								</select>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6">
						<div class="form-group">
							<label>Approval Next</label> <select class="form-control"
								name="approverdesignationnextid" id="approverdesignationnextidEdit">
								<option value="0"></option>
								</select>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="form-group">
							<label>Approval Previous</label> <select class="form-control"
								name="approverdesignationprevid" id="approverdesignationprevidEdit">
								<option value="0"></option>
								</select>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label>Step Number</label>
					<input type="text" id="stepnumberEdit" name="stepnumber" class="form-control">
				</div>
				<div class="form-group">
					<label>Workflow Description</label>
					<textarea name="description" id="descriptionEdit"
						placeholder="Description" class="form-control"></textarea>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
				<button type="button" value="submit" class="btn btn-primary"
					id="editLeaveWorkflowStepBtn">Save changes</button>

			</div>
		</form:form>
	</div>
</div>

<!-- div -->
>
<!-- ./ New Training Modal -->