package com.suixing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.commons.ServerResponse;
import com.suixing.entity.Comments;
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
    public ServerResponse getCommentsByCarId(Integer carId, Integer pageNum) {
        Sort sort = Sort.by(Sort.Direction.DESC,"DEVID").and(Sort.by(Sort.Direction.DESC,"comm_time"));
        return null;
    }
}
