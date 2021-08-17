package com.jincheng.customer.contoller;

import com.jincheng.customer.bean.CommonResult;
import com.jincheng.customer.bean.User;
import com.jincheng.customer.service.UserService;
import com.jincheng.customer.service.emailService;
import com.jincheng.customer.service.registerService;
import com.jincheng.customer.service.vercodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private emailService emailService;
    @Autowired
    private registerService registerService;
    @Autowired
    private vercodeService vercodeService;
//    @Autowired
//    private HttpServletRequest request;


    @PostMapping("/sublogin")
    public String sublogin(String username, String password, String vercode, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String chekcode = (String) session.getAttribute("vercode");
        System.out.println(chekcode);
        User user = userService.queryUser(username);
        if (chekcode.equals(vercode)) {
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    model.addAttribute("userMsg", "你好，" + user.getUsername());
                    session.setAttribute("loginUser", user);
                    return "index";
                } else {
                    model.addAttribute("loginErr", "密码错误");
                    return "login";
                }
            }else {
                model.addAttribute("loginErr", "用户名不存在");
                return "login";
            }
        } else{
            model.addAttribute("loginErr", "验证码错误");
            return "login";
        }
    }



    @PostMapping("/register")
    public String subregister(String username, String email, String password, String repassword, String usercode, HttpServletRequest request
            , HttpServletResponse response, Model model) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String vercode = (String) session.getAttribute("vercode");
//        String vercode = "ABC";
        if (usercode.equals(vercode)) {
            if (registerService.isEmail(email)) {
                if (registerService.isPassword(password, repassword)) {
                    userService.userRegister(username, email, password);
                    response.getWriter().write("注册成功，前往登录");
                    response.setHeader("refresh", "1;URL=login.html");
                } else {
                    model.addAttribute("registerErr", "两次密码输入不一样");
                    return "register";
                }
            } else {
                model.addAttribute("registerErr", "邮箱已被注册");
                return "register";
            }
        } else {
            model.addAttribute("registerErr", "验证码错误");
            return "register";
        }
        return null;
    }

    @GetMapping("/isLogin")
    @ResponseBody
    public CommonResult<User> getIndex(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            return new CommonResult<>(200, "您已经登录过了");
        }
        return new CommonResult<>(403, "请前往登录");
    }

    @GetMapping("/logout")
    @ResponseBody
    public CommonResult<User> quitLogin(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            session.removeAttribute("loginUser");
            model.addAttribute("userMsg", "亲，请登录");
            return new CommonResult<>(200, "");
        }
        return new CommonResult<>(403, "您还没登录，请前往登录");
    }



    @GetMapping("/getVerCode")
    @ResponseBody
    public void getCode(HttpServletRequest request, @RequestParam("email") String email) {
        emailService.setMailSender(email, request);

    }

    @GetMapping("/getImgvercode")
    @ResponseBody
    public CommonResult getverCode() throws IOException {

        String vercode = vercodeService.getVerocde();
        return new CommonResult(200, "成功！", vercode);
    }

}
