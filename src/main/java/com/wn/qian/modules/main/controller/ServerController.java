package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.Server;
import com.wn.qian.modules.main.service.IServerService;
import com.wn.utils.CryptoUtil;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/server")
public class ServerController extends BaseController {

    @Autowired
    private IServerService serverService;

    @PostMapping("/list")
    public IPage<Server> list(@RequestBody Server server){
        Page<Server> page = new Page<>(server.getPage(), server.getLimit());
        IPage<Server> result = serverService.page(page, serverService.getQueryWrapper(server));
        for (Server item : result.getRecords()) {
            item.setPassword(CryptoUtil.decodeTarget(item.getPassword()));
        }
        return result;
    }

    @PostMapping("/add")
    public R add(@RequestBody Server server){
        server.setPassword(CryptoUtil.encodeSrc(server.getPassword()));
        serverService.save(server);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody Server server){
        server.setPassword(CryptoUtil.encodeSrc(server.getPassword()));
        serverService.updateById(server);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody Server server){
        serverService.removeById(server.getId());
        return R.ok();
    }
}
