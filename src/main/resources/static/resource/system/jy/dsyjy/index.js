$(document).ready(function() {
	
	$("#version").combobox();
	$("#reverse").combobox();
	
	var cols = ["year", "phase", "specialNum"];
	for(var i=1; i < 12; i++) {
		cols.push("phase");
	}
	for(var i=1; i < 11; i++) {
		cols.push("c" + i + "n");
	}
	cols.push("phase");
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	var itemYz = null;
	var itemCount = null;
	var hms = null;
	var columnDefs = [];
	columnDefs.push({
		aTargets: [0],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.date) {
				value = item.year;
				if(item.date == "unknown") {
					$(nTd).attr("tj", "true");
				} else {
					$(nTd).attr("tj", "false");
				}
			} else {
				value = "个数";
				$(nTd).attr("tj", "true");
			}
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [1],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			hms = [];
			if(item.date) {
				value = item.phase;
			} 
			$(nTd).text(value);
		}
	});
	columnDefs.push({
		aTargets: [2],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			if(item.specialNum) {
				value = item.specialNum;
			} 
			$(nTd).text(value);
		}
	});
	for(var i = 3; i < 13; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var currentIndex = index - 2;
					if(item.date) {
						var lastNums = item["c" + currentIndex + "n"];
						if(lastNums) {
							lastNums = lastNums.split(/,\s*/);
							if(item.specialNum) {
								var yzField = "c" + currentIndex + "_yz";
								var yzCountField = "c" + currentIndex + "_yz_count";
								var restart = false;
								for(var i in lastNums) {
									var num = lastNums[i];
									if(num == item.specialNum) {
										itemYz[yzField] = 0;
										restart = true;
										if(!itemCount[yzCountField]) {
											itemCount[yzCountField] = 1;
										} else {
											itemCount[yzCountField]++;
										}
										$(nTd).css("backgroundColor", "red").css("color", "white");
										break;
									}  
								}
								if(!restart && itemYz[yzField] >= 0) {
									itemYz[yzField]++;
								}
								if(itemYz[yzField] >= 0) {
									if(itemYz[yzField] > 0) {
										$(nTd).css("backgroundColor", "#ffc");
									}
									$(nTd).text(itemYz[yzField]);
								} else {
									$(nTd).css("backgroundColor", "#ffc");
									$(nTd).text("");
								}
							} else {
								$(nTd).css("backgroundColor", "#ffc");
								$(nTd).text("");
							}
						} else {
							$(nTd).css("backgroundColor", "#ffc");
							$(nTd).text("");
						}
					} else {
						var yzCountField = "c" + currentIndex + "_yz_count";
						$(nTd).text(itemCount[yzCountField]);
					}
				}
			});
		})(i);
	}
	var currentReverseTd;
	columnDefs.push({
		aTargets: [13],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			currentReverseTd = $(nTd).text("");
		}
	});
	for(var i = 14; i < 24; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var currentIndex = index - 13;
					if(item.date) {
						var value = item["c" + currentIndex + "n"];
						if(value) {
							var arr = value.split(/,\s*/);
							for(var i in arr) {
								var num = arr[i];
								if(!isInArr(hms, num)) {
									hms.push(num);
								}
							}
							var len = arr.length < 10 ? "&nbsp;&nbsp;" + arr.length : arr.length;
							$(nTd).attr("nums", arr.length).html("<span style='background-color:red;padding: 5px;color:white; margin-right: 10px;'>" + len + "</span>" + value);
						} else {
							$(nTd).attr("nums", " ").text("");
						}
					} else {
						$(nTd).text("");
					}
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [24],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			var value = "";
			var arr = [];
			if(item.date) {
				for(var i = 1; i < 50; i++) {
					if(!isInArr(hms, i)) {
						arr.push(i);
					}
				}
				if(arr.length > 0) {
					value = arr.join(", ");
				}
			}
			if(arr.length > 0) {
				$(nTd).attr("nums", arr.length).text(value);
			} else {
				$(nTd).attr("nums", " ").text(value);
			}
			
			if(item.date) {
				var lastNums = arr;
				if(lastNums && lastNums.length > 0) {
					if(item.specialNum) {
						var yzField = "cr_yz";
						var yzCountField = "cr_yz_count";
						var restart = false;
						for(var i in lastNums) {
							var num = lastNums[i];
							if(num == item.specialNum) {
								itemYz[yzField] = 0;
								restart = true;
								if(!itemCount[yzCountField]) {
									itemCount[yzCountField] = 1;
								} else {
									itemCount[yzCountField]++;
								}
								currentReverseTd.css("backgroundColor", "red").css("color", "white");
								break;
							}  
						}
						if(!restart && itemYz[yzField] >= 0) {
							itemYz[yzField]++;
						}
						if(itemYz[yzField] >= 0) {
							if(itemYz[yzField] > 0) {
								currentReverseTd.css("backgroundColor", "#ffc");
							}
							currentReverseTd.text(itemYz[yzField]);
						} else {
							currentReverseTd.css("backgroundColor", "#ffc");
							currentReverseTd.text("");
						}
					} else {
						currentReverseTd.css("backgroundColor", "#ffc");
						currentReverseTd.text("");
					}
				} else {
					currentReverseTd.css("backgroundColor", "#ffc");
					currentReverseTd.text("");
				}
			} else {
				var yzCountField = "cr_yz_count";
				currentReverseTd.text(itemCount[yzCountField]);
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listDsyJY",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			itemYz = {};
			itemCount = {};
			queryInfo.object = {};
			queryInfo.object.version = $("#version").val();
			queryInfo.object.reverse = $("#reverse").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


