$(document).ready(function() {
	var lastRed = false;
	var lastGreen = false;
	var count = 0;
	var sxlist = ["zf0", "zf1", "zf2", "zf3", "zf4", "zf5", "zf6", "zf7", "zf8", "zf9", "zf10", "zf11"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("year");
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
	for(var i = 2; i < 14; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(item.id) {
						value = item[sxlist[index-2]];
						var small = 0;
						var large = 0;
						
						var uniqueArr = [];
						for(var j in sxlist) {
							var found = false;
							for(var k in uniqueArr) {
								if(uniqueArr[k] == item[sxlist[j]]) {
									found = true;
									break;
								}
							}
							if(!found) {
								uniqueArr.push(item[sxlist[j]]);
							}
						}
						uniqueArr.sort(function(a, b){return a-b});
						
						// 1,1
						if(uniqueArr.length == 2) {
							small = uniqueArr[0];
							large = uniqueArr[1];
						}
						
						// 1,1,1
						else if(uniqueArr.length == 3) {
							small = uniqueArr[0];
							large = uniqueArr[2];
						}
						
						// 2,1,1
						else if(uniqueArr.length == 4) {
							small = uniqueArr[1];
							large = uniqueArr[3];
						}
						
						// 2,1,2
						else if(uniqueArr.length == 5) {
							small = uniqueArr[1];
							large = uniqueArr[3];
						}
						
						// 2,2,2
						else if(uniqueArr.length == 6) {
							small = uniqueArr[1];
							large = uniqueArr[4];
						}
						
						// 3,1,3
						else if(uniqueArr.length == 7) {
							small = uniqueArr[2];
							large = uniqueArr[4];
						}
						
						// 3,2,3
						else if(uniqueArr.length == 8) {
							small = uniqueArr[2];
							large = uniqueArr[5];
						}
						
						// 3,3,3
						else if(uniqueArr.length == 9) {
							small = uniqueArr[2];
							large = uniqueArr[6];
						}
						
						// 4,2,4
						else if(uniqueArr.length == 10) {
							small = uniqueArr[3];
							large = uniqueArr[6];
						}
						
						// 4,3,4
						else if(uniqueArr.length == 11) {
							small = uniqueArr[3];
							large = uniqueArr[7];
						}
						
						// 4,4,4
						else {
							small = uniqueArr[3];
							large = uniqueArr[8];
						}
						
						if(value < small + 1) {
							$(nTd).css("color", "white").css("backgroundColor", "green");
						} else if(value > small && value < large) {
							$(nTd).css("backgroundColor", "yellow");
						} else {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [14],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			$(nTd).text("");
		}
	});
	columnDefs.push({
		aTargets: [15],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				var avg = item.avg;
				value = item.delta;
				var lastCount = item.lastCountYz;
				if(lastCount != null) {
					if(lastCount < avg) {
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
				} else {
					lastRed = false;
					lastGreen = false;
				}
			} else {
				value = Math.round(count / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listSXZFLevel2",
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


