$(document).ready(function() {
	var lastGreen = false;
	var lastRed = false;
	var count = 0;
	var sxlist = [];
	var lastItem;
	for(var i = 1; i < 8; i++) {
		sxlist.push("fd" + i);
	}
	var cols = ["year", "phase"];
	for(var i in sxlist) {
		cols.push(sxlist[i]);
	}
	cols.push("total");
	cols.push("delta");
	cols.push("lastYz");
	cols.push("maxYz");
	cols.push("prevDelta");
	cols.push("tm");
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
	for(var i = 0; i < 2; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.id) {
						if(index == 0) {
							$(nTd).text(item.year);
						} else {
							$(nTd).text(item.phase);
						}
					} else {
						$(nTd).text("");
					}
				}
			});
		})(i);
	}
	var lastNum = null;
	for(var i = 2; i < 9; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					if(item.id) {
						var value = item[sxlist[index-2]];
						if(item.id) {
							if(value == 0) {
								$(nTd).css("color", "white").css("backgroundColor", "red");
							} else {
								$(nTd).css("backgroundColor", "#ffc");
							}
						}
						$(nTd).text(value);
					} else {
						var text = [];
						var num;
						var range = 7;
						var length = 7;
						for(j in item.list) {
							var pos = parseInt(j) + 1;
							if(pos % range == 0) {
								num = parseInt(pos / range);
							} else {
								num = parseInt(pos / range) + 1;
							}
							if(num > length) {
								num = length;
							}
							if(num == index - 1) {
								text.push(item.list[j].num);
							}
						}
						$(nTd).text(text.join(", "));
					}
				}
			});
		})(i);
	}
	columnDefs.push({
		aTargets: [11],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				var value = item.lastYz;
				if(value > 7) {
					if(lastGreen) {
						count++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "green");
					lastRed = false;
					lastGreen = true;
				} else {
					if(lastRed) {
						count++;
					}
					$(nTd).css("color", "white").css("backgroundColor", "red");
					lastRed = true;
					lastGreen = false;
				}
				$(nTd).text(value);
			} else {
				$(nTd).text("");
			}
		}
	});
	columnDefs.push({
		aTargets: [13],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				$(nTd).text(item.prevDelta);
			} else {
				var input = $("<input id='prevDelta' size='5' readonly=true />").appendTo($(nTd));
			}
		}
	});
	columnDefs.push({
		aTargets: [14],
		fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
			if(item.id) {
				lastItem = item;
				$(nTd).text(item.tm);
			} else {
				var input = $("<input size='5' maxLength='2' />").appendTo($(nTd));
				$.removeCookie('year');
				$.removeCookie('phase');
				$.removeCookie('prevDelta');
				$.removeCookie('tm');
				input.keyup(function() {
					var value = $(this).val();
					if(value != "" && !isNaN(value)) {
						var prevDelta = value - lastItem.tm;
						$("#prevDelta").val(prevDelta);
						$.cookie('year', lastItem.year);
						$.cookie('phase', lastItem.phase);
						$.cookie('tm', value);
						$.cookie('prevDelta', prevDelta);
					} else {
						$("#prevDelta").val("");
						$.removeCookie('year');
						$.removeCookie('phase');
						$.removeCookie('tm');
						$.removeCookie('prevDelta');
					}
				});
			}
		}
	});
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listTMFDYZ?mode=1",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			count = 0;
			lastRed = false;
			lastGreen = false;
			lastItem = null;
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


