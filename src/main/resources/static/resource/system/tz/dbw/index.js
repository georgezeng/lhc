categories = categories.slice(1);

$(document).ready(function() {
	addTM12fdCategory("");
	
	$("#clearBtn").click(function() {
		conditionCount = 1;
		$("#conditionTable").find("tbody").empty();
	});
	
	var conditionCount = 1;
	$("#addBtn").click(function() {
		var input = $("#hmlist");
		var tbody = $("#conditionTable").find("tbody");
		var tr = $("<tr></tr>").appendTo(tbody);
		$("<td name='lineNum'></td>").text(conditionCount++).appendTo(tr);
		$("<td></td>").text(input.attr("text")).appendTo(tr);
		$("<td name='hm'></td>").text(input.val()).appendTo(tr).attr("txt", input.attr("text")).attr("hm", input.val());
		$("<a href='javascript:;'>删除</a>").appendTo($("<td></td>").appendTo(tr)).click(function() {
			$(this).parents("tr").remove();
			conditionCount = 1;
			tbody.find("td[name='lineNum']").each(function() {
				$(this).text(conditionCount++);
			});
		});
	});
	
	$("#qcBtn").click(function() {
		qcHm("");
	});
	
	$("#pickupBtn").click(function() {
		var count = 1;
		var tbody = $("#conditionTable").find("tbody");
		if(!(tbody.children().length == 9 || tbody.children().length == 13)) {
			alert("请选择9或13种分类");
			return;
		}
		
		var zhlist = createZHlist(tbody.children().length);
		var tds = tbody.find("td[name='hm']");
		$("#datatable2").find("tbody").empty();
		var dataTbody = $("#datatable").find("tbody").empty();
		for(var i in zhlist) {
			var zh = zhlist[i];
			var categories = [];
			var hms = [];
			for(var j in zh) {
				var td = tds.eq(zh[j]);
				categories.push(td.attr("txt"));
				var arr = td.attr("hm").split(/,\s*/);
				for(var k in arr) {
					var num = arr[k];
					if(!isInArr(hms, num)) {
						hms.push(num);
					}
				}
			}
			categories = categories.join(" | ");
			hms.sort(function(a, b){return a-b});
			
			var nonHms = [];
			for(var m = 1; m < 50; m++) {
				if(!isInArr(hms, m)) {
					nonHms.push(m);
				}
			}
			
			hms = hms.join(", ");
			nonHms = nonHms.join(", ");
			
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(count++);
			$("<td>").appendTo(dataTr).text(categories);
//			$("<td>").appendTo(dataTr).text(hms);
			$("<td>").appendTo(dataTr).text(nonHms);
			$("<td>").appendTo(dataTr).append($("<input name='ctlist' type='checkbox' />").attr("categories", categories).val(nonHms).click(function() {
				var checkedAll = false;
				$("input[name='ctlist']").each(function() {
					checkedAll = $(this).prop("checked");
					if(!checkedAll) {
						return false;
					}
				}); 
				$("input[name='allCheck']").prop("checked", checkedAll);
			})).append($("<a href='javascript:;' style='margin-left: 20px;'>删除</a>").click(function() {
				$(this).parents("tr").remove();
			}));
		}
		
		var dataTr = $("<tr>").appendTo(dataTbody);
		$("<td>").appendTo(dataTr).text("行号");
		$("<td>").appendTo(dataTr).text("组合");
		$("<td>").appendTo(dataTr).text("反转");
		$("<td>").appendTo(dataTr).append($("<input name='allCheck' type='checkbox' />").click(checkAllOrNot));
		
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	$("input[name='allCheck']").click(checkAllOrNot);
	
	function checkAllOrNot(obj) {
		var checked = $(this).prop("checked");
		$("input[name='allCheck']").prop("checked", checked);
		$("input[name='ctlist']").prop("checked", checked);
	}
	
	$("#mergeBtn").click(function() {
		var arr = [];
		$("input[name='ctlist']").each(function (){
			if($(this).prop("checked")) {
				arr.push($(this));
			}
		});
		if(arr.length == 0) {
			alert("请选择至少一条筛选结果");
			return;
		}
		var gsHms = $("#gslist").val().split(/,\s*/);
		if(!gsHms || gsHms.length == 1 && gsHms == "") {
			alert("请输入公式号码");
			return;
		}
		
		var count = 1;
		var dataTbody = $("#datatable2").find("tbody").empty();
		var allHms = [];
		var downloadForm = $("#download").empty();
		for(var i in arr) {
			var hms = arr[i].val().split(/,\s*/);
			var categories = arr[i].attr("categories");
			var newHms = [];
			for(var j in hms) {
				var num = hms[j];
				if(!isInArr(gsHms, num)) {
					newHms.push(num);
				}
			}
			newHms.sort(function(a, b){return a-b});
			
			for(var i in newHms) {
				var hm = newHms[i];
				var isIn = false;
				for(var j in allHms) {
					var hmObj = allHms[j];
					if(hmObj.num == hm) {
						hmObj.count++;
						isIn = true;
						break;
					}
				}
				if(!isIn) {
					allHms.push({num: hm, count: 1});
				}
			}
			
			newHms = newHms.join(", ");
			
			$("<input type='hidden' name='hms"+count+"'>").appendTo(downloadForm).val(newHms);
			$("<input type='hidden' name='categories"+count+"'>").appendTo(downloadForm).val(categories);
			
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(count++);
			$("<td>").appendTo(dataTr).text(categories);
			$("<td>").appendTo(dataTr).text(newHms);
		}
		
		allHms.sort(function(a, b) {
			return a.num-b.num;
		});
		var allHmsHtml = "";
		for(var i in allHms) {
			var hm = allHms[i];
			allHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			allHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			allHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			allHmsHtml += "</div>";
		}
		
		var dataTr = $("<tr>").appendTo(dataTbody);
		$("<td>").appendTo(dataTr).text("合计");
		$("<td>").appendTo(dataTr).text("");
		$("<td>").appendTo(dataTr).html(allHmsHtml);
		$("<input type='hidden' name='allHms'>").appendTo(downloadForm).val(allHms);
		$("<input type='hidden' name='allNonHms'>").appendTo(downloadForm).val(allNonHms);
	});
});


