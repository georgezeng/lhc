$(document).ready(function() {
	createYZlist("/mvc/yz/listBS9QYZ", ["red1", "red2", "red3", "blue1", "blue2", "blue3", "green1", "green2", "green3"], function(nTd, item, index){
		var color = null;
		if(index < 5) { 
			color = "red";
		} else if(index < 8) {
			color = "blue";
		} else {
			color = "green";
		}
		$(nTd).css("color", "white").css("backgroundColor", color);
	}, null, "号码/顺概率", function(nTd, item, index) {
		$(nTd).text(item.list[index-2].join(", "));
	});
});


