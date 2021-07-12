<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HRMS | Training Report</title>
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
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
.input-group-date .input-group-addon.select-input {
	width:100px;
	padding: 0;
}

.input-group-date .input-group-addon.select-input select {
	border: none;
	height: 320px;
}
.input-group-date .input-group-full-width{
	width:100px;
}
/* .table-condensed thead tr:nth-child(2),
.table-condensed tbody {
  display: none
} */
/* .fa-stop-circle{
	color:#ff0000;
} */
.container-run-contribution{
	margin-bottom:16px;
	padding-right:60px;
	margin-top:16px;
}
.container-run-contribution-result{
	margin-top:16px;
}
.run-payroll-contribution-result-value{
	font-weight:bold;
	padding-left:6px;
}
.run-payroll-contribution-result-label{
	padding-left:24px;
}
.btn-group-run-contribution{
	width:100%;
}
.btn-generate-contribution{
	width:50%;
}
.btn-run-contribution{
	width:50%;
}

@media only screen and (min-width: 768px){	
	.ibox-run-contribution-content{
		min-height:432px;
	}
}
.header {
	text-align: center;
}
.text-italic{
	/* font-style:italic; */
}
@media print {
  body * {
    visibility: hidden;
  }
  .section-to-print * {
    visibility: visible;
  }
  /* .section-to-print {
    position: absolute;
    left: 0;
    top: 0;
  } */
  @page :footer { 
        display: none
    } 
  
    @page :header { 
        display: none
    } 
  @page { 
        margin-top: 0; 
        margin-bottom: 0; 
    } 
    body { 
        padding-top: 0px; 
        padding-bottom: 0px ; 
    } 
}
</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
			
			<!-- contribution Report Start Here -->
		    <!-- Page Body starts -->
				<div class="row">
					<div class="col-md-12">
						<div class="ibox float-e-margins ibox-run-contribution">
						
							<div class="ibox-title custom-ibox-title">
								<h5>Training Report</h5>
							</div>
							
							<div class="ibox-content ibox-run-contribution-content">
								<div class="row container-run-contribution">
									<div class="col-md-9">
 										<form:form id="fromTrainingReport" 
												action="${pageContext.request.contextPath}/v1/training-report-run"	
												modelAttribute="trainingReport"
												method="POST" enctype="multipart/form-data">
										 <div class="row">
										 	<div class="col-md-12">
										 			<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Financial Year</label>
					                                    <div class="col-sm-7">
					                                        <select	class="form-control" name="financialyearid"
																			id="financialyearid"></select>
					                                    </div>
													</div>
													
													<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Quarter</label>
					                                    <div class="col-sm-7">
					                                        <select class="form-control"
																			name="quaterid" id="quaterid">
																	<option value="0"></option>
																	<option value="1">Quarter 1</option>
																	<option value="2">Quarter 2</option>
																	<option value="3">Quarter 3</option>
																	<option value="4">Quarter 4</option>
																	</select>
					                                    </div>
													</div>
													
													<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Category</label>
					                                    <div class="col-sm-7">
					                                       <select class="form-control"
																			name="trainingcategoryid" id="trainingcategoryid">
																			<option value="0"></option>
																			</select>
					                                    </div>
													</div>
										 	</div>
										 </div>


											<div class="row">
												<div class="col-md-7 col-md-offset-5">
													<div class="btn-group btn-group-run-contribution btn-align-center" role="group">
													  <button type="button" class="btn btn-primary btn-sm btn-run-contribution" id="btnRunTrainingReport">Generate</button>													 
													</div>
													<div class="sk-spinner sk-spinner-circle" id="spinnerRunContribution">
					                                    <div class="sk-circle1 sk-circle"></div>
					                                    <div class="sk-circle2 sk-circle"></div>
					                                    <div class="sk-circle3 sk-circle"></div>
					                                    <div class="sk-circle4 sk-circle"></div>
					                                    <div class="sk-circle5 sk-circle"></div>
					                                    <div class="sk-circle6 sk-circle"></div>
					                                    <div class="sk-circle7 sk-circle"></div>
					                                    <div class="sk-circle8 sk-circle"></div>
					                                    <div class="sk-circle9 sk-circle"></div>
					                                    <div class="sk-circle10 sk-circle"></div>
					                                    <div class="sk-circle11 sk-circle"></div>
					                                    <div class="sk-circle12 sk-circle"></div>
					                                </div>
												</div>
											</div>
										</form:form> 
										
