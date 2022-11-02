$(".comment-title").click(function (){
    var commentTitle = $(".comment-title").text();
    if(commentTitle == "显示评论"){
        $(".comment-title").text("关闭评论");
        getByPageNum(1);
        $(".replay-show-hiden").show();

    }else{
        $(".comment-title").text("显示评论");
        $(".replay-show-hiden").hide();
    }
});
function getByPageNum(pageNum) {
    var url;
    if(typeof(pageNum) == "number")
        url = "${pageContext.request.contextPath}/evaluate/${serverResponse.data.carId}/page/" + pageNum;
    else {
        url = "${pageContext.request.contextPath}/evaluate/${serverResponse.data.carId}/page";
        pageNum = 1;
    }
    $.get(url,null,
        function (result){
            $(".comment-ul").html("");
            if(result.code == 201){
                var liEle = '<h4>暂无评论</h4>';
                $(".comment-ul").append(liEle);
            }else{


                var infoArr =  result.data.list;
                for(var i = 0 ; i <infoArr.length;i++){
                    var custName = infoArr[i].customer.custName;
                    var evaluateStarts = parseInt(infoArr[i].evaluate.evaluateStarts);
                    var evaluateId = infoArr[i].evaluate.evaluateId;
                    var evaluateContent = infoArr[i].evaluate.evaluateContent;
                    var evaluateTime = infoArr[i].evaluate.evaluateTime;
                    var evaluatePraisedCount = infoArr[i].evaluate.evaluatePraisedCount;




                    var oneLiEle = '<li class="comment-li ' + evaluateId + '">\n' +
                        '                        <div class="comment-customer">\n' +
                        '                            <div class="customer-img">\n' +
                        '                                <img src="../images/details/用户.png">\n' +
                        '                            </div>\n' +
                        '                            <div class="a-link">\n' +
                        '                                <h5 class="customer-name">' + custName + '</h5>\n' +
                        '                                <span class="type-text">' + evaluateContent + '</span>\n' +
                        '                                <span class="stars-show"></span>'+
                        '                                <div class="comment-handle">\n' +
                        '                                    <span class="evaluate-time">' + evaluateTime + '</span>\n' +
                        '                                    <a class="replay-handle" href="">回复</a>\n' +
                        '                                    <span>|</span>\n' +
                        '                                    <a href="javascript:void(0)">\n' +
                        '                                    <span style="display:none;" class="evaluate-id">' + evaluateId + '</span>\n' +
                        '                                   <span style="display:none;">0</span>' +
                        '                                        <span id = "praisedStatus' + evaluateId + '" class="icon-act"></span>\n' +
                        '                                    </a>\n' +
                        '                                    <span  class="praise-number">' + evaluatePraisedCount + '</span>\n' +
                        '                                </div>\n' +
                        '\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                        <ul class="replay-ul">\n' +
                        '                        </ul>\n' +
                        '                    </li>';
                    $(".comment-ul").append(oneLiEle);
                    var ulNewClass = "."+evaluateId;
                    var starsPostion = "0 ";
                    if (evaluateStarts == 5) {
                        starsPostion += "0";
                    }else{
                        starsPostion +=-(176-((evaluateStarts-1)*44))+"px";
                    }

                    var custId = "${loginCustomer.custId}";
                    if(custId != "") {
                        var url = "${pageContext.request.contextPath}/evaluate/praised";
                        $.get(url, {
                                evaluateId: evaluateId,
                                custId: custId
                            }, function (result) {
                                if(result.code == 200) {
                                    var evaluateId = result.data.evaluateId;
                                    var praisedId = "#praisedStatus" + evaluateId;
                                    var status = result.data.status;
                                    if (status == 0) {
                                        document.querySelector(praisedId).style.background = "url(../images/details/用户.png)"
                                        document.querySelector(praisedId).parentElement.children[1].innerText = 1;
                                    }
                                }
                            }
                            , "json");
                    }
                    // console.log(starsPostion);
                    // if(evaluateStarts == 5){
                    //     starsPostion += "0";
                    // }else if(evaluateStarts == 4){
                    //     starsPostion += "-44px";
                    // }else if(evaluateStarts == 3){
                    //     starsPostion += "-88px";
                    // }else if(evaluateStarts == 2){
                    //     starsPostion += "-132px";
                    // }else if(evaluateStarts == 1){
                    //     starsPostion += "-176px";
                    // }

                    $(ulNewClass+" .stars-show").css(
                        "background-position",starsPostion
                    )

                    var customerReplyVOArr = infoArr[i].customerReplyVOList;
                    for(var j = 0; j < customerReplyVOArr.length;j++) {
                        var replyCustName = customerReplyVOArr[j].customer.custName;
                        var replyContext = customerReplyVOArr[j].reply.replyContent;
                        var replyTime = customerReplyVOArr[j].reply.replyTime;

                        var twoLiEle = ' <li>\n' +
                            '                                <div class="replay-customer">\n' +
                            '                                    <div class="replay-img">\n' +
                            '                                        <img src="../images/details/用户.png">\n' +
                            '                                    </div>\n' +
                            '                                    <h6 class="replay-customer-name">' + replyCustName + '</h6>\n' +
                            '                                    <!--                            <h6>Smith</h6>-->\n' +
                            '                                    <span class="replay-text">' + replyContext + '</span>\n' +
                            '                                    <span class="replay-time">' + replyTime + '</span>\n' +
                            '                                </div>\n' +
                            '                            </li>';
                        $(ulNewClass+" .replay-ul").append(twoLiEle);
                    }


                }
                /* var pageNum = result.data.pageNum;
                 var pages = result.data.pages;
                 var prePage = result.data.prePage;
                 var nextPage = result.data.nextPage;
                 var hasPreviousPage = result.data.hasPreviousPage;
                 var hasNextPage = result.data.hasNextPage;
                 var total = result.data.total;
                 var pageEle = '';
                 '<li>\n' +
                 '                            共有<span>56</span>条评价\n' +
                 '                            第<span>1</span>页\n' +
                 '                            <a href="">上一页</a>\n' +
                 '                            <a href="">下一页</a>\n' +
                 '                            共有<span>5</span>页\n' +
                 '                        </li>'
                 pageEle +='<li>\n' +
                     '                            共有<span>' + total + '</span>条评价\n';

                 pageEle += '                            第<span>' + pageNum + '</span>页\n';
                 if(hasPreviousPage)
                     pageEle += '                            <a href="javascript:getByPageNum(' + prePage + ')">上一页</a>\n';
                 if(hasNextPage)
                     pageEle += '                            <a href="javascript:getByPageNum(' + nextPage + ')">下一页</a>\n';
                 pageEle += '                            共有<span>' + pages + '</span>页\n' ;
                 $(".comment-ul").append(pageEle);*/
            }

        }
        ,"json");


    $(".replay-show-hiden").click(function (event){
        var target = event.target;
        var custId = "${loginCustomer.custId}";

        if(custId == ""){
            window.location.href = "${pageContext.request.contextPath}/customer/login";
        }
        var text = target.parentElement.parentElement.children[1].textContent;
        if(text == "回复"){
            var status = target.parentElement.children[1].textContent;
            if(status == 0) {
                target.parentElement.children[1].innerText = "1";
                target.style.background = "url(http://r1nxp2i0y.hn-bkt.clouddn.com/images/praised_icon-act.svg)"

            }else {
                target.parentElement.children[1].innerText = "0";
                target.style.background = "url(http://r1nxp2i0y.hn-bkt.clouddn.com/images/praise_icon_act.svg)"
            }
            evaluateId = target.parentElement.firstElementChild.textContent;

            var url = "${pageContext.request.contextPath}/evaluate/"+evaluateId+'/'+custId+'/'+status;
            $.ajax({
                type:"put",
                url,
                dataType:"json",
                success:function (result) {
                    target.parentElement.parentElement.lastElementChild.innerText=result.data;
                    var liClass = "."+evaluateId;
                    $(liClass+ " .praise-number").text();
                }
            });
        }

    })

}

showUser();
function showUser() {

    var url = "/details/user";
    var token = localStorage.getItem("token");
    console.log(token);

    $.ajax({
        type:"get",
        url:url,
        headers:{'token':token},
        success:function (result) {
            var user = result.data;

        }
    })

}
