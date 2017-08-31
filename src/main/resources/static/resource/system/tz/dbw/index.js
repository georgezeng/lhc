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

var zhlist = createZHlist(9);
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

$(document).ready(function() {
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
					$("#hmlist").val(item.value).attr("text", category.text + "-" + item.text);
				}).change();
			}).change();
		}
	});
	
	$("#clearBtn").click(function() {
		conditionCount = 1;
		$("#conditionTable").find("tbody").empty();
	});
	
	var conditionCount = 1;
	$("#addBtn").click(function() {
		var input = $("#hmlist");
		var tbody = $("#conditionTable").find("tbody");
		var tr = $("<tr></tr>").appendTo(tbody);
		$("<td name='lineNum'></td>").text(conditionCount++).appendTo(tr);
		$("<td></td>").text(input.attr("text")).appendTo(tr);
		$("<td name='hm'></td>").text(input.val()).appendTo(tr).attr("txt", input.attr("text")).attr("hm", input.val());
		$("<a href='javascript:;'>删除</a>").appendTo($("<td></td>").appendTo(tr)).click(function() {
			$(this).parents("tr").remove();
			conditionCount = 1;
			tbody.find("td[name='lineNum']").each(function() {
				$(this).text(conditionCount++);
			});
		});
	});
	
	$("#pickupBtn").click(function() {
		var count = 1;
		var tbody = $("#conditionTable").find("tbody");
		if(tbody.children().length != 9) {
			alert("请选择9种分类");
			return;
		}
		var tds = tbody.find("td[name='hm']");
		var dataTbody = $("#datatable").find("tbody").empty();
		var allHms = [];
		var allNonHms = [];
		pickup = {};
		var downloadForm = $("#download");
		downloadForm.empty();
		function isInArr(hms, k, isArr) {
			for(var m in hms) {
				contained = hms[m] == (isArr ? arr[k] : k);
				if(contained) {
					return true;
				}
			}
			return false;
		}
		for(var i in zhlist) {
			var zh = zhlist[i];
			var categories = [];
			var hms = [];
			for(var j in zh) {
				var td = tds.eq(zh[j]);
				categories.push(td.attr("txt"));
				var arr = td.attr("hm").split(/,\s*/);
				for(var k in arr) {
					if(!isInArr(hms, k, true)) {
						hms.push(arr[k]);
					}
					if(!isInArr(allHms, k, true)) {
						allHms.push(arr[k]);
					}
				}
			}
			categories = categories.join(" | ");
			hms.sort(function(a, b){return a-b});
			
			var nonHms = [];
			for(var m = 1; m < 50; m++) {
				if(!isInArr(hms, m)) {
					nonHms.push(m);
				}
			}
			
			hms = hms.join(", ");
			nonHms = nonHms.join(", ");
			
			$("<input type='hidden' name='hms"+count+"'>").appendTo(downloadForm).val(hms);
			$("<input type='hidden' name='nonHms"+count+"'>").appendTo(downloadForm).val(nonHms);
			$("<input type='hidden' name='categories"+count+"'>").appendTo(downloadForm).val(categories);
			
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(count++);
			$("<td>").appendTo(dataTr).text(categories);
			$("<td>").appendTo(dataTr).text(hms);
			$("<td>").appendTo(dataTr).text(nonHms);
		}
		for(var m = 1; m < 50; m++) {
			if(!isInArr(allHms, m)) {
				allNonHms.push(m);
			}
		}
		
		allHms = allHms.join(", ");
		allNonHms = allNonHms.join(", ");
		
		var dataTr = $("<tr>").appendTo(dataTbody);
		$("<td>").appendTo(dataTr).text("合计");
		$("<td>").appendTo(dataTr).text("整合(共"+allHms.length+"个)");
		$("<td>").appendTo(dataTr).text(allHms);
		$("<td>").appendTo(dataTr).text(allNonHms);
		$("<input type='hidden' name='allHms'>").appendTo(downloadForm).val(allHms);
		$("<input type='hidden' name='allNonHms'>").appendTo(downloadForm).val(allNonHms);
		
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	
});


