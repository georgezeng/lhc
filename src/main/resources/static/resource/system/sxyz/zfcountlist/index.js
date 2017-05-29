$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().change(function() {
				post({
					url: '/mvc/yz/phases/' + $(this).val(),
					success: function(list) {
						var phases = $("#phases");
						phases.empty();
						for(var i in list) {
							phases.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
						}
						if(phases.prev().hasClass("combobox-container")) {
							phases.prev().remove();
						}
						phases.combobox();
					}
				});
			}).change();
		}
	});
	
	var sxlist = ["zf0", "zf1", "zf2", "zf3", "zf4", "zf5", "zf6", "zf7", "zf8", "zf9", "zf10", "zf11"];
	var datatables = [];
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
	for(var i = 2; i < 14; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[sxlist[index-2]];
					if(value > 0 && value < 7) {
						$(nTd).css("color", "white").css("backgroundColor", "green");
					} else if(value > 6 && value < 11) {
						$(nTd).css("backgroundColor", "yellow");
					} else if(value > 10) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/countSXZF",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
			queryInfo.object.date = "desc";
		},
		order: [[0, 'desc'], [1, 'desc']],
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
	$("#searchBtn").click(function() {
		reloadTables();
	});
	
	$("#calSXYZBtn").click(function() {
		openLoading();
		post({
			url: '/mvc/yz/calSX/',
			success: function() {
				alert("生肖遗值计算完成");
				reloadTables();
				closeLoading();
			},
			jsonError: function(msg) {
				alert(msg);
				closeLoading();
			}
		});
	});
	
	function reloadTables() {
		for(var i in datatables) {
			datatables[i].ajax.reload();
		}
	}
	
	
});


