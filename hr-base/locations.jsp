<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Countries And Cities</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <!-- ajax -->

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
                            <li class="active"><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Countries</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-4"><i class="fa fa-code-fork"></i> Cities</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-3" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-countries" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty locations}">
	                    					<c:forEach var="location" items="${locations}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${location.abbreviation}"/></td>
							                        <td><c:out value="${location.name}"/></td>
							                        <td><c:out value="${location.description}"/></td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
			                        <!-- New Country Modal -->
						            <div class="modal inmodal" id="NewCountry" tabindex="-1" role="dialog" aria-hidden="true">
						            	<div class="modal-dialog">
						                    <div class="modal-content animated bounceInRight">
						                            <div class="modal-header">
						                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						                                <h4 class="modal-title">Add Country</h4>
						                                <small class="font-bold">Fill the form to add new country.</small>
						                            </div>
						                            <form:form action="${pageContext.request.contextPath}/country-create.htm" 
						                            role="form" method="post"  modelAttribute="country" name="countryCreate">						                                    
							                            <div class="modal-body">
							                                <div class="form-group"><label>Code Name</label> <input  name="codeName"  class="form-control"/></div>
							                                <div class="form-group"><label>Country Name</label> <input  name="countryName"  class="form-control"/></div>						                                                     
							                            </div>
							                            <div class="modal-footer">
							                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							                                <button type="submit" value="submit" class="btn btn-primary" id="submitCountry">Save changes</button>
							                            </div>
						                            </form:form>
						                        </div>
						                </div>
						             </div>
						            <!-- ./ New Country Modal -->
                                </div>
                            </div>
                            <div id="tab-4" class="tab-pane">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-cities" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty cities}">
	                    					<c:forEach var="city" items="${cities}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${city.abbreviation}"/></td>
							                        <td><c:out value="${city.name}"/></td>
							                        <td><c:out value="${city.description}"/></td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
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
        $('.dataTables-countries').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Countries'},
                {extend: 'pdf', title: 'Countries'},

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
        
        $('.dataTables-cities').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Cities'},
                {extend: 'pdf', title: 'Cities'},

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
</body>
</html>