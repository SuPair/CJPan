package com.whut.pan.util;

import com.whut.pan.domain.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sandeepin
 * 2018/2/12 0012
 */
public class WebUtil {

    public static String getSessionUserName(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        String userName = user.getUserName();
        if (userName == null) {
            userName = "null";
        }
        return userName;
    }
}