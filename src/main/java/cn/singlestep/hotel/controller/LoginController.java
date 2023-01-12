package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.Login;
import cn.singlestep.hotel.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description admin、user 登录接口
 * @date 2023/1/10 11:17
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    LoginService loginService;

    /**
     * admin、user 登录接口
     * */
    @PostMapping()
    public GlobalResponse loginMethod(@RequestBody Login loginInfo) {

        loginService.loginMethod(loginInfo);

        return GlobalResponse.success("登录成功");
    }
}
