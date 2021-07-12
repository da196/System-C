<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal inmodal" id="addCertification" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			    <i class="fa fa-user modal-icon"></i>
			    <h4 class="modal-title">Add Certification</h4>
			    <small class="font-bold">Employee certification Information Form.</small>
			</div>
			<form:form action="${pageContext.request.contextPath}/add-employee-certificationAjax" modelAttribute="newCertificationModel" role="form" method="post" id="newCertification">
				<div class="modal-body">
						<div class="form-group"><label>Course/Certificate Name</label> <input type="text" name="description" placeholder="Course Name" class="form-control required" autocomplete="off"></div>
						<div class="form-group"> <select style="width:100%" id="selectCertificationCategory" name="certificationcategoryid"  class="form-control required certificationCategories-via-ajax"></select></div>
						<div class="form-group"><label>Institution</label> <input type="text" name="institution" placeholder="Institution" class="form-control required" autocomplete="off"></div>
						<div class="form-group"> <select style="width:100%" id="selectCertificationInstitutionCountry" name="countryid"  class="form-control required certificationCountries-via-ajax"></select></div>						
						<div class="form-group col-sm-6"><label>Start Date</label> <input id="startDate2" type="text" name="datestart" class="form-control" placeholder="Select start date" autocomplete="off"></div>
						<div class="form-group col-sm-6"><label>End Date</label> <input id="endDate2" type="text" name="datend" placeholder="Select End date" class="form-control required" autocomplete="off"></div>
						<div class="form-group"><label>Attach Certificate</label> <input type="file" name="certificationFile" placeholder="Attach Certification Certificate" class="form-control required" autocomplete="off"></div>	
						<input type="hidden" name="employeeid" value="${details.id}">
						<input type="hidden" name="attachmenttypeid" value="4">	
				</div>
				<div class="modal-footer">
				    <button type="button"  class="btn btn-white" data-dismiss="modal">Close</button>
				    <button type="button" id="btnSaveCertification" class="btn btn-primary">Save changes</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

