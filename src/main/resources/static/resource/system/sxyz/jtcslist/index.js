$(document).ready(function() {
	createCSList("/mvc/yz/listSXJTCSYZ", ["shu", "niu", "hu", "tu", "long", "she", "ma", "yang", "hou", "ji", "gou", "zhu"], function(item) {
		return item.currentSx.name.toLowerCase();
	});
});


