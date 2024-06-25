package com.example.minibom.controller;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import com.example.minibom.common.Result;
import com.example.minibom.feign.UserFeign;
import com.example.minibom.service.UserService;
import com.example.minibom.utils.JWTUtils;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;


import com.huawei.innovation.rdm.minibom.bean.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * TestController
 *
 * @since 2024-04-11
 */
@RequestMapping("/feign")
@RestController
public class UserController {
    @Autowired
    private UserFeign userFeign;

    @Autowired
    private UserService userService;

    //打印request日志

    /**
     * find
     *
     * @return 查询结果
     */
    //查找所有用户
    @RequestMapping(value = "/user/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return userFeign.find("User", var1);
    }
    //根据id查找用户（直接在url中传入id）
    @RequestMapping(value = "/user/findById/{id}", method = RequestMethod.POST)
    public RDMResultVO findById(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("id", ConditionType.EQUAL, id);
        return userFeign.find("User", var1);
    }

    @RequestMapping(value = "/user/findByName/{name}", method = RequestMethod.POST)
    public RDMResultVO findByName(@PathVariable String name) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, name);
        return userFeign.find("User", var1);
    }
    //根据id查询用户（id传入请求体）
    @RequestMapping(value = "/user/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody User user){
        RDMParamVO<User> var1 = new RDMParamVO<>();
        var1.setParams(user);
        return userFeign.get("User", var1);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody User user) {
        RDMParamVO<User> var1 = new RDMParamVO<>();
        var1.setParams(user);
        return userFeign.create("User", var1);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody User user) {
        RDMParamVO<User> var1 = new RDMParamVO<>();
        var1.setParams(user);
        return userFeign.delete("User", var1);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody User user) {
        RDMParamVO<User> var1 = new RDMParamVO<>();
        var1.setParams(user);
        return userFeign.update("User", var1);
    }

    //注册功能
    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public Result register(@RequestBody User user) {
        userService.userRegister(user);
        return Result.success();
    }
    //登录功能
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        try {
            User user1 = userService.login(user);
            Map<String, Object> map = new HashMap<>();
            map.put("user", user1);
            String token = JWTUtils.getToken(user.getId(), user.getName());
            map.put("token", token);
            return Result.success(map);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

//    public ResponseEntity<?> login(@RequestBody User user1) {

//        try {
//            User user = userService.login(user1);
//            System.out.println(user);
//            if (user != null) {
//                // 假设这里调用JWTUtils生成令牌
//                String token = JWTUtils.getToken(user.getId(), user.getName());
//                Map<String, Object> response = new HashMap<>();
//                response.put("state", true);
//                response.put("msg", "认证成功");
//                response.put("token", token);
//                return ResponseEntity.ok(response);
//            } else {
//                Map<String, String> response = new HashMap<>();
//                response.put("state", "false");
//                response.put("msg", "用户不存在或密码错误");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        } catch (Exception e) {
//            Map<String, String> response = new HashMap<>();
//            response.put("state", "false");
//            response.put("msg", e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }


}

