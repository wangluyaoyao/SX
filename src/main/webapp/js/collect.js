//添加到收藏界面
$(".bt1").click(function (){
    //获得ul
     $(".collcet-ul").append('<li>'+
         '<div className="collect-car-box">'+
         '<div className="car-img">'+
         '<img src="../images/collect/collectcar1.png">'+
         '</div>'+
         '<div className="car-info">'+
         '<div className="car-name">荣威550混动沪牌</div>'+
         '<div className="car-des">'+
         '<p>车辆类型：三厢</p>'+
         '<p>座位：五座</p>'+
         '<a href="javascript:;" className="view-car-para">车辆详情></a>'+
         '</div>'+
         '</div>'+
         '<div className="car-a">'+
         '<button className="esc">取消收藏</button>'+
         ' <div className="car-des">'+
         '<p>变速箱：6档自动</p>'+
         '<p>能源消耗：汽油</p>'+
         '<p>排量：1.5L</p>'+
         '<p>进气:自然进气</p>'+
         '</div>'+
         +'</div>'+
         +'</div>'+
         +'</li>');
});

//取消收藏
$(".esc").attr().click(function(){

     layer.alert('确定提交吗？', {
          skin: 'layui-layer-molv' //样式类名 自定义样式
          , closeBtn: 1 // 是否显示关闭按钮
          , title : '网页提示！！' //标题
          , anim: 1 //动画类型
          , btn: ['确定', '取消'] //按钮
          , yes: function() {　　　　　　　　　 //这里也可以写响应的ajax请求
          }
          , btn2: function () {
               layer.msg('已取消');
               $(".esc").remove();
          }
     })
     var delectEle = $(".esc").parent().parent().parent();
     console.log(delectEle.html());
});
