<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | All Strategic Objectives</title>
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
                            <h3>All Strategic Objectives</h3>                            	
	                            <div class="table-responsive">
						           <table class="table table-striped table-bordered table-hover dataTables-strategic-goals" >
							           <thead>
								           <tr>
								               <th width="30px">S/N</th>
								               <th>Description</th>
								               <th>Option</th>
								           </tr>
							           </thead>
							           <tbody class="strategicObjectivesTabularAjax">
							           
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
		$('.dataTables-strategic-goals').DataTable({
		    dom: '<"html5buttons"B>lTfgitp',
		    buttons: [
		        { extend: 'copy'},
		        {extend: 'csv'},
		        {extend: 'excel', title: 'Strategic Goals'},
		        {extend: 'pdf', title: 'Strategic Goals'},
		
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
	
	$.ajax({
		url: '${pageContext.request.contextPath}/all-strategic-objectivesAjax',
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{
				console.log(data);
				$.each(data,function(i,item){
					$(".strategicObjectivesTabularAjax").append('<tr>'+
																'<td>'+ ++i +'</td>'+
																'<td>'+item.description+'</td>'+
																'<td>'+
																'<a href="<c:url value="/detailed-implementation-plan.htm/'+item.goalid+'/'+item.id+'" />" class="btn btn-xs btn-warning custom-margin-right">Implementation Plan</a>'+
																'</td>'+
															'</tr>');
				});	
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