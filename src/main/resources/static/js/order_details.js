$(".order_success_button").click(function () {
    layer.alert('是否已还车，并告知商家', {
        skin: 'layui-layer-molv' //样式类名 自定义样式
        , closeBtn: 1 // 是否显示关闭按钮
        , title : '确认还车！！' //标题
        , anim: 1 //动画类型
        , btn: ['确定', '取消'] //按钮
        , yes: function() {//这里也可以写响应的ajax请求
            var ordNumber = $("#ordNumber").val();

            var url = "/successOrder";
            $.ajax({
                type: "post",
                url: url,
                data:{
                    "ordNumber":ordNumber
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

