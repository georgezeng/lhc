$(document).ready(function() {
	createYZlist("/mvc/yz/listWXDSYZ", ["jinOdd", "jinEven", "muOdd", "muEven", "shuiOdd", "shuiEven", "huoOdd", "huoEven", "tuOdd", "tuEven"], null, null, "号码/顺概率", function(nTd, item, index) {
		$(nTd).text(item.list[index-2].join(", "));
	});
});


