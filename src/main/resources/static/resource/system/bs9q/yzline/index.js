var chartUrl = "/mvc/yz/listBS9QYZ?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '红1', data: []});
	series.push({name: '红2', data: []});
	series.push({name: '红3', data: []});
	series.push({name: '蓝1', data: []});
	series.push({name: '蓝2', data: []});
	series.push({name: '蓝3', data: []});
	series.push({name: '绿1', data: []});
	series.push({name: '绿2', data: []});
	series.push({name: '绿3', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}