<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Gender Distribution by Location</title>
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
               <div class="col-lg-12">
	               <div class="ibox float-e-margins">
	                   <div class="ibox-title">
	                       <h5>Employees Gender Distribution by Geographical Location</h5>
	                       <div class="ibox-tools">
	                            <a href="<c:url value="/hr-base-reports.htm"/>" class="btn btn-warning btn-xs" style="color: #fff;"><i class="fa fa-arrow-left"></i> Back To Reports</a>	                            
	                       </div>
	                   </div>
	                   <div class="ibox-content">
	                       <div class="table-responsive">
		                    <table class="table table-striped table-bordered table-hover employee-distribution-by-location" >
		                    <thead>
		                    <tr>
		                        <th>S/N</th>
		                        <th>Location</th>
		                        <th>Male</th>
		                        <th>Female</th>
		                        <th>Total</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    <c:if test="${not empty distByLocations}">
    						<c:forEach var="distByLocation" items="${distByLocations}" varStatus="status">
		                    	<tr class="gradeX">
			                        <td width="30px">${status.count}</td>
			                        <td><c:out value="${distByLocation.location}"/></td>
			                        <td><c:out value="${distByLocation.malenumber}"/></td>
			                        <td><c:out value="${distByLocation.femalenumber}"/></td>
			                        <td><c:out value="${distByLocation.totallocationnumber}"/></td>
			                    </tr> 
			                </c:forEach>
	       					</c:if>    
		                    </tbody>
		                    <tfoot>
		                    <tr>
		                        <th colspan="2" style='text-align:center;'>TOTAL</th>
		                        <th>${male}</th>
		                        <th>${female}</th>
		                        <th>${total}</th>
		                    </tr>
		                    </tfoot>
		                    </table>
	                       </div>
							
							<!-- Chart Section -->
							<div id="theChart" style="width:100%; height:350px;"></div>
	                        <div class="title-action">
		                        <img alt="image" class="img-circle" width="50px" src="<c:url value="/resources/img/logo.png" />" />
		                    </div>
	                   </div>
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
    $(document).ready(function(){
        $('.employee-distribution-by-location').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'excel', title: 'Employee Distribution By Location'},
                {extend: 'pdf', title: 'Employee Distribution By Location'},

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

<script>
function showChart(offices, maleStaff, femaleStaff){
	console.log(femaleStaff);
	var chart = Highcharts.chart('theChart', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Employee Gender Distribution By Locations'
	    },
	    credits: {
	    	enabled: false
	    },
	    legend: {
	        align: 'right',
	        verticalAlign: 'middle',
	        layout: 'vertical'
	    },
	    xAxis: {
	        categories: offices,
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
	        name: 'Male Staff',
	        data: maleStaff
	    }, {
	        name: 'Female Staff',
	        data: femaleStaff
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

$(document).ready(function(){
	var API_URL = '${pageContext.request.contextPath}/gender-distribution-by-locationAjax';
	
	$.ajax({
		url:API_URL,
		type:'GET',
		success:function(data){
			if(!data){
				alert("No data received");
			}else{
				var offices = [];
				var maleStaff = [];
				var femaleStaff = [];
				
				$.each(data, function(i,item){
					offices.push(item.location);
					maleStaff.push(item.malenumber);
					femaleStaff.push(item.femalenumber);
				});
				
				// display chart
				if(offices, maleStaff, femaleStaff){
					showChart(offices, maleStaff, femaleStaff);
				}
			}
		},
		error:function(jqXHR){
			alert("Error-"+jqXHR.status);
		}
	});
});
</script>

</body>
</html>