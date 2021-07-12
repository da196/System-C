<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Implementation Plans</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   	<link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">	
	<link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />	
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <!-- ajax -->

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
		 
	 <div class="wrapper wrapper-content">
		 <!-- Page Body starts -->
			<div class="row">
				<div class="col-lg-12">
                    <div class="tabs-container">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#tab-1"> <i class="fa fa-map-marker"></i> Outcomes</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-2"> <i class="fa fa-map-marker"></i> Activities</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-3"> <i class="fa fa-map-marker"></i> Outputs</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-4"> <i class="fa fa-map-marker"></i> Targets</a></li>
                            <li class=""><a data-toggle="tab" href="#tab-5"> <i class="fa fa-map-marker"></i> Activity Responsibles</a></li>
                        </ul>
                        <div class="tab-content">
                        
                           <!--****** Objectives Outcome ********-->
                            <div id="tab-1" class="tab-pane active">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newObjectiveOutcome">+ Add Outcome</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-outcomes" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody class="objective-outcomes">
				                    	
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
                                </div>
                            </div>
                            
                            <!--****** Outcome Activities ********-->
                            <div id="tab-2" class="tab-pane">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newOutcomeActivity">+ Add Activity</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-activities" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody class="objective-activities">
				                    	
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
                                </div>
                            </div>  
                            
                            <!--******  Activity Outputs ********-->
                            <div id="tab-3" class="tab-pane">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newActivityOutput">+ Add Output</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-outputs" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody class="activity-outputs">
				                    	
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Description</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
                                </div>
                            </div>
                            
                            <!--******  Outputs Targets ********-->
                            <div id="tab-4" class="tab-pane">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newOutputTarget">+ Add Target</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-targets" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Target</th>
					                        <th>Description</th>
					                        <th>Key Performance Indicators</th>
					                        <th>Timeline</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody class="output-targets">
				                    	
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Target</th>
					                        <th>Description</th>
					                        <th>Key Performance Indicators</th>
					                        <th>Timeline</th>
					                        <th>Actions</th>
					                    </tr>
					                    </tfoot>
					                    </table>
			                        </div>
                                </div>
                            </div>  
                            
                            <!--******  Targets Responsible ********-->
                            <div id="tab-5" class="tab-pane">
                                <div class="panel-body">
                                	<p align="right">
			                            <button type="button" class="btn btn-outline btn-primary btn-xs" data-toggle="modal" data-target="#newResponsible">+ Add Responsible</button>
			                        </p>
                                    <div class="table-responsive">
					                    <table class="table table-striped table-bordered table-hover dataTables-responsible" >
					                    <thead>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Target</th>
					                        <th>Responsible Unit</th>
					                        <th>Actions</th>
					                    </tr>
					                    </thead>
					                    <tbody class="target-responsible">
				                    	
					                    </tbody>
					                    <tfoot>
					                    <tr>
					                        <th>S/N</th>
					                        <th>Target</th>
					                        <th>Responsible Unit</th>
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
			
			<!--*********************** Modal forms section ************************-->
			<!-- New outcome Modal -->
            <div class="modal inmodal" id="newObjectiveOutcome" tabindex="-1" role="dialog" aria-hidden="true">
            	<div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Add Objective Outcome</h4>
                            </div>
                             <form:form action="${pageContext.request.contextPath}/add-objective-outcomeAjax" role="form" 
                             method="post" class="form-horizontal" id="newOutcome">
			                         <div class="modal-body">		                             
			                                <div class="form-group"><label class="col-lg-2 control-label">Objective</label>
			
			                                    <div class="col-lg-10"><select class="objectives-via-ajax" id="selectObjective" name="objectiveid" style="width: 100%"></select>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
			
			                                    <div class="col-lg-10"><textarea name="description" id="OutcomeDecription" placeholder="Outcome Decription" rows="3" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	                             
			                         </div>
			                         <div class="modal-footer">
			                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                             <button type="button" id="btnSaveOutcome" value="submit" class="btn btn-primary">Save Details</button>
			                         </div>
			                 </form:form>    
                        </div>
                </div>
             </div>
            <!-- ./ New outcome Modal -->
            
            <!-- New activity Modal -->
            <div class="modal inmodal" id="newOutcomeActivity" tabindex="-1" role="dialog" aria-hidden="true">
            	<div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Add Outcome Activity</h4>
                            </div>
                             <form:form action="${pageContext.request.contextPath}/add-objective-activityAjax" role="form" 
                             method="post" class="form-horizontal" id="newActivity">
			                         <div class="modal-body">		                             
			                                <div class="form-group"><label class="col-lg-2 control-label">Outcome</label>
			
			                                    <div class="col-lg-10"><select class="outcomes-via-ajax" id="selectOutcome" name="outcomeid" style="width: 100%"></select>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
			
			                                    <div class="col-lg-10"><textarea name="description" id="ActivityDecription" placeholder="Activity Decription" rows="3" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	                             
			                         </div>
			                         <div class="modal-footer">
			                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                             <button type="button" id="btnSaveActivity" value="submit" class="btn btn-primary">Save Details</button>
			                         </div>
			                 </form:form>    
                        </div>
                </div>
             </div>
            <!-- ./ New activity Modal -->
            
            <!-- New output Modal -->
            <div class="modal inmodal" id="newActivityOutput" tabindex="-1" role="dialog" aria-hidden="true">
            	<div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Add Activity Output</h4>
                            </div>
                             <form:form action="${pageContext.request.contextPath}/add-activity-outputAjax" role="form" 
                             method="post" class="form-horizontal" id="newOutput">
			                         <div class="modal-body">		                             
			                                <div class="form-group"><label class="col-lg-2 control-label">Activity</label>
			
			                                    <div class="col-lg-10"><select class="activities-via-ajax" id="selectActivity" name="activityid" style="width: 100%"></select>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
			
			                                    <div class="col-lg-10"><textarea name="description" id="OutputDecription" placeholder="Output Decription" rows="3" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	                             
			                         </div>
			                         <div class="modal-footer">
			                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                             <button type="button" id="btnSaveOutput" value="submit" class="btn btn-primary">Save Details</button>
			                         </div>
			                 </form:form>    
                        </div>
                </div>
             </div>
            <!-- ./ New output Modal -->
            
            <!-- New target Modal -->
            <div class="modal inmodal" id="newOutputTarget" tabindex="-1" role="dialog" aria-hidden="true">
            	<div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Add Output Target</h4>
                            </div>
                             <form:form action="${pageContext.request.contextPath}/add-output-targetAjax" role="form" 
                             method="post" class="form-horizontal" id="newTarget">
			                         <div class="modal-body">		                             
			                                <div class="form-group"><label class="col-lg-2 control-label">Output*</label>
			
			                                    <div class="col-lg-10"><select class="outputs-via-ajax" id="selectOutput" name="outputid" style="width: 100%"></select>
			                                    </div>
			                                </div>
			                                <div class="form-group"><label class="col-lg-2 control-label">Target*</label>
			
			                                    <div class="col-lg-10"><textarea name="target" id="target" placeholder="Target" rows="1" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Description</label>
			
			                                    <div class="col-lg-10"><textarea name="description" id="TargetDecription" placeholder="Target Decription" rows="3" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>
			                                <div class="form-group"><label class="col-lg-2 control-label">Key Performance Indicator*</label>
			
			                                    <div class="col-lg-10"><textarea name="keyperformanceindicator" id="keyPerformanceIndicator" placeholder="Key Performance Indicator" rows="2" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Timeline*</label>
			
			                                    <div class="col-lg-10"><textarea name="timeline" id="timeline" placeholder="Timeline" rows="1" style="resize: vertical;" class="form-control"></textarea>
			                                    </div>
			                                </div>	                             
			                         </div>
			                         <div class="modal-footer">
			                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                             <button type="button" id="btnSaveTarget" value="submit" class="btn btn-primary">Save Details</button>
			                         </div>
			                 </form:form>    
                        </div>
                </div>
             </div>
            <!-- ./ New target Modal -->
            
            <!-- New responsible Modal -->
            <div class="modal inmodal" id="newResponsible" tabindex="-1" role="dialog" aria-hidden="true">
            	<div class="modal-dialog">
                    <div class="modal-content animated bounceInRight">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title">Add Target Responsible</h4>
                            </div>
                             <form:form action="${pageContext.request.contextPath}/add-responsibleByAjax" role="form" 
                             method="post" class="form-horizontal" id="newResponsibleForm">
			                         <div class="modal-body">		                             
			                                <div class="form-group"><label class="col-lg-2 control-label">Target</label>
			
			                                    <div class="col-lg-10"><select class="targets-via-ajax" id="selectTarget" name="targetid" style="width: 100%"></select>
			                                    </div>
			                                </div>	
			                                <div class="form-group"><label class="col-lg-2 control-label">Responsible Unit</label>
			
			                                    <div class="col-lg-10"><select class="units-via-ajax" id="selectUnit" name="unitid" style="width: 100%"></select>
			                                    </div>
			                                </div>	                             
			                         </div>
			                         <div class="modal-footer">
			                             <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
			                             <button type="button" id="btnSaveResponsible" value="submit" class="btn btn-primary">Save Details</button>
			                         </div>
			                 </form:form>    
                        </div>
                </div>
             </div>
            <!-- ./ New responsible Modal -->
            
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-serialize-object/2.5.0/jquery.serialize-object.min.js"></script>

