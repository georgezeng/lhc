$(document).ready(function() {
	$("#menuBtn").click(function() {
		if($(this).text() == "隐藏菜单") {
			$("#menus").hide();
			$("#page-wrapper").css("marginLeft", "0");
			$(this).text("显示菜单");
		} else {
			$("#menus").show();
			$("#page-wrapper").css("marginLeft", "250px");
			$(this).text("隐藏菜单");
		}
	});
});
