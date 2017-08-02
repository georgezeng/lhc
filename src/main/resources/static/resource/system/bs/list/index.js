$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var sxlist = ["redOdd", "redEven", "blueOdd", "blueEven", "greenOdd", "greenEven"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("delta");
	cols.push("lastYz");
	cols.push("total");
	cols.push("totalAvg");
	cols.push("max");
	cols.push("maxAvg");
	cols.push("min0");
	cols.push("min0Avg");
	cols.push("min1");
	cols.push("min1Avg");
	cols.push("min2");
	cols.push("min2Avg");
	cols.push("min3");
	cols.push("min3Avg");
	cols.push("min4");
	cols.push("min4Avg");
	cols.push("min5");
	cols.push("min5Avg");
	cols.push("min6");
	cols.push("min6Avg");
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
	for(var i = 2; i < 8; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[sxlist[index-2]];
					if(value == 0) {
						var color = null;
						if(index < 4) { 
							color = "red";
						} else if(index < 6) {
							color = "blue";
						} else {
							color = "green";
						}
						$(nTd).css("color", "white").css("backgroundColor", color);
					} else {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [9],
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
		url : "/mvc/yz/listBSYZ",
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


