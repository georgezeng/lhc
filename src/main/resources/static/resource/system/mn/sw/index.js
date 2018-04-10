$(document).ready(function() {
	
	var cols = ["year", "phase", "specialNum"];
	for(var i=1; i < 11; i++) {
		cols.push("c" + i + "n");
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
	for(var i = 3; i < 13; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var currentIndex = index - 2;
					if(item.date) {
						var value = item["c" + currentIndex + "n"];
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
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listSwMn",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


