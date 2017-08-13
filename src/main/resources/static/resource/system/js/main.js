var datatables = [];
var customSeries;
var seriesCallback;
$(document).ready(function() {
	$("#menuBtn").click(function() {
		if($(this).text() == "隐藏菜单") {
			$("#menus").hide();
			$("#page-wrapper").css("marginLeft", "0");
			$(this).text("显示菜单");
		} else {
			$("#menus").show();
			$("#page-wrapper").css("marginLeft", "250px");
			$(this).text("隐藏菜单");
		}
	});
	
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
						phases.unbind().change(function() {
							init = true;
							$("#searchBtn").click();
						}).change();
					}
				});
			}).change();
		}
	});
	
	$("#phaseTotal").combobox();
	
	$("#searchBtn").click(function() {
		if(typeof(chartUrl) === "undefined") {
			reloadTables();
		} else {
			loadChart();
		}
	});
	
	$("#calYZBtn").click(function() {
		openLoading();
		post({
			url: '/mvc/yz/calYZ/',
			success: function() {
				alert("遗值计算完成");
				$("#searchBtn").click();
				closeLoading();
			},
			systemError: function(msg) {
				alert(msg);
				closeLoading();
			}
		});
	});
	
	function reloadTables() {
		for(var i in datatables) {
			datatables[i].ajax.reload();
		}
	}
	
	$("#downloadBtn").click(function() {
		$("#size").val($("select[name='dataTable_length']").val());
		$("#endYear").val($("#years").val());
		$("#endPhase").val($("#phases").val());
		$("#download").submit();
	});
	
	function loadChart() {
		post({
			url: chartUrl,
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
				var series;
				if(!customSeries) {
					series = [
						{name: '上期遗值', data: []},
						{name: '遗值和', data: []}, 
						{name: '和平均值', data: []}
						];
					for(var i = 1; i < 6; i++) {
						series.push({name: '倒' + i, data: []});
						series.push({name: '倒' + i + '平均值', data: []});
					}
					for(var i = 0; i < 20; i++) {
						series.push({name: '间' + i, data: []});
						series.push({name: '间' + i + '平均值', data: []});
					}
					for(var i in result.list) {
						var item = result.list[i];
						var count = 0;
						series[count].data.push(
								[item.year + "-" + item.phase, item.lastYz]
						);
						series[count++].visible = false;
						series[count].data.push(
								[item.year + "-" + item.phase, item.total]
						);
						series[count++].visible = false;
						series[count].data.push(
								[item.year + "-" + item.phase, item.totalAvg]
						);
						series[count++].visible = false;
						for(var j = 0; j < 5; j++) {
							series[count].data.push(
									[item.year + "-" + item.phase, item["top" + j]]
							);
							series[count++].visible = false;
							series[count].data.push(
									[item.year + "-" + item.phase, item["top" + j + "Avg"]]
							);
							series[count++].visible = false;
						}
						for(var j = 0; j < 20; j++) {
							series[count].data.push(
									[item.year + "-" + item.phase, item["min" + j]]
							);
							series[count++].visible = false;
							series[count].data.push(
									[item.year + "-" + item.phase, item["min" + j + "Avg"]]
							);
							series[count++].visible = false;
						}
					}
				} else {
					if(typeof seriesCallback === 'function') {
						series = seriesCallback(result, []);
					}
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

function createColumns(list, extraList) {
	var cols = ["year", "phase"];
	for(var i in list) {
		cols.push(list[i]);
	}
	if(extraList) {
		for(var i in extraList) {
			cols.push(extraList[i]);
		}
	}
	cols.push("delta");
	cols.push("lastYz");
	cols.push("total");
	cols.push("totalAvg");
	cols.push("top0");
	cols.push("top0Avg");
	for(var i = 0; i < 7; i++) {
		cols.push("min" + i);
		cols.push("min" + i + "Avg");
	}
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	return columns;
}