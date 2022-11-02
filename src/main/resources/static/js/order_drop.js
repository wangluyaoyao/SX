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

//优惠券
    $(document).ready(function () {
        if ($(".oth").click(function () {
            document.querySelector(".t_price").innerText =
                parseFloat(document.querySelector(".price_one").innerText) +
                parseFloat(document.querySelector(".price_two").innerText) +
                parseFloat(document.querySelector(".expenses_p").innerText) ;
        }))
        $(".coupon1").click(function () {
            $(".coupon1").css({"background": "lightgrey"});

            $(this).css({"background": "orange"});
            var cou_money = $(this).find(".cou_money").text();
            var couId = $(this).find(".couId").val();
            console.log(couId)
            $("#couId").val(couId);
            document.querySelector(".t_price").innerText =
                parseFloat(document.querySelector(".price_one").innerText) +
                parseFloat(document.querySelector(".price_two").innerText) +
                parseFloat(document.querySelector(".expenses_p").innerText) -
                cou_money;
        });
    })


select();
    function select() {
        var url = "/coupon";
        var token = localStorage.getItem("token");
        console.log(token);

        $.ajax({
            type:"get",
            url:url,
            async:false,
            headers:{'token':token},
            success:function (result) {
                var coupon = result.data;
                var userId = coupon[0].userId;
                console.log(coupon)
                console.log(userId)
                $("#userId").val(userId);
                for (var i=0; i<coupon.length;i++) {
                    var coupon1 = coupon[i];
                    var couId = coupon1["couId"];
                    var couPrice = coupon1["couPrice"];
                    var couEnd = coupon1["couEnd"];
                    var couExplain = coupon1["couExplain"];

                    var couponEle = "<div class=\"coupon1\">\n" +
                        "<input type=\"hidden\" value=\" " + couId + " \" name=\"couId\" class=\" couId \">" +
                        "                        <h1>￥<span class=\"cou_money\">" + couPrice + "</span></h1>\n" +
                        "                        <p>有效期至<span>" + couEnd + "</span></p>\n" +
                        "                        <div class=\"part_coupon1\">\n" +
                        "                            <h5>" + couExplain + "</h5>\n" +
                        "                        </div>\n" +
                        "                    </div>"
                    $(".coupon").append(couponEle);
                }
            }
        })

    }

// var s = $("#userId").val()
// console.log(s)

console.log($("#couId").val())

/*$(".jump").click(function (){
    a_();
})*/
// a_();
//     function a_() {
//         var url = "/saveOrder";
//         var token = localStorage.getItem("token");
//         console.log(token);
//
//         $.ajax({
//             type:"post",
//             url:url,
//             headers:{'token':token},
//             success:function (){
//                 console.log(1155555555)
//             }
//
//         })
//
//     }


