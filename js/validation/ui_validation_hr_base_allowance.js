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


function showModalAllowance(data){

	$('#id').val(data.id);
	$('#allowancetypeidAllowanceEdit').val(data.allowancetypeid).trigger("change");;
	$('#salarystructureidAllowanceEdit').val(data.salarystructureid).trigger("change");;
	$('#designationidAllowanceEdit').val(data.designationid).trigger("change");
	
	$('#employmentcategoryidAllowanceEdit').val(data.employmentcategoryid).trigger("change");
	$('#descriptionAllowanceEdit').val(data.description);
	$('#amountAllowanceEdit').val(data.amount);

	// show modal
	$('#editAllowance').modal();
}

function showAllowance(id){

	if(id){			
		var $apiUrl = "/v1/allowance-by-id/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
		
		$.ajax({
			url:$apiUrl,
			type:'GET',
			statusCode: {            	    	
    	    	208: function(responseObject, textStatus, jqXHR) {
    	            showError("Duplicate Allowance details not allowed.");
    	        },
    	        401: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are not authorized to peform this action.");
    	        },
    	        404: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are Allowance Details not found");
    	        },
    	        412: function(responseObject, textStatus, jqXHR) {
    	        	showError("Your request is not valid, please reveiew and submit again");
    	        },
    	        500: function(responseObject, textStatus, errorThrown) {
    	        	console.log("Server failed to process your request, please try again later");
    	        },
    	        503: function(responseObject, textStatus, errorThrown) {
    	        	showError("Allowance service unavailable");
    	        }           
    	    },
			success: function(data, textStatus, jqXHR){
				console.log(JSON.stringify(data));
				// update data
				showModalAllowance(data);
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("Failed to retrieve Allowance details");
			}
		});
	}
}

//show details
function delAllowance(id){
	if(id){			

		var $apiUrl = "/v1/allowance-delete/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
        $.confirm({
            title: 'Warning!',
            content: 'Please confirm to delete Allowance.',
            type: 'red',
            buttons: {          	
            	 cancel: {
                     text: "Cancel",
                     btnClass: 'btn-info',
                     keys: ['enter'],
                     action: function(){
                     	 window.location.reload(true);
                     }
                 },
                ok: {
                    text: "ok",
                    btnClass: 'btn-primary',
                    keys: ['enter'],
                    action: function(){
            			$.ajax({
            				url:$apiUrl,
            				type:'GET',
            				statusCode: {            	    	
                    	    	208: function(responseObject, textStatus, jqXHR) {
                    	            showError("Duplicate Allowance details not allowed.");
                    	        },
                    	        401: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are not authorized to peform this action.");
                    	        },
                    	        404: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are Allowance Details not found");
                    	        },
                    	        412: function(responseObject, textStatus, jqXHR) {
                    	        	showError("Your request is not valid, please reveiew and submit again");
                    	        },
                    	        500: function(responseObject, textStatus, errorThrown) {
                    	        	console.log("Server failed to process your request, please try again later");
                    	        },
                    	        503: function(responseObject, textStatus, errorThrown) {
                    	        	showError("Allowance service unavailable");
                    	        }           
                    	    },
            				success: function(data, textStatus, jqXHR){
            					console.log(JSON.stringify(data));
                                $.confirm({
                                    title: 'Congrats!',
                                    content: 'Allowance successfully deleted.',
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
            					console.log("Failed to retrieve Salary Scale details");
            				}
            			});
                    }
                }
            }
        });	

	}
}
//DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {

 // Create a FormValidation instance
 const updateAllowanceBtn = document.getElementById('updateAllowanceBtn');
 const formAUpdateAllowance = document.getElementById('formAUpdateAllowance');
 const fv = FormValidation.formValidation(formAUpdateAllowance, {
   fields: { 
	   allowancetypeid:{
     		validators: {
                 notEmpty: {
                     message: 'Allowance Type is required'
                 }
             }
     	},
     	salarystructureid: {
             validators: {
                 notEmpty: {
                     message: 'Salary Scale is required'
                 }
             }
         },
         designationid: {
             validators: {
                 notEmpty: {
                     message: 'Designation is required'
                 }
             }
         },
         employmentcategoryid:{
    		validators: {
                notEmpty: {
                    message: 'Employment Category is required'
                }
            }
    	},
    	amount: {
            validators: {
                notEmpty: {
                    message: 'Amount is required'
                }
            }
        },
//        description: {
//            validators: {
//                notEmpty: {
//                    message: 'Description is required'
//                }
//            }
//        }
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

 updateAllowanceBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){
             
             var id = 	$('#id').val();
         
             var $apiUrl = $('#formAUpdateAllowance').attr('action');
             var $data = $('#formAUpdateAllowance').serializeObject();//.serializeArray();//serialize();
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
                         showError("Duplicate Salary Structure details not allowed.");
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
                             showError("Salary Structure service unavailable");
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
                                 content: 'Salary Structure successfully updated.',
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