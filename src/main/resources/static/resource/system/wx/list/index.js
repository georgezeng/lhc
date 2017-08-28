$(document).ready(function() {
	createYZlist("/mvc/yz/listWXYZ", ["jin", "mu", "shui", "huo", "tu"], null, null, "号码/顺概率", function(nTd, item, index) {
		$(nTd).text(item.list[index-2].join(", "));
	});
});


