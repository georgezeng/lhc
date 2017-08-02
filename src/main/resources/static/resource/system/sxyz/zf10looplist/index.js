$(document).ready(function() {
	var sxlist = ["top10", "top9", "top8", "top7", "top6", "top5", "top4", "top3", "top2", "top1"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
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
	for(var i = 2; i < 12; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.posList[index-1];
					var isRed = false;
					if(index == 2) {
						if(value && value == item.posList[11]) {
							isRed = true;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					} else if(index == 11) {
						if(value && value == item.posList[0]) {
							isRed = true;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					}
					if(!isRed) {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/countSXZF10Loop",
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


