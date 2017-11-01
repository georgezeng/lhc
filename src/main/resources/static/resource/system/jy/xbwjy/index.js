$(document).ready(function() {
	
	$("#columns").combobox();
	
	var cols = ["year", "phase", "specialNum"];
	for(var i=1; i < 11; i++) {
		cols.push("w" + i);
	}
	for(var i=1; i < 11; i++) {
		cols.push("c" + i);
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
	var lastItem = null;
	var itemYz = null;
	var itemCount = null;
	var columnDefs = [];
	columnDefs.push({
		aTargets: [0],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.year) {
				value = item.year;
			} else {
				value = "个数";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [1],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.phase) {
				value = item.phase;
			} 
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [2],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.specialNum) {
				value = item.specialNum;
			} 
			$(nTd).text(value);
		}
	});
	for(var i = 3; i < 13; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.year) {
						if(lastItem) {
							var lastNums = lastItem["c" + (index-2)];
							var yzField = "c" + (index-2) + "_yz";
							var yzCountField = "c" + (index-2) + "_yz_count";
							var restart = false;
							for(var i in lastNums) {
								var num = lastNums[i];
								if(num == item.specialNum) {
									itemYz[yzField] = 0;
									restart = true;
									if(!itemCount[yzCountField]) {
										itemCount[yzCountField] = 1;
									} else {
										itemCount[yzCountField]++;
									}
									$(nTd).css("backgroundColor", "red").css("color", "white");
									break;
								}  
							}
							if(!restart && itemYz[yzField] >= 0) {
								itemYz[yzField]++;
							}
							if(itemYz[yzField] >= 0) {
								if(itemYz[yzField] > 0) {
									$(nTd).css("backgroundColor", "#ffc");
								}
								$(nTd).text(itemYz[yzField]);
							} else {
								$(nTd).css("backgroundColor", "#ffc");
								$(nTd).text("");
							}
						} else {
							$(nTd).css("backgroundColor", "#ffc");
							$(nTd).text("");
						}
					} else {
						var yzCountField = "c" + (index-2) + "_yz_count";
						$(nTd).text(itemCount[yzCountField]);
					}
				}
			});
		})(i);
	}
	for(var i = 13; i < 23; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.year) {
						if(lastItem) {
							var value = lastItem["c" + (index-12)].join(", ");
							$(nTd).text(value);
						} else {
							$(nTd).text("");
						}
						if(index == 22) {
							lastItem = item;
						}
					} else {
						var value = "";
						if(lastItem) {
							value = lastItem["c" + (index-12)].join(", ");
						}
						$(nTd).text(value);
					}
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listXBWJY",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			lastItem = null;
			itemYz = {};
			itemCount = {};
			queryInfo.object = {};
			queryInfo.object.type = $("#columns").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


