$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var redCounts = [];
	var sxlist = [];
	for(var i = 1; i < 50; i++) {
		sxlist.push("hm" + i);
	}
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("delta");
	cols.push("lastYz");
	cols.push("maxYz");
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
							value = "个数";
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
	for(var i = 2; i < 51; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value;
					if(item.id) {
						value = item[sxlist[index-2]];
						if(value == 0) {
							$(nTd).css("color", "white").css("backgroundColor", "red");
							if(!redCounts[index-2]) {
								redCounts[index-2] = 0;
							}
							redCounts[index-2]++;
						} else {
							$(nTd).css("backgroundColor", "#ffc");
						}
					} else {
						value = redCounts[index-2];
						$(nTd).css("fontWeight", "bold").css("color", "blue");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [53],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value
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
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listTMYZ",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = 0;
			redCounts = [];
			lastRed = false;
			lastGreen = false;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
	$("#downloadBtn").unbind().click(function() {
		$("#size").val($("select[name='dataTable_length']").val());
		$("#endYear").val($("#years").val());
		$("#endPhase").val($("#phases").val());
		$("#download").attr("action", "/mvc/yz/downloadTMYZ");
		$("#download").submit();
	});
	$("#downloadBtn2").click(function() {
		$("#size").val($("select[name='dataTable_length']").val());
		$("#endYear").val($("#years").val());
		$("#endPhase").val($("#phases").val());
		$("#download").attr("action", "/mvc/yz/downloadTMYZ2");
		$("#download").submit();
	});
});


