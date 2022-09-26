//翻页操作
function trunPage(obj){
    var page = $(obj).attr('data-external');
    var type = $('p.switch-head-line').find('span.chooseActive').attr('eval_data');	//获取评价类型
    getEvalHtml(page, type);	//元素追加
}

//选择操作
function chooseEval(obj){
    // 点击高亮
    $(obj).parent().find('span.chooseActive').removeClass('chooseActive');
    $(obj).addClass('chooseActive');

    // 点击评价时内容切换
    getEvalHtml(1, $(obj).attr('eval_data'));
}

//为了模拟效果，直接写一些固定数据。
function getEvalHtml(page, type){
    $('ul#user_evaluate').empty();	//元素清空
    $('div.switch-tag-wrap').empty();
    $('span#tempNotEva').empty();

    //处理评论 START
    var data = getDataByParam(page, type);
    var dataList = data.list;
    if(dataList!=''){	//数据拼接
        var liHtml = '';
        $.each(dataList, function(key, val){
            liHtml +='<li class="evaluate_li">'+
                '<span class="img">'+
                '<img src="' + val['photo'] + '" alt="" />'+
                '</span>'+
                '<span class="info">'+
                '<h4>' + val['name'] + '</h4>'+
                '<p>' + val['content'] + '</p>'+
                '<p class="time">' + val['createTime'] + '</p>';
            if(val['reply']){
                liHtml += '<div class="reply_info"> 回复：' + val['reply'] + ' </div>';
            }
            liHtml += '</span></li>';
        });
        //console.log(liHtml);
        $('ul#user_evaluate').append(liHtml);	//评论数据追加
        //处理评论END

        //处理分页 START
        var pageHtml = '';
        if(data['totalPage']>1){
            if(data['page']!=data['totalPage']){//当前页不是最后一页?>
                pageHtml +='<div class="js-switch next  fr" data-external="'+ (data['page']*1+1) +'" onclick="trunPage(this)">下一页</div>';
            }
            if(data['page']!=1){//当前页不是第一页?>
                pageHtml +='<div class="js-switch prev fr" data-external="'+ (data['page']*1-1) +'" onclick="trunPage(this)">上一页</div>';
            }
            if(data['totalPage']!=1 && data['page']!=1){//总页数不是1页，并且当前页不是第一页?>
                pageHtml +='<div class="js-switch first-page fr" data-external="1" onclick="trunPage(this)">首页</div>';
            }
            $('div.switch-tag-wrap').append(pageHtml);	//页数追加
        }
        //处理分页END
    }else{
        $('ul#user_evaluate').text('暂无评价！！！');
    }
}

//根据页数，类型获取模拟数据
function getDataByParam(page, type){
    var str = '';
    var photo = 'https://avatar.csdn.net/1/4/1/3_qq_36025814.jpg';
    if(type==1){
        str = 	'{"page":"'+page+'",'+
            '"totalPage":3,'+
            '"list":['+
            '{"content":"你好，这是好评(第'+page+'页)","createTime":"2019-04-27 11:52","name":"走起","photo":"'+photo+'","reply":""},'+
            '{"content":"商品很实用(第'+page+'页)","createTime":"2019-04-28 11:53","name":"lrfc","photo":"'+photo+'","reply":"谢谢您的评价(第'+page+'页)"}'+
            ']}';
    }else if(type==2){
        str = 	'{"page":"'+page+'",'+
            '"totalPage":0,'+
            '"list":[]}';
    }else if(type==3){
        str = 	'{"page":"'+page+'",'+
            '"totalPage":1,'+
            '"list":['+
            '{"content":"这个笔芯掉色","createTime":"2019-04-27 11:52","name":"走起","photo":"'+photo+'","reply":""}'+
            ']}';
    }
    return  JSON.parse(str);
}

//调后台接口动态获取数据
/*
function getEvalHtml(page, type){
    $.ajax({
        url:'/index/index/get',
        type:'post',
        dataType:'json',
        data:{page:page, type:type},
        success:function(data){
            if(data.code==1){
                $('ul#user_evaluate').empty();	//元素清空
                $('div.switch-tag-wrap').empty();
                $('span#tempNotEva').empty();
                //处理评论 START
                var data = data.data;
                var dataList = data.list;
                if(dataList!=''){
                    var liHtml = '';
                    $.each(dataList, function(key, val){
                        liHtml +='<li class="evaluate_li">'+
                                     '<span class="img">'+
                                    '<img src="' + val['photo'] + '" alt="" />'+
                                 '</span>'+
                                 '<span class="info">'+
                                     '<h4>' + val['name'] + '</h4>'+
                                     '<p>' + val['content'] + '</p>'+
                                     '<p class="time">' + val['createTime'] + '</p>';
                        if(val['reply']){
                            liHtml += '<div class="reply_info"> 回复：' + val['reply'] + ' </div>';
                        }
                        liHtml += '</span></li>';
                    });
                    //console.log(liHtml);
                    $('ul#user_evaluate').append(liHtml);	//评论数据追加
                    //处理评论END
                    //处理分页 START
                    var pageHtml = '';
                    if(data['totalPage']>1){
                        if(data['page']!=data['totalPage']){//当前页不是最后一页?>
                            pageHtml +='<div class="js-switch next  fr" data-external="'+ (data['page']*1+1) +'" onclick="trunPage(this)">下一页</div>';
                        }
                        if(data['page']!=1){//当前页不是第一页?>
                            pageHtml +='<div class="js-switch prev fr" data-external="'+ (data['page']*1-1) +'" onclick="trunPage(this)">上一页</div>';
                        }
                        if(data['totalPage']!=1 && data['page']!=1){//总页数不是1页，并且当前页不是第一页?>
                            pageHtml +='<div class="js-switch first-page fr" data-external="1" onclick="trunPage(this)">首页</div>';
                        }
                        $('div.switch-tag-wrap').append(pageHtml);	//页数追加
                    }
                    //处理分页END
                }else{
                    //$('ul#user_evaluate').text('暂无评价！！！');
                }
            }else{
                alert(data.msg);
            }
        },
        error:function(){
            alert('服务器异常！');
        }
    });
}*/
