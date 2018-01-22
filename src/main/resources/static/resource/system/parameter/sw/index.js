$(document).ready(function() {
	
	$("#columns").combobox();
	
	var redCounts = [];
	var lastItem;
	var cols = ["year", "phase", "specialNum", "phase", "a1Nums", "phase", 
		"a2Nums", "phase", "a3Nums", "phase", "a3pNums", "phase", "arNums", "phase", "arA2A3A3PNums", "phase", "a1a2a3Nums"];
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
			if(item.year) {
				$(nTd).text(item.year);
			} else {
				$(nTd).text("个数");
			}
		}
	});
	columnDefs.push({
		aTargets: [1],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.phase) {
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
				$(nTd).text(item.specialNum);
			} else {
				$(nTd).text("");
			}
		}
	});
	for(var i = 3; i < 17; i++) {
		(function(index) {
			if(index == 4 || index == 6 || index == 8 || index == 10 || index == 12 || index == 14 || index == 16) {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						if(lastItem) {
							var nums = null;
							switch(index) {
							case 4: nums = lastItem.a1Nums ? lastItem.a1Nums.split(/,\s*/) : null; break;
							case 6: nums = lastItem.a2Nums ? lastItem.a2Nums.split(/,\s*/) : null; break;
							case 8: nums = lastItem.a3Nums ? lastItem.a3Nums.split(/,\s*/) : null; break;
							case 10: nums = lastItem.a3pNums ? lastItem.a3pNums.split(/,\s*/) : null; break;
							case 12: nums = lastItem.arNums ? lastItem.arNums.split(/,\s*/) : null; break;
							case 14: nums = lastItem.arA2A3A3PNums ? lastItem.arA2A3A3PNums.split(/,\s*/) : null; break;
							case 16: nums = lastItem.a1a2a3Nums ? lastItem.a1a2a3Nums.split(/,\s*/) : null; break;
							}
							if(nums != null) {
								var len = nums.length < 10 ? "&nbsp;&nbsp;" + nums.length : nums.length;
								$(nTd).html("<span style='background-color:red;padding: 5px;color:white; margin-right: 10px;'>" + len + "</span>" + nums.join(", "));
							} else {
								$(nTd).text("");	
							}
						} else {
							$(nTd).text("");	
						}
						if(index == 16) {
							lastItem = item;
						}
					}
				});
			} else {
				columnDefs.push({
					aTargets: [index],
					fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
						var value = "0";
						if(lastItem) {
							var idx = (index - 3)/2;
							if(item.year) {
								if(!redCounts[idx]) {
									redCounts[idx] = 0;
								}
								var nums = null;
								switch(index) {
								case 3: nums = lastItem.a1Nums ? lastItem.a1Nums.split(/,\s*/) : null; break;
								case 5: nums = lastItem.a2Nums ? lastItem.a2Nums.split(/,\s*/) : null; break;
								case 7: nums = lastItem.a3Nums ? lastItem.a3Nums.split(/,\s*/) : null; break;
								case 9: nums = lastItem.a3pNums ? lastItem.a3pNums.split(/,\s*/) : null; break;
								case 11: nums = lastItem.arNums ? lastItem.arNums.split(/,\s*/) : null; break;
								case 13: nums = lastItem.arA2A3A3PNums ? lastItem.arA2A3A3PNums.split(/,\s*/) : null; break;
								case 15: nums = lastItem.a1a2a3Nums ? lastItem.a1a2a3Nums.split(/,\s*/) : null; break;
								}
								if(nums != null && nums.length > 0) {
									for(var i = 0; i < nums.length; i++) {
										if(parseInt(nums[i]) == item.specialNum) {
											value = "1";
											redCounts[idx] += 1;
											break;
										}
									}
								}
							} else {
								value = redCounts[idx];
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
			lastItem = null;
			redCounts = [];
			queryInfo.object = {};
			queryInfo.object.type = $("#columns").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


