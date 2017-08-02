$(document).ready(function() {
	
	$("#searchBtn").unbind().click(function() {
		loadChart();
	}).click();
	
	function loadChart() {
		post({
			url: '/mvc/yz/listSXZFLevel2/',
			data: {
				object: {
					year: parseInt($("#years").val()),
					phase: parseInt($("#phases").val())
				},
				pageInfo: {
					pageNo: 1,
					pageSize: parseInt($("#phaseTotal").val())
				}
			},
			success: function(result) {
				var series = [{name: '振幅', data: []}];
				for(var i = 0; i < result.list.length; i++) {
					var item = result.list[i];
					series[0].data.push({
						y: item.currentPos,
						name: item.year + "-" + item.phase
					});
				}
				
				var height = result.list.length;
				if(height > 11) {
					height *= 20;
				} else {
					height *= 30;
				}
				
				Highcharts.chart('charts', {
					
					chart: {
						type: 'bar',
						height: height + 'px'
					},
					
					title: {
						text: ''
					},
					
					plotOptions: {
				        bar: {
				            dataLabels: {
				                enabled: true
				            }
				        },
						series: {
							turboThreshold: 3000,
			                pointWidth: 16
			            }
				    },
					
					series: series
					
				});
			}
		});
	}
	
});


