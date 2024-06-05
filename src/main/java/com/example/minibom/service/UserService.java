package com.example.minibom.service;

import com.example.minibom.feign.UserFeign;
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

    @Autowired
    private UserFeign userFeign;

    public User login(User user) {
        // 根据用户名查找用户
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, user.getName());
        RDMResultVO result = userFeign.find("User", var1);
        System.out.println(result);
        // 如果用户不存在
        if (result.getData().size() == 0) {
            return null;
        }
        // 如果用户存在，判断密码是否正确
        Object data = result.getData().get(0);
        LinkedHashMap<String, Object> userMap = (LinkedHashMap<String, Object>) data;
        User user1 = new User();
        user1.setId(Long.valueOf((String) userMap.get("id")));
        user1.setName((String) userMap.get("name"));
        user1.setPassword((String) userMap.get("password"));
        user1.setEmail((String) userMap.get("email"));
        // 密码正确
        if (user1.getPassword().equals(user.getPassword())) {
            return user1;
        }
        else  return null;
    }
}
