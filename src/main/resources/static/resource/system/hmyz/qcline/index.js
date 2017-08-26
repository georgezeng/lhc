var chartUrl = "/mvc/yz/listPM";
customSeries = true;
seriesCallback = function(result, series) {
	series.push({name: '期差', data: []});
	series.push({name: '特码', data: []});
	for(var i in result.list) {
		var item = result.list[i];
		var count = 0;
		if(item.specialNum) {
			series[count].data.push(
					[item.year + "-" + item.phase, item.specialNum.delta]
			);
			series[count++].visible = false;
			series[count].data.push(
					[item.year + "-" + item.phase, item.specialNum.num]
			);
			series[count++].visible = false;
		} else {
			series[count].data.push(
					[item.year + "-" + item.phase, null]
			);
			series[count++].visible = false;
			series[count].data.push(
					[item.year + "-" + item.phase, null]
			);
			series[count++].visible = false;
		}
	}
	return series;
};


