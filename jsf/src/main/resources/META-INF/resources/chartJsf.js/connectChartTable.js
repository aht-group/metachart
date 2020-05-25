function selectElementInChart(selectedTime) {
	console.log('calling selectElementInChart with selectedTime :' +selectedTime);
	var selectedDate = new Date(selectedTime);
	
	$.each(Highcharts.charts, function(index, hc) {
		if(typeof hc==='undefined'){
			console.log('chart is not yet set');
		}
		else{
			var selectedPoints = hc.getSelectedPoints();
			$.each(selectedPoints, function(i, value) {
				value.select(false);
			});

			hc.getSeriesOrderByLinks().forEach(seriesElement => {
				pointhc = seriesElement.points.find(function (element){
					//console.log(selectedTime);
					//console.log(element.x);
					if(element.hasOwnProperty("category") && element.hasOwnProperty("shapeType") && element.shapeType== "rect"){return element.category == selectedTime;}
					if(element.name==undefined){return element.x == selectedTime;}
					else{return element.name == selectedTime;}
					});
				try{pointhc.select(true,true);}catch(err){console.log(err.message);}
			  });
		} 						
	});     	 	
}

function selectRowInTable(point) {
	var json = [];
	var selectdTime;
	if(point.shapeType == "rect"){
		if(point.hasOwnProperty("category")){ 
			selectdTime = point.category;
			var selectDT = new Date(point.category);
			json = [{name: 'time', value: point.category + selectDT.getTimezoneOffset()*60*1000}];
		}
	}
	else if(Boolean(point.name)){
		selectdTime = point.name;
		var selectDT = new Date(point.name);
		json = [{name: 'time', value: point.name + selectDT.getTimezoneOffset()*60*1000}];
	}else if(Boolean(point.x)){
		selectdTime = point.x;
		var selectDT = new Date(point.x);
		json = [{name: 'time', value: point.x + selectDT.getTimezoneOffset()*60*1000}];
	} 
	console.log(json);
	selectGraphPoint(json);
	selectElementInChart(selectdTime);
	console.log("selectGraphPoint(" + json + ")");
	point.select(true);
}
