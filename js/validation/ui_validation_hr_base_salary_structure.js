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


function showModalStructure(data){

	$('#id').val(data.id);
	$('#scaleIdEdit').val(data.scaleId).trigger("change");;
	$('#notchIdEdit').val(data.notchId).trigger("change");;
	$('#basicSalaryEdit').val(data.basicSalary);
	
	$('#basicsalaryMinEdit').val(data.basicsalaryMax);
	$('#basicsalaryMaxEdit').val(data.basicsalaryMin);
	$('#descriptionEdit').val(data.description);

	// show modal
	$('#editSalStructure').modal();
}

function showStructure(id){
	
	if(id){			
		var $apiUrl = "/v1/salary-structure-by-id/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
		
		$.ajax({
			url:$apiUrl,
			type:'GET',
			statusCode: {            	    	
    	    	208: function(responseObject, textStatus, jqXHR) {
    	            showError("Duplicate Salary structure details not allowed.");
    	        },
    	        401: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are not authorized to peform this action.");
    	        },
    	        404: function(responseObject, textStatus, jqXHR) {
    	        	showError("You are Salary Scale Details not found");
    	        },
    	        412: function(responseObject, textStatus, jqXHR) {
    	        	showError("Your request is not valid, please reveiew and submit again");
    	        },
    	        500: function(responseObject, textStatus, errorThrown) {
    	        	console.log("Server failed to process your request, please try again later");
    	        },
    	        503: function(responseObject, textStatus, errorThrown) {
    	        	showError("Salary Scale service unavailable");
    	        }           
    	    },
			success: function(data, textStatus, jqXHR){
				console.log(JSON.stringify(data));
				// update data
				showModalStructure(data);
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log("Failed to retrieve Salary Scale details");
			}
		});
	}
}

//show details
function delStructure(id){
	if(id){			

		var $apiUrl = "/v1/salary-structure-delete/"+id;
		if(getContextPath()!=null){
			$apiUrl = getContextPath()+""+$apiUrl;
		}
        $.confirm({
            title: 'Warning!',
            content: 'Please confirm to delete Salary Structure.',
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
                    	            showError("Duplicate Salary Structure details not allowed.");
                    	        },
                    	        401: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are not authorized to peform this action.");
                    	        },
                    	        404: function(responseObject, textStatus, jqXHR) {
                    	        	showError("You are Salary Structure Details not found");
                    	        },
                    	        412: function(responseObject, textStatus, jqXHR) {
                    	        	showError("Your request is not valid, please reveiew and submit again");
                    	        },
                    	        500: function(responseObject, textStatus, errorThrown) {
                    	        	console.log("Server failed to process your request, please try again later");
                    	        },
                    	        503: function(responseObject, textStatus, errorThrown) {
                    	        	showError("Salary Structure service unavailable");
                    	        }           
                    	    },
            				success: function(data, textStatus, jqXHR){
            					console.log(JSON.stringify(data));
                                $.confirm({
                                    title: 'Congrats!',
                                    content: 'Salary Structure successfully deleted.',
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
 const updateSalaryStructureBtn = document.getElementById('updateSalaryStructureBtn');
 const formSalaryStructureUpdate = document.getElementById('formSalaryStructureUpdate');
 const fv = FormValidation.formValidation(formSalaryStructureUpdate, {
   fields: { 
	   scaleId:{
     		validators: {
                 notEmpty: {
                     message: 'Salary Scale is required'
                 }
             }
     	},
     	notchId: {
             validators: {
                 notEmpty: {
                     message: 'Notch is required'
                 }
             }
         },
         basicSalary: {
             validators: {
                 notEmpty: {
                     message: 'Basic Salary is required'
                 }
             }
         },
         basicsalaryMax:{
    		validators: {
                notEmpty: {
                    message: 'Basic Salary Max is required'
                }
            }
    	},
    	basicsalaryMin: {
            validators: {
                notEmpty: {
                    message: 'Basic Salary Min is required'
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

 updateSalaryStructureBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){
             
             var id = 	$('#id').val();
         
             var $apiUrl = $('#formSalaryStructureUpdate').attr('action');
             var $data = $('#formSalaryStructureUpdate').serializeObject();//.serializeArray();//serialize();
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