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
						phases.combobox().unbind().change(function() {
							loadChart();
						}).change();
					}
				});
			}).change();
		}
	});
	
	$("#phaseTotal").combobox().change(function() {
		loadChart();
	});
	
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


