<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored = "false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>HRMS | Login</title>
   <base href="/" />
   <link rel="icon" href="<c:url value="/resources/img/logo2.png" />" type="image/x-icon">
   <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
   <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type = "text/css" />
   <link href="<c:url value="/resources/font-awesome/css/font-awesome.css" />" rel="stylesheet" type = "text/css" />
   <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet" type = "text/css" />
   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type = "text/css" />
   <link href="<c:url value="/resources/js/plugins/sweetalert/sweetalert.css" />" rel="stylesheet" type = "text/css" />
   <style>
   $base-color: #0071f6;
	
	@keyframes introduceBadge {
	  0% {
	    opacity: 0;
	  }
	  100% {
	    opacity: 1;
	  }
	}
	
	@keyframes pulseBadge {
	  0% {
	    transform: scale(1)
	  }
	  
	  50% {
	    transform: scale(1.05)
	  }
	  
	  100% {
	    transform: scale(1)
	  }
	}
	
	@keyframes pulseBadge2 {
	  0% {
	    transform: scale(1)
	  }
	  
	  50% {
	    transform: scale(1.1)
	  }
	  
	  100% {
	    transform: scale(1)
	  }
	}
	
	.badge {
	  animation: introduceBadge 1s linear 0s 1 both;
	  /* background: rgba($base-color, 0.2); */
	  /* background: #fff; */
	  background: none;
	  border-radius: 50%;
	  height: 136px;
	  /* left: 25%; */
	  bottom: 10px;
      padding: 20px;
	  position: relative;
	  width: 136px;
	
	  &:before,
	  &:after {
	    animation: pulseBadge 3s cubic-bezier(0.860, 0.000, 0.070, 1.000) 0s infinite both;
	    border: 2px dashed $base-color;
	    border-radius: inherit;
	    bottom: -16px;
	    content: "";
	    left: -16px;
	    opacity: 0.2;
	    position: absolute;
	    right: -16px;
	    top: -16px;
	  }
	
	  &:after {
	    animation-name: pulseBadge2;
	    bottom: -32px;
	    left: -32px;
	    opacity: 0.1;
	    right: -32px;
	    top: -32px;
	  }
	}
	
	@keyframes introduceLabel {
	  0% {
	    opacity: 0;
	    transform: translate(-50%, -50%) scale(0.4) rotateY(-1800deg);
	  }
	  100% {
	    opacity: 1;
	    transform: translate(-50%, -50%) scale(1) rotateY(20deg);
	  }
	}
	
	@keyframes rotateLabel {
	  0% {
	    transform: translate(-50%, -50%) rotateY(20deg);
	  }
	
	  50% {
	    transform: translate(-50%, -50%) rotateY(-20deg);
	  }
	
	  100% {
	    transform: translate(-50%, -50%) rotateY(20deg);
	  }
	}
	
	.badge__label {
	  animation: introduceLabel 2s cubic-bezier(0.19, 1, 0.22, 1) 1s 1 both,
	    rotateLabel 5s linear 3s infinite;
	  color: $base-color;
	  font: 900 88px/1 -apple-system, BlinkMacSystemFont;
	  left: 60%;
	  position: absolute;
	  text-align: center;
	  text-shadow: 0px 4px 8px rgba($base-color, 0.2);
	  top: 50%;
	  transform: translate(-50%, -50%);
	}
	
	/* badge */
	.app-download {
      display: flex;
      justify-content: space-around;
      align-items: center;
      direction: rtl;
      background-color: #0071f6;
      color: #fff;
      top: 0;
      position: fixed;
      flex-direction: column;
      z-index: 100;
      left: 2rem;
      border-bottom-right-radius: 50%;
      border-bottom-left-radius: 50%;
      box-shadow: 1px 2px 14px 5px #00000070;
      padding: 1rem;
      &:focus {
        outline: none;
      }
      label{
        margin-top: .5rem;
        margin-bottom: .5rem;
        color: #fff;
        cursor: pointer;
        &#first-title {
          font-size: 1rem;
        }  
        &#second-title{
          font-size:.8rem;
        }
      }
      
      .fa-mobile-phone {
        color: #fff;
        font-size: 2.5rem;
        width: 45px;
        height: 45px;
        display: flex;
        border: 2px dotted #fff;
        align-items: center;
        justify-content: center;
        border-radius: 50%;
        margin: .5rem 0;
        cursor: pointer;
      }
      .close-download-app {
        color: #fff;
        margin-top: .5rem;
        font-size: .7rem;
        cursor: pointer;
      }
    }

