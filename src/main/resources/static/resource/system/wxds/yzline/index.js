var chartUrl = "/mvc/yz/listWXDSYZ?mode=0";
seriesCallback = function(result, series) {
	series.push({name: '金单', data: []});
	series.push({name: '金双', data: []});
	series.push({name: '木单', data: []});
	series.push({name: '木双', data: []});
	series.push({name: '水单', data: []});
	series.push({name: '水双', data: []});
	series.push({name: '火单', data: []});
	series.push({name: '火双', data: []});
	series.push({name: '土单', data: []});
	series.push({name: '土双', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["jinOdd", "jinEven", "muOdd", "muEven", "shuiOdd", "shuiEven", "huoOdd", "huoEven", "tuOdd", "tuEven"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}