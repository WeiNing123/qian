package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.MyDatabase;
import com.wn.qian.modules.main.service.IMyDatabaseService;
import com.wn.utils.CryptoUtil;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/database")
public class MyDatabaseController extends BaseController {

    @Autowired
    private IMyDatabaseService myDatabaseService;

    @PostMapping("/list")
    public IPage<MyDatabase> list(@RequestBody MyDatabase database){
        database.setCreater((String) getRedisUtil().get("user_code"));
        Page<MyDatabase> page = new Page<>(database.getPage(), database.getLimit());
        IPage<MyDatabase> result = myDatabaseService.page(page, myDatabaseService.getQueryWrapper(database));
        for (MyDatabase item : result.getRecords()) {
            item.setPassword(CryptoUtil.decodeTarget(item.getPassword()));
        }
        return result;
    }

    @PostMapping("/add")
    public R add(@RequestBody MyDatabase database){
        database.setCreater((String) getRedisUtil().get("user_code"));
        database.setPassword(CryptoUtil.encodeSrc(database.getPassword()));
        myDatabaseService.save(database);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody MyDatabase database){
        database.setPassword(CryptoUtil.encodeSrc(database.getPassword()));
        myDatabaseService.updateById(database);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody MyDatabase database){
        myDatabaseService.removeById(database.getId());
        return R.ok();
    }
}
