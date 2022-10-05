//费用明细
var oImg1 = document.querySelector(".change_img");
var oPic = true;
oImg1.onclick = function () {
    if (oPic){
        oImg1.src = "../images/order_img/up.svg";
        oPic = false;
        $(".date").show();
        document.querySelector(".hr1").style.display="none";
        document.querySelector(".order_confirm").style.height = "600px";
    }else{
        oImg1.src ="../images/order_img/down.svg";
        oPic = true;
        document.querySelector(".date").style.display="none";
        $(".hr1").show();
        document.querySelector(".order_confirm").style.height = "550px";
    }
}

var oImg = document.querySelector(".img");
var oCha = true;
oImg.onclick = function () {
    if (oCha){
        oImg.src = "../images/order_img/up.svg";
        oCha = false;
        $(".expenses").show();
        document.querySelector(".hr2").style.display="none";
        $(".hr3").show();
        document.querySelector(".order_confirm").style.height = "600px";
    }else{
        oImg.src ="../images/order_img/down.svg";
        oCha = true;
        document.querySelector(".expenses").style.display="none";
        document.querySelector(".order_confirm").style.height = "500px";
    }
}

//  优惠活动
$(".select").click(function () {
    $(".coupon").show();
    $(".new_user").hide();
})

$(".other_select").click(function () {
    $(".coupon").hide();
    $(".new_user").show();
})

$(".oth").click(function () {
    $(".new_user").hide();
    $(".coupon").hide();
})

$(".change_img").click(function () {

    $(".expenses").show();
})

//费用明细
var dayCount = document.querySelector(".day").innerText;
document.querySelector(".price_one").innerText= 275 * dayCount;
document.querySelector(".price_two").innerText= 50 * dayCount;
console.log(parseFloat(document.querySelector(".price_one").innerText))


//其它服务
 document.querySelector(".price_now1").innerText= 53 * dayCount;




//优享补充保障
document.querySelector(".other_s1").onclick = function () {
    $(".youzhi").show();
    document.querySelector(".word").innerText = "优享补充保障";
    document.querySelector(".p").innerText = document.querySelector(".price_now1").innerText;
    document.querySelector(".t_price").innerText =
        parseFloat(document.querySelector(".price_one").innerText) +
        parseFloat(document.querySelector(".price_two").innerText) +
        parseFloat(document.querySelector(".expenses_p").innerText) +
        parseFloat(document.querySelector(".price_now1").innerText);

}

//尊享补充保障
document.querySelector(".other_s2").onclick = function () {
    $(".youzhi").show();
    document.querySelector(".word").innerText = "尊享补充保障";
    document.querySelector(".p").innerText = document.querySelector(".price_now2").innerText;
    document.querySelector(".t_price").innerText =
        parseFloat(document.querySelector(".price_one").innerText) +
        parseFloat(document.querySelector(".price_two").innerText) +
        parseFloat(document.querySelector(".expenses_p").innerText) +
        parseFloat(document.querySelector(".price_now2").innerText);
}

//百万补充保障
document.querySelector(".other_s3").onclick = function () {
    $(".youzhi").show();
    document.querySelector(".word").innerText = "百万补充保障";
    document.querySelector(".p").innerText = document.querySelector(".price_now3").innerText;
    document.querySelector(".t_price").innerText =
        parseFloat(document.querySelector(".price_one").innerText) +
        parseFloat(document.querySelector(".price_two").innerText) +
        parseFloat(document.querySelector(".expenses_p").innerText) +
        parseFloat(document.querySelector(".price_now3").innerText);
}
