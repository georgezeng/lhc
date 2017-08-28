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
	var arr = ["red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}