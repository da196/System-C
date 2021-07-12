
//********* Renders/Returns child units on change/select of parent

$(document).on("change", ".directorates-and-units-via-ajax", function(){
	var elementValue = $(this);
	var parentID = elementValue.val();
	/********* Sections *******************/
	$(".child-sections-via-ajax").select2();
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
				$(".child-sections-via-ajax").html("");
				$.each(data,function(i,item){				
					$(".child-sections-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
});


//********* Returns employment status reasons when status selected

$(document).on("change", ".employment-statuses-via-ajax", function(){
	var statusValue = $(this);
	var statusID = statusValue.val();
	
	/********* Employment status reasons *******************/
	$(".status-reasons-via-ajax").select2();
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
						$(".status-reasons-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					}else if(statusID != 2 && item.id != 8){
						$(".status-reasons-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
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

$(document).on("change", ".contract-types-via-ajax", function(){
	var elementValue = $(this);
	var selectedValue = elementValue.val();
	$(".contract-end-date").datepicker('setDate', null);
	if(selectedValue == 13 || selectedValue == 0){
		$(".hideShow-status").hide()
	}else{
		$(".hideShow-status").show()
	}
	
})


$(document).ready(function(){
	
/*************** New employee form date pickers ********************/

$('#dateOfBirth').datepicker({
	todayHighlight: true,
	 orientation: "bottom left",
	 format: "yyyy-mm-dd",
     endDate: new Date(new Date().setDate(new Date().getDate() - 6570)),
	autoclose: true
});

$('#dateofemployment').datepicker({
	todayHighlight: true,
	 orientation: "bottom left",
	 format: "yyyy-mm-dd",
	 endDate: new Date(new Date().setDate(new Date().getDate())),
	autoclose: true
});

$('#employmentenddate').datepicker({
	todayHighlight: true,
	 orientation: "bottom left",
	 format: "yyyy-mm-dd",
	autoclose: true
});

//*************Date validators
$.validator.addMethod("endDate", function(value, element) {
    var startDate = $('.startDate').val();
    return Date.parse(startDate) <= Date.parse(value) || value == "";
}, "* End date must be after start date");
$('#form').validate();
	
/********* Salutation *******************/
	$(".salutations-via-ajax").select2();
	$(".salutations-via-ajax").append('<option value="">Select Salution</option>');
	var apiUrl = '/get-salutations-byAjax';
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
				$.each(data,function(i,item){				
					$(".salutations-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Nationalities *******************/
	$(".emp-nationalities-via-ajax").select2();
	$(".emp-nationalities-via-ajax").append('<option value="">Select Nationality</option>');
	var apiUrl = '/get-nationalitiesAjax';
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
				$.each(data,function(i,item){				
					$(".emp-nationalities-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Gender *******************/
	$(".genders-via-ajax").select2();
	$(".genders-via-ajax").append('<option value="">Select Gender</option>');
	var apiUrl = '/get-gender-byAjax';
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
				$.each(data,function(i,item){				
					$(".genders-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
	
/********* Religion *******************/
	$(".religions-via-ajax").select2();
	$(".religions-via-ajax").append('<option value="0">Select Religion</option>');
	var apiUrl = '/get-religion-byAjax';
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
				$.each(data,function(i,item){				
					$(".religions-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					if(item.id == 18){
						$(".fixed-religion-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
					}
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});		
	
/********* Marital Statuses *******************/
	$(".marital-statuses-via-ajax").select2();
	$(".marital-statuses-via-ajax").append('<option value="">Select Marital Status</option>');
	var apiUrl = '/get-marital-sattus-byAjax';
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
				$.each(data,function(i,item){				
					$(".marital-statuses-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	

/********* Designations*******************/
	$(".designations-via-ajax").select2();
	$(".designations-via-ajax").append('<option value="">Select Designation</option>');
	var apiUrl = '/get-designations-byAjax';
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
				$.each(data,function(i,item){				
					$(".designations-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});
	
	
/********* Directorate and Units *******************/
	$(".directorates-and-units-via-ajax").select2();
	$(".directorates-and-units-via-ajax").append('<option value="">Select Directorate/Unit</option>');
	var apiUrl = '/get-all-unitsByAjax';
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
				
				$.each(data,function(i,item){				
					$(".directorates-and-units-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Sections *******************/
	$(".sections-via-ajax").select2();
	$(".sections-via-ajax").append('<option value="">Select Section</option>');
	var apiUrl = '/get-all-sectionByAjax';
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
				$.each(data,function(i,item){				
					$(".sections-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Employment Statuses *******************/
	$(".employment-statuses-via-ajax").select2();
	$(".employment-statuses-via-ajax").append('<option value="">Select Employment Status</option>');
	var apiUrl = '/get-employment-statusAjax';
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
				$.each(data,function(i,item){				
					$(".employment-statuses-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Contract Types *******************/
	$(".contract-types-via-ajax").select2();
	$(".contract-types-via-ajax").append('<option value="">Select Contract Type</option>');
	var apiUrl = '/get-contract-typesAjax';
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
				$.each(data,function(i,item){				
					$(".contract-types-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});		
	
/********* Supervisors *******************/
	$(".supervisors-via-ajax").select2();
	$(".supervisors-via-ajax").append('<option value="">Select Supervisor</option>');
	var apiUrl = '/get-supervisorAjax';
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
				$.each(data,function(i,item){				
					$(".supervisors-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	
/********* Duty Stations *******************/
	$(".duty-stations-via-ajax").select2();
	$(".duty-stations-via-ajax").append('<option value="">Select Duty Station</option>');
	var apiUrl = '/get-duty-stationsAjax';
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
				$.each(data,function(i,item){				
					$(".duty-stations-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});		
	
});
