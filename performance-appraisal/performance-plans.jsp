<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Strategic Plans</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/datapicker/datepicker3.css" />" rel="stylesheet">
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<style>
.select2-close-mask{
    z-index: 2099;
}
.select2-dropdown{
    z-index: 3051;
}

.custom-margin-right{
 margin-right: 7px;
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
		 
	 <div class="wrapper wrapper-content  animated fadeInRight">
		 <!--Body Contents -->
			<div class="row">				
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Strategic Plans</h3>
                            	<button type="button" class="btn btn-xs btn-warning" data-toggle="modal" data-target="#newPerformancePlan"> <i class="fa fa-plus"></i> Add Plan</button>
	                            <div class="table-responsive">
						           <table class="table table-striped table-bordered table-hover dataTables-strategic-plans" >
							           <thead>
								           <tr>
								               <th width="30px">S/N</th>
								               <th>Strategic Plan</th>
								               <th>Plan Timeline</th>
								               <th>Actions</th>
								           </tr>
							           </thead>
							           <tbody class="performancePlansTabularAjax">
							           
							           </tbody>
						           </table>
					           </div>
                        </div>
                    </div>
                </div>

            </div>
            <!------------------------------------------- ******************************************************
            ------------------------------------Modal sections--------------------------------------------------- 
            *********************************************************------------------------------------------->
            
            <!-- Performance Plans -->
            <div class="modal inmodal" id="newPerformancePlan" tabindex="-1" role="dialog" aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content animated flipInY">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                             <h4 class="modal-title">New performance plan</h4>
                         </div>
                         <form:form action="${pageContext.request.contextPath}/add-new-performance-plan.htm" 
						  role="form" method="post"  modelAttribute="newPerformancePlan" name="newPerformancePlan" class="form-horizontal">
		                         <div class="modal-body">		                             
		                                <div class="form-group"><label class="col-lg-2 control-label">Plan Name</label>
		
		                                    <div class="col-lg-10"><textarea name="name" id="planName" placeholder="Performance Plan" rows="3" style="resize: vertical;" class="form-control"></textarea>
		                                    </div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Abbreviation</label>
		
		                                    <div class="col-lg-10"><input type="text" name="abbreviation" id="planAbbreviation" placeholder="Abbrevition" class="form-control"></div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
		
		                                    <div class="col-lg-10"><textarea name="description" id="planDescription" placeholder="Description" rows="3" style="resize: vertical;" class="form-control"></textarea></div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Start Year</label>
		
		                                    <div class="col-lg-10"><input type="text" name="yearstarting" id="planYearstarting" placeholder="Start Year" class="year-picker form-control"></div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">End Year</label>
		
		                                    <div class="col-lg-10"><input type="text" name="yearending" id="planYearending" placeholder="End Year" class="year-picker form-control"></div>
		                                </div>	                             
		                         </div>
		                         <div class="modal-footer">
		                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
		                             <button type="submit" value="submit" onClick="newPerformancePlan()" class="btn btn-primary">Save Details</button>
		                         </div>
		                 </form:form>        
                     </div>
                 </div>
             </div>

             
		 <!-- Body contents -->
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<!-- Date Picker -->
<script src="<c:url value="/resources/js/plugins/datapicker/bootstrap-datepicker.js" />"></script>
 <script type="text/javascript">
 $('.year-picker').datepicker({
    minViewMode: 2,
    format: 'yyyy',
    autoclose: true
  });
     
 $("form :input").attr("autocomplete", "off");
 </script>

<script>

$(document).ready(function(){
	//----------------------------------------------//
	// 	      Retrieve performance plans Ajax
	//----------------------------------------------//
	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-performance-plansAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){	
					var GoalsUrl = '${pageContext.request.contextPath}/strategic-goals.htm/'+item.id;
					$(".performancePlansAjax").append('<a href="'+GoalsUrl+'" style="color: black;"><li class="warning-element">'
					                            +item.name+'.'
					                            +'<div class="agile-detail">'
					                                +'<button type="button" class="pull-right btn btn-xs btn-white">Details</button>'
					                                +'<i class="fa fa-clock-o"></i> '+item.yearstarting+'/'+item.yearending
					                            +'</div>'
					                        +'</li></a>');
					$(".performancePlansTabularAjax").append('<tr>'+
																'<td>'+ ++i +'</td>'+
																'<td>'+item.name+'</td>'+
																'<td>'+ item.yearstarting+'/'+item.yearending +'</td>'+
																'<td>'+
																'<a href="'+GoalsUrl+'" class="btn btn-xs btn-white custom-margin-right">View Goals</a>'+
																'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
																'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
																'</td>'+
															'</tr>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	

})

//*************Confirmation sweet alerts
function confirmDelete(planID){
	$.confirm({
	    title: 'Confirm Delete!',
	    content: 'Are you sure?, this item will be completely removed.',
	    buttons: {
	        confirm: function () {
	        	$.ajax({
	        		url: '${pageContext.request.contextPath}//delete-plan-byPlanIdAjax/'+planID,
	        		type: 'PUT',
	        		success:function(result){
	        			$.alert('Confirmed!');
	        			location.reload();
	        		},
	        		error:function(jqXHR,textStatus,errorThrown){
	        			alert("Error-"+textStatus+","+errorThrown);
	        		}
	        	});	            
	        },
	        cancel: function () {
	            $.alert('Canceled!');
	        },
	    }
	});
}

</script>

</body>
</html>