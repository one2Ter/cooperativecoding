function resize(){
    var width = document.body.clientWidth;
    var height = document.body.clientHeight;
    $("#box").height(height-40);
}
window.onresize = function resizeBody() {
    resize();
};

$(document).ready(function() {
    resize();
});