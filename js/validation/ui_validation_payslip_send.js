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

// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    	
	const btnSendPayslip = document.getElementById('btnSendPayslip');
    const formAddLoanDetailHeslb = document.getElementById('formSendPayslip');     
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
    	btnSendPayslip.innerHTML = 'Validating ...';
    });
    // click listener
    btnSendPayslip.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		// show progress
        		$('#spinnerSendPayslip').show();
        		// parse
        		var $apiUrl = $('#formSendPayslip').attr('action');
            	var $data = $('#formSendPayslip').serializeObject();//.serializeArray();//serialize();
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
            	    	var status = jqXHR.status;
            	        if(status==200 || status==208){
                	        // parse data
                	        console.log(JSON.stringify(data));
                	        console.log("Result-"+data);
           	        	 	
                	        // action
                	        $('#btnSendPayslip').html("Completed");
                	        // disable view payroll
                	        $('#payrollcycle').attr('disabled',true);
                	        $('#btnSendPayslip').attr('disabled',true);
                	        // show dialog
                	        showToast("Payslip sent successfully");
            	        }else{
                	        $('#btnSendPayslip').html("Failed, try again!");
            	        }
            	        // hide
            	        $('#spinnerSendPayslip').hide(1000);
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#btnSendPayslip').html("Try again!");
            	        $('#btnSendPayslip').attr("disabled", false);
            	        // hide
            	        $('#spinnerSendPayslip').hide(1000);
            	    }
            	});
        	}else{
        		btnSendPayslip.innerHTML  = 'Not valid';
        	}
        });
    });
});