$(document).ready(function() {
	createYZlist("/mvc/yz/listZX7YZ", ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10"], null, null, "号码", function(nTd, item, index) {
		return item.nums[index-2].join(", ");
	});
});


