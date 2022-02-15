package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.MyDatabaseMapper;
import com.wn.qian.modules.main.model.MyDatabase;
import com.wn.qian.modules.main.service.IMyDatabaseService;
import org.springframework.stereotype.Service;

@Service
public class MyDatabaseServiceImpl extends ServiceImpl<MyDatabaseMapper, MyDatabase> implements IMyDatabaseService {
    @Override
    public QueryWrapper<MyDatabase> getQueryWrapper(MyDatabase database) {
        QueryWrapper<MyDatabase> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        return wrapper;
    }
}
