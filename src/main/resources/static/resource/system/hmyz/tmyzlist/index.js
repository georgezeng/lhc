$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var sxlist = [];
	for(var i = 1; i < 50; i++) {
		sxlist.push("hm" + i);
	}
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("delta");
	cols.push("lastYz");
	cols.push("maxYz");
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
	for(var i = 2; i < 51; i++) {
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
		aTargets: [53],
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
		url : "/mvc/yz/listTMYZ",
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
	
});


