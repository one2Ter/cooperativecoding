var stompClient = null;
var connected = false;
var content = $("#chatcontent");
var cursor = null;
var username = null;
var maintainer = true;
function connect() {
    username = localStorage.getItem("username");
    stompClient = Stomp.over(new SockJS('/message'));
    stompClient.connect({}, function (frame) {
        connected = true;
        stompClient.subscribe('/clients/message', function (message) {
            var response = JSON.parse(message.body);
            switch (response.id){
                case 0:
                    receiveMessage("chat:"+JSON.parse(message.body).content+"from:"+JSON.parse(message.body).from,JSON.parse(message.body).from);
                    break;
                case -1:
                    var data = JSON.parse(message.body).content;
                    var lines = "";
                    for(var i=0;i<data.length;i++){
                        lines += data[i];
                    }
                    editor.getDoc().setValue(lines);
                    //重置光标位置
                    editor.doc.setCursor(cursor);
                    break;
                case 1:
                    var console = $("#console");
                    console.val(JSON.parse(message.body).content);
                    break;
            }
        });
    });
}

function sendMessage(){
    var input =$("#inputBox");
    var message = input.val();
    if(!connected){
        connect();
    }
    stompClient.send("/server/message", {}, JSON.stringify({'id':0,'content': message}));
    input.val("");
}

function receiveMessage(msg,from){
    if(from === username){
        content.append("<div class=\"customer_lists clearfix\"><div class=\"header_img jimi3\" style=\"background: url(../img/mine.jpg) no-repeat center;\"><div class=\"header_img_hover\"></div></div><div class=\"bkbubble left\"><p>"+msg+"</p></div></div>");
    }else{
        content.append("<div class=\"jimi_lists clearfix\"><div class=\"header_img jimi3 fl\"></div><div class=\"bkbubble right\"><p>"+msg+"</p></div></div>");
    }
    content.scrollTop(content[0].scrollHeight);
}
var editor = CodeMirror.fromTextArea(document.getElementById("codemirror"), {
    lineNumbers: true
});

//按键绑定
var map = {
    "Ctrl-S": function(cm){update();}
};
editor.addKeyMap(map);

var mac = CodeMirror.keyMap.default == CodeMirror.keyMap.macDefault;
CodeMirror.keyMap.default[(mac ? "Cmd" : "Ctrl") + "-Space"] = "autocomplete";


$.post("/file",function(data){
    editor.getDoc().setValue(mtoString(data));
});

function mtoString(data){
    var lines = "";
    for(var i=0;i<data.length-1;i++){
        lines += data[i]+"\n";
    }
    lines +=data[i];
    return lines;
}

function update(){
    var content = editor.getValue();
    stompClient.send("/server/message",{},JSON.stringify({'id':-1,'content':editor.getValue()}));
}

function run(){
    stompClient.send("/server/message",{},JSON.stringify({'id':1,'content':editor.getValue()}));
}

function resize(){
    var width = document.body.clientWidth;
    var height = document.body.clientHeight;
    $("body").height(height-60);
    editor.setSize(width*0.7,height-194);
    content.height(height-120);
}

$(document).ready(function(){
    connect();
    resize();
    content.scrollTop(content[0].scrollHeight);
});

window.onresize = function resizeBody(){
    resize();
};

//阻止内容相同时触发的change事件，防止死循环
editor.on('beforeChange',function (cm,obj) {
    cursor = cm.doc.getCursor();
    if(cm.getValue()===mtoString(obj.text)){
        obj.cancel();
    }
});

//监听编辑器代码变动，同步到所有客户端
editor.on('change',function (cm) {
    if(connected && maintainer){
        update();
    }
});

//切换代码高亮模式
CodeMirror.modeURL = "js/codemirror/mode/%N/%N.js";

function change(mime,mode,selected) {
    editor.setOption("mode", mime);
    CodeMirror.autoLoadMode(editor, mode);

    var modes = document.getElementsByClassName("code_mode_selected");
    for (var i = 0; i < modes.length; i++) {
        modes[i].className = "code_mode";
    }
    selected.className = "code_mode_selected";
}

function tabNew(){
    $("#tab_new").before("<span class='tabs' onclick='tabClick(this)'><i class='fa fa-file-code-o' aria-hidden='true'></i>head.h</span>");
}

function tabClick(e) {
    var tabs = document.getElementsByClassName("tabs_selected");
    for (var i = 0; i < tabs.length; i++) {
        tabs[i].className = "tabs";
    }

    e.className = "tabs_selected";
}