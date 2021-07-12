<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | General Settings</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">

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
		 <!-- Body contents starts -->
			<div class="row">
                <div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1"> Salutations</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2">Marital Status</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-3">Relative Categories</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-4">Religions</a></li>
                        </ul>
                        <div class="tab-content">
                        
                       	 <!-- Salutations -->
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-example" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty salutations}">
	                    					<c:forEach var="salutation" items="${salutations}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${salutation.name}"/></td>
							                        <td><c:out value="${salutation.abbreviation}"/></td>
							                        <td><c:out value="${salutation.description}"/></td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                    </tr>
					                    </tfoot>
					                    </table>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Marital Statuses -->
                        <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-example" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty maritalStatuses}">
	                    					<c:forEach var="maritalStatus" items="${maritalStatuses}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${maritalStatus.name}"/></td>
							                        <td><c:out value="${maritalStatus.abbreviation}"/></td>
							                        <td><c:out value="${maritalStatus.description}"/></td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                    </tr>
					                    </tfoot>
					                    </table>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Relative Categories -->
                        <div id="tab-3" class="tab-pane">
                                <div class="panel-body">
                                    <p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newRelativeCategory">+ Add Category</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover relatives-datatables" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty relativeCategories}">
	                    					<c:forEach var="relativeCategory" items="${relativeCategories}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${relativeCategory.name}"/></td>
							                        <td><c:out value="${relativeCategory.abbreviation}"/></td>
							                        <td><c:out value="${relativeCategory.description}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <a href="<c:url value="/delete-relative-category.htm/${relativeCategory.id}" />" class="btn btn-danger btn-circle btn-outline" title="Delete"><i class="fa fa-trash-o"></i></a>
							                        </td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Religions -->
                        <div id="tab-4" class="tab-pane">
                                <div class="panel-body">
                                    <p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newReligion">+ Add Religions</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover relatives-datatables" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                        <th>Status</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody>
				                    	<c:if test="${not empty religions}">
	                    					<c:forEach var="religion" items="${religions}" varStatus="status">
							                    <tr class="gradeX">
							                        <td width="30px">${status.count}</td>
							                        <td><c:out value="${religion.name}"/></td>
							                        <td><c:out value="${religion.abbreviation}"/></td>
							                        <td><c:out value="${religion.description}"/></td>
							                        <td><c:out value="${religion.active}"/></td>
							                        <td>
							                            <button class="btn btn-info btn-circle btn-outline" type="button" title="Edit Details"><i class="fa fa-edit"></i></button>
							                            <a href="<c:url value="/delete-religion.htm/${religion.id}" />" class="btn btn-danger btn-circle btn-outline" title="Delete"><i class="fa fa-trash-o"></i></a>
							                        </td>
							                    </tr>
						                    </c:forEach>
		                    			</c:if>
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Name</th>
					                        <th>Abbreviation</th>
					                        <th>Description</th>
					                        <th>Status</th>
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
               <!--*********************************************************************************** 
               							General Settings Modal forms sections 
             	************************************************************************************-->
             	
             	<!--*********************** New Relative category Modal *********************-->
			   	<div class="modal inmodal" id="newRelativeCategory" tabindex="-1" role="dialog" aria-hidden="true">
			           <div class="modal-dialog">
			           <div class="modal-content animated bounceInRight">
			                   <div class="modal-header">
			                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			                       <h4 class="modal-title">New Relative Category</h4>                      
			                   </div>
			                   <form:form action="${pageContext.request.contextPath}/create-relative-category.htm" 
			                    role="form" method="post"  modelAttribute="newRelativeCategory" name="relativeCategoryCreate">
			                    <div class="modal-body">                                
			                            <div class="form-group"><label>Abbreviation</label> <input type="text" name="abbreviation" placeholder="Abbreviation" class="form-control"></div>
			                            <div class="form-group"><label>Name</label> <input type="text" name="name" placeholder="Name" class="form-control"></div>		              
			                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" placeholder="Description" class="form-control"></textarea></div>
			                            		                                                   	                                
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                        <button type="submit" value="submit" class="btn btn-primary" id="submitRelativeCategory">Save changes</button>
			                    </div>
			                   </form:form>
			               </div>
			           </div>
			       </div>
			   	<!--********** ./ New Relative category Modal ************-->
			   	
			   	<!--*********************** New Religion Modal *********************-->
			   	<div class="modal inmodal" id="newReligion" tabindex="-1" role="dialog" aria-hidden="true">
			           <div class="modal-dialog">
			           <div class="modal-content animated bounceInRight">
			                   <div class="modal-header">
			                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			                       <h4 class="modal-title">New Religion</h4>                      
			                   </div>
			                   <form:form action="${pageContext.request.contextPath}/create-religion.htm" 
			                    role="form" method="post"  modelAttribute="newReligion" name="religionCreate">
			                    <div class="modal-body">                                
			                            <div class="form-group"><label>Abbreviation</label> <input type="text" name="abbreviation" placeholder="Abbreviation" class="form-control"></div>
			                            <div class="form-group"><label>Name</label> <input type="text" name="name" placeholder="Name" class="form-control"></div>		              
			                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" placeholder="Description" class="form-control"></textarea></div>
			                            		                                                   	                                
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                        <button type="submit" value="submit" class="btn btn-primary" id="submitReligion">Save changes</button>
			                    </div>
			                   </form:form>
			               </div>
			           </div>
			       </div>
			   	<!--********** ./ New Religion Modal ************-->
             	
		 <!-- Body contents ends -->
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

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

</body>
</html>