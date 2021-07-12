<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Employees</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/chosen/chosen.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/switchery/switchery.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/jasny/jasny-bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/datapicker/datepicker3.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
   <link href="<c:url value="/resources/css/preloader.css" />" rel="stylesheet">
   
   <style>
   	.select2-close-mask{
	    z-index: 2099;
	}
	.select2-dropdown{
	    z-index: 3051;
	}
   </style>
</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../includes/sidebar.jsp" />		
		 <div id="page-wrapper" class="gray-bg">
		 
		<!-- Top Bar Include -->
		 <jsp:include page="../includes/topbar.jsp" />
		  
	 <div class="wrapper wrapper-content animated fadeInRight">
     <div class="row"></div>       
	 
		 <!-- Employees data table-->
		   <div class="row">
                <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Employees</h5>
                        <div class="ibox-tools">
                            <p>                            
	                            <a href="<c:url value="/add-new-employee.htm" />" class="btn btn-primary btn-xs">+Add New Employee</a>
	                        </p>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<!--*** Pre-loader section ***-->
                    	<div class="loader">
                    		<h3>Please wait...</h3>
						    <div class="box">
						        <div class="circle"></div>
						    </div>
						    <div class="box">
						        <div class="circle"></div>
						    </div>
						</div>					
                        <div class="table-responsive loadEmployees">
		                    <table class="table table-striped table-bordered table-hover dataTables-example" >
		                    <thead>
		                    <tr>
		                        <th>S/N</th>
		                        <th>Name</th>
		                        <th>Gender</th>
		                        <th>PF No.</th>
		                        <th>Department/Unit</th>
		                        <th>Designation</th>
		                        <th>Join Date</th>
		                        <th>Date Of Birth</th>
		                        <th>Actions</th>
		                    </tr>
		                    </thead>
		                    <tbody class="all-employees">
		                           
		                    </tbody>
		                    <tfoot>
		                    <tr>
		                        <th>S/N</th>
		                        <th>Name</th>
		                        <th>Gender</th>
		                        <th>PF No.</th>
		                        <th>Department/Unit</th>
		                        <th>Designation</th>
		                        <th>Join Date</th>
		                        <th>Date Of Birth</th>
		                        <th>Actions</th>
		                    </tr>
		                    </tfoot>
		                    </table>
                        </div>

                    </div>
                </div>
            </div>
            </div>
		 <!-- /.Employees data table -->
		 
		<!--****** This holds form to add new employee modal *********-->
		<jsp:include page="../forms/employee-profile/add-new-employee.jsp" />	
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
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Select2 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

<!-- Datepicker and full Calender -->
<script src="<c:url value="/resources/js/plugins/datapicker/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/plugins/fullcalendar/moment.min.js" />"></script>

<%-- <script src="<c:url value="/resources/js/plugins/pace/pace.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/chosen/chosen.jquery.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jsKnob/jquery.knob.js" />"></script> --%>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/staps/jquery.steps.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/validate/jquery.validate.min.js" />"></script>

<script>
	function getContextPath() {
	    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
</script>
<!-- HR Base -->
<script src="<c:url value="/resources/js/modules/add-new-employee.js" />"></script>
<script src="<c:url value="/resources/js/modules/hr-base.js" />"></script>

<!-- Page-Level Scripts -->

<script>
//Hide datatable innitially
$('.loadEmployees').hide();

var employeesEntries = 0;

function employeesDatatable(){
	if(employeesEntries == 0) {
	     $('.dataTables-example').DataTable({
	         dom: '<"html5buttons"B>lTfgitp',
	         buttons: [
	             { extend: 'copy'},
	             {extend: 'csv'},
	             {extend: 'excel', title: 'Employees'},
	             {extend: 'pdf', title: 'Employees'},

	             {extend: 'print',
	              customize: function (win){
	                     $(win.document.body).addClass('white-bg');
	                     $(win.document.body).css('font-size', '10px');

	                     $(win.document.body).find('table')
	                             .addClass('compact')
	                             .css('font-size', 'inherit');
	             }
	             }
	         ],
	         "pageLength": 50

	     });
	     
	     employeesEntries == 1;
	}		
}

 $(document).ready(function(){	 
/********* Get all employees for datatable *******************/
var apiUrl = '/get-all-employees-for-datatable-byAjax';
var profileUrl = '/employee-profile.htm';
if(getContextPath()!=null){
	apiUrl = getContextPath()+""+apiUrl;
	profileUrl = getContextPath()+""+profileUrl;
	}
$.ajax({
	url: apiUrl,
	type: 'GET',
	success:function(data,textStatus,jqXHR){
		if(!data){
			alert("No Data Received");
		}else{	
			$.each(data,function(i,item){				
				$(".all-employees").append('<tr class="gradeX">'+
						                        '<td width="30px">'+ ++i +'</td>'+
						                        '<td>'+item.firstName+' '+item.middleName+' '+item.lastName+'</td>'+
						                        '<td>'+item.gender+'</td>'+
						                        '<td>'+item.fileNumber+'</td>'+
						                        '<td>'+item.unit+'</td>'+
						                        '<td>'+item.designation+'</td>'+
						                        '<td>'+item.dateofemployment+'</td>'+
						                        '<td>'+item.dob+'</td>'+
						                        '<td>'+
						                            '<a href="'+profileUrl+'/'+item.id+'" class="btn btn-info btn-outline btn-xs" type="button" title="Employee Profile"><i class="fa fa-user"></i> Profile</a>'+			                            
						                        '</td>'+
						                    '</tr>');
			});	
			
			employeesDatatable();
		}
	},
	complete: function(){
		$('.loader').hide();
		$('.loadEmployees').show();
    },
	error:function(jqXHR,textStatus,errorThrown){
		alert("Error-"+textStatus+","+errorThrown);
	}
});	
		
	 
     /* Init DataTables */
     var oTable = $('#editable').DataTable();

     /* Apply the jEditable handlers to the table */
     oTable.$('td').editable( '../example_ajax.php', {
         "callback": function( sValue, y ) {
             var aPos = oTable.fnGetPosition( this );
             oTable.fnUpdate( sValue, aPos[0], aPos[1] );
         },
         "submitdata": function ( value, settings ) {
             return {
                 "row_id": this.parentNode.getAttribute('id'),
                 "column": oTable.fnGetPosition( this )[2]
             };
         },

         "width": "90%",
         "height": "100%"
     } );	
     
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
</script>
 
</body>
</html>