<!-- Page-Level Scripts -->
<script>
/******************************************* 
 ***********   Datatables fx(s) ***************
 vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv*/
 
$(document).ready(function(){  
	var outcomesReady = 0;
	function renderOutcomesDatatable(){
		if(outcomesReady==0){
			$('.dataTables-outcomes').DataTable({
		        dom: '<"html5buttons"B>lTfgitp',
		        buttons: [
		            { extend: 'copy'},
		            {extend: 'csv'},
		            {extend: 'excel', title: 'Objectives Outcomes'},
		            {extend: 'pdf', title: 'Objectives Outcomes'},
		
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
			
			$('.dataTables-activities').DataTable({
		        dom: '<"html5buttons"B>lTfgitp',
		        buttons: [
		            { extend: 'copy'},
		            {extend: 'csv'},
		            {extend: 'excel', title: 'Objectives Activities'},
		            {extend: 'pdf', title: 'Objectives Activities'},
		
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
			
			$('.dataTables-outputs').DataTable({
		        dom: '<"html5buttons"B>lTfgitp',
		        buttons: [
		            { extend: 'copy'},
		            {extend: 'csv'},
		            {extend: 'excel', title: 'Activities Outputs'},
		            {extend: 'pdf', title: 'Activities Outputs'},
		
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
			
			$('.dataTables-targets').DataTable({
		        dom: '<"html5buttons"B>lTfgitp',
		        buttons: [
		            { extend: 'copy'},
		            {extend: 'csv'},
		            {extend: 'excel', title: 'Outputs Targets'},
		            {extend: 'pdf', title: 'Outputs Targets'},
		
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
			
			$('.dataTables-responsible').DataTable({
		        dom: '<"html5buttons"B>lTfgitp',
		        buttons: [
		            { extend: 'copy'},
		            {extend: 'csv'},
		            {extend: 'excel', title: 'Targets Responsible'},
		            {extend: 'pdf', title: 'Targets Responsible'},
		
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
		outcomesReady == 1;	
		}
	}
	
	/******************************************************************* 
	 ***********   Objective outcomes POST+GET+DELETE+PUT ***************
	 vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
	
      //*********** Get all objectives by Ajax ************
      $("#selectObjective").select2({
	    dropdownParent: $("#newObjectiveOutcome")
	  });
      
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
	  					$(".objectives-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
	  				});				
	  			}
	  		},
	  		error:function(jqXHR,textStatus,errorThrown){
	  			alert("Error-"+textStatus+","+errorThrown);
	  		}
	  	});
	  	
  	
  	 /************** Post/Add outcome by Ajax ***************/   	  	  
      $("#btnSaveOutcome").click(function(){
      	var form = $('#newOutcome');
          var action = form.attr("action");            
          var data = form.serializeObject();
      	$.ajax({
              url: action,
              dataType: 'json',
              type: 'POST',
              contentType: 'application/json',
              data: JSON.stringify(data),
              
              success: function(data, textStatus, jqXHR){
              	//var status = jqXHR.status;
           	    $.confirm({
           	        title: 'Success!',
           	        content: 'Outcome has been added!',
           	        type: 'green',
           	        typeAnimated: true,
           	        buttons: {
           	            tryAgain: {
           	                text: 'OK',
           	                btnClass: 'btn-green',
           	                action: function(){
           	                	location.reload();
           	                }
           	            }
           	        }
           	    });
              },
              error: function(jqXHR, textStatus, errorThrown){
                  console.log('Error');
              }
      	 });
      	
      });
  	 
      /****** get objective outcome ******/
   	 $("#selectOutcome").select2({
 	    dropdownParent: $("#newOutcomeActivity")
 	  });      
       $(".outcomes-via-ajax").select2();
 	  	$(".outcomes-via-ajax").append('<option value="0">Select Outcome</option>');
       $.ajax({
           url: '${pageContext.request.contextPath}/get-all-objective-outcomeAjax',
           type: 'GET',            
           success: function(data, textStatus, jqXHR){
           	if(!data){
   				alert("No Data Received");
   			}else{
   				$.each(data,function(i,item){	
   					$(".outcomes-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
   					$(".objective-outcomes").append('<tr>'+
   														'<td>'+ ++i +'</td>'+
   														'<td>'+ item.description +'</td>'+
   														'<td>'+
 														'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
 														'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
 														'</td>'+
   													'</tr>'
   												);
   				});	
   				
   				//Outcomes datatable
   				renderOutcomesDatatable();
   			}
           },
           error: function(jqXHR, textStatus, errorThrown){
               console.log('The request said: '+jqXHR.status);
           }
   	 });
       
       /******************************************************************* 
   	 ***********   Outcome activity POST+GET+DELETE+PUT ***************
   	 vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
  	 
      /************** Post/Add Activity by Ajax ***************/   	  	  
      $("#btnSaveActivity").click(function(){
      	var form = $('#newActivity');
          var action = form.attr("action");            
          var data = form.serializeObject();
      	$.ajax({
              url: action,
              dataType: 'json',
              type: 'POST',
              contentType: 'application/json',
              data: JSON.stringify(data),
              
              success: function(data, textStatus, jqXHR){
              	//var status = jqXHR.status;
           	    $.confirm({
           	        title: 'Success!',
           	        content: 'Activity has been added!',
           	        type: 'green',
           	        typeAnimated: true,
           	        buttons: {
           	            tryAgain: {
           	                text: 'OK',
           	                btnClass: 'btn-green',
           	                action: function(){
           	                	location.reload();
           	                }
           	            }
           	        }
           	    });
              },
              error: function(jqXHR, textStatus, errorThrown){
            	  alert('Error while trying to save outcome activities. (Status Code: '+jqXHR.status+')');
              }
      	 });
      	
      });
  	 
  	 
  	
  	 
      /****** get outcome Activities ******/
      $("#selectActivity").select2({
	    dropdownParent: $("#newActivityOutput")
	  });      
      $(".activities-via-ajax").select2();
	  	$(".activities-via-ajax").append('<option value="0">Select Activity</option>');
      $.ajax({
          url: '${pageContext.request.contextPath}/get-all-objective-activitiesAjax',
          type: 'GET',            
          success: function(data, textStatus, jqXHR){
          	if(!data){
  				alert("No Data Received");
  			}else{
  				$.each(data,function(i,item){	
  					$(".activities-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
  					$(".objective-activities").append('<tr>'+
  														'<td>'+ ++i +'</td>'+
  														'<td>'+ item.description +'</td>'+
  														'<td>'+
														'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
														'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
														'</td>'+
  													'</tr>'
  												);
  				});	
  			}
          },
          error: function(jqXHR, textStatus, errorThrown){
        	  alert('Error while trying to retrieve outcome activities. (Status Code: '+jqXHR.status+')');
          }
  	 });
      
/******************************************************************* 
 ***********   Activity output POST+GET+DELETE+PUT ***************
 vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
      
  /************** Post/Add Output by Ajax ***************/   	  	  
      $("#btnSaveOutput").click(function(){
      	var form = $('#newOutput');
          var action = form.attr("action");            
          var data = form.serializeObject();
      	$.ajax({
              url: action,
              dataType: 'json',
              type: 'POST',
              contentType: 'application/json',
              data: JSON.stringify(data),
              
              success: function(data, textStatus, jqXHR){
              	//var status = jqXHR.status;
           	    $.confirm({
           	        title: 'Success!',
           	        content: 'Output has been added!',
           	        type: 'green',
           	        typeAnimated: true,
           	        buttons: {
           	            tryAgain: {
           	                text: 'OK',
           	                btnClass: 'btn-green',
           	                action: function(){
           	                	location.reload();
           	                }
           	            }
           	        }
           	    });
              },
              error: function(jqXHR, textStatus, errorThrown){
            	  alert('Error while trying to save activity output. (Status Code: '+jqXHR.status+')');
              }
      	 });
      	
      });
      
      /****** get Activities Output ******/
      $("#selectOutput").select2({
	    dropdownParent: $("#newOutputTarget")
	  });      
      $(".outputs-via-ajax").select2();
	  	$(".outputs-via-ajax").append('<option value="0">Select Output</option>');
      $.ajax({
          url: '${pageContext.request.contextPath}/get-all-activity-outputsAjax',
          type: 'GET',            
          success: function(data, textStatus, jqXHR){
          	if(!data){
  				alert("No Data Received");
  			}else{
  				$.each(data,function(i,item){	
  					$(".outputs-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
  					$(".activity-outputs").append('<tr>'+
  														'<td>'+ ++i +'</td>'+
  														'<td>'+ item.description +'</td>'+
  														'<td>'+
														'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
														'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
														'</td>'+
  													'</tr>'
  												);
  				});	
  			}
          },
          error: function(jqXHR, textStatus, errorThrown){
              alert('Error while trying to retrieve all activity outputs. (Status Code: '+jqXHR.status+')');
          }
  	 });
      
 /******************************************************************* 
  ***********   Output targets POST+GET+DELETE+PUT ***************
  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
            
    /************** Post/Add target by Ajax ***************/   	  	  
        $("#btnSaveTarget").click(function(){
        	var form = $('#newTarget');
            var action = form.attr("action");            
            var data = form.serializeObject();
            
        	$.ajax({
                url: action,
                dataType: 'json',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                
                success: function(data, textStatus, jqXHR){
                	//var status = jqXHR.status;
             	    $.confirm({
             	        title: 'Success!',
             	        content: 'Target has been added!',
             	        type: 'green',
             	        typeAnimated: true,
             	        buttons: {
             	            tryAgain: {
             	                text: 'OK',
             	                btnClass: 'btn-green',
             	                action: function(){
             	                	location.reload();
             	                }
             	            }
             	        }
             	    });
                },
                error: function(jqXHR, textStatus, errorThrown){
                  $.alert({
                      title: 'Status Code: '+jqXHR.status,
                      content: 'Error while trying to save output target!',
                  });
                }
        	 });
        	
        });  
 
        /****** get Output Targets ******/
        $("#selectTarget").select2({
  	    dropdownParent: $("#newResponsible")
  	  		});      
        $(".targets-via-ajax").select2();
  	  	$(".targets-via-ajax").append('<option value="0">Select Target</option>');
        $.ajax({
            url: '${pageContext.request.contextPath}/get-all-outputs-targetsAjax',
            type: 'GET',            
            success: function(data, textStatus, jqXHR){
            	if(!data){
    				alert("No Data Received");
    			}else{
    				$.each(data,function(i,item){	
    					$(".targets-via-ajax").append('<option value="'+item.id+'">'+item.description+'</option>');
    					$(".output-targets").append('<tr>'+
    														'<td>'+ ++i +'</td>'+
    														'<td>'+ item.target +'</td>'+
    														'<td>'+ item.description +'</td>'+
    														'<td>'+ item.keyperformanceindicator +'</td>'+
    														'<td>'+ item.timeline +'</td>'+
    														'<td>'+
  														'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
  														'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
  														'</td>'+
    													'</tr>'
    												);
    				});	
    			}
            },
            error: function(jqXHR, textStatus, errorThrown){
            	$.alert({
                    title: 'Status Code: '+jqXHR.status,
                    content: 'Error while trying to retrieve output target!',
                });
            }
    	 });
        
        
 /******************************************************************* 
  ***********   Responsible POST+GET+DELETE+PUT ***************
  vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv */
            
    /************** Post/Add responsible by Ajax ***************/   	  	  
        $("#btnSaveResponsible").click(function(){
        	var form = $('#newResponsibleForm');
            var action = form.attr("action");            
            var data = form.serializeObject();
            
        	$.ajax({
                url: action,
                dataType: 'json',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                
                success: function(data, textStatus, jqXHR){
             	    $.confirm({
             	        title: 'Success!',
             	        content: 'Responsible has been added!',
             	        type: 'green',
             	        typeAnimated: true,
             	        buttons: {
             	            tryAgain: {
             	                text: 'OK',
             	                btnClass: 'btn-green',
             	                action: function(){
             	                	location.reload();
             	                }
             	            }
             	        }
             	    });
                },
                error: function(jqXHR, textStatus, errorThrown){
                  $.alert({
                      title: 'Status Code: '+jqXHR.status,
                      content: 'Error while trying to add new responsible!',
                  });
                }
        	 });
        	
        });  
 
 /* Get/retrieve responsible */
    $.ajax({
        url: '${pageContext.request.contextPath}/get-all-responsibleByAjax',
        type: 'GET',            
        success: function(data, textStatus, jqXHR){
        	if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){	
					$(".target-responsible").append('<tr>'+
														'<td>'+ ++i +'</td>'+
														'<td>'+ item.targetid +'</td>'+
														'<td>'+ item.unitid +'</td>'+
														'<td>'+
												'<a href="#" class="btn btn-xs btn-info custom-margin-right">Edit</a>'+
												'<button class="btn btn-xs btn-danger" onClick="confirmDelete('+item.id+');">Delete</button>'+
												'</td>'+
													'</tr>'
												);
				});	
			}
        },
        error: function(jqXHR, textStatus, errorThrown){
        	$.alert({
                title: 'Status Code: '+jqXHR.status,
                content: 'Error while trying to retrieve responsible!',
            });
        }
	 });
 
 	/* retrieve all units */
 	$("#selectUnit").select2({
  	    dropdownParent: $("#newResponsible")
  	  		});      
        $(".units-via-ajax").select2();
  	  	$(".units-via-ajax").append('<option value="0">Select Unit</option>');
    $.ajax({
        url: '${pageContext.request.contextPath}/get-all-unitsByAjax',
        type: 'GET',            
        success: function(data, textStatus, jqXHR){
        	if(!data){
				alert("No Data Received");
			}else{
				$.each(data,function(i,item){	
					$(".units-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});	
			}
        },
        error: function(jqXHR, textStatus, errorThrown){
        	$.alert({
                title: 'Status Code: '+jqXHR.status,
                content: 'Error while trying to retrieve units!',
            });
        }
	 });
        
       	
});
    
    
</script>
</body>
</html>