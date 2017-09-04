var zhlist = createZHlist(9);

$(document).ready(function() {
	addTM12fdCategory();
	
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
		if(tbody.children().length != 9) {
			alert("请选择9种分类");
			return;
		}
		var tds = tbody.find("td[name='hm']");
		var dataTbody = $("#datatable").find("tbody").empty();
		var allHms = [];
		var allNonHms = [];
		pickup = {};
		var downloadForm = $("#download");
		downloadForm.empty();
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
					if(!isInArr(allHms, num)) {
						allHms.push(num);
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
			
			$("<input type='hidden' name='hms"+count+"'>").appendTo(downloadForm).val(hms);
			$("<input type='hidden' name='nonHms"+count+"'>").appendTo(downloadForm).val(nonHms);
			$("<input type='hidden' name='categories"+count+"'>").appendTo(downloadForm).val(categories);
			
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(count++);
			$("<td>").appendTo(dataTr).text(categories);
			$("<td>").appendTo(dataTr).text(hms);
			$("<td>").appendTo(dataTr).text(nonHms);
		}
		for(var m = 1; m < 50; m++) {
			if(!isInArr(allHms, m)) {
				allNonHms.push(m);
			}
		}
		
		allHms.sort(function(a, b){return a-b});
		allNonHms.sort(function(a, b){return a-b});
		allHms = allHms.join(", ");
		allNonHms = allNonHms.join(", ");
		
		var dataTr = $("<tr>").appendTo(dataTbody);
		$("<td>").appendTo(dataTr).text("合计");
		$("<td>").appendTo(dataTr).text("整合(共"+allHms.length+"个)");
		$("<td>").appendTo(dataTr).text(allHms);
		$("<td>").appendTo(dataTr).text(allNonHms);
		$("<input type='hidden' name='allHms'>").appendTo(downloadForm).val(allHms);
		$("<input type='hidden' name='allNonHms'>").appendTo(downloadForm).val(allNonHms);
		
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	
});


