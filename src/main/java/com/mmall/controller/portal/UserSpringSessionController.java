package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/springsession/")
public class UserSpringSessionController {
    @Autowired
    private IUserService iUserService;


    @RequestMapping(value = "login.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> login(String username, String password, HttpSession session) {
       //测试全局异常
        int i =0;
        int b = 666/i;
        ServerResponse<User> serverResponse = iUserService.login(username, password);
        System.out.println("登录");
        if (serverResponse.isSuccess()) {
//            CookieUtil.writeLoginCookie(response, session.getId());
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
//            RedisShardedPoolUtil.setEx(session.getId(), JsonUtil.obj2String(serverResponse.getData()), Const.RedisCacheExTime.REDIS_SESSION_EXPIRE_TIME);
        }
        return serverResponse;

    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
//        String loginToken = CookieUtil.readLoginCookie(request);
//        CookieUtil.delLoginCookie(request, response);
//        RedisShardedPoolUtil.del(loginToken);
        return ServerResponse.createBySuccess();

    }

    @RequestMapping(value = "get_information.do", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session) {
//        String loginToken = CookieUtil.readLoginCookie(request);
        User user = (User) session.getAttribute(Const.CURRENT_USER);
//        if (StringUtils.isEmpty(loginToken)) {
//            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户信息");
//        }
//        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
//        User user = JsonUtil.string2Obj((String)session.getAttribute(Const.CURRENT_USER), User.class);
        if (user == null) {
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(), "未登录，需要强制登录status=10");
        }
        return iUserService.getInformation(user.getId());
    }
}
