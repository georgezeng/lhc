$(document).ready(function() {
	addTM12fdCategory("");
	
	var zhlist = createZHlist(9);
	
	$("#clearBtn").click(function() {
		$("#conditionTable").find("tbody").empty();
		$("#gslist").val("");
		$("#qlBtn").click();
	});
	
	$("#qlBtn").click(function() {
		var datatable = $("#datatable").find("tbody").empty();
		$("<td id='totalTd'></td>").appendTo(
			$("<tr id='totalTr'><td>总计</td></tr>")
			.appendTo(datatable).css("backgroundColor", "#f9f9f9")
			.css("border", "1px solid #ddd"));
		$("#download").empty().append($("<input id='totalResult' type='hidden' name='totalResult'></input>"));
		$("#download").append($("<input id='totalList' type='hidden' name='totalList'></input>"));
		lastSZColor = null;
		lastSz = null;
		lastResult = 0;
		lastColor = null;
		totalHms = [];
	})
	
	var totalHms;
	var lastSZColor;
	var lastSz;
	$("#addBtn").click(function() {
		var sz = $("#fdlist").val();
		if(!lastSz) {
			lastSz = sz;
			lastSZColor = "#ffc";
		} else if(lastSz != sz) {
			lastSz = sz;
			if(lastSZColor == "#ffc") {
				lastSZColor = "#ffffff";
			} else {
				lastSZColor = "#ffc";
			}
		}
		var input = $("#hmlist");
		var tbody = $("#conditionTable").find("tbody");
		var sameSzTr = tbody.find("tr[name='sz"+sz+"']:last-child");
		var tr = $("<tr name='sz"+sz+"'></tr>").appendTo(tbody);
		if(sameSzTr.length > 0) {
			sameSzTr.after(tr);
		} 
		tr.css("backgroundColor", lastSZColor).css("border", "1px solid #ddd");
		$("<td></td>").text(sz).appendTo(tr);
		$("<td></td>").text(input.attr("text")).appendTo(tr);
		$("<td name='hm'></td>").text(input.val()).appendTo(tr).attr("txt", input.attr("text")).attr("hm", input.val());
		$("<a href='javascript:;'>删除</a>").appendTo($("<td></td>").appendTo(tr)).click(function() {
			$(this).parents("tr").remove();
			if(tbody.children().length == 0) {
				lastSZColor = null;
				lastSz = null;
			}
		});
	});
	
	$("#fdlist").combobox();
	$("#tclist").combobox();
	
	$("#qcBtn").click(function() {
		filter(true);
	});
	
	var lastColor;
	var lastResult = 0;
	$("#pickupBtn").click(function() {
		filter(false);
	});
	
	function filter(isQc) {
		var tbody = $("#conditionTable").find("tbody");
		function collectSZ(index) {
			var sz = [];
			tbody.find("tr[name='sz"+index+"']").each(function() {
				var hms = $(this).find("td[name='hm']").attr("hm").split(/,\s*/);
				for(var i in hms) {
					var hm = hms[i];
					if(!isInArr(sz, hm)) {
						sz.push(hm);
					}
				}
			});
			return sz;
		}
		
		var tempGsHms = $("#gslist").val().split(/,\s*/);
		var tcHms = $("#tclist").val().trim().split(/,\s*/);
		function collectGS(fdHms) {
			for(var i in tempGsHms) {
				var hm = tempGsHms[i];
				if(!isInArr(tcHms, hm) && !isInArr(fdHms, hm)) {
					fdHms.push(hm);
				}
			}
		}
		
		function collectFD(fd1, fd2, fd3, fd4) {
			var sz = [];
			function collect(fd) {
				for(var i in fd) {
					var hm = fd[i];
					if(!isInArr(sz, hm)) {
						sz.push(hm);
					}
				}
			}
			collect(fd1);
			collect(fd2);
			collect(fd3);
			collect(fd4);
			collectGS(sz);
			var reverse = [];
			for(var i = 1; i < 50; i++) {
				if(!isInArr(sz, i)) {
					reverse.push(i);
				}
			}
			return reverse;
		}
		
		var A = collectSZ('A');
		var B = collectSZ('B');
		var C = collectSZ('C');
		var D = collectSZ('D');
		var E = collectSZ('E');
		var F = collectSZ('F');
		var G = collectSZ('G');
		var H = collectSZ('H');
		
		var AE=[A, E],BF=[B, F],CG=[C, G],DH=[D, H];
		if(isQc) {
			function qc(fd1, fd2) {
				var a = [];
				for(var i in fd1) {
					var hm = fd1[i];
					if(!isInArr(fd2, hm)) {
						a.push(hm);
					}
				}
				var b = [];
				for(var i in fd2) {
					var hm = fd2[i];
					if(!isInArr(fd1, hm)) {
						b.push(hm);
					}
				}
				return [a, b];
			}
			
			AE = qc(A, E);
			BF = qc(B, F);
			CG = qc(C, G);
			DH = qc(D, H);
		}
		
		var ABCD = collectFD(A, B, C, D);
		var EFGH = collectFD(E, F, G, H);
		
		var EBCD = collectFD(AE[1], B, C, D);
		var AFCD = collectFD(A, BF[1], C, D);
		var ABGD = collectFD(A, B, CG[1], D);
		var ABCH = collectFD(A, B, C, DH[1]);
		
		var AFGH = collectFD(AE[0], F, G, H);
		var EBGH = collectFD(E, BF[0], G, H);
		var EFCH = collectFD(E, F, CG[0], H);
		var EFGD = collectFD(E, F, G, DH[0]);
		
		var datatable = $("#datatable").find("tbody");
		if(lastResult % 2 == 0) {
			lastColor = "#ffc";
		} else {
			lastColor = "#fff";
		}
		
		var downloadForm = $("#download");
		var allHms = [];
		function createTr(fd) {
			var fdHms = eval(fd);
			$("<td></td>").appendTo(
					$("<tr><td>"+fd+"</td></tr>")
						.appendTo(datatable)
						.before($("#totalTr"))
						.css("backgroundColor", lastColor)
					.css("border", "1px solid #ddd"))
					.text(fdHms.join(", "));
			
			$("<input type='hidden' name='fd_" + fd + "_name_" + lastResult + "'>").appendTo(downloadForm).val(fd);
			$("<input type='hidden' name='fd_" + fd + "_value_" + lastResult + "'>").appendTo(downloadForm).val(fdHms.join(", "));
			
			for(var i in fdHms) {
				var hm = fdHms[i];
				var found = false;
				for(var j in allHms) {
					var item = allHms[j];
					if(item.num == hm) {
						item.count++;
						found = true;
						break;
					}
				}
				if(!found) {
					allHms.push({
						num: hm,
						count: 1
					});
				}
				found = false;
				for(var j in totalHms) {
					var item = totalHms[j];
					if(item.num == hm) {
						item.count++;
						found = true;
						break;
					}
				}
				if(!found) {
					totalHms.push({
						num: hm,
						count: 1
					});
				}
			}
		}
		
		var trs = [
			"ABCD",
			"EFGH",
			"EBCD",
			"AFCD",
			"ABGD",
			"ABCH",
			"AFGH",
			"EBGH",
			"EFCH",
			"EFGD"
		];
		for(var i in trs) {
			createTr(trs[i]);
		}
		
		var allHmsTxt = "";
		for(var i in allHms) {
			var hm = allHms[i];
			allHmsTxt += "(" + hm.num + " | " + hm.count;
			if(i < allHms.length - 1) {
				allHmsTxt += "), ";
			} else {
				allHmsTxt += ")";
			}
		}
		$("<input type='hidden' name='fdlist_" + lastResult + "'>").appendTo(downloadForm).val(allHmsTxt);
		
		var totalHmsTxt = "";
		for(var i in totalHms) {
			var hm = totalHms[i];
			totalHmsTxt += "(" + hm.num + " | " + hm.count;
			if(i < allHms.length - 1) {
				totalHmsTxt += "), ";
			} else {
				totalHmsTxt += ")";
			}
		}
		$("#totalList").val(totalHmsTxt);
		
		lastResult++;
		$("#totalResult").val(lastResult);
		
		var allHmsHtml = "";
		for(var i in allHms) {
			var hm = allHms[i];
			allHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			allHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			allHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			allHmsHtml += "</div>";
		}
		
		var totalHmsHtml = "";
		for(var i in totalHms) {
			var hm = totalHms[i];
			totalHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			totalHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			totalHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			totalHmsHtml += "</div>";
		}
		$("<td></td>").appendTo(
			$("<tr name='totalPart'><td>合计</td></tr>")
				.appendTo(datatable)
				.before($("#totalTr"))
				.css("backgroundColor", "#f9f9f9")
			.css("border", "1px solid #ddd"))
			.html(allHmsHtml);
		$("<tr>").appendTo(datatable).before($("#totalTr"));
		$("#totalTd").html(totalHmsHtml);
	}
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	$("#clearBtn").click();
});


