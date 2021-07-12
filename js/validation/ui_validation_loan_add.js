// utils
function showError(errorMessage){
	$.dialog({
	    title: '',
	    content: errorMessage,
	});
	/*$.alert({
        title: '',
        content: errorMessage,
    });*/
}
// DOM ready
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    
	// Create a FormValidation instance    
	const btnAddLoan = $('#btnAddLoan'); 
    const formAddLoan = document.getElementById('formAddLoan');     
    const fv = FormValidation.formValidation(formAddLoan, {
        fields: { 
        	employeeid: {
                validators: {
                    notEmpty: {
                        message: 'Borrower is required'
                    }
                }
            },
            lenderid: {
                validators: {
                    notEmpty: {
                        message: 'Lender is required'
                    }
                }
            },
            loantypeid: {
                validators: {
                    notEmpty: {
                        message: 'Loan type is required'
                    }
                }
            },
            loanfrequencyid: {
                validators: {
                    notEmpty: {
                        message: 'Repayment cycle is required'
                    }
                }
            },
            amountdebt: {
                validators: {
                    notEmpty: {
                        message: 'Loan amount is required'
                    }
                }
            },
            duration: {
                validators: {
                    notEmpty: {
                        message: 'Loan period is required'
                    }
                }
            },
            interestrate: {
                validators: {
                    notEmpty: {
                        message: 'Loan interest rate is required'
                    }
                }
            },
            dateissued: {
                validators: {
                    notEmpty: {
                        message: 'Loan issue date is required'
                    }
                }
            },
            daterepaymentstart: {
                validators: {
                    notEmpty: {
                        message: 'Loan repayment start date is required'
                    }
                }
            },
        },
        plugins: { 
        	trigger: new FormValidation.plugins.Trigger(),
        	// Validate fields when clicking the Submit button
            submitButton: new FormValidation.plugins.SubmitButton(),
            
            // Submit the form when all fields are valid
            //defaultSubmit: new FormValidation.plugins.DefaultSubmit(),
            //bootstrap: new FormValidation.plugins.Bootstrap(),
            icon: new FormValidation.plugins.Icon({
                valid: 'fa fa-check',
                invalid: 'fa fa-times',
                validating: 'fa fa-refresh',
            }),
		},
    }).on('core.form.valid', function() {
    	// Send the form data to back-end
        // You need to grab the form data and create an Ajax request to send them
    	var $apiUrl = $('#formAddLoan').attr('action');
    	var $data = $('#formAddLoan').serializeObject();
    	//var $data = $('#formAddLoan').serialize();
    	console.log($data);
    	console.log(JSON.stringify($data));
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
    	            showError("Duplicate loan contribution not allowed.");
    	        },
    	        401: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are not authorized to peform this action.");
    	        },
    	        404: function(responseObject, textStatus, jqXHR) {
    	        	showError("The item you are looking for is not found.");
    	        },
    	        412: function(responseObject, textStatus, jqXHR) {
    	        	showError("Your request is not valid, please reveiew and submit again");
    	        },
    	        500: function(responseObject, textStatus, errorThrown) {
    	        	showError("Server failed to process your request, please try again later");
    	        },
    	        503: function(responseObject, textStatus, errorThrown) {
    	        	showError("Loan service unavailable");
    	        }           
    	    },
    	    success: function(data, textStatus, jqXHR) {
    	    	var statusCode = jqXHR.status;
    	    	if(statusCode==208){
    	    		alert("Loan exists, try loan adjustment");
    	    	}else{
        	        console.log("Loan added - "+data);
        	        // parse data
        	        var loan = JSON.parse(data);
        	        var loanID = loan.id;
        	        var debt = loan.amountdebt;
        	        if(loanID<=0){
        	        	$.confirm({
        	        	    title: '',
        	        	    content: 'The employee has similar loan with pending repayment. Please, try loan adjustment.',
        	        	    type: 'red',
        	        	    buttons: {   
        	        	        ok: {
        	        	            text: "ok",
        	        	            btnClass: 'btn-primary',
        	        	            keys: ['enter'],
        	        	            action: function(){}
        	        	        },
        	        	    }
        	        	});
        	        }else{
            	        var loantypeID = loan.loantypeid;
            	        console.log("Loan type ID = "+loantypeID);
            	        if(loantypeID && loantypeID==2){
            	        	// update DOM
            	        	$('#loanid').val(loanID);
            	        	// show dialog HESLB loan details
            	        	$("#addHeslbLoanDetailsModal").modal();
            	        }else{
            	        	$.confirm({
            	        	    title: 'Congrats!',
            	        	    content: 'Loan request submitted successfully.',
            	        	    type: 'green',
            	        	    buttons: {   
            	        	        ok: {
            	        	            text: "ok",
            	        	            btnClass: 'btn-primary',
            	        	            keys: ['enter'],
            	        	            action: function(){}
            	        	        },
            	        	    }
            	        	});
            	        }        	        	
            	        // update UI
            	        btnAddLoan.html("Saved");
            	        btnAddLoan.attr("disabled", true); 
        	        }
    	    	}
    	    },
    	    error: function(jqXHR, textStatus, errorThrown) {
    	        var errorMessage = jqXHR.responseText;
    	        if (errorMessage.length > 0) {
    	            //alert(errorMessage);
    	        }
    	    }
    	});
    });
});