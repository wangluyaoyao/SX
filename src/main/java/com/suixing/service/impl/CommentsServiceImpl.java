package com.suixing.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Comments;
import com.suixing.entity.Reply;
import com.suixing.entity.User;
import com.suixing.mapper.CommentsMapper;
import com.suixing.mapper.ReplyMapper;
import com.suixing.mapper.UserMapper;
import com.suixing.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author smith
 * @since 2022-10-31
 */
@Service
public class CommentsServiceImpl extends ServiceImpl<CommentsMapper, Comments> implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private ReplyMapper replyMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Comments getCommentsByCarId(Integer carId) {
//        Page<Comments> page = PageHelper.startPage(pageNum,5);                // 分页
//        PageInfo pageInfo = page.toPageInfo();
//        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
//        Page<Comments> commentsPage = new Page<>(pageNum,5);
//        Page<Comments> pageInfo = commentsMapper.selectPage(commentsPage,queryWrapper);

        //        Reply replyId = replyMapper.selectById(commId);



        return commentsMapper.selectById(carId);

    }

    @Override
    public ServerResponse getCommentsByCarForPage(Integer pageNum) {
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        System.out.println("当前查询的页数"+pageNum);
        Page<Comments> commentsPage = new Page<>(pageNum,5);
        Page<Comments> pageInfo = commentsMapper.selectPage(commentsPage,queryWrapper);
        System.out.println(pageInfo.getRecords());
        if (pageInfo.getRecords() !=null){
            return ServerResponse.success("查询成功",pageInfo);
        }else {
            return ServerResponse.fail("查询失败",null);
        }
    }

    @Override
    public User getUserByCommId(Integer carId) {
        Comments comments = commentsMapper.selectById(carId);
        Integer userId = comments.getUserId();
        return userMapper.selectById(userId);
    }


}
