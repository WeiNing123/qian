package com.wn.qian.modules.main.controller;

import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.service.ISysMenuService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/main/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/list")
    public R list(){
        List<SysMenu> result = sysMenuService.list(null);
        return R.ok().put("data", result);
    }
}
