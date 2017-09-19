$(document).ready(function() {
	var dsLastGreen = false;
	var dsLastRed = false;
	var dxLastGreen = false;
	var dxLastRed = false;
	var ddLastGreen = false;
	var ddLastRed = false;
	var dsCount = 0;
	var dxCount = 0;
	var ddCount = 0;
	var csCount = [];
	for(var i = 0; i < 19; i++) {
		csCount[i] = 0;
	}
	var cols = ["year", "phase", "small", "large", "lastSLYz", "phase", "phase", "odd", "even", "lastOEYz", "phase", "phase", "smallOdd", "smallEven", "largeOdd", "largeEven", "lastSLOEYz", "phase", "phase"];
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
	for(var i = 2; i < 19; i++) {
		(function(index) {
			if(index != 4 && index != 5 && index != 6  
					&& index != 9 && index != 10 && index != 11
					&& index != 16 && index != 17 && index != 18) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var value = item[cols[index]];
						if(item.id) {
							if(value == 0) {
								csCount[index]++;
								$(nTd).css("color", "white").css("backgroundColor", "red");
							} else {
								$(nTd).css("backgroundColor", "#ffc");
							}
						} else {
							value = csCount[index];
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
				value = item.lastSLYz;
				if(value > 2) {
					if(dxLastGreen) {
						dxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					dxLastRed = false;
					dxLastGreen = true;
				} else {
					if(dxLastRed) {
						dxCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					dxLastRed = true;
					dxLastGreen = false;
				}
			} else {
				value = Math.round(dxCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [5],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.small;
				if(item.large > item.small) {
					value = item.large;
				}
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [6],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.small + item.large;
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	
	columnDefs.push({
		aTargets: [9],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastOEYz;
				if(value > 2) {
					if(dsLastGreen) {
						dsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					dsLastRed = false;
					dsLastGreen = true;
				} else {
					if(dsLastRed) {
						dsCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					dsLastRed = true;
					dsLastGreen = false;
				}
			} else {
				value = Math.round(dsCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [10],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.odd;
				if(item.even > item.odd) {
					value = item.even;
				}
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [11],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.odd + item.even;
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	
	columnDefs.push({
		aTargets: [16],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.lastSLOEYz;
				if(value > 4) {
					if(ddLastGreen) {
						ddCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					ddLastRed = false;
					ddLastGreen = true;
				} else {
					if(ddLastRed) {
						ddCount++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					ddLastRed = true;
					ddLastGreen = false;
				}
			} else {
				value = Math.round(ddCount / item.total * 10000) / 100 + "%";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [17],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.smallOdd;
				if(item.smallEven > value) {
					value = item.smallEven;
				}
				if(item.largeOdd > value) {
					value = item.largeOdd;
				}
				if(item.largeEven > value) {
					value = item.largeEven;
				}
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [18],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = null;
			if(item.id) {
				value = item.smallOdd + item.smallEven + item.largeOdd + item.largeEven;
			} else {
				value = "";
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listHMDSYZ",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			dsLastGreen = false;
			dsLastRed = false;
			dxLastGreen = false;
			dxLastRed = false;
			ddLastGreen = false;
			ddLastRed = false;
			dsCount = 0;
			dxCount = 0;
			ddCount = 0;
			for(var i = 0; i < 19; i++) {
				csCount[i] = 0;
			}
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


