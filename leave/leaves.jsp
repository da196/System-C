<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Leave Module</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

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
		 <!-- Grid dashboard boxes starts -->		
         <div class="ibox-title">
             <h5>Employees Leaves Dashboard</h5>
         </div>
         <!-- Filters -->
         <div class="ibox-content m-b-sm border-bottom">         		
         		<div class="row">
		            <div class="col-lg-3">
		                <div class="widget style1 blue-bg">
		                        <div class="row">
		                            <div class="col-xs-4 text-center">
		                                <i class="fa fa-plane fa-5x"></i>
		                            </div>
		                            <div class="col-xs-8 text-right">
		                                <span> Approved Requests </span>
		                                <h2 class="font-bold"> 7</h2>
		                            </div>
		                        </div>
		                </div>
		            </div>		            
		            <div class="col-lg-3">
		                <div class="widget style1 lazur-bg">
		                    <div class="row">
		                        <div class="col-xs-4">
		                            <i class="fa fa-spinner fa-5x"></i>
		                        </div>
		                        <div class="col-xs-8 text-right">
		                            <span> Pending Requests </span>
		                            <h2 class="font-bold">4</h2>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="col-lg-3">
		                <div class="widget style1 yellow-bg">
		                    <div class="row">
		                        <div class="col-xs-4">
		                            <i class="fa fa-ban fa-5x"></i>
		                        </div>
		                        <div class="col-xs-8 text-right">
		                            <span>  System Cancelled</span>
		                            <h2 class="font-bold">5</h2>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            <div class="col-lg-3">
		                <div class="widget style1 red-bg">
		                    <div class="row">
		                        <div class="col-xs-4">
		                            <i class="fa fa-times fa-5x"></i>
		                        </div>
		                        <div class="col-xs-8 text-right">
		                            <span> Rejected Requests </span>
		                            <h2 class="font-bold">2</h2>
		                        </div>
		                    </div>
		                </div>
		            </div>		            
		        </div>

                <!-- Data table -->
                <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
						 <div class="table-responsive">
                            <table class="footable table table-stripped toggle-arrow-tiny table-hover leaves-datatable" data-page-size="15">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th data-hide="phone">Employee</th>
                                    <th data-hide="phone">Leave Type</th>
                                    <th data-hide="phone">Date From</th>
                                    <th data-hide="phone,tablet" >Date To</th>
                                    <th data-hide="phone">Status</th>
                                    <th class="text-right">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${not empty leaveApplications}">
                   					<c:forEach var="leaveApplication" items="${leaveApplications}" varStatus="status">
		                                <tr>
		                                    <td>${status.count}</td>
		                                    <td><c:out value="${leaveApplication.firstname}"/> <c:out value="${leaveApplication.middlename}"/> <c:out value="${leaveApplication.lastname}"/></td>
		                                    <td><c:out value="${leaveApplication.leavetypename}"/></td>
		                                    <td><c:out value="${leaveApplication.startdate}"/></td>
		                                    <td><c:out value="${leaveApplication.enddate}"/></td>
		                                    <td>
												<c:if test="${leaveApplication.approved == 0}">
														<span class="label label-info">Pending</span>
												</c:if> 
												<c:if test="${leaveApplication.approved == 1}">
														<span class="label label-success">Approved</span>
												</c:if> 
												<c:if test="${leaveApplication.approved == 2}">
														<span class="label label-danger">Rejected</span>
												</c:if> 
												<c:if test="${leaveApplication.approved == 3}">
													<span class="label label-warning">System Cancelled</span>
												</c:if>
												<c:if test="${leaveApplication.approved == 4}">
													<span class="label label-primary">Recalled</span>
												</c:if>
											</td>
		                                    <td class="text-right">
		                                        <div class="btn-group">
		                                            <a href="<c:url value="/employee-leave-dashboard.htm/${leaveApplication.employeeid}" />" class="btn-white btn btn-xs">Options & More</a>
		                                        </div>
		                                    </td>
		                                </tr>
	                                </c:forEach>
                                </c:if>
                                </tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="7">
                                        <ul class="pagination pull-right"></ul>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
						 </div>
                        </div>
                    </div>
                </div>
            </div>
                
            </div>

		 <!-- Grid dashboard boxes ends -->
 		</div> 
		 	<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	</div>	
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<script>
    $(document).ready(function(){
        $('.leaves-datatable').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Leaves'},
                {extend: 'pdf', title: 'Leaves'},

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
            "pageLength": 25

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

    });

    function fnClickAddRow() {
        $('#editable').dataTable().fnAddData( [
            "Custom row",
            "New row",
            "New row",
            "New row",
            "New row" ] );

    }
</script>

<script>
$(document).ready(function($) {
    $(".clickable-row").click(function() {
        window.location = $(this).data("href");
    });
});
</script>

</body>
</html>