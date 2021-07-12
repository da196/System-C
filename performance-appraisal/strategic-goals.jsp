<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Strategic Goals, Objectives, Targets</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />
    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/datapicker/datepicker3.css" />" rel="stylesheet">

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
		 
		 <!-- Top navigation Bar -->
		 <jsp:include page="../includes/topbar.jsp" />
		 <!-- Top navigation bar ends -->
		 
	 <div class="wrapper wrapper-content  animated fadeInRight">
		 <!--Body Contents -->
		 	<div class="row">
		<div class="col-lg-12">
		    <div class="tabs-container">
		        <ul class="nav nav-tabs">
		            <li class="active"><a data-toggle="tab" href="#tab-1"> <i class="fa fa-map-marker"></i> Strategic Goals, Objectives and Targets</a></li>
		        </ul>
		        <div class="tab-content">		        
		            <div id="tab-3" class="tab-pane active">
		                <div class="panel-body">
		                	<a class="btn btn-xs btn-warning" href="<c:url value="/performance-plans.htm" />"><i class="fa fa-arrow-left"></i> Back to plans</a>
		                	<div class="table-responsive">
						           <table class="table table-striped table-bordered table-hover dataTables-strategic-plans" >
						           <thead>
						           <tr>
						               <th width="30px">S/N</th>
						               <th>Strategic Goals</th>
						               <th>Strategic Objectives</th>
						               <th>Targets</th>
						           </tr>
						           </thead>
						           <tbody>
						           		<tr class="gradeX">
						           			<td width="30px"></td>
						           			<td><button type="button" class="btn btn-sm btn-block btn-primary" data-toggle="modal" data-target="#newPerformanceGoal"> <i class="fa fa-plus"></i> Add Goal</button></td>
						           			<td><button type="button" class="btn btn-sm btn-block btn-success" data-toggle="modal" data-target="#newPerformanceObjective"> <i class="fa fa-plus"></i> Add Objective</button></td>
						           			<td><button type="button" class="btn btn-sm btn-block btn-info" data-toggle="modal" data-target="#newPerformanceTarget"> <i class="fa fa-plus"></i> Add Target</button></td>
						           		</tr>
						           		<c:if test="${not empty goals}">
						           			<c:forEach var="goalSummary" items="${goals}" varStatus="status">
						           				<c:set var="goal" value="${goalSummary.performanceGoal}" />						           				
						           				<c:set var="objectiveSummaryCount" value="${goalSummary.performanceObjectiveSummarylist.size()}" />
						           				<c:if test="${objectiveSummaryCount==0}">
						           					<c:set var="objectiveSummaryCount" value="1" />
						           				</c:if>
						           				<c:set var="objectives" value="${goalSummary.performanceObjectiveSummarylist}" />
						           				<c:if test="${not empty goal}">
						           					<c:set var="fisrtObjective" value="${objectives[0]}" />
						           					<tr class="gradeX">
										                <td width="30px"  rowspan="${objectiveSummaryCount}">${status.count}</td>
														<td rowspan="${objectiveSummaryCount}"><c:out value="${goal.name}" /></td>
														<!--./ goal -->
														<c:choose>
															<c:when test="${not empty fisrtObjective}">
																<td><c:out value="${fisrtObjective.performanceObjective.description}" /></td>
																<!--./ objective -->
																<td>
																	<c:if test="${not empty fisrtObjective.targetlist}">
																		<ol style="padding-left:8px">
																		<c:forEach var="target" items="${fisrtObjective.targetlist}">
																		  <li><c:out value="${target.name}" /></li>
																		</c:forEach>
																		</ol>
																	</c:if>
																</td>
																<!--./ target -->
															</c:when>
															<c:otherwise>
																<td></td>
																<!--./ objective -->
																<td></td>
																<!--./ target -->
															</c:otherwise>
														</c:choose>														
									            	</tr>
									            	<c:forEach var="objective" items="${objectives}" varStatus="objectiveIndex">
									            	 	<c:if test="${objectiveIndex.count>1}">
									            	 		<tr class="gradeX">
																<td><c:out value="${objective.performanceObjective.description}" /></td>
																<!--./ objective -->
																<td>
																	<c:if test="${not empty objective.targetlist}">
																		<ol style="padding-left:8px">
																		<c:forEach var="target" items="${objective.targetlist}">
																		  <li><c:out value="${target.name}" /></li>
																		</c:forEach>
																		</ol>
																	</c:if>
																</td>
																<!--./ target -->
											            	</tr>
									            	 	</c:if>
									            	</c:forEach>
						           				</c:if>						           				
						           			</c:forEach>
						           		</c:if>
						          </tbody>
						          </table>
						          	${requestScope['javax.servlet.forward.request_uri']} <br>
				            </div>
		               </div>
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
             
             <!-- Performance Goals -->
             <div class="modal inmodal" id="newPerformanceGoal" tabindex="-1" role="dialog" aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content animated flipInY">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                             <h4 class="modal-title">New performance Goal</h4>
                         </div>
                         <form:form action="${pageContext.request.contextPath}/add-new-performance-goal.htm" role="form" method="post"  modelAttribute="newPerformanceGoal" class="form-horizontal">
		                         <div class="modal-body">		                             
		                                <div class="form-group"><label class="col-lg-2 control-label">Plan</label>
		
		                                    <div class="col-lg-10"><select class="plans-via-ajax" id="select2insidemodal" name="planid" style="width: 100%"></select>
		                                    </div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Goal</label>
		
		                                    <div class="col-lg-10"><textarea name="name" id="goalName" placeholder="Performance Goal" rows="3" style="resize: vertical;" class="form-control"></textarea>
		                                    </div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
		
		                                    <div class="col-lg-10"><textarea name="description" id="goalDescription" placeholder="Description" rows="3" style="resize: vertical;" class="form-control"></textarea></div>
		                                </div>	                             
		                         </div>
		                         <div class="modal-footer">
		                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
		                             <button type="submit" value="submit" class="btn btn-primary">Save Details</button>
		                         </div>
		                 </form:form>        
                     </div>
                 </div>
             </div>
             
             <!-- Performance Objective -->
             <div class="modal inmodal" id="newPerformanceObjective" tabindex="-1" role="dialog" aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content animated flipInY">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                             <h4 class="modal-title">New performance Objective</h4>
                         </div>
                         <form:form action="${pageContext.request.contextPath}/add-new-performance-objective.htm" role="form" method="post"  modelAttribute="newPerformanceObjective" class="form-horizontal">
		                         <div class="modal-body">		                             
		                                <div class="form-group"><label class="col-lg-2 control-label">Goal</label>
		
		                                    <div class="col-lg-10"><select class="goals-via-ajax" id="selectGoal" name="goalid" style="width: 100%"></select>
		                                    </div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Objective</label>
		
		                                    <div class="col-lg-10"><textarea name="description" id="objectiveDescription" placeholder="Objective Description" rows="3" style="resize: vertical;" class="form-control"></textarea>
		                                    </div>
		                                </div>	                             
		                         </div>
		                         <div class="modal-footer">
		                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
		                             <button type="submit" value="submit" class="btn btn-primary">Save Details</button>
		                         </div>
		                 </form:form>        
                     </div>
                 </div>
             </div>
             
             <!-- Performance Targets -->
             <div class="modal inmodal" id="newPerformanceTarget" tabindex="-1" role="dialog" aria-hidden="true">
                 <div class="modal-dialog">
                     <div class="modal-content animated flipInY">
                         <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                             <h4 class="modal-title">New performance Target</h4>
                         </div>
                         <form:form action="${pageContext.request.contextPath}/add-new-performance-target.htm" role="form" method="post"  modelAttribute="newPerformanceTarget" class="form-horizontal">
		                         <div class="modal-body">		                             
		                                <div class="form-group"><label class="col-lg-2 control-label">Objective</label>
		
		                                    <div class="col-lg-10"><select class="objectives-via-ajax" id="selectObjective" name="objectiveid" style="width: 100%"></select>
		                                    </div>
		                                </div>
		                                <div class="form-group"><label class="col-lg-2 control-label">Target Name</label>
		
		                                    <div class="col-lg-10"><textarea name="name" id="targetName" placeholder="Target Name" rows="3" style="resize: vertical;" class="form-control"></textarea>
		                                    </div>
		                                </div>	
		                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
		
		                                    <div class="col-lg-10"><textarea name="description" id="targetDecription" placeholder="Target Decription" rows="3" style="resize: vertical;" class="form-control"></textarea>
		                                    </div>
		                                </div>	                             
		                         </div>
		                         <div class="modal-footer">
		                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
		                             <button type="submit" value="submit" class="btn btn-primary">Save Details</button>
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
	
	$(".plans-via-ajax").select2();
	$(".plans-via-ajax").append('<option value="0">Select Plan Name</option>');
	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-performance-plansAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){						
					$(".performancePlansAjax").append('<li class="warning-element">'
					                            +item.name+'.'
					                            +'<div class="agile-detail">'
					                                +'<button type="button" class="pull-right btn btn-xs btn-white">Details</button>'
					                                +'<i class="fa fa-clock-o"></i> '+item.yearstarting+'/'+item.yearending
					                            +'</div>'
					                        +'</li>');
					
					$(".plans-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
	$("#select2insidemodal").select2({
	    dropdownParent: $("#newPerformanceGoal")
	  });
	
	//----------------------------------------------//
	// 	      Retrieve performance goals Ajax
	//----------------------------------------------//
	
	$(".goals-via-ajax").select2();
	$(".goals-via-ajax").append('<option value="0">Select Goal</option>');
	$.ajax({
		url: '${pageContext.request.contextPath}/get-performance-goalsAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){	
					
					$(".performanceGoalsAjax").append('<li class="success-element">'
					                            +item.name+'.'
					                            +'<div class="agile-detail">'
					                                +'<button type="button" class="pull-right btn btn-xs btn-white">Details</button>'
					                                +'<i class="fa fa-clock-o"></i> '
					                                +'</div>'
					                        +'</li>');
					
					$(".goals-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	$("#selectGoal").select2({
	    dropdownParent: $("#newPerformanceObjective")
	  });
	
	//----------------------------------------------//
	// 	      Retrieve performance objectives Ajax
	//----------------------------------------------//
	
	$(".objectives-via-ajax").select2();
	$(".objectives-via-ajax").append('<option value="0">Select Objective</option>');
	$.ajax({
		url: '${pageContext.request.contextPath}/get-performance-objectivesAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){	
					
					$(".performanceObjectivesAjax").append('<li class="info-element">'
					                            +item.description+'.'
					                            +'<div class="agile-detail">'
					                                +'<button type="button" class="pull-right btn btn-xs btn-white">Details</button>'
					                                +'<i class="fa fa-clock-o"></i> '
					                                +'</div>'
					                        +'</li>');
					$(".objectives-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	$("#selectObjective").select2({
	    dropdownParent: $("#newPerformanceTarget")
	  });
	
	//---------------------------------------------------------------------//
	// 	      Data table for strategic plans, goals and objectives
	//--------------------------------------------------------------------//
	
	$('.dataTables-strategic-plans').DataTable({
        dom: '<"html5buttons"B>lTfgitp',
        buttons: [
            { extend: 'copy'},
            {extend: 'csv'},
            {extend: 'excel', title: 'Strategic Plans'},
            {extend: 'pdf', title: 'Strategic Plans'},

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
	
	//-------------------------------------------------------//
	// 	      Retrieve strategic goals by Plan ID Ajax
	//------------------------------------------------------//
	
	var urlID = window.location.href.split('/');
	var planID = urlID.pop() || urlID.pop();
	$.ajax({
		url: '${pageContext.request.contextPath}/get-performance-goals-by-planIdAjax/'+planID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){
					$(".strategicGoals").append('<td><li>'
					                            +item.description+'.'
					                            +'<div class="agile-detail">'
					                                +'<button type="button" class="pull-right btn btn-xs btn-white">Edit</button>'
					                                +'</div>'
					                        +'</li></td>');
				});				
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