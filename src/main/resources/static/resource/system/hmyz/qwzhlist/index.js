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
	
	var sxlist = ["w0", "w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9"];
	var datatables = [];
	var cols = ["year"];
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
	var pairs = [
		[0,0], [0,1],
		[1,0], [1,2],
		[2,0], [2,3],
		[3,0], [3,4],
		[4,0], [4,5],
		[5,0], [5,6],
		[6,0], [6,7],
		[7,0], [7,8],
		[8,0], [8,9],
		[9,0], [9,10]
	];
	columnDefs.push({
		aTargets: [0],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.year > 0) {
				var pair = pairs[iRow];
				$(nTd).css("color", "blue").text(pair[0] + "/" + pair[1]);
			} else {
				$(nTd).css("fontWeight", "bold").css("fontSize", "20px").text("合计: " + item.phase);
			}
		}
	});
	for(var i = 1; i < 11; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.year > 0) {
						var num = item[sxlist[index-1]];
						$(nTd).css("fontWeight", "bold").text(num);
					} else {
						$(nTd).css("fontWeight", "bold").css("fontSize", "20px").text(num);
					}
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listQWZH",
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


