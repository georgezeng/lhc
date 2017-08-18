var chartUrl = "/mvc/yz/listBSYZ";
seriesCallback = function(result, series) {
	series.push({name: '红单', data: []});
	series.push({name: '红双', data: []});
	series.push({name: '蓝单', data: []});
	series.push({name: '蓝双', data: []});
	series.push({name: '绿单', data: []});
	series.push({name: '绿双', data: []});
	return series;
}
seriesValueCallback = function(series, item) {
	var count = 0;
	var arr = ["redOdd", "redEven", "blueOdd", "blueEven", "greenOdd", "greenEven"];
	for(i in arr) {		
		series[count].data.push(
				[item.year + "-" + item.phase, item[arr[i]]]
		);
		series[count++].visible = false;
	}
	return count;
}