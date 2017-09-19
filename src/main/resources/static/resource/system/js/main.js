var categories = [
	{
		code: "gs",
		text: "公式",
		subs: [
			{
				value: "",
				text: "手动"
			}
		]
	},
	{
		code: "sx",
		text: "生肖",
		subs: [
			{
				value: "",
				text: "鼠"
			},
			{
				value: "",
				text: "牛"
			},
			{
				value: "",
				text: "虎"
			},
			{
				value: "",
				text: "兔"
			},
			{
				value: "",
				text: "龙"
			},
			{
				value: "",
				text: "蛇"
			},
			{
				value: "",
				text: "马"
			},
			{
				value: "",
				text: "羊"
			},
			{
				value: "",
				text: "猴"
			},
			{
				value: "",
				text: "鸡"
			},
			{
				value: "",
				text: "狗"
			},
			{
				value: "",
				text: "猪"
			}
		]
	},
	{
		code: "ds",
		text: "单双",
		subs: [
			{
				value: "1, 3, 5, 7, 9",
				text: "0单"
			},
			{
				value: "2, 4, 6, 8",
				text: "0双"
			},
			{
				value: "11, 13, 15, 17, 19",
				text: "1单"
			},
			{
				value: "10, 12, 14, 16, 18",
				text: "1双"
			},
			{
				value: "21, 23, 25, 27, 29",
				text: "2单"
			},
			{
				value: "20, 22, 24, 26, 28",
				text: "2双"
			},
			{
				value: "31, 33, 35, 37, 39",
				text: "3单"
			},
			{
				value: "30, 32, 34, 36, 38",
				text: "3双"
			},
			{
				value: "41, 43, 45, 47, 49",
				text: "4单"
			},
			{
				value: "40, 42, 44, 46, 48",
				text: "4双"
			}
		]
	},
	{
		code: "sw",
		text: "首位",
		subs: [
			{
				value: "1, 2, 3, 4, 5, 6, 7, 8, 9",
				text: "位0"
			},
			{
				value: "10, 11, 12, 13, 14, 15, 16, 17, 18, 19",
				text: "位1"
			},
			{
				value: "20, 21, 22, 23, 24, 25, 26, 27, 28, 29",
				text: "位2"
			},
			{
				value: "30, 31, 32, 33, 34, 35, 36, 37, 38, 39",
				text: "位3"
			},
			{
				value: "40, 41, 42, 43, 44, 45, 46, 47, 48, 49",
				text: "位4"
			}
		]
	},
	{
		code: "mw",
		text: "末位",
		subs: [
			{
				value: "10, 20, 30, 40",
				text: "位0"
			},
			{
				value: "1, 11, 21, 31, 41",
				text: "位1"
			},
			{
				value: "2, 12, 22, 32, 42",
				text: "位2"
			},
			{
				value: "3, 13, 23, 33, 43",
				text: "位3"
			},
			{
				value: "4, 14, 24, 34, 44",
				text: "位4"
			},
			{
				value: "5, 15, 25, 35, 45",
				text: "位5"
			},
			{
				value: "6, 16, 26, 36, 46",
				text: "位6"
			},
			{
				value: "7, 17, 27, 37, 47",
				text: "位7"
			},
			{
				value: "8, 18, 28, 38, 48",
				text: "位8"
			},
			{
				value: "9, 19, 29, 39, 49",
				text: "位9"
			}
		]
	},
	{
		code: "lh",
		text: "合数",
		subs: [
			{
				value: "19, 28, 37, 46",
				text: "位0"
			},
			{
				value: "1, 10, 29, 38, 47",
				text: "位1"
			},
			{
				value: "2, 11, 20, 39, 48",
				text: "位2"
			},
			{
				value: "3, 12, 21, 30, 49",
				text: "位3"
			},
			{
				value: "4, 13, 22, 31, 40",
				text: "位4"
			},
			{
				value: "5, 14, 23, 32, 41",
				text: "位5"
			},
			{
				value: "6, 15, 24, 33, 42",
				text: "位6"
			},
			{
				value: "7, 16, 25, 34, 43",
				text: "位7"
			},
			{
				value: "8, 17, 26, 35, 44",
				text: "位8"
			},
			{
				value: "9, 18, 27, 36, 45",
				text: "位9"
			}
		]
	},
	{
		code: "bs9q",
		text: "波色",
		subs: [
			{
				value: "1, 2, 7, 8, 12, 13",
				text: "红1"
			},
			{
				value: "18, 19, 23, 24, 29, 30",
				text: "红2"
			},
			{
				value: "34, 35, 40, 45, 46",
				text: "红3"
			},
			{
				value: "5, 6, 11, 16, 17",
				text: "蓝1"
			},
			{
				value: "21, 22, 27, 28, 32",
				text: "蓝2"
			},
			{
				value: "33, 38, 39, 43, 44, 49",
				text: "蓝3"
			},
			{
				value: "3, 4, 9, 10, 14",
				text: "绿1"
			},
			{
				value: "15, 20, 25, 26, 31",
				text: "绿2"
			},
			{
				value: "36, 37, 41, 42, 47, 48",
				text: "绿3"
			}
		]
	},
	{
		code: "bs",
		text: "波色单双",
		subs: [
			{
				value: "1, 7, 13, 19, 23, 29, 35, 45",
				text: "红单"
			},
			{
				value: "2, 8, 12, 18, 24, 30, 34, 40, 46",
				text: "红双"
			},
			{
				value: "5, 11, 17, 21, 27, 33, 39, 43, 49",
				text: "蓝单"
			},
			{
				value: "6, 16, 22, 28, 32, 38, 44",
				text: "蓝双"
			},
			{
				value: "3, 9, 15, 25, 31, 37, 41, 47",
				text: "绿单"
			},
			{
				value: "4, 10, 14, 20, 26, 36, 42, 48",
				text: "绿双"
			}
		]
	},
	{
		code: "zs",
		text: "质数",
		subs: [
			{
				value: "2, 3, 5, 7, 11",
				text: "分段1"
			},
			{
				value: "13, 17, 19, 23, 29",
				text: "分段2"
			},
			{
				value: "31, 37, 41, 43, 47",
				text: "分段3"
			},
			{
				value: "1, 9, 15, 21, 25",
				text: "分段4"
			},
			{
				value: "27, 33, 35, 39, 45, 49",
				text: "分段5"
			},
			{
				value: "4, 6, 8, 10, 12, 14",
				text: "分段6"
			},
			{
				value: "16, 18, 20, 22, 24, 26",
				text: "分段7"
			},
			{
				value: "28, 30, 32, 34, 36, 38",
				text: "分段8"
			},
			{
				value: "40, 42, 44, 46, 48",
				text: "分段9"
			}
		]
	},
	{
		code: "wx",
		text: "五行",
		subs: [
			{
				value: "5, 6, 13, 14, 21, 22, 35, 36, 43, 44",
				text: "金"
			},
			{
				value: "3, 4, 17, 18, 25, 26, 33, 34, 47, 48",
				text: "木"
			},
			{
				value: "2, 9, 10, 23, 24, 31, 32, 39, 40",
				text: "水"
			},
			{
				value: "1, 11, 12, 19, 20, 27, 28, 41, 42, 49",
				text: "火"
			},
			{
				value: "7, 8, 15, 16, 29, 30, 37, 38, 45, 46",
				text: "土"
			}
		]
	},
	{
		code: "wxds",
		text: "五行单双",
		subs: [
			{
				value: "5, 13, 21, 35, 43",
				text: "金单"
			},
			{
				value: "6, 14, 22, 36, 44",
				text: "金双"
			},
			{
				value: "3, 17, 25, 33, 47",
				text: "木单"
			},
			{
				value: "4, 18, 26, 34, 48",
				text: "木双"
			},
			{
				value: "9, 23, 31, 39",
				text: "水单"
			},
			{
				value: "2, 10, 24, 32, 40",
				text: "水双"
			},
			{
				value: "1, 11, 19, 27, 41, 49",
				text: "火单"
			},
			{
				value: "12, 20, 28, 42",
				text: "火双"
			},
			{
				value: "7, 15, 29, 37, 45",
				text: "土单"
			},
			{
				value: "8, 16, 30, 38, 46",
				text: "土双"
			}
			]
	},
	{
		code: "pd",
		text: "配对",
		subs: [
			{
				value: "1, 38, 39, 42",
				text: "位1"
			},
			{
				value: "2, 4, 8, 16, 32",
				text: "位2"
			},
			{
				value: "3, 9, 27, 45",
				text: "位3"
			},
			{
				value: "5, 15, 25, 35",
				text: "位4"
			},
			{
				value: "6, 18, 23, 29",
				text: "位5"
			},
			{
				value: "7, 21, 26, 49",
				text: "位6"
			},
			{
				value: "10, 20, 30, 40",
				text: "位7"
			},
			{
				value: "11, 22, 33, 44",
				text: "位8"
			},
			{
				value: "12, 24, 36, 48",
				text: "位9"
			},
			{
				value: "13, 17, 31, 47",
				text: "位10"
			},
			{
				value: "14, 34, 41, 43",
				text: "位11"
			},
			{
				value: "19, 28, 37, 46",
				text: "位12"
			}
		]
	},
	{
		code: "qq",
		text: "七区",
		subs: [
			{
				value: "1, 2, 3, 4, 5, 6, 7",
				text: "位1"
			},
			{
				value: "8, 9, 10, 11, 12, 13, 14",
				text: "位2"
			},
			{
				value: "15, 16, 17, 18, 19, 20, 21",
				text: "位3"
			},
			{
				value: "22, 23, 24, 25, 26, 27, 28",
				text: "位4"
			},
			{
				value: "29, 30, 31, 32, 33, 34, 35",
				text: "位5"
			},
			{
				value: "36, 37, 38, 39, 40, 41, 42",
				text: "位6"
			},
			{
				value: "43, 44, 45, 46, 47, 48, 49",
				text: "位7"
			}
		]
	},
	{
		code: "twelve",
		text: "十二区",
		subs: [
			{
				value: "1, 2, 3, 4",
				text: "位1"
			},
			{
				value: "5, 6, 7, 8",
				text: "位2"
			},
			{
				value: "9, 10, 11, 12",
				text: "位3"
			},
			{
				value: "13, 14, 15, 16",
				text: "位4"
			},
			{
				value: "17, 18, 19, 20",
				text: "位5"
			},
			{
				value: "21, 22, 23, 24",
				text: "位6"
			},
			{
				value: "25, 26, 27, 28",
				text: "位7"
			},
			{
				value: "29, 30, 31, 32",
				text: "位8"
			},
			{
				value: "33, 34, 35, 36",
				text: "位9"
			},
			{
				value: "37, 38, 39, 40",
				text: "位10"
			},
			{
				value: "41, 42, 43, 44",
				text: "位11"
			},
			{
				value: "45, 46, 47, 48, 49",
				text: "位12"
			}
		]
	},
	{
		code: "slq",
		text: "十六区",
		subs: [
			{
				value: "1, 2, 3",
				text: "位1"
			},
			{
				value: "4, 5, 6",
				text: "位2"
			},
			{
				value: "7, 8, 9",
				text: "位3"
			},
			{
				value: "10, 11, 12",
				text: "位4"
			},
			{
				value: "13, 14, 15",
				text: "位5"
			},
			{
				value: "16, 17, 18, 19, 20",
				text: "位6"
			},
			{
				value: "19, 20, 21",
				text: "位7"
			},
			{
				value: "22, 23, 24",
				text: "位8"
			},
			{
				value: "25, 26, 27",
				text: "位9"
			},
			{
				value: "28, 29, 30",
				text: "位10"
			},
			{
				value: "31, 32, 33",
				text: "位11"
			},
			{
				value: "34, 35, 36",
				text: "位12"
			},
			{
				value: "37, 38, 39",
				text: "位13"
			},
			{
				value: "40, 41, 42",
				text: "位14"
			},
			{
				value: "43, 44, 45",
				text: "位15"
			},
			{
				value: "46, 47, 48, 49",
				text: "位16"
			}
		]
	}
];

