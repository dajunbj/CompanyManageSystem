/**
 * 画面初期化
 */
function transitionTo(linkIndex) {
	$("#transitionTo").val(linkIndex);
	$("#transitionTo").trigger("click");
}


/**
 * 共通画面レアいうとを初期化する
 */
function commonLoad() {
	//共通ヘッダを取込する
	$(".publicHeader").load('/html/layout/header.html');
	//メニュー画面を取込する
	$(".left").load('/html/layout/left.html');
	//footerを取込する
	$(".footer").load('/html/layout/footer.html');
}