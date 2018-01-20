$(document).ready(function() {
	
	$("#columns").combobox();
	
	var cols = ["year", "phase", "specialNum", "phase", "a1Nums", "phase", 
		"a2Nums", "phase", "a3Nums", "phase", "a3pNums", "phase", "arNums", "phase", "arA2A3A3PNums"];
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
	for(var i = 3; i < 15; i++) {
		(function(index) {
			if(index == 4 || index == 6 || index == 8 || index == 10 || index == 12 || index == 14) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var nums = null;
						switch(index) {
						case 4: nums = item.a1Nums ? item.a1Nums.split(/,\s*/) : null; break;
						case 6: nums = item.a2Nums ? item.a2Nums.split(/,\s*/) : null; break;
						case 8: nums = item.a3Nums ? item.a3Nums.split(/,\s*/) : null; break;
						case 10: nums = item.a3pNums ? item.a3pNums.split(/,\s*/) : null; break;
						case 12: nums = item.arNums ? item.arNums.split(/,\s*/) : null; break;
						case 14: nums = item.arA2A3A3PNums ? item.arA2A3A3PNums.split(/,\s*/) : null; break;
						}
						if(nums != null) {
							var len = nums.length < 10 ? "&nbsp;&nbsp;" + nums.length : nums.length;
							$(nTd).html("<span style='background-color:red;padding: 5px;color:white; margin-right: 10px;'>" + len + "</span>" + nums.join(", "));
						} else {
							$(nTd).text("");	
						}
					}
				});
			} else {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var nums = null;
						switch(index) {
						case 3: nums = item.a1Nums ? item.a1Nums.split(/,\s*/) : null; break;
						case 5: nums = item.a2Nums ? item.a2Nums.split(/,\s*/) : null; break;
						case 7: nums = item.a3Nums ? item.a3Nums.split(/,\s*/) : null; break;
						case 9: nums = item.a3pNums ? item.a3pNums.split(/,\s*/) : null; break;
						case 11: nums = item.arNums ? item.arNums.split(/,\s*/) : null; break;
						case 13: nums = item.arA2A3A3PNums ? item.arA2A3A3PNums.split(/,\s*/) : null; break;
						}
						var value = "0";
						if(nums != null && nums.length > 0) {
							for(var i = 0; i < nums.length; i++) {
								if(parseInt(nums[i]) == item.specialNum) {
									value = "1";
									break;
								}
							}
						}
						$(nTd).text(value);
					}
				});
			}
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listFXSWA",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.type = $("#columns").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


