

function userShow(){
    var token = localStorage.getItem("token");
    console.log(token)
    if(token != null) {
        $.ajax({
            type: "post",
            url: "/customer/userVerification/" + token,  //用户验证,
            // data:{token:token},
            success: function (result) {
                var user = result.data
                // if (userName!=null){
                //更新页面
                console.log(user)
                $(".loginAfter").text("欢迎回来!"+user.userName);
                $(".userId").val(user.userId)
                $(".loginAfter").attr("href", "")
                $(".bye").text("退出");
                $(".bye").attr("href", "../index.html")
                //  }
            }
        })
    }
}
userShow();

$(".bye").click(function (){

    localStorage.removeItem("token");
});


//关闭窗口后自动清楚token
// window.onload = function () {
//     let lastTime = localStorage.getItem("lastTime");
//     const interval = 3 * 1000;
//     // 如果没有上一次离开的时间或者时间间隔大于3s，就清除token
//     if (!lastTime || new Date().getTime() - lastTime > interval) {
//         localStorage.removeItem("token");
//         console.log("清除token")
//     }else{
//         console.log("时间过短不清除token")
//     }
// };
// window.onunload = function () {
//     localStorage.setItem("lastTime", new Date().getTime());
// };