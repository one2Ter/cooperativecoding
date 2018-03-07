var stompClient = null;
var connected = false;
var cursor = null;
var username = null;
var code_id = null;
var maintainer = false;
var project_id = null;
var content = $("#chatcontent");
function connect() {
    stompClient = Stomp.over(new SockJS('/message'));
    stompClient.connect({}, function(frame) {
        connected = true;
        stompClient.subscribe('/clients/message', function(message) {
            var response = JSON.parse(message.body);
            var text = response.content;
            var from = response.from;
            switch (response.channel) {
                case 0:
                    handleMessage(text, from);
                    break;
                case -1:
                    if (from !== username) {
                        cursor = editor.doc.getCursor();
                        editor.getDoc().setValue(text);
                        editor.doc.setCursor(cursor);
                    }
                    break;
                case 1:
                    editor.setOption("readOnly","false");
                    $("#preloader_6").hide();
                    $("#console_text").show();
                    $("#btn_run").css({"cursor":"pointer","color":"black"});
                    $("#btn_run").removeAttr("disabled");
                    $("#console_text").val(text);
                    break;
            }
        });
    });
}

function sendMessage() {
    if (connected) {
        var input = $("#inputBox");
        stompClient.send("/server/message", {}, JSON.stringify({
            'channel': 0,
            'content': input.val()
        }));
        input.val("");
    }
}

function handleMessage(msg, from) {

    if (from === username) {
        content.append("<div class=\"customer_lists clearfix\"><div class=\"header_img jimi3\" style=\"background: url(../img/mine.jpg) no-repeat center;\"><div class=\"header_img_hover\"></div></div><div class=\"bkbubble left\"><p>" + msg + "</p></div></div>");
    } else {
        content.append("<div class=\"jimi_lists clearfix\"><div class=\"header_img jimi3 fl\"></div><div class=\"bkbubble right\"><p>" + msg + "</p></div></div>");
    }
    content.scrollTop(content[0].scrollHeight);
}
var editor = CodeMirror.fromTextArea(document.getElementById("codemirror"), {
    lineNumbers: true
});

function tabClick(e, index) {
    //选项卡选中状态UI
    code_id = index;
    var tabs = document.getElementsByClassName("tabs_selected");
    for (var i = 0; i < tabs.length; i++) {
        tabs[i].className = "tabs";
    }
    e.className = "tabs_selected";
    $.post("/code/" + index, function(data) {
        editor.getDoc().setValue(data.content);
    });
}

$.post("/code/all", function(data) {
    for (var i = 0; i < data.length; i++) {
        var title = data[i].code_title;
        code_id = data[i].code_id;

        $("#tab_new").before("<span class='tabs' id='tab_" + code_id + "' onclick='tabClick(this," + code_id + ",)'><i class='far fa-file-code' aria-hidden='true'></i>" + title + "</span>");

        if (data[i].executable) {
            var tabs = document.getElementsByClassName("tabs");
            tabs[tabs.length - 1].className = "tabs_selected";
            editor.getDoc().setValue(data[i].content);
            loadMode(data[i].mode);
        }
    }
});

function loadMode(mode) {
    switch (mode) {
        case "c":
            editor.setOption("mode", "text/x-c++src");
            CodeMirror.autoLoadMode(editor, "clike");
            break;
    }
}

function mtoString(data) {
    var lines = "";
    for (var i = 0; i < data.length - 1; i++) {
        lines += data[i] + "\n";
    }
    lines += data[i];
    return lines;
}


//调用Docker编译运行
function run() {
    var console_text = $("#console_text");
    var btn_run = $("#btn_run");
    var load_mask = $("#preloader_6");

    //编译等待期间控制台样式
    editor.setOption("readOnly","nocursor");

    console_text.hide();
    load_mask.show();
    btn_run.css({"color":"#AABBCC","cursor":"wait"});
    btn_run.attr("disabled", "disabled");
    console_text.text("正在编译请稍候...");

    var input_parameters = document.getElementsByClassName("input-parameters");
    var inputs = "";
    for (var i = 0; i < input_parameters.length - 1; i++) {
        inputs += input_parameters[i].value + "|";
    }
    inputs = inputs + input_parameters[input_parameters.length - 1].value;
    stompClient.send("/server/message", {}, JSON.stringify({
        'channel': 1,
        'content': editor.getValue(),
        'extra': inputs
    }));
}

