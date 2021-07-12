/**
 * This is in for leave module
 */

// Retrieve all leaves by Ajax
var API_URL = '/get-leavesByAjax';

if(getContextPath()!=null){
	API_URL = getContextPath()+""+API_URL;
	}

$.ajax({
	url:API_URL,
	type:'GET',
	success:function(data){
		if(!data){
			alert("No data received");
		}else{
			var currentDate = new Date().toISOString().slice(0, 10);
			var next7Days = new Date(new Date().setDate(new Date().getDate() + 7)).toISOString().slice(0, 10);
			var leavesInNext7Days = 0;
			$.each(data,function(i,item){
				if(item.startdate >= currentDate && item.startdate <= next7Days){
					leavesInNext7Days++
				}
			});
			$('.leavesInNext7Days').append(leavesInNext7Days);
		}
	},
	error: function(jqXHR){
		alert("Error (Status Code)-"+jqXHR.status);
	}
});
// --END
