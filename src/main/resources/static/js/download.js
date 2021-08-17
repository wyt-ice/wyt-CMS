function orderDownload(){
    var downloadType = 'order';
    $.ajax({
        // 请求方式
        type: "get",
        // 请求地址
        url: "/order/download",
        data:'downloadType='+downloadType,
        // 请求成功
        success: function () {
            // console.log(data.code)
            alert("正在下载！")
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            alert("请联系管理员")
        }
    });
}

function customerDownload(){
    var downloadType = 'customer';
    $.ajax({
        // 请求方式
        type: "get",
        // 请求地址
        url: "/customer/download",
        data:'downloadType='+downloadType,
        // 请求成功
        success: function () {
            // console.log(data.code)
            alert("正在下载！")
        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            alert("请联系管理员")
        }
    });
}