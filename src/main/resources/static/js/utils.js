/**
 * 此js目前未被使用 里面有原生ajax和jquery封装的ajax的用法，若有需要请关注！
 */
/**
 * 原生ajax
 */
function ajax() {
    var xhr;
    if (typeof (XMLHttpRequest) == "undefined") {
        xhr = ActiveXObject("Micrpsoft.XMLHTTP");
    } else {
        xhr = new XMLHttpRequest();
    }
    //2、设置请求方式和请求地址
    xhr.open("get", "../Customer", true);// 参数1：请求方式（get，post）；参数2：请求的相对路径；参数3：是否为异步
    // 4、监听状态变化，注册回调函数
    xhr.onreadystatechange = function () {
        // readyState属性有5个状态值
        // 0：表示 new 完 xhr
        // 1：表示open完xhr
        // 2：表示send完xhr
        // 3：表示xhr正在接受服务器的数据
        // 4：表示xhr接受完服务器数据
        if (xhr.readyState == 4 && (xhr.status >= 200 || xhr.status == 304)) {
            handleData(xhr.responseText);
        }
    }

    // 3、发送请求
    xhr.send();
}


function ajax_query() {
    $.ajax({
        // 请求方式
        type: "GET",
        /**
         * 请求的媒体类型 1、 服务端需要返回一段普通文本给客户端，Content-Type="text/plain" 2
         * 、服务端需要返回一段HTML代码给客户端 ，Content-Type="text/html" 3 、服务端需要返回一段XML代码给客户端
         * ，Content-Type="text/xml" 4 、服务端需要返回一段javascript代码给客户端 5
         * 、服务端需要返回一段json串给客户端
         */
        contentType: "text/html;charset=UTF-8",
        // 请求地址
        url: "../Customer",
        // 数据，json字符串
        // data: '',
        // 请求成功
        success: function (result) {
            handleData(result);
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
        }
    });
}

function handleData(msg) {
    dom = '<table border="1"><tr><td>客户ID</td></td><td>客户名称</td>'
    dom += '<td>客户工作</td><td>客户电话</td><td>操作</td></tr>'

    var a = msg.substr(1, msg.length - 1).split(",");
    console.log(a);
    for (var i = 0; i < a.length; i++) {
        customer = a[i].split("-");
        dom += '<tr>';
        // 循环加载第2+行
        for (n = 0; n < customer.length; n++) {
            dom += '<td>' + customer[n] + '</td>';

        }
        dom += '<td><button>查看</button><button>修改</button><button>删除</button></td></tr>';

    }
    dom += '</table>';

    // js和html交互的方式
    var table = document.getElementById("customerList");
    table.innerHTML = dom;

}
