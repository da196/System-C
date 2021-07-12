<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="modal inmodal" id="editFamilyInfo" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content animated bounceInRight">
			<div class="modal-header">
			    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			    <i class="fa fa-user modal-icon"></i>
			    <h4 class="modal-title">Update Family Information</h4>
			    <small class="font-bold">Employee Family Information Form.</small>
			</div>
			<form:form action="${pageContext.request.contextPath}/add-relative-employeeAjax" role="form" method="post" id="newRelative">
				<div class="modal-body">	
				        <div class="form-group col-sm-6"> <select style="width:100%" id="selectCategory" name="relativecategoryid" class="form-control required categories-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectNationality"  name="nationalityid" class="form-control required nationalities-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectCountry" name="countryofbirthid" class="form-control required countries-via-ajax"></select></div>
						<div class="form-group col-sm-6"> <select style="width:100%" id="selectCountry2" name="countryofresidenceid"  class="form-control required countries2-via-ajax"></select></div>
						<div class="form-group col-sm-6"><label>First Name</label> <input type="text" name="firstname" placeholder="First Name" class="form-control required" autocomplete="off"></div>
						<div class="form-group col-sm-6"><label>Middle Name</label> <input type="email" name="middlename" placeholder="Middle Name" class="form-control required" autocomplete="off"></div>
						<div class="form-group col-sm-6"><label>Last Name</label> <input type="text" name="lastname" placeholder="Lat Name" class="form-control required" autocomplete="off"></div>
						<div class="form-group col-sm-6"><label>Mobile Number</label> <input type="text" name="phoneprimary" placeholder="Mobile Number" class="form-control required" autocomplete="off"></div>
						<div class="form-group"><label>Date of Birth</label> <input type="date" name="dob" placeholder="Date of birth" class="form-control required" autocomplete="off"></div>
						<div class="form-group"><label>Address</label> <input type="text" name="address" placeholder="Address" class="form-control required" autocomplete="off"></div>	
						<input type="hidden" name="employeeid" value="${details.id}">
						<input type="hidden" name="attachmenttypeid" value="2">	
				</div>
				<div class="modal-footer">
				    <button type="button"  class="btn btn-white" data-dismiss="modal">Close</button>
				    <button type="button" id="btnSaveEmployeeRelative" class="btn btn-primary">Save changes</button>
				</div>
			</form:form>
		</div>
	</div>
</div>

