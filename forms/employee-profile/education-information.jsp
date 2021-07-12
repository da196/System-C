<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal inmodal" id="addEducationInfo" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			    <i class="fa fa-user modal-icon"></i>
			    <h4 class="modal-title">Add Education</h4>
			    <small class="font-bold">Employee Education Information Form.</small>
			</div>
			<form:form action="${pageContext.request.contextPath}/add-employee-educationAjax" modelAttribute="newEducation" role="form" method="post" id="newEducation">
				<div class="modal-body">	
				        <div class="form-group col-sm-6"> <select style="width:100%" id="selectEducationLevel" name="levelid" class="form-control required levels-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectCourse"  name="courseid" class="form-control required courses-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectIstitution" name="institutionid" class="form-control required institutions-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectInstitutionCountry" name="countryid"  class="form-control required institutionsCountries-via-ajax"></select></div>
						<div class="form-group col-sm-6"><label>Start Year</label> <input id="startDate" type="text" name="datestart" class="form-control" placeholder="Select start year" autocomplete="off"></div>
						<div class="form-group col-sm-6"><label>End Year</label> <input id="endDate" type="text" name="datend" placeholder="Select End year" class="form-control required" autocomplete="off"></div>
						<div class="form-group"><label>Attach Certificate</label> <input type="file" name="educationCertificationFile" placeholder="Attach Education Certification" class="form-control required" autocomplete="off"></div>	
						<input type="hidden" name="employeeid" value="${details.id}">
						<input type="hidden" name="attachmenttypeid" value="3">	
				</div>
				<div class="modal-footer">
				    <button type="button"  class="btn btn-white" data-dismiss="modal">Close</button>
				    <button type="button" id="btnSaveEducationInfo" class="btn btn-primary">Save changes</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

