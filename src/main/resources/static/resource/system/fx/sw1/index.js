$(document).ready(function() {
	var count = 0;
	var totalCount = 0;
	var lastHms = null;
	var redCounts = [];
	
	$("#columns").combobox();
	
	$("#showOrHideBtn").click(function() {
		var value = $("#columns").val();
		var table = $("#dataTable");
		lastHms = null;
		if(value == "0" || value == "1") {
			var zf = value == "0" ? "false" : "true";
			table.find("th[zf='"+zf+"']").add(table.find("td[zf='"+zf+"']")).each(function() {
				var td = $(this);
				if(td.attr("status") == "hide") {
					td.attr("status", "show");
					td.show();
				} else {
					td.attr("status", "hide");
					td.hide();
				}
			});
			
			table.find("tbody").find("tr").each(function() {
				var td = $(this).children().eq(70);
				if(td.text() != "反转") {
					if(lastHms) {
						td.text(lastHms);
					}
					lastHms = countReverseNums(td);
				}
			});
		} else {
			table.find("tr").each(function() {
				var td = $(this).children().eq(parseInt(value));
				if(td.attr("status") == "hide") {
					td.attr("status", "show");
					td.show();
				} else {
					td.attr("status", "hide");
					td.hide();
				}
				td = $(this).children().eq(70);
				if(td.text() != "反转") {
					if(lastHms) {
						td.text(lastHms);
					}
					lastHms = countReverseNums(td);
				}
			});
		}
		countNumber();
		countTotalNumber();
	});
	
	function countNumber() {
		count = 0;
		var table = $("#dataTable");
		table.find("tr").each(function() {
			var tr = $(this);
			var subCount = 0;
			tr.children().each(function() {
				if($(this).attr("status") == "show" && $(this).text() == "0") {
					count++;
					subCount++;
					return false;
				}
			});
			if(subCount > 0) {
				$(tr).find("td[name='counter']").text("1");
			} else {
				$(tr).find("td[name='counter']").text("0");
			}
		});
		$("#counter").text(count);
	}
	
	function countTotalNumber() {
		totalCount = 0;
		var table = $("#dataTable");
		table.find("tr").each(function() {
			var tr = $(this);
			var subCount = 0;
			tr.children().each(function() {
				if($(this).attr("status") == "show" && $(this).text() == "0") {
					totalCount++;
					subCount++;
				}
			});
			$(tr).find("td[name='totalCounter']").text(subCount);
		});
		$("#totalCounter").text(totalCount);
	}
	
	function countReverseNums(nTd) {
		var allHms = [];
		nTd.parent("tr").find("td[status='show']").each(function() {
			if($(this).attr("hms")) {
				allHms = allHms.concat($(this).attr("hms").split(/,\s*/));
			}
		});
		var reverseNums = [];
		for(var i = 1; i < 50; i++) {
			if(!isInArr(allHms, i)) {
				reverseNums.push(i);
			}
		}
		return reverseNums.join(", ");
	}
	
	var numlist = [
		"sxNums", "sxzfNums",
		"dsNums", "dszfNums",
		"swNums", "swzfNums",
		"mwNums", "mwzfNums",
		"lhNums", "lhzfNums",
		"bsNums", "bszfNums",
		"zsNums", "zszfNums",
		"wxNums", "wxzfNums",
		"wxdsNums", "wxdszfNums",
		"pdNums", "pdzfNums",
		"fdNums", "fdzfNums",
		"qqNums", "qqzfNums",
		"qiwNums", "qiwzfNums",
		"twelveNums", "twelvezfNums",
		"slqNums", "slqzfNums",
	];
	
	var sxlist = [
		"sx", "sxzf",
		"ds", "dszf",
		"sw", "swzf",
		"mw", "mwzf",
		"lh", "lhzf",
		"bs", "bszf",
		"zs", "zszf",
		"wx", "wxzf",
		"wxds", "wxdszf",
		"pd", "pdzf",
		"fd", "fdzf",
		"qq", "qqzf",
		"qiw", "qiwzf",
		"twelve", "twelvezf",
		"slq", "slqzf",
		"zx1", "zx1zf",
		"zx2", "zx2zf",
		"zx3", "zx3zf",
		"zx4", "zx4zf",
		"zx5", "zx5zf",
		"zx6", "zx6zf",
		"zx7", "zx7zf",
		"zx8", "zx8zf",
		"zx9", "zx9zf",
		"zx10", "zx10zf",
		"zx11", "zx11zf",
		"zx12", "zx12zf",
		"zx13", "zx13zf",
		"zx14", "zx14zf",
		"zx15", "zx15zf",
		"zx16", "zx16zf",
		"zx17", "zx17zf",
		"zx18", "zx18zf"
	];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("phase");
	cols.push("phase");
	cols.push("phase");
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
	columnDefs.push({
		aTargets: [0],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.date) {
				value = item.year;
			} else {
				value = "个数";
			}
			$(nTd).text(value);
		}
	});
	for(var i = 2; i < 68; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = "";
					if(item.date) {
						if(!redCounts[index-2]) {
							redCounts[index-2] = 0;
						}
						value = item[sxlist[index-2]];
						if(value == 0) {
							$(nTd).css("backgroundColor", "red").css("color", "white");
							redCounts[index-2]++;
						} else {
							$(nTd).css("backgroundColor", "#ffc")
						}
						$(nTd).attr("hms", item[numlist[index-2]]);
					} else {
						value = redCounts[index-2];
					}
					var th = $("#dataTable").find("th").eq(index);
					if(th.attr("status") == "hide") {
						$(nTd).attr("status", "hide");
						setTimeout(function() {
							$(nTd).hide();
						}, 100);
					} else {
						$(nTd).attr("status", "show");
					}
					if(index % 2 == 0) {
						$(nTd).attr("zf", "false");
					} else {
						$(nTd).attr("zf", "true");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [68],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = 0;
			if(item.date) {
				for(var i in sxlist) {
					if(item[sxlist[i]] == 0) {
						value = 1;
						count++;
						break;
					}
				}
				$(nTd).attr("name", "counter").text(value);
			} else {
				value = count;
				$(nTd).attr("id", "counter").text(count);
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [69],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var currentCount = 0;
			if(item.date) {
				for(var i in sxlist) {
					if(item[sxlist[i]] == 0) {
						currentCount++;
						totalCount++;
					}
				}
				$(nTd).attr("name", "totalCounter").text(currentCount);
			} else {
				$(nTd).attr("id", "totalCounter").text(totalCount);
			}
		}
	});
	columnDefs.push({
		aTargets: [70],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(lastHms) {
				$(nTd).text(lastHms);
			} else {
				$(nTd).text("");
			}
			if(item.date) {
				lastHms = countReverseNums($(nTd));
			} else {
				lastHms = null;
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listFXSW1",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = 0;
			totalCount = 0;
			lastHms = null;
			redCounts = [];
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


