


getByPage(1);//默认查询第一页
//根据页数查询到数据
function getByPage(pageNum){
     $(".pro-product").html(" ")
    var url = "/Car/getCarById/"+pageNum;
    $.get(url,function (response) {
        console.log(response.data)
        var result =  response.data
        var CarArray =  result.records;
        for (var i =0 ; i<CarArray.length;i++){
            var car = CarArray[i];
            console.log(car);
            var ele ="<div class=\"pro-rearly\">"+ "   <div class=\"img\">\n" +
                "                            <img src=\""+car.carImg+"\" alt=\"\">\n" +
                "                        </div>\n" +
                "                        <!-- 车辆信息 -->\n" +
                "                        <div class=\"information\">\n" +
                "                            <li><a class=\"car-name\">"+car.carName+"</a></li>\n" +
                "                            <li class=\"info\">\n" +
                "                                <a>"+car.carDisp+"</a><span>|</span>\n" +
                "                                <a>"+car.carCase+"</a><span>|</span>\n" +
                "                                <a>"+car.carSeat+"</a><span>|</span>\n" +
                "                                <a href=\"../details/details_of_cars.html\">车辆详情></a>\n" +
                "                            </li>\n" +
                "                            <li class=\"lable\">\n" +
                "                                <a>省油</a>\n" +
                "                                <a>舒适</a>\n" +
                "                                <a>新能源</a>\n" +
                "                            </li>\n" +
                "                        </div>\n" +
                "                        <!-- 价格 -->\n" +
                "                        <div class=\"pro-price\">\n" +
                "                            <a>¥</a>\n" +
                "                            <a>"+car.carPrice+"</a>\n" +
                "                            <span>/每日</span>\n" +
                "                        </div>\n" +
                "                        <!-- 预定按钮 -->\n" +
                "                        <div class=\"bottom\">\n" +
                "                            <a href=\"../order/order_drop.html\">预购</a>\n" +
                "                        </div>"+"</div>"
            $(".pro-product").append(ele);
        }
        //页码信息;

        var prePage = result.current-1;//上一页
        var nextPage = result.current+1; //下一页
        var hrefStr = "javascript:getByPage("+prePage+")";
        var pageEle = "<div class=\"page-select\">"+"<span>共</span>\n" +
            "                 <a>"+result.pages+"</a>\n" +
            "                 <span>页 | </span>\n" +
            "                 <a>"+result.total+"</a>\n" +
            "                 <span>条数据</span>\n" +
            "                 <a href= 'javascript:getByPage("+prePage+")' class=\"upper\">上一页</a>\n" +
            "                 <span>\n" +
            "                 当前是第\n" +
            "                 </span>\n" +
            "                 <a>"+result.current+"</a>\n" +
            "                 <span>\n" +
            "                 页\n" +
            "                 </span>\n" +
            "                 <a href='javascript:getByPage("+nextPage+")' class=\"down\">下一页</a>"+"</div>"
        $(".pro-product").append(pageEle);
        if(result.current===1){
        $(".upper").removeAttr("href");
            // $(".upper").attr("pointer-events","none")
       }
        if(result.current===result.pages){
            $(".down").removeAttr("href");
            // $(".upper").attr("pointer-events","none")
        }
    })
}
//上一页，下一页按钮监听
$(".upper").click(function () {
    console.log("上一页")
})
$(".down").click(function () {
    console.log("下一页")
})
// 删除指定ID
// $(".deleteById").click(function (){
//     deleteById
//
// })

function deleteById(inShopId,pageNum){
    $.post("deleteById",{shopId:inShopId},function(result){
        console.log(result.data)
    })
    getByPage(pageNum);
}
$(".addShop").click(function() {
    var shopAddrProvince = $(".shopAddrProvince").val();
    var shopAddrCity = $(".shopAddrCity").val();
    var shopAddrArea = $(".shopAddrArea").val();
    var shopAddrDetail = $(".shopAddrDetail").val();
    var shopName = $(".shopName").val();
    var shopTelno = $(".shopTelno").val();
    console.log(shopTelno)
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/saveShop",
        data:{
            shopAddrProvince: shopAddrProvince,
            shopAddrCity: shopAddrCity,
            shopAddrArea: shopAddrArea,
            shopAddrDetail: shopAddrDetail,
            shopName: shopName,
            shopTelno: shopTelno
        },
        success:function (result) {   //     success含义:if (xhr.readyState == 4 && xhr.status == 200){
            console.log(result.data) //data :服务端的响应数据
            // if (data) {
            //     console.log("success")
            //     $(".insertState").text("添加成功")
            //     // document.querySelector(".insertState").innerText = "添加成功"
            // } else {
            //     console.log("fail")
            //     $(".insertState").text("添加失败")
            //     // document.querySelector(".insertState").innerText = "添加失败"
            // }
        }
    })
})