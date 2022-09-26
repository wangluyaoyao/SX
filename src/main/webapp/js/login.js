//验证用户名
$(".username").blur(checkUsername)

//验证用户名
function checkUsername(){
    var username = $(".username").val()

    if(username === "" || username == null){
        $(".usernameTip").text("用户名不能为空")
        return false;
    }else if( !/^[a-zA-Z]\w{5,11}$/.test(username)){
        $(".usernameTip").text("用户名只能由字母、数字、下划线组成，长度为6-12个字符之间")
        return false;
    }else{

        $(".usernameTip").text("");
        return true;
    }
}

//验证密码
$(".password").blur(checkuserpwd);

function checkuserpwd(){
    var userpwd = $(".password").val()

    if(userpwd === "" || userpwd == null){
        $(".psdTip").text("密码不能为空")
        return false;
    }else if( !/^[a-zA-Z0-9]\w{6,11}$/.test(userpwd)){
        $(".psdTip").text("密码格式错误")
        return false;
    }else{

        $(".psdTip").text("");
        return true;
    }
}
//验证手机号
$(".phoneNo").blur(checkPhoneNo)
function checkPhoneNo(){
    var phoneNo = $(".phoneNo").val()

    if(phoneNo === "" || phoneNo == null){
        $(".phoneTip").text("手机号不能为空")
        return false;
    }else if( !/^[a-zA-Z]\w{11}$/.test(phoneNo)){
        $(".phoneTip").text("手机号格式错误")
        return false;
    }else{

        $(".phoneTip").text("");
        return true;
    }
}
