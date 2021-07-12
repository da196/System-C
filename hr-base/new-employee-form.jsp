<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | +Add New Employee</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
   <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
   <link href="<c:url value="/resources/css/plugins/chosen/chosen.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/switchery/switchery.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/jasny/jasny-bootstrap.min.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <!-- Date Picker CSS -->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />
	<style type="text/css">
		.sample-validation {
		}
	</style>
  
</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../includes/sidebar.jsp" />		
		 <div id="page-wrapper" class="gray-bg">
		 
		 <!-- Top navigation Bar -->
		 <jsp:include page="../includes/topbar.jsp" />
		 <!-- Top navigation bar ends -->
		 
	 <div class="wrapper wrapper-content">
		 <!-- New employee form -->
			<div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-title">
                            <h5><i class="fa fa-user"></i> New Employee Form</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <form:form action="${pageContext.request.contextPath}/employee-create.htm" role="form" method="post"  modelAttribute="employee" name="employeeCreate" id="form" class="wizard-big">
                               <h1>Basic Information</h1>
                               <fieldset>
                                   <div class="row">
                                        <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>First name *</label>
                                               <input id="firstName" name="firstName" type="text" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Middle name *</label>
                                               <input id="middleName" name="middleName" type="text" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Last name *</label>
                                               <input id="lastName" name="lastName" type="text" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>File Number *</label>
                                               <input id="fileNumber" name="fileNumber" type="text" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Card Number *</label>
                                               <input id="cardNumber" name="cardNumber" type="text" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Office Email *</label>
                                               <input id="email" name="email" type="email" class="form-control required" autocomplete="off">
                                           </div>
                                       </div>
                                       <!-- Fields Row -->													
                                       <div class="col-lg-4">
									        <label>Salutation *</label>
									        <select style="width:100%" name="salutationId"  class="form-control required salutations-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-4">
									        <label>Nationality *</label>
									        <select style="width:100%" name="nationalityId"  class="form-control required emp-nationalities-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4">
									        <label>Gender *</label>
									        <select style="width:100%" name="genderid"  class="form-control required genders-via-ajax"></select>									       
									   </div> 
                                       <div class="col-lg-4 hidden">
									        <label>Religion *</label>
									        <select style="width:100%" name="religionId"  class="form-control required fixed-religion-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-6">
									        <label>Marital Status *</label>
									        <select style="width:100%" name="maritalstatusId"  class="form-control required marital-statuses-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-6">
                                           <div class="form-group">
                                               <label>Date Of Birth *</label>
                                               <div class="input-group date">
			                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="dateOfBirth" name="dob" type="text" placeholder="Date Of Birth" class="form-control required" autocomplete="off">
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
									        <select style="width:100%" name="designationId"  class="form-control required designations-via-ajax"></select>									       
									   </div>
                                       <div class="col-lg-4">
									        <label>Directorate/Unit *</label>
									        <select style="width:100%" name="unitId"  class="form-control required directorates-and-units-via-ajax"></select>									       
									   </div>
                                        <div class="col-lg-4">
									        <label>Section *</label>
									        <select style="width:100%" name="sectionid"  class="form-control required child-sections-via-ajax"></select>									       
									   </div>
                                       <!-- Fields Row -->
                                       <div class="col-lg-4">
                                           <div class="form-group">
                                               <label>Employment Date *</label>
                                               <div class="input-group date">
			                                    <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="dateofemployment" name="dateofemployment" type="text" placeholder="Date Of Employment" class="form-control required" autocomplete="off">
			                               	   </div>
                                           </div>
                                       </div>
                                       <div class="col-lg-4">
									        <label>Employment Status *</label>
									        <select style="width:100%" name="employmentstatusid"  class="form-control required employment-statuses-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4">
									        <label>Status Reason *</label>
									        <select style="width:100%" name="employmentstatusreasonid"  class="form-control required status-reasons-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4">
									        <label>Supervisor *</label>
									        <select style="width:100%" name="supervisordesignationid"  class="form-control required supervisors-via-ajax"></select>									       
									   </div>	
                                       <div class="col-lg-4">
									        <label>Contract Type *</label>
									        <select style="width:100%" name="employmentcategoryId"  class="form-control required contract-types-via-ajax"></select>									       
									   </div>
									   <div class="col-lg-4 hideShow-status">
                                            <label>Contract End Date *</label>
                                            <div class="input-group date">
		                                    	<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input id="employmentenddate" name="employmentenddate" type="text" placeholder="Contract end Date" class="form-control contract-end-date" autocomplete="off">
		                               	    </div>
                                       </div> 
                                       <div class="col-lg-12">
                                            <label>Is Supervisor? *</label> <br>
											<label class="radio-inline">
										      <input type="radio" name="issupervisor" value="1">Yes
										    </label>
										    <label class="radio-inline">
										      <input type="radio" name="issupervisor" value="0" checked>No
										    </label>
                                       </div>                                       
                                   </div>
                               </fieldset>	
                               <h1>Other Information</h1>
                               <fieldset>
                               	<div class="row">
                                    <div class="col-lg-12">
								        <label>Duty Station *</label>
								        <select style="width:100%" name="dutystationid"  class="form-control required duty-stations-via-ajax"></select>									       
								   </div>
								   <div class="col-lg-12">
								        <label>National Identification Number (NIN)</label>
								        <input id="nationalId" name="nationalId" type="text" class="form-control required" autocomplete="off">									       
								   </div>
								   <div class="col-lg-12">
								        <label>Passport Number</label>
								        <input id="passportNo" name="passportNo" type="text" class="form-control" autocomplete="off">									       
								   </div>
								   <div class="col-lg-12">
								        <label>TIN Number</label>
								        <input id="tinNumber" name="tin" type="text" class="form-control required" autocomplete="off">									       
								   </div>
                               	</div>
                               </fieldset>			                                
                           </form:form>
                        </div>
                    </div>
                </div>
        </div>
		 <!-- New employee form -->
 		</div> 
		 	<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	</div>	
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/inspinia.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>

