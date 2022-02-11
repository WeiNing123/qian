package com.wn.qian.modules.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wn.qian.framework.base.BaseController;
import com.wn.qian.modules.main.model.SysMenu;
import com.wn.qian.modules.main.service.ISysMenuService;
import com.wn.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/main/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/list")
    public IPage<SysMenu> list(@RequestBody SysMenu menu){
        Page<SysMenu> page = new Page<>(menu.getPage(), menu.getLimit());
        IPage<SysMenu> result = sysMenuService.page(page, sysMenuService.getQueryWrapper(menu));
        for (SysMenu sm : result.getRecords()) {
            menu.setParentId(sm.getId());
            List<SysMenu>la = sysMenuService.list(sysMenuService.getQueryWrapper(menu));
            if (la.size() > 0) {
                for (SysMenu me : la) {
                    menu.setParentId(me.getId());
                    List<SysMenu>lb = sysMenuService.list(sysMenuService.getQueryWrapper(menu));
                    if (lb.size() > 0) {
                        me.setChildren(lb);
                    }
                }
                sm.setChildren(la);
            }
        }
        return result;
    }

    @PostMapping("/tree")
    public R tree(@RequestBody SysMenu menu) {
        List<SysMenu>result = sysMenuService.list(sysMenuService.getQueryWrapper(menu));
        for (SysMenu sm : result) {
            menu.setParentId(sm.getId());
            List<SysMenu>la = sysMenuService.list(sysMenuService.getQueryWrapper(menu));
            if (la.size() > 0) {
                for (SysMenu me : la) {
                    menu.setParentId(me.getId());
                    List<SysMenu>lb = sysMenuService.list(sysMenuService.getQueryWrapper(menu));
                    if (lb.size() > 0) {
                        me.setChildren(lb);
                    }
                }
                sm.setChildren(la);
            }
        }
        Map<String, Object> js = new HashMap<>();
        List<Map<String, Object>> arr = new ArrayList<>();
        js.put("id", 0);
        js.put("label", "顶级");
        js.put("children", result);
        arr.add(js);
        return R.ok().put("data", arr);
    }

    @PostMapping("/add")
    public R add(@RequestBody SysMenu menu) {
        sysMenuService.save(menu);
        return R.ok();
    }

    @PostMapping("/edit")
    public R edit(@RequestBody SysMenu menu) {
        sysMenuService.updateById(menu);
        return R.ok();
    }

    @PostMapping("/delete")
    public R delete(@RequestBody SysMenu menu) {
        sysMenuService.removeById(menu.getId());
        return R.ok();
    }
}
