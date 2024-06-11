package com.example.minibom;

import com.example.minibom.controller.FeignController;
import com.example.minibom.controller.PartController;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.User;
import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.minibom.bean.enumerate.PartSource;
import com.huawei.innovation.rdm.minibom.bean.enumerate.AssemblyMode;
import org.checkerframework.checker.units.qual.A;
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
    @Autowired
    private PartController partController;

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
        //boolean res = feignController.login(user);
        //System.out.print(res);
        user.setPassword("123456！");
    }

    @Test
    public void testEnumCreatePart() {
        PartCreateDTO part = new PartCreateDTO();
        PartSource partSource = PartSource.valueOf("Make");
        part.setSource(partSource);
        AssemblyMode assemblyMode = AssemblyMode.valueOf("Separable");
        part.setPartType(assemblyMode);
        part.setIterationNote("test1");
        System.out.print(part);
        RDMResultVO res = partController.create(part);
        System.out.print(res);
    }

}
