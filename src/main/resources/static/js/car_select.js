//品牌事件冒泡
document.querySelector(".car-name").onclick = function(event){
    var element = event.target;
    var typeName =  element.nodeName;//标签名称
    var type =  element.type;//标签类型
//    console.log(typeName);
//    console.log(type);
//     console.log(element);
    var workArray = document.querySelector(".car-name").children;
    var temp = workArray[1].children[0].className
    // console.log(temp)
    $(".all").attr("class"," ")
    // for(var i = 0;i<workArray.length;i++){
    //  workArray[i].children[0].className = " "
    // }
    element.className = "active"
}


$(".pro-sumbit").click(function (){
    var content =  $(".car-search").val();
    getCarFilter(content)
    $(".pro-product").html(" ")
})

 getCarFilter(null);  //开始时先查询一次

function getCarFilter(carName){
    console.log(carName)
    $.ajax({
        url:"/Car/getCarFilter",
        type:"get",
        data:{carName : carName},
        success:function (result){
            console.log(result.data)
             // var carArray =  result.data.records;  //集合转为数组
            showCar(result.data)
        }

    })
}





//车辆渲染
function showCar(carArray) {
    for (var i = 0; i < carArray.length; i++) {
        var  car = carArray[i];
        console.log(car)
        var ele = "<div class=\"pro-rearly\">\n" +
            "                <a href=\"javascript:void(0)\">\n" +
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
            "                                <a>"+car.carDisp+"</a>\n" +
            "                                <a>"+car.carModel+"</a>\n" +
            "                                <a>"+car.carSeat+"</a>\n" +
            "                            </li>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>\n" +
            "                    <!-- 价格 -->\n" +
            "                    <div class=\"pro-price\">\n" +
            "                        <div class=\"pro-text\">\n" +
            "                            <a>¥</a>\n" +
            "                            <a>"+car.carPrice+"</a>\n" +
            "                            <span>/日</span>\n" +
            "                        </div>\n" +
            "\n" +
            "                    </div>\n" +
            "                </a>\n" +
            "            </div>"
        $(".pro-product").append(ele);
    }

}
