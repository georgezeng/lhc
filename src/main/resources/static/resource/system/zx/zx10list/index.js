$(document).ready(function() {
	createYZlist("/mvc/yz/listZX10YZ", ["w1", "w2", "w3", "w4", "w5", "w6", "w7"], null, null, "号码", function(nTd, item, index) {
		return item.nums[index-2].join(", ");
	});
});


