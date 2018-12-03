package org.zdd.bookstore.web.controller;

import org.zdd.bookstore.common.pojo.BSResult;
import org.zdd.bookstore.common.utils.BSResultUtil;
import org.zdd.bookstore.model.entity.Store;
import org.zdd.bookstore.model.entity.User;
import org.zdd.bookstore.model.service.IMailService;
import org.zdd.bookstore.model.service.IStoreService;
import org.zdd.bookstore.model.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private IMailService mailService;

    @Autowired
    private IStoreService storeService;

    @Value("${mail.fromMail.addr}")
    private String from;

    @Value("${my.ip}")
    private String ip;

    private final String USERNAME_PASSWORD_NOT_MATCH = "用户名或密码错误";

    private final String USERNAME_CANNOT_NULL = "用户名不能为空";

    @RequestMapping("/login")
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password,
                        HttpServletRequest request, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "login";
        }
        //未认证的用户
        Subject userSubject = SecurityUtils.getSubject();
        if (!userSubject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);

            token.setRememberMe(false);//禁止记住我功能
            try {

                //登录成功
                userSubject.login(token);
                User loginUser = (User) userSubject.getPrincipal();
                request.getSession().setAttribute("loginUser", loginUser);
                Store store = storeService.findStoreByUserId(loginUser.getUserId());
                request.getSession().setAttribute("loginStore", store);


                SavedRequest savedRequest = WebUtils.getSavedRequest(request);
                String url = "/";
                if (savedRequest != null) {
                    url = savedRequest.getRequestUrl();
                    if(url.contains(request.getContextPath())){
                        url = url.replace(request.getContextPath(),"");
                    }
                }
                if(StringUtils.isEmpty(url) || url.equals("/favicon.ico")){
                    url = "/";
                }

                return "redirect:" + url;

            } catch (UnknownAccountException | IncorrectCredentialsException uae) {
                model.addAttribute("loginMsg", USERNAME_PASSWORD_NOT_MATCH);
                return "login";
            } catch (LockedAccountException lae) {
                model.addAttribute("loginMsg", "账户已被冻结！");
                return "login";
            } catch (AuthenticationException ae) {
                model.addAttribute("loginMsg", "登录失败！");
                return "login";
            }

        } else {
            //用户已经登录
            return "redirect:/index";
        }

    }

    @RequestMapping("/info")
    public String personInfo(){
        return "user_info";
    }

    /* @RequestMapping("/login1")
     public String login1(@RequestParam(value = "username", required = false) String username,
                          @RequestParam(value = "password", required = false) String password,
                          Model model, HttpServletRequest request) {

         if (StringUtils.isEmpty(username)) {
             model.addAttribute("loginMsg", USERNAME_CANNOT_NULL);
             return "login";
         }

         if (StringUtils.isEmpty(password)) {
             model.addAttribute("loginMsg", "密码不能为空");
             return "login";
         }

         BSResult<User> bsResult = userService.login(username, password);
         //登录校验失败
         if (bsResult.getData() == null) {
             model.addAttribute("loginMsg", bsResult.getMessage());
             return "login";
         }

         //登录校验成功，重定向到首页
         User user = bsResult.getData();
         //置密码为空
         user.setPassword("");
         request.getSession().setAttribute("user", user);
         return "redirect:/";
     }
     */
    //shiro框架帮我们注销
    @RequestMapping("/logout")
    @CacheEvict(cacheNames="authorizationCache",allEntries = true)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/page/login";
    }

    /**
     * 注册 检验用户名是否存在
     *
     * @param username
     * @return
     */
    @RequestMapping("/checkUserExist")
    @ResponseBody
    public BSResult checkUserExist(String username) {
        if (StringUtils.isEmpty(username)) {
            return BSResultUtil.build(200, USERNAME_CANNOT_NULL, false);
        }

        return userService.checkUserExistByUsername(username);
    }

    /**
     * 注册，发激活邮箱
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(User user, Model model) {

        BSResult isExist = checkUserExist(user.getUsername());

        //尽管前台页面已经用ajax判断用户名是否存在，
        // 为了防止用户不是点击前台按钮提交表单造成的错误，后台也需要判断
        if ((Boolean) isExist.getData()) {

            BSResult bsResult = userService.saveUser(user);
            //获得未激活的用户
            User userNotActive = (User) bsResult.getData();
            try {
                mailService.sendHtmlMail(user.getEmail(), "<dd书城>---用户激活---",
                        "<html><body><a href='http://"+ip+"/user/active?activeCode=" + userNotActive.getCode() + "'>亲爱的" + user.getUsername() +
                                "，请您点击此链接前往激活</a></body></html>");
            } catch (Exception e) {
                e.printStackTrace();
                model.addAttribute("registerError", "发送邮件异常！请检查您输入的邮箱地址是否正确。");
                return "fail";
            }
            model.addAttribute("username", user.getUsername());
            return "register_success";
        } else {

            //用户名已经存在，不能注册
            model.addAttribute("registerError", isExist.getMessage());
            return "register";
        }

    }

    @RequestMapping("/active")
    public String activeUser(String activeCode, Model model) {
        BSResult bsResult = userService.activeUser(activeCode);
        if (!StringUtils.isEmpty(bsResult.getData())) {
            model.addAttribute("username", bsResult.getData());
            return "active_success";
        } else {
            model.addAttribute("failMessage", bsResult.getMessage());
            return "fail";
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public BSResult updateUser(User user, HttpSession session){
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setNickname(user.getNickname());
        loginUser.setLocation(user.getLocation());
        loginUser.setDetailAddress(user.getDetailAddress());
        loginUser.setGender(user.getGender());
        loginUser.setUpdated(new Date());
        loginUser.setPhone(user.getPhone());
        loginUser.setIdentity(user.getIdentity());
        loginUser.setPhone(user.getPhone());
        BSResult bsResult = userService.updateUser(loginUser);
        session.setAttribute("loginUser", loginUser);
        return bsResult;
    }

    @RequestMapping("/password/{userId}")
    @ResponseBody
    public BSResult changePassword(@PathVariable("userId") int userId,String oldPassword,String newPassword){
        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)){
            return BSResultUtil.build(400, "密码不能为空");
        }
        return userService.compareAndChange(userId,oldPassword,newPassword);
    }

}
