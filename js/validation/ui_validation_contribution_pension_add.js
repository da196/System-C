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
function hideAddPensionContributionModal(){
	$('#addPensionContributionModal').modal('hide');
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    
	const addPensionContributionButton = document.getElementById('addPensionContributionButton');
    const formPensionContribution = document.getElementById('formAddPensionContribution');     
    const fv = FormValidation.formValidation(formPensionContribution, {
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
                        message: 'Pension fund is required'
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
    	addPensionContributionButton.innerHTML = 'Validating ...';
    });
    // click listener
    addPensionContributionButton.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		var $apiUrl = $('#formAddPensionContribution').attr('action');
            	var $data = $('#formAddPensionContribution').serializeObject();//.serializeArray();//serialize();
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
            	            showError("Duplicate pension contribution not allowed.");
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
            	        	showError("Pension service unavailable");
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
                	        $('#addPensionContributionButton').html("Saved");
                	        $('#addPensionContributionButton').attr("disabled", true);
                	        
                	        // close dialog
                	        hideAddPensionContributionModal();            	        
                	        // reload
                	        window.location.reload(true);
            	        }else{
            	        	$('#addPensionContributionButton').html("Try again!");
            	        }          	        
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	        var errorMessage = jqXHR.responseText;
            	        if (errorMessage.length > 0) {
            	            //console.log(errorMessage);
            	        } 
            	        //showError(errorMessage);
            	        $('#addPensionContributionButton').html("Try again!");
            	        $('#addPensionContributionButton').attr("disabled", false);
            	    }
            	});
        	}else{
        		addPensionContributionButton.innerHTML  = 'Not valid';
        	}
        });
    });
});