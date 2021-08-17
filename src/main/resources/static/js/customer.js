var customerId;

/**
 * @param {Object}
 *            id 弹出编辑对话框
 */
function delDataDialog(custId) {
    $('#delCustomerDialog').modal('show');
    customerId = custId;
}

$('#delButton').on('click', function () {

    $.ajax({
        // 请求方式
        type: "DELETE",
        // 请求地址
        url: "/customer?id=" + customerId,
        // 请求成功
        success: function (result) {
            $('#delCustomerDialog').modal('hide');
            refreshData()

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
});

/**
 * @param {Object}
 *            id 弹出添加对话框
 */
function addDataDialog() {
    $('#addCustomerDialog').modal('show');

}

//restful规范： post  delete put  get  增删查改
function addData() {
    var custName = $("input[name=custName]")[0].value;
    var custIndustry = $("select[name=custIndustry]")[0].value;
    var custMobile = $("input[name=custMobile]")[0].value;
    var custLevel = $("select[name=custLevel]")[0].value;
    var custCreateTime = $("input[name=custCreateTime]")[0].value;
    // json串{key：value}
    var a = {
        'custName': custName,
        'custIndustry': custIndustry,
        'custMobile': custMobile,
        'custLevel': custLevel,
        'custCreateTime': custCreateTime
    };
    // jquery ajax用法
    $.ajax({
        // 请求方式
        type: "POST",
        // 请求地址
        url: "/customer",
        contentType: 'application/json;charset=UTF-8',
        // 数据
        data: JSON.stringify(a),
        // 请求成功
        success: function (result) {
            $('#addCustomerDialog').modal('hide');
            refreshData()

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}

//restful规范： post  delete put  get  增删查改
function modifyData() {
    // var custName = $("input[name=custName1]")[0].value;
    // var custIndustry = $("select[name=custIndustry1]")[0].value;
    // var custMobile = $("input[name=custMobile1]")[0].value;
    // var custLevel = $("select[name=custLevel1]")[0].value;
    // var custCreateTime = $("input[name=custCreateTime1]")[0].value;
    var custName = $("input[name=custName1]").val();
    var custIndustry = $("select[name=custIndustry1]").val();
    var custMobile = $("input[name=custMobile1]").val();
    var custLevel = $("select[name=custLevel1]").val();
    var custCreateTime = $("input[name=custCreateTime1]").val();
    // json串{key：value}
    var a = {
        'custId': customerId,
        'custName': custName,
        'custIndustry': custIndustry,
        'custMobile': custMobile,
        'custLevel': custLevel,
        'custCreateTime': custCreateTime
    };
    // jquery ajax用法
    $.ajax({
        // 请求方式
        type: "PUT",
        // 请求地址
        url: "/customer",
        // 数据
        data: JSON.stringify(a),

        contentType: 'application/json;charset=UTF-8',
        // 请求成功
        success: function (result) {
            $('#modifyCustomerDialog').modal('hide');

            refreshData()

        },
        // 请求失败，包含具体的错误信息
        error: function (e) {
            console.log(e.status);
            console.log(e.responseText);
            alert("操作有误请联系管理员")
        }
    });
}

// function modifyDataDialog(custId) {
//     $('#modifyCustomerDialog').modal('show');
//     customerId = custId;
//
// }
function modifyDataDialog(customer) {
    $('#modifyCustomerDialog').modal('show');
    customerId = customer.custId;
    $("input[name=custName1]").val(customer.custName);
    if (customer.custIndustry == "教育培训") {
        $("select[name=custIndustry1]").val(1);
    } else if (customer.custIndustry == "电子商务") {
        $("select[name=custIndustry1]").val(2);
    } else if (customer.custIndustry == "对外贸易") {
        $("select[name=custIndustry1]").val(3);
    } else if (customer.custIndustry == "酒店旅游") {
        $("select[name=custIndustry1]").val(4);
    } else if (customer.custIndustry == "房地产") {
        $("select[name=custIndustry1]").val(5);
    } else {
        $("select[name=custIndustry1]").val(0);
    }
    if (customer.custLevel == "VIP客户") {
        $("select[name=custLevel1]").val(6);
    } else if (customer.custLevel == "普通客户") {
        $("select[name=custLevel1]").val(7);
    } else {
        $("select[name=custLevel1]").val(0);
    }
    $("input[name=custMobile1]").val(customer.custMobile);

    $("input[name=custCreateTime1]").val(customer.custCreateTime);

}

function refreshData() {
    // var a = {
    //     'custId': $("#selectId").val() != "" ? $("#selectId").val() : 0,
    //     'custName': $("#selectName").val(),
    //     'custIndustry': $("#selectIndustry").val(),
    //     'custMobile': $("#selectMobile").val(),
    //     'custLevel': $("#selectLevel").val()
    // };
    // getData(a);
    // console.log(a);
    var b = {
        'custId': $("#selectId").val() != "" ? $("#selectId").val() : 0,
        'custName': $("#selectName").val(),
        'custIndustry': $("#selectIndustry").val(),
        'custMobile': $("#selectMobile").val(),
        'custLevel': $("#selectLevel").val(),
    };
    $.ajax({
        // 请求方式
        type: "post",
        // 请求地址
        url: "/customer/count",
        data: JSON.stringify(b),
        contentType: 'application/json;charset=UTF-8',
        // 请求成功
        success: function (result) {
            b.recordsTotal=result;
            getData(b);

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

    $('#customerList')
        .DataTable(
            {
                "destroy": true,
                "pagingType": "full_numbers",
                "sLoadingRecords": "正在加载数据...",
                "sZeroRecords": "暂无数据",
                "ajax": {
                    url: "/customer/query",
                    dataSrc: 'data',//原来为空，修改为data 因为服务端模式返回的是data入参
                    "type": "post",
                    "data": query,
                },
                "searching":false,
                "paging":true,//是否开启分页
                "serverSide":true,//服务端分页
                "lengthChange": false,//是否允许用户自定义显示数量
                "scrollY": "360px",//竖向滚动条
                "scrollCollapse": "true",//是否出现滚动条
                "iDisplayLength":20,
                "columns": [
                    {
                        "data": "custId"
                    },
                    {
                        "data": "custName"
                    },
                    {
                        "data": "custIndustry"
                    },
                    {
                        "data": "custLevel"
                    },
                    {
                        "data": "custMobile"
                    },
                    {
                        "data": "custCreateTime"
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
                            var html = "<a  href='#' role='button' data-toggle='modal' onclick='modifyDataDialog("
                                + JSON.stringify(row)
                                + ")'><i class='fa fa-pencil' ></i>修改</a>    "
                                + "<a href='#' role='button' data-toggle='modal'  onclick='delDataDialog("
                                + row.custId
                                + " )'><i class='fa fa-trash-o'  ></i>删除</a>";
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
