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
						if(phases.prev().hasClass("combobox-container")){
							phases.prev().remove();
						}
						phases.combobox();
						phases.change(function() {
							reloadTables();
						}).change();
					}
				});
			}).change();
		}
	});
	
	var datatables = [];
	var cols = ["year", "phase"];
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	for(var i = 0; i < 21; i++) {
		var col = "lastYZ" + i;
		columns.push({
			name: col,
			data : "lastYz",
			sortable: false
		});
	}
	cols = ["21-30", "31-40", "41-50", "51+"];
	for(var i in cols) {
		var col = "lastYZ-" + cols[i];
		columns.push({
			name : col,
			data : "lastYz",
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
					var isTotal = false;
					if(index == 0) {
						value = item.year;
						if(value == 0) {
							value = "合计";
							isTotal = true;
						}
					} else {
						value = item.phase;
						if(value == 0) {
							value = "";
							isTotal = true;
						}
					}
					if(isTotal) {
						$(nTd).css("fontWeight", "bold").css("color", "blue");
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	for(var i = 2; i < 27; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = null;
					if(item.year > 0) {
						value = item.lastYz;
						var isRed = false;
						if(index < 23) {
							isRed = value == index-2;
						} else if(index == 23) {
							isRed = value > 20 && value < 31;
						} else if(index == 24) {
							isRed = value > 30 && value < 41;
						} else if(index == 25) {
							isRed = value > 40 && value < 51;
						} else if(index == 26) {
							isRed = value > 50;
						}
						if(isRed) {
							count[index-2] = 1;
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else {
							if(!count[index-2]) {
								count[index-2] = 0;
							}
							value = count[index-2];
							count[index-2] += 1;
						}
					} else {
						value = item.lastYzList[index-2];
						$(nTd).css("fontWeight", "bold");
						var avg = 0;
						for(var i in item.lastYzList) {
							avg += item.lastYzList[i];
						}
						avg = Math.floor(avg / 25);
						if(value > avg + 1) {
							$(nTd).css("color", "white").css("backgroundColor", "red");
						} else if(value < avg - 1) {
							$(nTd).css("color", "white").css("backgroundColor", "green");
						} else {
							$(nTd).css("backgroundColor", "yellow");
						}
					}
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "datatable",
		url : "/mvc/yz/countSXJZ",
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
	
	var count = [];
	function reloadTables() {
		for(var i in datatables) {
			count = [];
			datatables[i].ajax.reload();
		}
	}
	
});


