//设置默认时间
{
    var now = new Date();
    var valuetime = now.getFullYear() + ":" + (now.getMonth() + 1) + ":" + now.getDate()
    $(".start-time").val(valuetime)
    if (now.getHours() < 10) {
        var hour = "0" + now.getHours()
    } else var hour = now.getHours()
    if (now.getMinutes() < 10) {
        var minu = "0" + now.getMinutes()
    } else var minu = now.getMinutes()
    if (now.getSeconds() < 10) {
        var seco = "0" + now.getSeconds()
    } else var seco = now.getSeconds()
    $(".satrt-hour").val(hour + ":" + minu + ":" + seco)
}
var te

getByPage(1);//默认查询第一页
//根据页数查询到数据
function getByPage(pageNum){
     $(".pro-product").html(" ")
    var url = "/Car/getCarById/"+pageNum;
    $.get(url,function (response) {
        console.log(response.data)
        var result =  response.data
        var CarArray =  result.records;
        showCar(CarArray)
        //页码信息;
        {
            var prePage = result.current - 1;//上一页
            var nextPage = result.current + 1; //下一页
            var hrefStr = "javascript:getByPage(" + prePage + ")";
            var pageEle = "<div class=\"page-select\">" + "<span>共</span>\n" +
                "                 <a>" + result.pages + "</a>\n" +
                "                 <span>页 | </span>\n" +
                "                 <a>" + result.total + "</a>\n" +
                "                 <span>条数据</span>\n" +
                "                 <a href= 'javascript:getByPage(" + prePage + ")' class=\"upper\">上一页</a>\n" +
                "                 <span>\n" +
                "                 当前是第\n" +
                "                 </span>\n" +
                "                 <a>" + result.current + "</a>\n" +
                "                 <span>\n" +
                "                 页\n" +
                "                 </span>\n" +
                "                 <a href='javascript:getByPage(" + nextPage + ")' class=\"down\">下一页</a>" + "</div>"
            $(".pro-product").append(pageEle);


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

/*
* 多条件筛选
* */

// $(".special_coupno").on('click',"#cou_receive",function(event){
$(".car-name a").click(function() {


    var text = $(this);
    if(text.attr('class')==='active'){
        return
    };
    console.log(text.text());
    var carName = text.text();
    getByCarName(carName);

})

//根据姓名查询
function getByCarName(carName){
        $(".pro-product").html(" ");
    console.log("条件查询");
    if (carName ==="全部"){
        getByPage(1);
    }else {
        $.ajax({
            url:"/Car/getCarListByBrand",
            type:"get",
            data:{brand:carName,
                  page: 1
            },
            success:function (response){
                var result =  response.data
                sereenCar = result.pageList;
                // var carArray =  result.records;
                //   console.log(carArray)
                //    $(".pro-product").html(result)
                showCar(sereenCar)
                // 页码信息;
                buttonLimit(result);

            }
        })
    }
}
//分页
function getCarBrandPage(page){
    $(".pro-product").html(" ")
    $.ajax({
        url:"/Car/getCarBrandPage/"+page,
        type:"get",
        success:function (response){
            var result = response.data;
            var sereenCar =  response.data.pageList;
            showCar(sereenCar);
            // 页码信息;
            buttonLimit(result);
        }
    })
}
//分页按钮限制
function buttonLimit(result){
    var prePage = result.pageNo - 1;//上一页
    var nextPage = result.pageNo + 1; //下一页
    var pageEle = "<div class=\"page-select\">" + "<span>共</span>\n" +
        "                 <a>" + result.pageSum + "</a>\n" +
        "                 <span>页 | </span>\n" +
        "                 <a>" + result.total + "</a>\n" +
        "                 <span>条数据</span>\n" +
        "                 <a href= 'javascript:getCarBrandPage(" + prePage + ")' class=\"upper\">上一页</a>\n" +
        "                 <span>\n" +
        "                 当前是第\n" +
        "                 </span>\n" +
        "                 <a>" + result.pageNo + "</a>\n" +
        "                 <span>\n" +
        "                 页\n" +
        "                 </span>\n" +
        "                 <a href='javascript:getCarBrandPage(" + nextPage + ")' class=\"down\">下一页</a>" + "</div>"
    $(".pro-product").append(pageEle);
    if (result.pageNo === 1) {
        $(".upper").removeAttr("href");
        // $(".upper").attr("pointer-events","none")
    }
    if (result.pageNo === result.pageSum) {
        $(".down").removeAttr("href");
        // $(".upper").attr("pointer-events","none")
    }
}
//商品渲染
function showCar(carArray){
    for (var i =0 ; i<carArray.length;i++){
        var car = carArray[i];
        console.log(car);
        var ele = " <div class=\"pro-rearly\">\n" +
            "                    <div class=\"img\">\n" +
            "                        <img src=\""+car.carImg+"\" alt=\"\">\n" +
            "                    </div>\n" +
            "                    <!-- 车辆信息 -->\n" +
            "                    <div class=\"information\">\n" +
            "                        <div class=\"in-top\">\n" +
            "                            <li><a class=\"car-name\">"+car.carName+"</a></li>\n" +
            "                        </div>\n" +
            "                        <div class=\"in-middle\">\n" +
            "                            <li class=\"info\">\n" +
            "                                <a>"+car.carDisp+"</a><span>|</span>\n" +
            "                                <a>"+car.carCase+"</a><span>|</span>\n" +
            "                                <a>"+car.carSeat+"座"+"</a><span>|</span>\n" +
            "                                <a href=\"../details/datails_of_cars.html\" class=\"info-detalis\">车辆详情></a>\n" +
            "                            </li>\n" +
            "                        </div>\n" +
            "                        <div class=\"in-bottom\">\n" +
            "                            <li class=\"lable\">\n" +
            "                                <a>省油</a>\n" +
            "                                <a>舒适</a>\n" +
            "                                <a>新能源</a>\n" +
            "                            </li>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>\n" +
            "                    <!-- 价格 -->\n" +
            "                    <div class=\"pro-price\">\n" +
            "                        <div class=\"pro-text\">\n" +
            "                            <a>¥</a>\n" +
            "                            <a>"+car.carPrice+"</a>\n" +
            "                            <span>/每日</span>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>\n" +
            "                    <!-- 预定按钮 -->\n" +
            "                    <div class=\"pro-bottom\">\n" +
            "\n" +
            "                        <a href=\"../order/order_drop.html\">\n" +
            "                            <div class=\"pro-order\">\n" +
            "                                预购\n" +
            "                            </div>\n" +
            "                        </a>\n" +
            "                    </div>\n" +
            "                </div>"
        $(".pro-product").append(ele);
    }
}

