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
	
	var lastColor;
	var lastResult = 0;
	$("#pickupBtn").click(function() {
		filter();
	});
	
	function filter() {
		var tbody = $("#conditionTable").find("tbody");
		var allCFHms = [];
		
		function collectSZ(index) {
			var sz = [];
			tbody.find("tr[name='sz"+index+"']").each(function() {
				var hms = $(this).find("td[name='hm']").attr("hm").split(/,\s*/);
				for(var i in hms) {
					var hm = hms[i];
					if(!isInArr(sz, hm)) {
						sz.push(hm);
					}
					var item = null;
					for(var j in allCFHms) {
						item = allCFHms[j];
						if(item && item.num == hm) {
							item.count++;
							break;
						} else {
							item = null;
						}
					}
					if(!item) {
						allCFHms.push({num:hm, count:1});
					}
				}
			});
			return sz;
		}
		
		var A = collectSZ('A');
		var B = collectSZ('B');
		var C = collectSZ('C');
		var E = collectSZ('E');
		var F = collectSZ('F');
		var G = collectSZ('G');
		
		
		function collectFD(fd1, fd2, fd3) {
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
			return sz;
		}
		
		function collectFZFD(fd1, fd2, fd3) {
			var sz = collectFD(fd1, fd2, fd3);
			var reverse = [];
			for(var i = 1; i < 50; i++) {
				if(!isInArr(sz, i)) {
					reverse.push(i);
				}
			}
			return reverse;
		}
		
		function collectFDForCF(fd1, fd2) {
			var temp = [];
			function collect(fd) {
				for(var i in fd) {
					var hm = fd[i];
					var item = null;
					for(var j in temp) {
						item = temp[j];
						if(item.num == hm) {
							item.count++;
							break;
						} else {
							item = null;
						}
					}
					if(!item) {
						temp.push({num:hm, count:1});
					}
				}
			}
			collect(fd1);
			collect(fd2);
			var sz = [];
			for(var i in temp) {
				var item = temp[i];
				if(item.count > 1) {
					sz.push(item);
				}
			}
			return sz;
		}
		
		var AE = collectFDForCF(A, E);
		var BF = collectFDForCF(B, F);
		var CG = collectFDForCF(C, G);
		
		var ABC = collectFD(A, B, C);
		var ABCFZ = collectFZFD(A, B, C);
		var EFG = collectFD(E, F, G);
		var EFGFZ = collectFZFD(E, F, G);
		var AFG = collectFD(A, F, G);
		var AFGFZ = collectFZFD(A, F, G);
		var EBC = collectFD(E, B, C);
		var EBCFZ = collectFZFD(E, B, C);
		var AFC = collectFD(A, F, C);
		var AFCFZ = collectFZFD(A, F, C);
		var EBG = collectFD(E, B, G);
		var EBGFZ = collectFZFD(E, B, G);
		var ABG = collectFD(A, B, G);
		var ABGFZ = collectFZFD(A, B, G);
		var EFC = collectFD(E, F, C);
		var EFCFZ = collectFZFD(E, F, C);
		
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
			if(fd.indexOf("49-") > -1) {
				fdHms = eval(fd.replace("49-", "")+"FZ");
			}
			$("<td></td>").appendTo(
					$("<tr><td>"+fd+"</td></tr>")
						.appendTo(datatable)
						.css("backgroundColor", lastColor)
					.css("border", "1px solid #ddd"))
					.text(fdHms.join(", "));
			
			if(fd.indexOf("49-") == -1) {
				return;
			}
			
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
			}
		}
		
		var trs = [
			"ABC",
			"49-ABC",
			"EFG",
			"49-EFG",
			"AFG",
			"49-AFG",
			"EBC",
			"49-EBC",
			"AFC",
			"49-AFC",
			"EBG",
			"49-EBG",
			"ABG",
			"49-ABG",
			"EFC",
			"49-EFC"
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
		
		var aeHtml = "";
		AE.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		var lastCount = 0;
		for(var i in AE) {
			var hm = AE[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					aeHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			aeHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			aeHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			aeHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			aeHtml += "</div>";
		}
		$("<td></td>").appendTo(
			$("<tr><td>AE</td></tr>")
				.appendTo(datatable)
				.css("backgroundColor", "#f9f9f9")
			.css("border", "1px solid #ddd"))
			.html(aeHtml);
		
		var bfHtml = "";
		BF.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		var lastCount = 0;
		for(var i in BF) {
			var hm = BF[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					aeHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			bfHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			bfHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			bfHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			bfHtml += "</div>";
		}
		$("<td></td>").appendTo(
				$("<tr><td>BF</td></tr>")
				.appendTo(datatable)
				.css("backgroundColor", "#f9f9f9")
				.css("border", "1px solid #ddd"))
				.html(bfHtml);
		
		var cgHtml = "";
		CG.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		var lastCount = 0;
		for(var i in CG) {
			var hm = CG[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					aeHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			cgHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			cgHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			cgHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			cgHtml += "</div>";
		}
		$("<td></td>").appendTo(
				$("<tr><td>CG</td></tr>")
				.appendTo(datatable)
				.css("backgroundColor", "#f9f9f9")
				.css("border", "1px solid #ddd"))
				.html(cgHtml);
		
		var allCFHmsHtml = "";
		allCFHms.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		lastCount = 0;
		for(var i in allCFHms) {
			var hm = allCFHms[i];
			if(lastCount != hm.count) {
				if(lastCount > 0) {
					allCFHmsHtml += "<div class='clearfix'></div>";
				}
				lastCount = hm.count;
			} 
			allCFHmsHtml += "<div style='float: left; border: 1px solid black; margin-right: 10px; margin-bottom: 10px;'>";
			allCFHmsHtml += "<div style='padding: 5px; color: white; background-color: red;'>" + (hm.num > 9 ? hm.num : "&nbsp;" + hm.num + "&nbsp;") + "</div>";
			allCFHmsHtml += "<div style='padding: 5px;'>" + (hm.count > 9 ? hm.count : "&nbsp;" + hm.count + "&nbsp;") + "</div>";
			allCFHmsHtml += "</div>";
		}
		$("<td></td>").appendTo(
				$("<tr name='totalPart'><td>选重</td></tr>")
				.appendTo(datatable)
				.css("backgroundColor", "#f9f9f9")
				.css("border", "1px solid #ddd"))
				.html(allCFHmsHtml);
		
		var allHmsHtml = "";
		allHms.sort(function(a, b) {
			return a.count < b.count ? -1 : (a.count == b.count ? (a.num < b.num ? -1 : (a.num == b.num ? 0 : 1)) : 1); 
		});
		lastCount = 0;
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
		
		$("<td></td>").appendTo(
				$("<tr name='totalPart'><td>组内去重</td></tr>")
				.appendTo(datatable)
				.css("backgroundColor", "#f9f9f9")
				.css("border", "1px solid #ddd"))
				.html(allHmsHtml);
	}
	
	$("#downloadBtn").unbind().click(function() {
		$("#download").submit();
	});
	
	$("#clearBtn").click();
});


