package com.chj.web;

import com.chj.model.User;
import com.chj.service.UserService;
import groovy.grape.GrapeIvy;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：chj
 * @date ：Created in 2020/4/6 17:21
 * @params :
 */
@Controller
public class LoginController {

    /**
     * 拦截找login
     *      login   get请求  无参数
     * @return
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     * 登录请求
     * @param username
     * @return
     */
    @PostMapping("/login")
    public String selectByName(String username,String password){
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                System.out.println("认证通过");
            }catch (UnknownAccountException uae) {
                System.out.println("用户不存在" + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                System.out.println("密码错误");
            } catch (LockedAccountException lae) {
                System.out.println("该账户已经锁定");
            }
            catch (AuthenticationException ae) {
                /**
                 * AuthenticationException是以上三个异常的父类
                 */
                System.out.println("认证失败");
            }
        }


        return "login";
    }

    /**
     * 成功之后跳转
     * @return
     */
    @RequestMapping("/")
    public String index(){
        return "index";
    }








    @RequestMapping("/bb")
    public String bb(){
        return "bb";
    }
    @RequestMapping("/404")
    public String error(){
        return "404";
    }
}
