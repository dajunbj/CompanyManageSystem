

function clickRead(obj) {
	//参照ボタンを押下
	//	$("#clickRead").val(linkIndex);
	$("#read").trigger("click");
}
function clickUpdate(obj) {
	//更新ボタンを押下
	//	$("#update").val(linkIndex);
	$("#update").trigger("click");
}
function clickDelete(obj) {
	var vl = $("#"+obj).val();
	//削除ボタンを押下
	$("#delete").val(vl);
	$("#delete").trigger("click");
}

$(document).ready(function() {
	commonLoad();
});
