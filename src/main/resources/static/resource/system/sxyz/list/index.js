$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			years.append("<option value='0'>全部</option>");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().change(function() {
				post({
					url: '/mvc/yz/phases/' + $(this).val(),
					success: function(list) {
						var phases = $("#phases");
						phases.empty();
						phases.append("<option value='0'>全部</option>");
						for(var i in list) {
							phases.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
						}
						if(phases.prev().hasClass("combobox-container")){
							phases.prev().remove();
						}
						phases.combobox();
					}
				});
			}).change();
		}
	});
	
	var sxlist = ["shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"];
	var datatables = [];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("delta");
	cols.push("lastYz");
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
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					} else {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [16],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = item.lastYz;
			if(value > 0 && value < 7) {
				$(nTd).css("color", "white").css("backgroundColor", "red");
			} else if(value > 6 && value < 10) {
				$(nTd).css("backgroundColor", "yellow");
			} else if(value > 9) {
				$(nTd).css("color", "white").css("backgroundColor", "green");
			}
			$(nTd).text(value);
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listSX",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		order: [[0, 'asc'], [1, 'asc']],
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


