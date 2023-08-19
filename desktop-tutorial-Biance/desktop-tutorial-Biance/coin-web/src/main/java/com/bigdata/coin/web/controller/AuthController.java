package com.bigdata.coin.web.controller;



import com.bigdata.coin.service.AuthService;
import com.bigdata.coin.result.Result;
import com.bigdata.coin.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

/**
 * 定时任务.
 **/
@RestController
@RequestMapping("/login")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;


    /**
     * 登陆.
     **/
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result login(@RequestParam("domain") @NotBlank String domain, @RequestParam("passwd") @NotBlank String passwd) {
        return ResultUtils.success(authService.login(domain,passwd));
    }

}