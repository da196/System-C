<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Employee Distribution Per Directorate</title>
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
	                       <h5>Employee Distribution Per Directorate</h5>
	                       <div class="ibox-tools">
	                            <a href="<c:url value="/hr-base-reports.htm"/>" class="btn btn-warning btn-xs" style="color: #fff;"><i class="fa fa-arrow-left"></i> Back To Reports</a>	                            
	                       </div>
	                   </div>
	                   <div class="ibox-content">
	                       <div class="table-responsive">
			                    <table class="table table-striped table-bordered table-hover employee-distribution-per-directorate" >
			                    <thead>
			                    <tr>
			                        <th>S/N</th>
			                        <th>Directorate Name</th>
			                        <th>No. Of staff</th>
			                    </tr>
			                    </thead>
			                    <tbody>
		                    		<c:if test="${not empty empInDirectorates}">
                   					<c:forEach var="empInDirectorate" items="${empInDirectorates}" varStatus="status">
					                    <tr class="gradeX">
					                        <td width="30px">${status.count}</td>
					                        <td>${empInDirectorate.departmentname}</td>
					                        <td>${empInDirectorate.totalnumber}</td>
					                    </tr>						                    
				                    </c:forEach>					                    	
                    			</c:if>
			                    </tbody>
	                    		<tfoot>
	                    			<tr>
				                    	<th colspan="2" style='text-align:center'><b>TOTAL</b></th>
				                    	<th>${totals}</th>
				                    </tr>	
			                    </tfoot>
			                    </table>
	                        </div>	
	                        <div class="ibox-content">
		                       <div id="theChart" style="width:100%; height:350px;"></div>
		                        <div class="title-action">
			                        <img alt="image" class="img-circle" width="50px" src="<c:url value="/resources/img/logo.png" />" />
			                    </div>
		                    </div>
	                   </div>
	               </div>
	               <input type="hidden" id="DirDistribution" value="${empInDirectorates}">
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
        $('.employee-distribution-per-directorate').DataTable({
            dom: '<"html5buttons"B>lTfgitp',
            buttons: [
                { extend: 'copy'},
                {extend: 'excel', title: 'Employee Distribution Per Directorate'},
                {extend: 'pdf', title: 'Employee Distribution Per Directorate'},

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
function showChart(directorates, staffInDir){
	Highcharts.chart('theChart', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: 'Employee Distribution Per Directorates'
	    },
	    credits: {
	    	enabled: false
	    },
	    xAxis: {
	        categories: directorates,
	        crosshair: true
	    },
	    yAxis: {
	        min: 0,
	        title: {
	            text: 'Number Of Staff'
	        }
	    },
	    tooltip: {
	        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
	        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
	            '<td style="padding:0"><b>{point.y}</b></td></tr>',
	        footerFormat: '</table>',
	        shared: true,
	        useHTML: true
	    },
	    plotOptions: {
	        column: {
	            pointPadding: 0.2,
	            borderWidth: 0
	        }
	    },
	    series: [{
	        name: 'Staff',
	        data: staffInDir

	    }]
	});
}

//DOM ready
$(document).ready(function() {
	// call API to retrieve statistics
	var API_URL = '${pageContext.request.contextPath}/employee-distribution-per-directorateAjax';
	$.ajax({
		url:API_URL,
		type:'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data received");
			}else{
				var directorates = [];
				var staffInDir = [];
				
				$.each(data,function(i,item){
					// push data onto array
					directorates.push(item.departmentname);
					staffInDir.push(item.totalnumber);
				});
				
				if(directorates && staffInDir){
					showChart(directorates, staffInDir);
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

</body>
</html>