$(document).ready(function() {
	
	var cols = [
		"year", "phase", "specialNum",
		"sxTotal", "sxd1",
		"sxlrTotal", "sxlrPos", "sxlrd1",
		"dsTotal", "dsd1", 
		"swTotal", "swPos", "swd1",
		"mwTotal", "mwd1", 
		"lhTotal", "lhd1", 
		"bsTotal", "bsd3", "bsd4", "bsd5", 
		"zsTotal", "zsd1", 
		"wxTotal", "wxd3", "wxd4", 
		"wxdsTotal", "wxdsd1", 
		"pdTotal", "pdd1", 
		"fdTotal", "fdd1", 
		"qqTotal", "qqPos", "qqd1",
		"qiwTotal", "qiwPos", "qiwd1",
		"twelveTotal", "twelvePos", "twelved1",
		"slqTotal", "slqd1" 
	];
	
	var columns = [];
	for(var i in cols) {
		var col = cols[i];
		columns.push({
			name : col,
			data : col,
			sortable: false
		});
	}
	var columnDefs = [];
	for(var i = 3; i < 43; i++) {
		(function(index) {
			columnDefs.push({
				aTargets: [index],
				fnCreatedCell: function(nTd, sData, item, iRow, iCol) {
					var value = item[cols[index]]
					$(nTd).text(value);
				}
			});
		})(i);
	}
	datatables.push(createDataTable({
		id : "dataTable",
		url : "/mvc/yz/listAllD1?mode=0",
		bFilter: false,
		data : function(queryInfo, infoSettings) {
			queryInfo.object = {};
			queryInfo.object.year = parseInt($("#years").val());
			queryInfo.object.phase = parseInt($("#phases").val());
		},
		columns : columns,
		aoColumnDefs: columnDefs
	}));
	
});


