<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HRMS | Payroll Details</title>
<link rel="icon" href="<c:url value="/resources/img/logo2.png" />"
	type="image/x-icon">
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/font-awesome/css/font-awesome.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/dataTables/datatables.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/plugins/iCheck/custom.css" />"
	rel="stylesheet">
<link
	href="<c:url value="/resources/css/plugins/steps/jquery.steps.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/animate.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
<link
	href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css"
	rel="stylesheet" />
<!-- formvalidation io -->
<link
	href="<c:url value="/resources/dist/css/formValidation.min.css" />"
	rel="stylesheet">
<!-- daterange picker -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<style>
.td-large-x{
	min-width:220px;
}
.td-large{
	min-width:172px;
}
.td-medium{
	min-width:102px;
}
.btn-circle-size{
	width:18px;
	height:18px;
}
/* .btn-xs{
	width:12px;
	height:12px;
	padding:0px;
	margin:0px;
} */
.odd,.even {
   line-height: 12px;
   max-height: 12px;
   height: 12px;
   padding:0px;
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
			<div class="wrapper wrapper-content">
				<!-- Page Body starts -->
				<div class="row">
					<div class="col-md-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title custom-ibox-title">
	                           <h5>
	                           <span class="text-normal">Payroll&nbsp;of&nbsp;</span>	                           
	                           <fmt:parseDate value="${payroll.datepay}" pattern="yyyy-MM-dd" var="paydate" />
	                           <fmt:formatDate value="${paydate}" type="date" pattern="MMMM,yyyy" var="paydateFormatted" />
	                           <span class="text-bold" id="payroll-pay-date">${paydateFormatted}</span>
	                           </h5>
	                           <div class="ibox-tools">	
								   <button class="btn btn-primary btn-xs" id="btnExportExcel">
	                                   <i class="fa fa-print"></i>
	                                   <span>Export&nbsp;to&nbsp;Excel&nbsp;</span>
	                               </button>
								   &nbsp;&nbsp;
								   <button class="btn btn-primary btn-xs hide hidden" id="btnExportPDF">
	                                   <i class="fa fa-print"></i>
	                                   <span>Export&nbsp;to&nbsp;PDF&nbsp;</span>
	                               </button>
									&nbsp;&nbsp;
									<a class="collapse-link">
	                                   <i class="fa fa-chevron-up"></i>
	                               </a>
	                               <a class="close-link-x">
	                                   <i class="fa fa-times"></i>
	                               </a>
	                           </div>
	                       </div>
	                       <div class="ibox-content">
	                       		<div class="row">
	                       			<div class="col-md-12">
	                       				<div class="table-responsive table-responsive-sm">
	                       					<table
												class="table table-sm table-striped table-bordered table-hover dataTables-payroll"
												id="dataTables-payroll">
												<thead>
													<tr>
														<th>S/N</th>													
														<th>Employee&nbsp;Full&nbsp;Name</th>
														<th>Basic&nbsp;Pay</th>
														<th>Other&nbsp;Income</th>
														<th>Servant&nbsp;&amp;&nbsp;Hospitality</th>
														<th>House&nbsp;Allowance</th>
														<th>Transport&nbsp;Allowance	</th>
														<th>Gross&nbsp;Pay</th>
														<th>Taxable&nbsp;Pay</th>
														<th>PAYE</th>	
														<th>PSSSF</th>
														<th>PSSSF&nbsp;Employer</th>
														<th>ZSSF</th>
														<th>ZSSF&nbsp;Employer</th>	
														<th>Azania&nbsp;Bank</th>
														<th>Residential&nbsp;House&nbsp;Loan&nbsp;-&nbsp;Deduction</th>
														<th>Residential&nbsp;Loan&nbsp;Balance</th>
														<th>Transport&nbsp;Loan&nbsp;-&nbsp;Deduction</th>
														<th>Transport&nbsp;Loan&nbsp;Balance</th>
														<th>HESLB&nbsp;-&nbsp;Deduction</th>
														<th>HESLB&nbsp;Balance</th>
														<th>Jubilee&nbsp;Insurance</th>
														<th>NIC</th>														
														<th>Posta&nbsp;na&nbsp;Simu&nbsp;Saccos&nbsp;Ltd</th>
														<th>TCRA&nbsp;Saccos</th>
														<th>MKM&nbsp;TCRA</th>
														<th>Salary&nbsp;Advance&nbsp;Deduction</th>														
														<th>Salary&nbsp;Advance&nbsp;Balance</th>
														<th>Total&nbsp;Deduction</th>
														<th>Net&nbsp;Pay</th>
														<th>Action</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="i" begin="1" end="1">
													<c:if test="${not empty payroll.payrollResponselist}">
														<c:forEach var="employeePayroll" items="${payroll.payrollResponselist}"
															varStatus="status">
															<tr style="height:10px;">
																<td width="30px">${status.count}</td>
																<td class="td-large-x"><c:out value="${employeePayroll.fullName}" /></td>
																<td class="text-right">
																<c:set var="salaryBasic" value="${employeePayroll.amountsalarybasic}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${salaryBasic}" />
																</td>
																<td class="text-right">
																<c:set var="incomeOther" value="${employeePayroll.amountotherincome}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${incomeOther}" />
																</td>
																<!-- allowance -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.allowancelist}">
																        <c:set var="hasAllowanceHospitality" value="${0}"/>
																    	<c:forEach var="allowanceHospitality" items="${employeePayroll.allowancelist}">
																    		<c:if test="${allowanceHospitality.allowancetypeCode eq 1}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${allowanceHospitality.amountsalaryallowance}" />
																			</td>
																			<c:set var="hasAllowanceHospitality" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasAllowanceHospitality==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    	<c:set var="hasAllowanceHousing" value="${0}"/>
																    	<c:forEach var="allowanceHousing" items="${employeePayroll.allowancelist}">
																    		<c:if test="${allowanceHousing.allowancetypeCode eq 2}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${allowanceHousing.amountsalaryallowance}" />
																			</td>
																			<c:set var="hasAllowanceHousing" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasAllowanceHousing==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    	<c:set var="hasAllowanceTransport" value="${0}"/>
																    	<c:forEach var="allowanceTransport" items="${employeePayroll.allowancelist}">
																    		<c:if test="${allowanceTransport.allowancetypeCode eq 3}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${allowanceTransport.amountsalaryallowance}" />
																			</td>
																			<c:set var="hasAllowanceTransport" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasAllowanceTransport==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>																
																<!-- /.allowance -->
																<td class="text-right">
																<c:set var="salaryGross" value="${employeePayroll.amountsalarygross}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${salaryGross}" />
																</td>
																<td class="text-right">
																<c:set var="salaryTaxable" value="${employeePayroll.amounttaxable}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${salaryTaxable}" />
																</td>
																<td class="text-right">
																<c:set var="taxPaye" value="${employeePayroll.amounttaxpaye}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${taxPaye}" />
																</td>
																<!-- pension -->
																<!-- PSSF -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionMandatoryPensionlist}">
																    	<c:set var="hasPSSSF" value="${0}"/>
																    	<c:forEach var="employeeDeductionMandatory" items="${employeePayroll.deductionMandatoryPensionlist}">
																    		<c:if test="${employeeDeductionMandatory.mandataryContributionServiceProvider eq 'PSSSF'}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionMandatory.amountdeductionmandatoryPension}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionMandatory.amountdeductionmandatoryPensionEmployer}" />
																			</td>
																			<c:set var="hasPSSSF" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasPSSSF==0}">
																    		<td class="text-right">0</td>
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>
																<!-- PSSF -->
																<!-- ZSSF -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionMandatoryPensionlist}">
																    	<c:set var="hasZSSF" value="${0}"/>
																    	<c:forEach var="employeeDeductionMandatory" items="${employeePayroll.deductionMandatoryPensionlist}">
																    		<c:if test="${employeeDeductionMandatory.mandataryContributionServiceProvider eq 'ZSSF'}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionMandatory.amountdeductionmandatoryPension}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionMandatory.amountdeductionmandatoryPensionEmployer}" />
																			</td>
																			<c:set var="hasZSSF" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasZSSF==0}">
																    		<td class="text-right">0</td>
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>
																<!-- /.ZSSF -->										
																<!-- /.pension -->
																<!-- loan -->
																<!-- Azania Bank Loan -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionLoanlist}">
																    	<c:set var="hasLoanAzaniaBank" value="${0}"/>
																    	<c:forEach var="employeeLoan" items="${employeePayroll.deductionLoanlist}">
																    		<c:if test="${employeeLoan.loantypeCode==4}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountdeductionloan}" />
																			</td>
																			<c:set var="hasLoanAzaniaBank" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasLoanAzaniaBank==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>	
																<!-- /.Azania Bank Loan -->
																<!-- Residential House Loan -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionLoanlist}">
																    	<c:set var="hasLoanResidentialHouse" value="${0}"/>
																    	<c:forEach var="employeeLoan" items="${employeePayroll.deductionLoanlist}">
																    		<c:if test="${employeeLoan.loantypeCode==5}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountdeductionloan}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountloanbalance}" />
																			</td>
																			<c:set var="hasLoanResidentialHouse" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasLoanResidentialHouse==0}">
																    		<td class="text-right">0</td>
																        	<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>	
																<!-- /.Residential House Loan -->
																<!-- Transport Loan -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionLoanlist}">
																    	<c:set var="hasLoanTransport" value="${0}"/>
																    	<c:forEach var="employeeLoan" items="${employeePayroll.deductionLoanlist}">
																    		<c:if test="${employeeLoan.loantypeCode==6}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountdeductionloan}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountloanbalance}" />
																			</td>
																			<c:set var="hasLoanTransport" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasLoanTransport==0}">
																    		<td class="text-right">0</td>
																        	<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>	
																<!-- /.Transport Loan -->
																<!-- HESLB -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionLoanlist}">
																    	<c:set var="hasLoanHESLB" value="${0}"/>
																    	<c:forEach var="employeeLoan" items="${employeePayroll.deductionLoanlist}">
																    		<c:if test="${employeeLoan.loantypeCode==2}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountdeductionloan}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountloanbalance}" />
																			</td>
																			<c:set var="hasLoanHESLB" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasLoanHESLB==0}">
																    		<td class="text-right">0</td>
																        	<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>	
																<!-- /.HESLB -->
																<!-- ./loan -->
																<!-- insurance -->
																<!-- JUBILEE -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionVoluntarylist}">
																    	<c:set var="hasJubileeLifeInsurance" value="${0}"/>
																    	<c:forEach var="employeeDeductionVoluntary" items="${employeePayroll.deductionVoluntarylist}">
																    		<c:if test="${employeeDeductionVoluntary.voluntaryContributiontypeCode==6}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionVoluntary.amountdeductionvoluntary}" />
																			</td>
																			<c:set var="hasJubileeLifeInsurance" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasJubileeLifeInsurance==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>
																<!-- /.JUBILEE -->
																<!-- NIC -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionVoluntarylist}">
																    	<c:set var="hasnNICLifeInsurance" value="${0}"/>
																    	<c:forEach var="employeeDeductionVoluntary" items="${employeePayroll.deductionVoluntarylist}">
																    		<c:if test="${employeeDeductionVoluntary.voluntaryContributiontypeCode==7}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionVoluntary.amountdeductionvoluntary}" />
																			</td>
																			<c:set var="hasnNICLifeInsurance" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasnNICLifeInsurance==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>
																<!-- /.NIC -->
																<!-- /.insurance -->
																<!-- saccos -->
																<!-- POSTA na SIMU -->	
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionVoluntarylist}">
																    	<c:set var="hasPostaNaSimu" value="${0}"/>
																    	<c:forEach var="employeeDeductionVoluntary" items="${employeePayroll.deductionVoluntarylist}">
																    		<c:if test="${employeeDeductionVoluntary.voluntaryContributiontypeCode==5}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionVoluntary.amountdeductionvoluntary}" />
																			</td>
																			<c:set var="hasPostaNaSimu" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasPostaNaSimu==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>	
																<!-- /.POSTA na SIMU -->	
																<!-- TCRA SACCOS -->																
																<td class="text-right">
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeePayroll.amountTcraSaccos}" />
																</td>
																<!-- /.TCRA SACCOS -->	
																<!-- TCRA MKM -->	
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionVoluntarylist}">
																    	<c:set var="hasMKM" value="${0}"/>
																    	<c:forEach var="employeeDeductionVoluntary" items="${employeePayroll.deductionVoluntarylist}">
																    		<c:if test="${employeeDeductionVoluntary.voluntaryContributiontypeCode==4}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeDeductionVoluntary.amountdeductionvoluntary}" />
																			</td>
																			<c:set var="hasMKM" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasMKM==0}">
																    		<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>
																<!-- /.TCRA MKM -->	
																<!-- /.saccos -->
																<!-- advance salary -->
																<c:choose>
																    <c:when test="${not empty employeePayroll.deductionLoanlist}">
																    	<c:set var="hasLoanSalaryAdvance" value="${0}"/>
																    	<c:forEach var="employeeLoan" items="${employeePayroll.deductionLoanlist}">
																    		<c:if test="${employeeLoan.loantypeCode==1}">
																    		<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountdeductionloan}" />
																			</td>
																			<td class="text-right">
																			<fmt:formatNumber type="number" pattern="###,###,###.###" value="${employeeLoan.amountloanbalance}" />
																			</td>
																			<c:set var="hasLoanSalaryAdvance" value="${1}"/>
																    		</c:if>
																    	</c:forEach>
																    	<c:if test="${hasLoanSalaryAdvance==0}">
																    		<td class="text-right">0</td>
																        	<td class="text-right">0</td>
																    	</c:if>
																    </c:when>    
																    <c:otherwise>
																        <td>-</td>
																        <td>-</td>
																    </c:otherwise>
																</c:choose>		
																<!-- /.advance salary -->
																<td class="text-right">
																<c:set var="deductionTotal" value="${employeePayroll.amountdeduction}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${deductionTotal}" />
																</td>
																<td class="text-right">
																<c:set var="salaryNet" value="${employeePayroll.amountsalarynet}" /> 
																<fmt:formatNumber type="number" pattern="###,###,###.###" value="${salaryNet}" />
																</td>
																<td>
																	<a href="#" title="Edit"><i class="fa fa-edit"></i>
																	</a>&nbsp;&nbsp;
																</td>
															</tr>
														</c:forEach>
													</c:if>
													</c:forEach>													
												</tbody>
												<tfoot>
													<tr>
														<th>S/N</th>													
														<th>Employee&nbsp;Full&nbsp;Name</th>
														<th>Basic&nbsp;Pay</th>
														<th>Other&nbsp;Income</th>
														<th>Servant&nbsp;&amp;&nbsp;Hospitality</th>
														<th>House&nbsp;Allowance</th>
														<th>Transport&nbsp;Allowance	</th>
														<th>Gross&nbsp;Pay</th>
														<th>Taxable&nbsp;Pay</th>
														<th>PAYE</th>	
														<th>PSSSF</th>
														<th>PSSSF&nbsp;Employer</th>
														<th>ZSSF</th>
														<th>ZSSF&nbsp;Employer</th>	
														<th>Azania&nbsp;Bank</th>
														<th>Residential&nbsp;House&nbsp;Loan&nbsp;-&nbsp;Deduction</th>
														<th>Residential&nbsp;Loan&nbsp;Balance</th>
														<th>Transport&nbsp;Loan&nbsp;-&nbsp;Deduction</th>
														<th>Transport&nbsp;Loan&nbsp;Balance</th>
														<th>HESLB&nbsp;-&nbsp;Deduction</th>
														<th>HESLB&nbsp;Balance</th>
														<th>Jubilee&nbsp;Insurance</th>
														<th>NIC</th>														
														<th>Posta&nbsp;na&nbsp;Simu&nbsp;Saccos&nbsp;Ltd</th>
														<th>TCRA&nbsp;Saccos</th>
														<th>MKM&nbsp;TCRA</th>
														<th>Salary&nbsp;Advance&nbsp;Deduction</th>														
														<th>Salary&nbsp;Advance&nbsp;Balance</th>
														<th>Total&nbsp;Deduction</th>
														<th>Net&nbsp;Pay</th>
														<th>Action</th>
													</tr>
												</tfoot>
											</table>
	                       				</div>
	                       			</div>
	                       		</div>
	                       </div>
						</div>
					</div>
				</div>
			</div>
			<!-- Footer Include -->
			<jsp:include page="../includes/footer.jsp" />
		</div>
	</div>
	<!-- Modals -->
	<!-- Mainly scripts -->
	<script src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/metisMenu/jquery.metisMenu.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/jeditable/jquery.jeditable.js" />"></script>
	<script
		src="<c:url value="/resources/js/plugins/dataTables/datatables.min.js" />"></script>
	<!-- Custom and plugin javascript -->
	<script src="<c:url value="/resources/js/inspinia.js" />"></script>
	<!-- jQuery UI -->
	<script
		src="<c:url value="/resources/js/plugins/jquery-ui/jquery-ui.min.js" />"></script>
	<!-- select2 -->
	<script
		src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
	<!-- formvalidation.io -->
	<script
			src="<c:url value="/resources/dist/js/FormValidation.min.js" />"></script>
	<script
			src="<c:url value="/resources/dist/js/FormValidation.full.min.js" />"></script>	
	<!-- date and date picker -->
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
	<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>	
	<script>
	$.fn.serializeObject = function() {
	    var o = {};
	    var a = this.serializeArray();
	    $.each(a, function() {
	        if (o[this.name]) {
	            if (!o[this.name].push) {
	                o[this.name] = [o[this.name]];
	            }
	            o[this.name].push(this.value || '');
	        } else {
	            o[this.name] = this.value || '';
	        }
	    });
	    return o;
	};
	</script>
	<!-- Page-Level Scripts -->
	<script>
	//--------------------------------------------
	//- utils
	//--------------------------------------------
	function getContextPath() {
	   return window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	}
	function getBaseUrl() {
		   return window.location.origin;
	}
	</script>
	<script>
		$(document).ready(function(){
			// payroll
			var $table = $('#dataTables-payroll').DataTable({
				dom : '<"html5buttons"B>lTfgitp',
				buttons : [
					{extend : 'copy'},
					{extend : 'csv'},
					{extend : 'excel',title : 'Payroll details'},
					{extend : 'pdf',title : 'Payroll details'},
					{extend : 'print',customize : function(win) {
						$(win.document.body).addClass('white-bg');
						$(win.document.body).css('font-size','10px');
						$(win.document.body).find('table').addClass('compact').css('font-size','inherit');
					}
				}]
			});
			// export tools
			$('#btnExportExcel').click(function(){
				// headers
				var headersKey =  ["sn","fullName","salaryBasic","wages","allowanceHospitality"
					,"allowanceHousing","allowanceTransport","salaryGross","salaryTaxable","taxPaye",
					"pensionPSSSF","pensionPSSSFEmployer","pensionZSSF","pensionZSSFEmployer","loanAzaniaBank",
					"loanResidential","loanResidentialBalance","loanTransport","loanTransportBalance","loanHESLB",
					"loanHESLBBalance","insuranceJubilee","insuranceNIC","voluntaryPostaSimu","voluntaryTCRASACCOS",
					"voluntaryTCRAMKM","loanSalaryAdvance","loanSalaryAdvanceBalance","totalDeduction","salaryNetPay"];
				var headers =  [];
				var dataColumns = $table.settings().columns()[0];
				dataColumns.forEach(function(index) {
					console.log("Column index-"+index);
					if(index!=dataColumns.length-1){// skip last
						headers.push($($table.column(index).header()).text())  
						console.log("Headers -"+JSON.stringify(headers));
					}
				});
				// rows
				var rows = [];
				var dataRows = $table.rows().data().toArray();
				dataRows.forEach(function(row,pos) {
				  var item = {};
				  var canPush = 0;
				  row.forEach(function(content, index) {
					  if(index!=dataColumns.length-1){// skip last
						  console.log("Row index-"+index);
						  item[headersKey[index]] = $.trim(content);
						  canPush = 1;
					  }
				  });
				  if(canPush>0){
					  rows.push(item);
					}
				});
				console.log("Rows -"+JSON.stringify(rows));
				console.log("Rows count -"+rows.length);
				// excel data
				var dataExcel = {};
				if(rows!=null){
					dataExcel["headers"] = headers;
				}
				if(rows!=null){
					dataExcel["rows"] = rows;
				}
				// pay date
				var payDate = $('#payroll-pay-date').text();
				console.log("Pay date -"+payDate.trim());
				if(payDate!=null){
					dataExcel["paydate"] = payDate.trim();
				}
				console.log("Excel -"+JSON.stringify(dataExcel));
				// send excel to controller
				if(dataExcel){
					var $apiUrl = "${pageContext.request.contextPath}/v1/payroll-run-details.excel";
					$.ajax({
						url:$apiUrl,
						type:'POST',
						dataType: "text",
						contentType:'application/json',
						data: JSON.stringify(dataExcel),
						statusCode: {            	    	
		        	    	208: function(responseObject, textStatus, jqXHR) {
		        	            showError("Duplicate payroll details not allowed.");
		        	        },
		        	        401: function(responseObject, textStatus, jqXHR) {
		        	        	showError("You are not authorized to peform this action.");
		        	        },
		        	        404: function(responseObject, textStatus, jqXHR) {
		        	        	showError("Payroll item not found.");
		        	        },
		        	        412: function(responseObject, textStatus, jqXHR) {
		        	        	showError("Your request is not valid, please reveiew and submit again");
		        	        },
		        	        500: function(responseObject, textStatus, errorThrown) {
		        	        	console.log("Server failed to process your request, please try again later");
		        	        },
		        	        503: function(responseObject, textStatus, errorThrown) {
		        	        	showError("Payroll service unavailable");
		        	        }           
		        	    },
						success: function(data, textStatus, jqXHR){
							console.log("DataTable-"+JSON.stringify(data));
							// download
							var fileUri = data;
							window.location.href = '${pageContext.request.contextPath}/payroll-run-details-download.excel/'+fileUri;
						},
						error:function(jqXHR, textStatus, errorThrown){
							console.log("Error status - "+textStatus+", Error - "+errorThrown);
							console.log("Failed to retrieve payroll details");
						}
					});
				}
			});
		});
	</script>
</body>
</html>