.bounce {
  animation: bounce 0.5s;
  animation-direction: alternate;
  animation-timing-function: cubic-bezier(.5, 0.05, 1, .5);
  animation-iteration-count: infinite;
  -webkit-animation-name: bounce;
  -webkit-animation-duration: 0.5s;
  -webkit-animation-direction: alternate;
  -webkit-animation-timing-function: cubic-bezier(.5, 0.05, 1, .5);
  -webkit-animation-iteration-count: infinite;
}

@keyframes bounce {
  from {
    transform: translate3d(0, 0, 0);
  }
  to {
    transform: translate3d(0, 3px, 0);
  }
}

@-webkit-keyframes bounce {
  from {
    -webkit-transform: translate3d(0, 0, 0);
    transform: translate3d(0, 0, 0);
  }
  to {
    -webkit-transform: translate3d(0, 3px, 0);
    transform: translate3d(0, 3px, 0);
  }
}
   
   </style>
</head>
<body class="gray-bg">
	<div class="app-download">
      <label id="first-title">ISO 9001:2015</label>
      <label id="second-title">CERTIFIED</label>
      <i class="bounce"><img src="<c:url value="/resources/img/check2.png" />" width="30px"></i>
    </div>
 <!-- Login Body -->
     <div class="loginColumns animated fadeInDown">	       
        <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div class="badge">
			  <div class="badge__label">
			  	<p align="center"><img src="<c:url value="/resources/img/logo2.png" />"></p>
			  </div>
			</div>
            <h3>Welcome to HRMS</h3>
            <p>TCRA Human Resource Management System</p>
                <form:form action="${pageContext.request.contextPath}/authenticate.htm" role="form" method="post"  modelAttribute="user" name="authReq">
                    <div class="form-group">
                        <input id="username" type="text" name="username" class="form-control" placeholder="Username" required="">
                    </div>
                    <div class="form-group">
                        <input id="password" name="password" type="password" class="form-control" placeholder="Password" required="">
                    </div>
                    <button type="submit" value="submit" class="btn btn-block btn-outline btn-primary" id="submitCredentials">Login</button>

                    <p align="right"><a href="#"><small>Version 1.0</small></a></p>                        
                </form:form>
			<c:set var="now" value="<%=new java.util.Date()%>" />
            <p class="m-t"> <small>Copyright of TCRA | All Right Reserved &copy; <fmt:formatDate pattern="yyyy" value="${now}" /></small> </p>
        </div>
    </div>       
    </div>
 <!-- ./ Login Body -->
	
<!-- Mainly scripts -->
<script src="<c:url value="/resources/js/jquery-2.1.1.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />" type="text/javascript"></script>
<!-- Custom and plugin javascript -->
<script src="<c:url value="/resources/js/inspinia.js" />" type="text/javascript"></script>
<!-- jQuery UI -->
<script src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/resources/js/plugins/sweetalert/sweetalert.min.js" />" type="text/javascript"></script>

<!-- HANDLE ALL MESSAGES COMING FROM BACKEND ON URL -->
<c:if test="${not empty error}">
	<script type="text/javascript">
		sweetAlert("Oops...", "${error}", "error");
	</script>
	</c:if>
	<c:if test="${not empty success}">
	<script type="text/javascript">
		sweetAlert('Excellent!', "${success}", "success");
	</script>
</c:if>
</body>
</html>