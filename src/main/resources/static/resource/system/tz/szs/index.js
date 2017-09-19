categories.splice(0, 1);

$(document).ready(function() {
	function addBtnEvent(index, callback) {
		var input = $("#hmlist" + index);
		var tbody = $("#conditionTable" + index).find("tbody");
		var tr = $("<tr></tr>").appendTo(tbody);
		$("<td name='lineNum'></td>").text(conditionTableCount[index]++).appendTo(tr);
		var text = input.attr("text");
		if(callback) {
			text = callback();
		} 
		$("<td></td>").text(text).appendTo(tr);
		$("<td name='hm'></td>").text(input.val()).appendTo(tr).attr("txt", input.attr("text")).attr("hm", input.val());
		$("<a href='javascript:;'>删除</a>").appendTo($("<td></td>").appendTo(tr)).click(function() {
			$(this).parents("tr").remove();
			conditionTableCount[index] = 1;
			tbody.find("td[name='lineNum']").each(function() {
				$(this).text(conditionTableCount[index]++);
			});
		});
	}
	
	addTM12fdCategory(1);
	
	$(".limitList").combobox();
	
	post({
		url: "/mvc/yz/listLastCJ",
		success: function(result) {
			if(result) {
				var item = result.list[0];
				var category = $("#jgCategory");
				for(var i = 0; i <= 7; i++) {
					var text = "间隔" + i;
					if(i == 7) {
						i = "6Plus";
						text = "间隔6+";
					}
					var hms = item["jg"+i];
					category.append($("<option>").text(text).val(hms.join(", ")));
				}
				category.combobox().change(function() {
					var thisEl = $(this);
					var selectedIndex = thisEl.prop('selectedIndex');
					$("#hmlist2").val(thisEl.val()).attr("text", thisEl.find("option").eq(selectedIndex).text());
				}).change();
			}
		}
	});
	
	$("#clearBtn").click(function() {
		for(var i = 1; i < 4; i++) {
			conditionTableCount[i] = 1;
			$("#conditionTable" + i).find("tbody").empty();
		}
	});
	
	var conditionTableCount = [];
	for(var i = 1; i < 3; i++) {
		conditionTableCount[i] = 1;
		$("#addBtn" + i).click(function(index) {
			return function() {
				addBtnEvent(index);
			}
		}(i));
	}
	conditionTableCount[3] = 1;
	$("#addBtn3").click(function() {
		addBtnEvent(3, function() {
			return $("#limitList3").val();
		});
	});
	
	$("#qcBtn").click(function() {
		for(var i = 1; i < 4; i++) {
			qcHm(i);
		}
	});
	
	function loopComposite(originalCompositeArr, callback, parentCompositeArr, index) {
		if(!index) {
			index = 0;
		}
		if(!parentCompositeArr) {
			parentCompositeArr = [];
		}
		var compositeArr = originalCompositeArr[index];
		for(var i in compositeArr) {
			if(originalCompositeArr[index+1]) {
				loopComposite(originalCompositeArr, callback, parentCompositeArr.concat([compositeArr[i]]), index+1);
			} else {
				callback(parentCompositeArr.concat([compositeArr[i]]));
			}
		}
	}
	
	$("#pickupBtn").click(function() {
		var tbody = $("#conditionTable3").find("tbody");
		if(tbody.children().length != 7) {
			alert("请选择7组号码");
			return;
		}
		
		openLoading();
		
		var totalLimit = parseInt($("#limitList4").val());
		var compisiteLevel1Arr = [];
		tbody.children().each(function() {
			var tr = $(this);
			var limit = parseInt(tr.children().eq(1).text());
			var hms = tr.children().eq(2).attr("hm").split(/,\s*/);
			var len = hms.length > limit ? limit : hms.length;
			var compositeLevel2Arr = [];
			for(var i = 1; i <= len; i++) {
				compositeLevel2Arr.push(getCompositeArrs(hms, i));
			}
			compisiteLevel1Arr.push(compositeLevel2Arr);
		});
		
		var hmsCompositeArr = [];
		loopComposite(compisiteLevel1Arr, function(compositeLevel2Arrs) {
			var len = 0;
			for(var i in compositeLevel2Arrs) {
				len += compositeLevel2Arrs[i][0].length;
			}
			if(len <= totalLimit) {
				hmsCompositeArr.push(compositeLevel2Arrs);
			}
		});
		
		var dbwTbody = $("#conditionTable1").find("tbody");
		var dbwHms = [];
		dbwTbody.find("td[name='hm']").each(function() {
			dbwHms = dbwHms.concat($(this).attr("hm").split(/,\s*/));
		});
		var dbwLimit = parseInt($("#limitList1").val());
		
		var jgTbody = $("#conditionTable2").find("tbody");
		var jgHms = [];
		var jgLimit = parseInt($("#limitList2").val());
		jgTbody.find("td[name='hm']").each(function() {
			jgHms = jgHms.concat($(this).attr("hm").split(/,\s*/));
		});
		
		var hmsArr = [];
		for(var i in hmsCompositeArr) {
			loopComposite(hmsCompositeArr[i], function(arr) {
				var dbwCount = 0;
				var jgCount = 0;
				for(var i in arr) {
					for(var j in dbwHms) {
						if(dbwHms[j] == arr[i]) {
							dbwCount++;
							break;
						}
					}
					for(var j in jgHms) {
						if(jgHms[j] == arr[i]) {
							jgHms++;
							break;
						}
					}
				}
				if(dbwCount <= dbwLimit && jgCount <= jgLimit) {
					hmsArr.push(arr);
				}
			});
		}
		
		var dataTbody = $("#datatable").find("tbody").empty();
		var downloadForm = $("#download");
		downloadForm.empty();
		$("<input type='hidden' name='total'>").appendTo(downloadForm).val(hmsArr.length);
		var count = 0;
		for(var i in hmsArr) {
			var hms = hmsArr[i].join(",");
			$("<input type='hidden' name='hms"+(count++)+"'>").appendTo(downloadForm).val(hms);
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(hms);
		}
		
		closeLoading();
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	
});


