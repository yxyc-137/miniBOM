package com.example.minibom.controller;


import com.example.minibom.feign.ClassificationNodeFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMPageVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeQueryViewDTO;
import feign.Param;

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

    //GET方法：根据分类节点id获取子节点（输入参数parentId)
    @RequestMapping(value = "classificationNode/getChildren", method = RequestMethod.GET)
    public RDMResultVO getChildern(@RequestParam("parentId") Long parentId,
                                       @RequestParam(value = "enable", required = false) Boolean enable) {
        return classificationNodeFeign.getChild(parentId, enable);
    }

    //GET方法：根据分类节点id获取所有子节点（输入为空获得所有分类节点)
    @RequestMapping(value = "classificationNode/getAllChildList", method = RequestMethod.GET)
    public RDMResultVO getAllChildList(@RequestParam(value = "parentId", required = false) Long parentId) {
        QueryChildListDTO queryChildListDTO = new QueryChildListDTO();
        queryChildListDTO.setParentId(parentId);
        RDMParamVO<QueryChildListDTO> var1 = new RDMParamVO<>();
        var1.setParams(queryChildListDTO);
        return classificationNodeFeign.getAllChildList(var1);
    }

    //POST方法：分页查询分类节点（路径入参pageSize和curPage)
    @RequestMapping(value = "classificationNode/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return classificationNodeFeign.find("ClassificationNode", var1);
    }

    //POST方法：根据分类节点id获取分类节点信息（输入参数id）
    @RequestMapping(value = "classificationNode/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody ClassificationNode classificationNode) {
        RDMParamVO<ClassificationNode> var1 = new RDMParamVO<>();
        var1.setParams(classificationNode);
        return classificationNodeFeign.get("ClassificationNode", var1);
    }

    //根据id查找分类节点信息（直接在url中传入id）
    @RequestMapping(value = "/classificationNode/findById/{id}", method = RequestMethod.POST)
    public RDMResultVO findById(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("id", ConditionType.EQUAL, id);
        return classificationNodeFeign.find("ClassificationNode", var1);
    }

    //根据name查找分类节点信息（直接在url中传入name）
    @RequestMapping(value = "/classificationNode/findByName/{name}", method = RequestMethod.POST)
    public RDMResultVO findByName(@PathVariable String name) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, name);
        return classificationNodeFeign.find("ClassificationNode", var1);
    }
    //POST方法：创建分类节点（输入参数ClassificationNode(传name和nameEn）
    @RequestMapping(value = "classificationNode/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody ClassificationNode classificationNode) {
        RDMParamVO<ClassificationNode> var1 = new RDMParamVO<>();
        var1.setParams(classificationNode);
        return classificationNodeFeign.create("ClassificationNode", var1);
    }

    //POST方法：更新分类节点（输入参数ClassificationNode(传id和name和nameEn)
    @RequestMapping(value = "classificationNode/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody ClassificationNode classificationNode) {
        RDMParamVO<ClassificationNode> var1 = new RDMParamVO<>();
        var1.setParams(classificationNode);
        return classificationNodeFeign.update("ClassificationNode", var1);
    }

    //POST方法：删除分类节点（输入参数ClassificationNode(传id)
    @RequestMapping(value = "classificationNode/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody ClassificationNode classificationNode) {
        RDMParamVO<ClassificationNode> var1 = new RDMParamVO<>();
        var1.setParams(classificationNode);
        return classificationNodeFeign.delete("ClassificationNode", var1);
    }

    //GET方法：根据分类节点id获取分类节点信息（输入参数linkId：分类节点id)
    @RequestMapping(value = "classificationNode/getCategoryNodeInfo", method = RequestMethod.GET)
    public RDMResultVO getCategoryNodeInfo(@RequestParam("linkId") Long linkId) {
        return classificationNodeFeign.getCategoryNodeInfo(linkId);
    }




}
