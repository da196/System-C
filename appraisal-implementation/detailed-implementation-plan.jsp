<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Detailed Implementation Plan</title>
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
                            <h3 class="objective-by-id">Implementation Plan for objective:</h3>                            	
	                            <div class="table-responsive">
						           <table class="table table-striped table-bordered table-hover dataTables-implementation-plan" >
							           <thead>
								           <tr>
								               <th width="30px">S/N</th>
								               <th>Outcome</th>
								               <th>Activities</th>
								               <th>Output</th>
								           </tr>
							           </thead>
							           <tbody>
							           	<c:if test="${not empty compiledImplPlan}">
							           		<c:forEach var="outcomeList" items="${compiledImplPlan}" varStatus="status">
							           			<c:set var="activityCount" value="${outcomeList.performanceObjectiveOutcomeActivityGlobalResponselist.size()}" />							           			
							           			<c:if test="${activityCount==0}">
						           					<c:set var="activityCount" value="1" />
						           				</c:if>
						           				<c:set var="activities" value="${outcomeList.performanceObjectiveOutcomeActivityGlobalResponselist}" />
						           				
						           				<c:set var="outputsCount" value="${activities.performanceObjectiveOutcomeActivityOutputGlobalResponselist.size()}" />
						           				<c:if test="${outputsCount==0}">
						           					<c:set var="outputsCount" value="1" />
						           				</c:if>
						           				<c:if test="${not empty outcomeList}">
						           					<c:set var="fisrtActivity" value="${activities[0]}" />
						           					<tr>
								           				<td rowspan="${activityCount}">${status.count}</td>
								           				<td rowspan="${activityCount}">${outcomeList.description}</td>
								           				<!--./ Activities for first outcome -->
														<c:choose>
															<c:when test="${not empty fisrtActivity}">
																<td rowspan="${outputsCount}"><c:out value="${fisrtActivity.description}" /></td>
																<!--./ Activities for first outcome -->
																<c:choose>
																	<c:when test="${not empty fisrtActivity.performanceObjectiveOutcomeActivityOutputGlobalResponselist}">
																		<c:forEach var="output0" items="${fisrtActivity.performanceObjectiveOutcomeActivityOutputGlobalResponselist}">
																			<td><c:out value="${output0.description}" /></td>
																		</c:forEach>																		
																	</c:when>
																	<c:otherwise>
																		<td></td>
																	</c:otherwise>
																</c:choose>	
															</c:when>
															<c:otherwise>
																<td></td>
															</c:otherwise>
														</c:choose>	
								           			</tr>
								           			<!--./ Other Activities -->
								           			<c:forEach var="activity" items="${activities}" varStatus="activityIndex">
									            	 	<c:if test="${activityIndex.count>1}">
									            	 		<tr class="gradeX">
									            	 			<!-- from the second activity ++ -->
																<td><c:out value="${activity.description}" /></td>
											            	</tr>
									            	 	</c:if>
									            	</c:forEach>
						           				</c:if>								           			
							           		</c:forEach>
							           	</c:if>
							           </tbody>
						           </table>
					           </div>
                        </div>
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

//----------------------------------------------//
// 	      Datatable Ajax
//----------------------------------------------//

var goalReady = 0

function goalsDatatableFunction(){
	if(goalReady==0){
		$('.dataTables-implementation-plan').DataTable({
		    dom: '<"html5buttons"B>lTfgitp',
		    buttons: [
		        { extend: 'copy'},
		        {extend: 'csv'},
		        {extend: 'excel', title: 'Implementation Plan'},
		        {extend: 'pdf', title: 'Implementation Plan'},
		
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
		goalReady == 1;
	}
}

$(document).ready(function(){	
	//----------------------------------------------//
	// 	      Retrieve performance plans Ajax
	//----------------------------------------------//
	var urlID = window.location.href.split('/');
	var objectiveID = urlID.pop() || urlID.pop();
	$.ajax({
		url: '${pageContext.request.contextPath}/get-objective-by-objectiveIdAjax/'+objectiveID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$(".objective-by-id").append(' '+data.description);
				//Datatable fx
				goalsDatatableFunction();
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	

})

</script>

</body>
</html>