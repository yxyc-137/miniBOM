package com.example.minibom.controller;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import com.example.minibom.feign.UserFeign;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;


import com.huawei.innovation.rdm.minibom.bean.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TestController
 *
 * @since 2024-04-11
 */
@RequestMapping("/feign")
@RestController
public class FeignController {
    @Autowired
    private UserFeign userFeign;

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
    //根据id查询用户（id传入请求体）
    @RequestMapping(value = "/user/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody RDMParamVO<User> var1){
        return userFeign.get("User", var1);
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody RDMParamVO<User> var1) {
        System.out.println(var1);
        return userFeign.create("User", var1);
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody RDMParamVO<User> var1) {
        return userFeign.delete("User", var1);
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody RDMParamVO<User> var1) {
        return userFeign.update("User", var1);
    }
    //登录功能
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
//    public RDMResultVO login(@RequestBody RDMParamVO<User> var1) {
//
//    }
}

