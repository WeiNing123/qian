package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.SysUserMapper;
import com.wn.qian.modules.main.model.SysUser;
import com.wn.qian.modules.main.service.ISysUserService;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Override
    public QueryWrapper<SysUser> getQueryWrapper(SysUser sysUser) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        if (sysUser.getUserCode()!=null && !sysUser.getUserCode().equals("")) wrapper.eq("user_code", sysUser.getUserCode());
        if (sysUser.getPassword()!=null && !sysUser.getPassword().equals("")) wrapper.eq("password", sysUser.getPassword());
        return wrapper;
    }
}
