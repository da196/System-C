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
	$("#container-run-heslb-result").removeAttr("hide");
	$("#container-run-heslb-result").removeAttr("hidden");
	$('#container-run-heslb-result').show();
}

//DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    	
	const btnRunHeslb = document.getElementById('btnRunHeslb');
    const formAddLoanDetailHeslb = document.getElementById('formRunHeslb');     
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
    	btnRunHeslb.innerHTML = 'Validating ...';
    });
    // click listener
    btnRunHeslb.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		// show progress
        		console.log("Valid");
        		var startDate = document.getElementById("payrollcycle").value;
        		//alert(startDate)
    	        var journalDate = moment(startDate, "DD-MM-YYYY").format("MMMM, YYYY");
    	        
    	        $('#payroll-date').text(journalDate);
        		
        		$('#spinnerRunHeslb').show();
        		// parse
        		var $apiUrl = $('#formRunHeslb').attr('action');
            	var $data = $('#formRunHeslb').serializeObject();//.serializeArray();//serialize();
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
                	          s += '<table class="table table-sm table-bordered">';
                	          s += '<thead>';
                	          s += '<tr>';
                	          s += '<th>S/N</th>';
                	          s += '<th>PF NO.</th>';
                	          s += '<th>FORM FOUR INDRX NO. ( as provided in the bill schedule</th>';
                	          s += '<th>FULL NAME AS USED DURING STUDY</th>';
                	          s += '<th>AMOUNT DEDUCTED</th>';
                	          s += '</tr>';
                	          s += '</thead>';
                	          s += '<tbody >';
                	          $.each(responseData.heslbReportlist, function(index,resData) {        
                	           
                	                s += '<tr>';
                				    s += '<td>' + (index + 1) + '</td>';
                					s += '<td>' + resData.fileNumber + '</td>';
                					s += '<td>' + resData.formFourIndexNumber + '</td>';
                					s += '<td>' + resData.fullnameAsperHeslb + '</td>';
                					var amountDuducted = parseFloat(resData.amountDeducted);
    	        					var amountDuductedFormatted = numeral(amountDuducted).format('0,0.00');
                					s += '<td>' + amountDuductedFormatted + '</td>';
                					s += '</tr>';
                	          });	
                	          s += '<tr>';
                	            var totalAmountDeducted = parseFloat(responseData.totalAmountDeducted);
	        					var totalAmountDeductedFormatted = numeral(totalAmountDeducted).format('0,0.00');
                	          s += '<td colspan="4" class="text-right text-bold"> <b>' + 'Total' + ' </b></td>';
                	          s += '<td><b>' + totalAmountDeductedFormatted + '</b></td>';
                	          s += '</tr>';
                	          s += '</tbody>';
                	          s += '</table>';
                	          s += '</div>';
                	          $('#result').html(s);
                    	        
							  // action
                    	        $('#btnRunHeslb').html("Completed");
                    	        $('#btnRunHeslb').attr("disabled", true);
                    	        // disable view HESLB
                    	        $('#payrollcycle').attr('disabled',true);
                    	        $('#btnRunHeslb').attr('disabled',true);
                    	        // enable view HESLB
                    	        $('#btnViewPayroll').attr('disabled',false);
                    	        // show dialog
                    	        showToast("HESLB report run successfully");
                    	        // show result
                    	        showPanelResult();
                	        }else{
                    	        $('#btnRunHeslb').html("Failed, try again!");
                	        }
            	    	}else{
            	    		 $('#btnRunHeslb').html("No HESLB data, try again!");
            	    	}
            	        // hide
            	        $('#spinnerRunHeslb').hide(1000);
                		$('.container-run-heslb').hide(1000);
                		$('#container-run-heslb-result').show();
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	    	
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#btnRunHeslb').html("Try again!");
            	        $('#btnRunHeslb').attr("disabled", false);
            	        // hide
            	        $('#spinnerRunHeslb').hide(1000);
            	    }
            	});
        	}else{
        		btnRunHeslb.innerHTML  = 'Not valid';
        	}
        });
    });
});