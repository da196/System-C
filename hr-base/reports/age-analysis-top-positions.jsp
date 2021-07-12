<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Age Analysis Seniors and Top Positions</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" />" rel="stylesheet">	
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
			   <input type="hidden" id="queryArray" value="${ageDistTopStaffs}"> 
			   <input type="hidden" id="femaleNumber" value="0">              
               <div class="col-lg-12">
	               <div class="ibox float-e-margins" id="printableArea">
	                   <div class="ibox-title">
	                       <h5>Age Analysis Seniors and Top Positions</h5>
	                       <div class="ibox-tools">
	                            <a href="<c:url value="/hr-base-reports.htm"/>" class="btn btn-warning btn-xs" style="color: #fff;"><i class="fa fa-arrow-left"></i> Back To Reports</a>	                            
	                       </div>
	                   </div>
	                   <div class="ibox-content">
	                   		<div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover employeePerDirectorate-datatable" >
			                    <thead>
			                    <tr>
			                        <th>S/N</th>
			                        <th>Age Group</th>
			                        <th>Directors</th>
			                        <th>Heads</th>
			                        <th>Total</th>
			                    </tr>
			                    </thead>
			                    <tbody>
		                    		<c:if test="${not empty ageDistTopStaffs}">
                   					<c:forEach var="ageDistTopStaff" items="${ageDistTopStaffs}" varStatus="status">
					                    <tr class="gradeX">
					                        <td width="30px">${status.count}</td>
					                        <td>${ageDistTopStaff.agegroup}</td>
					                        <td>${ageDistTopStaff.directors}</td>
					                        <td>${ageDistTopStaff.heads}</td>
					                        <td>${ageDistTopStaff.totalinAgeGroup}</td>
					                    </tr>						                    
				                    </c:forEach>					                    	
                    			</c:if>
			                    </tbody>
	                    		<tfoot>
	                    			<tr>
				                    	<th colspan="2" style='text-align:center'><b>TOTAL</b></th>
				                    	<th><b>${directors}</b></th>
				                    	<th><b>${heads}</b></th>
				                    	<th><b>${totals}</b></th>
				                    </tr>	
			                    </tfoot>
			                    </table>
	                        </div>
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

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>


<script>
// show chart
function showChart(groups,directors,heads){
	
	var chart = Highcharts.chart('theChart', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Age Analysis'
	    },
	    credits: {
	    	enabled: false
	    },
	    subtitle: {
	        text: 'Age Analysis Seniors and Top Positions'
	    },
	    legend: {
	        align: 'right',
	        verticalAlign: 'middle',
	        layout: 'vertical'
	    },
	    xAxis: {
	        categories: groups,
	        labels: {
	            x: -10
	        }
	    },
	    yAxis: {
	        allowDecimals: false,
	        title: {
	            text: 'Number of Staff'
	        }
	    },
	    series: [{
	        name: 'Directors',
	        data: directors
	    }, {
	        name: 'Heads',
	        data: heads
	    }],
	    responsive: {
	        rules: [{
	            condition: {
	                maxWidth: 500
	            },
	            chartOptions: {
	                legend: {
	                    align: 'center',
	                    verticalAlign: 'bottom',
	                    layout: 'horizontal'
	                },
	                yAxis: {
	                    labels: {
	                        align: 'left',
	                        x: 0,
	                        y: -5
	                    },
	                    title: {
	                        text: null
	                    }
	                },
	                subtitle: {
	                    text: null
	                },
	                credits: {
	                    enabled: false
	                }
	            }
	        }]
	    }
	});
}
// DOM ready
$(document).ready(function() {
	// call API to retrieve statistics
	var API_URL = '${pageContext.request.contextPath}/age-analysis-top-positionsAjax';
	$.ajax({
		url:API_URL,
		type:'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data received");
			}else{
				var groups = [];
				var directors = [];
				var heads = [];
				// parse data
				$.each(data,function(i,item){
					// push data onto array
					groups.push(item.agegroup);
					directors.push(item.directors);
					heads.push(item.heads);
				});
				// display chart
				if(groups && directors && heads){
					showChart(groups,directors,heads);
				}
			}
		},
		error: function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	// --END
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