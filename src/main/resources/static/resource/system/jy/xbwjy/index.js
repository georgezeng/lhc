$(document).ready(function() {
	
	$("#columns").combobox();
	
	var cols = ["year", "phase", "specialNum"];
	for(var i=1; i < 11; i++) {
		cols.push("w" + i);
	}
	for(var i=1; i < 11; i++) {
		cols.push("c" + i);
	}
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
	var lastItem = null;
	var itemYz = null;
	var itemCount = null;
	var hms = null;
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
			hms = [];
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
					var currentIndex = index - 2;
					if(currentIndex == 4 || currentIndex == 6 || currentIndex == 7 || currentIndex == 8 || currentIndex == 9) {
						$(nTd).text("").hide();
						return ;
					}
					if(item.year) {
						if(lastItem) {
							var lastNums = lastItem["c" + currentIndex];
							var yzField = "c" + currentIndex + "_yz";
							var yzCountField = "c" + currentIndex + "_yz_count";
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
						var yzCountField = "c" + currentIndex + "_yz_count";
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
					var currentIndex = index - 12;
					if(currentIndex == 4 || currentIndex == 6 || currentIndex == 7 || currentIndex == 8 || currentIndex == 9) {
						$(nTd).text("").hide();
						return ;
					}
					if(item.year) {
						if(lastItem) {
							var arr = lastItem["c" + (index-12)];
							var value = arr.join(", ");
							for(var i in arr) {
								var num = arr[i];
								if(!isInArr(hms, num)) {
									hms.push(num);
								}
							}
							$(nTd).text(value);
						} else {
							$(nTd).text("");
						}
					} else {
						var value = "";
						if(lastItem) {
							var arr = lastItem["c" + (index-12)];
							var value = arr.join(", ");
							for(var i in arr) {
								var num = arr[i];
								if(!isInArr(hms, num)) {
									hms.push(num);
								}
							}
						}
						$(nTd).text(value);
					}
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [23],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(lastItem) {
				var arr = [];
				for(var i = 1; i < 50; i++) {
					if(!isInArr(hms, i)) {
						arr.push(i);
					}
				}
				value = arr.join(", ");
			}
			lastItem = item;
			hms = null;
			$(nTd).text(value);
		}
	});
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