function resize() {
    var width = document.body.clientWidth;
    var height = document.body.clientHeight;
    $("body").height(height - 60);
    editor.setSize(width * 0.7, height - 192);
    content.height(height - 120);
    content.scrollTop(content[0].scrollHeight);
}

function heartbeat() {
    if (connected) {
        stompClient.send("/server/message", {}, JSON.stringify({
            'channel': 2
        }));
    }
}

$(document).ready(function() {
    connect();
    resize();
    $.get("/user", function(data) {
        username = data.username;
    });
    $.get("/project/current", function(data) {
        project_id = data;
    });
    setInterval(heartbeat, 10000);
    $.get("/project", function(data) {
        if (!data.maintainer) {
            maintainer = false;
        } else {
            $("#maintain_status").html(data.maintainer.name + "(" + data.maintainer.username + ")正在编辑...");
            maintainer = username === data.maintainer.username;
        }
    });
});

window.onresize = function resizeBody() {
    resize();
};

//阻止内容相同时触发的change事件，防止死循环
//再简单点...
editor.on('beforeChange', function(cm, obj) {
    if (obj.origin === "setValue") {
        if (cm.getValue() === mtoString(obj.text)) {
            obj.cancel();
        }
    } else {
        if (maintainer) {
            if (cm.getValue() === mtoString(obj.text)) {
                obj.cancel();
            }
        } else {
            obj.cancel();
            alert("请先取得该文档的编辑权限!");
        }
    }
});

editor.on('change', function(cm) {
    if (connected && maintainer) {
        stompClient.send("/server/message", {}, JSON.stringify({
            'channel': -1,
            'content': editor.getValue(),
            'extra': code_id
        }));
    }
});
//切换代码高亮模式
CodeMirror.modeURL = "js/codemirror/mode/%N/%N.js";

function change(mime, mode, selected) {
    editor.setOption("mode", mime);
    CodeMirror.autoLoadMode(editor, mode);
}

function tabNew() {
    if (maintainer) {
        var code_id;
        var file_name = $("#ip_filename").val();
        if (file_name !== "") {
            $.post("/code/new", {
                'code_title': $("#ip_filename").val()
            }, function(data) {
                code_id = data.code_id;
                $("#tab_new").before("<span class='tabs' onclick='tabClick(this," + code_id + ",)'><i class='fa fa-file-code-o' aria-hidden='true'></i>" + file_name + "</span>");
            });
        }
    } else {
        alert("请先取得该文档的编辑权限!");
    }
}

function m_input_focus(e) {
    var parameters = $("#parameters");
    var children = parameters.children("input");

    for (var i = 0; i < children.length; i++) {
        if (children[i] !== e && children[i].value === "") {
            children[i].remove();
        }
    }
    parameters.append("<input onfocus=\"m_input_focus(this)\" type=\"text\" class=\"form-control input-parameters\" placeholder=\"请输入参数\" />");
}

function loadProject() {
    $.post("/project/all", function(data) {
        var content = null;
        for (var i = 0; i < data.length; i++) {
            var pid = data[i].project_id;
            var project_name = data[i].project_name;
            var online = data[i].online;
            var cname = "fa fa-toggle-off fa-grey";

            if (project_id === pid) {
                cname = "fa fa-toggle-on fa-green";
            }
            content += "<tr onclick='projectSwitch(this," + pid + ")' style='cursor: pointer'><th id='" + pid + "' scope=\"row\"><i style='font-size: 24px;' class='" + cname + "'></i></th><td>" + project_name + "</td><td>" + online + "</td></tr>";
        }
        $("#project_list").html(content);
    });
}

function projectSwitch(e, pid) {
    project_id = parseInt(pid);
    var es = document.getElementById("project_list").getElementsByTagName("i");
    for (var i = 0; i < es.length; i++) {
        es[i].className = "fa fa-toggle-off fa-grey";
    }
    e.getElementsByTagName("i")[0].className = "fa fa-toggle-on fa-green";
}

function switchProject() {
    $.post("/user/project", {
        'project_id': project_id
    }, function(data) {
        window.location.reload();
    });
}

//项目接管、释放

function takeCharge() {
    $.post("/project/take", function(data) {
        window.location.reload();
    });
}

function logout() {
    $.post("/logout", function(data) {
        window.location.reload();
    });
}