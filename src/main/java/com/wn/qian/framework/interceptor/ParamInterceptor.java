package com.wn.qian.framework.interceptor;

import com.wn.qian.framework.config.PathVerifyConfig;
import com.wn.utils.CryptoUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Slf4j
@Component
public class ParamInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod()))
            return true;

        if (PathVerifyConfig.laissezPasser(request.getRequestURI()))
            return true;

        JSONObject json = new JSONObject();
        String token = request.getHeader("Access-Token");

        if (token == null){
            json.put("code", 302);
            json.put("msg", "用户信息验证失败,无效token");
            returnJson(response, json.toString());
            return false;
        }

        String decryptStr = CryptoUtil.decodeTarget(token);
        long time = Long.valueOf(decryptStr.split("&@&")[1]);
        long sjc = new Date().getTime()/1000 - time;

        if(sjc > 7200){
            json.put("code", 303);
            json.put("msg", "登录超时");
            returnJson(response, json.toString());
            return false;
        }

        return true;
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) throws Exception {
    }
}
