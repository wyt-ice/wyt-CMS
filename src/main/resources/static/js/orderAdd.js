var productName
var productPrice
function getPrice(proMsg){
    var proname=$(proMsg).attr("proname");
    var price=$(proMsg).attr("price");
    productName=proname;
    productPrice=price
}
function orderAdd() {
    var proName = productName;
    var custName = $("input[name=custName]")[0].value;
    var counts = $("input[name=counts]")[0].value;
    var accountRecei = counts*productPrice;
    var prepayment = accountRecei;
    var address = $("input[name=address]")[0].value;
    var orderState = "已下单";
    // json串{key：value}
    var a = {
        'proName': proName,
        'custName': custName,
        'counts':counts,
        'accountRecei': accountRecei,
        'prepayment': prepayment,
        'address': address,
        'orderState':orderState,
    };
    // jquery ajax用法
    $.ajax({
        // 请求方式
        type: "POST",
        // 请求地址
        url: "/order",
        contentType: 'application/json;charset=UTF-8',
        // 数据
        data: JSON.stringify(a),
        // 请求成功
        success: function () {
            window.location.href = "orderManager.html"


        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}
function getQR(){
    $('#wxpay').modal('show');
}