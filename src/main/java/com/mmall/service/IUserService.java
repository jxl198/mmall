package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;

import java.rmi.ServerError;

/**
 * @author jiangxl
 */
public interface IUserService {
        public ServerResponse<User> login(String username, String password);
        public ServerResponse<String> register(User user);
        public ServerResponse<String> checkValid(String str, String type);
        public ServerResponse<String> selectQuestion(String  username);
        public ServerResponse<String> checkAnswer(String username,String question,String answer);
        public ServerResponse<String> forgetResetPassword(String username, String passwordNew, String forgetToken);
        public ServerResponse<String> resetPassword(String passwordOld,String passwordNew,User user);
        public ServerResponse<User> updateInformation(User user);
        public ServerResponse<User> getInformation(Integer userId);

        //backend
        public ServerResponse<String> checkAdminRole(User user);
}
