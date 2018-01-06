$(document).ready(function() {
	
	$("#version").combobox();
	$("#qc").combobox();
	$("#reverse").combobox();
	
	$("#downloadBtn").unbind().click(function() {
		var years = [];
		var phases = [];
		var specialNums = [];
		var c1 = [];
		var c2 = [];
		var c3 = [];
		var c4 = [];
		var c5 = [];
		var c6 = [];
		var c7 = [];
		var c8 = [];
		var c9 = [];
		var c10 = [];
		var c11 = [];
		var c1n = [];
		var c2n = [];
		var c3n = [];
		var c4n = [];
		var c5n = [];
		var c6n = [];
		var c7n = [];
		var c8n = [];
		var c9n = [];
		var c10n = [];
		var c11n = [];
		
		var table = $("#dataTable");
		table.find("tbody").find("tr").each(function() {
			var tds = $(this).find("td");
			if(tds.eq(0).attr("tj") == 'false') {
				years.push(tds.eq(0).text());
				phases.push(tds.eq(1).text());
				specialNums.push(tds.eq(2).text());
				c1.push(tds.eq(3).text() == '0' ? "1" : " ");
				c2.push(tds.eq(4).text() == '0' ? "1" : " ");
				c3.push(tds.eq(5).text() == '0' ? "1" : " ");
				c4.push(tds.eq(6).text() == '0' ? "1" : " ");
				c5.push(tds.eq(7).text() == '0' ? "1" : " ");
				c6.push(tds.eq(8).text() == '0' ? "1" : " ");
				c7.push(tds.eq(9).text() == '0' ? "1" : " ");
				c8.push(tds.eq(10).text() == '0' ? "1" : " ");
				c9.push(tds.eq(11).text() == '0' ? "1" : " ");
				c10.push(tds.eq(12).text() == '0' ? "1" : " ");
				c11.push(tds.eq(13).text() == '0' ? "1" : " ");
				c1n.push(tds.eq(14).attr("nums"));
				c2n.push(tds.eq(15).attr("nums"));
				c3n.push(tds.eq(16).attr("nums"));
				c4n.push(tds.eq(17).attr("nums"));
				c5n.push(tds.eq(18).attr("nums"));
				c6n.push(tds.eq(19).attr("nums"));
				c7n.push(tds.eq(20).attr("nums"));
				c8n.push(tds.eq(21).attr("nums"));
				c9n.push(tds.eq(22).attr("nums"));
				c10n.push(tds.eq(23).attr("nums"));
				c11n.push(tds.eq(24).attr("nums"));
			}
		});
		
		$("#yearList").val(years.join(","));
		$("#phaseList").val(phases.join(","));
		$("#specialNums").val(specialNums.join(","));
		$("#c1").val(c1.join(","));
		$("#c2").val(c2.join(","));
		$("#c3").val(c3.join(","));
		$("#c4").val(c4.join(","));
		$("#c5").val(c5.join(","));
		$("#c6").val(c6.join(","));
		$("#c7").val(c7.join(","));
		$("#c8").val(c8.join(","));
		$("#c9").val(c9.join(","));
		$("#c10").val(c10.join(","));
		$("#c11").val(c11.join(","));
		$("#c1n").val(c1n.join(","));
		$("#c2n").val(c2n.join(","));
		$("#c3n").val(c3n.join(","));
		$("#c4n").val(c4n.join(","));
		$("#c5n").val(c5n.join(","));
		$("#c6n").val(c6n.join(","));
		$("#c7n").val(c7n.join(","));
		$("#c8n").val(c8n.join(","));
		$("#c9n").val(c9n.join(","));
		$("#c10n").val(c10n.join(","));
		$("#c11n").val(c11n.join(","));
		
		downloadForm.submit();
	});
	
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
				value = arr.join(", ");
			}
			if(arr.length > 0) {
				$(nTd).attr("nums", arr.length).text(value);
			} else {
				$(nTd).attr("nums", " ");
			}
			
			if(item.date) {
				var lastNums = arr;
				if(lastNums) {
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
		url : "/mvc/yz/listDsxJY",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			itemYz = {};
			itemCount = {};
			queryInfo.object = {};
			queryInfo.object.version = $("#version").val();
			queryInfo.object.reverse = $("#reverse").val();
			queryInfo.object.qc = $("#qc").val();
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


