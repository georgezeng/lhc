$(document).ready(function() {
	var sxlist = ["min1", "add1", "jg0", "jg1", "jg2", "jg3", "jg4", "jg5", "jg6", "jg6Plus"];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
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
	for(var i = 2; i < 12; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					$(nTd).empty();
					var list = item["s" + sxlist[index-2]];
					var line = $("<div style='margin-bottom: 10px;'>").appendTo($(nTd));
					for(var i in list) {
						$("<span>").appendTo(line).text((list[i] < 10 ? "0" + list[i] : list[i]))
							.css("padding", "5px")
							.css("margin", "0 5px 0 5px")
							.css("backgroundColor", "red")
							.css("color", "white");
					}
					list = item[sxlist[index-2]];
					line = $("<div>").appendTo($(nTd));
					for(var i in list) {
						$("<span>").appendTo(line).text((list[i] < 10 ? "0" + list[i] : list[i]))
						.css("padding", "5px")
						.css("margin", "0 5px 0 5px")
						.css("backgroundColor", "green")
						.css("color", "white");
					}
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listCJYZ",
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


