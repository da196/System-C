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
function showToast(message){
	$.dialog({
	    title: '',
	    content: message,
	});
}
function showPanelResult(){
	$("#container-run-contribution-result").removeAttr("hide");
	$("#container-run-contribution-result").removeAttr("hidden");
	$('#container-run-spinnerRunHeslb-result').show();
}

//DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    	
	const btnRunContribution = document.getElementById('btnRunContribution');
    const formAddLoanDetailHeslb = document.getElementById('formRunContribution');     
    const fv = FormValidation.formValidation(formAddLoanDetailHeslb, {
        fields: { 
        	payrollcycle:{
        		validators: {
                    notEmpty: {
                        message: 'Payroll cycle is required'
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
    	btnRunContribution.innerHTML = 'Validating ...';
    });
    // click listener
    btnRunContribution.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		// show progress
        		console.log("Valid");
        		var startDate = document.getElementById("payrollcycle").value;
        		//alert(startDate)
    	        var journalDate = moment(startDate, "DD-MM-YYYY").format("MMMM, YYYY");
        		//alert(journalDate)
    	        $('#payroll-date').text(journalDate);
        		
        		$('#spinnerRunContribution').show();
        		// parse
        		var $apiUrl = $('#formRunContribution').attr('action');
            	var $data = $('#formRunContribution').serializeObject();//.serializeArray();//serialize();
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
            	            //showError("Payroll date closed. Payroll data retrieved successfully.");
            	        },
            	        401: function(responseObject, textStatus, jqXHR) {
            	        	showError("You are not authorized to run journal.");
            	        },
            	        404: function(responseObject, textStatus, jqXHR) {
            	        	showError("Running journal data not found.");
            	        },
            	        412: function(responseObject, textStatus, jqXHR) {
            	        	showError("Run journal request not valid, please reveiew and submit again");
            	        },
            	        500: function(responseObject, textStatus, errorThrown) {
            	        	showError("System failed to run journal, please try again later");
            	        },
            	        503: function(responseObject, textStatus, errorThrown) {
            	        	showError("Run journal service unavailable");
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
                  	          s += '<th>First Name</th>';
                  	          s += '<th>Middle Name</th>';
                  	          s += '<th>Last Name</th>';
                  	          s += '<th>Gender</th>';
                  			  s += '<th>Date of Birth</th>';
                  	          s += '<th>Basic Pay</th>';
                  	          s += '<th>Gross Pay </th>';
                  	          s += '<th>Employee Contribution</th>';
                  	          s += '<th>Employer Contribution</th>';
                  	        
                  	          s += '</tr>';
                  	          s += '</thead>';
                  	          s += '<tbody >';
                  				$.each(responseData.healthInsuarancelist, function(index,resData) {                   
                                  	s += '<tr>';
                                  	s += '<td>' + (index + 1) + '</td>';
                                  	s += '<td>' + resData.firstName + '</td>';
                  					s += '<td>' + resData.middleName + '</td>';
                                  	s += '<td>' + resData.lastName + '</td>';
                  					s += '<td>' + resData.gender + '</td>';
                  					s += '<td>' + resData.dateOfBirth + '</td>';
                                  	
                                  	var basicPay = parseFloat(resData.basicPay);
                      	        	var basicPayFormatted = numeral(basicPay).format('0,0.00');
                                  	s += '<td>' + basicPayFormatted + '</td>';
                  					
                  					var grossPay = parseFloat(resData.grossPay);
                      	        	var grossPayFormatted = numeral(grossPay).format('0,0.00');
                                  	s += '<td>' + grossPayFormatted + '</td>';
                  						
                  					var nhifPay = parseFloat(resData.nhifPay) / 2;
                      	        	var nhifPayFormatted = numeral(nhifPay).format('0,0.00') ;
                                  	s += '<td>' + nhifPayFormatted + '</td>';
                                	s += '<td>' + nhifPayFormatted + '</td>';
                  					
                                  	s += '</tr>';
                  				});	
                  	      	
                  	          s += '</tbody>';
                  	          s += '</table>';
                  	          s += '</div>';
                  	          $('#result').html(s);
//                  	          $('#datatables').DataTable( {
//                  	        	  dom: 'Bfrtip',
//                  		            buttons: [
//                  		            	
//                  		                'csv', 'excel'
//                  		                //, 'pdf', 'print'
//                  		                
//                  		            ]
//                  	           } );
                  	        $('#datatables').DataTable({
                  	            dom: '<"html5buttons"B>lTfgitp',
                  	            buttons: [
                  	                {extend: 'copy', title: 'NHIF Report of - ' + journalDate},
                  	                {extend: 'csv', title: 'NHIF Report of - ' + journalDate},
                  	                {extend: 'excel', title: 'NHIF Report of - ' + journalDate},
                  	                {extend: 'pdf', title: 'NHIF Report of - ' + journalDate},
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
                    	        $('#btnRunContribution').html("Completed");
                    	        $('#btnRunContribution').attr("disabled", true);
                    	        // disable view HESLB
                    	        $('#payrollcycle').attr('disabled',true);
                    	        $('#btnRunContribution').attr('disabled',true);
                    	        // enable view HESLB
                    	        $('#btnViewPayroll').attr('disabled',false);
                    	        // show dialog
                    	        showToast("Contribution report run successfully");
                    	        // show result
                    	        showPanelResult();
                	        }else{
                    	        $('#btnRunContribution').html("Failed, try again!");
                	        }
            	    	}else{
            	    		 $('#btnRunContribution').html("No Contribution data, try again!");
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
            	        	showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#btnRunContribution').html("Try again!");
            	        $('#btnRunContribution').attr("disabled", false);
            	        // hide
            	        $('#spinnerRunContribution').hide(1000);
            	    }
            	});
        	}else{
        		btnRunContribution.innerHTML  = 'Not valid';
        	}
        });
    });
});