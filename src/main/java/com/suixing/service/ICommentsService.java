package com.suixing.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.suixing.entity.Comments;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author smith
 * @since 2022-10-31
 */
public interface ICommentsService extends IService<Comments> {

    List<Comments> listComments();
    int saveComment(Comments comments);
}
