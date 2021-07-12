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
function hideAddInsuranceContributionModal(){
	$('#addInsuranceContributionModal').modal('hide');
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    
	const addInsuranceContributionButton = document.getElementById('addInsuranceContributionButton');
    const formInsuranceContribution = document.getElementById('formAddInsuranceContribution');     
    const fv = FormValidation.formValidation(formInsuranceContribution, {
        fields: { 
        	employeeid:{
        		validators: {
                    notEmpty: {
                        message: 'Employee is required'
                    }
                }
        	},
        	insurancetypeid: {
                validators: {
                    notEmpty: {
                        message: 'Insurance type is required'
                    }
                }
            },
        	insuranceproviderid: {
                validators: {
                    notEmpty: {
                        message: 'Insurance company is required'
                    }
                }
            },
            datestart: {
                validators: {
                    notEmpty: {
                        message: 'Joining date is required'
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
    	addInsuranceContributionButton.innerHTML = 'Validating ...';
    });
    // click listener
    addInsuranceContributionButton.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		var $apiUrl = $('#formAddInsuranceContribution').attr('action');
            	var $data = $('#formAddInsuranceContribution').serializeObject();//.serializeArray();//serialize();
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
            	            showError("Duplicate insurance contribution not allowed.");
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
            	        	showError("Insurance service unavailable");
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
                	        $('#addInsuranceContributionButton').html("Saved");
                	        $('#addInsuranceContributionButton').attr("disabled", true);
                	        
                	        // close dialog
                	        hideAddInsuranceContributionModal();            	        
                	        // reload
                	        window.location.reload(true);
            	        }
            	        $('#addInsuranceContributionButton').html("Try again!");
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	//showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#addInsuranceContributionButton').html("Try again!");
            	        $('#addInsuranceContributionButton').attr("disabled", false);
            	    }
            	});
        	}else{
        		addInsuranceContributionButton.innerHTML  = 'Not valid';
        	}
        });
    });
});