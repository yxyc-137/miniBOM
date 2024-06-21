package com.example.minibom.controller;

import com.example.minibom.feign.BOMUsesOccurrenceFeign;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.BOMUsesOccurrence;
import com.huawei.innovation.rdm.minibom.dto.entity.BOMUsesOccurrenceCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/feign")
@RestController
public class BOMUsesOccurrenceController {

    @Autowired
    private BOMUsesOccurrenceFeign bomUsesOccurrenceFeign;
    //分页查询bomoccurrence信息
    @RequestMapping(value = "bomUsesOccurrence/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return bomUsesOccurrenceFeign.find("BOMUsesOccurrence", var1);
    }

    //根据source id查询bomoccurrence信息
    @RequestMapping(value = "bomUsesOccurrence/findBySourceId/{id}", method = RequestMethod.POST)
    public  RDMResultVO findBySourceId(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("bomLink.source.id", ConditionType.EQUAL, id);
        return bomUsesOccurrenceFeign.find("BOMUsesOccurrence", var1);
    }

    //根据bomoccurrence id获取bomoccurrence信息
    @RequestMapping(value = "bomUsesOccurrence/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody BOMUsesOccurrence bomUsesOccurrence) {
        RDMParamVO<BOMUsesOccurrence> var1 = new RDMParamVO<>();
        var1.setParams(bomUsesOccurrence);
        return bomUsesOccurrenceFeign.get("BOMUsesOccurrence", var1);
    }

    //创建bomoccurrence
    @RequestMapping(value = "bomUsesOccurrence/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody BOMUsesOccurrenceCreateDTO dto) {
        RDMParamVO<BOMUsesOccurrenceCreateDTO> var1 = new RDMParamVO<>();
        var1.setParams(dto);
        return bomUsesOccurrenceFeign.create("BOMUsesOccurrence", var1);
    }

    //更新bomoccurrence
    @RequestMapping(value = "bomUsesOccurrence/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody BOMUsesOccurrence bomUsesOccurrence) {
        RDMParamVO<BOMUsesOccurrence> var1 = new RDMParamVO<>();
        var1.setParams(bomUsesOccurrence);
        return bomUsesOccurrenceFeign.update("BOMUsesOccurrence", var1);
    }

    //删除bomoccurrence
    @RequestMapping(value = "bomUsesOccurrence/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody BOMUsesOccurrence bomUsesOccurrence) {
        RDMParamVO<BOMUsesOccurrence> var1 = new RDMParamVO<>();
        var1.setParams(bomUsesOccurrence);
        return bomUsesOccurrenceFeign.delete("BOMUsesOccurrence", var1);
    }

}
