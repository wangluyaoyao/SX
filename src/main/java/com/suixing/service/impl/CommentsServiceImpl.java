package com.suixing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.entity.Comments;
import com.suixing.mapper.CommentsMapper;
import com.suixing.service.ICommentsService;
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

    @Override
    public List<Comments> listComments() {
        return null;
    }

    @Override
    public int saveComment(Comments comments) {
        return 0;
    }
}
