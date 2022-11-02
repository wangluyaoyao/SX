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
    document.querySelector(".price_one").innerText = 275 * dayCount;
    document.querySelector(".price_two").innerText = 50 * dayCount;

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
            headers:{'token':token},
            success:function (result) {
                var coupon1 = result.data;
                for (var i=0; i<coupon.length;i++){
                    var coupon = coupon[i];
                    var couPrice = coupon["couPrice"];
                    var couponTimeEnd = coupon["couponTimeEnd"];
                    var couExplain = coupon["couExplain"];
                }
                var couponEle = "<div class=\"coupon1\">\n" +
                    "                        <h1>￥<span class=\"cou_money\">"+couPrice+"</span></h1>\n" +
                    "                        <p>有效期至<span>"+couponTimeEnd+"</span></p>\n" +
                    "                        <div class=\"part_coupon1\">\n" +
                    "                            <h5>"+couExplain+"</h5>\n" +
                    "                        </div>\n" +
                    "                    </div>"
                $(".coupon").append(couponEle);

            }
        })

    }




