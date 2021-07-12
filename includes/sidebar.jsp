<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page isELIgnored = "false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar-default navbar-static-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav metismenu" id="side-menu">
                <li class="nav-header">   
                	<security:authorize access="isAuthenticated()">              	               
                    <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="<c:url value="/resources/img/usr.png" />" />
                             </span>
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> 
	                            <span class="block m-t-xs"> 
	                            	<strong class="font-bold">
	                            		<security:authentication property="principal.fullname" />
	                            	</strong>
	                            </span> 
	                            <span class="text-muted text-xs block" style="color: #fff;"><strong><security:authentication property="principal.filenumber" /></strong> <b class="caret"></b></span> 
                            </span> 
                       	</a>
                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="#">Profile</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/logout.htm" />">Logout</a></li>
                        </ul>
                    </div>
                    </security:authorize>
                    <div class="logo-element">
                        HRMS
                    </div>
                </li>
                <li>
                    <a href="<c:url value="/dashboard.htm" />"><i class="fa fa-bars"></i> <span class="nav-label">Dashboard</span></a>
                </li>
                <li>
                    <a href="#"><i class="fa fa-cogs"></i> <span class="nav-label">HR Administration </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/add-new-employee.htm" />"><i class="fa fa-plus"></i> Add New Employee</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/employees.htm" />"><i class="fa fa-users"></i> All Employees</a>
                        </li>
                    </ul>
                    
                    <ul class="nav nav-second-level collapse">
                   		 <li>
                            <a href="#"><i class="fa fa-graduation-cap"></i> Educations <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li><a href="<c:url value="/education-certificates.htm" />"><i class="fa fa-university"></i> Education</a></li>
                                <li><a href="<c:url value="/short-courses.htm" />"><i class="fa fa-book"></i> Short Courses</a></li>
                                <li><a href="<c:url value="/certifications.htm" />"><i class="fa fa-certificate"></i> Certifications</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-cogs"></i> Settings <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="<c:url value="/locations.htm" />">Locations (Countries and Cities)</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/designations.htm" />">Designations</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/education-settings.htm" />">Education Settings</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/employment-status.htm" />">Employment Status and Category</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/general-settings.htm" />">General Settings</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/nationalities.htm" />">Nationality</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/organization-offices.htm" />">Organization Offices</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/banks.htm" />">Banks Settings</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/salary-scales.htm" />">Salary and Allowances Settings</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/social-security.htm" />">Social Security</a>
                                </li>
                            </ul>
                        </li>                         
                        <li><a href="<c:url value="/hr-base-reports.htm" />"><i class="fa fa-bar-chart"></i> Reports</a></li>
                    </ul>
                </li>
                <li><a href="#"><i class="fa fa-money"></i> <span
					class="nav-label">Payroll </span><span class="fa arrow"></span></a>
				<ul class="nav nav-second-level collapse">
					<li><a href="#"><i class="fa fa-money"></i> Process
							payroll <span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="<c:url value="/payroll-run.htm" />">Run payroll</a></li>
							<li class="hide hidden"><a href="#">Close period</a></li>
							<li><a href="<c:url value="/payroll-send-payslip.htm" />">Send payslip</a></li>							
							<li class="hide hidden"><a href="<c:url value="#" />">Reports</a></li>							
						</ul></li>
					<li><a href="#"><i class="fa fa-pencil"></i> Transactions
							<span class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="#">Loans <span class="fa arrow"></span></a>
								<ul class="nav nav-fourth-level">
									<li><a href="<c:url value="/payroll-loans.htm" />">Manage Loans</a></li>
									<li><a href="<c:url value="/payroll-add-loan.htm" />">Issue Loan</a></li>
								</ul>
							</li>
							<li><a href="#">Contribution<span class="fa arrow"></span></a>
								<ul class="nav nav-fourth-level">
									<li><a href="<c:url value="/payroll-voluntary-contribution.htm" />">Voluntary</a></li>
									<li class="disabled"><a tabindex="-1" href="#">Mandatory</a></li>
									<li><a href="<c:url value="/payroll-employee-pension-fund.htm" />">Pension</a></li>
									<li><a href="<c:url value="/payroll-employee-health-insurance.htm" />">Insurance</a></li>
								</ul>
							</li>
							<li class="hide hidden"><a href="#">Tax <span class="fa arrow"></span></a>
								<ul class="nav nav-fourth-level">
									<li><a href="<c:url value="/payroll-paye.htm" />">PAYE</a></li>
								</ul>
							</li>
						</ul></li>
					<li><a href="#"><i class="fa fa-cogs"></i> Settings <span
							class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="<c:url value="/payroll-settings.htm" />">Payroll</a>
							</li>
							<li><a href="<c:url value="/payroll-tax-settings.htm" />">Tax</a>
							</li>
							<li><a
								href="<c:url value="/payroll-contribution-settings.htm" />">Contribution</a>
							</li>
							<li><a href="<c:url value="/payroll-loan-settings.htm" />">Loan</a>
							</li>
							<li><a href="<c:url value="/payroll-bank-settings.htm" />">Banks</a>
							</li>
						</ul></li>
					<li><a href="#"><i class="fa fa-bar-chart"></i> Reports <span
							class="fa arrow"></span></a>
						<ul class="nav nav-third-level">
							<li><a href="<c:url value="/payroll-journal.htm" />">Payroll Journal</a></li>
							<li><a href="<c:url value="/payroll-reports-heslb.htm" />">HESLB</a></li>
							<li><a href="<c:url value="/payroll-reports-wcf.htm" />">WCF</a></li>
							<li><a href="<c:url value="/payroll-reports-psssf.htm" />">PSSSF</a></li>
							<li><a href="<c:url value="/payroll-reports-payee.htm" />">PAYEE</a></li>
							<li><a href="<c:url value="/payroll-reports-nhif.htm" />">NHIF</a></li>
						</ul></li>
				</ul></li>
                <li>
                    <a href="#"><i class="fa fa-plane"></i> <span class="nav-label">Leave </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/leaves.htm" />"><i class="fa fa-tasks"></i> Dashboard</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/leave-application.htm" />"><i class="fa fa-calendar-o"></i> Leave Application</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="#"><i class="fa fa-cogs"></i> Settings <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="<c:url value="/leave-types.htm" />">Leave Types</a>
                                </li>
                                <li>
									<a href="<c:url value="/leave-workflow.htm" />">Leave Workflow</a>
									</li>
									<li>
									<a href="<c:url value="/leave-workflow-step.htm" />">Leave Workflow Step</a>
									</li>
									<li>
									<a href="<c:url value="/leave-commutation-workflow.htm" />">Leave Commutation Workflow</a>
									</li>
									<li>
									<a href="<c:url value="/leave-commutation-workflow-step.htm" />">Leave Commutation Workflow Step</a>
									</li>
									
									<li>
									<a href="<c:url value="/leave-recall-workflow.htm" />">Leave Recall Workflow</a>
									</li>
									<li>
									<a href="<c:url value="/leave-recall-workflow-step.htm" />">Leave Recall Workflow Step</a>
									</li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/leave-reports.htm" />"><i class="fa fa-area-chart"></i> Reports</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-book"></i> <span class="nav-label">Training </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/training.htm" />"><i class="fa fa-file-o"></i> Training Form</a>
                        </li>
                         <li>
                            <a href="<c:url value="/training-report.htm" />"><i class="fa fa-area-chart"></i> Report</a>
                        </li>
                   	</ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="#"><i class="fa fa-cogs"></i> Settings <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="<c:url value="/training-category.htm" />">Category</a>                                   
                                </li>
                                 <li>
                                    <a href="<c:url value="/training-initiator.htm" />">Initiator</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/training-sponsor.htm" />">Sponsor</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/training-type.htm" />">Type</a>
                                </li>
                                
                                <li>
                                    <a href="<c:url value="/training-workflow.htm" />">Workflow</a>
                                </li>
                                 <li>
                                    <a href="<c:url value="/training-workflow-step.htm" />">Workflow Step</a>
                                </li>
                            </ul>
                        
                    </ul>                        
                </li>
                <li>
                    <a href="#"><i class="fa fa-paste"></i> <span class="nav-label">SP and Appraisal </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/performance-plans.htm" />"><i class="fa fa-calendar-o"></i> Strategic Plans</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="#"><i class="fa fa-cogs"></i> SP Setups <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="<c:url value="/all-strategic-goals.htm" />">Strategic Goals</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/all-strategic-objectives.htm" />">Strategic Objectives</a>
                                </li>
                                <li>
                                    <a href="<c:url value="/all-strategic-targets.htm" />">Strategic Targets</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/detailed-objectives.htm" />"><i class="fa fa-calendar-o"></i> Implementation Plan</a>
                        </li>
                    </ul>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="<c:url value="/implementation-plan.htm" />"><i class="fa fa-cog"></i> Plans Setups</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="fa fa-sitemap"></i> <span class="nav-label">Administration </span><span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level collapse">
                        <li>
                            <a href="#"><i class="fa fa-tasks"></i> Transactions <span class="fa arrow"></span></a>
                            <ul class="nav nav-third-level">
                                <li>
                                    <a href="<c:url value="#" />">Administration</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="/hrms"><i class="fa fa-line-chart"></i> <span class="nav-label">Reports</span></a>
                </li>
            </ul>

        </div>
    </nav>