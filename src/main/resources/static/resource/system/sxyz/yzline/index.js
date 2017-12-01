var chartUrl = "/mvc/yz/listSX?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '组合0', data: []});
	series.push({name: '组合1+', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["nonZhCount", "zhCount"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}