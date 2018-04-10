$(document).ready(function() {
	
	var cols = ["year", "phase", "specialNum", "myYz", "myNums", "my100Yz", "my100Nums", "swYz", "swNums", "v1Yz", "v1Nums", "v2Yz", "v2Nums", "v3Yz", "v3Nums"];
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
	var redCounts;
	for(var i = 3; i < 15; i++) {
		(function(index) {
			if(index % 2 == 1) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						if(item.date) {
							if(!redCounts[index]) {
								redCounts[index] = 0;
							}
							var value = sData;
							if (value == '0') {
								redCounts[index]++;
								$(nTd).css("backgroundColor", "red").css("color", "white");
							} else {
								$(nTd).css("backgroundColor", "#ffc");
							}
							$(nTd).text(value);
						} else {
							$(nTd).text(redCounts[index]);
						}
					}
				});
			} else {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						if(item.date) {
							var value = sData;
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
			}
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listCyht",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			redCounts = [];
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


