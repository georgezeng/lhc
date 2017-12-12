$(document).ready(function() {
	addTM12fdCategory("");
	
//	var zhlist = createZHlist(9);
	
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
		$("#download").append($("<input id='compositeSize' type='hidden' name='compositeSize'></input>"));
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
	
	$("#xcBtn").click(function() {
		var totalHms = [];
		var tbody = $("#conditionTable").find("tbody");
		
		function collectSZ(index) {
			tbody.find("tr[name='sz"+index+"']").each(function() {
				var hms = $(this).find("td[name='hm']").attr("hm").split(/,\s*/);
				for(var i in hms) {
					var hm = hms[i];
					var found = false;
					for(var j in totalHms) {
						var item = totalHms[j];
						if(item.num == hm) {
							found = true;
							item.count++;
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
			});
		}
		
		collectSZ("A");
		collectSZ("B");
		collectSZ("C");
		collectSZ("D");
		collectSZ("E");
		collectSZ("F");
		collectSZ("G");
		collectSZ("H");
		
		var totalHmsHtml = "";
		totalHms.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? 0 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		lastCount = 0;
		for(var i in totalHms) {
			var hm = totalHms[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					totalHmsHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			totalHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			totalHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			totalHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			totalHmsHtml += "</div>";
		}
		$("<tr>").appendTo(datatable).before($("#totalTr"));
		$("#totalTd").html(totalHmsHtml);
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
		var I = collectSZ('I');
		var J = collectSZ('J');
		var K = collectSZ('K');
		var L = collectSZ('L');
		
		var AEI=[A, E],BFJ=[B, F],CGK=[C, G],DHL=[D, H];
		$("#compositeSize").val(2);
		if(I.length > 0) {
			AEI=[A, E, I];
			BFJ=[B, F, J];
			CGK=[C, G, K];
			DHL=[D, H, L];
			$("#compositeSize").val(3);
		}
		
		if(AEI.length == 2) {
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
				AEI 	= qc(A, E);
				BFJ = qc(B, F);
				CGK = qc(C, G);
				DHL = qc(D, H);
			}
			
			var ABCD = collectFD(A, B, C, D);
			var EFGH = collectFD(E, F, G, H);
			
			var EBCD = collectFD(AEI[1], B, C, D);
			var AFCD = collectFD(A, BFJ[1], C, D);
			var ABGD = collectFD(A, B, CGK[1], D);
			var ABCH = collectFD(A, B, C, DHL[1]);
			
			var AFGH = collectFD(AEI[0], F, G, H);
			var EBGH = collectFD(E, BFJ[0], G, H);
			var EFCH = collectFD(E, F, CGK[0], H);
			var EFGD = collectFD(E, F, G, DHL[0]);
		} else {
			if(isQc) {
				function qc(fd1, fd2, fd3) {
					var a = [];
					for(var i in fd1) {
						var hm = fd1[i];
						if(!isInArr(fd2, hm) && !isInArr(fd3, hm)) {
							a.push(hm);
						}
					}
					var b = [];
					for(var i in fd2) {
						var hm = fd2[i];
						if(!isInArr(fd1, hm) && !isInArr(fd3, hm)) {
							b.push(hm);
						}
					}
					var c = [];
					for(var i in fd3) {
						var hm = fd3[i];
						if(!isInArr(fd1, hm) && !isInArr(fd2, hm)) {
							c.push(hm);
						}
					}
					return [a, b, c];
				}
				AEI = qc(A, E, I);
				BFJ = qc(B, F, J);
				CGK = qc(C, G, K);
				DHL = qc(D, H, L);
			}
			
			var ABCD = collectFD(A, B, C, D);
			var EFGH = collectFD(E, F, G, H);
			var IJKL = collectFD(I, J, K, L);
			
			var EBCD = collectFD(AEI[1], B, C, D);
			var AFCD = collectFD(A, BFJ[1], C, D);
			var ABGD = collectFD(A, B, CGK[1], D);
			var ABCH = collectFD(A, B, C, DHL[1]);
			
			var EJKL = collectFD(AEI[1], J, K, L);
			var IFKL = collectFD(I, BFJ[1], K, L);
			var IJGL = collectFD(I, J, CGK[1], L);
			var IJKH = collectFD(I, J, K, DHL[1]);
			
			var AFGH = collectFD(AEI[0], F, G, H);
			var EBGH = collectFD(E, BFJ[0], G, H);
			var EFCH = collectFD(E, F, CGK[0], H);
			var EFGD = collectFD(E, F, G, DHL[0]);
			
			var AJKL = collectFD(AEI[0], J, K, L);
			var IBKL = collectFD(I, BFJ[0], K, L);
			var IJCL = collectFD(I, J, CGK[0], L);
			var IJKD = collectFD(I, J, K, DHL[0]);
			
			var IBCD = collectFD(AEI[2], B, C, D);
			var AJCD = collectFD(A, BFJ[2], C, D);
			var ABKD = collectFD(A, B, CGK[2], D);
			var ABCL = collectFD(A, B, C, DHL[2]);
			
			var IFGH = collectFD(AEI[2], F, G, H);
			var EJGH = collectFD(E, BFJ[2], G, H);
			var EFKH = collectFD(E, F, CGK[2], H);
			var EFGL = collectFD(E, F, G, DHL[2]);
				
		}
		
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
		
		if(AEI.length == 2) {
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
		} else {
			var trs = [
				"ABCD",
				"EFGH",
				"IJKL",
				"EBCD",
				"AFCD",
				"ABGD",
				"ABCH",
				"EJKL",
				"IFKL",
				"IJGL",
				"IJKH",
				"AFGH",
				"EBGH",
				"EFCH",
				"EFGD",
				"AJKL",
				"IBKL",
				"IJCL",
				"IJKD",
				"IBCD",
				"AJCD",
				"ABKD",
				"ABCL",
				"IFGH",
				"EJGH",
				"EFKH",
				"EFGL"
			];
		}
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
		allHms.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		var lastCount = 0;
		for(var i in allHms) {
			var hm = allHms[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					allHmsHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			allHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			allHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			allHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			allHmsHtml += "</div>";
		}
		
		var totalHmsHtml = "";
		totalHms.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? 0 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		lastCount = 0;
		for(var i in totalHms) {
			var hm = totalHms[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					totalHmsHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
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


