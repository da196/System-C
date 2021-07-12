$(function(){
    $('#addCertification').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        //var title = button.data('title');
        var input_date = button.data('date');
        var modal = $(this);

        //modal.find('.modal-title').text('Clicked: ' + title);
        modal.find('.modal-body #inputDate').val(input_date);

        $('#startDate2').datepicker({
        	autoclose: true,
        	format: 'yyyy-mm-dd'
        	});
        
        $('#endDate2').datepicker({
        	autoclose: true,
        	format: 'yyyy-mm-dd'
        	});

    });
})

/************** Post/Add Employee certification by Ajax ***************/   	  	  
$("#btnSaveCertification").click(function(){
	var form = $('#newCertification');
    var action = form.attr("action");  
    
	$.ajax({
		url: action,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        enctype: 'multipart/form-data',
        data: new FormData($("#newCertification")[0]),
        
        success: function(data, textStatus, jqXHR){
     	    $.confirm({
     	        title: 'Success!',
     	        content: 'Certification has been added!',
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
              content: 'Error while trying to save Certification information!',
          });
        }
	 });
	
});

$(document).ready(function(){
/************************** GET Certification countries ************************************/

	$(".certificationCountries-via-ajax").select2();
	$(".certificationCountries-via-ajax").append('<option value="0">Select Country</option>');
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
					$(".certificationCountries-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCertificationInstitutionCountry").select2({
	    dropdownParent: $("#addCertification")
	  });
	
/************************** GET Certification categories ************************************/

	$(".certificationCategories-via-ajax").select2();
	$(".certificationCategories-via-ajax").append('<option value="0">Select Category</option>');
	var apiUrl = '/get-certification-categoryAjax';
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
					$(".certificationCategories-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectCertificationCategory").select2({
	    dropdownParent: $("#addCertification")
	  });
})