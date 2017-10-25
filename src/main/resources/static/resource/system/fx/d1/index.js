$(document).ready(function() {
	var count = 0;
	
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
			});
		}
		countNumber();
	});
	
	function countNumber() {
		count = 0;
		var table = $("#dataTable");
		table.find("tr").each(function() {
			var tr = $(this);
			var subCount = 0;
			tr.children().each(function() {
				if($(this).attr("status") == "show" && $(this).attr("red") == "true") {
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
	
	var sxlist = [
		"sxd1", "sxzfd1",
		"dsd1", "dszfd1",
		"swd1", "swzfd1",
		"mwd1", "mwzfd1",
		"lhd1", "lhzfd1",
		"bsd1", "bszfd1",
		"zsd1", "zszfd1",
		"wxd1", "wxzfd1",
		"wxdsd1", "wxdszfd1",
		"pdd1", "pdzfd1",
		"fdd1", "fdzfd1",
		"qqd1", "qqzfd1",
		"twelved1", "twelvezfd1",
		"slqd1", "slqzfd1",
	];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
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
	for(var i = 2; i < 30; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = "";
					if(item.date) {
						value = item[sxlist[index-2]];
						if(item["redFor" + sxlist[index-2]]) {
							$(nTd).attr("red", "true").css("backgroundColor", "red").css("color", "white");
						} else {
							$(nTd).css("backgroundColor", "#ffc")
						}
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
		aTargets: [30],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = 0;
			if(item.date) {
				for(var i in sxlist) {
					if(item["redFor" + sxlist[i]]) {
						value = 1;
						count++;
						break;
					}
				}
				$(nTd).attr("name", "counter");
			} else {
				value = count;
				$(nTd).attr("id", "counter");
				countNumber();
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listAllD1",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = 0;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


