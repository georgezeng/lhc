$(document).ready(function() {
	post({
		url: '/mvc/yz/years',
		success: function(list) {
			var years = $("#years");
			years.append("<option value='0'>0</option>");
			for(var i in list) {
				years.append("<option value='" + list[i] + "'>" + list[i] + "</option>");
			}
			years.combobox().change(function() {
				post({
					url: '/mvc/yz/phases/' + $(this).val(),
					success: function(list) {
						var phases = $("#phases");
						phases.empty();
						phases.append("<option value='0'>0</option>");
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
	
	var tableId = "dataTable";
	var datatable = createDataTable({
		id : tableId,
		url : "/mvc/yz/listSX",
		searchPlaceholder : "年份",
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		lengthMenu : [ 50, 100, 150, 200, 300 ],
		order: [[0, 'asc'], [1, 'asc']],
		columns : [ {
			name : "year",
			data : "year",
			sortable: false
		}, {
			name : "phase",
			data : "phase",
			sortable: false
		}, {
			name : "shu",
			data : "shu",
			sortable: false
		}, {
			name : "niu",
			data : "niu",
			sortable: false
		} , {
			name : "hu",
			data : "hu",
			sortable: false
		} , {
			name : "tu",
			data : "tu",
			sortable: false
		} , {
			name : "long",
			data : "long",
			sortable: false
		} , {
			name : "she",
			data : "she",
			sortable: false
		} , {
			name : "ma",
			data : "ma",
			sortable: false
		} , {
			name : "yang",
			data : "yang",
			sortable: false
		} , {
			name : "hou",
			data : "hou",
			sortable: false
		} , {
			name : "ji",
			data : "ji",
			sortable: false
		} , {
			name : "gou",
			data : "gou",
			sortable: false
		} , {
			name : "zhu",
			data : "zhu",
			sortable: false
		} , {
			name : "total",
			data : "total",
			sortable: false
		} , {
			name : "delta",
			data : "delta",
			sortable: false
		} , {
			name : "lastYz",
			data : "lastYz",
			sortable: false
		} ],
		aoColumnDefs: [
			{
				aTargets: [2],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.shu;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [3],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.niu;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [4],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.hu;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [5],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.tu;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [6],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.long;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [7],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.she;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [8],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.ma;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [9],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.yang;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [10],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.hou;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [11],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.ji;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [12],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.gou;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			},
			{
				aTargets: [13],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item.zhu;
					if(value == 0) {
						$(nTd).css("color", "white").css("backgroundColor", "red");
					}
					$(nTd).text(value);
				}
			}
		]
	});
	
	$("#searchBtn").click(function() {
		datatable.ajax.reload();
	})
	
});

