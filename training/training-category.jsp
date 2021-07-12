<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Training Category</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/animate.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- formvalidation io -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/dist/css/formValidation.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<link href="<c:url value="/resources/css/style.custom.css" />" rel="stylesheet">

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
            <li class="active"><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Training Category</a></li>
        </ul>
        <div class="tab-content">
            <div id="tab-3" class="tab-pane active">
                <div class="panel-body">
                <p align="right">
               <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#addTrainingCategory">+ Add Category</button>
           </p>
             <div class="table-responsive">
		           <table class="table table-striped table-bordered table-hover dataTables-leaveTypes" >
		           <thead>
		           <tr>
		               <th>S/N</th>		              
		               <th>Training Category</th>		         
		               <th>Description</th>
		               <th>Action</th>
		           </tr>
		           </thead>
		           <tbody>
		           <c:if test="${not empty trainingCategory}">
    				<c:forEach var="trainingCategory" items="${trainingCategory}" varStatus="status">
		            <tr class="gradeX">
		                <td width="30px">${status.count}</td>						
						<td><c:out value="${trainingCategory.name}"/></td>
						<td><c:out value="${trainingCategory.description}"/></td>
		                <td>
		                    <!-- <button class="btn btn-info btn-circle btn-outline" 
		                    type="button" title="Edit Details"><i class="fa fa-edit"></i></button>	 -->	
		                    <button onClick="showTraningCategoryDetails('${trainingCategory.id}');"
								class="btn btn-info btn-circle btn-outline"
								type="button" title="View details"><i class="fa fa-eye"></i>
						    </button>		                            
		                    <a href="<c:url value="/delete-designation.htm/${trainingCategory.id}" />" class="btn btn-danger btn-circle btn-outline" title="Delete"><i class="fa fa-trash-o"></i></a>
		                </td>
		            </tr>
	           		</c:forEach>
	       			</c:if>
		          </tbody>
		          <tfoot>
		          <tr>
		               <th>S/N</th>		             
		               <th>Training Category </th>		         
		               <th>Description</th>
		               <th>Action</th>
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


	           <!-- New Training Category Modal -->
			   	<div class="modal inmodal" id="addTrainingCategory" tabindex="-1" role="dialog" aria-hidden="true">
			           <div class="modal-dialog">
			           <div class="modal-content animated bounceInRight">
			                   <div class="modal-header">
			                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			                       <h4 class="modal-title">+ Add Training Category</h4>                      
			                   </div>

			                   <form:form action="${pageContext.request.contextPath}/v1/add-training-category" 
			                    role="form" method="post"  modelAttribute="trainingCategory" 
			                     name="formTrainingCategory" id="formTrainingCategory">
			                    
			                    
			                    
			                    <div class="modal-body">                                
			                            <div class="form-group"><label>Training Category</label> 
			                            <input type="text" name="name" id="name" placeholder="Training Category" class="form-control">
			                            </div>
			                           <div class="form-group"><label>Abbreviation</label> 
			                            <input type="text" name="code" id="code" placeholder="Training Abbreviation" class="form-control">
			                            </div>
			                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" id="description" placeholder="Description" class="form-control"></textarea></div>
			                            		                                                   	                                
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                        
			                        <button type="button" value="submit" class="btn btn-primary" 
			                        name="trainingCategoryBtn" id="trainingCategoryBtn">Save changes</button>

			                    </div>
			                   </form:form>
			               </div>
			           </div>
			       </div>
			   	<!-- ./ New Training Category Modal -->
			   	
			   	
			   	  <!-- New Update Training Category Modal -->
			   	<div class="modal inmodal" id="updateTrainingCategory" tabindex="-1" role="dialog" aria-hidden="true">
			           <div class="modal-dialog">
			           <div class="modal-content animated bounceInRight">
			                   <div class="modal-header">
			                       <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			                       <h4 class="modal-title">+ Update Training Category</h4>                      
			                   </div>

			                   <form:form action="${pageContext.request.contextPath}/v1/update-training-category" 
			                    role="form" method="post"  modelAttribute="UpdateTrainingCategory" 
			                    name="formUpdateTrainingCategory" id="formUpdateTrainingCategory">
			                    
			                    
			                    
			                    <div class="modal-body">                                
			                            <div class="form-group"><label>Training Category</label> 
			                            <input type="text" name="name" id="nameEdit" placeholder="Training Category" class="form-control">
			                            </div>
			                           <div class="form-group"><label>Abbreviation</label> 
			                            <input type="text" name="code" id="codeEdit" placeholder="Training Abbreviation" class="form-control">
			                            </div>
			                            <div class="form-group"><label>Description</label> <textarea type="text" name="description" id="description" placeholder="Description" class="form-control"></textarea></div>
			                            		                                                   	                                
			                    </div>
			                    <div class="modal-footer">
			                        <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                        
			                        <button type="button" value="submit" class="btn btn-primary" 
			                        name="trainingCategoryBtn" id="trainingCategoryBtn">Save changes</button>

			                    </div>
			                   </form:form>
			               </div>
			           </div>
			       </div>
			   	<!-- ./ New Update Training Category Modal -->
			   	
			   	
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
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
<script
src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>

		<script
			src="<c:url value="/resources/dist/js/plugins/Bootstrap.min.js" />"></script>
		<script
			src="<c:url value="/resources/dist/js/plugins/StartEndDate.min.js" />"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
		<!-- date and date picker -->
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
			<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>
		

<script src="<c:url value="/resources/js/validation/ui_validation_training_add_category.js" />"></script>

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
        

       
           
        
        

</script>
</body>
</html>