function isInArr(hms, k) {
	for(var m in hms) {
		contained = hms[m] == k;
		if(contained) {
			return true;
		}
	}
	return false;
}

function createZHlist(max) {
	var list = [];
	for(var i = max - 1; i > 0; i--) {
		for(var j = i - 1; j >= 0; j--) {
			var miss = [i, j];
			var arr = []; 
			for(var k = 0; k < max; k++) {
				var missed = false;
				for(var m in miss) {
					missed = miss[m] == k;
					if(missed) {
						break;
					}
				}
				if(!missed) {
					arr.push(k);
				}
			}
			list.push(arr);
		}
	}
	list.sort(function(arr1, arr2) {
		for(var i in arr1) {
			if(arr1[i] != arr2[i]) {
				return arr1[i] - arr2[i];
			}
		}
		return 0;
	});
	return list;
}

/**
 * 获得从m中取n的所有组合
 */
function getCompositeArrs(arr, n) {
    var resultArrs = [],
        compositeArr = [],
        flagArr = [],
        isEnd = false,
        m = arr.length,
        i, j, leftCnt;
 
    for (i = 0; i < m; i++) {
    	flagArr[i] = i < n ? 1 : 0;
    	if(flagArr[i]) {
    		compositeArr.push(arr[i]);
    	}
    }
 
    // 第一个组合
    resultArrs.push(compositeArr);
    
    if(n < m) {
    	while (!isEnd) {
    		leftCnt = 0;
    		for (i = 0; i < m - 1; i++) {
    			if (flagArr[i] && !flagArr[i+1]) {
    				for(j = 0; j < i; j++) {
    					flagArr[j] = j < leftCnt ? 1 : 0;
    				}
    				flagArr[i] = 0;
    				flagArr[i+1] = 1;
    				var aTmp = flagArr.concat();
    				compositeArr = [];
    				for (k = 0; k < m; k++) {
    			    	if(aTmp[k]) {
    			    		compositeArr.push(arr[k]);
    			    	}
    			    }
    				resultArrs.push(compositeArr);
    				if(aTmp.slice(-n).join("").indexOf('0') == -1) {
    					isEnd = true;
    				}
    				break;
    			}
    			flagArr[i] && leftCnt++;
    		}
    	}
    }
 
    return resultArrs;
}

