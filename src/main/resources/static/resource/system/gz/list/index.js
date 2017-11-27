$(document).ready(function() {
	var createTables = function() {
		for(var i = 1; i < categories.length; i++) {
			var item = categories[i];
			var table = $("<table></table>").addClass("table table-striped table-bordered table-hover").appendTo($("#tables"));
			table.append("<thead><tr><th style='width: 100px;'>"+item.text+"</th><th>号码</th></tr></thead>");
			var tbody = $("<tbody></tbody>").appendTo(table);
			for(var j = 0; j < item.subs.length; j++) {
				tbody.append("<tr><td>"+item.subs[j].text+"</td><td>"+item.subs[j].value+"</td></tr>");
			}
		}
	};
	var createTablesFunc = function() {
		if(init) {
			createTables();
		} else {
			setTimeout(createTablesFunc, 100);
		}
	}
	createTablesFunc();
});

