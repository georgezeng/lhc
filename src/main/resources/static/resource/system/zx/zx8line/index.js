var chartUrl = "/mvc/yz/listZX8YZ?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '段位1', data: []});
	series.push({name: '段位2', data: []});
	series.push({name: '段位3', data: []});
	series.push({name: '段位4', data: []});
	series.push({name: '段位5', data: []});
	series.push({name: '段位6', data: []});
	series.push({name: '段位7', data: []});
	series.push({name: '段位8', data: []});
	series.push({name: '段位9', data: []});
	series.push({name: '段位10', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}