function addTM12fdCategory(index) {
	post({
		url: "/mvc/yz/listTM12FDLastPhaseList",
		success: function(list) {
			if(list) {
				var tm12fd = {
					code: "tm12fd",
					text: "分段",
					subs: []
				};
				var range = 4;
				var length = 12;
				for(var i = 1; i <= length; i++) {
					var text = [];
					var num = 0;
					for(j = 0; j < list.length; j++) {
						var pos = j + 1;
						if(pos % range == 0) {
							num = parseInt(pos / range);
						} else {
							num = parseInt(pos / range) + 1;
						}
						if(num > length) {
							num = length;
						}
						if(num == i) {
							text.push(list[j].num);
						}
					}
					tm12fd.subs.push({
						text: "位" + i,
						value: text.join()
					});
				}
				categories.push(tm12fd);
			}
			
			for(var i in categories) {
				var category = categories[i];
				$("#category").append($("<option></option>").val(category.code).text(category.text));
			}
			$("#category").combobox().change(function() {
				var thisEl = $(this);
				var category = categories[thisEl.prop('selectedIndex')];
				$("#subTitle").text(category.text+": ");
				$("#sd").add($("#sd").prev()).remove();
				var parent = $("#hmlist_p");
				parent.children().remove();
				var hmlists = $("<select id='hmlists'></select>").appendTo(parent);
				for(var i in category.subs) {
					var sub = category.subs[i];
					hmlists.append($("<option></option>").val(sub.value ? sub.value : sub.text).text(sub.text));
				}
				hmlists.combobox().change(function() {
					var thisEl = $(this);
					var item = category.subs[thisEl.prop('selectedIndex')];
					if(item.value == "") {
						$("#tip").show();
					} else {
						$("#tip").hide();
					}
					$("#hmlist" + index).val(item.value).attr("text", category.text + "-" + item.text);
				}).change();
			}).change();
		}
	});
}

