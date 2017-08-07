$(document).ready(function() {
	var query;
	init = true;
	var datatable = createDataTable({
		id : "dataTable",
		url : "/mvc/kj/list",
		searchPlaceholder : "年份/日期/特码生肖",
		data : function(queryInfo, infoSettings) {
			query = queryInfo;
			queryInfo.object = infoSettings.search.value;
		},
		success : function(result) {
			for (var i = 0; i < result.list.length; i++) {
				var item = result.list[i];
				item.nums = [];
				for(var j = 1; j < 7; j++) {
					item.nums.push({
						num: item["num"+j],
						sx: item["num"+j+"Sx"]
					});
				}
				item.special = {
					sx: item.specialSx,
					num: item.specialNum
				};
			}
		},
		order: [[1, 'desc']],
		columns : [ {
			name : "year",
			data : "year",
			sortable: false
		}, {
			name : "date",
			data : "date"
		}, {
			name : "phase",
			data : "phase"
		}, {
			name : "nums",
			sortable: false
		}, {
			name : "special",
			sortable: false
		} ],
		columnDefs: [
			{
				targets: 3,
				data: function ( item, type, val, meta ) {
					var content = [];
					for(var i = 0; i < item.nums.length; i++) {
						content.push(renderNumberAndSX(item.nums[i], 'pm'));
					}
					return content.join("");
				}
			},
			{
				targets: 4,
				data: function ( item, type, val, meta ) {
					return renderNumberAndSX(item.special, 'tm');
				}
			}
		]
	});
	
	$("#fetchBtn").unbind().click(function() {
		var year = checkYear();
		if(!year) {
			return;
		}
		openLoading();
		post({
			url: '/mvc/kj/sync/' + year,
			success: function() {
				alert("抓取成功");
				datatable.ajax.reload();
				closeLoading();
			},
			systemError: function(msg) {
				alert(msg);
				closeLoading();
			}
		});
	});
	
	$("#downloadBtn").unbind().click(function() {
		$("#searchKey").val(query.object);
		$("#download").submit();
	});
	
	$("#clearBtn").unbind().click(function() {
		var year = checkYear();
		if(!year) {
			return;
		}
		openLoading();
		post({
			url: '/mvc/kj/delete/' + year,
			success: function() {
				alert("清除成功");
				datatable.ajax.reload();
				closeLoading();
			},
			systemError: function(msg) {
				alert(msg);
				closeLoading();
			}
		});
	});
	
	$("#date").datepicker({
		changeMonth: true,
		changeYear: true,
		monthNames: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'],
		monthNamesShort: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
		dateFormat: 'yy-mm-dd'
	});
	
	$("#editPanel").dialog({
		autoOpen: false,
		modal: true,
		width: 600,
		buttons: {
			"保存": function() {
				var errors = 0;
				$("input[name='editInfo']").each(function() {
					if($(this).val().trim() == "") {
						errors++;
						alert($(this).attr("txt")+"不能为空");
						return false;
					}
				});
				if(errors) {
					return;
				}
				var dialog = $(this);
				openLoading();
				post({
					url: '/mvc/kj/save/',
					data: (function() {
						var obj = {};
						$("input[name='editInfo']").add($("select[name='editInfo']")).each(function() {
							obj[$(this).attr("id")] = $(this).val();
						});
						return obj;
					})(),
					success: function() {
						closeLoading();
						alert("保存成功");
						datatable.ajax.reload();
						dialog.dialog("close");
					},
					systemError: function(msg) {
						alert(msg);
						closeLoading();
					}
				});
			}
		}
	});
	
	$("#editBtn").unbind().click(function() {
		var dialog = $("#editPanel").dialog("open");
		$(".ui-dialog-titlebar-close").show();
	});
	
	
	
});

function renderNumberAndSX(item, cls) {
	var content = [];
	content.push("<div class='p'>");
	content.push("	<div class='" + cls + "'>" + item.num + "</div>");
	content.push("	<div class='sx'>" + item.sx.text + "</div>");
	content.push("</div>");
	return content.join("");
}

function checkYear() {
	var year = $("#year").val().trim();
	if(year == '') {
		alert("请输入年份");
		return false;
	}
	return year;
}
