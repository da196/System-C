<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | PDF Document</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<script src='https://cdn.jsdelivr.net/npm/pdfmake@latest/build/pdfmake.min.js'></script>
  <script src='https://cdn.jsdelivr.net/npm/pdfmake@latest/build/vfs_fonts.min.js'></script>
  <!-- html-to-pdfmake file: -->
  <script src="https://cdn.jsdelivr.net/npm/html-to-pdfmake/browser.js"></script>
<style>
.centered-text {
	text-align: center;
}
</style>


</head>
<body>
	<script>
    var val = htmlToPdfmake("
    		<div class="row" id="printableArea">
			<div class="col-md-12">
				<p class="centered-text">
					THE UNITED REPUBLIC OF TANZANIA
					</hp>
				<h3 class="font-bold m-b-xs centered-text">
					TANZANIA COMMUNICATION REGULATORY AUTHORITY</h3>
				<p class="centered-text">ISO 9001:2015
					CERTIFIED</p>
				<p class="centered-text">
					<img src="<c:url value="/resources/img/logo2.png" />" width="100px">
				</p>
				<h4 class="centered-text">TCRA/HR/FORM
					No. 8A</h4>
				<h2 class="centered-text">LEAVE
					APPLICATION FORM</h2>
				<hr>
	
				<h4 class="centered-text">SECTION A:</h4>
				<p>1. Full Name:..................................................................................................</p>
				<p>2. Designation:................................................................................................</p>
				<p>3. Department/Unit:............................................................................................</p>
				<p>4. Type of Leave (Please Tick)</p>
				<pre id="toastrOptions" class="p-m">Annual Leave</pre>
				<p>5. Employment Date:............................................................................................</p>
				<p>6. Any Other leave:............................................................................................</p>
				<p>7. Last leave taken on:.......................................for .........................................days</p>
				<p>8. Leave days due:...........................................as at ............................................</p>
				<hr>
			</div>
		</div>
    		");
    var dd = {content:val};
    pdfMake.createPdf(dd).download();
  	</script>
  	
	<!-- Mainly scripts -->
	<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>

	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>

	<!-- jQuery UI -->
	<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
	<script>
function printDiv(printableAreaXXX) {
    var printContents = document.getElementById(printableAreaXXX).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
}
</script>
</body>