package com.example.minibom;

import com.example.minibom.controller.FeignController;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = FeignApiMainApplication.class)
@RunWith(SpringRunner.class)
public class FeignControllerTest {

    @Autowired
    private FeignController feignController;

//    @Test
//    public void testFind() {
//        RDMResultVO res = feignController.find();
//        System.out.print(res);
//    }
    @Test
    public void testLogin() {
        User user = new User();
        user.setName("xlx");
        user.setPassword("123456!");
        boolean res = feignController.login(user);
        System.out.print(res);
    }


}
