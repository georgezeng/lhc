var datatables = [];
var customSeries;
var seriesCallback;
var seriesValueCallback;
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
				var series = [];
				if(typeof seriesCallback === 'function') {
					series = seriesCallback(result, series);
				}
				if(!customSeries) {
					series.push({name: '上期遗值', data: []});
					series.push({name: '遗值和', data: []});
					series.push({name: '和平均值', data: []});
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
						if(typeof seriesValueCallback === 'function') {
							count = seriesValueCallback(series, item);
						}
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

function createLRList(url, list, smallPos, largePos) {
	if(url.indexOf("?mode") == -1) {
		url = url + "?mode=1";
	}
	var lastColor;
	var count = 0;
	var lastItem;
	var cols = ["year", "phase"];
	for(var i in list) {
		cols.push(list[i]);
	}
	cols.push("phase");
	cols.push("lastYzColor");
	cols.push("phase");
	cols.push("red");
	cols.push("yellow");
	cols.push("green");
	cols.push("colorMax");
	cols.push("colorTotal");
	cols.push("colorCount");
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	var columnDefs = [];
	var extraLine = function(nTd, sData, item, iRow, iCol) {
		var value = null;
		if(index == 0) {
			value = item.year;
			if(!item.id) {
				value = "顺概率";
				$(nTd).css("color", "blue");
			} 
		} else {
			value = item.phase;
			if(!item.id) {
				value = "";
			}
		}
		$(nTd).text(value);
	}
	for(var i = 0; i < 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(index == 0) {
						value = item.year;
						if(!item.id) {
							value = "顺概率";
							$(nTd).css("color", "blue");
						} 
					} else {
						value = item.phase;
						if(!item.id) {
							value = "";
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	for(var i = 2; i < list.length + 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[list[index-2]];
					if(item.id) {
						var uniqueArr = [];
						for(var j in list) {
							uniqueArr.push(item[list[j]]);
						}
						uniqueArr.sort(function(a, b){return a-b});
						var small = uniqueArr[smallPos];
						var large = uniqueArr[largePos];
						
						var color = null;
						if(value < small + 1) {
							color = ["white", "red"];
						} else if(value > small && value < large) {
							color = ["black", "yellow"];
						} else {
							color = ["white", "green"];
						}
						if(!item.colors) {
							item.colors = [];
						}
						item.colors.push(color);
						if(value == 0) {
							item.colorIndex = index - 2;
						}
						$(nTd).css("color", color[0]).css("backgroundColor", color[1]);
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [list.length + 2],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			$(nTd).text("");
		}
	});
	columnDefs.push({
		aTargets: [list.length + 3],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				$(nTd).text("").css("backgroundColor", item.lastYzColor);
				if(lastColor == item.lastYzColor) {
					count++;
				}
				lastColor = item.lastYzColor;
			} else {
				value = Math.round(count / item.total * 10000) / 100 + "%";
				$(nTd).text(value);
			}
		}
	});
	columnDefs.push({
		aTargets: [list.length + 4],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			$(nTd).text("");
		}
	});
	columnDefs.push({
		aTargets: [list.length + 5],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.red == 0) {
				$(nTd).css("backgroundColor", "red").css("color", "white");
			}
			$(nTd).text(item.red);
		}
	});
	columnDefs.push({
		aTargets: [list.length + 6],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.yellow == 0) {
				$(nTd).css("backgroundColor", "yellow").css("color", "white");
			}
			$(nTd).text(item.yellow);
		}
	});
	columnDefs.push({
		aTargets: [list.length + 7],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.green == 0) {
				$(nTd).css("backgroundColor", "green").css("color", "white");
			}
			$(nTd).text(item.green);
		}
	});
	columnDefs.push({
		aTargets: [list.length + 10],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(!item.id) {
				$(nTd).text("");
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : url,
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			lastItem = null;
			count = 0;
			lastColor = null;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
}

function createLRZH(url) {
	var sxlist = ["redRed", "redYellow", "redGreen", "yellowRed", "yellowYellow", "yellowGreen", "greenRed", "greenYellow", "greenGreen"];
	var columns = createColumns(sxlist, ["pos"]);
	var columnDefs = [];
	for(var i = 2; i < 11; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[sxlist[index-2]];
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					} else {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [13],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = item.lastYz;
			if(value > 7) {
				if(lastGreen) {
					count++;
				}
				$(nTd).css("color", "white").css("backgroundColor", "green");
				lastRed = false;
				lastGreen = true;
			} else {
				if(lastRed) {
					count++;
				}
				$(nTd).css("color", "white").css("backgroundColor", "red");
				lastRed = true;
				lastGreen = false;
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : url,
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = 0;
			lastRed = false;
			lastGreen = false;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
}

function createJZTable(url) {
	var count = [];
	var cols = ["year", "phase"];
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	for(var i = 0; i < 31; i++) {
		var col = "lastYZ" + i;
		columns.push({
			name: col,
			data : "lastYz",
			sortable: false
		});
	}
	cols = ["31-40", "41-50", "51+"];
	for(var i in cols) {
		var col = "lastYZ-" + cols[i];
		columns.push({
			name : col,
			data : "lastYz",
			sortable: false
		});
	}
	var columnDefs = [];
	for(var i = 0; i < 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					var isTotal = false;
					if(index == 0) {
						value = item.year;
						if(value == 0) {
							value = "合计";
							isTotal = true;
						}
					} else {
						value = item.phase;
						if(value == 0) {
							value = "";
							isTotal = true;
						}
					}
					if(isTotal) {
						$(nTd).css("fontWeight", "bold").css("color", "blue");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	for(var i = 2; i < 36; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(item.year > 0) {
						value = item.lastYz;
						var isRed = false;
						if(index < 33) {
							isRed = value == index-2;
						} else if(index == 33) {
							isRed = value > 30 && value < 41;
						} else if(index == 34) {
							isRed = value > 40 && value < 51;
						} else if(index == 35) {
							isRed = value > 50;
						}
						if(isRed) {
							count[index-2] = 1;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else {
							if(!count[index-2]) {
								count[index-2] = 0;
							}
							value = count[index-2];
							count[index-2] += 1;
						}
					} else {
						value = item.lastYzList[index-2];
						$(nTd).css("fontWeight", "bold");
						var avg = 0;
						for(var i in item.lastYzList) {
							avg += item.lastYzList[i];
						}
						avg = Math.floor(avg / item.lastYzList.length);
						if(value > avg + 1) {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else if(value < avg - 1) {
							$(nTd).css("color", "white").css("backgroundColor", "green");
						} else {
							$(nTd).css("backgroundColor", "yellow");
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "datatable",
		url : url,
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = [];
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
}