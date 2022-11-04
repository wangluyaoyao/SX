



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
        document.getElementById("checkTip").innerText="";

    }
    else {
        document.getElementById("checkTip").innerText="两次密码不相同";

    }
}


var button = document.getElementById('btn');
button.onclick = function() {
    button.disabled = 'disabled';
    var time = 60;
    var timer = setInterval(function() {
        if (time == -1) {
            clearInterval(timer)
            button.disabled = '';
            button.value = '获取验证码';
        } else {
            button.value = time + '秒后重新获取';
            time--;
        }
    }, 1000)
}

var sms = "";
var phoneNo = ""
//发送验证码
$(".btn").click(function (){
    sendCode()
})

function sendCode(){

    // var sms = "";
    var phone = $("#phone").val();
    console.log("登陆的手机号:"+phone);
    if (phone != ""){

        $.ajax({
            url : "../customer/send",
            type : "get",
            dataType : "text",
            async:false,
            data: {
                "phone" : phone
            },
            success:function (result) {
                console.log("............");
                console.log(result)

                console.log(typeof result)

                sms = JSON.stringify(result).slice(52,58);
                phoneNo = result
                // console.log(typeof sms[2]);
                console.log(sms)
                console.log("code:"+ sms);
                //    console.log("sms:"+sms)
                alert("发送成功");
            }
        });

        return sms;
        // console.log("sms:"+sms);
    }else {
        $("#loginfail1").text("请输入手机号")
        return false;
    }

}

//点击注册
$(".registBtn").click(function (){
    register()
})
function register(){
    $.ajax({
        type:"post",
        url:"../customer/register",
        data:{
            userTel: $(".phoneNo").val(),
            userPsd: $(".userPsd").val()
        },
        success:function (responseTel){
            console.log("responseTel:"+responseTel);
            var userPsd = $(".userPsd")
            var userTel = $(".phoneNo")
            var code = $("#code").val();
            var box = document.querySelector("input[type = checkbox]").checked;
            var pw1 = document.getElementById("password").value;
            var pw2 = document.getElementById("checkPassword").value;
            if (/^[0-9]\w{9,10}$/.test(userTel)){
                alert("格式不正确");
            }else if (!box){
                alert("请同意服务")
            }else if (code == ""){
                alert("请输入验证码")
            }else if (sms != code){
                alert("验证码不正确")
            }else if (pw1 !== pw2){
                alert("两次密码不相同")
            }else if (userPsd === "" || userPsd == null){
                alert("密码不能为空")
            }else if (/^[a-zA-Z0-9]\w{5,100}$/.test(userPsd)){
                alert("密码格式不正确")
            }else {
                alert("注册成功！")
            }

        }
    })
}