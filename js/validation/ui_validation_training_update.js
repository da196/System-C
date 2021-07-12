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
 const updateTrainingBtn = document.getElementById('updateTrainingBtn');
 const formUpdateTraining = document.getElementById('formUpdateTraining');
 const fv = FormValidation.formValidation(formUpdateTraining, {
   fields: { 
	   employeeidEdit:{
     		validators: {
                 notEmpty: {
                     message: 'Employee Name is required'
                 }
             }
     	},
     	name: {
             validators: {
                 notEmpty: {
                     message: 'Course name is required'
                 }
             }
         },
         description: {
             validators: {
                 notEmpty: {
                     message: 'Description is required'
                 }
             }
         },
         dateexpectedstartEdit: {
             validators: {
                 notEmpty: {
                     message: 'Expected Start Date is required'
                 }
             }
         },
         dateexpectedend: {
             validators: {
                 notEmpty: {
                     message: 'Expected End Date is required'
                 }
             }
         },
         financialyearidEdit: {
             validators: {
                 notEmpty: {
                     message: 'Financial is required'
                 }
             }
         },
         trainingcategoryidEdit: {
             validators: {
                 notEmpty: {
                     message: 'Training Category is required'
                 }
             }
         },
         trainingtypeidEdit: {
             validators: {
                 notEmpty: {
                     message: 'Training Type is required'
                 }
             }
         },
         traininginitiatoridEdit: {
             validators: {
                 notEmpty: {
                     message: 'Training Initiator is required'
                 }
             }
         },
         trainingsponsoridEdit: {
             validators: {
                 notEmpty: {
                     message: 'Training Sponsor is required'
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
 updateTrainingBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){
             

             var $apiUrl = $('#formUpdateTraining').attr('action');
             var form = $("#formUpdateTraining")[0];
             var $data = new FormData(form);
             var $id  = parseInt($('#id').val());

             $.ajax({
                 type : "POST",
                 encType : "multipart/form-data",
                 url : $apiUrl + "/" +$id,
                 cache : false,
                 processData : false,
                 contentType : false,
                 data : $data,
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

                             $.confirm({
                                 title: 'Congrats!',
                                 content: 'Training successfully updated.',
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