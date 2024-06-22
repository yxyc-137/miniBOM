package com.example.minibom.controller;

import com.example.minibom.feign.EXADefinitionFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdsModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import com.huawei.innovation.rdm.xdm.bean.entity.EXADefinition;
import com.huawei.innovation.rdm.xdm.bean.enumerate.ModelTypeEnum;
import com.huawei.innovation.rdm.xdm.bean.relation.EXADefinitionLink;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionQueryViewDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/feign")
@RestController
public class EXADefinitionController {
    @Autowired
    private EXADefinitionFeign exaDefinitionFeign;

    //属性只写了find和get方法

    //POST方法：分页查询属性信息（路径入参pageSize和curPage)
    @RequestMapping(value = "exaDefinition/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return exaDefinitionFeign.find(var1);
    }
    //POST方法：根据分类节点id获取属性信息（输入参数id）
    @RequestMapping(value = "exaDefinition/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody PersistObjectIdDecryptDTO dto) {
        RDMParamVO<PersistObjectIdDecryptDTO> var1 = new RDMParamVO<>();
        var1.setParams(dto);
        return exaDefinitionFeign.get("EXADefinition", var1);
    }

    @RequestMapping(value = "/exaDefinition/findById", method = RequestMethod.POST)
    public RDMResultVO findById(@RequestParam("id") String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("id", ConditionType.EQUAL, id);
        return exaDefinitionFeign.find(var1);
    }

    //根据name查找分类节点信息（直接在url中传入name）
    @RequestMapping(value = "/exaDefinition/findByName", method = RequestMethod.POST)
    public RDMResultVO findByName(@RequestParam("name") String name) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, name);
        return exaDefinitionFeign.find(var1);
    }

    @RequestMapping(value = "/exaDefinition/nodeRefered/{pageSize}/{pageNum}", method = RequestMethod.POST)
    public RDMResultVO nodeRefered(@PathVariable("pageSize") Integer pageSize,
                                   @PathVariable("pageNum") Integer pageNum,
                                   @RequestParam("id") Long exaDefinitionId) {
        EXADefinitionQueryViewDTO var1 = new EXADefinitionQueryViewDTO();
        var1.setId(exaDefinitionId);
        return exaDefinitionFeign.NodeRefered(pageSize, pageNum, var1);
    }

    //POST方法：创建属性（输入参数EXADefiniton(传name、nameEn、type、description和descriptionEn）
    @RequestMapping(value = "exaDefinition/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody EXADefinitionCreateDTO dto) {
        //实数
        if (dto.getType().equals("DECIMAL")) {
            dto.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\",\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"legalValueType\":\"\",\"length\":0,\"multiValue\":false,\"notnull\":false,\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":2,\"range\":\"\",\"secretLevel\":\"internal\",\"stockInDB\":true,\"variable\":true}");
        }
        //整型
        if (dto.getType().equals("INTEGER")) {
            dto.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\",\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"legalValueType\":\"\",\"length\":0,\"multiValue\":false,\"notnull\":false,\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"range\":\"\",\"secretLevel\":\"internal\",\"stockInDB\":true,\"variable\":true}");
        }
        //字符串
        if (dto.getType().equals("STRING")) {
            dto.setConstraint("{\"associationType\":\"STRONG\",\"caseMode\":\"DEFAULT\",\"compose\":false,\"encryption\":false,\"graphIndex\":false,\"legalValueType\":\"\",\"length\":200,\"multiValue\":false,\"notnull\":false,\"optionalValue\":\"LEGAL_VALUE_TYPE\",\"precision\":0,\"secretLevel\":\"internal\",\"stockInDB\":true,\"variable\":true}");
        }
        RDMParamVO<EXADefinitionCreateDTO> var1 = new RDMParamVO<>();
        var1.setParams(dto);
        return exaDefinitionFeign.create("EXADefinition", var1);
    }

    //POST方法：更新属性（输入参数EXADefiniton
    @RequestMapping(value = "exaDefinition/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody EXADefinitionUpdateDTO exaDefinition) {
        RDMParamVO<EXADefinitionUpdateDTO> var1 = new RDMParamVO<>();
        var1.setParams(exaDefinition);
        return exaDefinitionFeign.update("EXADefinition", var1);
    }

    //POST方法：删除属性（输入参数EXADefiniton(传id)
    @RequestMapping(value = "exaDefinition/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody EXADefinition exaDefinition) {
        RDMParamVO<EXADefinition> var1 = new RDMParamVO<>();
        var1.setParams(exaDefinition);
        return exaDefinitionFeign.delete("EXADefinition", var1);
    }

    @RequestMapping(value = "/exaDefinitionLink/findAll", method = RequestMethod.POST)
    public RDMResultVO findAllLink() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return exaDefinitionFeign.findLink(var1);
    }

    //根据分类节点id查找扩展属性
    @RequestMapping(value = "/exaDefinitionLink/findById/{id}", method = RequestMethod.POST)
    public RDMResultVO findLinkById(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("target.id", ConditionType.EQUAL, id);
        return exaDefinitionFeign.findLink(var1);
    }

    @RequestMapping(value = "/exaDefinitionLink/create", method = RequestMethod.POST)
    public RDMResultVO createLink(@RequestBody EXADefinitionLink exaDefinition) {
        RDMParamVO<EXADefinitionLink> var1 = new RDMParamVO<>();
        var1.setParams(exaDefinition);
        return exaDefinitionFeign.createLink(var1);
    }

    @RequestMapping(value = "/exaDefinitionLink/batchCreate", method = RequestMethod.POST)
    public RDMResultVO batchCreateLink(@RequestBody List<EXADefinitionLink> exaDefinition) {
        RDMParamVO<List<EXADefinitionLink>> var1 = new RDMParamVO<>();
        var1.setParams(exaDefinition);
        return exaDefinitionFeign.batchCreateLink(var1);
    }

    @RequestMapping(value = "/exaDefinitionLink/delete", method = RequestMethod.POST)
    public RDMResultVO deleteLink(@RequestBody EXADefinitionLink exaDefinition) {
        RDMParamVO<EXADefinitionLink> var1 = new RDMParamVO<>();
        var1.setParams(exaDefinition);
        return exaDefinitionFeign.deleteLink(var1);
    }
    @RequestMapping(value = "/exaDefinitionLink/batchDelete", method = RequestMethod.POST)
    public RDMResultVO batchDeleteLink(@RequestBody PersistObjectIdsModifierDTO dto) {
        RDMParamVO<PersistObjectIdsModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(dto);
        return exaDefinitionFeign.batchDeleteLink(var1);
    }

}
