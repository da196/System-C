<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Designations</title>
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
		            <li class="active"><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Designations</a></li>
		        </ul>
		        <div class="tab-content">
		            <div id="tab-3" class="tab-pane active">
		                <div class="panel-body">
		                	<p align="right">
		               <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#NewDesignation">+ Add Designation</button>
		           </p>
		             <div class="table-responsive">
				           <table class="table table-striped table-bordered table-hover dataTables-designations" >
				           <thead>
				           <tr>
				               <th>S/N</th>
				               <th>Abbreviation</th>
				               <th>Designation Name</th>
				               <th>Description</th>
				               <th>Actions</th>
				           </tr>
				           </thead>
				           <tbody>
				           <c:if test="${not empty designations}">
		    				<c:forEach var="designation" items="${designations}" varStatus="status">
				            <tr class="gradeX">
				                <td width="30px">${status.count}</td>
								<td><c:out value="${designation.abbreviation}"/></td>
								<td><c:out value="${designation.name}"/></td>
								<td><c:out value="${designation.description}"/></td>
				                <td>
				                    <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details" data-toggle="modal" data-target="#UpdateDesignation"><i class="fa fa-edit"></i></button>				                            
				                    <a href="<c:url value="/delete-designation.htm/${designation.id}" />" class="btn btn-danger btn-circle btn-outline" title="Delete"><i class="fa fa-trash-o"></i></a>
				                </td>
				            </tr>
			           		</c:forEach>
			       			</c:if>
				          </tbody>
				          <tfoot>
				          <tr>
				              <th>S/N</th>
				              <th>Abbreviation</th>
				              <th>Designation Name</th>
				              <th>description</th>
				              <th>Actions</th>
				          </tr>
				          </tfoot>
				          </table>
		            </div>
			           <!-- New Designation Modal -->
					   	<div class="modal inmodal" id="NewDesignation" tabindex="-1" role="dialog" aria-hidden="true">
					           <div class="modal-dialog">
					           <div class="modal-content animated bounceInRight">
					                   <div class="modal-header">
					                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					                       <h4 class="modal-title">+ Add Designation</h4>                      
					                   </div>
					                   <form:form action="${pageContext.request.contextPath}/designation-create.htm" 
					                    role="form" method="post"  modelAttribute="designation" name="designationCreate">
					                    <div class="modal-body">                                
					                            <div class="form-group"><label>Abbreviation</label> <input type="text" name="abbreviation" placeholder="Abbreviation" class="form-control"></div>
					                            <div class="form-group"><label>Designation Name</label> <input type="text" name="name" placeholder="Name" class="form-control"></div>		              
					                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" placeholder="Description" class="form-control"></textarea></div>
					                            		                                                   	                                
					                    </div>
					                    <div class="modal-footer">
					                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					                        <button type="submit" value="submit" class="btn btn-primary" id="submitDesignation">Save changes</button>
					                    </div>
					                   </form:form>
					               </div>
					           </div>
					       </div>
					   	<!-- ./ New Designation Modal -->
					   	
					   	<!-- Update Designation Modal -->
					   	<div class="modal inmodal" id="UpdateDesignation" tabindex="-1" role="dialog" aria-hidden="true">
					           <div class="modal-dialog">
					           <div class="modal-content animated bounceInRight">
					                   <div class="modal-header">
					                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
					                       <h4 class="modal-title">Update Designation</h4>                      
					                   </div>
					                   <form:form action="${pageContext.request.contextPath}/designation-create.htm" 
					                    role="form" method="post"  modelAttribute="designation" name="designationCreate">
					                    <div class="modal-body">                                
					                            <div class="form-group"><label>Abbreviation</label> <input type="text" name="abbreviation" placeholder="Abbreviation" class="form-control"></div>
					                            <div class="form-group"><label>Designation Name</label> <input type="text" name="name" placeholder="Name" class="form-control"></div>		              
					                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" placeholder="Description" class="form-control"></textarea></div>
					                            		                                                   	                                
					                    </div>
					                    <div class="modal-footer">
					                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
					                        <button type="submit" value="submit" class="btn btn-primary" id="UpdateDesignation">Save changes</button>
					                    </div>
					                   </form:form>
					               </div>
					           </div>
					       </div>
					   	<!-- ./ Update Designation Modal -->
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
     $('.dataTables-designations').DataTable({
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
     
     
   $.ajax({
	url: '${pageContext.request.contextPath}/get-employees-listAjax',
	type: 'GET',
	success:function(data,textStatus,jqXHR){
		if(!data){
			alert("No Data Received");
		}else{								
			$.each(data,function(i,item){	
				var fullName = item.firstName+" "+item.middleName+" "+item.lastName;
				$(".employees-via-ajax").append('<option value="'+item.id+'">'+fullName+'</option>');
			});				
		}
	},
	error:function(jqXHR,textStatus,errorThrown){
		alert("Error-"+textStatus+","+errorThrown);
	}
});

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