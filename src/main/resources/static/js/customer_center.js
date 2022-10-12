
$(".sub-nav").click(function (event){
    //侧栏样式
    var element = event.target;
    $(".cc-change").each(function (){
        this.className = "cc-change";
    })
    element.className = "cc-change ck";
    var eId = element.id;
    console.log(eId);
    //内容切换
    $(".information").each(function (){
        this.className = "information hide";
    });
    if (eId === "exchange-info1" || eId === "exchange1"){
        $("#info1").attr("class","information");
    }else if (eId === "exchange-info2"){
        $("#info2").attr("class","information");
    }else if (eId === "exchange-info3"){
        $("#info3").attr("class","information");
    }else if (eId === "exchange-info4" || eId === "exchange2"){
        $("#info4").attr("class","information");
    }else if (eId === "exchange-info5"){
        $("#info5").attr("class","information");
    }
});
//点击个人中心-用户信息中的修改跳到修改信息
$(".update-info").click(function (){
    //侧栏样式
    $(".cc-change").each(function (){
        this.className = "cc-change";
    })


    $("#exchange-info3").attr("class","cc-change ck");
    //内容切换
    $(".information").each(function (){
        this.className = "information hide";
    });
    $("#info3").attr("class","information");

});



$(".order_exchange").click(function (){
    console.log("1a");
    $(".information").each(function (){
        this.className = "information hide";
    });
    $("#info6").attr("class","information");
});


//获取修改个人信息的值
$("#Submit").click(function(){
    var username = $(".username").val();
    console.log(username);

    var usertel = $(".usertel").val();
    console.log(usertel);

    var useremail = $(".useremail").val();
    console.log(useremail);

    var useridcardIpt = $(".useridcardIpt").val();
    console.log(useridcardIpt);

    var gender = $(".gerderBtn:checked").val();//单选按钮 radio
    console.log(gender);

    var userdate = $(".userdate").val();
    console.log(userdate);


});
//验证用户名
$(".username").blur(checkUsername);
function checkUsername(){
    var username = $(".username").val();
    //console.log(username);
    if(username=="" || username ==null){
        $(".usernameTip").text("用户名不能为空");
        return false;
    }else if(!/^[a-zA-Z]\w{5,11}$/.test(username)){
        $(".usernameTip").text("用户名只能有字母、数字、下划线组成，长度为6-12个字符");
        return false;
    }else{
        $(".usernameTip").text("");
        return true;
    }
}



//验证邮箱
$(".useremail").blur(checkUseremail);
function checkUseremail(){
    var useremail = $(".useremail").val();
    if(useremail==""||useremail==null){
        $(".useremailTip").text("邮箱不能为空");
        return false;
    }else if(!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(useremail)){
        $(".useremailTip").text("邮箱格式不对");
        return false;
    }else{
        $(".useremailTip").text("");
        return true;
    }
}

//验证手机号
$(".usertel").blur(checkUsertel);
function checkUsertel(){
    var usertel = $(".usertel").val();
    var usertel =  $(".usertel").val();
    if(usertel==""||usertel==null){
        $(".useretelTip").text("手机号不能为空");
        return false;
    }else if(!/^[1][3,4,5,7,8][0-9]{9}$/.test(usertel)){
        $(".useretelTip").text("手机号格式不对");
        return false;
    }else{
        $(".useretelTip").text("");
        return true;
    }
}

//修改密码
//验证密码
$(".userpassword").blur(checkUserpassword);
function checkUserpassword(){
    var userpassword =  $(".userpassword").val();
    if(userpassword==""||userpassword==null){
        $(".userpasswordTip").text("密码不能为空");
        return false;
    }else if(!/^[a-zA-Z]\w{5,17}$/.test(userpassword)){
        $(".userpasswordTip").text("密码只能是字母、数字、下划线组成，长度我6-18个字符");
        return false;
    }else{
        $(".userpasswordTip").text("");
        return true;
    }
}

//切换订单界面
$(function () {
    $('#myTab a:last').tab('show');
})
$('#myTab a').click(function (e) {
    e.preventDefault();
    $(this).tab('show');
})

//显示当前用户信息
showUser();
function showUser(){
    var url = "/usercenter/user";
    $.get(url,function (user){

        console.log(user.data);
        var user = user.data;
        userId = user.userId;
        userTel = user.userTel;
        userPsd = user.userPsd;
        userGender = user.userGender;
        if (userGender === "0"){
            userGender = '男';
            console.log("0")
        }else{
            userGender = '女';
            console.log("1")
        }

        userIdcard = user.userIdcard;
        userName = user.userName;
        userEmail = user.userEmail;
        userBir = user.userBir;
        userPetname = user.userPetname;
        var userEle = "<h2>用户信息</h2>\n" +
            "                <div class=\"mod information-index\">\n" +
            "                    <div class=\"ui-bfc information-index-panel\">\n" +
            "                        <!--头像暂定-->\n" +
            "                        <div class=\"ui-bfc-hd hd\">\n" +
            "                            <img src=\"../images/customer_photo.png\" width=\"99\" height=\"99\">\n" +
            "                        </div>\n" +
            "                        <!-- 个人信息-->\n" +
            "                        <div class=\"ui-bfc-bd bd\">\n" +
            "                            <table width=\"100%\">\n" +
            "                                <tbody>\n" +
            "                                <tr>\n" +
            "                                    <td class=\"cell-title\">用户名：</td>\n" +
            "                                    <td>\n" +
            "                                        <p>"+userPetname+"</p>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td class=\"cell-title\">电子邮箱：</td>\n" +
            "                                        <td>\n" +
            "                                            <p>"+userEmail+"</p>\n" +
            "                                            <span class=\"gray\">(未验证)</span>\n" +
            "                                            <a class=\"update-info\">修改</a>\n" +
            "                                        </td>\n" +
            "\n" +
            "                                    </tr>\n" +
            "                                    <tr>\n" +
            "                                        <td class=\"cell-title\">手机号码：</td>\n" +
            "                                        <td>\n" +
            "                                            <p>"+userTel+"</p>\n" +
            "                                            <span class=\"gray\">(未验证)</span>\n" +
            "                                            <a class=\"update-info\">修改</a>\n" +
            "                                        </td>\n" +
            "                                    </tr>\n" +
            "                                <tr>\n" +
            "                                    <td class=\"cell-title\">身份证：</td>\n" +
            "                                    <td>\n" +
            "                                        <p>"+userIdcard+"</p>\n" +
            "                                        <span class=\"gray\">(未验证)</span>\n" +
            "                                        <a class=\"update-info\">立即认证</a>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <td class=\"cell-title\">真实姓名：</td>\n" +
            "                                    <td>\n" +
            "                                        <p>"+userName+"</p>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <td class=\"cell-title\">生日：</td>\n" +
            "                                    <td>\n" +
            "                                        <p>"+userBir+" </p>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                <tr>\n" +
            "                                    <td class=\"cell-title\">性别：</td>\n" +
            "                                    <td>\n" +
            "                                        <p>"+userGender+" </p>\n" +
            "                                    </td>\n" +
            "                                </tr>\n" +
            "                                </tbody>\n" +
            "                            </table>\n" +
            "                        </div>\n" +
            "                    </div>\n" +
            "                    <div class=\"operate-panel\">\n" +
            "                        <a class=\"ui-btn ui-btn-white-l update-info\">修改信息</a>\n" +
            "                    </div>\n" +
            "                </div>";
        $("#info1").append(userEle);


    })

}






