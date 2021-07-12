<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 <!-- New Employee Modal Form -->
<div class="modal inmodal fade" id="newEmployee" tabindex="-1" role="dialog"  aria-hidden="true">
          <div class="modal-dialog modal-lg">
              <div class="modal-content">
                  <div class="modal-header">
                      <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                      <h4 class="modal-title">New Employee Registration</h4>
                      <small class="font-bold">Fields with (*) are mandatory.</small>
                  </div>
                  <div class="modal-body" id="content">
                      <!-- Form Contents -->
                     <div class="row">
               <div class="col-lg-12">
                   <div class="ibox">
                       <div class="ibox-content">
                           <form:form action="${pageContext.request.contextPath}/employee-create.htm" role="form" method="post"  modelAttribute="employee" name="employeeCreate" id="form" class="wizard-big">
                               <h1>Basic Information</h1>
                               <fieldset>
                                   <div class="row">
                                        <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>First name *</label>
                                               <input id="firstName" name="firstName" type="text" class="form-control required">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Middle name *</label>
                                               <input id="middleName" name="middleName" type="text" class="form-control required">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Last name *</label>
                                               <input id="lastName" name="lastName" type="text" class="form-control required">
                                           </div>
                                       </div>
                                       <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>File Number *</label>
                                               <input id="fileNumber" name="fileNumber" type="text" class="form-control required">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Card Number *</label>
                                               <input id="cardNumber" name="cardNumber" type="text" class="form-control required">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Office Email *</label>
                                               <input id="email" name="email" type="email" class="form-control required">
                                           </div>
                                       </div>
                                       <!-- Fields Row -->													
                                       <div class="col-lg-4">
									        <label>Salutation *</label>
									        <select style="width:100%" id="selectSalution" name="salutationId"  class="form-control required salutations-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-4">
									        <label>Nationality *</label>
									        <select style="width:100%" id="selectNationality" name="genderid"  class="form-control required nationalities-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4">
									        <label>Gender *</label>
									        <select style="width:100%" id="selectGender" name="nationalityId"  class="form-control required genders-via-ajax"></select>									       
									   </div> 
                                       <div class="col-lg-4">
									        <label>Religion *</label>
									        <select style="width:100%" id="selectReligion" name="religionId"  class="form-control required fixed-religion-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4">
									        <label>Marital Status *</label>
									        <select style="width:100%" id="selectMaritaStatus" name="maritalstatusId"  class="form-control required marital-statuses-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Date Of Birth *</label>
                                               <div class="input-group date">
			                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="dob" name="dob" type="date" max="${toDate}" class="form-control">
			                                </div>
                                           </div>
                                       </div>
                                       <!-- Fields Row -->			                                        
                                   </div>
                               </fieldset>
                               <h1>More Details</h1>
                               <fieldset>
                                   <div class="row">
                                       <!-- Fields Row -->
                                       <div class="col-lg-4">
									        <label>Designation *</label>
									        <select style="width:100%" id="selectDesignation" name="'designationId'"  class="form-control required designations-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-4">
									        <label>Directorate/Unit *</label>
									        <select style="width:100%" id="selectDirAndUnit" name="'unitId'"  class="form-control required directorates-and-units-via-ajax"></select>									       
									   </div>
                                        <div class="col-lg-4">
									        <label>Section *</label>
									        <select style="width:100%" id="selectSection" name="'sectionid'"  class="form-control required sections-via-ajax"></select>									       
									   </div>
                                       <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Employment Date *</label>
                                               <input id="dateofemployment" name="dateofemployment" type="date" max="${toDate}" class="form-control required">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
									        <label>Employment Status *</label>
									        <select style="width:100%" id="selectEmploymentStatus" name="'employmentstatusid'"  class="form-control required employment-statuses-via-ajax"></select>									       
									   </div>	
                                       <div class="col-lg-4">
									        <label>Contract Type *</label>
									        <select style="width:100%" id="selectContractType" name="employmentcategoryId"  class="form-control required contract-types-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-12">
									        <label>Supervisor *</label>
									        <select style="width:100%" id="selectSupervisor" name="supervisordesignationid"  class="form-control required supervisors-via-ajax"></select>									       
									   </div>
                                   </div>
                               </fieldset>	
                               <h1>Other Information</h1>
                               <fieldset>
                               	<div class="row">
                                    <div class="col-lg-12">
								        <label>Duty Station *</label>
								        <select style="width:100%" id="selectDutyStation" name="dutystationid"  class="form-control required duty-stations-via-ajax"></select>									       
								   </div>
								   <div class="col-lg-12">
								        <label>National Identification Number (NIN)</label>
								        <input id="nationalId" name="nationalId" type="text" class="form-control">									       
								   </div>
								   <div class="col-lg-12">
								        <label>Passport Number</label>
								        <input id="passportNo" name="passportNo" type="text" class="form-control">									       
								   </div>
								   <div class="col-lg-12">
								        <label>TIN Number</label>
								        <input id="tinNumber" name="tin" type="text" class="form-control">									       
								   </div>
                               	</div>
                               </fieldset>			                                
                           </form:form>
                       </div>
                   </div>
                   </div>

               </div>
                     <!-- /.Form Contents -->
                 </div>
                 <div class="modal-footer">
                     <button type="button" class="btn btn-white" data-dismiss="modal">Close Form</button>
                 </div>
             </div>
         </div>
     </div>

