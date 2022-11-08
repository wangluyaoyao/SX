package com.suixing.controller;


import com.suixing.commons.ServerResponse;
import com.suixing.entity.Comments;
import com.suixing.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author smith
 * @since 2022-10-31
 */
@RestController
//@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private ICommentsService commentsService;
    @GetMapping("/details/{carId}/page/{pageNum}")
    public ServerResponse getUserDetail(@PathVariable("pageNum")Integer pageNum,@PathVariable("carId")Integer carId){
        if (pageNum == null)
            pageNum = 1;

        return commentsService.getCommentsByCarForPage(pageNum);
    }

    @GetMapping("/getComments/")
    public Comments getComments(Integer carId){
    return null;
    }
}
