package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.PasswordManagement;
import com.wn.qian.modules.main.service.IPasswordManagementService;
import com.wn.utils.CryptoUtil;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main/passwordManagement")
public class PasswordManagementController extends BaseController {

    @Autowired
    private IPasswordManagementService passwordManagementService;

    @PostMapping("/list")
    public IPage<PasswordManagement> list(@RequestBody PasswordManagement management){
        management.setCreater((String) getRedisUtil().get("user_code"));
        Page<PasswordManagement> page = new Page<>(management.getPage(), management.getLimit());
        IPage<PasswordManagement> result = passwordManagementService.page(page, passwordManagementService.getQueryWrapper(management));
        for (PasswordManagement pm : result.getRecords()) {
            pm.setPassword(CryptoUtil.decodeTarget(pm.getPassword()));
        }
        return result;
    }

    @PostMapping("/add")
    public R add(@RequestBody PasswordManagement management){
        management.setCreater((String) getRedisUtil().get("user_code"));
        management.setPassword(CryptoUtil.encodeSrc(management.getPassword()));
        passwordManagementService.save(management);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody PasswordManagement management){
        management.setPassword(CryptoUtil.encodeSrc(management.getPassword()));
        passwordManagementService.updateById(management);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody PasswordManagement management){
        passwordManagementService.removeById(management.getId());
        return R.ok();
    }
}