<%-- 										<form:form id="fromTrainingReport" 
												action="${pageContext.request.contextPath}/v1/training-report-run"	
												modelAttribute="trainingReport"
												target="_blank" method="GET">
												
												 <div class="row">
										 	<div class="col-md-12">
										 			<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Financial Year</label>
					                                    <div class="col-sm-7">
					                                        <select	class="form-control" name="financialyearid"
																			id="financialyearid"></select>
					                                    </div>
													</div>
													
													<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Quarter</label>
					                                    <div class="col-sm-7">
					                                        <select class="form-control"
																			name="quaterid" id="quaterid">
																	<option value="0"></option>
																	<option value="1">Quarter 1</option>
																	<option value="2">Quarter 2</option>
																	<option value="3">Quarter 3</option>
																	<option value="4">Quarter 4</option>
																	</select>
					                                    </div>
													</div>
													
													<div class="form-group row">
												    	<label class="col-sm-2 col-sm-2 control-label text-right">Category</label>
					                                    <div class="col-sm-7">
					                                       <select class="form-control"
																			name="trainingcategoryid" id="trainingcategoryid">
																			<option value="0"></option>
																			</select>
					                                    </div>
													</div>
										 	</div>
										 </div>


											<div class="row">
												<div class="col-md-7 col-md-offset-5">
													<div class="btn-group btn-group-run-contribution btn-align-center" role="group">
													  <button type="submit" value="submit" class="btn btn-primary btn-sm btn-run-contribution" 
													  id="btnRunTrainingReport">Generate</button>													 
													</div>
													<div class="sk-spinner sk-spinner-circle" id="spinnerRunContribution">
					                                    <div class="sk-circle1 sk-circle"></div>
					                                    <div class="sk-circle2 sk-circle"></div>
					                                    <div class="sk-circle3 sk-circle"></div>
					                                    <div class="sk-circle4 sk-circle"></div>
					                                    <div class="sk-circle5 sk-circle"></div>
					                                    <div class="sk-circle6 sk-circle"></div>
					                                    <div class="sk-circle7 sk-circle"></div>
					                                    <div class="sk-circle8 sk-circle"></div>
					                                    <div class="sk-circle9 sk-circle"></div>
					                                    <div class="sk-circle10 sk-circle"></div>
					                                    <div class="sk-circle11 sk-circle"></div>
					                                    <div class="sk-circle12 sk-circle"></div>
					                                </div>
												</div>
											</div>
												
										</form:form> --%>
									</div>
									<div class="col-md-3"><span class="hide hidden">Progress</span></div>
								</div>	
								
							
							<!--Returned contribution Result datatable -->
							
							<!-- Body Report -->
										
											<div class="row">
											<div class="col-md-12">
													<!-- Data Table -->
												<div  id="result"></div>	
											</div>
										</div>
			
						</div>
			           </div>
					</div>
				</div>
			
						</div>
			<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
		</div>
	</div>
	<!-- Modals -->
	<!-- Mainly scripts -->
	<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>
		
	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>

	<!-- jQuery UI -->
	<script
		src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
	<!-- select2 -->
	<script
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
	<!-- formvalidation.io -->
	<script
			src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
	<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>	
	<!-- date and date picker -->
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>	
	<script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/2.0.6/numeral.min.js"></script>
	
		<script>
	$.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name]) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	</script>
	<!-- Page-Level Scripts -->	
	<script src="<c:url value="/resources/js/utils/DateUtils.js" />"></script>

	<script>
	function showDatePicker(target){
		target.click();
	}
	</script>
	<script>
	$(document).ready(function(){
		$('#container-run-contribution-result').hide();
		$('#spinnerRunContribution').hide();
		
		
		// pull financial year sponsor
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-financial-year-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	
    					$("#financialyearid").append('<option value="'+item.id+'">'+item.description+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	}); 
		
		
    	// pull training category
    	$.ajax({
    		url: '${pageContext.request.contextPath}/get-training-category-listAjax',
    		type: 'GET',
    		success:function(data,textStatus,jqXHR){
    			if(!data){
    				alert("No Data Received");
    			}else{								
    				$.each(data,function(i,item){	    					
    					$("#trainingcategoryid").append('<option value="'+item.id+'">'+item.name+'</option>');
    				});				
    			}
    		},
    		error:function(jqXHR,textStatus,errorThrown){
    			alert("Error-"+textStatus+","+errorThrown);
    		}
    	});
		
	});
	</script>
	
	<script src="<c:url value="/resources/js/validation/ui_validation_training_report.js" />"></script>	
	
</body>
</html>