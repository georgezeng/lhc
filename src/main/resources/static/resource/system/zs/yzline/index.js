$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().unbind().change(function() {
				post({
					url: '/mvc/yz/phases/' + $(this).val(),
					success: function(list) {
						var phases = $("#phases");
						phases.empty();
						for(var i in list) {
							phases.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
						}
						if(phases.prev().hasClass("combobox-container")){
							phases.prev().remove();
						}
						phases.combobox();
						loadChart();
					}
				});
			}).change();
		}
	});
	
	$("#phaseTotal").combobox();
	
	$("#calYZBtn").click(function() {
		openLoading();
		post({
			url: '/mvc/yz/calYZ/',
			success: function() {
				alert("遗值计算完成");
				loadChart();
				closeLoading();
			},
			systemError: function(msg) {
				alert(msg);
				closeLoading();
			}
		});
	});
	
	$("#searchBtn").click(function() {
		loadChart();
	}).click();
	
	function loadChart() {
		post({
			url: '/mvc/yz/listZSYZ',
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
				var series = [
					{name: '遗值(上)', data: []}, 
					{name: '遗值和', data: []}, 
					{name: '和平均值', data: []}, 
					{name: '最大值', data: []},
					{name: '最大平均值', data: []},
					{name: '间0', data: []},
					{name: '间0平均值', data: []},
					{name: '间1', data: []},
					{name: '间1平均值', data: []},
					{name: '间2', data: []},
					{name: '间2平均值', data: []},
					{name: '间3', data: []},
					{name: '间3平均值', data: []},
					{name: '间4', data: []},
					{name: '间4平均值', data: []},
					{name: '间5', data: []},
					{name: '间5平均值', data: []},
					{name: '间6', data: []},
					{name: '间6平均值', data: []},
				];
				for(var i in result.list) {
					var item = result.list[i];
					series[0].data.push(
							[item.year + "-" + item.phase, item.lastYz]
					);
					series[1].data.push(
							[item.year + "-" + item.phase, item.total]
					);
					series[2].data.push(
							[item.year + "-" + item.phase, item.totalAvg]
					);
					series[3].data.push(
							[item.year + "-" + item.phase, item.max]
					);
					series[4].data.push(
							[item.year + "-" + item.phase, item.maxAvg]
					);
					series[5].data.push(
							[item.year + "-" + item.phase, item.min0]
					);
					series[6].data.push(
							[item.year + "-" + item.phase, item.min0Avg]
					);
					series[7].data.push(
							[item.year + "-" + item.phase, item.min1]
					);
					series[8].data.push(
							[item.year + "-" + item.phase, item.min1Avg]
					);
					series[9].data.push(
							[item.year + "-" + item.phase, item.min2]
					);
					series[10].data.push(
							[item.year + "-" + item.phase, item.min2Avg]
					);
					series[11].data.push(
							[item.year + "-" + item.phase, item.min3]
					);
					series[12].data.push(
							[item.year + "-" + item.phase, item.min3Avg]
					);
					series[13].data.push(
							[item.year + "-" + item.phase, item.min4]
					);
					series[14].data.push(
							[item.year + "-" + item.phase, item.min4Avg]
					);
					series[15].data.push(
							[item.year + "-" + item.phase, item.min5]
					);
					series[16].data.push(
							[item.year + "-" + item.phase, item.min5Avg]
					);
					series[17].data.push(
							[item.year + "-" + item.phase, item.min6]
					);
					series[18].data.push(
							[item.year + "-" + item.phase, item.min6Avg]
					);
				}
				
				Highcharts.chart('charts', {
					
					chart: {
						height: 760
					},
					
					title: {
						text: ''
					},
					
					yAxis: {
						title: {
							text: '遗值'
						}
					},
					
					xAxis: {
						allowDecimals: false,
						minRange: 1,
						gridLineWidth: 1
					},
					
					plotOptions: {
						series: {
							turboThreshold: 3000
			            }
				    },
				    
				    tooltip: {
				    	positioner: function(lw, lh, p) {
				    		return {
				    			x: 100, 
				    			y: 10
				    		};
				    	}
			        },
					
					series: series
					
				});
			}
		});
	}
	
});


