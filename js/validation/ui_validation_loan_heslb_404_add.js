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
// hide modal
function hideAddHeslbLoanDetailsModal404(){
	$('#addHeslbLoanDetailsModal404').modal('hide');
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    
	const addLoanHeslbButton404 = document.getElementById('addLoanHeslbButton404');
    const formAddLoanDetailHeslb = document.getElementById('formAddLoanHeslb404');     
    const fv = FormValidation.formValidation(formAddLoanDetailHeslb, {
        fields: { 
        	cseeindexnumber:{
        		validators: {
                    notEmpty: {
                        message: 'CSEE number is required'
                    }
                }
        	},
        	firstname: {
                validators: {
                    notEmpty: {
                        message: 'First name is required'
                    }
                }
            },
            middlename: {
                validators: {
                    notEmpty: {
                        message: 'Middle name is required'
                    }
                }
            },
            lastname: {
                validators: {
                    notEmpty: {
                        message: 'Last name is required'
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
    	addLoanHeslbButton404.innerHTML = 'Validating ...';
    });
    // click listener
    addLoanHeslbButton404.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		var $apiUrl = $('#formAddLoanHeslb404').attr('action');
            	var $data = $('#formAddLoanHeslb404').serializeObject();//.serializeArray();//serialize();
            	console.log('Data=['+$data+']');
            	console.log('Data=['+JSON.stringify($data)+']');
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
            	            showError("Duplicate loan details not allowed.");
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
            	        // parse data
            	        var contribution = data;
            	        console.log(data);
            	        console.log(JSON.stringify(contribution));
            	        var status = jqXHR.status;
            	        if(status==200){
            	        	 // update UI
                	        $('#addLoanHeslbButton404').html("Saved");
                	        $('#addLoanHeslbButton404').attr("disabled", true);
                	        // show dialog
                	        $.confirm({
            	        	    title: 'Congrats!',
            	        	    content: 'HESLB Loan details added successfully.',
            	        	    type: 'green',
            	        	    buttons: {   
            	        	        ok: {
            	        	            text: "ok",
            	        	            btnClass: 'btn-primary',
            	        	            keys: ['enter'],
            	        	            action: function(){
            	                	        // close dialog
            	        	            	hideAddHeslbLoanDetailsModal404();            	        
            	                	        // reload
            	                	        //window.location.reload(true);
            	        	            }
            	        	        },
            	        	    }
            	        	});
            	        }else{
                	        $('#addLoanHeslbButton404').html("Try again!");
            	        }
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	//showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#addLoanHeslbButton404').html("Try again!");
            	        $('#addLoanHeslbButton404').attr("disabled", false);
            	    }
            	});
        	}else{
        		addLoanHeslbButton404.innerHTML  = 'Not valid';
        	}
        });
    });
});