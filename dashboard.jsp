<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Dashboard</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
   
   <!-- Toastr style -->
   <link href="<c:url value="/resources/css/plugins/toastr/toastr.min.css" />" rel="stylesheet">
   <script src="https://code.highcharts.com/highcharts.js"></script>
   
   
<!-- Styles -->
<style>
#chartdiv {
  width: 100%;
  height: 80px;
}

.top-dasboard{
 height: 150px;
}

.link-decoration{
	text-decoration: none;
	color: #fff;
}

</style>

</head>
<body>
<div id="wrapper">
	<!-- Side Bar Include -->
	<jsp:include page="includes/sidebar.jsp" />		
	 <div id="page-wrapper" class="gray-bg">
	 
	 <!-- Top navigation Bar -->
	 <jsp:include page="includes/topbar.jsp" />
	 <!-- Top navigation bar ends -->
  
<div class="row">
    <div class="col-lg-3">
        <div class="widget style1">
                <div class="row">
                    <div class="col-xs-4 text-center">
                        <i class="fa fa-forward fa-5x"></i>
                    </div>
                    <div class="col-xs-8 text-right">
                        <span> Upcoming </span>
                        <h2 class="font-bold">Events</h2>
                    </div>
                </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="widget style1 navy-bg">
            <div class="row">
            	<a href="<c:url value="/leaves.htm" />" class="link-decoration">
                 <div class="col-xs-4">
                     <i class="fa fa-suitcase fa-5x"></i>
                 </div>	                    
                 <div class="col-xs-8 text-right">
                     <span> Going for leave (In next 7 Days) </span>
                     <h2 class="font-bold leavesInNext7Days"></h2>
                 </div>
                </a>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="widget style1 lazur-bg">
            <div class="row">
                <div class="col-xs-4">
                    <i class="fa fa-pied-piper-alt fa-5x"></i>
                </div>
                <div class="col-xs-8 text-right">
                    <span> Retiring (In next 6 Months) </span>
                    <h2 class="font-bold">2</h2>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-3">
        <div class="widget style1 yellow-bg">
            <div class="row">
                <div class="col-xs-4">
                    <i class="fa fa-music fa-5x"></i>
                </div>
                <div class="col-xs-8 text-right">
                    <span> Going for training (Next 1 Week)</span>
                    <h2 class="font-bold">12</h2>
                </div>
            </div>
        </div>
    </div>
</div>	
<div class="row  border-bottom white-bg dashboard-header">
    <div class="col-sm-3">
        <h2>Statistical Board</h2>
        <small>Employees distribution per Directorates/Units.</small>
        <ul class="list-group clear-list m-t">
        	<c:if test="${not empty departments}">
              <c:forEach var="department" items="${departments}" varStatus="status" begin="0" end="4">
              	<li class="list-group-item">
                  <span class="pull-right">
                      <c:out value="${department.globalpercentage}"/>
                  </span>
                  <span class="label label-primary"><c:out value="${status.count}"/></span> <c:out value="${department.unitshortname}"/>
              </li>
              </c:forEach>
          </c:if>                  
        </ul>
    </div>
    <div class="col-sm-6">
        <div class="flot-chart dashboard-chart">
            <div id="theChart" style="width:100%; height:200px;"></div>
        </div>
        <div class="row text-left">
            <div class="col-xs-4">
                <div class=" m-l-md">
                <span class="h4 font-bold m-t block"> <c:out value="${typeT.totalnumber}"/></span>
                <small class="text-muted m-b block">All Employees</small>
                </div>
            </div>
            <div class="col-xs-4">
                <span class="h4 font-bold m-t block"><c:out value="${typeM.totalnumber}"/></span>
                <small class="text-muted m-b block">Male Employees</small>
            </div>
            <div class="col-xs-4">
                <span class="h4 font-bold m-t block"><c:out value="${typeF.totalnumber}"/></span>
                <small class="text-muted m-b block">Female Employees</small>
            </div>
        </div>
    </div>
    <div class="col-sm-3">
        <div class="statistic-box">
            <div class="row text-center">
                <div id="genderRatio" style="width:100%; height:200px;"></div>
            </div>
            <div class="m-t">
                <small>The number of men is <strong><c:out value="${typeM.totalpercentage}"/></strong> while women are <strong><c:out value="${typeF.totalpercentage}"/></strong>.</small>
            </div>

        </div>
    </div>
 </div>	
		 
 <div class="wrapper wrapper-content">
	 <!-- Grid dashboard boxes starts -->
	<!-- Hidden values for chart -->
    <input type="hidden" id="maleNo" value="${typeM.totalnumber}">
    <input type="hidden" id="femaleNo" value="${typeF.totalnumber}"> 
    
	 <!-- Grid Stats row -->   
	
	     	
