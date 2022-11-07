//查询是否被点赞
//getLikeStatus();
function getLikeStatus(){
    var url = "/getLikeStatus";
    var token = localStorage.getItem("token");
    var carId = 1;
    $.ajax({
        type: "get",
        url: url,
        data:{
            'carId':carId,
        },
        headers: {'token': token},
        success: function (result) {
            console.log(result);
            var status = result['likeStatus'];
            var likeCount = result['likeCount'];
            console.log(status);
            console.log(likeCount);
            if (status !== 1){
                $("#like").attr("src","../images/like.png");
                $("#like").attr("class","like-img");
                console.log(111)
            }else {
                $("#like").attr("src","../images/haslike.png");
                $("#like").attr("class","haslike-img");
                console.log(222)
            }
            $(".like-count").text(likeCount);
        }
    })
}

//点赞
$(document).on( 'click',".like-img",function(event){
    var url = "/like";
    var token = localStorage.getItem("token");
    var ele = event.target;
    console.log(ele)
    var carId = ele.id;
    console.log(carId)
    console.log("点赞")
    $.ajax({
        type: "post",
        url: url,
        data:{
            'carId':carId,
        },
        headers: {'token': token},
        success: function (result) {
            console.log(result);
            $("#"+carId+"").attr("src","../images/haslike.png");
            $("#"+carId+"").attr("class","haslike-img");
            $(".like-count"+carId+"").text(result);
        }
    })
})


//取消点赞
$(document).on( 'click',".haslike-img",function(event){
    var url = "/dislike";
    var token = localStorage.getItem("token");
    var ele = event.target;
    console.log(ele)
    var carId = ele.id;
    console.log(carId)
    console.log("取消点赞")
    $.ajax({
        type: "post",
        url: url,
        data:{
            'carId':carId,
        },
        headers: {'token': token},
        success: function (result) {
            console.log(result);
            $("#"+carId+"").attr("src","../images/like.png");
            $("#"+carId+"").attr("class","like-img");
            $(".like-count"+carId+"").text(result);
        }
    })
})

