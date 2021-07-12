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
 const submitTrainingBtn = document.getElementById('submitTrainingBtn');
 const formAddTraining = document.getElementById('formAddTraining');
 const fv = FormValidation.formValidation(formAddTraining, {
   fields: { 
     	employeeid:{
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
         dateexpectedstart: {
             validators: {
                 notEmpty: {
                     message: 'Expected Start Date is required'
                 }
             }
         },
         date_expected_end: {
             validators: {
                 notEmpty: {
                     message: 'Expected End Date is required'
                 }
             }
         },
         financialyearid: {
             validators: {
                 notEmpty: {
                     message: 'Financial is required'
                 }
             }
         },
         trainingcategoryid: {
             validators: {
                 notEmpty: {
                     message: 'Training Category is required'
                 }
             }
         },
         trainingtypeid: {
             validators: {
                 notEmpty: {
                     message: 'Training Type is required'
                 }
             }
         },
         traininginitiatorid: {
             validators: {
                 notEmpty: {
                     message: 'Training Initiator is required'
                 }
             }
         },
         trainingsponsorid: {
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
// .on('core.form.validating', function() {
// 	submitTrainingBtn.innerHTML = 'Validating ...';
// });
 // click listener
 submitTrainingBtn.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){

             var $apiUrl = $('#formAddTraining').attr('action');
             var form = $("#formAddTraining")[0];
             var $data = new FormData(form);

             $.ajax({
                 type : "POST",
                 encType : "multipart/form-data",
                 url : $apiUrl,
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
                              // update UI
//                             $('#submitTrainingBtn').html("Saved");
//                             $('#submitTrainingBtn').attr("disabled", true);
                             $.confirm({
                                 title: 'Congrats!',
                                 content: 'Training successfully added.',
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