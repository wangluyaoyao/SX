



//验证手机号
$(".phoneNo").blur(checkPhoneNo)


function checkPhoneNo(){
    var phoneNo = $(".phoneNo").val()

    if(phoneNo === "" || phoneNo == null){
        $(".phoneTip").text("手机号不能为空")
        return false;
    }else if( !/^[0-9]\w{9,10}$/.test(phoneNo)){
        $(".phoneTip").text("手机号格式错误")
        return false;
    }else{

        $(".phoneTip").text("");
        return true;
    }
}
//验证登陆密码
$(".password").blur(checkPassword)


function checkPassword(){
    var password = $(".password").val()

    if(password === "" || password == null){
        $(".pasdTip").text("密码不能为空")
        return false;
    }else if( !/^[a-zA-Z0-9]\w{5,20}$/.test(password)){
        $(".pasdTip").text("密码格式错误")
        return false;
    }else{

        $(".pasdTip").text("");
        return true;
    }
}
//确认密码

function validate() {
    var pw1 = document.getElementById("password").value;
    var pw2 = document.getElementById("checkPassword").value;
    if(pw1 === pw2) {
        document.getElementById("checkTip").innerText="两次密码相同";

    }
    else {
        document.getElementById("checkTip").innerText="两次密码不相同";

    }
}

//验证手机验证码
$(".smsTip").click()