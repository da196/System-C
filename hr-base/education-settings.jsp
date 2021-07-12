<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Education Settings</title>
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
                            <li class="active"><a data-toggle="tab" href="#tab-1"> <i class="fa fa-map-marker"></i> Education levels</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2"> <i class="fa fa-map-marker"></i> Education Courses</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Education Institutions</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-4"> <i class="fa fa-map-marker"></i> Institution Types</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-5"> <i class="fa fa-map-marker"></i> Institution Categories</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#NewEduLevel">+ Add Education Level</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-eduLevels" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty educationLevels}">
	                    					<c:forEach var="educationLevel" items="${educationLevels}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${educationLevel.abbreviation}"/></td>
							                        <td><c:out value="${educationLevel.name}"/></td>
							                        <td><c:out value="${educationLevel.description}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <button class="btn btn-danger btn-circle btn-outline" type="button" title="Delete Details"><i class="fa fa-trash-o"></i></button>							                            
							                        </td>
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
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
			                        <!-- New Education level Modal -->
						            <div class="modal inmodal" id="NewEduLevel" tabindex="-1" role="dialog" aria-hidden="true">
						            	<div class="modal-dialog">
						                    <div class="modal-content animated bounceInRight">
						                            <div class="modal-header">
						                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						                                <h4 class="modal-title">Add Education Level</h4>
						                                <small class="font-bold">Fill the form to add new level.</small>
						                            </div>
						                            <form:form action="${pageContext.request.contextPath}/create-education-level.htm" 
						                            role="form" method="post"  modelAttribute="newEducationLevel" name="countryLevel">						                                    
							                            <div class="modal-body">
							                                <div class="form-group"><label>Abbreviation</label> <input  name="abbreviation"  class="form-control required"/></div>
							                                <div class="form-group"><label>Name</label> <input  name="name"  class="form-control required"/></div>
							                                <div class="form-group"><label>Description</label> <input  name="description"  class="form-control"/></div>						                                                     
							                            </div>
							                            <div class="modal-footer">
							                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							                                <button type="submit" value="submit" class="btn btn-primary" id="submitLevel">Save changes</button>
							                            </div>
						                            </form:form>
						                        </div>
						                </div>
						             </div>
						            <!-- ./ New Education level Modal -->
                                </div>
                            </div>
                            
                            
                             <!-- Education Courses Table -->
                            <div id="tab-2" class="tab-pane active">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#NewCourse">+ Add Course</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-eduLevels" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                        <th>Education Level</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty courses}">
	                    					<c:forEach var="course" items="${courses}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${course.abbreviation}"/></td>
							                        <td><c:out value="${course.name}"/></td>
							                        <td><c:out value="${course.description}"/></td>
							                        <td><c:out value="${course.educationlevel}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <button class="btn btn-danger btn-circle btn-outline" type="button" title="Delete Details"><i class="fa fa-trash-o"></i></button>							                            
							                        </td>
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
					                        <th>Education Level</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
			                        <!-- New Course Modal -->
						            <div class="modal inmodal" id="NewCourse" tabindex="-1" role="dialog" aria-hidden="true">
						            	<div class="modal-dialog">
						                    <div class="modal-content animated bounceInRight">
						                            <div class="modal-header">
						                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						                                <h4 class="modal-title">Add new course</h4>
						                                <small class="font-bold">Fill the form to add new course.</small>
						                            </div>
						                            <form:form action="${pageContext.request.contextPath}/create-education-course.htm" 
						                            role="form" method="post"  modelAttribute="newEducationCourse" name="courseCreate">						                                    
							                            <div class="modal-body">
							                                <div class="form-group"><label>Abbreviation</label> <input  name="abbreviation"  class="form-control"/></div>
							                                <div class="form-group"><label>Name *</label> <input  name="name"  class="form-control required"/></div>
							                                <div class="form-group">
				                                                <label>Level *</label>
				                                                <div class="input-group">
				                                                <select name='educationlevelid' data-placeholder="Choose Level" class="chosen-select" style="width:525px;">
				                                                   <option value="">Select Education Level</option>  
																   <c:forEach var="educationLevel" items="${educationLevels}">
																       <option id="educationlevelid" value="${educationLevel.id}">${educationLevel.name}</option>   
																   </c:forEach>
																</select>
				                                                </div>
			                                            	</div>
							                                <div class="form-group"><label>Description</label> <input  name="description"  class="form-control"/></div>					                                                     
							                            </div>
							                            <div class="modal-footer">
							                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							                                <button type="submit" value="submit" class="btn btn-primary" id="submitCourse">Save changes</button>
							                            </div>
						                            </form:form>
						                        </div>
						                </div>
						             </div>
						            <!-- ./ New Course Modal -->
                                </div>
                            </div> 
                            
                            <!-- Institutions Table -->
                            <div id="tab-3" class="tab-pane active">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#NewInstitution">+ Add Institution</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-eduLevels" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Type</th>
					                        <th>Category</th>
					                        <th>City</th>
					                        <th>Country</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty institutions}">
	                    					<c:forEach var="institution" items="${institutions}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${institution.abbreviation}"/></td>
							                        <td><c:out value="${institution.name}"/></td>
							                        <td><c:out value="${institution.type}"/></td>
							                        <td><c:out value="${institution.category}"/></td>
							                        <td><c:out value="${institution.city}"/></td>
							                        <td><c:out value="${institution.country}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <button class="btn btn-danger btn-circle btn-outline" type="button" title="Delete Details"><i class="fa fa-trash-o"></i></button>							                            
							                        </td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Type</th>
					                        <th>Category</th>
					                        <th>City</th>
					                        <th>Country</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
			                        <!-- New Institution Modal -->
						            <div class="modal inmodal" id="NewInstitution" tabindex="-1" role="dialog" aria-hidden="true">
						            	<div class="modal-dialog">
						                    <div class="modal-content animated bounceInRight">
						                            <div class="modal-header">
						                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
						                                <h4 class="modal-title">Add New Institution</h4>
						                                <small class="font-bold">Fill the form to add new institution.</small>
						                            </div>
						                            <form:form action="${pageContext.request.contextPath}/create-institution.htm" 
						                            role="form" method="post"  modelAttribute="newInstitution" name="institutionCreate">						                                    
							                            <div class="modal-body">
							                                <div class="form-group"><label>Abbreviation</label> <input  name="abbreviation"  class="form-control"/></div>
							                                <div class="form-group"><label>Name*</label> <input  name="name"  class="form-control required"/></div>
							                                <div class="form-group"><label>Description]</label> <input  name="description"  class="form-control required"/></div>
							                                <div class="form-group">
				                                                <label>Type *</label>
				                                                <div class="input-group">
				                                                <select name='typeid' data-placeholder="Choose Type" class="chosen-select" style="width:525px;">
				                                                   <option value="">Select Institution type</option>  
																   <c:forEach var="institutionType" items="${institutionTypes}">
																       <option id="typeid" value="${institutionType.id}">${institutionType.name}</option>   
																   </c:forEach>
																</select>
				                                                </div>
			                                            	</div>
							                                <div class="form-group">
				                                                <label>Category</label>
				                                                <div class="input-group">
				                                                <select name='categoryId' data-placeholder="Choose Category" class="chosen-select" style="width:525px;">
				                                                   <option value="">Select category</option>  
																   <c:forEach var="institutionCategory" items="${institutionCategories}">
																       <option id="categoryId" value="${institutionCategory.id}">${institutionCategory.name}</option>   
																   </c:forEach>
																</select>
				                                                </div>
			                                            	</div>	
							                                <div class="form-group">
				                                                <label>City</label>
				                                                <div class="input-group">
				                                                <select name='cityId' data-placeholder="Choose City" class="chosen-select" style="width:525px;">
				                                                   <option value="">Select city</option>  
																   <c:forEach var="city" items="${cities}">
																       <option id="cityId" value="${city.id}">${city.name}</option>   
																   </c:forEach>
																</select>
				                                                </div>
			                                            	</div>	
							                                <div class="form-group">
				                                                <label>Country</label>
				                                                <div class="input-group">
				                                                <select name='countryId' data-placeholder="Choose Country" class="chosen-select" style="width:525px;">
				                                                   <option value="">Select Country</option>  
																   <c:forEach var="location" items="${locations}">
																       <option id="countryId" value="${location.id}">${location.name}</option>   
																   </c:forEach>
																</select>
				                                                </div>
			                                            	</div>				                                                     
							                            </div>
							                            <div class="modal-footer">
							                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							                                <button type="submit" value="submit" class="btn btn-primary" id="submitInstitution">Save changes</button>
							                            </div>
						                            </form:form>
						                        </div>
						                </div>
						             </div>
						            <!-- ./ New Institution Modal -->
                                </div>
                            </div>
                            
                            <!-- Institution type Table -->
                            <div id="tab-4" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-eduLevels" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty institutionTypes}">
	                    					<c:forEach var="institutionType" items="${institutionTypes}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${institutionType.abbreviation}"/></td>
							                        <td><c:out value="${institutionType.name}"/></td>
							                        <td><c:out value="${institutionType.description}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <button class="btn btn-danger btn-circle btn-outline" type="button" title="Delete Details"><i class="fa fa-trash-o"></i></button>							                            
							                        </td>
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
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
                                </div>
                            </div>
                            
                            <!-- Institution category Table -->
                            <div id="tab-5" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-eduLevels" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Abbreviation</th>
					                        <th>Name</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty institutionCategories}">
	                    					<c:forEach var="institutionCategory" items="${institutionCategories}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${institutionCategory.abbreviation}"/></td>
							                        <td><c:out value="${institutionCategory.name}"/></td>
							                        <td><c:out value="${institutionCategory.description}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <button class="btn btn-danger btn-circle btn-outline" type="button" title="Delete Details"><i class="fa fa-trash-o"></i></button>
							                        </td>
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
					                        <th>Actions</th>
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
        $('.dataTables-eduLevels').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'csv'},
                {extend: 'excel', title: 'Education Levels'},
                {extend: 'pdf', title: 'Education Levels'},

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