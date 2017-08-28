var chartUrl = "/mvc/yz/listZSYZ?mode=0";
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
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["fd1", "fd2", "fd3", "fd4", "fd5", "fd6", "fd7", "fd8", "fd9"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}