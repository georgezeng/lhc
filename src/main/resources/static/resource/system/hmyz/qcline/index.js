$(document).ready(function() {
	$("#searchBtn").unbind().click(function() {
		loadChart();
	}).click();
	
	function loadChart() {
		post({
			url: '/mvc/yz/pmList',
			data: {
				object: {
					year: parseInt($("#years").val()),
					phase: parseInt($("#phases").val()),
				},
				pageInfo: {
					pageNo: 1,
					pageSize: parseInt($("#phaseTotal").val())
				}
			},
			success: function(result) {
				var series = [{name: '期差', data: []}];
				for(var i in result.list) {
					var item = result.list[i];
					if(item.specialNum) {
						series[0].data.push(
								[item.year + "-" + item.phase, item.specialNum.delta]
						);
					} else {
						series[0].data.push(
								[item.year + "-" + item.phase, null]
						);
					}
				}
				
				Highcharts.chart('charts', {
					
					title: {
						text: ''
					},
					
					yAxis: {
						title: {
							text: '期差'
						}
					},
					
					xAxis: {
						allowDecimals: false,
						minRange: 1
					},
					
					series: series
					
				});
			}
		});
	}
	
});


