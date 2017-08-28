$(document).ready(function() {
	createYZlist("/mvc/yz/listBSYZ", ["redOdd", "redEven", "blueOdd", "blueEven", "greenOdd", "greenEven"], null, null, "号码/顺概率", function(nTd, item, index) {
		$(nTd).text(item.list[index-2].join(", "));
	});
});


