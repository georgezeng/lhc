$(document).ready(function() {
	var sxDsLastGreen = false;
	var sxDsLastRed = false;
	var sxDxLastGreen = false;
	var sxDxLastRed = false;
	var hmDsLastGreen = false;
	var hmDsLastRed = false;
	var hmDxLastGreen = false;
	var hmDxLastRed = false;
	var sxDsCount = 0;
	var sxDxCount = 0;
	var hmDsCount = 0;
	var hmDxCount = 0;
	var cols = ["year", "phase", "sxSmall", "sxLarge", "lastSxDxYz", "sxSingle", "sxEven", "lastSxDsYz", "hmSmall", "hmLarge", "lastHmDxYz", "hmSingle", "hmEven", "lastHmDsYz"];
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
	for(var i = 2; i < 13; i++) {
		(function(index) {
			if(index != 4 && index != 7 && index != 10) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var value = item[cols[index]];
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
			}
		})(i);
	}
	columnDefs.push({
		aTargets: [4],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastSxDxYz;
				if(value > 2) {
					if(sxDxLastGreen) {
						sxDxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					sxDxLastRed = false;
					sxDxLastGreen = true;
				} else {
					if(sxDxLastRed) {
						sxDxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					sxDxLastRed = true;
					sxDxLastGreen = false;
				}
			} else {
				value = Math.round(sxDxCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [7],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastSxDsYz;
				if(value > 2) {
					if(sxDsLastGreen) {
						sxDsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					sxDsLastRed = false;
					sxDsLastGreen = true;
				} else {
					if(sxDsLastRed) {
						sxDsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					sxDsLastRed = true;
					sxDsLastGreen = false;
				}
			} else {
				value = Math.round(sxDsCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [10],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastHmDxYz;
				if(value > 2) {
					if(hmDxLastGreen) {
						hmDxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					hmDxLastRed = false;
					hmDxLastGreen = true;
				} else {
					if(hmDxLastRed) {
						hmDxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					hmDxLastRed = true;
					hmDxLastGreen = false;
				}
			} else {
				value = Math.round(hmDxCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [13],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastHmDsYz;
				if(value > 2) {
					if(hmDsLastGreen) {
						hmDsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					hmDsLastRed = false;
					hmDsLastGreen = true;
				} else {
					if(hmDsLastRed) {
						hmDsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					hmDsLastRed = true;
					hmDsLastGreen = false;
				}
			} else {
				value = Math.round(hmDsCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listSXDSYZ",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			sxDsLastGreen = false;
			sxDsLastRed = false;
			sxDxLastGreen = false;
			sxDxLastRed = false;
			hmDsLastGreen = false;
			hmDsLastRed = false;
			hmDxLastGreen = false;
			hmDxLastRed = false;
			sxDsCount = 0;
			sxDxCount = 0;
			hmDsCount = 0;
			hmDxCount = 0;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


