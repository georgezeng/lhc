var chartUrl = "/mvc/yz/listPTYZ";
customSeries = true;
seriesCallback = function(result, series) {
	series.push({name: '加1', data: []});
	series.push({name: '减1', data: []});
	for(var i = 0; i < 7; i++) {
		series.push({name: '间' + i, data: []});
	}
	series.push({name: '间6+', data: []});
	for(var i in result.list) {
		var item = result.list[i];
		var count = 0;
		series[count].data.push(
				[item.year + "-" + item.phase, item.add1]
		);
		series[count++].visible = false;
		series[count].data.push(
				[item.year + "-" + item.phase, item.min1]
		);
		series[count++].visible = false;
		for(var i = 0; i < 7; i++) {
			series[count].data.push(
					[item.year + "-" + item.phase, item["jg" + i]]
			);
			series[count++].visible = false;
		}
		series[count].data.push(
				[item.year + "-" + item.phase, item.jg6Plus]
		);
		series[count++].visible = false;
	}
	return series;
};
