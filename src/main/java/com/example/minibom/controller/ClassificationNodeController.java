package com.example.minibom.controller;


import com.example.minibom.feign.ClassificationNodeFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeQueryViewDTO;
import feign.Param;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/feign")
@RestController
public class ClassificationNodeController {

    @Autowired
    private ClassificationNodeFeign classificationNodeFeign;

    @RequestMapping(value = "classificationNode/getChildren", method = RequestMethod.GET)
    public RDMResultVO getAllChildList(@RequestParam("parentId") Long parentId,
                                       @RequestParam(value = "enable", required = false) Boolean enable) {
        return classificationNodeFeign.getChild(parentId, enable);
    }

    @RequestMapping(value = "classificationNode/getCategoryNodeInfo", method = RequestMethod.GET)
    public RDMResultVO getCategoryNodeInfo(@RequestParam("linkId") Long linkId) {
        return classificationNodeFeign.getCategoryNodeInfo(linkId);
    }

}
