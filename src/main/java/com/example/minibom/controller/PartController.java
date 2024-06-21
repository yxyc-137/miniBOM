package com.example.minibom.controller;

import com.example.minibom.feign.PartFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.*;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;


import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartUpdateByAdminDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartViewDTO;
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import static java.lang.Long.parseLong;

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

    //根据id查找part（直接在url中传入id）
    @RequestMapping(value = "/part/findById/{id}", method = RequestMethod.POST)
    public RDMResultVO findById(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("id", ConditionType.EQUAL, id);
        return partFeign.find("Part", var1);
    }

    //根据name查找part（直接在url中传入name）
    @RequestMapping(value = "/part/findByName/{name}", method = RequestMethod.POST)
    public RDMResultVO findByName(@PathVariable String name) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, name);
        return partFeign.find("Part", var1);
    }



    //根据id查询part（id传入请求体）get
//    e.g.
//    {
//        "id": "637303847798181888"
//    }
    @RequestMapping(value = "/part/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody Part part){
        RDMParamVO<Part> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.get("Part", var1);
    }



    //创建part, 请求体要有master，branch
//    e.g.
//    {
//        "iterationNote": "createExample",
//            "name": "示例部件",
//            "master": {
//
//    },
//        "branch": {
//
//    }
//    }
    @RequestMapping(value = "/part/create", method = RequestMethod.POST)
    public RDMResultVO create(@RequestBody PartCreateDTO part) {
        RDMParamVO<PartCreateDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.create("Part", var1);
    }



    //删除part（根据masterId）请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody MasterIdModifierDTO part) {
        RDMParamVO<MasterIdModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.delete("Part", var1);
    }



    //更新part（根据masterId）: 步骤是先检出，再更新并检入
    //首先检出part 请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/checkout", method = RequestMethod.POST)
    public RDMResultVO checkout(@RequestBody VersionCheckOutDTO part) {
        RDMParamVO<VersionCheckOutDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.checkout("Part", var1);
    }


    //再更新并检入part 请求体要有masterId，以及data（在里面写所修改的字段）
//    e.g.
//    {
//        "masterId": 642004286863060993,
//        "data": {
//            "source": "Make"
//        }
//    }
    @RequestMapping(value = "/part/updateAndCheckin", method = RequestMethod.POST)
    public RDMResultVO updateAndCheckin(@RequestBody VersionUpdateAndCheckinDTO<PartUpdateByAdminDTO> part) {
        RDMParamVO<VersionUpdateAndCheckinDTO<PartUpdateByAdminDTO>> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.updateAndCheckin("Part", var1);
    }



    // 检入part 请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/checkin", method = RequestMethod.POST)
    public RDMResultVO checkin(@RequestBody VersionCheckInDTO part) {
        RDMParamVO<VersionCheckInDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.checkin("Part", var1);
    }



    //修订part（发布大版本） 请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/revise", method = RequestMethod.POST)
    public RDMResultVO revise(@RequestBody VersionReviseDTO part) {
        RDMParamVO<VersionReviseDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.revise("Part", var1);
    }



    // 获取版本列表（查询part历史版本） 请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/getAllVersions", method = RequestMethod.POST)
    public RDMResultVO getAllVersions(@RequestBody VersionMasterDTO part) {
        RDMParamVO<VersionMasterDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.getAllVersions("Part", var1);
    }



    // 删除最新分支的最新版本  请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/deleteLatestVersion", method = RequestMethod.POST)
    public RDMResultVO deleteLatestVersion(@RequestBody VersionMasterModifierDTO part) {
        RDMParamVO<VersionMasterModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.deleteLatestVersion("Part", var1);
    }



    // 删除最新分支下的所有小版本（即删除了最新分支） 请求体要有masterId
//    e.g.
//    {
//        "masterId": 642004286863060993
//    }
    @RequestMapping(value = "/part/deleteBranch", method = RequestMethod.POST)
    public RDMResultVO deleteBranch(@RequestBody VersionMasterModifierDTO part) {
        RDMParamVO<VersionMasterModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.deleteBranch("Part", var1);
    }

    //查询大版本下最新小版本
    @RequestMapping(value = "/part/getLatestVersion", method = RequestMethod.POST)
    public RDMResultVO getLatestVersion(@RequestBody VersionMasterDTO part) {
        RDMParamVO<VersionMasterDTO> var1 = new RDMParamVO<>();
        var1.setParams(part);
        return partFeign.getVersionByMaster(var1);
    }

    //查询所有part的最新版本
    @RequestMapping(value = "/part/getAllLatestPart", method = RequestMethod.POST)
    public RDMResultVO getAllLatestPart() {
        //新建一个List存储最新版本的part
        List<Object> result = new ArrayList<>();
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        RDMResultVO all = partFeign.find("Part", var1);
        //记录所有masterId（不重复）
        HashSet<String> masterIdSet = new HashSet<>();
        for (Object item : all.getData()) {
            LinkedHashMap<String, Object> partMap = (LinkedHashMap<String, Object>) item;
            Object master = partMap.get("master");
            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>) master;
            String masterId = masterMap.get("id").toString();
            masterIdSet.add(masterId);
        }
        for (String mid : masterIdSet) {
            VersionMasterDTO versionMasterDTO = new VersionMasterDTO();
            versionMasterDTO.setMasterId(Long.parseLong(mid));
            versionMasterDTO.setVersion("A");
            RDMParamVO<VersionMasterDTO> var2 = new RDMParamVO<>();
            var2.setParams(versionMasterDTO);
            RDMResultVO latestVersion = partFeign.getVersionByMaster(var2);
            result.add(latestVersion.getData().get(0));
        }
        RDMResultVO rdmResultVO = new RDMResultVO();
        rdmResultVO.setData(result);
        rdmResultVO.setResult("SUCCESS");
        return rdmResultVO;
    }
}
