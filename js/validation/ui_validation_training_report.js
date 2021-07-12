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
 const btnRunTrainingReport = document.getElementById('btnRunTrainingReport');
 const fromTrainingReport = document.getElementById('fromTrainingReport');
 const fv = FormValidation.formValidation(fromTrainingReport, {
   fields: { 

         financialyearid: {
             validators: {
                 notEmpty: {
                     message: 'Financial is required'
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
 btnRunTrainingReport.addEventListener('click', function() {
     fv.validate().then(function(status) {
             if(status === 'Valid'){
             
             var $apiUrl = $('#fromTrainingReport').attr('action');
             var $data = $('#fromTrainingReport').serializeObject();//.serializeArray();//serialize();
             var $financialyearid  = parseInt($('#financialyearid').val());
             var $quaterid  = parseInt($('#quaterid').val());
             var $trainingcategoryid  = parseInt($('#trainingcategoryid').val());
             var $financialyear  = $('#financialyearid').text();
        
             console.log('ID=['+$financialyearid+']');
             console.log('Data=['+$data+']');
             console.log('Data=['+JSON.stringify($data)+']');
             $.ajax({
//                 url: $apiUrl+"/"+$id,
             	url: $apiUrl + "/"+ $financialyearid + "/" +$quaterid + "/" + $trainingcategoryid ,
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
         	    	if(data){
            	    	// log
            	        console.log(data);
            	        var status = jqXHR.status;
            	        if(status==200 || status==208){
                	        // parse data
                	        var responseData = JSON.parse(data);
                	        console.log(JSON.stringify(responseData));
                	        console.log("Result-"+responseData);
                	        
                	        // Bind Data
              	          var s = '';
            	          s += '<div class="table-responsive">';
            	          s += '<table id="datatables" class="table table-sm table-bordered">';
            	          s += '<thead>';
            	          s += '<tr>';
            	          s += '<th>S/N</th>';
            	          s += '<th>Employee</th>';
            	          s += '<th>Course Name</th>';
            	          s += '<th>Description</th>';
            	          s += '<th>Institution</th>';
            			  s += '<th>Expected Start Date</th>';
            	          s += '<th>Expected End Date</th>';
            	          s += '<th>Financial Year</th>';
            	          s += '<th>Category</th>';
            	          s += '<th>Type</th>';
            	          s += '<th>Initiator</th>';
            			  s += '<th>Sponsor</th>';
            	          s += '</tr>';
            	          s += '</thead>';
            	          s += '<tbody >';
            				$.each(responseData, function(index,resData) {                   
                            	s += '<tr>';
                            	s += '<td>' + (index + 1) + '</td>';
                            	s += '<td>' + resData.employeeFullName + '</td>';
            					s += '<td>' + resData.name + '</td>';
                            	s += '<td>' + resData.description + '</td>';
            					s += '<td>' + resData.institution + '</td>';
            					s += '<td>' + resData.dateexpectedstart + '</td>';
                           	
//                            	var basicPay = parseFloat(resData.basicPay);
//                	        	var basicPayFormatted = numeral(basicPay).format('0,0.00');
//                            	s += '<td>' + basicPayFormatted + '</td>';
//            					
//            					var grossPay = parseFloat(resData.grossPay);
//                	        	var grossPayFormatted = numeral(grossPay).format('0,0.00');
//                            	s += '<td>' + grossPayFormatted + '</td>';
//            						
//            					var wcfPay = parseFloat(resData.wcfPay);
//                	        	var wcfPayFormatted = numeral(wcfPay).format('0,0.00');
//                            	s += '<td>' + wcfPayFormatted + '</td>';
            					
                            	s += '<td>' + resData.dateexpectedend + '</td>';
            					s += '<td>' + resData.financialyear + '</td>';
            					s += '<td>' + resData.trainingcategory + '</td>';
            					
            					s += '<td>' + resData.trainingtype + '</td>';
            					s += '<td>' + resData.traininginitiator + '</td>';
            					s += '<td>' + resData.trainingsponsor + '</td>';
                            
            					
                            	s += '</tr>';
            				});	
            	      	
            	          s += '</tbody>';
            	          s += '</table>';
            	          s += '</div>';
            	          $('#result').html(s);
                	      $('#datatables').DataTable({
                  	            dom: '<"html5buttons"B>lTfgitp',
                  	            buttons: [
                  	                {extend: 'copy', title: 'Training Report of - ' + $financialyear},
                  	                {extend: 'csv', title: 'Training Report of - ' + $financialyear},
                  	                {extend: 'excel', title: 'Training Report of - ' + $financialyear},
                  	                {extend: 'pdf', title: 'Training Report of - ' + $financialyear},
                  	                {extend: 'print',
                  	                 customize: function (win){
                  	                        $(win.document.body).addClass('white-bg');
                  	                        $(win.document.body).css('font-size', '10px');

                  	                        $(win.document.body).find('table')
                  	                                .addClass('compact')
                  	                                .css('font-size', 'inherit');
                  	                }
                  	                }
                  	            ]

                  	        });
                	        
						  // action
//                	        $('#btnRunContribution').html("Completed");
//                	        $('#btnRunContribution').attr("disabled", true);
//                	        // disable view HESLB
//                	        $('#payrollcycle').attr('disabled',true);
//                	        $('#btnRunContribution').attr('disabled',true);
//                	        // enable view HESLB
//                	        $('#btnViewPayroll').attr('disabled',false);
//                	        // show dialog
                	       // showToast("Contribution report run successfully");
                	        // show result
                	        showPanelResult();
            	        }else{
//                	        $('#btnRunContribution').html("Failed, try again!");
            	        }
        	    	}else{
//        	    		 $('#btnRunContribution').html("No Contribution data, try again!");
        	    	}
        	        // hide
        	        $('#spinnerRunContribution').hide(1000);
            		$('.container-run-contribution').hide(1000);
            		$('#container-run-contribution-result').show();
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