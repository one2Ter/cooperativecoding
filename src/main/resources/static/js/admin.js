
function resize(){
    // var width = document.body.clientWidth;
    //var height = document.body.clientHeight;
    $("#box").height(document.body.clientHeight-40);
}
window.onresize = function resizeBody() {
    resize();
};

$(document).ready(function() {
    resize();
});


function onMenuClick(e,page){
    var menus = $("#menus li");
    for(var i=0;i<menus.length;i++){
        menus[i].style.borderBottom = "none";
    }
    e.style.borderBottom = "3px #2ecc71 solid";
    $("#panel_right").attr('src',"/admin/"+page+"/index.html");
}