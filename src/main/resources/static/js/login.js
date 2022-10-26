//验证用户名
$(".userTel").blur(checkUsername)

//验证用户名
function checkUsername(){
    var username = $(".userTel").val()

    if(username === "" || username == null){
        $(".usernameTip").text("手机号不能为空")
        return false;
    }else if( !/^[0-9]\w{10}$/.test(username)){
        $(".usernameTip").text("手机号格式不正确")
        return false;
    }else{

        $(".usernameTip").text("");
        return true;
    }
}

//验证密码
$(".userPsd").blur(checkuserpwd);

function checkuserpwd(){
    var userPsd = $(".userPsd").val()

    if(userPsd === "" || userPsd == null){
        $(".psdTip").text("密码不能为空")
        return false;
    }else if( !/^[a-zA-Z0-9]\w{5,100}$/.test(userPsd)){
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
    }else if( !/^[0-9]\w{10}$/.test(phoneNo)){
        $(".phoneTip").text("手机号格式错误")
        return false;
    }else{

        $(".phoneTip").text("");
        return true;
    }
}

function getLoginValue(){
    var userTel = document.querySelector(".userTel").value;
    var userPwd = document.querySelector(".userPsd").value;
    var box = document.querySelector("input[type = checkbox]").checked;
    console.log("userTel:"+userTel);
    console.log("userPwd:"+userPwd);
    if (userTel!=null &&  userPwd!= null && box!= null){
        console.log("no null")
        document.querySelector(".error").innerText="";
        login();
    }else if (!userTel || !userPwd){
        console.log("no password or telNo id error")
        document.querySelector(".error").innerText="登陆账号或者密码错误！"
        return false;
    }else if (!box){
        console.log("no box!");
        document.querySelector(".no-agree").innerText="请勾选服务！";
        return false;
    }else {
        console.log("all none")
        login();
    }
}


function login(){
    $(".btn").click(function (){
        $.ajax({
            type:"post",
            url:"../customer/login",
            data:{
                userTel:$(".userTel").val(),
                userPsd:$(".userPsd").val()
            },
            success:function (result) {
                console.log("result:"+result);

                var token = result.data;
                console.log("token:"+token);

                localStorage.setItem("token",token);
                window.location.href = "../index.html";
            }
        })
    })
}
