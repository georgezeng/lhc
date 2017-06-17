$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().change(function() {
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
				reloadTables();
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
			url: '/mvc/yz/listSX/',
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
				var series = [{name: '遗值和', data: []}, {name: '遗值差', data: []}, {name: '上期遗值', data: []}];
				for(var i in result.list) {
					var item = result.list[i];
					series[0].data.push(
							[item.year + "-" + item.phase, item.total]
					);
					series[1].data.push(
							[item.year + "-" + item.phase, item.delta]
					);
					series[2].data.push(
							[item.year + "-" + item.phase, item.lastYz]
					);
				}
				
				Highcharts.chart('charts', {
					
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
						minRange: 1
					},
					
					plotOptions: {
						series: {
							turboThreshold: 3000
			            }
				    },
					
					series: series
					
				});
			}
		});
	}
	
});

