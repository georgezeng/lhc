var chartUrl = "/mvc/yz/listTMFDYZ";
customSeries = true;
seriesCallback = function(result, series) {
	for(var i = 1; i < 8; i++) {
		series.push({name: '段位' + i, data: []});
	}
	series.push({name: '和', data: []});
	series.push({name: '差值', data: []});
	series.push({name: '最大遗值', data: []});
	series.push({name: '期差', data: []});
	series.push({name: '特码', data: []});
	for(var i in result.list) {
		var item = result.list[i];
		var count = 0;
		for(var i = 1; i < 8; i++) {
			series[count].data.push(
					[item.year + "-" + item.phase, item["fd" + i]]
			);
			series[count++].visible = false;
		}
		series[count].data.push(
				[item.year + "-" + item.phase, item.total]
		);
		series[count++].visible = false;
		series[count].data.push(
				[item.year + "-" + item.phase, item.delta]
		);
		series[count++].visible = false;
		series[count].data.push(
				[item.year + "-" + item.phase, item.maxYz]
		);
		series[count++].visible = false;
		series[count].data.push(
				[item.year + "-" + item.phase, item.prevDelta]
		);
		series[count++].visible = false;
		series[count].data.push(
				[item.year + "-" + item.phase, item.tm]
		);
		series[count++].visible = false;
	}
	return series;
};



