<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--------------------------------------------------------------------------
	Update Forms Section 
---------------------------------------------------------------------------->

<!--**************** Personal Information ********************-->
<div class="modal inmodal fade" id="editPersonalInfo" tabindex="-1"
	role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content animated bounceInRight">
			<form:form
				action="${pageContext.request.contextPath}/update-employee-information.htm" role="form" method="post" modelAttribute="updateEmployeeInformation"
				name="employeeInformation">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<i class="fa fa-user modal-icon"></i>
					<h4 class="modal-title">Update Personal Information</h4>
					<small class="font-bold">${details.firstName}
						${details.middleName} ${details.lastName}</small>
				</div>
				<div class="modal-body">
					<div class="panel-group" id="accordion">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h5 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseOne">1. Personal Information</a>
								</h5>
							</div>
							<div id="collapseOne" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="form-group col-lg-6">
										<label>Salutation</label>
										<div class="input-group">
											<select name='salutationId' style="width: 370px"
												data-placeholder="Choose Salutation" class="chosen-select update-salutation"
												id="updateSalutation">
												<option value="${details.salutationId}">${details.salutation}</option>
												<c:forEach var="salutation" items="${salutations}">
													<option id="salutationId" value="${salutation.id}">${salutation.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<input type="hidden" name="id" value="${details.id}" class="form-control required">
									<div class="form-group col-lg-6">
										<label>Firstname</label> <input type="text" name="firstName"
											value="${details.firstName}" class="form-control required">
									</div>
									<div class="form-group col-lg-6">
										<label>Middlename</label> <input type="text"
											name="middleName" value="${details.middleName}"
											class="form-control required">
									</div>
									<div class="form-group col-lg-6">
										<label>Lastname</label> <input type="text" name="lastName"
											value="${details.lastName}" class="form-control required">
									</div>
									<div class="form-group col-lg-6">
                                       <label>Date of birth</label>
                                       <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="updateDob" name="dob" value="${details.dob}" type="text" placeholder="Contract End Date" class="form-control">
	                               	   </div>
                                    </div>
									<div class="form-group col-lg-6">
                                       <label>Date of Employment</label>
                                       <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="updateDateOfEmployment" name="dateofemployment" value="${details.dateofemployment}" type="text" placeholder="Update Employment Date" class="form-control required">
	                               	   </div>
                                    </div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseTwo">2. More Details</a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="form-group col-lg-6">
										<label>Designation</label>
										<div class="input-group">
											<select name='designationId' id="updateDesignation"
												data-placeholder="Choose Designation"
												class="chosen-select update-designation" style="width: 370px">
												<option value="${details.designationId}">${details.designation}</option>
												<c:forEach var="designation" items="${designations}">
													<option id="designationId" value="${designation.id}">${designation.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Directorate/Unit</label>
										<div class="input-group">
											<select name='unitId' id="updateUnit"
												data-placeholder="Choose Directorate/Unit"
												class="chosen-select update-unit" style="width: 370px">
												<option value="${details.unitId}">${details.unit}</option>
												<c:forEach var="unit" items="${units}">
													<option id="unitId" value="${unit.id}">${unit.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Section</label>
										<div class="input-group">
											<select name='sectionid' data-placeholder="Choose Section"
												id="updateSection" class="chosen-select update-section" style="width: 370px">
												<option value="${details.sectionid}">${details.section}</option>
												<c:forEach var="section" items="${sections}">
													<option id="sectionid" value="${section.id}">${section.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Supervisor</label>
										<div class="input-group">
											<select name='supervisordesignationid' id="updateSupervisor" style="width: 370px"
												data-placeholder="Choose Supervisor" class="chosen-select update-supervisor"
												style="width: 505px;">
												<option value="${details.supervisordesignationid}">${details.supervisor}</option>
												<c:forEach var="supervisor" items="${supervisors}">
													<option id="supervisordesignationid"
														value="${supervisor.id}">${supervisor.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Employment Status</label>
										<div class="input-group">
											<select name='employmentstatusid' id="updateEmploymentStatus"
												data-placeholder="Choose Employment Status"
												class="chosen-select update-employment-status" style="width: 370px">
												<option value="${details.employmentstatusid}">${details.employmentstatus}</option>
												<c:forEach var="empStatus" items="${empStatuses}">
													<option id="employmentstatusid" value="${empStatus.id}">${empStatus.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
								        <label>Status Reason </label>
								        <select style="width: 370px" name="employmentstatusreasonid" class="form-control required edit-status-reasons-via-ajax"></select>									       
								   </div>
									<div class="form-group col-lg-6">
										<label>File Number</label> <input type="text"
											name="fileNumber" value="${details.fileNumber}"
											class="form-control required">
									</div>
									<div class="form-group col-lg-6">
										<label>Card Number</label> <input type="text"
											name="cardNumber" value="${details.cardNumber}"
											class="form-control required">
									</div>									
									<div class="form-group col-lg-6">
										<label>Office Email</label> <input type="email" name="email"
											value="${details.email}" class="form-control required">
									</div>
									<div class="form-group col-lg-6">
										<label>Contract Type</label>
										<div class="input-group">
											<select name='employmentcategoryId' id="updateContract"
												data-placeholder="Choose Employment Category"
												class="chosen-select update-contract" style="width: 370px">
												<option value="${details.employmentcategoryId}">${details.employmentcategory}</option>
												<c:forEach var="empCategory" items="${empCategories}">
													<option id="employmentcategoryId"
														value="${empCategory.id}">${empCategory.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6 contract-hideShow-status">
                                       <label>Contract End Date</label>
                                       <div class="input-group date">
	                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="updateEndDate" name="employmentenddate" type="text" placeholder="Contract End Date" class="form-control update-contract-end-date">
	                               	   </div>
                                    </div>									
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#collapseThree">3. Other Information</a>
								</h4>
							</div>
							<div id="collapseThree" class="panel-collapse collapse">
								<div class="panel-body">
									<div class="form-group col-lg-6">
										<label>Gender</label>
										<div class="input-group">
											<select name='genderid' id="updateGender" data-placeholder="Choose Gender"
												class="chosen-select update-gender" style="width: 370px">
												<option value="${details.genderid}">${details.gender}</option>
												<c:forEach var="gender" items="${genders}">
													<option id="genderid" value="${gender.id}">${gender.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Marital Status</label>
										<div class="input-group">
											<select name='maritalstatusId' id="updateMaritalStatus"
												data-placeholder="Choose Marital Status"
												class="chosen-select update-marital-status" style="width: 370px">
												<option value="${details.maritalstatusId}">${details.maritalstatus}</option>
												<c:forEach var="maritalStatus" items="${maritalStatuses}">
													<option id="maritalstatusId" value="${maritalStatus.id}">${maritalStatus.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>Nationality</label>
										<div class="input-group">
											<select name='nationalityId' id="updateNationality"
												data-placeholder="Choose Nationality"
												class="chosen-select update-nationality" style="width: 370px">
												<option value="${details.nationalityId}">${details.nationality}</option>
												<c:forEach var="nationality" items="${nationalities}">
													<option id="nationalityId" value="${nationality.id}">${nationality.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<input type="hidden" name="religionId" value="${details.religionId}"/>
									<input type="hidden" id="currentStatusReason" value="${details.employmentstatusreasonid}"/>
									<input type="hidden" id="currentStatusReasonName" value="${details.employmentstatusreason}"/>
									<div class="form-group col-lg-6">
										<label>Duty station</label>
										<div class="input-group">
											<select name='dutystationid' id="updateDutyStation"
												data-placeholder="Choose Duty station"
												class="chosen-select update-duty-station" style="width: 370px">
												<option value="${details.dutystationid}">${details.dutystation}</option>
												<c:forEach var="office" items="${offices}">
													<option id="dutystationid" value="${office.id}">${office.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group col-lg-6">
										<label>National Identification Number (NIN)</label> <input
											type="text" name="nationalId" value="${details.nationalId}"
											class="form-control">
									</div>
									<div class="form-group col-lg-6">
										<label>Passport Number</label> <input type="text"
											name="passportNo" value="${details.passportNo}"
											class="form-control">
									</div>
									<div class="form-group col-lg-6">
										<label>TIN Number</label> <input type="text"
											name="tin" value="${details.tin}"
											class="form-control">
									</div>
									<div class="form-group col-lg-6">																	    
									    <c:if test="${details.issupervisor == 0}">
									    	<label>Is Supervisor? *</label> <br>
											<label class="radio-inline">
										      <input type="radio" name="issupervisor" value="1">Yes
										    </label>
										    <label class="radio-inline">
										      <input type="radio" name="issupervisor" value="0" checked>No
										    </label>
									    </c:if>
									    <c:if test="${details.issupervisor == 1}">
									    	<label>Is Supervisor? *</label> <br>
											<label class="radio-inline">
										      <input type="radio" name="issupervisor" value="1" checked>Yes
										    </label>
										    <label class="radio-inline">
										      <input type="radio" name="issupervisor" value="0">No
										    </label>
									    </c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					<button type="submit" value="submit" class="btn btn-primary"
						id="submitInformation">Save changes</button>
				</div>
			</form:form>
		</div>
	</div>
</div>
