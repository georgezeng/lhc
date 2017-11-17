var chartUrl = "/mvc/yz/listZX10YZ?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '段位1', data: []});
	series.push({name: '段位2', data: []});
	series.push({name: '段位3', data: []});
	series.push({name: '段位4', data: []});
	series.push({name: '段位5', data: []});
	series.push({name: '段位6', data: []});
	series.push({name: '段位7', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["w1", "w2", "w3", "w4", "w5", "w6", "w7"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}