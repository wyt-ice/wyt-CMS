// $("#hello").on("click",function (){
//     $("#hello").href("/login.html");
// });


function isLogin() {
    $.ajax({
        // 请求方式
        type: "get",
        // 请求地址
        url: "/isLogin",
        // 请求成功
        success: function (data) {
            // console.log(data.code)
            if (data.code == 200) {
                alert("您已经登录")
            }
            if (data.code == 403) {
                alert("请前往登录")
                window.location.href = "login.html"
            }
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            alert("请联系管理员")
        }
    });
}

function logout() {
    $.ajax({
        // 请求方式
        type: "get",
        // 请求地址
        url: "/logout",
        // 请求成功
        success: function (data) {
            console.log(data.code)
            if (data.code == 200) {
                alert("注销成功")
                window.location.href = "index.html"
            }
            if (data.code == 403) {
                alert("亲，您还没登录")
            }

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {

            alert("请联系管理员")


        }
    });
}