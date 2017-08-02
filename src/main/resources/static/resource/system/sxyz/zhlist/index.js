$(document).ready(function() {
	var sxlist = ["shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
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
	for(var i = 2; i < 14; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[sxlist[index-2]];
					if(item.topSx) {
						if(item.topSx.name == item.currentSx.name || item.lastSx && item.lastSx.name == item.topSx.name) {
							if(value == 0) {
								$(nTd).css("color", "white").css("backgroundColor", "red");
							} else {
								$(nTd).css("backgroundColor", "#ffc");
							}
						} else {
							$(nTd).css("backgroundColor", "#ffc");
						}
					} else {
						$(nTd).css("fontWeight", "bold").css("color", "blue");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listSXZH",
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


