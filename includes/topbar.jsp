<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page isELIgnored = "false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!-- Top navigation Bar -->
<div class="row border-bottom">
	<nav class="navbar navbar-static-top white-bg" role="navigation" style="margin-bottom: 0">
	<div class="navbar-header">
	    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
	</div>
    <ul class="nav navbar-top-links navbar-right">
        <li>
            <span class="m-r-sm text-muted welcome-message">HUMAN RESOURCE MANAGEMENT SYSTEM</span>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                <i class="fa fa-envelope"></i>  <span class="label label-warning">-</span>
            </a>
            <ul class="dropdown-menu dropdown-messages">
                <li>
                    <div class="dropdown-messages-box">
                        <a href="profile.html" class="pull-left">
                            <img alt="image" class="img-circle" src="<c:url value="/resources/img/dp.png" />">
                        </a>
                        <div>
                            <small class="pull-right">Time</small>
                            <strong>No Message</strong> <br>
                            <small class="text-muted">Contents to be displayed here</small>
                        </div>
                    </div>
                </li>
                <li class="divider"></li>
               
                <li>
                    <div class="text-center link-block">
                        <a href="#">
                            <i class="fa fa-envelope"></i> <strong>Read All Messages</strong>
                        </a>
                    </div>
                </li>
            </ul>
        </li>
        <li class="dropdown">
            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                <i class="fa fa-bell"></i>  <span class="label label-primary">-</span>
            </a>
            <ul class="dropdown-menu dropdown-alerts">
                <li>
                    <a href="mailbox.html">
                        <div>
                            <i class="fa fa-envelope fa-fw"></i> Notifications Here
                            <span class="pull-right text-muted small">Time</span>
                        </div>
                    </a>
                </li>
                <li class="divider"></li>              
                <li>
                    <div class="text-center link-block">
                        <a href="#">
                            <strong>All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                </li>
            </ul>
        </li>


        <li>
            <a href="<c:url value="/logout.htm" />">
                <i class="fa fa-sign-out"></i> Log out
            </a>
        </li>
        <li>
            <a class="right-sidebar-toggle">
                <i class="fa fa-tasks"></i>
            </a>
        </li>
    </ul>	
	</nav>
</div>
<!-- Top navigation bar ends -->