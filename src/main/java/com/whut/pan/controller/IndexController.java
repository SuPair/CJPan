package com.whut.pan.controller;

import com.whut.pan.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 * 主要页面映射
 * @author Sandeepin
 */
@Controller
public class IndexController {

    /**
     * 管理页面
     * @return 页面
     */
    @RequestMapping("/")
    public ModelAndView admin(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        String userName = user.getUserName();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("author", userName);
        return modelAndView;
    }

    /**
     * 测试页面
     * @return 页面
     */
    @RequestMapping("/test1")
    public String test(){
        return "test";
    }

    /**
     * 测试页面
     * @return 页面
     */
    @RequestMapping("/onlineplayer")
    public ModelAndView onlineplayer(HttpServletRequest request, String fileName, String filePath){
        User user = (User)request.getSession().getAttribute("user");
        String userName = user.getUserName();
        ModelAndView modelAndView = new ModelAndView("onlineplayer");
        modelAndView.addObject("author", userName);
        modelAndView.addObject("fileName", fileName);
        modelAndView.addObject("filePath", filePath);
        return modelAndView;
    }

}
