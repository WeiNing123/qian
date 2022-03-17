package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.PasswordManagementMapper;
import com.wn.qian.modules.main.model.PasswordManagement;
import com.wn.qian.modules.main.service.IPasswordManagementService;
import org.springframework.stereotype.Service;

@Service
public class PasswordManagementServiceImpl extends ServiceImpl<PasswordManagementMapper, PasswordManagement> implements IPasswordManagementService {

    @Override
    public QueryWrapper<PasswordManagement> getQueryWrapper(PasswordManagement management) {
        QueryWrapper<PasswordManagement> wrapper = new QueryWrapper<>();
        wrapper.eq("creater", management.getCreater());
        if (management.getLabel()!=null && !management.getLabel().equals("")) {
            wrapper.like("label", management.getLabel());
        }
        wrapper.orderByAsc("id");
        return wrapper;
    }
}
