package com.example.minibom.service;

import com.example.minibom.feign.ClassificationNodeFeign;
import com.example.minibom.feign.PartFeign;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassificationNodeService {
    @Autowired
    private PartFeign partFeign;

    @Autowired
    private ClassificationNodeFeign classificationNodeFeign;
    public RDMResultVO delete(@RequestBody ClassificationNode classificationNode) {
        Long deleteId = classificationNode.getId();
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        RDMResultVO all = partFeign.find("Part", var1);
        for (Object item : all.getData()) {
            LinkedHashMap<String, Object> part = (LinkedHashMap<String, Object>) item;
            ArrayList<LinkedHashMap<String, Object>> extAttrsList = (ArrayList<LinkedHashMap<String, Object>>)part.get("extAttrs");
            for (LinkedHashMap<String, Object> attrs: extAttrsList) {
                if (attrs.containsKey("value")) {
                    Map<String, Object> value = (Map<String, Object>)attrs.get("value");
                    System.out.println(value);
                    if (value.get("id").equals(deleteId.toString())) {
                        RDMResultVO result = new RDMResultVO();
                        result.setResult("ERROR");
                        return result;
                    }
                }
            }
        }
        RDMParamVO<ClassificationNode> var2 = new RDMParamVO<>();
        var2.setParams(classificationNode);
        return classificationNodeFeign.delete("ClassificationNode", var2);
    }
}
