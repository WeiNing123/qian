package com.wn.qian.modules.main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wn.qian.modules.main.mapper.ServerMapper;
import com.wn.qian.modules.main.model.Server;
import com.wn.qian.modules.main.service.IServerService;
import org.springframework.stereotype.Service;

@Service
public class ServerServiceImpl extends ServiceImpl<ServerMapper, Server> implements IServerService {
    @Override
    public QueryWrapper<Server> getQueryWrapper(Server server) {
        QueryWrapper<Server> wrapper = new QueryWrapper<>();
        wrapper.eq("creater", server.getCreater());
        if (server.getLabel()!=null && !server.getLabel().equals("")) {
            wrapper.like("label", server.getLabel());
        }
        wrapper.orderByAsc("id");
        return wrapper;
    }
}