<!-- Dashboard report summary -->
<div class="row">
      <div class="col-lg-12">
          <div class="ibox float-e-margins">
              <div class="ibox-title custom-ibox-title">
                  <h5>% Distribution of employees in Directorates and Education levels </h5>
                  <div class="ibox-tools">
                      <a class="collapse-link">
                          <i class="fa fa-chevron-up"></i>
                      </a>
                      <a class="close-link">
                          <i class="fa fa-times"></i>
                      </a>
                  </div>
              </div>
              <div class="ibox-content">

                  <div class="row">
                  <!-- Department wise -->
                     <div class="col-lg-6">
                         <table class="table table-hover margin bottom">
                             <thead>
                             <tr>
                                 <th style="width: 1%" class="text-center">No.</th>
                                 <th>Directorate/Unit</th>
                                 <th class="text-center">Total</th>
                                 <th class="text-center">%</th>
                             </tr>
                             </thead>
                             <tbody>
                             		<c:if test="${not empty departments}">
                             			<c:forEach var="department" items="${departments}" varStatus="status">	
                                <tr>
                                    <td class="text-center"><c:out value="${status.count}" /></td>
                                    <td><c:out value="${department.unitname}" /></td>
                                    <td class="text-center small"><c:out value="${department.totalnumber}" /></td>
                                    <td class="text-center"><span class="label label-primary"><c:out value="${department.globalpercentage}" /></span></td>

                                </tr>
                             </c:forEach>
                            </c:if>
                             </tbody>
                         </table>
                     </div>
                     <!-- Education wise -->
                     <div class="col-lg-6">
                         <table class="table table-hover margin bottom">
                             <thead>
                             <tr>
                                 <th style="width: 1%" class="text-center">No.</th>
                                 <th>Education Level</th>
                                 <th class="text-center">Total</th>
                                 <th class="text-center">%</th>
                             </tr>
                             </thead>
                             <tbody>
                             <c:if test="${not empty eduLevels}">
                             		<c:forEach var="eduLevel" items="${eduLevels}" varStatus="status" begin="0" end="9">
                               <tr>
                                   <td class="text-center"><c:out value="${status.count}" /></td>
                                   <td> <c:out value="${eduLevel.name}" /></td>
                                   <td class="text-center small"><i>Not Set</i></td>
                                   <td class="text-center"><span class="label label-warning"><i>Not Set</i></span></td>		
                               </tr>
                            </c:forEach>   
                       </c:if>        
                             </tbody>
                         </table>
                     </div>
             </div>
             </div>
         </div>
     </div>
 </div>
        	
		 <!-- Grid dashboard boxes ends -->
 		</div> 
		 	<!-- Footer Include -->
			<jsp:include page="includes/footer.jsp" />
	 	</div>
	 	<!-- /.page-wrapper -->
	 	
	 	<!-- Side Tab -->
	 	<div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active"><a data-toggle="tab" href="#tab-1">
                        Notes
                    </a></li>
                    <li><a data-toggle="tab" href="#tab-2">
                        Projects
                    </a></li>
                    <li class=""><a data-toggle="tab" href="#tab-3">
                        <i class="fa fa-gear"></i>
                    </a></li>
                </ul>

                <div class="tab-content">
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> Latest Notes</h3>
                            <small><i class="fa fa-tim"></i> You have 10 new message.</small>
                        </div>
                        <div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="<c:url value="/resources/img/dp.png" />">
                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">

                                        There are many variations of passages of Lorem Ipsum available.
                                        <br>
                                        <small class="text-muted">Today 4:21 pm</small>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div id="tab-2" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-cube"></i> Latest projects</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>

                        <ul class="sidebar-list">
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Business valuation</h4>
                                    It is a long established fact that a reader will be distracted.

                                    <div class="small">Completion with: 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>                           
                        </ul>
                    </div>
                    <div id="tab-3" class="tab-pane">
                        <div class="sidebar-title">
                            <h3><i class="fa fa-gears"></i> Settings</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>
                        <div class="setings-item">
                    <span>
                        Show notifications
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example">
                                    <label class="onoffswitch-label" for="example">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>                        
                    </div>
                </div>

            </div>
        </div>
	 	<!-- /.Side Tab -->
	</div>	
