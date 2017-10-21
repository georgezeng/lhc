$(document).ready(function() {
	createYZlist("/mvc/yz/listTM12FDYZ", ["w1", "w2", "w3", "w4", "w5", "w6", "w7", "w8", "w9", "w10", "w11", "w12"], null, null, "号码", function(nTd, item, index) {
		var text = [];
		var num;
		var range = 4;
		var length = 12;
		for(j in item.list) {
			var pos = parseInt(j) + 1;
			if(pos % range == 0) {
				num = parseInt(pos / range);
			} else {
				num = parseInt(pos / range) + 1;
			}
			if(num > length) {
				num = length;
			}
			if(num == index - 1) {
				text.push(item.list[j].num);
			}
		}
		return text.join(", ");
	});
});


