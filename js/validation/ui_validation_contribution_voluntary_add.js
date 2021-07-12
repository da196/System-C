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
// hide modal
function hideAddVoluntaryContributionModal(){
	$('#addVoluntaryContributionModal').modal('hide');
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    
	const addContributionButton = document.getElementById('addContributionButton');
    const formVoluntaryContribution = document.getElementById('formAddVoluntaryContribution');     
    const fv = FormValidation.formValidation(formVoluntaryContribution, {
        fields: { 
        	employeeid:{
        		validators: {
                    notEmpty: {
                        message: 'Employee is required'
                    }
                }
        	},
        	serviceproviderid: {
                validators: {
                    notEmpty: {
                        message: 'Institution is required'
                    }
                }
            },
            contributiontypeid: {
                validators: {
                    notEmpty: {
                        message: 'Contribution type is required'
                    }
                }
            },
            contributionmode: {
                validators: {
                    notEmpty: {
                        message: 'Contribution deduction mode is required'
                    }
                }
            },
            currencyid: {
                validators: {
                    notEmpty: {
                        message: 'Currency is required'
                    }
                }
            },
            amount: {
                validators: {
                    notEmpty: {
                        message: 'Contribution amount is required'
                    }
                }
            },
            joiningdate: {
                validators: {
                    notEmpty: {
                        message: 'Joining is required'
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
    	addContributionButton.innerHTML = 'Validating ...';
    });
    // click listener
    addContributionButton.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		var $apiUrl = $('#formAddVoluntaryContribution').attr('action');
            	var $data = $('#formAddVoluntaryContribution').serializeObject();//.serializeArray();//serialize();
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
            	            showError("Duplicate contribution not allowed.");
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
            	        	showError("Contributution service unavailable");
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
                	        $('#addContributionButton').html("Saved");
                	        $('#addContributionButton').attr("disabled", true);
                	        
                	        // close dialog
                	        hideAddVoluntaryContributionModal();            	        
                	        // reload
                	        window.location.reload(true);
            	        }else{
            	        	$('#addContributionButton').html("Try again!");
            	        }            	        
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        if (errorMessage.length > 0) {
            	            //console.log(errorMessage);
            	        } 
            	        //showError(errorMessage);
            	        $('#addContributionButton').html("Try again!");
            	        $('#addContributionButton').attr("disabled", false);
            	    }
            	});
        	}else{
        		addContributionButton.innerHTML  = 'Not valid';
        	}
        });
    });
});