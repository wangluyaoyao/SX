package com.suixing.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suixing.entity.SxUser;
import com.suixing.mapper.SxUserMapper;
import com.suixing.service.ISxUserService;
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
public class SxUserServiceImpl extends ServiceImpl<SxUserMapper, SxUser> implements ISxUserService {

}
