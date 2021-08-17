var productId

function delDataDialog(proId) {
    $('#delProductDialog').modal('show');
    productId = proId;
}

$('#delButton').on('click', function () {

    $.ajax({
        // 请求方式
        type: "DELETE",
        // 请求地址
        url: "/product?id=" + productId,
        // 请求成功
        success: function (result) {
            $('#delProductDialog').modal('hide');
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
    $('#addProductDialog').modal('show');
}

function showImg(cell){
    baseImg= window.URL.createObjectURL(cell.files[0]);
    // filename = cell.files[0].name
    $("#product").attr("src", baseImg);
    console.log("baseImg:"+baseImg)
}

function imageToBase64(img) {//转base64的方法
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var dataURL = canvas.toDataURL("image/png");//规定图片是什么格式，image/格式
    return dataURL;
}

//restful规范： post  delete put  get  增删查改
function addData() {
    var proName = $("input[name=proName]")[0].value;
    var proType = $("select[name=proType]")[0].value;
    var proCount = $("input[name=proCount]")[0].value;
    var price = $("input[name=price]")[0].value;
    var img = new Image();
    img.src=baseImg;
    console.log(img);
    var base64 = imageToBase64(img);
    console.log(base64)
    // json串{key：value}
    var a = {
        'proName': proName,
        'proType': proType,
        'proImg':base64,
        'proCount': proCount,
        'price': price,
    };
    // jquery ajax用法
    $.ajax({
        // 请求方式
        type: "POST",
        // 请求地址
        url: "/product",
        contentType: 'application/json;charset=UTF-8',
        // 数据
        data: JSON.stringify(a),
        // 请求成功
        success: function (result) {
            $('#addProductDialog').modal('hide');
            console.log(base64)
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
function showImg1(cell){
    baseImg1= window.URL.createObjectURL(cell.files[0]);
    // filename = cell.files[0].name
    $("#product1").attr("src", baseImg1);
    console.log("baseImg:"+baseImg1)
}

//restful规范： post  delete put  get  增删查改
function modifyData() {
    var proName = $("input[name=proName1]").val();
    var proType = $("select[name=proType1]").val();
    var proCount = $("input[name=proCount1]").val();
    var price = $("input[name=price1]").val();
    var img = new Image();
    img.src=baseImg1;
    console.log(img);
    var base64 = imageToBase64(img);
    console.log(base64)
    // json串{key：value}
    var a = {
        'proId':productId,
        'proName': proName,
        'proType': proType,
        'proImg':base64,
        'proCount': proCount,
        'price': price
    };
    // jquery ajax用法
    $.ajax({
        // 请求方式
        type: "PUT",
        // 请求地址
        url: "/product",
        // 数据
        data: JSON.stringify(a),

        contentType: 'application/json;charset=UTF-8',
        // 请求成功
        success: function (result) {
            $('#modifyProductDialog').modal('hide');
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

function modifyDataDialog(product) {
    // console.log(product);
    $('#modifyProductDialog').modal('show');
    productId = product.proId;
    $("input[name=proName1]").val(product.proName);
    if (product.proType == "水果") {
        $("select[name=proType1]").val("水果");
    } else if (product.proType == "肉禽") {
        $("select[name=proType1]").val("肉禽");
    } else if (product.proType  == "蔬菜") {
        $("select[name=proType1]").val("蔬菜");
    } else if (product.proType == "其他") {
        $("select[name=proType1]").val("其他");
    } else {
        $("select[name=proType1]").val(0);
    }
    $("input[name=proImg1]").val(product.proImg);

    $("input[name=proCount1]").val(product.proCount);

    $("input[name=price1]").val(product.price);

}
function showPro(product){
    $('#proImgDialog').modal('show');
    proImg = product.proImg
    // $("#proImg").attr("src", "images/product/"+proImg);
    $("#proImg").attr("src", "/images/product/"+proImg);
}

function refreshData() {
    var b = {
        'proId': $("#selectId").val() != "" ? $("#selectId").val() : 0,
        'proName': $("#selectName").val(),
        'proType': $("#selectProType").val(),
    };
    console.log(b)
    getData(b)
}

function getData(query) {

    $('#productList')
        .DataTable(
            {
                "destroy": true,
                "pagingType": "full_numbers",
                "sLoadingRecords": "正在加载数据...",
                "sZeroRecords": "暂无数据",
                "ajax": {
                    url: "/product/query",
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
                        "data": "proId"
                    },
                    {
                        "data": "proName"
                    },
                    {
                        "data": "proType"
                    },
                    {
                        "data": "proImg"
                    },
                    {
                        "data": "proCount"
                    },
                    {
                        "data": "price"
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
                                + row.proId + " )'><i class='fa fa-trash-o'  ></i>删除</a>"+
                                "<a href='#' role='button' data-toggle='modal'  onclick='showPro("
                                + JSON.stringify(row) + ")'><i class='fa fa-pencil'  ></i>查看</a>";
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