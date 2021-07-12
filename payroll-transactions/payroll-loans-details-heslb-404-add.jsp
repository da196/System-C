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
			<h4 class="modal-title">HESLB</h4>
			<small class="font-bold">High Education Student Loan Board Details</small>
		</div>		
	<form:form
		action="${pageContext.request.contextPath}/v1/payroll-loan-heslb-add"
		role="form" method="post" modelAttribute="loanHeslb"
		id="formAddLoanHeslb404">
		
		<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<input type="hidden" id="404loanid" name="loanid" /> 
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="form-group row">
						<label for="404cseeindexnumber" class="col-md-4 col-form-label text-right">CSEE&nbsp;No.</label>
						<div class="col-md-8">
							<div class="input-group input-group-full-width">
								<input type="text" class="form-control form-control-sm"
								id="404cseeindexnumber" name="cseeindexnumber"
								placeholder="Enter Secondary Education Index Number" />
							</div>
						</div>
					</div>
				</div>
			</div>			
			<div class="row">
				<div class="col-md-12">
					<div class="form-group row">
						<label for="404firstname" class="col-md-4 col-form-label text-right">First&nbsp;Name</label>
						<div class="col-md-8">
							<div class="input-group input-group-full-width">
								<input type="text" class="form-control form-control-sm"
								id="404firstname" name="firstname"
								placeholder="Enter First Name" />
							</div>
						</div>
					</div>
				</div>
			</div>	
			<div class="row">
				<div class="col-md-12">
					<div class="form-group row">
						<label for="404middlename" class="col-md-4 col-form-label text-right">Middle&nbsp;Name</label>
						<div class="col-md-8">
							<div class="input-group input-group-full-width">
								<input type="text" class="form-control form-control-sm"
								id="404middlename" name="middlename"
								placeholder="Enter Middle Name" />
							</div>
						</div>
					</div>
				</div>
			</div>	
			<div class="row">
				<div class="col-md-12">
					<div class="form-group row">
						<label for="404lastname" class="col-md-4 col-form-label text-right">Last&nbsp;Name</label>
						<div class="col-md-8">
							<div class="input-group input-group-full-width">
								<input type="text" class="form-control form-control-sm"
								id="404lastname" name="lastname"
								placeholder="Enter Last Name" />
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
		<div class="modal-footer">
			<span class="text-left warning" id="footerErrorMessage404"></span>
			<button type="button" class="btn btn-danger btn-xs"
				data-dismiss="modal">Cancel</button>
			<button type="button" id="addLoanHeslbButton404"
				class="btn btn-primary btn-xs">Save</button>
		</div>
		
	</form:form>
	</div>
</div>
<script src="<c:url value="/resources/js/validation/ui_validation_loan_heslb_404_add.js" />"></script>