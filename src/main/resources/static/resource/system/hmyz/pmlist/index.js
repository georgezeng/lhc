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
	
	var sxlist = ["num1", "num2", "num3", "num4", "num5", "num6"];
	var sxlist2 = ["tp1", "tp2", "tp3", "tp4", "tp5", "tp6"];
	var datatables = [];
	var cols = ["year", "phase", "specialNum"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("specialNum");
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
			if(item.year > 0) {
				$(nTd).text(item.year);
			} else {
				$(nTd).text("占比");
			}
		}
	});
	columnDefs.push({
		aTargets: [1],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.phase > 0) {
				$(nTd).text(item.phase);
			} else {
				$(nTd).text("");
			}
		}
	});
	columnDefs.push({
		aTargets: [2],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.specialNum) {
				$(nTd).css("fontWeight", "bold").css("color", "blue").text(item.specialNum.num);
			}
		}
	});
	for(var i = 3; i < 9; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.year > 0) {
						var num = item[sxlist[index-3]];
						if(num.matchedForSpecialNum) {
							$(nTd).css("color", "red");
						}
						$(nTd).css("fontWeight", "bold").text(num.num);
					} else {
						var num = item[sxlist2[index-3]];
						$(nTd).css("fontWeight", "bold").text(num + "%");
					}
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [9],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.specialNum) {
				$(nTd).css("fontWeight", "bold").css("color", "green").text(item.specialNum.delta);
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/pmList",
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


