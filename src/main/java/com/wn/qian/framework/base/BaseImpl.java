package com.wn.qian.framework.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface BaseImpl<T> {

    QueryWrapper<T> getQueryWrapper(T t);
}
