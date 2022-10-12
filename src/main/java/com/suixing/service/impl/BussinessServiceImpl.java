package com.suixing.service.impl;

import com.suixing.commons.ServerResponse;
import com.suixing.entity.Bussiness;
import com.suixing.mapper.BussinessMapper;
import com.suixing.service.IBussinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2022-10-03
 */
@Service
public class BussinessServiceImpl  implements IBussinessService {

    @Autowired
    private BussinessMapper bussinessMapper;
    @Override
    public ServerResponse getAll() {
        List<Bussiness> list = bussinessMapper.selectList(null);
        if (list != null){
            return ServerResponse.success("查询成功",list);
        }
        return ServerResponse.fail("查询失败",null);

    }
}
