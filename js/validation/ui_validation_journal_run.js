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
function showToast(message){
	$.dialog({
	    title: '',
	    content: message,
	});
}
function showPanelResult(){
	$("#container-run-journal-result").removeAttr("hide");
	$("#container-run-journal-result").removeAttr("hidden");
	$('#container-run-journal-result').show();
}
// DOM loaded
document.addEventListener('DOMContentLoaded', function(e) {
    // Create a FormValidation instance    	
	const btnRunJournal = document.getElementById('btnRunJournal');
    const formAddLoanDetailHeslb = document.getElementById('formRunJournal');     
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
    	btnRunJournal.innerHTML = 'Validating ...';
    });
    // click listener
    btnRunJournal.addEventListener('click', function() {
        fv.validate().then(function(status) {
        	if(status === 'Valid'){
        		// show progress
        		console.log("Valid");
        		
        		$('#spinnerRunJournal').show();
        		// parse
        		var $apiUrl = $('#formRunJournal').attr('action');
            	var $data = $('#formRunJournal').serializeObject();//.serializeArray();//serialize();
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
                    	        var journal = JSON.parse(data);
                    	        console.log(JSON.stringify(journal));
                    	        console.log("Result-"+journal);
               	        	 	// update UI
                    	        var totalCredit = 0;
                    	        var totalDebit = 0;
                    	        // date
                    	        var journalDate = moment(journal.payday, "YYYY-MM-DD").format("MMMM, YYYY");
                    	        $('#payroll-date').text(journalDate);
                    	        // net pay
                    	        if(journal.netpay>=0){
                    	        	var netPay = numeral(journal.netpay).format('0,0.00');
                        	        $('#journal-pay-net').text(netPay);
                        	        // update debit
                        	        totalCredit = totalCredit+journal.netpay;
                        	    }
                    	        //---./net pay
                    	        // basic pay
                    	        if(journal.basicpay>=0){
                    	        	var basicPay = numeral(journal.basicpay).format('0,0.00');
                        	        $('#journal-pay-basic').text(basicPay);
                        	        // update debit
                        	        totalDebit = totalDebit+journal.basicpay;
                        	    }
                    	        //---./basic pay
                    	        if(journal.psssfemployerpay>=0 || journal.psssfpay>=0){
                    	        	// employer
                    	        	var psssfPayEmployer = numeral(journal.psssfemployerpay).format('0,0.00');
                        	        $('#journal-pay-pension-psssf-employer').text(psssfPayEmployer);
                        	        // update debit
                        	        totalDebit = totalDebit+journal.psssfemployerpay;
                    	        	// employee
                    	        	var psssfPay = numeral(journal.psssfpay).format('0,0.00');
                        	        $('#journal-pay-pension-psssf').text(psssfPay);
                        	        // update credit
                        	        totalCredit = totalCredit+journal.psssfpay;
                        	    }
                    	        //---./psssf
                    	        if(journal.zssfemployerpay>=0 || journal.zssfpay>=0){
                    	        	// employer
                    	        	var zssfPayEmployer = numeral(journal.zssfemployerpay).format('0,0.00');
                        	        $('#journal-pay-pension-zssf-employer').text(zssfPayEmployer);
                        	        // update debit
                        	        totalDebit = totalDebit+journal.zssfemployerpay;
                    	        	// employee
                    	        	var zssfPay = numeral(journal.zssfpay).format('0,0.00');
                        	        $('#journal-pay-pension-zssf').text(zssfPay);
                        	        // update credit
                        	        totalCredit = totalCredit+journal.zssfpay;
                        	    }
                    	        //---./zssf
                    	        if(journal.allowancelist){
                    	        	$.each(journal.allowancelist,function(k,allowance){
                    	        		var allowanceCode = allowance.allowancetypeCode;
                    	        		if(allowance && allowanceCode){
                    	        			// log
                    	        			console.log("[Key,Value]="+k+","+allowanceCode);
                    	        			// update allowance UI
                    	        			if(allowanceCode==1){
                    	        				// update servant allowance
                    	        				var allowanceAmount = allowance.amountsalaryallowance;
                    	        				if(allowanceAmount){
                    	        					allowanceAmount = parseFloat(allowanceAmount);
                        	        				var allowanceAmountFormatted = numeral(allowanceAmount).format('0,0.00');
                                        	        $('#journal-pay-allowance-hospitality').text(allowanceAmountFormatted);
                                        	        // update debit
                                        	        totalDebit = totalDebit+allowanceAmount;
                    	        				}
                    	        			}else if(allowanceCode==2){
                    	        				// update house allowance
                    	        				var allowanceAmount = allowance.amountsalaryallowance;
                    	        				if(allowanceAmount){
                    	        					allowanceAmount = parseFloat(allowanceAmount);
                        	        				var allowanceAmountFormatted = numeral(allowanceAmount).format('0,0.00');
                                        	        $('#journal-pay-allowance-house').text(allowanceAmountFormatted);
                                        	        // update debit
                                        	        totalDebit = totalDebit+allowanceAmount;
                    	        				}
                    	        			}else if(allowanceCode==3){
                    	        				// update transport allowance
                    	        				var allowanceAmount = allowance.amountsalaryallowance;
                    	        				if(allowanceAmount){
                    	        					allowanceAmount = parseFloat(allowanceAmount);
                        	        				var allowanceAmountFormatted = numeral(allowanceAmount).format('0,0.00');
                                        	        $('#journal-pay-allowance-transport').text(allowanceAmountFormatted);
                                        	        // update debit
                                        	        totalDebit = totalDebit+allowanceAmount;
                    	        				}
                    	        			}
                    	        		}
                    	        	});
                        	    }
                    	        //---./allowance
                    	        /*if(journal.netpay){
                    	        	var payNET = journal.netpay;
                    	        	var payNETFormatted = numeral(payNET).format('0,0.00');
                        	        $('#journal-pay-net').text(payNETFormatted);
                        	        // update credit
                        	        totalCredit = totalCredit+payNET;
                    	        }*/
                    	        //---./NET Pay
                    	        if(journal.deductionloanlist){
                    	        	$.each(journal.deductionloanlist,function(k,loan){
                    	        		var loanCode = loan.loantypeCode;
                    	        		if(loan && loanCode){
                    	        			// salary advance
                    	        			if(loanCode==1){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-salary-advance').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        			// HESLB
                    	        			if(loanCode==2){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-heslb').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        			// TCRA SACCOS
                    	        			if(loanCode==3){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-tcra-saccos').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        			// Azania Bank
                    	        			if(loanCode==4){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-bank-azania').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        			// Residential house
                    	        			if(loanCode==5){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-residential-house').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        			// Transport loan
                    	        			if(loanCode==6){
                    	        				var loanAmount = loan.amountdeductionloan;
                    	        				if(loanAmount){
                    	        					loanAmount = parseFloat(loanAmount);
                        	        				var loanAmountFormatted = numeral(loanAmount).format('0,0.00');
                                        	        $('#journal-pay-loan-transport').text(loanAmountFormatted);
                                        	        // update credit
                                        	        totalCredit = totalCredit+loanAmount;
                    	        				}
                    	        			}
                    	        		}
                    	        	});
                    	       	}
                    	        //----./loan
                    	        if(journal.deductionvoluntarylist){
                    	        	var amountTCRASACCOS = 0;
                    	        	$.each(journal.deductionvoluntarylist,function(k,voluntary){
                    	        		var voluntaryCode = voluntary.voluntaryContributiontypeCode;
                    	        		if(voluntary && voluntaryCode){
                    	        			//SACCOS membership
                    	        			if(voluntaryCode==1){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					amountTCRASACCOS = amountTCRASACCOS + voluntaryAmount;                        	        				
                    	        				}
                    	        			}
                    	        			//SACCOS saving
                    	        			if(voluntaryCode==2){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					amountTCRASACCOS = amountTCRASACCOS + voluntaryAmount;                        	        				
                    	        				}
                    	        			}
                    	        			//SACCOS amana
                    	        			if(voluntaryCode==3){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					amountTCRASACCOS = amountTCRASACCOS + voluntaryAmount;                        	        				
                    	        				}
                    	        			}
                    	        			//MKM
                    	        			if(voluntaryCode==4){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					var amountMKMFormatted = numeral(voluntaryAmount).format('0,0.00');
                                        	        $('#journal-pay-contribution-tcra-mkm').text(amountMKMFormatted);
                                        	    	// update credit
                                        	        totalCredit = totalCredit+voluntaryAmount;
                    	        				}
                    	        			}
                    	        			//POSTA na SIMU
                    	        			if(voluntaryCode==5){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					var amountPostaFormatted = numeral(voluntaryAmount).format('0,0.00');
                                        	        $('#journal-pay-contribution-tcra-posta-simu').text(amountPostaFormatted);
                                        	    	// update credit
                                        	        totalCredit = totalCredit+voluntaryAmount;
                    	        				}
                    	        			}
                    	        			//Jubilee
                    	        			if(voluntaryCode==6){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					var amountJubileeFormatted = numeral(voluntaryAmount).format('0,0.00');
                                        	        $('#journal-pay-insurance-jubilee').text(amountJubileeFormatted);
                                        	    	// update credit
                                        	        totalCredit = totalCredit+voluntaryAmount;
                    	        				}
                    	        			}
                    	        			//NIC
                    	        			if(voluntaryCode==7){
                    	        				var voluntaryAmount = voluntary.amountdeductionvoluntary;
                    	        				if(voluntaryAmount){
                    	        					voluntaryAmount = parseFloat(voluntaryAmount);
                    	        					var amountNICFormatted = numeral(voluntaryAmount).format('0,0.00');
                                        	        $('#journal-pay-insurance-nic').text(amountNICFormatted);
                                        	    	// update credit
                                        	        totalCredit = totalCredit+voluntaryAmount;
                    	        				}
                    	        			}
                    	        		}
                    	        	});
                    	        	// TCRA SACCOS
                    	        	// update credit
                    	        	var amountTCRASACCOSFormatted = numeral(amountTCRASACCOS).format('0,0.00');
                        	        $('#journal-pay-contribution-tcra-saccos').text(amountTCRASACCOSFormatted);
                        	        totalCredit = totalCredit+amountTCRASACCOS;
                    	        }
                    	        //----./deduction voluntary
                    	        if(journal.payepay>=0){
                    	        	var paye = numeral(journal.payepay).format('0,0.00');
                        	        $('#journal-pay-tax-paye').text(paye);
                        	        // update credit
                        	        totalCredit = totalCredit+journal.payepay;
                        	    }
                    	        //----./PAYE
                    	        if(journal.debttotal>=0){
                    	        	var debit = parseFloat(journal.debttotal);
                    	        	var debitFormatted = numeral(debit).format('0,0.00');
                        	        $('#journal-pay-debit-received').text(debitFormatted);
                        	    }
                    	        if(totalDebit>0){
                    	        	var debitFormatted = numeral(totalDebit).format('0,0.00');
                    	        	$('#journal-pay-debit').text(debitFormatted);
                    	        }
                    	        //----./debit
                    	        if(journal.credittotal>=0){
                    	        	var credit = parseFloat(journal.credittotal);
                    	        	var creditFormatted = numeral(credit).format('0,0.00');
                        	        $('#journal-pay-credit-received').text(creditFormatted);
                        	    }
                    	        if(totalCredit>0){
                    	        	var creditFormatted = numeral(totalCredit).format('0,0.00');
                    	        	$('#journal-pay-credit').text(creditFormatted);
                    	        }
                    	        //----./credit
                    	        if(journal.miscelaneouspay){
                    	        	var miscIncome = parseFloat(journal.miscelaneouspay);
                    	        	var miscIncomeFormatted = numeral(miscIncome).format('0,0.00');
                        	        $('#journal-pay-income-misc').text(miscIncomeFormatted);
                        	    }
                    	        //----./misc income
                    	        if(journal.otherincome>=0){
                    	        	var otherIncome = parseFloat(journal.otherincome);
                    	        	var otherIncomeFormatted = numeral(otherIncome).format('0,0.00');
                        	        $('#journal-pay-income-other').text(otherIncomeFormatted);
                        	    }
                    	        //----./other income
                    	        // action
                    	        $('#btnRunJournal').html("Completed");
                    	        $('#btnRunJournal').attr("disabled", true);
                    	        // disable view journal
                    	        $('#payrollcycle').attr('disabled',true);
                    	        $('#btnRunJournal').attr('disabled',true);
                    	        // enable view journal
                    	        $('#btnViewPayroll').attr('disabled',false);
                    	        // show dialog
                    	        showToast("Payroll journal run successfully");
                    	        // show result
                    	        showPanelResult();
                	        }else{
                    	        $('#btnRunJournal').html("Failed, try again!");
                	        }
            	    	}else{
            	    		 $('#btnRunJournal').html("No journal data, try again!");
            	    	}
            	        // hide
            	        $('#spinnerRunJournal').hide(1000);
                		$('.container-run-journal').hide(1000);
                		$('#container-run-journal-result').show();
            	    },
            	    error: function(jqXHR, textStatus, errorThrown) {
            	    	
            	        var errorMessage = jqXHR.responseText;
            	        var statusText = jqXHR.textStatus;
            	        var status = jqXHR.status;
            	        if (errorMessage.length > 0) {
            	        	showError("Message="+errorMessage+",Status="+status+",StatusText="+statusText);
            	        } 
            	        $('#btnRunJournal').html("Try again!");
            	        $('#btnRunJournal').attr("disabled", false);
            	        // hide
            	        $('#spinnerRunJournal').hide(1000);
            	    }
            	});
        	}else{
        		btnRunJournal.innerHTML  = 'Not valid';
        	}
        });
    });
});