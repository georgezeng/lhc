$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var sxlist = [];
	for(var i = 1; i < 8; i++) {
		sxlist.push("fd" + i);
	}
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("delta");
	cols.push("lastYz");
	cols.push("maxYz");
	cols.push("prevDelta");
	cols.push("tm");
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
					if(item.id) {
						if(index == 0) {
							$(nTd).text(item.year);
						} else {
							$(nTd).text(item.phase);
						}
					} else {
						$(nTd).text("");
					}
				}
			});
		})(i);
	}
	var lastNum = null;
	for(var i = 2; i < 9; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.id) {
						var value = item[sxlist[index-2]];
						if(item.id) {
							if(value == 0) {
								$(nTd).css("color", "white").css("backgroundColor", "red");
							} else {
								$(nTd).css("backgroundColor", "#ffc");
							}
						}
						$(nTd).text(value);
					} else {
						var text = [];
						for(j in item.list) {
							var num;
							var range = 7;
							var length = 7;
							if(j % range == 0) {
								num = j / range;
							} else {
								num = j / range + 1;
							}
							if(num > length) {
								num = length;
							}
							var fd = parseInt(num);
							if(fd == index) {
								text.push(item.list[j].num);
							}
						}
						
						$(nTd).text(text.join(", "));
					}
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [11],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
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
			} else {
				$(nTd).text("");
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listTMFDYZ",
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


