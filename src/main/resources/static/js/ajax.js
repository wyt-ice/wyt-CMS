function add() {
    var request = {
        'id': 1,
        'name': 'zs'
    };
    //ajax请求
    $.ajax({
        // 请求方式 请求方法
        type: "post",
        // 请求地址
        url: "/hello",
        data: request,
        // 请求成功  回调函数
        success: function (response) {
            alert(response);
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert(e.responseText);
        }
    });
};

function get() {

    //ajax请求
    $.ajax({
        // 请求方式 请求方法
        type: "get",
        // 请求地址
        url: "/student",

        // 请求成功  回调函数
        success: function (response) {
            // alert(JSON.stringify(response))
            document.body.append("\n" + JSON.stringify(response));
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert(e.responseText);
        }
    });
};


function modify() {
    var request = {
        'id': 1,
        'name': 'zs'
    };
    //ajax请求
    $.ajax({
        // 请求方式 请求方法
        type: "put",
        // 请求地址
        url: "/hello",
        data: request,
        // 请求成功  回调函数
        success: function (response) {
            alert(response);
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert(e.responseText);
        }
    });
};


function del() {
    var request = {
        'id': 1,
        'name': 'zs'
    };
    //ajax请求
    $.ajax({
        // 请求方式 请求方法
        type: "delete",
        // 请求地址
        url: "/hello",
        data: request,
        // 请求成功  回调函数
        success: function (response) {
            alert(response);
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert(e.responseText);
        }
    });
};


