$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().unbind().change(function() {
				post({
					url: '/mvc/yz/phases/' + $(this).val(),
					success: function(list) {
						var phases = $("#phases");
						phases.empty();
						for(var i in list) {
							phases.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
						}
						if(phases.prev().hasClass("combobox-container")){
							phases.prev().remove();
						}
						phases.combobox();
						phases.unbind().change(function() {
							reloadTables();
						}).change();
					}
				});
			}).change();
		}
	});
	
	var sxlist = ["top10", "top9", "top8", "top7", "top6", "top5", "top4", "top3", "top2", "top1"];
	var datatables = [];
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		if(i < 2) {
			columns.push({
				name : col,
				data : col,
				sortable: false
			});
		} else {
			columns.push({
				name : col,
				data : "year",
				sortable: false
			});
		}
	}
	var columnDefs = [];
	for(var i = 2; i < 12; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.lastYzList[index-1];
					var isRed = false;
					if(index == 2) {
						if(value && value == item.lastYzList[11]) {
							isRed = true;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					} else if(index == 11) {
						if(value && value == item.lastYzList[0]) {
							isRed = true;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						}
					}
					if(!isRed) {
						$(nTd).css("backgroundColor", "#ffc");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/countSX10Loop",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
	$("#searchBtn").click(function() {
		reloadTables();
	});
	
	$("#calYZBtn").click(function() {
		openLoading();
		post({
			url: '/mvc/yz/calYZ/',
			success: function() {
				alert("遗值计算完成");
				reloadTables();
				closeLoading();
			},
			systemError: function(msg) {
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


