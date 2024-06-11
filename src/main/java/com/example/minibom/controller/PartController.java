package com.example.minibom.controller;

import com.example.minibom.feign.PartFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.MasterIdModifierDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.VersionCheckInDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.VersionCheckOutDTO;
import com.huawei.innovation.rdm.coresdk.basic.dto.VersionUpdateAndCheckinDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;


import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartUpdateByAdminDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

/**
 * TestController
 *
 * @since 2024-04-11
 */
@RequestMapping("/feign")

@RestController
public class PartController {
    @Autowired
    private PartFeign partFeign;

    //打印request日志

    /**
     * find
     *
     * @return 查询结果
     */
    //查找所有part
    @RequestMapping(value = "/part/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return partFeign.find("Part", var1);
    }
//    //根据id查找用户（直接在url中传入id）
//    @RequestMapping(value = "/user/findById/{id}", method = RequestMethod.POST)
//    public RDMResultVO findById(@PathVariable String id) {
//        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
//        QueryRequestVo params = new QueryRequestVo();
//        var1.setParams(params);
//        params.addCondition("id", ConditionType.EQUAL, id);
//        return userFeign.find("User", var1);
//    }
//
//    @RequestMapping(value = "/user/findByName/{name}", method = RequestMethod.POST)
//    public RDMResultVO findByName(@PathVariable String name) {
//        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
//        QueryRequestVo params = new QueryRequestVo();
//        var1.setParams(params);
//        params.addCondition("name", ConditionType.EQUAL, name);
//        return userFeign.find("User", var1);
//    }
    //根据id查询part（id传入请求体）get
    @RequestMapping(value = "/part/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody Part part){
        RDMParamVO<Part> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.get("Part", var1);
    }

    //创建part
    @RequestMapping(value = "/part/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody PartCreateDTO part) {
        RDMParamVO<PartCreateDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.create("Part", var1);
    }

    //删除part（根据masterId）
    @RequestMapping(value = "/part/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody MasterIdModifierDTO part) {
        RDMParamVO<MasterIdModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.delete("Part", var1);
    }

    //更新part（根据masterId）
    //首先检出part
    @RequestMapping(value = "/part/checkout", method = RequestMethod.POST)
    public RDMResultVO checkout(@RequestBody VersionCheckOutDTO part) {
        RDMParamVO<VersionCheckOutDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.checkout("Part", var1);
    }

    //再更新并检入part
    @RequestMapping(value = "/part/updateAndCheckin", method = RequestMethod.POST)
    public RDMResultVO updateAndCheckin(@RequestBody VersionUpdateAndCheckinDTO<PartUpdateByAdminDTO> part) {
        RDMParamVO<VersionUpdateAndCheckinDTO<PartUpdateByAdminDTO>> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.updateAndCheckin("Part", var1);
    }

    // 检入part
    @RequestMapping(value = "/part/checkin", method = RequestMethod.POST)
    public RDMResultVO checkin(@RequestBody VersionCheckInDTO part) {
        RDMParamVO<VersionCheckInDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.checkin("Part", var1);
    }
}
