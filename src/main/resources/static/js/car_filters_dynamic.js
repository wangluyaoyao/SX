
//根据页数查询
function getByPage(pageNum){
    $(".shopsInFor").html(" ")
    var url = "${pageContext.request.contextPath}/getByPage/"+pageNum;
    $.get(url,function (shops) {
        console.log(shops.data)
        var pageInfo =  shops.data;
        var shopArray =  shops.data.records;
        console.log(shopArray.length)
        console.log(shopArray.shopName)
        for (var i =0 ; i<shopArray.length;i++){
            var shop = shopArray[i];
            console.log(shop);
            var shopName = shop.shopName;
            var shopScore = shop.shopScore;
            var shopAddress = shop.shopAddrProvince+shop.shopAddrCity+shop.shopAddrArea+shop.shopAddrDetail;
            var shopTelno = shop.shopTelno;
            var tel =
            $(".shopsInFor").html($(".shopsInFor").html()+tel)
        }
        $(".shopsInFor").show();
        //页码信息
        var pageInformation = " <div class=\"page-information\">\n" ;
        var prePage = pageInfo.current-1;
        var nextPage = pageInfo.current+1;
        // var hrefStr = "javascript:getByPage("+prePage+")";

        if(pageInfo.size == 0){
            getByPage(pageInfo.pageNum-1)
        }
        if(pageInfo.current > 1)
            pageInformation+= "  <a href= 'javascript:getByPage("+prePage+")'>上一页</a>\n" ;
        pageInformation+= "  当前是<span>"+pageInfo.current +" </span>页\n" ;
        if(pageInfo.total>3 )
            pageInformation+= " <a href='javascript:getByPage("+nextPage+")'>下一页</a>\n" ;
        pageInformation +="  共有<span>"+pageInfo.total +"</span>条记录，共有<span>"+pageInfo.pages +"</span>页\n" +
            "   </div>";

        $(".shopsInFor").append(pageInformation);
    })
}

// 删除指定ID
// $(".deleteById").click(function (){
//     deleteById
//
// })

function deleteById(inShopId,pageNum){
    $.post("deleteById",{shopId:inShopId},function(result){
        console.log(result.data)
    })
    getByPage(pageNum);
}
$(".addShop").click(function() {
    var shopAddrProvince = $(".shopAddrProvince").val();
    var shopAddrCity = $(".shopAddrCity").val();
    var shopAddrArea = $(".shopAddrArea").val();
    var shopAddrDetail = $(".shopAddrDetail").val();
    var shopName = $(".shopName").val();
    var shopTelno = $(".shopTelno").val();
    console.log(shopTelno)
    $.ajax({
        type: "POST",
        url: "${pageContext.request.contextPath}/saveShop",
        data:{
            shopAddrProvince: shopAddrProvince,
            shopAddrCity: shopAddrCity,
            shopAddrArea: shopAddrArea,
            shopAddrDetail: shopAddrDetail,
            shopName: shopName,
            shopTelno: shopTelno
        },
        success:function (result) {   //     success含义:if (xhr.readyState == 4 && xhr.status == 200){
            console.log(result.data) //data :服务端的响应数据
            // if (data) {
            //     console.log("success")
            //     $(".insertState").text("添加成功")
            //     // document.querySelector(".insertState").innerText = "添加成功"
            // } else {
            //     console.log("fail")
            //     $(".insertState").text("添加失败")
            //     // document.querySelector(".insertState").innerText = "添加失败"
            // }
        }
    })
})