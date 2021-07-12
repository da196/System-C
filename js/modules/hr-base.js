
// Open modal in AJAX callback
$('#manual-ajax').click(function(event) {
  event.preventDefault();
  this.blur(); // Manually remove focus from clicked link.
  $.get(this.href, function(html) {
    $(html).appendTo('body').modal();
  });
});

$(function(){
    $('#addEducationInfo').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        //var title = button.data('title');
        var input_date = button.data('date');
        var modal = $(this);

        //modal.find('.modal-title').text('Clicked: ' + title);
        modal.find('.modal-body #inputDate').val(input_date);

        $('#startDate').datepicker({
        	autoclose: true,
        	format: "yyyy",
            viewMode: "years", 
            minViewMode: "years"
        	});
        
        $('#endDate').datepicker({
        	autoclose: true,
        	format: "yyyy",
            viewMode: "years", 
            minViewMode: "years"
        	});

    });
})

/************** Post/Add Employee relative by Ajax ***************/   	  	  
$("#btnSaveEmployeeRelative").click(function(){
	var form = $('#newRelative');
    var action = form.attr("action");            
    var data = form.serializeObject();
    
	$.ajax({
        url: action,
        dataType: 'json',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        
        success: function(data, textStatus, jqXHR){
        	//var status = jqXHR.status;
     	    $.confirm({
     	        title: 'Success!',
     	        content: 'Relative has been added!',
     	        type: 'green',
     	        typeAnimated: true,
     	        buttons: {
     	            tryAgain: {
     	                text: 'OK',
     	                btnClass: 'btn-green',
     	                action: function(){
     	                	location.reload();
     	                }
     	            }
     	        }
     	    });
        },
        error: function(jqXHR, textStatus, errorThrown){
          $.alert({
              title: 'Status Code: '+jqXHR.status,
              content: 'Error while trying to save Relative information!',
          });
        }
	 });
	
});

/************** Post/Add Employee Education by Ajax ***************/   	  	  
$("#btnSaveEducationInfo").click(function(){
	var form = $('#newEducation');
    var action = form.attr("action");            

	$.ajax({
        url: action,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        enctype: 'multipart/form-data',
        data: new FormData($("#newEducation")[0]),                
        
        success: function(data, textStatus, jqXHR){
     	    $.confirm({
     	        title: 'Success!',
     	        content: 'Education has been added!',
     	        type: 'green',
     	        typeAnimated: true,
     	        buttons: {
     	            tryAgain: {
     	                text: 'OK',
     	                btnClass: 'btn-green',
     	                action: function(){
     	                	location.reload();
     	                }
     	            }
     	        }
     	    });
        },
        error: function(jqXHR, textStatus, errorThrown){
          $.alert({
              title: 'Status Code: '+jqXHR.status,
              content: 'Error while trying to save Education information!',
          });
        }
	 });
	
});

$(document).ready(function(){
	
/************************** GET Countries ************************************/
	/*** 1. Country of birth *********/
	$(".countries-via-ajax").select2();
	$(".countries-via-ajax").append('<option value="0">Select Country of Birth</option>');
	var apiUrl = '/get-countriesAjax';
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
					$(".countries-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCountry").select2({
	    dropdownParent: $("#editFamilyInfo")
	  });
	
	/*** 2. Country of residence *********/
	$(".countries2-via-ajax").select2();
	$(".countries2-via-ajax").append('<option value="0">Select Country of Residence</option>');
	var apiUrl = '/get-countriesAjax';
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
					$(".countries2-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCountry2").select2({
	    dropdownParent: $("#editFamilyInfo")
	  });
	
	
/************************ GET Nationalities *********************************/
	
	$(".nationalities-via-ajax").select2();
	$(".nationalities-via-ajax").append('<option value="0">Select Nationality</option>');
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
					$(".nationalities-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectNationality").select2({
	    dropdownParent: $("#editFamilyInfo")
	  });
	
/************************ GET Relative categories *********************************/
	
	$(".categories-via-ajax").select2();
	$(".categories-via-ajax").append('<option value="0">Select Relative Category</option>');
	var apiUrl = '/get-relative-categoriesAjax';
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
					$(".categories-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCategory").select2({
	    dropdownParent: $("#editFamilyInfo")
	  });
	
/************************ GET institutions *********************************/
	
	$(".institutions-via-ajax").select2();
	$(".institutions-via-ajax").append('<option value="0">Select Institution</option>');
	var apiUrl = '/get-institutionsAjax';
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
					$(".institutions-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectIstitution").select2({
	    dropdownParent: $("#addEducationInfo")
	  });
	
/************************ GET Education Levels *********************************/
	
	$(".levels-via-ajax").select2();
	$(".levels-via-ajax").append('<option value="0">Select Education level</option>');
	var apiUrl = '/get-education-levelsAjax';
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
					$(".levels-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectEducationLevel").select2({
	    dropdownParent: $("#addEducationInfo")
	  });
	
/************************ GET Education Course *********************************/
	
	$(".courses-via-ajax").select2();
	$(".courses-via-ajax").append('<option value="0">Select Education Course</option>');
	var apiUrl = '/get-education-coursesAjax';
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
					$(".courses-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCourse").select2({
	    dropdownParent: $("#addEducationInfo")
	  });
	
/************************ GET Institution countries *********************************/
	
	$(".institutionsCountries-via-ajax").select2();
	$(".institutionsCountries-via-ajax").append('<option value="0">Select Institution country</option>');
	var apiUrl = '/get-countriesAjax';
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
					$(".institutionsCountries-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectInstitutionCountry").select2({
	    dropdownParent: $("#addEducationInfo")
	  });
	
});