//显示用户的收藏
showCollect();
function showCollect(){
     var url = "/sx-collect/collectAll";
     var token = localStorage.getItem("token");
     $.ajax({
          type: "get",
          url: url,
          headers: {'token': token},
          success:function (result){
               console.log(result);
               var data = result.data;
               for (var i=0; i<data.length;i++){
                    var dataele = data[i];
                    var car = dataele['car'];
                    var collect = dataele['collect'];
                    collectId = collect.collectId;
                    carName = car.carName;
                    carId = car.carId;
                    carImg = car.carImg;
                    carModel = car.carModel;
                    carDisp = car.carDisp;
                    carSeat = car.carSeat;
                    carCase = car.carCase;
                    carExhaust = car.carExhaust;
                    carTank = car.carTank;
                    var collectEle = "<li>\n" +
                        "                <div class=\"collect-car-box\">\n" +
                        "                    <div class=\"car-img\">\n" +
                        "                        <a href=\"/details/"+carId+"\"><img src=\""+carImg+"\"></a>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"car-esc\">\n" +
                        "                        <button class=\"esc\" value=\""+collectId+"\">取消收藏</button>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"car-tittle\">\n" +
                        "                        <div class=\"car-name\">"+carName+"</div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"car-info\">\n" +
                        "                        <div class=\"car-des\">\n" +
                        "                            <ul>\n" +
                        "                                <li>车辆类型:<span>"+carModel+"</span></li>\n" +
                        "                                <li>座位:<span>"+carSeat+"</span></li>\n" +
                        "                                <li>排量:<span>"+carDisp+"</span></li>\n" +
                        "                            </ul>\n" +
                        "                        </div>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"car-infoother\">\n" +
                        "                        <div class=\"car-des\">\n" +
                        "                            <ul>\n" +
                        "                                <li>变速箱:<span>"+carCase+"</span></li>\n" +
                        "                                <li> 进气:<span>"+carExhaust+"</span></li>\n" +
                        "                                <li>油箱:<span>"+carTank+"</span></li>\n" +
                        "                            </ul>\n" +
                        "\n" +
                        "                        </div>\n" +
                        "\n" +
                        "                    </div>\n" +
                        "\n" +
                        "                </div>\n" +
                        "            </li>";
                    $(".collcet-ul").append(collectEle);
               }
          }
     })
}

//取消收藏
$(document).on( 'click',".esc",function(event){
     layer.alert('确定提交吗？', {
          skin: 'layui-layer-molv' //样式类名 自定义样式
          , closeBtn: 1 // 是否显示关闭按钮
          , title : '提示！！' //标题
          , anim: 1 //动画类型
          , btn: ['确定', '取消'] //按钮
          , yes: function() {//这里也可以写响应的ajax请求
               var ele=event.target;
               var collectId = ele.value;
               var url = "/sx-collect/collectEsc";
               $.ajax({
                    type: "post",
                    url: url,
                    data:{
                         "collectId":collectId
                    },
                    success:function (result){
                         console.log("成功");
                         location.reload();
                    }
               })　　　　　
          }
          , btn2: function () {
               layer.msg('已取消');
          }
     })
});

//判断是否收藏了
decideCollect();
function decideCollect(){
     var url = "/sx-collect/collect";
     var token = localStorage.getItem("token");
     var carId = $(".carId").val();
     $.ajax({
          type: "get",
          url: url,
          data: {
               "carId":carId
          },
          headers: {'token': token},
          success:function (result){
               if (result != null){
                    $(".collect_sucess_text").text("已收藏!");
                    $(".collect_img").attr("src","../images/collect/collectsuccess.png");
                    $(".collect_img").attr("class","collect_sucess");
               }

          }
     })
}
//收藏车辆
$(".collect_img").click(function (){
     var url = "/sx-collect/collectSave";
     var token = localStorage.getItem("token");
     var carId = $(".carId").val();
     $.ajax({
              type: "post",
              url: url,
              data: {
                   "carId": carId
              },
              headers: {'token': token},
              success: function (result) {
                   console.log("成功!")
              }
         })
});



