if (sessionStorage.getItem("id")) {
    $("#userid_display").html(sessionStorage.getItem("id"));
}

$('.order').click(function () {
    if (sessionStorage.getItem("id") == null) {
        $("#modal-login").modal("show");
        return null;
    }

});
$('.shopcar').click(function () {
    if (sessionStorage.getItem("id") == null) {
        $("#modal-login").modal("show");
        return null;
    }

});
$("#userid_display").click(function () {
    sessionStorage.clear();
    window.location.reload();
});

$("#link_post").click(function () {
    $("#modal-title").html("注册");
    $("#rt").removeClass("hidden")
    $("#link_post").addClass("hidden");
    $("#login").addClass("hidden");
    $("#post").removeClass("hidden");
});
$("#rt").click(function () {
    $("#modal-title").html("登陆");
    $("#link_post").removeClass("hidden");
    $("#login").removeClass("hidden");
    $("#post").addClass("hidden");
    $("#rt").addClass("hidden");
});

$("#login").click(function () {
    login("login");
});

$("#post").click(function () {
    login("");
});

function login(type) {
    var userName = $("#userid");
    if (!userName.val().match(/^[1-9][0-9]{8}$/)) {
        alert("有效长度为6-10数字");
        return null;
    }
    var userPassword = $("#password");
    if (!userPassword.val().match(/^(\w){6,20}$/)) {
        alert("请输入字母或数字且长度为6-10位的密码");
        return null;
    }
    $.getJSON("login", { "id": userName.val(), "password": userPassword.val(), "type": type },
        function (result) {
            var json = eval(result);
            console.log(json);
            var bl = json["bl"];

            if (type == "") {
                if (bl == true) {
                    $("#userid").html(userName.val());
                    sessionStorage.setItem("id", userName.val());
                    window.location.reload();
                } else {
                    alert('账号以被注册');
                }
            }

            if (type == "login") {
                if (bl == true) {
                    $("#userid").html(userName.val());
                    sessionStorage.setItem("id", userName.val());
                    window.location.reload();
                } else {
                    alert('账号或密码输入错误');
                }

            }
        }
    )
}

//sessionStorage本地存储(5M)
//sessionStorage.setItem(key,value)存储
//sessionStorage.getItem(key)得到
//sessionStorage.removeItem(key)删除数据
//sessionStorage.clear()删除全部数据