<!--  ajax-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>


<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<script src="<c:url value="/resources/js/plugins/pace/pace.min.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
<script src="<c:url value="/resources/js/plugins/toastr/toastr.min.js" />"></script>

<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/4/core.js"></script>
<script src="https://cdn.amcharts.com/lib/4/charts.js"></script>
<script src="https://cdn.amcharts.com/lib/4/themes/animated.js"></script>

<script type="text/javascript">
function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
}
</script>
<!-- Leave module -->
<script src="<c:url value="/resources/js/modules/leave.js" />"></script>

<!-- Chart code -->
<script>
/* Bar chart employee distribution */
function showEmployeesByAgeChart(ageGroup, totalNumber){
	
Highcharts.chart('theChart', {
    chart: {
        type: 'column'
    },
    title: {
        text: 'Number of Staff by Age Groups'
    },
    credits: {
    	enabled: false
    },
    xAxis: {
        categories: ageGroup,
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
        data: totalNumber

    }]
});
}

/* Pie chart gender ratio */

var male = parseInt(document.getElementById("maleNo").value);
var female = parseInt(document.getElementById("femaleNo").value);

Highcharts.chart('genderRatio', {
	    chart: {
	        plotBackgroundColor: null,
	        plotBorderWidth: null,
	        plotShadow: false,
	        type: 'pie'
	    },
	    title: {
	        text: 'Gender Ratio'
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
	            }
	        }
	    },
	    series: [{
	        name: 'Ratio',
	        colorByPoint: true,
	        data: [{
	            name: 'Men',
	            y: male,
	            sliced: false,
	            selected: true
	        }, {
	            name: 'Women',
	            y: female
	        }]
	    }]
	});
	

/* Welcome toast popup */
$(document).ready(function() {
	/* Toast popup */
	setTimeout(function() {
        toastr.options = {
            closeButton: true,
            progressBar: true,
            showMethod: 'slideDown',
            timeOut: 4000
        };
        toastr.success('HRMS Statistical Dashboard', 'Welcome to TCRA HRMS');

    }, 1300);
	
	// call API to employee distribution by age group
	var API_URL = '${pageContext.request.contextPath}/number-of-staff-by-age-groupsAjax';
	$.ajax({
		url:API_URL,
		type:'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data received");
			}else{
				console.log(data);
				var ageGroup = [];
				var totalNumber = [];
				// parse data
				$.each(data,function(i,item){
					// push data onto array
					ageGroup.push(item.agegroup);
					totalNumber.push(item.totalnumber);
				});
				// display chart
				if(ageGroup, totalNumber){
					showEmployeesByAgeChart(ageGroup, totalNumber);
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