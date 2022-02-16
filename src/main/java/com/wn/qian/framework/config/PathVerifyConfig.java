package com.wn.qian.framework.config;

import java.util.ArrayList;
import java.util.List;

public class PathVerifyConfig {

    /**
     * 无需验证直接放行路径
     * @return
     */
    private static List<String> release(){
        List<String>url = new ArrayList<>();
        url.add("/auth/");
        url.add("/main/materials/upload");
        url.add("/upfile/");
        return url;
    }

    /**
     * 是否免验证
     * @param url
     * @return
     */
    public static boolean laissezPasser(String url){
        boolean or = false;
        List<String> paths = release();
        for (String path : paths) {
            if (url.indexOf(path) != -1) {
                or = true;
                break;
            }
        }
        return or;
    }
}
