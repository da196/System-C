<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal-dialog modal-lg">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title">Insurance</h4>
			<small class="font-bold">Add employee health insurance</small>
		</div>		
		<form:form
			action="${pageContext.request.contextPath}/v1/payroll-employee-insurance-add"
			role="form" method="post" modelAttribute="insuranceContribution"
			id="formAddInsuranceContribution">
			
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group row">
							<label for="employeeid" class="col-md-4 col-form-label text-right">Employee</label>
							<div class="col-md-8">
								<div class="input-group input-group-full-width">
									<select class="form-control form-control-sm" id="employeeid"
										name="employeeid">
									</select>
									<div class="input-group-addon select-input" id="loaderEmployees">
										<div class="sk-spinner sk-spinner-fading-circle">
											<div class="sk-circle1 sk-circle"></div>
											<div class="sk-circle2 sk-circle"></div>
											<div class="sk-circle3 sk-circle"></div>
											<div class="sk-circle4 sk-circle"></div>
											<div class="sk-circle5 sk-circle"></div>
											<div class="sk-circle6 sk-circle"></div>
											<div class="sk-circle7 sk-circle"></div>
											<div class="sk-circle8 sk-circle"></div>
											<div class="sk-circle9 sk-circle"></div>
											<div class="sk-circle10 sk-circle"></div>
											<div class="sk-circle11 sk-circle"></div>
											<div class="sk-circle12 sk-circle"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group row">
							<label for="insurancetypeid"
								class="col-md-4 col-form-label text-right">Insurance Type</label>
							<div class="col-md-8">
								<div class="input-group input-group-full-width">
									<select class="form-control form-control-sm"
										id="insurancetypeid" name="insurancetypeid">
									</select>
									<div class="input-group-addon select-input"
										id="loaderInsuranceTypes">
										<div class="sk-spinner sk-spinner-fading-circle">
											<div class="sk-circle1 sk-circle"></div>
											<div class="sk-circle2 sk-circle"></div>
											<div class="sk-circle3 sk-circle"></div>
											<div class="sk-circle4 sk-circle"></div>
											<div class="sk-circle5 sk-circle"></div>
											<div class="sk-circle6 sk-circle"></div>
											<div class="sk-circle7 sk-circle"></div>
											<div class="sk-circle8 sk-circle"></div>
											<div class="sk-circle9 sk-circle"></div>
											<div class="sk-circle10 sk-circle"></div>
											<div class="sk-circle11 sk-circle"></div>
											<div class="sk-circle12 sk-circle"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group row">
							<label for="insuranceproviderid"
								class="col-md-4 col-form-label text-right">Insurance&nbsp;Company</label>
							<div class="col-md-8">
								<div class="input-group input-group-full-width">
									<select class="form-control form-control-sm"
										id="insuranceproviderid" name="insuranceproviderid">
									</select>
									<div class="input-group-addon select-input"
										id="loaderServiceProviders">
										<div class="sk-spinner sk-spinner-fading-circle">
											<div class="sk-circle1 sk-circle"></div>
											<div class="sk-circle2 sk-circle"></div>
											<div class="sk-circle3 sk-circle"></div>
											<div class="sk-circle4 sk-circle"></div>
											<div class="sk-circle5 sk-circle"></div>
											<div class="sk-circle6 sk-circle"></div>
											<div class="sk-circle7 sk-circle"></div>
											<div class="sk-circle8 sk-circle"></div>
											<div class="sk-circle9 sk-circle"></div>
											<div class="sk-circle10 sk-circle"></div>
											<div class="sk-circle11 sk-circle"></div>
											<div class="sk-circle12 sk-circle"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group row">
							<label for="insurancenumber" class="col-md-4 col-form-label text-right">Insurance&nbsp;Membership&nbsp;No.</label>
							<div class="col-md-8">
								<input type="text" class="form-control form-control-sm"
									id="insurancenumber" name="insurancenumber"
									placeholder="300307821" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="form-group row">
							<label for="amount" class="col-md-4 col-form-label text-right">Joining&nbsp;</label>
							<div class="col-md-8">
								<div class="input-group date">
									<span class="input-group-addon" onClick="showDatePicker()"><i class="fa fa-calendar"></i></span>
									<input type="text" class="form-control"
										id="joiningdate" name="datestart" placeholder="11/20/2021" />
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<span class="text-left warning" id="footerErrorMessage"></span>
				<button type="button" class="btn btn-danger btn-xs"
					onclick="hideAddInsuranceContributionModal();">Cancel</button>
				<button type="button" id="addInsuranceContributionButton"
					class="btn btn-primary btn-xs">Save</button>
			</div>
			
		</form:form>
	</div>
</div>
<script>
function showDatePicker(){
	$('#joiningdate').click();
}
</script>
<script
		src="<c:url value="/resources/js/validation/ui_validation_contribution_insurance_add.js" />"></script>