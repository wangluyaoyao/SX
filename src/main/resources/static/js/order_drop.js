
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





