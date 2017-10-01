$(document).ready(function() {
	var count = 0;
	
	$("#columns").combobox();
	
	$("#showOrHideBtn").click(function() {
		var value = $("#columns").val();
		var table = $("#dataTable");
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
		countNumber();
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
	
	var sxlist = [
		"sxj0", "sxzfj0",
		"dsj0", "dszfj0",
		"swj0", "swzfj0",
		"mwj0", "mwzfj0",
		"lhj0", "lhzfj0",
		"bsj0", "bszfj0",
		"zsj0", "zszfj0",
		"wxj0", "wxzfj0",
		"wxdsj0", "wxdszfj0",
		"pdj0", "pdzfj0",
		"fdj0", "fdzfj0",
		"qqj0", "qqzfj0",
		"twelvej0", "twelvezfj0",
		"slqj0", "slqzfj0",
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
						if(value == 0) {
							$(nTd).css("backgroundColor", "red").css("color", "white");
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
					if(item[sxlist[i]] == 0) {
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
		url : "/mvc/yz/listAllJ0",
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


