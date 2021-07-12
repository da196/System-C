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


function showModalAllowaceType(data){
	$('#id').val(data.id);
	$('#abbreviationAllowanceTypeEdit').val(data.abbreviation);
	$('#nameAllowanceTypeEdit').val(data.name);
	$('#descriptionAllowanceTypeEdit').val(data.description);

	// show modal
	$('#editlAllowaceType').modal();
}

function showAllowanceType(id){
	
	if(id){			
		var $apiUrl = "/v1/allowance-type-by-id/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
		
		$.ajax({
			url:$apiUrl,
			type:'GET',
			statusCode: {            	    	
    	    	208: function(responseObject, textStatus, jqXHR) {
    	            showError("Duplicate Allowance Type details not allowed.");
    	        },
    	        401: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are not authorized to peform this action.");
    	        },
    	        404: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are Allowance Type Details not found");
    	        },
    	        412: function(responseObject, textStatus, jqXHR) {
    	        	showError("Your request is not valid, please reveiew and submit again");
    	        },
    	        500: function(responseObject, textStatus, errorThrown) {
    	        	console.log("Server failed to process your request, please try again later");
    	        },
    	        503: function(responseObject, textStatus, errorThrown) {
    	        	showError("Allowance Type service unavailable");
    	        }           
    	    },
			success: function(data, textStatus, jqXHR){
				console.log(JSON.stringify(data));
				// update data
				showModalAllowaceType(data);
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("Failed to retrieve Allowance Type details");
			}
		});
	}
}

//show details
function delAllowanceType(id){
	if(id){			

		var $apiUrl = "/v1/allowance-type-delete/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
        $.confirm({
            title: 'Warning!',
            content: 'Please confirm to delete Allowance Type.',
            type: 'red',
            buttons: {
                ok: {
                    text: "ok",
                    btnClass: 'btn-danger',
                    keys: ['enter'],
                    action: function(){
            			$.ajax({
            				url:$apiUrl,
            				type:'GET',
            				statusCode: {            	    	
                    	    	208: function(responseObject, textStatus, jqXHR) {
                    	            showError("Duplicate Allowance Type details not allowed.");
                    	        },
                    	        401: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are not authorized to peform this action.");
                    	        },
                    	        404: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are Allowance Type Details not found");
                    	        },
                    	        412: function(responseObject, textStatus, jqXHR) {
                    	        	showError("Your request is not valid, please reveiew and submit again");
                    	        },
                    	        500: function(responseObject, textStatus, errorThrown) {
                    	        	console.log("Server failed to process your request, please try again later");
                    	        },
                    	        503: function(responseObject, textStatus, errorThrown) {
                    	        	showError("Allowance Type service unavailable");
                    	        }           
                    	    },
            				success: function(data, textStatus, jqXHR){
            					console.log(JSON.stringify(data));
                                $.confirm({
                                    title: 'Congrats!',
                                    content: 'Allowance Type successfully deleted.',
                                    type: 'green',
                                    buttons: {
                                        ok: {
                                            text: "ok",
                                            btnClass: 'btn-primary',
                                            keys: ['enter'],
                                            action: function(){
                                                // close dialog
                                               // hideEditHeslbLoanModal();
                                                // reload
                                                window.location.reload(true);
                                            }
                                        },
                                    }
                                });	
                                },
            				error:function(jqXHR, textStatus, errorThrown){
            					console.log("Failed to retrieve Allowance Type details");
            				}
            			});
                    }
                },  cancel: {
                    text: "Cancel",
                    btnClass: 'btn-info',
                    keys: ['enter'],
                    action: function(){
                    	 window.location.reload(true);
                    }
                }
            }
        });	

	}
}
//DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {

 // Create a FormValidation instance
 const allowanceTypeBtn = document.getElementById('allowanceTypeBtn');
 const formAUpdateAllowanceType = document.getElementById('formAUpdateAllowanceType');
 const fv = FormValidation.formValidation(formAUpdateAllowanceType, {
   fields: { 
	   abbreviation:{
     		validators: {
                 notEmpty: {
                     message: 'Abbreviation is required'
                 }
             }
     	},
     	name: {
             validators: {
                 notEmpty: {
                     message: 'Name is required'
                 }
             }
         },
         description: {
             validators: {
                 notEmpty: {
                     message: 'Description is required'
                 }
             }
         }
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

 allowanceTypeBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){
             
             var id = 	$('#id').val();
             var $apiUrl = $('#formAUpdateAllowanceType').attr('action');
             var $data = $('#formAUpdateAllowanceType').serializeObject();//.serializeArray();//serialize();
         	 console.log('Data = ['+$data+']');
         	 console.log('Data = ['+JSON.stringify($data)+']');
             $.ajax({
         	    url: $apiUrl + "/" + id,
        	    type: 'POST',
        	    contentType: 'application/json',
        	    dataType: 'text',
        	    headers: {
        	    	"Accept": "application/json",
        	    },
        	    data: JSON.stringify($data),
                 statusCode: {
                     208: function(responseObject, textStatus, jqXHR) {
                         showError("Duplicate Allowance Type details not allowed.");
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
                             showError("Allowance Type service unavailable");
                     }
                 },
                 success: function(data, textStatus, jqXHR) {
                     // parse data
                     var contribution = data;
                     console.log(data);
                     console.log(JSON.stringify(contribution));
                     var status = jqXHR.status;
                     if(status==200){
                             $.confirm({
                                 title: 'Congrats!',
                                 content: 'Allowance Type successfully updated.',
                                 type: 'green',
                                 buttons: {
                                     ok: {
                                         text: "ok",
                                         btnClass: 'btn-primary',
                                         keys: ['enter'],
                                         action: function(){
                                             window.location.reload(true);
                                         }
                                     },
                                 }
                             });
                     }else{

                     }
                 }
             });
             }

     });
 });
});