var chartUrl = "/mvc/yz/listQIWYZ?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '区位1', data: []});
	series.push({name: '区位2', data: []});
	series.push({name: '区位3', data: []});
	series.push({name: '区位4', data: []});
	series.push({name: '区位5', data: []});
	series.push({name: '区位6', data: []});
	series.push({name: '区位7', data: []});
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