package com.example.mybatisSign.controllers;

import com.example.mybatisSign.models.User;
import com.example.mybatisSign.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    private UserService _userService;

    @RequestMapping("/")
    public ModelAndView Index(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/User/Index");
        //初始化Index页面需要的变量
        model.addAttribute("User", new User());
        model.addAttribute("LoginMessage", "");

        return mav;
    }

    @RequestMapping(value = "/Login/DoLogin", method = RequestMethod.POST)
    public ModelAndView DoLogin(@ModelAttribute User User, Model model, @RequestParam(name = "btn") String btn) {
        ModelAndView mav = new ModelAndView();
        model.addAttribute("User", new User());
        model.addAttribute("LoginMessage", "");

        //先确定点击的按钮
        if (btn.equals("Register")) {
            mav.setViewName("views/User/Register");
        }
        else {
            String username = User.getUsername();
            String password = User.getPassword();
            if (username.equals("") || password.equals("")) {
                model.addAttribute("LoginMessage", "用户名或密码不能为空");
                mav.setViewName("views/User/Index");
            }
            else {
                User user = this._userService.GetUserByUsernameAndPassword(username, password);
                if (user == null) {
                    model.addAttribute("LoginMessage", "用户名或密码错误");
                    mav.setViewName("views/User/Index");
                }
                else {
                    mav.setViewName("views/User/Success");
                }
            }
        }

        return mav;
    }

    //初始化注册页面
    @RequestMapping("/User/Register")
    public ModelAndView Register(Model model) {
        model.addAttribute("username", "");
        model.addAttribute("RegisterMessage", "");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("views/User/Register");
        return mav;
    }

    //注册操作
    @RequestMapping(value = "/Register/DoRegister", method = RequestMethod.POST)
    public ModelAndView DoRegister(Model model,
                                   @RequestParam(name = "username") String username,
                                   @RequestParam(name = "password") String password,
                                   @RequestParam(name = "repeatPassword") String repeatPassword) {
        ModelAndView mav = new ModelAndView();
        if (username.equals("") || password.equals("") || repeatPassword.equals("")) {
            model.addAttribute("RegisterMessage", "用户名、密码或重复密码不能为空");
            mav.setViewName("views/User/Register");
        } else {
            if (!password.equals(repeatPassword)) {
                model.addAttribute("RegisterMessage", "两次密码不一致");
                model.addAttribute("username", username);
                mav.setViewName("views/User/Register");
            } else if (null != this._userService.GetUserByUsername(username)) {
                model.addAttribute("RegisterMessage", "用户已存在");
                mav.setViewName("views/User/Register");
            } else {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                this._userService.Save(user);
                model.addAttribute("User", new User());
                model.addAttribute("LoginMessage", "");
                mav.setViewName("views/User/Index");
            }
        }

        return mav;
    }
}
