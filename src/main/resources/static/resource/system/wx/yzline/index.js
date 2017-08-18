var chartUrl = "/mvc/yz/listWXYZ";
seriesCallback = function(result, series) {
	series.push({name: '金', data: []});
	series.push({name: '木', data: []});
	series.push({name: '水', data: []});
	series.push({name: '火', data: []});
	series.push({name: '土', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["jin", "mu", "shui", "huo", "tu"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}