function qcHm(index) {
	var tds = $("#conditionTable" + index).find("tbody").find("td[name='hm']");
	var registered = [];
	tds.each(function() {
		var td = $(this);
		var hms = td.attr("hm").split(/,\s*/);
		var newHms = [];
		for(var i in hms) {
			var num = hms[i];
			if(!isInArr(registered, num)) {
				registered.push(num);
				newHms.push(num); 
			} 
		}
		newHms = newHms.join(", ");
		td.attr("hm", newHms).text(newHms);
	});
}

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
				$(nTd).css("backgroundColor", "yellow");
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

function createCSList(url, sxlist, itemPropertyCallback) {
	var lastRed = false;
	var lastGreen = false;
	var count = 0;
	var total;
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("avg");
	cols.push("avg");
	cols.push("total");
	cols.push("pairs");
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
	for(var i = 2; i < sxlist.length + 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(item.id) {
						value = item[sxlist[index-2]];
						if(value < item.avg) {
							$(nTd).css("color", "white").css("backgroundColor", "green");
						} else {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [sxlist.length + 3],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				total++;
				var property = item.currentPos;
				if(itemPropertyCallback) {
					property = itemPropertyCallback(item);
				}
				value = item[property];
				if(value < item.avg) {
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
			} else {
				value = Math.round(count / total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [sxlist.length + 4],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.large + "/" + item.small;
			} else {
				value = "";
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
			total = 0;
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

function createYZlist(url, sxlist, setZeroYzFunc, extraColumns, extraLineTitle, extraLineFunc) {
	if(url.indexOf("?mode") == -1) {
		url = url + "?mode=1";
	}
	if(!extraColumns) {
		extraColumns = [];
	}
	if(!extraLineTitle) {
		extraLineTitle = "顺概率";
	}
	if(!setZeroYzFunc) {
		setZeroYzFunc = function(nTd, item, index) {
			$(nTd).css("color", "white").css("backgroundColor", "red");
		};
	}
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var columns = createColumns(sxlist, extraColumns);
	var columnDefs = [];
	for(var i = 0; i < 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(index == 0) {
						value = item.year;
						if(!item.id) {
							value = extraLineTitle;
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
	for(var i = 2; i < sxlist.length + 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value;
					if(item.id) {
						value = item[sxlist[index-2]];
						if(value == 0) {
							setZeroYzFunc(nTd, item, index);
						} else {
							$(nTd).css("backgroundColor", "#ffc");
						}
					} else {
						if(extraLineFunc) {
							value = extraLineFunc(nTd, item, index);
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [sxlist.length + extraColumns.length + 3],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastYz;
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
			} else {
				value = Math.round(count / item.total * 10000) / 100;
				if(isNaN(value) || value == "Infinity") {
					value = "";
				} else {
					value += "%";
				}
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [sxlist.length + extraColumns.length + 4],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.total;
			} else {
				value = "";
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

function createZFlist(url, sxlist) {
	if(url.indexOf("?mode") == -1) {
		url = url + "?mode=1";
	}
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var columns = createColumns(sxlist);
	var columnDefs = [];
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
	for(var i = 2; i < sxlist.length+2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[sxlist[index-2]];
					if(item.id) {
						if(value == 0) {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else {
							$(nTd).css("backgroundColor", "#ffc");
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [sxlist.length+3],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastYz;
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
			} else {
				value = Math.round(count / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [sxlist.length+4],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.total;
			} else {
				value = "";
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

function createLoop(url, length, dividen, list) {
	$(document).ready(function() {
		var sxlist = [];
		for(var i = length; i >= 1; i--) {
			sxlist.push("top" + i);
		}
		var cols = ["year", "phase"];
		for(var i in sxlist) {
			cols.push(sxlist[i]);
		}
		cols.push("year");
		cols.push("year");
		var columns = [];
		for(var i in cols) {
			var col = cols[i];
			if(i < 2) {
				columns.push({
					name : col,
					data : col,
					sortable: false
				});
			} else {
				columns.push({
					name : col,
					data : "year",
					sortable: false
				});
			}
		}
		var columnDefs = [];
		var items = [];
		for(var i = 2; i < length+2; i++) {
			(function(index) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var value = item.lastYzList[index-1];
						var isRed = false;
						var numValue = value;
						if(list && value) {
							numValue = list[value];
						}
						if(index == 2) {
							items[iRow] = {};
							items[iRow].total = 0;
							if(value && value == item.lastYzList[length+1]) {
								isRed = true;
								$(nTd).css("color", "white").css("backgroundColor", "red");
							}
						} else if(index == length+1) {
							if(value && value == item.lastYzList[0]) {
								isRed = true;
								$(nTd).css("color", "white").css("backgroundColor", "red");
							}
						}
						if(numValue) {
							items[iRow].total += parseInt(numValue);
						}
						if(!isRed) {
							$(nTd).css("backgroundColor", "#ffc");
						}
						$(nTd).text(numValue);
					}
				});
			})(i);
		}
		columnDefs.push({
			aTargets: [length+2],
			fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
				$(nTd).text(items[iRow].total);
			}
		});
		columnDefs.push({
			aTargets: [length+3],
			fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
				$(nTd).text(Math.floor(items[iRow].total / dividen * 100) / 100);
			}
		});
		datatables.push(createDataTable({
			id : "dataTable",
			url : url,
			bFilter: false,
			data : function(queryInfo, infoSettings) {
				items = [];
				queryInfo.object = {};
				queryInfo.object.year = parseInt($("#years").val());
				queryInfo.object.phase = parseInt($("#phases").val());
			},
			columns : columns,
			aoColumnDefs: columnDefs
		}));
		
	});
}