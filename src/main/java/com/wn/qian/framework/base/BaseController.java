package com.wn.qian.framework.base;

import com.wn.qian.framework.utils.RedisUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Data
public class BaseController {

    @Autowired
    private RedisUtil redisUtil;
}