<!-- Select2 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/staps/jquery.steps.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/validate/jquery.validate.min.js" />"></script>

<script>
	function getContextPath() {
	    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
</script>

<script>
 $(document).ready(function(){
     $("#wizard").steps();
     $("#form").steps({
         bodyTag: "fieldset",
         onStepChanging: function (event, currentIndex, newIndex)
         {
             // Always allow going backward even if the current step contains invalid fields!
             if (currentIndex > newIndex)
             {
                 return true;
             }

             // Forbid suppressing "Warning" step if the user is to young
             if (newIndex === 3 && Number($("#age").val()) < 18)
             {
                 return false;
             }

             var form = $(this);

             // Clean up if user went backward before
             if (currentIndex < newIndex)
             {
                 // To remove error styles
                 $(".body:eq(" + newIndex + ") label.error", form).remove();
                 $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
             }

             // Disable validation on fields that are disabled or hidden.
             form.validate().settings.ignore = ":disabled,:hidden";

             // Start validation; Prevent going forward if false
             return form.valid();
         },
         onStepChanged: function (event, currentIndex, priorIndex)
         {
             // Suppress (skip) "Warning" step if the user is old enough.
             if (currentIndex === 2 && Number($("#age").val()) >= 18)
             {
                 $(this).steps("next");
             }

             // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
             if (currentIndex === 2 && priorIndex === 3)
             {
                 $(this).steps("previous");
             }
         },
         onFinishing: function (event, currentIndex)
         {
             var form = $(this);

             // Disable validation on fields that are disabled.
             // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
             form.validate().settings.ignore = ":disabled";

             // Start validation; Prevent form submission if false
             return form.valid();
         },
         onFinished: function (event, currentIndex)
         {
             var form = $(this);

             // Submit form input
             form.submit();
         }
     }).validate({
                 errorPlacement: function (error, element)
                 {
                     element.before(error);
                 },
                 rules: {
                     confirm: {
                         equalTo: "#password"
                     }
                 }
             });  

 });
 
 $(function(){
    $(".hideShow-status").hide() // This hide contract termination date on page load
 });
</script>
<!-- Date Picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script>
<!-- HR Base -->
<script src="<c:url value="/resources/js/modules/add-new-employee.js" />"></script>
 
</body>
</html>