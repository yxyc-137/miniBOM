package com.example.minibom.controller;

import com.example.minibom.feign.EXADefinitionFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import com.huawei.innovation.rdm.xdm.bean.entity.EXADefinition;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionQueryViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/feign")
@RestController
public class EXADefinitionController {
    @Autowired
    private EXADefinitionFeign exaDefinitionFeign;

    //属性只写了find和get方法

    //POST方法：分页查询属性信息（路径入参pageSize和curPage)
    @RequestMapping(value = "exaDefinition/find/{pageSize}/{curPage}", method = RequestMethod.POST)
    public RDMResultVO find(@PathVariable(value = "pageSize",required = false) Integer pageSize,
                            @PathVariable(value = "curPage",required = false) Integer curPage) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return exaDefinitionFeign.find(var1, pageSize, curPage);
    }
    //POST方法：根据分类节点id获取属性信息（输入参数id）
    @RequestMapping(value = "exaDefinition/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody PersistObjectIdDecryptDTO dto) {
        RDMParamVO<PersistObjectIdDecryptDTO> var1 = new RDMParamVO<>();
        var1.setParams(dto);
        return exaDefinitionFeign.get("EXADefinition", var1);
    }
//    //POST方法：创建属性（输入参数EXADefiniton(传name和nameEn）
//    @RequestMapping(value = "exaDefinition/create", method = RequestMethod.POST)
//    public RDMResultVO create(@RequestBody EXADefinitionCreateDTO dto) {
//        RDMParamVO<EXADefinitionCreateDTO> var1 = new RDMParamVO<>();
//        var1.setParams(dto);
//        return exaDefinitionFeign.create("EXADefinition", var1);
//    }
//
//    //POST方法：更新属性（输入参数EXADefiniton(传id和name和nameEn)
//    @RequestMapping(value = "exaDefinition/update", method = RequestMethod.POST)
//    public RDMResultVO update(@RequestBody EXADefinition exaDefinition) {
//        RDMParamVO<EXADefinition> var1 = new RDMParamVO<>();
//        var1.setParams(exaDefinition);
//        return exaDefinitionFeign.update("EXADefinition", var1);
//    }
//
//    //POST方法：删除属性（输入参数EXADefiniton(传id)
//    @RequestMapping(value = "exaDefinition/delete", method = RequestMethod.POST)
//    public RDMResultVO delete(@RequestBody EXADefinition exaDefinition) {
//        RDMParamVO<EXADefinition> var1 = new RDMParamVO<>();
//        var1.setParams(exaDefinition);
//        return exaDefinitionFeign.delete("EXADefinition", var1);
//    }

}
