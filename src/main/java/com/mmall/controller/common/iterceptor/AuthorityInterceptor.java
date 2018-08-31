package com.mmall.controller.common.iterceptor;

import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.controller.backend.UserManagerController;
import com.mmall.controller.portal.UserController;
import com.mmall.pojo.User;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * @author jiangxl
 * @description
 * @date 2018-07-26 11:39
 **/
@Slf4j
public class AuthorityInterceptor implements HandlerInterceptor {
    /**
     * 进入controlle之前执行的方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("preHandle");
        //拿到请求中的controller中的方法名
        HandlerMethod handlerMethod = (HandlerMethod) o;
        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();
        StringBuffer requestParamBuffer = new StringBuffer();
        Map paramMap = httpServletRequest.getParameterMap();
        Iterator iterator = paramMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String mapKey = (String) entry.getKey();
            String mapValue = StringUtils.EMPTY;
            Object object = entry.getValue();
            if (object instanceof String[]) {
                String[] strObj = (String[]) object;
                mapValue = Arrays.toString(strObj);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);

        }
        if (StringUtils.equals(className, "UserManagerController")
                && StringUtils.equals(methodName, "login")) {
            log.info("权限拦截器拦截到请求，类名：{}，方法名：{}", className, methodName);
            return true;
        }
        User user = null;
        String loginToken = CookieUtil.readLoginCookie(httpServletRequest);
        if (StringUtils.isNotBlank(loginToken)) {
            String userJsonStr = RedisShardedPoolUtil.get(loginToken);
            user = JsonUtil.string2Obj(userJsonStr, User.class);
        }
        if (user == null || (user.getRole().intValue() != Const.Role.ROLE_ADMIN)) {
            // 不会执行controller的方法
            httpServletResponse.reset();//必须添加，否则会报getWriter has already been called for this response
            httpServletResponse.setCharacterEncoding("UTF-8");  //需要设置编码，否则会乱码
            httpServletResponse.setContentType("application/json;charset=utf-8");//设置返回值的类型，这里全部是json格式
            PrintWriter pw = httpServletResponse.getWriter();
            if (user == null) {
                if (StringUtils.equals(className, "ProductManageController")
                        && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "拦截器拦截，请登录管理员");
                    pw.write(JsonUtil.obj2String(resultMap));

                } else {
                    pw.write(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户未登录")));
                }
            } else {
                if (StringUtils.equals(className, "ProductManageController")
                        && StringUtils.equals(methodName, "richtextImgUpload")) {
                    Map resultMap = Maps.newHashMap();
                    resultMap.put("success", false);
                    resultMap.put("msg", "拦截器拦截，用户无权限操作");
                    pw.write(JsonUtil.obj2String(resultMap));
                } else {
                    pw.write(JsonUtil.obj2String(ServerResponse.createByErrorMessage("拦截器拦截，用户无权限操作")));

                }
            }
            pw.flush();
            pw.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion");
    }
}
