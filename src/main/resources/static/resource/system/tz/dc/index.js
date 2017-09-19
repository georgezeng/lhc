var zhlist = createZHlist(5);

$(document).ready(function() {
	categories.splice(0, 1);
	addTM12fdCategory("");
	
	$("#clearBtn").click(function() {
		conditions = {
			count1: 1,
			count2: 1
		};
		$("#gs").val("");
		$("#conditionTable1").find("tbody").empty();
		$("#conditionTable2").find("tbody").empty();
	});
	
	var conditions = {
		count1: 1,
		count2: 1
	};
	$("#addBtn").click(addBtnClickEvent(1));
	$("#addSelfSelectBtn").click(addBtnClickEvent(2));
	
	function addBtnClickEvent(index) {
		return function() {
			var input = $("#hmlist");
			var tbody = $("#conditionTable" + index).find("tbody");
			var tr = $("<tr></tr>").appendTo(tbody);
			$("<td name='lineNum'></td>").text(conditions["count" + index]++).appendTo(tr);
			$("<td></td>").text(input.attr("text")).appendTo(tr);
			$("<td name='hm'></td>").text(input.val()).appendTo(tr).attr("txt", input.attr("text")).attr("hm", input.val());
			$("<a href='javascript:;'>删除</a>").appendTo($("<td></td>").appendTo(tr)).click(function() {
				$(this).parents("tr").remove();
				conditions["count" + index] = 1;
				tbody.find("td[name='lineNum']").each(function() {
					$(this).text(conditions["count" + index]++);
				});
			});
		}
	}
	
	$("#qcBtn").click(function() {
		qcHm(1);
	});
	
	$("#pickupBtn").click(function() {
		if($("#gs").val() == "") {
			alert("请输入公式");
			return;
		}
		var gsHms = $("#gs").val().split(/,\s*/);
		var tbody = $("#conditionTable1").find("tbody");
		if(tbody.children().length > 0 && tbody.children().length != 5) {
			alert("请选择5种分类");
			return;
		}
		var nonGsHms = [];
		for(var m = 1; m < 50; m++) {
			if(!isInArr(gsHms, m)) {
				nonGsHms.push(m);
			}
		}
		var tds = tbody.find("td[name='hm']");
		var count = 1;
		var dataTbody = $("#datatable").find("tbody").empty();
		var allGsPlusHms = [];
		var allGsMinusHms = [];
		var downloadForm = $("#download").empty();
		function createTr(tds, zh) {
			var categories = [];
			var gsPlusHms = [];
			var gsMinusHms = [];
			for(var j in zh) {
				var td = tds.eq(zh[j]);
				categories.push(td.attr("txt"));
				var arr = td.attr("hm").split(/,\s*/);
				for(var k in arr) {
					var num = arr[k];
					if(!isInArr(gsPlusHms, num)) {
						gsPlusHms.push(num);
					}
					if(!isInArr(gsMinusHms, num)) {
						gsMinusHms.push(num);
					}
					if(!isInArr(allGsPlusHms, num)) {
						allGsPlusHms.push(num);
					}
					if(!isInArr(allGsMinusHms, num)) {
						allGsMinusHms.push(num);
					}
				}
			}
			for(var k in gsHms) {
				var num = gsHms[k];
				if(!isInArr(gsPlusHms, num)) {
					gsPlusHms.push(num);
				}
				if(!isInArr(allGsPlusHms, num)) {
					allGsPlusHms.push(num);
				}
			}
			for(var k in nonGsHms) {
				var num = nonGsHms[k];
				if(!isInArr(gsPlusHms, num)) {
					gsMinusHms.push(num);
				}
				if(!isInArr(allGsMinusHms, num)) {
					allGsMinusHms.push(num);
				}
			}
			categories = categories.join(" | ") + " | 公式";
			gsPlusHms.sort(function(a, b){return a-b});
			gsMinusHms.sort(function(a, b){return a-b});
			
			var gsPlusNonHms = [];
			var gsMinusMonHms = [];
			for(var m = 1; m < 50; m++) {
				if(!isInArr(gsPlusHms, m)) {
					gsPlusNonHms.push(m);
				}
				if(!isInArr(gsMinusHms, m)) {
					gsMinusMonHms.push(m);
				}
			}
			
			gsPlusHms = gsPlusHms.join(", ");
			gsMinusHms = gsMinusHms.join(", ");
			gsPlusNonHms = gsPlusNonHms.join(", ");
			gsMinusMonHms = gsMinusMonHms.join(", ");
			
			$("<input type='hidden' name='gsPlusHms"+count+"'>").appendTo(downloadForm).val(gsPlusHms);
			$("<input type='hidden' name='gsMinusHms"+count+"'>").appendTo(downloadForm).val(gsMinusHms);
			$("<input type='hidden' name='gsPlusNonHms"+count+"'>").appendTo(downloadForm).val(gsPlusNonHms);
			$("<input type='hidden' name='gsMinusMonHms"+count+"'>").appendTo(downloadForm).val(gsMinusMonHms);
			$("<input type='hidden' name='categories"+count+"'>").appendTo(downloadForm).val(categories);
			
			var dataTr = $("<tr>").appendTo(dataTbody);
			$("<td>").appendTo(dataTr).text(count++);
			$("<td>").appendTo(dataTr).text(categories);
			$("<td>").appendTo(dataTr).text(gsPlusHms);
			$("<td>").appendTo(dataTr).text(gsMinusHms);
			$("<td>").appendTo(dataTr).text(gsPlusNonHms);
			$("<td>").appendTo(dataTr).text(gsMinusMonHms);
		}
		
		var done = false;
		
		if(tds.length > 0) {
			for(var i in zhlist) {
				createTr(tds, zhlist[i]);
			}
			done = true;
		}
		
		tbody = $("#conditionTable2").find("tbody");
		tds = tbody.find("td[name='hm']");
		if(tds.length > 0) {
			var zh = [];
			for(var i = 0; i < tds.length; i++) {
				zh.push(i);
			}
			count = 1;
			createTr(tds, zh);
			done = true;
		}
		
		if(!done) {
			alert("请先选择手动或自动组合条件");
			return;
		}
		
		var allGsPlusNonHms = [];
		var allGsMinusNonHms = [];
		for(var m = 1; m < 50; m++) {
			if(!isInArr(allGsPlusHms, m)) {
				allGsPlusNonHms.push(m);
			}
			if(!isInArr(allGsMinusHms, m)) {
				allGsMinusNonHms.push(m);
			}
		}
		
		lenOfallGsPlusHms = allGsPlusHms.length;
		lenOfallGsMinusHms = allGsMinusHms.length;
		allGsPlusHms.sort(function(a, b){return a-b});
		allGsMinusHms.sort(function(a, b){return a-b});
		allGsPlusNonHms.sort(function(a, b){return a-b});
		allGsMinusNonHms.sort(function(a, b){return a-b});
		allGsPlusHms = allGsPlusHms.join(", ");
		allGsMinusHms = allGsMinusHms.join(", ");
		allGsPlusNonHms = allGsPlusNonHms.join(", ");
		allGsMinusNonHms = allGsMinusNonHms.join(", ");
		
		var dataTr = $("<tr>").appendTo(dataTbody);
		$("<td>").appendTo(dataTr).text("合计");
		$("<td>").appendTo(dataTr).text("整合(公式+共"+lenOfallGsPlusHms+"个，公式-共"+lenOfallGsMinusHms+"个)");
		$("<td>").appendTo(dataTr).text(allGsPlusHms);
		$("<td>").appendTo(dataTr).text(allGsMinusHms);
		$("<td>").appendTo(dataTr).text(allGsPlusNonHms);
		$("<td>").appendTo(dataTr).text(allGsMinusNonHms);
		$("<input type='hidden' name='allGsPlusHms'>").appendTo(downloadForm).val(allGsPlusHms);
		$("<input type='hidden' name='allGsMinusHms'>").appendTo(downloadForm).val(allGsMinusHms);
		$("<input type='hidden' name='allGsPlusNonHms'>").appendTo(downloadForm).val(allGsPlusNonHms);
		$("<input type='hidden' name='allGsMinusNonHms'>").appendTo(downloadForm).val(allGsMinusNonHms);
		
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	
});


