$(document).ready(function() {
	var query;
	var datatable = createDataTable({
		id : "dataTable",
		url : "/mvc/kj/list",
		searchPlaceholder : "年份/日期/特码生肖",
		data : function(queryInfo, infoSettings) {
			query = queryInfo;
			queryInfo.object = infoSettings.search.value;
		},
		lengthMenu : [ 10, 20, 50, 100, 200, 300 ],
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
	
	$("#fetchBtn").click(function() {
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
	
	$("#downloadBtn").click(function() {
		$("#searchKey").val(query.object);
		$("#download").submit();
	});
	
	$("#clearBtn").click(function() {
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
