<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Gender Distribution</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/chosen/chosen.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/switchery/switchery.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/jasny/jasny-bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/datapicker/datepicker3.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/select2/select2.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />" rel="stylesheet">	
   <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
   <script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
	<script src="https://code.highcharts.com/modules/export-data.js"></script>
	<script src="https://code.highcharts.com/modules/accessibility.js"></script>
</head>
<body>
	<div id="wrapper">
		<!-- Side Bar Include -->
		<jsp:include page="../../includes/sidebar.jsp" />		
		 <div id="page-wrapper" class="gray-bg">
		 
		 <!-- Top navigation Bar -->
		 <jsp:include page="../../includes/topbar.jsp" />
		 <!-- Top navigation bar ends -->
		 
	 <div class="wrapper wrapper-content animated fadeInRight">
		 <!-- Report Body -->
			 <div class="row"> 
			   <input type="hidden" id="maleNumber" value="${male}"> 
			   <input type="hidden" id="femaleNumber" value="${female}">              
               <div class="col-lg-12">
	               <div class="ibox float-e-margins" id="printableArea">
	                   <div class="ibox-title">
	                       <h5>Gender Distribution (%)</h5>
	                       <div class="ibox-tools">
	                            <a href="<c:url value="/hr-base-reports.htm"/>" class="btn btn-warning btn-xs" style="color: #fff;"><i class="fa fa-arrow-left"></i> Back To Reports</a>	                            
	                       </div>
	                   </div>
	                   <div class="ibox-content">
                       <div id="theChart" style="width:100%; height:350px;"></div>
                        <div class="title-action">
	                        <img alt="image" class="img-circle" width="50px" src="<c:url value="/resources/img/logo.png" />" />
	                    </div>
	                   </div>
	               </div>
          		 </div>
           </div>
           <div class="row wrapper border-bottom white-bg page-heading">
                <div class="col-lg-4">
                    <h2>Options</h2>
                </div>
                <div class="col-lg-8">
                    <div class="title-action">                        
                        <a onClick="window.location.reload();" class="btn btn-white"><i class="fa fa-refresh "></i> Refresh </a>
                        <a href="#" onclick="printDiv('printableArea')" class="btn btn-primary"><i class="fa fa-print"></i> Print Report </a>
                    </div>
                </div>
            </div>
		 <!-- Report Body ends -->
 		</div> 
		 	<!-- Footer Include -->
			<jsp:include page="../../includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	</div>	
<!-- Mainly scripts -->
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/inspinia.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
<script src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>

<!-- Select2 -->
<!-- <script src="<c:url value="/resources/js/plugins/select2/select2.full.min.js" />"></script> -->

<!-- Datepicker and full Calender -->
<script src="<c:url value="/resources/js/plugins/datapicker/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/plugins/fullcalendar/moment.min.js" />"></script>

<%-- <script src="<c:url value="/resources/js/plugins/pace/pace.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/chosen/chosen.jquery.js" />"></script>
<script src="<c:url value="/resources/js/plugins/jsKnob/jquery.knob.js" />"></script> --%>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/staps/jquery.steps.min.js" />"></script>

<script src="<c:url value="/resources/js/plugins/validate/jquery.validate.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>

<script>
$(function () {
	
});
</script>
<script>
$(document).ready(function() {
	// load chart
	var men = document.getElementById('maleNumber').value;
	men = !isNaN(men)?Number.parseInt(men):0;
	var women = document.getElementById('femaleNumber').value;
	women = !isNaN(women)?Number.parseInt(women):0;
    var chart;
    var genders_arrays = [
        	['men', men],
        	['women', women] 
        ]; 
    Highcharts.chart('theChart', {
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
            type: 'pie'
        },
        title: {
            text: 'Employee Distribution Percentagewise'
        },
        credits: {
            enabled: false
        },
        tooltip: {
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        accessibility: {
            point: {
                valueSuffix: '%'
            }
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                },
                showInLegend: true
            }
        },
        series: [{
            name: 'Ratio',
            colorByPoint: true,
            data: [{
                name: 'Women',
                y: women,
                sliced: true,
                selected: true
            }, {
                name: 'Men',
                y: men
            }]
        }]
    });
});
</script>

<script>
function printDiv(printableArea) {
    var printContents = document.getElementById(printableArea).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
</script>

</body>
</html>