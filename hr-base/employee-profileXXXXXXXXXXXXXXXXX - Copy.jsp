<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>HRMS | Employee Profile</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">

<style type="text/css">
/************************************************* 
*******   Attribute 1 Object left margin**********
*************************************************/
.spacer {
	margin-left: 12px;
}

/****************************************** 
*******   Attribute 2 PDF Viewer **********
*******************************************/
* {
	margin: 0;
	padding: 0;
}

.top-bar {
	background: #333;
	color: #fff;
	padding: 1rem;
}

.page-info {
	margin-left: 1rem;
}

.error {
	background: orangered;
	color: #fff;
	padding: 1rem;
}

/********************************************* 
*******   Attribute 3 Modal Xl size **********
*********************************************/
@media ( min-width : 768px) {
	.modal-xl {
		width: 75%;
		max-width: 1200px;
	}
	.modal-title {
		text-align: center;
		font-size: h4;
	}
}
</style>

<style>
.pdfobject-container { height: 70rem; border: 1rem solid rgba(0,0,0,.1); }
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
			    <!-- Constants -->
			    <input type="hidden" id="employeeID" value="${details.id}">
				<!-- Contents Starts -->
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row m-b-lg m-t-lg">
						<div class="col-md-6">
							<div class="profile-image">
								<img src="<c:url value="/resources/img/nodp.png" />"
									alt="No Photo">
							</div>
							<div class="profile-info">
								<div class="">
									<div>
										<h2 class="no-margins">
											<c:out value="${details.salutation}" />
											.
											<c:out value="${details.firstName}" />
											<c:out value="${details.lastName}" />
										</h2>
										<h4>
											<c:out value="${details.designation}" />
											|
											<c:out value="${details.dutystation}" />
										</h4>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-3">
							<table class="table small m-b-xs">
								<tbody>
									<tr>
										<td><strong>Date Of Birth</strong></td>
										<td><c:out value="${details.dob}" /></td>
									</tr>
									<tr>
										<td><strong>Employment Date</strong></td>
										<td><c:out value="${details.dateofemployment}" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="col-md-3">
							<small>Retirement Date</small>
							<h2 class="no-margins">
								<c:out value="${details.dateofretirement}" />
							</h2>
							<div id="sparkline1"></div>
							<button type="button" class="btn btn-xs btn-outline btn-primary"
								data-toggle="modal" data-target="#editPersonalInfo">
								<i class="fa fa-edit"></i> Edit Employee Details
							</button>
						</div>
					</div>
					<div class="row">
						<div class="col-md-8">
							<div class="ibox">
								<div class="ibox-title">
									<h5>Employee Profile</h5>
								</div>

								<!-- Personal Information -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">
											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/personalInfo.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Personal Information </a>
														</h3>
														<dl class="small m-t-md">
															<span class="pull-right">
																<dd>Gender:</dd>
																<dt>
																	<c:out value="${details.gender}" />
																</dt>
															</span>
															<dd>Fullname:</dd>
															<dt>
																<c:out value="${details.firstName}" />
																<c:out value="${details.middleName}" />
																<c:out value="${details.lastName}" />
															</dt>
															<hr>

															<span class="pull-right">
																<dd>Marital Status:</dd>
																<dt>
																	<c:out value="${details.maritalstatus}" />
																</dt>
															</span>
															<dd>Religion:</dd>
															<dt>
																<c:out value="${details.religion}" />
															</dt>
															<hr>

															<span class="pull-right">
																<dd>Passport No.:</dd>
																<dt>
																	<c:out value="${details.passportNo}" />
																</dt>
															</span>
															<dd>National ID No. (NIN):</dd>
															<dt>
																<c:out value="${details.nationalId}" />
															</dt>
															<hr>
														</dl>
													</td>
													<td>
														<button type="button"
															class="btn btn-xs btn-outline btn-primary"
															data-toggle="modal" data-target="#editPersonalInfo">
															<i class="fa fa-edit"></i> Update
														</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<!-- Education Information -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">

											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/education.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Education Information
															</a>
														</h3>
														<div id="vertical-timeline"
															class="vertical-container light-timeline no-margins">
															<c:if test="${not empty educations}">
																<c:forEach var="education" items="${educations}"
																	varStatus="status" begin="0" end="2">
																	<div class="vertical-timeline-block">
																		<div class="vertical-timeline-icon navy-bg">
																			<i class="fa fa-graduation-cap"></i>
																		</div>
																		<div class="vertical-timeline-content">
																			<h4>
																				<c:out value="${education.courseName}" />
																			</h4>
																			<p>
																				<c:out value="${education.instituteName}" />
																			</p>

																			<div class="m-t-sm">
																				<i class="fa fa-clock-o"></i>
																				<c:out value="${education.startYear}" />
																				-- <i class="fa fa-clock-o"></i>
																				<c:out value="${education.endYear}" />
																			</div>
																			<hr>
																			<c:if test="${education.approved == 0}">
																				<ol class="dd-list">
																					<li class="dd-item" data-id="2" data-toggle="modal"
																						id="educationCertificateIDPending" value="http://10.200.222.71:7070${education.attachmentUri}"
																						data-target="#eductationCerificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-warning"><i
																								class="fa fa-spinner"></i></span>
																							<code>Pending</code>
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${education.approved == 1}">
																				<ol class="dd-list">
																					<li class="dd-item" data-toggle="modal"
																						id="educationCertificateIDApproved" value="http://10.200.222.71:7070${education.attachmentUri}"
																						data-target="#eductationCerificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-primary"><i
																								class="fa fa-check"></i></span> Approved
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${education.approved == -1}">
																				<ol class="dd-list">
																					<li class="dd-item" data-id="2" data-toggle="modal"
																						id="educationCertificateIDRejected" value="http://10.200.222.71:7070${education.attachmentUri}"
																						data-target="#eductationCerificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-danger"><i
																								class="fa fa-close"></i></span> Rejected
																						</div>
																					</li>
																				</ol>
																			</c:if>
																		</div>
																	</div>
																	<!--***************** View education Document Modal ************************-->
																	<div class="modal fade bd-example-modal-xl"
																		id="eductationCerificateViewer${status.count}"
																		tabindex="-1" role="dialog" aria-hidden="true">
																		<div class="modal-dialog modal-xl">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal">
																						<span aria-hidden="true">&times;</span><span
																							class="sr-only">Close</span>
																					</button>
																					<h4 class="modal-title">Document Viewer</h4>
																					<c:if
																						test="${education.approved != 0 && education.approvalComment != Null}">
																						<p>
																							<strong style="color: red;">Reasons for Rejection:</strong> ${education.approvalComment}
																						</p>
																					</c:if>
																				</div>
																				<div class="modal-body">
																					<!--********** Document URL Value ************-->
																					<input type="hidden" id="educationDocUrl" value="http://10.200.222.71:7070${education.attachmentUri}">																					
																					<div class="pdfRendering"></div>
																					
																				</div>

																				<div class="modal-footer">
																					<c:if test="${education.approved == 0}">
																						<a
																							href="<c:url value="/approve-education.htm/${details.id}/${education.id}" />"
																							class="btn btn-primary" title="Approve Education"><i
																							class="fa fa-check"></i> Approve</a>
																						<a data-toggle="modal" class="btn btn-danger"
																							href="#rejectEducationCertificate${status.count}">Reject</a>
																					</c:if>
																					<button type="button" class="btn btn-white"
																						data-dismiss="modal">Close</button>
																				</div>
																			</div>
																		</div>
																	</div>

																	<!--****************** Reject education Form ********************-->
																	<div id="rejectEducationCertificate${status.count}"
																		class="modal fade" aria-hidden="true">
																		<div class="modal-dialog">
																			<div class="modal-content">
																				<div class="modal-body">
																					<div class="row">
																						<div class="col-sm-12">
																							<h3 class="m-t-none m-b">Reject Employee
																								Education</h3>
																							<form:form
																								action="${pageContext.request.contextPath}/reject-education.htm/${details.id}/${education.id}"
																								role="form" method="post"
																								modelAttribute="rejectEducation"
																								name="employeeRejectEducation">
																								<div class="form-group">
																									<label>Reasons *</label> <input type="text"
																										name="comment"
																										placeholder="Write Reasons for rejection"
																										class="form-control required">
																								</div>
																								<div>
																									<button
																										class="btn btn-sm btn-primary pull-right m-t-n-xs"
																										type="submit">
																										<strong>Submit</strong>
																									</button>
																									<button type="button"
																										class="btn btn-sm btn-white m-t-n-xs"
																										data-dismiss="modal">Cancel</button>
																								</div>
																							</form:form>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</c:forEach>
															</c:if>
															<c:if test="${empty educations}">
																<div class="vertical-timeline-block">
																	<div class="vertical-timeline-content">
																		<p>
																			<i>No Records</i>
																		</p>
																	</div>
																</div>
															</c:if>
														</div>
													</td>
													<td><a href="#"
														class="btn btn-xs btn-outline btn-link"><i
															class="fa fa-tasks"></i> <i>See All</i> </a></td>

												</tr>
											</tbody>
										</table>
									</div>

								</div>

								<!-- Short Course -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">

											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/book.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Short Courses </a>
														</h3>
														<div id="vertical-timeline"
															class="vertical-container light-timeline no-margins">
															<c:if test="${not empty shortCourses}">
																<c:forEach var="shortCourse" items="${shortCourses}"
																	varStatus="status" begin="0" end="2">
																	<div class="vertical-timeline-block">
																		<div class="vertical-timeline-icon navy-bg">
																			<i class="fa fa-book"></i>
																		</div>
																		<div class="vertical-timeline-content">
																			<h4>
																				<c:out value="${shortCourse.coursename}" />
																			</h4>
																			<p>
																				<c:out value="${shortCourse.institution}" />
																			</p>
																			<div class="m-t-sm">
																				<i class="fa fa-clock-o"></i>
																				<c:out value="${shortCourse.datestart}" />
																				-- <i class="fa fa-clock-o"></i>
																				<c:out value="${shortCourse.datend}" />
																			</div>
																			<hr>
																			<c:if test="${shortCourse.approved == 0}">
																				<ol class="dd-list">
																					<li class="dd-item" id="shortCourseCertificateIDPending" value="${shortCourse.uri}" data-toggle="modal"
																						data-target="#shortCourseCertificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-warning"><i
																								class="fa fa-spinner"></i></span>
																							<code>Pending</code>
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${shortCourse.approved == 1}">
																				<ol class="dd-list">
																					<li class="dd-item" id="shortCourseCertificateIDApproved" value="${shortCourse.uri}" data-toggle="modal"
																						data-target="#shortCourseCertificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-primary"><i
																								class="fa fa-check"></i></span> Approved
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${shortCourse.approved == -1}">
																				<ol class="dd-list">
																					<li class="dd-item" id="shortCourseCertificateIDRejected" value="${shortCourse.uri}" data-toggle="modal"
																						data-target="#shortCourseCertificateViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-danger"><i
																								class="fa fa-close"></i></span> Rejected
																						</div>
																					</li>
																				</ol>
																			</c:if>
																		</div>
																	</div>

																	<!--***************** View short course Document Modal ************************-->
																	<div class="modal inmodal fade"
																		id="shortCourseCertificateViewer${status.count}"
																		tabindex="-1" role="dialog" aria-hidden="true">
																		<div class="modal-dialog  modal-xl">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal">
																						<span aria-hidden="true">&times;</span><span
																							class="sr-only">Close</span>
																					</button>
																					<h4 class="modal-title">Document Viewer</h4>
																					<c:if
																						test="${shortCourse.approved != 0 && shortCourse.approvalComment != Null}">
																						<p>
																							<strong style="color: red;">Reasons for
																								Rejection:</strong> ${shortCourse.approvalComment}
																						</p>
																					</c:if>
																				</div>
																				<div class="modal-body">
																					<div class="top-bar">
																						<button class="btn btn-warning" id="prev-page">
																							<i class="fas fa-arrow-circle-left"></i> Prev
																							Page
																						</button>
																						<button class="btn btn-warning" id="next-page">
																							Next Page <i class="fas fa-arrow-circle-right"></i>
																						</button>
																						<span class="page-info"> Page <span
																							id="page-num"></span> of <span id="page-count"></span>
																						</span>
																					</div>

																					<canvas id="pdf-renderXXX"></canvas>
																				</div>

																				<div class="modal-footer">
																					<c:if test="${shortCourse.approved == 0}">
																						<a
																							href="<c:url value="/approve-short-course.htm/${details.id}/${shortCourse.id}" />"
																							class="btn btn-primary"
																							title="Approve Short course"><i
																							class="fa fa-check"></i> Approve</a>
																						<a data-toggle="modal" class="btn btn-danger"
																							href="#rejectShortCourseCertificate${status.count}">Reject</a>
																					</c:if>
																					<button type="button" class="btn btn-white"
																						data-dismiss="modal">Close</button>
																				</div>
																			</div>
																		</div>
																	</div>

																	<!--****************** Reject short course Form ********************-->
																	<div id="rejectShortCourseCertificate${status.count}"
																		class="modal fade" aria-hidden="true">
																		<div class="modal-dialog">
																			<div class="modal-content">
																				<div class="modal-body">
																					<div class="row">
																						<div class="col-sm-12">
																							<h3 class="m-t-none m-b">Reject Employee
																								Short Course.</h3>
																							<form:form
																								action="${pageContext.request.contextPath}/reject-short-course.htm/${details.id}/${shortCourse.id}"
																								role="form" method="post"
																								modelAttribute="rejectShortCourse"
																								name="employeeRejectShortCourse">
																								<div class="form-group">
																									<label>Reasons *</label> <input type="text"
																										name="comment"
																										placeholder="Write Reasons for rejection"
																										class="form-control required">
																								</div>
																								<div>
																									<button
																										class="btn btn-sm btn-primary pull-right m-t-n-xs"
																										type="submit">
																										<strong>Submit</strong>
																									</button>
																									<button type="button"
																										class="btn btn-sm btn-white m-t-n-xs"
																										data-dismiss="modal">Cancel</button>
																								</div>
																							</form:form>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>

																</c:forEach>
															</c:if>
															<c:if test="${empty shortCourses}">
																<div class="vertical-timeline-block">
																	<div class="vertical-timeline-content">
																		<p>
																			<i>No Records</i>
																		</p>
																	</div>
																</div>
															</c:if>
														</div>
													</td>
													<td><a href="#"
														class="btn btn-xs btn-outline btn-link"><i
															class="fa fa-tasks"></i> <i>See All</i> </a></td>

												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<!-- Certifications -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">

											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/certfication.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Certifications </a>
														</h3>
														<div id="vertical-timeline"
															class="vertical-container light-timeline no-margins">
															<c:if test="${not empty certifications}">
																<c:forEach var="certification" items="${certifications}"
																	varStatus="status" begin="0" end="2">
																	<div class="vertical-timeline-block">
																		<div class="vertical-timeline-icon navy-bg">
																			<i class="fa fa-file"></i>
																		</div>
																		<div class="vertical-timeline-content">
																			<h4>
																				<c:out
																					value="${certification.certificationcategoryname}" />
																			</h4>
																			<p>
																				<c:out value="${certification.institution}" />
																			</p>
																			<div class="m-t-sm">
																				<i class="fa fa-clock-o"></i>
																				<c:out value="${certification.datestart}" />
																				-- <i class="fa fa-clock-o"></i>
																				<c:out value="${certification.datend}" />
																			</div>
																			<hr>
																			<c:if test="${certification.approved == 0}">
																				<ol class="dd-list">
																					<li class="dd-item" id="certificationCertificateIDPending" value="${certification.uri}" data-toggle="modal"
																						data-target="#certificateDocumentViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-warning"><i
																								class="fa fa-spinner"></i></span>
																							<code>Pending</code>
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${certification.approved == 1}">
																				<ol class="dd-list">
																					<li class="dd-item" id="certificationCertificateIDApproved" value="${certification.uri}" data-toggle="modal"
																						data-target="#certificateDocumentViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-primary"><i
																								class="fa fa-check"></i></span> Approved
																						</div>
																					</li>
																				</ol>
																			</c:if>
																			<c:if test="${certification.approved == -1}">
																				<ol class="dd-list">
																					<li class="dd-item" id="certificationCertificateIDRejected" value="${certification.uri}" data-toggle="modal"
																						data-target="#certificateDocumentViewer${status.count}">
																						<div class="dd-handle">
																							<span class="pull-right"><i
																								class="fa fa-file-pdf-o"></i> Certificate </span> <span
																								class="label label-danger"><i
																								class="fa fa-close"></i></span> Rejected
																						</div>
																					</li>
																				</ol>
																			</c:if>
																		</div>
																	</div>

																	<!--***************** View Certification Document Modal ************************-->
																	<div class="modal inmodal fade"
																		id="certificateDocumentViewer${status.count}"
																		tabindex="-1" role="dialog" aria-hidden="true">
																		<div class="modal-dialog  modal-xl">
																			<div class="modal-content">
																				<div class="modal-header">
																					<button type="button" class="close"
																						data-dismiss="modal">
																						<span aria-hidden="true">&times;</span><span
																							class="sr-only">Close</span>
																					</button>
																					<h4 class="modal-title">Document Viewer</h4>
																					<c:if
																						test="${certification.approved != 0 && certification.approvalComment != Null}">
																						<p>
																							<strong style="color: red;">Reasons for
																								Rejection:</strong> ${certification.approvalComment}
																						</p>
																					</c:if>
																				</div>
																				<div class="modal-body">
																					<div class="top-bar">
																						<button class="btn btn-warning" id="prev-page">
																							<i class="fas fa-arrow-circle-left"></i> Prev
																							Page
																						</button>
																						<button class="btn btn-warning" id="next-page">
																							Next Page <i class="fas fa-arrow-circle-right"></i>
																						</button>
																						<span class="page-info"> Page <span
																							id="page-num"></span> of <span id="page-count"></span>
																						</span>
																					</div>

																					<canvas id="pdf-renderXXX"></canvas>
																				</div>

																				<div class="modal-footer">
																					<c:if test="${certification.approved == 0}">
																						<a
																							href="<c:url value="/approve-certification.htm/${details.id}/${certification.id}" />"
																							class="btn btn-primary"
																							title="Approve Short course"><i
																							class="fa fa-check"></i> Approve</a>
																						<a data-toggle="modal" class="btn btn-danger"
																							href="#rejectCertification${status.count}">Reject</a>
																					</c:if>
																					<button type="button" class="btn btn-white"
																						data-dismiss="modal">Close</button>
																				</div>
																			</div>
																		</div>
																	</div>

																	<!--****************** Reject Certification Form ********************-->
																	<div id="rejectCertification${status.count}"
																		class="modal fade" aria-hidden="true">
																		<div class="modal-dialog">
																			<div class="modal-content">
																				<div class="modal-body">
																					<div class="row">
																						<div class="col-sm-12">
																							<h3 class="m-t-none m-b">Reject Employee
																								Certification.</h3>
																							<form:form
																								action="${pageContext.request.contextPath}/reject-certification.htm/${details.id}/${certification.id}"
																								role="form" method="post"
																								modelAttribute="rejectCertification"
																								name="employeeRejectCertification">
																								<div class="form-group">
																									<label>Reasons *</label> <input type="text"
																										name="comment"
																										placeholder="Write Reasons for rejection"
																										class="form-control required">
																								</div>
																								<div>
																									<button
																										class="btn btn-sm btn-primary pull-right m-t-n-xs"
																										type="submit">
																										<strong>Submit</strong>
																									</button>
																									<button type="button"
																										class="btn btn-sm btn-white m-t-n-xs"
																										data-dismiss="modal">Cancel</button>
																								</div>
																							</form:form>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																	</div>
																</c:forEach>
															</c:if>
															<c:if test="${empty certifications}">
																<div class="vertical-timeline-block">
																	<div class="vertical-timeline-content">
																		<p>
																			<i>No Records</i>
																		</p>
																	</div>
																</div>
															</c:if>
														</div>
													</td>
													<td><a href="#"
														class="btn btn-xs btn-outline btn-link"><i
															class="fa fa-tasks"></i> <i>See All</i> </a></td>

												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<!-- Bank Information -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">
											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/bank.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Bank Information </a>
														</h3>
														<table class="table">
															<thead>
																<tr>
																	<th>#</th>
																	<th>Bank</th>
																	<th>Account Number</th>
																	<th>Priority</th>
																	<th>More</th>
																</tr>
															</thead>
															<tbody>
																<c:if test="${not empty banks}">
																	<c:forEach var="bank" items="${banks}"
																		varStatus="status">
																		<tr>
																			<td>${status.count}</td>
																			<td style="text-align: left"><c:out
																					value="${bank.bankName}" /></td>
																			<td style="text-align: left"><c:out
																					value="${bank.accountnumber}" /></td>
																			<td style="text-align: left"><c:out
																					value="${bank.priority}" /></td>
																			<td><a href="#" data-toggle="modal"
																				data-target="#bankDetails">Details <i
																					class="fa fa-angle-down"></i></a></td>
																			<div class="modal inmodal" id="bankDetails"
																				tabindex="-1" role="dialog" aria-hidden="true">
																				<div class="modal-dialog">
																					<div class="modal-content animated flipInY">
																						<div class="modal-header">
																							<button type="button" class="close"
																								data-dismiss="modal">
																								<span aria-hidden="true">&times;</span><span
																									class="sr-only">Close</span>
																							</button>
																							<h4 class="modal-title">Bank Account Details</h4>
																						</div>
																						<div class="modal-body">
																							<h5>Account Details</h5>
																							<ul class="list-group">
																								<li class="list-group-item"><span
																									class="badge badge-primary" id="bankName">${bank.bankName}</span>
																									<strong>BANK NAME:</strong></li>
																								<li class="list-group-item"><span
																									class="badge badge-primary">${bank.accountnumber}</span>
																									<strong>ACCOUNT NUMBER:</strong></li>
																								<li class="list-group-item"><span
																									class="badge badge-primary">${bank.accountname}</span>
																									<strong>ACCOUNT NAME:</strong></li>
																								<li class="list-group-item"><span
																									class="badge badge-primary">${bank.bankbranchname}</span>
																									<strong>BRANCH NAME:</strong></li>
																								<li class="list-group-item"><span
																									class="badge badge-primary">${bank.priority}</span>
																									<strong>PRIORITY:</strong></li>
																								<li class="list-group-item"><span
																									class="badge badge-primary">${bank.amount}</span>
																									<strong>AMOUNT:</strong></li>
																							</ul>
																						</div>
																						<div class="modal-footer">
																							<button type="button" class="btn btn-white"
																								data-dismiss="modal">Close</button>
																							<button type="button" class="btn btn-warning"
																								id="accountDetailsChanger"
																								onclick="updateAccountDetails(this)">Update</button>
																						</div>
																					</div>
																				</div>
																			</div>
																		</tr>
																	</c:forEach>
																</c:if>
																<c:if test="${empty banks}">
																	<tr>
																		<td colspan="5" style='text-align: center;'><i>No
																				Records</i></td>
																	</tr>
																</c:if>
															</tbody>
														</table>
													</td>
													<td>
														<button type="button"
															class="btn btn-xs btn-outline btn-primary"
															data-toggle="modal" data-target="#updateBankInformation">
															<i class="fa fa-plus"></i> Add Account
														</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<!-- Employment Details (Administration) -->
								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">
											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/administration.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Employment Details</a>
														</h3>
														<dl class="small m-t-md">
															<span class="pull-right">
																<dd>Employment Status:</dd>
																<dt>
																	<c:out value="${details.employmentstatus}" />
																</dt>
															</span>
															<dd>Employment Category:</dd>
															<dt>
																<c:out value="${details.employmentcategory}" />
															</dt>
															<hr>
														</dl>
														<div class="m-t-sm">
															<dd>Office Email:</dd>
															| <a href="#" class="text-muted"><i
																class="fa fa-envelope"></i> <c:out
																	value="${details.email}" /></a>
														</div>
													</td>
													<td>
														<button type="button"
															class="btn btn-xs btn-outline btn-primary"
															data-toggle="modal" data-target="#editPersonalInfo">
															<i class="fa fa-edit"></i> Update
														</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<div class="ibox-content">
									<div class="table-responsive">
										<table class="table shoping-cart-table">

											<tbody>
												<tr>
													<td width="90"><img
														src="<c:url value="/resources/img/family.png" />"
														height="50px" alt="info"></td>
													<td class="desc">
														<h3>
															<a href="#" class="text-navy"> Family Information </a>
														</h3>
														<table class="table">
															<thead>
																<tr>
																	<th>#</th>
																	<th>Name</th>
																	<th>Relationship</th>
																	<th>Birthdate</th>
																	<th>Mobile</th>
																</tr>
															</thead>
															<tbody>
																<c:if test="${not empty relatives}">
																	<c:forEach var="relative" items="${relatives}"
																		varStatus="status">
																		<tr>
																			<td>${status.count}</td>
																			<td style="text-align: left"><c:out
																					value="${relative.fullname}" /></td>
																			<td style="text-align: left"><c:out
																					value="${relative.relativecategoryname}" /></td>
																			<td style="text-align: left"><c:out
																					value="${relative.dob}" /></td>
																			<td style="text-align: left"><c:out
																					value="${relative.mobileNo}" /></td>
																		</tr>
																	</c:forEach>
																</c:if>
															</tbody>
														</table>
													</td>
													<td><a href="#"
														class="btn btn-xs btn-outline btn-link"> # </a></td>

												</tr>
											</tbody>
										</table>
									</div>
								</div>

								<div class="ibox-content">

									<button class="btn btn-primary pull-right">
										<i class="fa fa fa-file-pdf-o"></i> Print CV
									</button>
									<a href="<c:url value="/employees.htm" />"
										class="btn btn-white"><i class="fa fa-arrow-left"></i>
										Back to Employees</a>

								</div>
							</div>

						</div>
						<div class="col-md-4">

							<div class="ibox">
								<div class="ibox-title">
									<span class="pull-right salaryScaleCheck"></span>
									<h5>Salary Information</h5>
								</div>
								<div class="ibox-content">
									<span> Basic Salary </span>
									<h3>
										<center> <i class="salaryCurrency"></i> <i class="basicSalary"></i> </center>
									</h3>
									<hr />

									<span class="pull-right">
										<dd class="salaryScale"></dd>
									</span>
									<dt>Salary Scale:</dt>

									<span class="pull-right">
										<dd class="salaryCurrency"></dd>
									</span>
									<dt>Salary Currency:</dt>
									<hr>
									<div class="m-t-sm">
										<a href="#" class="btn btn-block btn-white btn-sm"> More Details</a>
									</div>
								</div>
							</div>

							<div class="ibox">
								<div class="ibox-title">
									<span class="pull-right"><button type="button"
											class="btn btn-xs btn-outline btn-primary"
											data-toggle="modal" data-target="#editPersonalInfo">
											<i class="fa fa-edit"></i> Update
										</button></span>
									<h5>Administration</h5>
								</div>
								<div class="ibox-content">
									<div>
										<dt>Directorate/Unit:</dt>
										<dd>
											<c:out value="${details.unit}" />
										</dd>
									</div>
									<hr />
									<div>
										<dt>Section:</dt>
										<dd>
											<c:out value="${details.section}" />
										</dd>
									</div>
									<hr />
									<div>
										<dt>Supervisor:</dt>
										<dd>
											<c:out value="${details.supervisor}" />
										</dd>
									</div>
									<hr />
									<div>
										<dt>File Number:</dt>
										<dd>
											<c:out value="${details.fileNumber}" />
										</dd>
									</div>
									<hr />
									<div>
										<dt>Card Number:</dt>
										<dd>
											<c:out value="${details.cardNumber}" />
										</dd>
									</div>
								</div>
							</div>

							<div class="ibox">
								<div class="ibox-title">
									<c:if test="${not empty addressCurrent}">
										<span class="pull-right"><button
												class="btn btn-xs btn-outline btn-primary"
												data-toggle="modal" data-target="#editContactInfo">
												<i class="fa fa-edit"></i> Update
											</button></span>
									</c:if>
									<c:if test="${empty addressCurrent}">
										<span class="pull-right"><button
												class="btn btn-xs btn-outline btn-primary"
												data-toggle="modal" data-target="#updateContactInfo">
												<i class="fa fa-edit"></i> Update
											</button></span>
									</c:if>
									<h5>Contact & Address</h5>
								</div>
								<div class="ibox-content">
									<span class="pull-right">
										<dd>
											<c:if test="${not empty addressCurrent}">
												<c:out value="${addressCurrent.contactphoneprimary}" />
											</c:if>
											<c:if test="${empty addressCurrent}">
												<i class="text-navy">No Record</i>
											</c:if>
										</dd>
									</span>
									<dt>MOBILE:</dt>

									<span class="pull-right">
										<dd>
											<c:if test="${not empty addressCurrent}">
												<c:out value="${addressCurrent.contactemailaddress}" />
											</c:if>
											<c:if test="${empty addressCurrent}">
												<i class="text-navy">No Record</i>
											</c:if>
										</dd>
									</span>
									<dt>EMAIL:</dt>

									<span class="pull-right">
										<dd>
											<c:if test="${not empty addressCurrent}">
												<c:out value="${addressCurrent.addressline1}" />
											</c:if>
											<c:if test="${empty addressCurrent}">
												<i class="text-navy">No Record</i>
											</c:if>
										</dd>
									</span>
									<dt>ADDRESS:</dt>

									<span class="pull-right">
										<dd>
											<c:if test="${not empty addressCurrent}">
												<c:out value="${addressCurrent.postalcode}" />
											</c:if>
											<c:if test="${empty addressCurrent}">
												<i class="text-navy">No Record</i>
											</c:if>
										</dd>
									</span>
									<dt>POSTALCODE:</dt>

									<span class="pull-right">
										<dd>
											<c:out value="${details.nationality}" />
										</dd>
									</span>
									<dt>NATIONALITY:</dt>
									<hr>
								</div>
							</div>

							<div class="ibox">
								<div class="ibox-title">
									<span class="pull-right"><a href="#"
										class="btn btn-xs btn-outline btn-link"> # </a></span>
									<h5>Employment History</h5>
								</div>
								<div class="ibox-content">
									<c:if test="${not empty employments}">
										<c:forEach var="employment" items="${employments}"
											varStatus="status">
											<div>
												<dl class="small m-b-none">
													<dt>
														<c:out value="${employment.jobTitle}" />
														at
														<c:out value="${employment.instituteName}" />
													</dt>
													<dd>
														<c:out value="${employment.fromDate}" />
														to
														<c:out value="${employment.toDate}" />
													</dd>
												</dl>
											</div>
											<hr />
										</c:forEach>
									</c:if>
								</div>
							</div>

							<div class="ibox">
								<div class="ibox-title">
									<h5>Leave History</h5>
								</div>
								<div class="ibox-content">
									<div class="leaveHistory">
									</div>
									<div class="m-t-sm">
										<a href="#" class="btn btn-block btn-white btn-sm"> See
											More</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- ./ Contents Ends -->
			</div>
			<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
		</div>

		<!--------------------------------------------------------------------------
	 		Update Forms Section 
 		---------------------------------------------------------------------------->

		<!--**************** Personal Information ********************-->
		<div class="modal inmodal" id="editPersonalInfo" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<form:form
						action="${pageContext.request.contextPath}/update-employee-information.htm"
						role="form" method="post"
						modelAttribute="updateEmployeeInformation"
						name="employeeInformation">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">
								<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
							</button>
							<i class="fa fa-user modal-icon"></i>
							<h4 class="modal-title">Update Personal Information</h4>
							<small class="font-bold">${details.firstName}
								${details.middleName} ${details.lastName}</small>
						</div>
						<div class="modal-body">
							<div class="panel-group" id="accordion">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h5 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseOne">1. Personal Information</a>
										</h5>
									</div>
									<div id="collapseOne" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="form-group">
												<label>Salutation</label>
												<div class="input-group">
													<select name='salutationId'
														data-placeholder="Choose Salutation" class="chosen-select"
														style="width: 505px;">
														<option value="${details.salutationId}">${details.salutation}</option>
														<c:forEach var="salutation" items="${salutations}">
															<option id="salutationId" value="${salutation.id}">${salutation.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<input type="hidden" name="id" value="${details.id}" class="form-control required">
											<div class="form-group">
												<label>Firstname</label> <input type="text" name="firstName"
													value="${details.firstName}" class="form-control required">
											</div>
											<div class="form-group">
												<label>Middlename</label> <input type="text"
													name="middleName" value="${details.middleName}"
													class="form-control required">
											</div>
											<div class="form-group">
												<label>Lastname</label> <input type="text" name="lastName"
													value="${details.lastName}" class="form-control required">
											</div>
											<div class="form-group">
												<label>Date of birth</label> <input type="date" name="dob"
													value="${details.dob}" max="${toDate}"
													class="form-control required">
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseTwo">2. More Details</a>
										</h4>
									</div>
									<div id="collapseTwo" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="form-group">
												<label>Designation</label>
												<div class="input-group">
													<select name='designationId'
														data-placeholder="Choose Designation"
														class="chosen-select" style="width: 505px;">
														<option value="${details.designationId}">${details.designation}</option>
														<c:forEach var="designation" items="${designations}">
															<option id="designationId" value="${designation.id}">${designation.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Directorate/Unit</label>
												<div class="input-group">
													<select name='unitId'
														data-placeholder="Choose Directorate/Unit"
														class="chosen-select" style="width: 505px;">
														<option value="${details.unitId}">${details.unit}</option>
														<c:forEach var="unit" items="${units}">
															<option id="unitId" value="${unit.id}">${unit.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Section</label>
												<div class="input-group">
													<select name='sectionid' data-placeholder="Choose Section"
														class="chosen-select" style="width: 505px;">
														<option value="${details.sectionid}">${details.section}</option>
														<c:forEach var="section" items="${sections}">
															<option id="sectionid" value="${section.id}">${section.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Supervisor</label>
												<div class="input-group">
													<select name='supervisordesignationid'
														data-placeholder="Choose Supervisor" class="chosen-select"
														style="width: 505px;">
														<option value="${details.supervisordesignationid}">${details.supervisor}</option>
														<c:forEach var="supervisor" items="${supervisors}">
															<option id="supervisordesignationid"
																value="${supervisor.id}">${supervisor.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>File Number</label> <input type="text"
													name="fileNumber" value="${details.fileNumber}"
													class="form-control required">
											</div>
											<div class="form-group">
												<label>Card Number</label> <input type="text"
													name="cardNumber" value="${details.cardNumber}"
													class="form-control required">
											</div>
											<div class="form-group">
												<label>Date of Employment</label> <input type="date"
													name="dateofemployment" value="${details.dateofemployment}"
													max="${toDate}" class="form-control required">
											</div>
											<div class="form-group">
												<label>Office Email</label> <input type="email" name="email"
													value="${details.email}" class="form-control required">
											</div>
											<div class="form-group">
												<label>Employment Category</label>
												<div class="input-group">
													<select name='employmentcategoryId'
														data-placeholder="Choose Employment Category"
														class="chosen-select" style="width: 505px;">
														<option value="${details.employmentcategoryId}">${details.employmentcategory}</option>
														<c:forEach var="empCategory" items="${empCategories}">
															<option id="employmentcategoryId"
																value="${empCategory.id}">${empCategory.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Employment Status</label>
												<div class="input-group">
													<select name='employmentstatusid'
														data-placeholder="Choose Employment Status"
														class="chosen-select" style="width: 505px;">
														<option value="${details.employmentstatusid}">${details.employmentstatus}</option>
														<c:forEach var="empStatus" items="${empStatuses}">
															<option id="employmentstatusid" value="${empStatus.id}">${empStatus.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#collapseThree">3. Other Information</a>
										</h4>
									</div>
									<div id="collapseThree" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="form-group">
												<label>Gender</label>
												<div class="input-group">
													<select name='genderid' data-placeholder="Choose Gender"
														class="chosen-select" style="width: 505px;">
														<option value="${details.genderid}">${details.gender}</option>
														<c:forEach var="gender" items="${genders}">
															<option id="genderid" value="${gender.id}">${gender.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Marital Status</label>
												<div class="input-group">
													<select name='maritalstatusId'
														data-placeholder="Choose Marital Status"
														class="chosen-select" style="width: 505px;">
														<option value="${details.maritalstatusId}">${details.maritalstatus}</option>
														<c:forEach var="maritalStatus" items="${maritalStatuses}">
															<option id="maritalstatusId" value="${maritalStatus.id}">${maritalStatus.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Nationality</label>
												<div class="input-group">
													<select name='nationalityId'
														data-placeholder="Choose Nationality"
														class="chosen-select" style="width: 505px;">
														<option value="${details.nationalityId}">${details.nationality}</option>
														<c:forEach var="nationality" items="${nationalities}">
															<option id="nationalityId" value="${nationality.id}">${nationality.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Religion</label>
												<div class="input-group">
													<select name='religionId'
														data-placeholder="Choose Religion" class="chosen-select"
														style="width: 505px;">
														<option value="${details.religionId}">${details.religion}</option>
														<c:forEach var="religion" items="${religions}">
															<option id="religionId" value="${religion.id}">${religion.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>Duty station</label>
												<div class="input-group">
													<select name='dutystationid'
														data-placeholder="Choose Duty station"
														class="chosen-select" style="width: 505px;">
														<option value="${details.dutystationid}">${details.dutystation}</option>
														<c:forEach var="office" items="${offices}">
															<option id="dutystationid" value="${office.id}">${office.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label>National Identification Number (NIN)</label> <input
													type="text" name="nationalId" value="${details.nationalId}"
													class="form-control">
											</div>
											<div class="form-group">
												<label>Passport Number</label> <input type="text"
													name="passportNo" value="${details.passportNo}"
													class="form-control">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							<button type="submit" value="submit" class="btn btn-primary"
								id="submitInformation">Save changes</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<!--**************** Contacts & Address Information ********************-->
		<div class="modal inmodal" id="updateContactInfo" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<!--************** Modal Contents include ***************-->
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<i class="fa fa-user modal-icon"></i>
						<h4 class="modal-title">Update Contact Information</h4>
						<small class="font-bold">${details.firstName}
							${details.middleName} ${details.lastName}</small>
					</div>
					<form:form
						action="${pageContext.request.contextPath}/create-contact-information.htm"
						role="form" method="post" modelAttribute="contactInformation"
						name="contactAdd">
						<div class="modal-body">
							<input type="hidden" id="editContactInfoEmployeeId"
								name="employeeid" value="${details.id}" /> <input type="hidden"
								id="addresstypeid" name="addresstypeid" value="1" />
							<div class="form-group">
								<label>Mobile Number</label> <input type="text"
									name="contactphoneprimary" placeholder="Mobile Number"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Personal Email</label> <input type="email"
									name="contactemailaddress" placeholder="Email"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Postal code</label> <input type="text" name="postalcode"
									placeholder="Postalcode" class="form-control required">
							</div>
							<div class="form-group">
								<label>P. O Box</label> <input type="text" name="addressline1"
									placeholder="P. O Box" class="form-control required">
							</div>
							<div class="form-group">
								<label>City *</label>
								<div class="input-group">
									<select name=adresscityid data-placeholder="Choose City"
										class="chosen-select" style="width: 525px;">
										<option value="-1">Select City</option>
										<c:forEach var="city" items="${cities}">
											<option id="adresscityid" value="${city.id}">${city.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							<button type="submit" value="submit" class="btn btn-primary"
								id="submitAddress">Save changes</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<!--**************** Edit if address Details exists ********************-->
		<div class="modal inmodal" id="editContactInfo" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<i class="fa fa-user modal-icon"></i>
						<h4 class="modal-title">Edit Contact Information</h4>
						<small class="font-bold">${details.firstName}
							${details.middleName} ${details.lastName}</small>
					</div>
					<form:form
						action="${pageContext.request.contextPath}/edit-contact-information.htm"
						role="form" method="post" modelAttribute="editContactInformation"
						name="contactEdit">
						<div class="modal-body">
							<input type="hidden" id="editContactInfoEmployeeId"
								name="employeeid" value="${details.id}" /> <input type="hidden"
								id="addresstypeid" name="addresstypeid" value="1" /> <input
								type="hidden" name="adressid" value="${addressCurrent.adressid}" />
							<input type="hidden" name="contactid"
								value="${addressCurrent.contactid}" />
							<div class="form-group">
								<label>Mobile Number</label> <input type="text"
									name="contactphoneprimary"
									value="${addressCurrent.contactphoneprimary}"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Personal Email</label> <input type="email"
									name="contactemailaddress"
									value="${addressCurrent.contactemailaddress}"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Postal code</label> <input type="text" name="postalcode"
									value="${addressCurrent.postalcode}"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>P. O Box</label> <input type="text" name="addressline1"
									value="${addressCurrent.addressline1}"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>City *</label>
								<div class="input-group">
									<select name=adresscityid data-placeholder="Choose City"
										class="chosen-select" style="width: 525px;">
										<option value="${addressCurrent.adresscityid}">${addressCurrent.adresscityid}</option>
										<c:forEach var="city" items="${cities}">
											<option id="adresscityid" value="${city.id}">${city.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							<button type="submit" value="submit" class="btn btn-primary"
								id="submitAddress">Save changes</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<!--**************** Update Bank account ********************-->
		<div class="modal inmodal" id="updateBankInformation" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<i class="fa fa-user modal-icon"></i>
						<h4 class="modal-title">Edit Bank Information</h4>
						<small class="font-bold">${details.firstName}
							${details.middleName} ${details.lastName}</small>
					</div>
					<form:form
						action="${pageContext.request.contextPath}/add-bank-account.htm"
						role="form" method="post" modelAttribute="employeeBankAccount"
						name="updateBank">
						<div class="modal-body">
							<input type="hidden" name="employeeid" value="${details.id}" />
							<div class="form-group">
								<label>Bank Name *</label>
								<div class="input-group">
									<select name=bankid data-placeholder="Choose Bank"
										class="chosen-select" style="width: 525px;">
										<option value="0">Select Bank</option>
										<c:forEach var="banks" items="${bankList}">
											<option id="bankid" value="${banks.id}">${banks.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label>Account Name *</label> <input type="text"
									id="accountname" name="accountname" placeholder="Account Name"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Account Number *</label> <input type="text"
									id="accountNumber" name="accountnumber"
									placeholder="Account Number" class="form-control required">
							</div>
							<div class="form-group">
								<label>Branch *</label>
								<div class="input-group">
									<select name=bankbranchid data-placeholder="Choose Branch"
										class="chosen-select" style="width: 525px;">
										<option value="0">Select Branch</option>
										<c:forEach var="branch" items="${branches}">
											<option id="bankbranchid" value="${branch.id}">${branch.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label>Priority *</label> <input type="number" min="0" id="priority"
									name="priority" value="0" placeholder="Account Priority"
									class="form-control required">
							</div>
							<div class="form-group">
								<label>Amount *</label> <input type="number" min="0" value="0" id="amount"
									name="amount" placeholder="Amount"
									class="form-control required">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							<button type="submit" value="submit" class="btn btn-primary"
								id="submitBankDetails">Save changes</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		
		<!--**************** Update Salary Information ********************-->
		<div class="modal inmodal" id="updateSalaryInformation" tabindex="-1"
			role="dialog" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content animated bounceInRight">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">
							<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
						</button>
						<i class="fa fa-user modal-icon"></i>
						<h4 class="modal-title">Update Salary Information</h4>
						<small class="font-bold">${details.firstName}
							${details.middleName} ${details.lastName}</small>
					</div>
					<form:form
						action="${pageContext.request.contextPath}/update-salary-details.htm"
						role="form" method="post" modelAttribute="employeeSalaryDetails"
						name="updateSalaryDetails">
						<div class="modal-body">
							<input type="hidden" name="employeeid" value="${details.id}" />
							<div class="form-group">
								<label>Salary Structure *</label>
								<div class="input-group">
									<select name=salarystructureId data-placeholder="Choose Structure"
										class="chosen-select" style="width: 525px;">
										<option value="0">Select Structure</option>
										<c:forEach var="structure" items="${employeesSalStructures}">
											<option id="salarystructureId" value="${structure.id}">${structure.scalename} - ${structure.notch}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label>Currency *</label>
								<div class="input-group">
									<select name=currencyId data-placeholder="Choose Currency"
										class="chosen-select" style="width: 525px;">
										<option value="0">Select Currency</option>
										<c:forEach var="currency" items="${currencies}">
											<option id="currencyId" value="${currency.id}">${currency.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
							<button type="submit" value="submit" class="btn btn-primary"
								id="submitBankDetails">Save changes</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>

		<!--------------------------------------------------------------------------
	 		./Update Forms Section 
 		---------------------------------------------------------------------------->
		<!-- /.page-wrapper -->
	</div>

	<!-- Mainly scripts -->
	<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>

	<!-- validator -->
	<script
		src="<c:url value="/resources/js/plugins/validate/jquery.validate.min.js" />"></script>


	<!-- Peity -->
	<script
		src="<c:url value="/resources/js/plugins/peity/jquery.peity.min.js" />"></script>

	<script src="<c:url value="/resources/js/demo/peity-demo.js" />"></script>

	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>

	<script src="<c:url value="/resources/js/plugins/pace/pace.min.js" />"></script>

	<!-- jQuery UI -->
	<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>

	<!-- PDF Viewer -->
	<script src="<c:url value="/resources/js/pdfObject/pdfobject.js" />"></script>	
<script>																				
	//var docUrl = document.getElementById("educationDocUrl").value;
	PDFObject.embed(docUrl, ".pdfRendering");
</script>

<script type="text/javascript">
function salaryStructureId(structureID){
	$.ajax({
		url: '${pageContext.request.contextPath}/get-salary-structurebyIdAjax/'+structureID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data Received about salary structure");
			}else{
				var salary = data.basicSalary;
				var commaSeparatedNumber = salary.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
				$(".basicSalary").append(commaSeparatedNumber+'/=');
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("ErrorII-"+textStatus+","+errorThrown);
		}
	});
}

$(document).ready(function(){
	var empID = document.getElementById("employeeID").value;	
	$.ajax({
		url: '${pageContext.request.contextPath}/get-employees-salaryStructureAjax/'+empID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data Received about employee salary scale");
			}else{	
				if(data.salaryScale == null){
					$(".salaryScaleCheck").append('<button type="button" class="btn btn-xs btn-outline btn-primary" data-toggle="modal" data-target="#updateSalaryInformation"> <i class="fa fa-edit"></i> Update </button>');
					$(".salaryScale").append('<i>Not Set</i>');
					$(".salaryCurrency").append('<i>Not Set</i>');									
				}else{
					$(".salaryScale").append(data.salaryScale);
					$(".salaryCurrency").append(data.currency);
					var structureID = data.salarystructureId;
					if(structureID){
						salaryStructureId(structureID);
					}					
				}				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	//getting leave history by employee id
	$.ajax({
		url: '${pageContext.request.contextPath}/get-leaveHistoryByEmployeeIdAjax/'+empID,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No data Received on leave history");
			}else{		
				var status = 'Pending';
				$.each(data,function(i,item){					
					if(item.approved==0){
						status = 'Pending';
					}else if(item.approved==1){
						status = 'Approved';
					}else if(item.approved==2){
						status = 'Rejected';
					}else if(item.approved==3){
						status = 'System Cancelled';
					}else if(item.approved==4){
						status = 'Recalled';
					}else{
						status = 'No Status';
					}
					
					$(".leaveHistory").append('<dl class="small m-b-none"><dt class="pull-right">'+status+'</dt><dt>'+item.leavetypename+'</dt><dd><i><strong>From:</strong> '+item.startdate+' - <strong>To:</strong> '+item.enddate+'</i></dd></dl><hr>');
					
					if(i==9){
						return false;
					}
				});	
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("ErrorII-"+textStatus+","+errorThrown);
		}
	});
});
</script>
<script>
function updateAccountDetails(ac){
    var bankName = document.getElementById('bankName').value=ac.innerHTML;
    var accPriority = document.getElementById('priority').value=innerHTML;
    var attr = bankName.innerHTML = '<input value="'+bankName.innerText+'">';

   }
</script>



</body>
</html>