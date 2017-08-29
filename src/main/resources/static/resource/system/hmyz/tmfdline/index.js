var chartUrl = "/mvc/yz/listTMFDYZ?mode=0";
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
	series.push({name: '八字', data: []});
	var lastItem;
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
		setBZLine(series, lastItem, item);
		lastItem = item;
	}
	var item = null;
	if($.cookie("year")) {
		item = {
			year: $.cookie("year"),
			phase: $.cookie("phase"),
			prevDelta: parseInt($.cookie("prevDelta")),
			tm: parseInt($.cookie("tm"))
		}
		series[10].data.push(
			[item.year + "-" + item.phase, item.prevDelta]
		);
		series[11].data.push(
			[item.year + "-" + item.phase, item.tm]
		);
		setBZLine(series, lastItem, item);
	}
	return series;
};

function setBZLine(series, lastItem, item) {
	if(lastItem) {
		if(item.tm > lastItem.tm && item.prevDelta > lastItem.prevDelta) {
			series[12].data.push(
				[item.year + "-" + item.phase, 1]
			);
		} else if(item.tm < lastItem.tm && item.prevDelta < lastItem.prevDelta) {
			series[12].data.push(
				[item.year + "-" + item.phase, 2]
			);
		} else if(item.tm > lastItem.tm && item.prevDelta < lastItem.prevDelta 
				|| item.tm == lastItem.tm && item.prevDelta < lastItem.prevDelta
				|| item.tm > lastItem.tm && item.prevDelta == lastItem.prevDelta) {
			series[12].data.push(
				[item.year + "-" + item.phase, 3]
			);
		} else {
			series[12].data.push(
				[item.year + "-" + item.phase, 4]
			);
		}
		series[12].visible = false;
	}
}

$(document).ready(function() {
	$("#calYZBtn").before($("<button class='btn btn-success'>重置</button>").click(function() {
		$.removeCookie('year');
		$.removeCookie('phase');
		$.removeCookie('prevDelta');
		$.removeCookie('tm');
		$("#searchBtn").click();
	}));
});

