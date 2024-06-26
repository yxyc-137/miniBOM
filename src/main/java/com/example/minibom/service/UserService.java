package com.example.minibom.service;


import com.example.minibom.exception.CustomException;
import com.example.minibom.feign.UserFeign;
import com.example.minibom.utils.MD5Util;
import com.example.minibom.utils.RandUtils;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
public class UserService {
    private static final int SALT_LENGTH = 6;
    @Autowired
    private UserFeign userFeign;

    public void userRegister(User registerQo) {

        // 查询信息是否存在
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("email", ConditionType.EQUAL, registerQo.getEmail());
        RDMResultVO result = userFeign.find("User", var1);
        if (result.getData().size() != 0) {
            throw new CustomException("该邮箱已被注册");
        }

        params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, registerQo.getName());
        result = userFeign.find("User", var1);
        if (result.getData().size() != 0) {
            throw new CustomException("该用户名已被注册");
        }

        params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("telephone", ConditionType.EQUAL, registerQo.getTelephone());
        result = userFeign.find("User", var1);
        if (result.getData().size() != 0) {
            throw new CustomException("该手机号已被注册");
        }

        //密码加盐加密
        String salt = RandUtils.getRandomCode(SALT_LENGTH); //生成盐值
        String passwordMd5 = MD5Util.md5Lower(registerQo.getPassword(), salt);//MD5加密

        //创建用户并保存
        User user = new User();
        user.setName(registerQo.getName());
        user.setPassword(passwordMd5);
        user.setEmail(registerQo.getEmail());
        user.setEmail(registerQo.getTelephone());
        user.setHash(salt);

        RDMParamVO<User> userRDMParamVO = new RDMParamVO<>();
        userRDMParamVO.setParams(user);
        userFeign.create("User", userRDMParamVO);

    }
    public User login(User login) {
        // 根据用户名查找用户
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        params.addCondition("name", ConditionType.EQUAL, login.getName());
        var1.setParams(params);
        RDMResultVO result = userFeign.find("User", var1);
        //System.out.println(result);
        // 如果用户不存在
        if (result.getData().size() == 0) {
            throw new CustomException("用户不存在");
        }

        // 如果用户存在，获取用户信息
        Object data = result.getData().get(0);
        LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>) data;
        User user1 = new User();
        user1.setId(Long.valueOf((String) userMap.get("id")));
        user1.setName((String) userMap.get("name"));
        user1.setPassword((String) userMap.get("password"));
        user1.setEmail((String) userMap.get("email"));
        user1.setHash((String) userMap.get("hash"));

        // 密码校验
        // 密码盐值处理
        String password = MD5Util.md5Lower(login.getPassword() + user1.getHash());
        if (user1.getPassword().equals(password)) {
            return user1;
        } else {
            throw new CustomException("用户密码错误");
        }

    }

}
