var team_id=0;
var project_id=0;
var p1=1;
var p2=1;
var p3=1;
var p4=1;
function resize() {
    var width = document.body.clientWidth;
    var height = document.body.clientHeight;

    if(width<1200){
        $(".table").css("width","100%");
        $("#table_1").css("margin","12px 2% 12px 4%");
        $("#table_2").css("margin","12px 2% 12px 4%");
        $("#table_3").css("margin","12px 2% 12px 4%");
        $("#table_4").css("margin","12px 2% 12px 4%");
    }else{
        $(".table").css("width","50%");
        $("#table_1").css("margin","24px 2% 12px 4%");
        $("#table_2").css("margin","24px 4% 12px 2%");
        $("#table_3").css("margin","12px 2% 24px 4%");
        $("#table_4").css("margin","12px 4% 24px 2%");
    }

}

function listen(){
    $("tbody tr").click(function () {
        if ($(this).attr("class")!=="page") {
            $(this).siblings().css("background-color","transparent");
            $(this).css("background-color","lavender");
        }
    });

    $("tr").dblclick(function () {

    });
}

$(document).ready(function() {
    resize();
    load();
});

window.onresize = function resizeBody() {
    resize();
};

function page(m,n) {
    switch (m){
        case 1:
            break;
        case 2:
            break;
        case 3:
            p3 = n;
            load_user(team_id);
            break;
        case 4:
            break;
    }
}

function load() {
    load_team();
    load_project(team_id);
    load_user(team_id);
    load_code(project_id);
}

function load_team() {
    $.get("/teams",function (data) {
        var n = Math.ceil(data.length/8);
        for(var i=1;i<n+1;i++){
            $("#li_1").before("<li onclick='page(1,"+i+")' class=\"page-item\"><a class=\"page-link\" href=\"#\">"+i+"</a></li>");
        }
        for (var i=8*(p1-1);i<8*p1;i++) {
            if(data[i]){
                $("#tr_1").before("<tr onclick=\"load_project_and_user("+data[i].team_id+")\"><td><input type='text' value='"+data[i].team_id+"'/><td><input type='text' value='"+data[i].team_name+"'/></td><td>0</td><td>0</td><td style=\"width: 80px;margin-left: 5px\"><i class=\"fas fa-pencil-alt\"></i><i class=\"far fa-trash-alt\"></i></td></tr>");
            }else{
                $("#tr_1").before("<tr><td></td><td></td><td></td><td></td><td></td></tr>");
            }

        }
        listen();
    });
}

function load_project(tid) {
    $(".project_load").remove();
    $.post("/projects",{"team_id":tid},function (data) {
        var n = Math.ceil(data.length/8);
        for(var i=1;i<n+1;i++){
            $("#li_2").before("<li onclick='page(2,"+i+")' class=\"page-item project_load\"><a class=\"page-link\" href=\"#\">"+i+"</a></li>");
        }
        for (var i=8*(p2-1);i<8*p2;i++) {
            if(data[i]){
                $("#tr_2").before("<tr onclick='load_code("+data[i].project_id+")' class='project_load'><td><input type='text' value='"+data[i].project_id+"'/><td><input type='text' value='"+data[i].project_name+"'/></td><td>Accord</td><td style=\"width: 80px;margin-left: 5px\"><i class=\"fas fa-pencil-alt\"></i><i class=\"far fa-trash-alt\"></i></td></tr>");
            }else{
                $("#tr_2").before("<tr class='project_load'><td></td><td></td><td></td><td></td></tr>");
            }
        }
        listen();
    });
}

function load_user(tid) {

    $(".user_load").remove();
    $.post("/users",{"team_id":tid},function (data) {
        var n = Math.ceil(data.length/8);
        for(var i=1;i<n+1;i++){
            $("#li_3").before("<li onclick='page(3,"+i+")' class=\"page-item user_load\"><a class=\"page-link\" href=\"#\">"+i+"</a></li>");
        }

        for (var i=8*(p3-1);i<8*p3;i++) {
            if(data[i]){
                $("#tr_3").before("<tr class='user_load'><td><input type='text' value='"+data[i].username+"'/></td><td><input type='text' value='"+data[i].name+"'/></td><td><input type='text' value='"+data[i].team.team_name+"'/></td><td><input type='text' value='"+data[i].role+"'/></td><td>Accord</td><td style=\"width: 80px;margin-left: 5px\"><i class=\"fas fa-pencil-alt\"></i><i class=\"far fa-trash-alt\"></i></td></tr>");
            }else{
                $("#tr_3").before("<tr class='user_load'><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            }
        }
        listen();
    });
}

function load_project_and_user(tid) {
    // $(this).siblings().css("background-color","transparent");
    // $(this).css("background-color","lavender");
    load_project(tid);
    load_user(tid);

}

function load_code(pid) {
    $(".code_load").remove();
    $.get("/codes",{"project_id":pid},function (data) {
        var n = Math.ceil(data.length/8);
        for(var i=1;i<n+1;i++){
            $("#li_4").before("<li onclick='page(4,"+i+")' class=\"page-item code_load\"><a class=\"page-link\" href=\"#\">"+i+"</a></li>");
        }
        for (var i=8*(p4-1);i<8*p4;i++) {
            if(data[i]){
                $("#tr_4").before("<tr class='code_load'><td><input type='text' value='"+data[i].code_id+"'/></td><td><input type='text' value='"+data[i].code_title+"'/></td><td><input type='text' value='"+data[i].executable+"'/></td><td><input type='text' value='"+data[i].mode+"'/></td><td><input type='text' value='"+data[i].project.project_name+"'/></td><td style=\"width: 80px;margin-left: 5px\"><i class=\"fas fa-pencil-alt\"></i><i class=\"far fa-trash-alt\"></i></td></tr>");
            }else{
                $("#tr_4").before("<tr class='code_load'><td></td><td></td><td></td><td></td><td></td><td></td></tr>");
            }
        }
        listen();
    });

}