//********* Renders/Returns child units on change/select of parent
$(document).on("change", ".update-unit", function(){
	var elementValue = $(this);
	var parentID = elementValue.val();
	/********* Sections *******************/
	$(".update-section").select2();
	var apiUrl = '/get-child-unitAjax/'+parentID;
	if(getContextPath()!=null){
		apiUrl = getContextPath()+""+apiUrl;
		}
	$.ajax({
		url: apiUrl,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{	
				$(".update-section").html("");
				$.each(data,function(i,item){				
					$(".update-section").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
});

//********* Returns employment status reasons when status selected
//Current Employment status reason
var currentStatusReasonID = document.getElementById("currentStatusReason").value;
var currentStatusReasonName = document.getElementById("currentStatusReasonName").value;
$(".edit-status-reasons-via-ajax").append('<option value="'+currentStatusReasonID+'">'+currentStatusReasonName+'</option>')

$(document).on("change", ".update-employment-status", function(){
	var statusValue = $(this);
	var statusID = statusValue.val();
	
	/********* Employment status reasons *******************/
	$(".edit-status-reasons-via-ajax").select2();	
	var apiUrl = '/get-employment-status-reasonsAjax';
	if(getContextPath()!=null){
		apiUrl = getContextPath()+""+apiUrl;
		}
	$.ajax({
		url: apiUrl,
		type: 'GET',
		success:function(data,textStatus,jqXHR){
			if(!data){
				alert("No Data Received");
			}else{	
				$(".status-reasons-via-ajax").html("");
				$.each(data,function(i,item){
					if(statusID == 2 && item.id == 8){
						$(".edit-status-reasons-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					}else if(statusID != 2 && item.id != 8){
						$(".edit-status-reasons-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					}					
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
});

//************* Show the contract end date if specific time is selected

$(document).on("change", ".update-contract", function(){
	var elementValue = $(this);
	var selectedValue = elementValue.val();
	$(".update-contract-end-date").datepicker('setDate', null);
	if(selectedValue == 13 || selectedValue == 0){
		$(".contract-hideShow-status").hide()
	}else{
		$(".contract-hideShow-status").show()
	}
	
})

$(document).ready(function(){
	
/********* Applying Select2 and datePickers to update employee update form *******************/
	//Salutations
	$(".update-salutation").select2();
	$("#updateSalutation").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Designations
	$(".update-designation").select2();
	$("#updateDesignation").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Directorates/Units
	$(".update-unit").select2();
	$("#updateUnit").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Section
	$(".update-section").select2();
	$("#updateSection").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Supervisor
	$(".update-supervisor").select2();
	$("#updateSupervisor").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Contract Types
	$(".update-contract").select2();
	$("#updateContract").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Employment Status
	$(".update-employment-status").select2();
	$("#updateEmploymentStatus").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Gender
	$(".update-gender").select2();
	$("#updateGender").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Marital Status
	$(".update-marital-status").select2();
	$("#updateMaritalStatus").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Nationalities
	$(".update-nationality").select2();
	$("#updateNationality").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Religion
	$(".update-religion").select2();
	$("#updateReligion").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	//Duty Station
	$(".update-duty-station").select2();
	$("#updateDutyStation").select2({
	    dropdownParent: $("#editPersonalInfo")
	  });
	
	
	//Set Date pickers
	$('#updateEndDate').datepicker({
		todayHighlight: true,
		 orientation: "bottom left",
		 format: "yyyy-mm-dd",
		autoclose: true
	});
	
	//Set Date pickers
	$('#updateDob').datepicker({
		todayHighlight: true,
		 orientation: "bottom left",
		 format: "yyyy-mm-dd",
		autoclose: true
	});
	
	$('#updateDateOfEmployment').datepicker({
		todayHighlight: true,
		 orientation: "bottom left",
		 format: "yyyy-mm-dd",
		autoclose: true
	});
	
	//Updating bank information
	$("#addBankAccount").select2({
	    dropdownParent: $("#updateBankInformation")
	  });
	
	//Branches selection
	$(document).on("change", ".update-bank", function(){
		var elementValue = $(this);
		var selectedValue = elementValue.val();
		
		/********* Populate/show branches *******************/
			
		var apiUrl = '/get-bank-branches-by-Ajax/'+selectedValue;
		if(getContextPath()!=null){
			apiUrl = getContextPath()+""+apiUrl;
			}
		$.ajax({
			url: apiUrl,
			type: 'GET',
			success:function(data){
				if(!data){
					alert("No Data Received");
				}else{	
					$(".branches-by-ajax").html("");
					$(".branches-by-ajax").select2();
					$(".branches-by-ajax").select2({
					    dropdownParent: $("#updateBankInformation")
					  });
					$(".branches-by-ajax").append('<option value="">Select Branch</option>')
					$.each(data,function(i,item){
						$(".branches-by-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');					
					});				
				}
			},
			error:function(jqXHR,textStatus,errorThrown){
				alert("Error-"+textStatus+","+errorThrown);
			}
		});	
		
	})

})