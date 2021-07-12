// utils
function showError(errorMessage){
	$.dialog({
	    title: '',
	    content: errorMessage,
	});
}
function showAlert(message){
	$.alert({
        title: '',
        content: message,
    });
}
function showToast(message){
	$.dialog({
	    title: '',
	    content: message,
	});
}
function showPanelResult(){
	$("#container-run-payroll-result").removeAttr("hide");
	$("#container-run-payroll-result").removeAttr("hidden");
	$('#container-run-payroll-result').show();
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    	
	const btnRunPayroll = document.getElementById('btnRunPayroll');
    const formAddLoanDetailHeslb = document.getElementById('formRunPayroll');     
    const fv = FormValidation.formValidation(formAddLoanDetailHeslb, {
        fields: { 
        	payrollcycle:{
        		validators: {
                    notEmpty: {
                        message: 'Payroll cycle is required'
                    }
                }
        	},
        },
        plugins: { 
        	 trigger: new FormValidation.plugins.Trigger(),
             //tachyons: new FormValidation.plugins.Tachyons(),
             icon: new FormValidation.plugins.Icon({
                 valid: 'fa fa-check',
                 invalid: 'fa fa-times',
                 validating: 'fa fa-refresh'
             }),
		},
    })
    .on('core.form.validating', function() {
    	btnRunPayroll.innerHTML = 'Validating ...';
    });
    // click listener
    btnRunPayroll.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		// show progress
        		$('#spinnerRunPayroll').show();
        		// parse
        		var $apiUrl = $('#formRunPayroll').attr('action');
            	var $data = $('#formRunPayroll').serializeObject();//.serializeArray();//serialize();
            	console.log('Data = ['+$data+']');
            	console.log('Data = ['+JSON.stringify($data)+']');
            	$.ajax({
            	    url: $apiUrl,
            	    type: 'POST',
            	    contentType: 'application/json',
            	    dataType: 'text',
            	    headers: {
            	    	"Accept": "application/json",
            	    },
            	    data: JSON.stringify($data),
            	    statusCode: {            	    	
            	    	208: function(responseObject, textStatus, jqXHR) {
            	            //showError("Payroll date closed. Payroll data retrieved successfully.");
            	        },
            	        401: function(responseObject, textStatus, jqXHR) {
            	        	showError("You are not authorized to run payroll.");
            	        },
            	        404: function(responseObject, textStatus, jqXHR) {
            	        	showError("Running payroll data not found.");
            	        },
            	        412: function(responseObject, textStatus, jqXHR) {
            	        	showError("Run payroll request not valid, please reveiew and submit again");
            	        },
            	        500: function(responseObject, textStatus, errorThrown) {
            	        	showError("System failed to run payroll, please try again later");
            	        },
            	        503: function(responseObject, textStatus, errorThrown) {
            	        	showError("Run payroll service unavailable");
            	        }           
            	    },
            	    success: function(data, textStatus, jqXHR) {
            	    	if(data){
                	    	// log
                	        console.log(data);
                	        var status = jqXHR.status;
                	        if(status==200 || status==208){
                    	        // parse data
                    	        var payroll = JSON.parse(data);
                    	        console.log(JSON.stringify(payroll));
                    	        console.log("Result-"+payroll);
               	        	 	// update UI
                    	        if(payroll.employeeTotal>=0){
                        	        $('#total-employee').text(payroll.employeeTotal);
                        	    }
                    	        if(payroll.grossSalaryTotal>=0){
                    	        	var salary = numeral(payroll.grossSalaryTotal).format('0,0.00')
                    	        	$('#total-salary').text(salary);
                        	    }
                    	        if(payroll.taxTotal>=0){
                    	        	var tax = numeral(payroll.taxTotal).format('0,0.00');
                    	        	$('#total-tax').text(tax);
                        	    }
                    	        if(payroll.pensionEmployeeTotal>=0){
                    	        	var pension = payroll.pensionEmployeeTotal+payroll.pensionEmployerTotal;
                    	        	var _pension = numeral(pension).format('0,0.00');
                        	        $('#total-pension').text(_pension);
                        	    }
                    	        if(payroll.insuranceEmployerTotal>=0){
                    	        	var insurance = numeral(payroll.insuranceEmployerTotal).format('0,0.00');
                    	        	$('#total-insurance').text(insurance);
                        	    }
                    	        if(payroll.loanTotal>=0){
                    	        	var loan = numeral(payroll.loanTotal).format('0,0.00');
                    	        	$('#total-loan').text(loan);
                        	    }
                    	        // action
                    	        $('#btnRunPayroll').html("Completed");
                    	        $('#btnRunPayroll').attr("disabled", true);
                    	        // disable view payroll
                    	        $('#payrollcycle').attr('disabled',true);
                    	        $('#btnRunPayroll').attr('disabled',true);
                    	        // enable view payroll
                    	        $('#btnViewPayroll').attr('disabled',false);
                    	        // show dialog
                    	        showToast("Payroll run successfully");
                    	        // show result
                    	        showPanelResult();
                	        }else{
                    	        $('#btnRunPayroll').html("Failed, try again!");
                	        }
            	    	}else{
            	    		 $('#btnRunPayroll').html("No payroll data, try again!");
            	    	}
            	        // hide
            	        $('#spinnerRunPayroll').hide(1000);
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#btnRunPayroll').html("Try again!");
            	        $('#btnRunPayroll').attr("disabled", false);
            	        // hide
            	        $('#spinnerRunPayroll').hide(1000);
            	    }
            	});
        	}else{
        		btnRunPayroll.innerHTML  = 'Not valid';
        	}
        });
    });
});