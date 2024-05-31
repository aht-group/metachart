import Highcharts from 'highcharts';
export function selectElementInChart(selectedTime: any): void {
    console.log('calling selectElementInChart with selectedTime :' + selectedTime);
    let selectedDate: Date = new Date(selectedTime);

    $.each(Highcharts.charts, function(index: number, hc: any) {
        if(typeof hc === 'undefined'){
            console.log('chart is not yet set');
        }
        else{
                        let selectedPoints: any[] = hc.getSelectedPoints();
                        $.each(selectedPoints, function(i: number, value: any) {
                            value.select(false);
                        });

                        hc.getSeriesOrderByLinks().forEach((seriesElement: any) => {
                            let pointhc = seriesElement.points.find(function (element: any){
                                //console.log(selectedTime);
                                //console.log(element.x);
                                if(element.hasOwnProperty("category") && element.hasOwnProperty("shapeType") && element.shapeType== "rect"){return element.category == selectedTime;}
            					if(element.name==undefined){return element.x == selectedTime;}
            					else{return element.name == selectedTime;}
                            });
                            try{pointhc.select(true,true);}catch(err: any){console.log(err.message);}
                        });
        }
    });
}

export function selectRowInTable(point: any): void { // Add type annotation to the point parameter
    var json: any[] = []; // Add type annotation to the json variable
    var selectdTime;
    if (point.shapeType == "rect") {
        if (point.hasOwnProperty("category")) {
            selectdTime = point.category;
            var selectDT = new Date(point.category);
            json = [{ name: 'time', value: point.category + selectDT.getTimezoneOffset() * 60 * 1000 }];
        }
    }
    else if (Boolean(point.name)) {
        selectdTime = point.name;
        var selectDT = new Date(point.name);
        json = [{ name: 'time', value: point.name + selectDT.getTimezoneOffset() * 60 * 1000 }];
    } else if (Boolean(point.x)) {
        selectdTime = point.x;
        var selectDT = new Date(point.x);
        json = [{ name: 'time', value: point.x + selectDT.getTimezoneOffset() * 60 * 1000 }];
    }
    console.log(json);
    selectGraphPoint(json);
    selectElementInChart(selectdTime);
    console.log("selectGraphPoint(" + json + ")");
    point.select(true);
}

export function selectGraphPoint(json: any[]) {
    throw new Error("Function not implemented.");
}
