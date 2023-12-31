package com.xxxx.crm.interceptors;

import com.xxxx.crm.exceptions.NoLoginException;
import com.xxxx.crm.service.UserService;
import com.xxxx.crm.utils.LoginUserUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 非法请问拦截
 * */
public class NoLoginInterceptor extends HandlerInterceptorAdapter {
    @Resource
    private UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取cookie中的用户ID
        Integer userId = LoginUserUtil.releaseUserIdFromCookie(request);
        //判断用户ID是否不为空，且数据库中的存在对应的用户记录
        if(null == userId || null == userService.selectByPrimaryKey(userId)){
            //抛出未登录异常
            throw new NoLoginException();
        }
       return true;
    }
}
