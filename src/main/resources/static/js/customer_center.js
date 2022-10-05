
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