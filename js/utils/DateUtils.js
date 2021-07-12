// dd-mm-yyyy to js object
function ddMMyyyyToDate(date){
	var dateObj = {
		day:0, 
		month:0, 
		year:0
	}
	if(date){
		var dateParts = date.split('-');
		if(dateParts){
			dateObj.day = dateParts[0];
			dateObj.month = dateParts[1];
			dateObj.year = dateParts[2];
		}
	}
	return dateObj;
}

function toMMMMYYYY(date){
	
}