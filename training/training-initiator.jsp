<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Training Initiator</title>
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
	<!-- Page Body starts -->
	<div class="row">
<div class="col-lg-12">
    <div class="tabs-container">
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Training Initiator</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-3" class="tab-pane active">
                <div class="panel-body">
                
             <div class="table-responsive">
		           <table class="table table-striped table-bordered table-hover dataTables-leaveTypes" >
		           <thead>
		           <tr>
		               <th>S/N</th>
		               <th>Training Initiator</th>		         
		               <th>Description</th>
		           </tr>
		           </thead>
		           <tbody>
		           <c:if test="${not empty trainingInitiator}">
    				<c:forEach var="trainingInitiator" items="${trainingInitiator}" varStatus="status">
		            <tr class="gradeX">
		                <td width="30px">${status.count}</td>
						<td><c:out value="${trainingInitiator.name}"/></td>
						<td><c:out value="${trainingInitiator.description}"/></td>

		            </tr>
	           		</c:forEach>
	       			</c:if>
		          </tbody>
		          <tfoot>
		          <tr>
		               <th>S/N</th>
		               <th>Training Initiator</th>		         
		               <th>Description</th>
		          </tr>
		          </tfoot>
		          </table>
            </div>
	          
               </div>
           </div>
       </div>
   </div>
  </div>
</div>
 <!-- Page body ends -->
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

<!-- Page-Level Scripts -->
<script>
    $(document).ready(function(){
        $('.dataTables-leaveTypes').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Designations'},
                {extend: 'pdf', title: 'Designations'},

                {extend: 'print',
                 customize: function (win){
                        $(win.document.body).addClass('white-bg');
                        $(win.document.body).css('font-size', '10px');

                        $(win.document.body).find('table')
                                .addClass('compact')
                                .css('font-size', 'inherit');
                }
                }
            ]

        });

        /* Init DataTables */
       // var oTable = $('#editable').DataTable();

        /* Apply the jEditable handlers to the table */
/*         oTable.$('td').editable( '../example_ajax.php', {
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
        } ); */

    });

/*     function fnClickAddRow() {
        $('#editable').dataTable().fnAddData( [
            "Custom row",
            "New row",
            "New row",
            "New row",
            "New row" ] );

    } */
</script>
</body>
</html>