$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var sxlist = ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12"];
	var columns = createColumns(sxlist);
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
	for(var i = 2; i < 14; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.id) {
						var value = item[sxlist[index-2]];
						if(value == 0) {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else {
							$(nTd).css("backgroundColor", "#ffc");
						}
						$(nTd).text(value);
					} else {
						var text = [];
						var num;
						var range = 4;
						var length = 12;
						for(j in item.list) {
							var pos = parseInt(j) + 1;
							if(pos % range == 0) {
								num = parseInt(pos / range);
							} else {
								num = parseInt(pos / range) + 1;
							}
							if(num > length) {
								num = length;
							}
							if(num == index - 1) {
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
		aTargets: [15],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastYz;
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
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listTM12FDYZ?mode=1",
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


