package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Comments;
import com.suixing.entity.Reply;
import com.suixing.entity.User;
import com.suixing.mapper.CommentsMapper;
import com.suixing.mapper.ReplyMapper;
import com.suixing.mapper.UserMapper;
import com.suixing.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements IReplyService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Reply getReplyByCommId(Integer carId) {

        Comments comments = commentsMapper.selectById(carId);
        Integer commId = comments.getCommId();
        return replyMapper.selectById(commId);
    }

    @Override
    public User getUserByReplyId(Integer carId) {

        Comments comments = commentsMapper.selectById(carId);

        Integer userId = comments.getUserId();


        return userMapper.selectById(userId);
    }


}
