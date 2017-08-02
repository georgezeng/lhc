$(document).ready(function() {
	var sxlist = ["w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("currentYz");
	cols.push("maxTimes");
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
	for(var i = 2; i < 12; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var num = item[sxlist[index-2]];
					if(num == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					} else if(item.year > 0) {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(num);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [14],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				$(nTd).text(item.maxTimes);
			} else {
				$(nTd).text("");
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listQWYZ",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


