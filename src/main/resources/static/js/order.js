var ordersId

function delDataDialog(orderId) {
    $('#delOrderDialog').modal('show');
    ordersId = orderId;
}

$('#delButton').on('click', function () {

    $.ajax({
        // 请求方式
        type: "DELETE",
        // 请求地址
        url: "/order?id=" + ordersId,
        // 请求成功
        success: function (result) {
            $('#delOrderDialog').modal('hide');
            getData();

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
});

function addDataDialog() {
    $('#addOrderDialog').modal('show');
}
function addData() {
    var proName = $("input[name=proName]")[0].value;
    var custName = $("input[name=custName]")[0].value;
    var counts = $("input[name=counts]")[0].value;
    var accountRecei = $("input[name=accountRecei]")[0].value;
    var prepayment = $("input[name=prepayment]")[0].value;
    var address = $("input[name=address]")[0].value;
    var orderState = $("select[name=orderState]")[0].value;
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
        success: function (result) {
            $('#addOrderDialog').modal('hide');
            getData();

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}
function modifyData() {
    var proName = $("input[name=proName1]").val();
    var custName = $("input[name=custName1]").val();
    var counts = $("input[name=counts1]").val();
    var accountRecei = $("input[name=accountRecei1]").val();
    var prepayment = $("input[name=prepayment1]").val();
    var address = $("input[name=address1]").val();
    var orderState = $("select[name=orderState1]").val();
    // json串{key：value}
    var a = {
        'orderId':ordersId,
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
        type: "PUT",
        // 请求地址
        url: "/order",
        // 数据
        data: JSON.stringify(a),

        contentType: 'application/json;charset=UTF-8',
        // 请求成功
        success: function (result) {
            $('#modifyOrderDialog').modal('hide');
            getData();

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}

function modifyDataDialog(order) {
    // console.log(product);
    $('#modifyOrderDialog').modal('show');
    ordersId = order.orderId;
    $("input[name=proName1]").val(order.proName);
    $("input[name=custName1]").val(order.custName);
    $("input[name=counts1]").val(order.counts);
    $("input[name=accountRecei1]").val(order.accountRecei);
    $("input[name=prepayment1]").val(order.prepayment);
    $("input[name=address1]").val(order.address);
    if (order.orderState == "完成订单") {
        $("select[name=orderState1]").val("完成订单");
    } else if (order.orderState == "已发货") {
        $("select[name=orderState1]").val("已发货");
    } else if (order.orderState == "售罄") {
        $("select[name=orderState1]").val("售罄");
    } else {
        $("select[name=orderState1]").val(0);
    }


}
function refreshData() {
    var b = {
        'orderState':$("#selectState").val(),
    };
    console.log(b)
    getData(b)
}

function orderSubmit(order){
    $.ajax({
        // 请求方式
        type: "POST",
        // 请求地址
        url: "/order/submit",
        contentType: 'application/json;charset=UTF-8',
        // 数据
        data: order,
        // 请求成功
        success: function (result) {
            $('#addOrderDialog').modal('hide');
            getData();

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}

function getData(query) {

    $('#orderList')
        .DataTable(
            {
                "destroy": true,
                "pagingType": "full_numbers",
                "sLoadingRecords": "正在加载数据...",
                "sZeroRecords": "暂无数据",
                "ajax": {
                    url: "/order/query",
                    dataSrc: '',//原来为空，修改为data 因为服务端模式返回的是data入参
                    "type": "post",
                    "data":query,
                },
                "searching":false,
                "paging":true,//是否开启分页
                "serverSide":false,//服务端分页
                "lengthChange": false,//是否允许用户自定义显示数量
                "scrollY": "360px",//竖向滚动条
                "scrollCollapse": "true",//是否出现滚动条
                "iDisplayLength":20,
                "columns": [
                    {
                        "data": "orderId"
                    },
                    {
                        "data": "proName"
                    },
                    {
                        "data": "custName"
                    },
                    {
                        "data": "counts"
                    },
                    {
                        "data": "accountRecei"
                    },
                    {
                        "data": "prepayment"
                    },
                    {
                        "data": "address"
                    },
                    {
                        "data": "orderState"
                    },
                    {
                        "data": null,
                        "render": function (data, type, row, meta) {
                            // var html = '<a  href="#" role="button" data-toggle="modal" onclick="modifyDataDialog('
                            //     + row.custId
                            //     + ')"><i class="fa fa-pencil" ></i>修改</a>    '
                            //     + '<a href="#" role="button" data-toggle="modal"  onclick="delDataDialog('
                            //     + row.custId
                            //     + ')"><i class="fa fa-trash-o"  ></i>删除</a>';
                            var html =
                                "<a  href='#' role='button' data-toggle='modal' onclick='modifyDataDialog("
                                + JSON.stringify(row) + ")'><i class='fa fa-pencil' ></i>修改</a>" +
                                "<a href='#' role='button' data-toggle='modal'  onclick='delDataDialog("
                                + row.orderId + " )'><i class='fa fa-trash-o'  ></i>删除</a>";
                            return html;
                        }
                    }

                ],
                "language": {
                    "processing": "玩命加载中...",
                    "lengthMenu": "显示 _MENU_ 项结果",
                    "zeroRecords": "没有匹配结果",
                    "info": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                    "infoEmpty": "显示第 0 至 0 项结果，共 0 项",
                    "infoFiltered": "(由 _MAX_ 项结果过滤)",
                    "infoPostFix": "",
                    "url": "",
                    "paginate": {
                        "first": "首页",
                        "previous": "前一页",
                        "next": "下一页",
                        "last": "末页"
                    }
                }
            });

}