package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.SysMenuMapper;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.service.ISysMenuService;
import org.springframework.stereotype.Service;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
}
