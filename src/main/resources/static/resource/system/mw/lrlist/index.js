$(document).ready(function() {
	var lastColor;
	var count = 0;
	var lastItem;
	var sxlist = ["w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9"];
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
	for(var i = 0; i < 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(index == 0) {
						value = item.year;
						if(!item.id) {
							value = "顺概率";
							$(nTd).css("color", "blue");
						} 
					} else {
						value = item.phase;
						if(!item.id) {
							value = "";
						}
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
					var value = item[sxlist[index-2]];
					if(item.id) {
						var uniqueArr = [];
						for(var j in sxlist) {
							uniqueArr.push(item[sxlist[j]]);
						}
						uniqueArr.sort(function(a, b){return a-b});
						var small = uniqueArr[2];
						var large = uniqueArr[6];
						
						var color = null;
						if(value < small + 1) {
							color = ["white", "red"];
						} else if(value > small && value < large) {
							color = ["black", "yellow"];
						} else {
							color = ["white", "green"];
						}
						if(!item.colors) {
							item.colors = [];
						}
						item.colors.push(color);
						if(value == 0) {
							item.colorIndex = index - 2;
						}
						$(nTd).css("color", color[0]).css("backgroundColor", color[1]);
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [12],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			$(nTd).text("");
		}
	});
	columnDefs.push({
		aTargets: [13],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				if(lastItem) {
					var color = lastItem.colors[item.colorIndex][1];
					$(nTd).text("").css("backgroundColor", color);
					if(lastColor) {
						if(lastColor == color) {
							count++;
						}
					}
					lastColor = color;
				} else {
					$(nTd).text("");
				}
				lastItem = item;
			} else {
				value = Math.round(count / item.total * 10000) / 100 + "%";
				$(nTd).text(value);
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listMWYZ?mode=1",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			lastItem = null;
			count = 0;
			lastColor = null;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


