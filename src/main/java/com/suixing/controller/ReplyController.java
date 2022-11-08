package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.Reply;
import com.suixing.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@RestController
//@RequestMapping("/sx-reply")
public class ReplyController {

    @Autowired
    private IReplyService replyService;

    @PostMapping("savaReply")
    public ServerResponse saveReply(Reply reply){
        ServerResponse replyResponse = replyService.saveReply(reply);
        System.out.println("保存的回复为："+replyResponse);
        if (replyResponse.getResultcode() == 200){
            return ServerResponse.success("保存成功",replyResponse);
        }else {
            return ServerResponse.fail("保存失败",null);
        }

    }
}
