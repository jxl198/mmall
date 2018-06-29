package com.mmall.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-29 10:27
 **/
@Slf4j
public class CookieUtil {
    private static final String COOKIE_DOMAIN = ".happymmall.com";
    private static final String COOKIE_NAME = "mmall_login_token";

    /**
     * 写入cookie
     *
     * @param response
     * @param token
     */
    public static void writeLoginCookie(HttpServletResponse response, String token) {
        Cookie ck = new Cookie(COOKIE_NAME, token);
        ck.setDomain(COOKIE_DOMAIN);
        ck.setPath("/");  //代表设置在根目录
        //如果maxage不设置，cookie不会写入硬盘，只会写入内存，只在当前页面有效
        ck.setMaxAge(60 * 60 * 24 * 365); //-1 代表永久 单位是秒
        log.info("write cookieName:{},cookieValue:{},", ck.getName(), ck.getValue());
        response.addCookie(ck);

    }

    public static String readLoginCookie(HttpServletRequest request) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                log.info("cookieName:{},cookieValue:{}", ck.getName(), ck.getValue());
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    log.info("return cookieName：{}，cookieValue:{}", ck.getName(), ck.getValue());
                    return ck.getValue();

                }
            }
        }
        return null;
    }


    public static void delLoginCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cks = request.getCookies();
        if (cks != null) {
            for (Cookie ck : cks) {
                if (StringUtils.equals(ck.getName(), COOKIE_NAME)) {
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);//代表删除cookie
                    log.info("del cookieName：{}，cookieValue:{}", ck.getName(), ck.getValue());
                    response.addCookie(ck);
                    return ;
                }
            }
        }

    }
}
