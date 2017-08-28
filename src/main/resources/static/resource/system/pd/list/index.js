$(document).ready(function() {
	createYZlist("/mvc/yz/listPDYZ", ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12"], null, null, "号码/顺概率", function(nTd, item, index) {
		$(nTd).text(item.list[index-2].join(", "));
	});
});


