<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <title>HRMS | Leave Reports</title>
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">

    <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">

   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

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
		 <!-- Grid dashboard boxes starts -->
			<div class="row">
				<div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-content">
                            <h3>Leave Reports</h3>
                            <div class="input-group">
                                <input type="text" placeholder="Search Reports. " class="input input-sm form-control">
                                <span class="input-group-btn">
                                        <button type="button" class="btn btn-sm btn-white"> <i class="fa fa-search"></i> Search</button>
                                </span>
                            </div>

                            <ul class="sortable-list connectList agile-list" id="todo">
                                <li class="warning-element">
                                	<a href="<c:url value="/pending-leaves.htm"/>">
	                                    <strong>Pending leaves (Get all leaves that are in pending status)</strong>
	                                    <button class="pull-right btn btn-xs btn-primary">Preview</button>
                                    </a>
                                </li>
                                <li class="success-element">
                                    <a href="<c:url value="/employees-on-leaves.htm"/>">
	                                    <strong>Get all employees on leaves</strong>
	                                    <button class="pull-right btn btn-xs btn-primary">Preview</button>
                                    </a>
                                </li>
                                <li class="danger-element">
                                    <a href="<c:url value="/rejected-leaves.htm"/>">
	                                    <strong>Get all rejected leaves</strong>
	                                    <button class="pull-right btn btn-xs btn-primary">Preview</button>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
			</div>
		 <!-- Grid dashboard boxes ends -->
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

<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />"></script>

<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

</body>
</html>