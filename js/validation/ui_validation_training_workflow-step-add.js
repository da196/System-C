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


//DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {

 // Create a FormValidation instance
 const addTrainingWorkflowStepBtn = document.getElementById('addTrainingWorkflowStepBtn');
 const formAddTrainingWorkflowStep = document.getElementById('formUpdateTrainingWorkflowStep');
 const fv = FormValidation.formValidation(formAddTrainingWorkflowStep, {
   fields: { 
	   workflowid:{
     		validators: {
                 notEmpty: {
                     message: 'Workflow Name is required'
                 }
             }
     	},
     	approverdesignationid: {
             validators: {
                 notEmpty: {
                     message: 'Approval Designation is required'
                 }
             }
         },
         approverdesignationnextid: {
             validators: {
                 notEmpty: {
                     message: 'Approval Designation Next is required'
                 }
             }
         },

         approverdesignationprevid: {
             validators: {
                 notEmpty: {
                     message: 'Approval Designation Previous is required'
                 }
             }
         },
         stepnumber: {
             validators: {
                 notEmpty: {
                     message: 'Workflow Description is required'
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
 addTrainingWorkflowStepBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){

             var $apiUrl = $('#formAddTrainingWorkflowStep').attr('action');
             var $data = $('#formAddTrainingWorkflowStep').serializeObject();//.serializeArray();//serialize();
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
                         showError("Duplicate Leave Commutation Workflow Step not allowed.");
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
                             showError("Leave Commutation Workflow Step service unavailable");
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
//                             $('#submitTrainingBtn').html("Saved");
//                             $('#submitTrainingBtn').attr("disabled", true);
                             $.confirm({
                                 title: 'Congrats!',
                                 content: 'Leave Commutation Workflow Step successfully added.',
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
                     }else{
//                             $('#submitTrainingBtn').html("Try again!");
                     }
                 },
                 error: function(jqXHR, textStatus, errorThrown) {
                     var errorMessage = jqXHR.responseText;
                     var statusText = jqXHR.textStatus;
                     var status = jqXHR.status;
                     if (errorMessage.length > 0) {
                             //showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
                     }
//                     $('#submitTrainingBtn').html("Try again!");
//                     $('#submitTrainingBtn').attr("disabled", false);
                 }
             });
             }
//             else{
//             	submitTrainingBtn.innerHTML  = 'Not valid';
//             }
     });
 });
});