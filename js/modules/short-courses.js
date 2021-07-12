$(function(){
    $('#addShortCourse').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget);
        //var title = button.data('title');
        var input_date = button.data('date');
        var modal = $(this);

        //modal.find('.modal-title').text('Clicked: ' + title);
        modal.find('.modal-body #inputDate').val(input_date);

        $('#startDate1').datepicker({
        	autoclose: true,
        	format: 'yyyy-mm-dd'
        	});
        
        $('#endDate1').datepicker({
        	autoclose: true,
        	format: 'yyyy-mm-dd'
        	});

    });
})

/************** Post/Add Employee short course by Ajax ***************/   	  	  
$("#btnSaveShortCourse").click(function(){
	var form = $('#newShortCourse');
    var action = form.attr("action");  
    
	$.ajax({
		url: action,
        type: 'POST',
        contentType: false,
        processData: false,
        cache: false,
        enctype: 'multipart/form-data',
        data: new FormData($("#newShortCourse")[0]),
        
        success: function(data, textStatus, jqXHR){
     	    $.confirm({
     	        title: 'Success!',
     	        content: 'Short Course has been added!',
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
              content: 'Error while trying to save Short Course information!',
          });
        }
	 });
	
});

$(document).ready(function(){
/************************** GET Short courses countries ************************************/

	$(".shorCourseCountries-via-ajax").select2();
	$(".shorCourseCountries-via-ajax").append('<option value="0">Select Country</option>');
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
					$(".shorCourseCountries-via-ajax").append('<option value="'+item.id+'">'+item.name+'</option>');
				});				
			}
		},
		error:function(jqXHR,textStatus,errorThrown){
			alert("Error-"+textStatus+","+errorThrown);
		}
	});	
	$("#selectShortCourseInstitutionCountry").select2({
	    dropdownParent: $("#addShortCourse")
	  });
})