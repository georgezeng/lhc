$(document).ready(function() {
	
	$("#version").combobox();
	$("#reverse").combobox();
	
	var cols = ["year", "phase", "specialNum"];
	for(var i=1; i < 9; i++) {
		cols.push("phase");
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
	var columnDefs = [];
	columnDefs.push({
		aTargets: [0],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.date) {
				value = item.year;
				if(item.date == "unknown") {
					$(nTd).attr("tj", "true");
				} else {
					$(nTd).attr("tj", "false");
				}
			} else {
				value = "个数";
				$(nTd).attr("tj", "true");
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [1],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.date) {
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
	var indexes = [3, 5, 7, 9];
	for(var i in indexes) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.date) {
						var value = null;
						switch(index) {
						case 3: value = item.aen; break;
						case 5: value = item.bfn; break;
						case 7: value = item.cgn; break;
						case 9: value = item.dhn; break;
						} 
						if(value) {
							var arr = value.split(/,\s*/);
							var len = arr.length < 10 ? "&nbsp;&nbsp;" + arr.length : arr.length;
							$(nTd).attr("nums", arr.length).html("<span style='background-color:red;padding: 5px;color:white; margin-right: 10px;'>" + len + "</span>" + value);
						} else {
							$(nTd).attr("nums", " ").text("");
						}
					} else {
						$(nTd).text("");
					}
				}
			});
		})(indexes[i]);
	}
	var counts = [];
	var indexes = [4, 6, 8, 10];
	for(var i in indexes) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.date) {
						var value = null;
						switch(index) {
						case 4: value = item.aen; break;
						case 6: value = item.bfn; break;
						case 8: value = item.cgn; break;
						case 10: value = item.dhn; break;
						} 
						if(value) {
							var arr = value.split(/,\s*/);
							var isFound = false;
							for(var j in arr) {
								if(arr[j] == item.specialNum) {
									isFound = true;
									break;
								}
							}
							if(isFound) {
								counts[index]++;
								$(nTd).css("backgroundColor", "red").css("color", "white").text("1");
							} else {
								$(nTd).text("0");
							}
						} else {
							$(nTd).text("");
						}
					} else {
						$(nTd).text(counts[index]);
					}
				}
			});
		})(indexes[i]);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listDmgJY",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			counts = [null, null, null, null, 0, null, 0, null, 0, null, 0];
			queryInfo.object = {};
			queryInfo.object.version = $("#version").val();
			queryInfo.object.reverse = $("#reverse").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


