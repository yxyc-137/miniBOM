package com.example.minibom.controller;

import com.example.minibom.common.Result;
import com.example.minibom.feign.BOMLinkFeign;
import com.example.minibom.feign.PartFeign;
import com.example.minibom.service.BomLinkService;
import com.huawei.innovation.rdm.coresdk.basic.dto.*;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;


import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.bean.entity.PartMaster;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;

@RequestMapping("/feign")
@RestController
public class BOMLinkController {
    @Autowired
    private BOMLinkFeign bomLinkFeign;

    @Autowired
    private BomLinkService bomLinkService;
    //打印request日志

    /**
     * find
     *
     * @return 查询结果
     */
    //查找所有BOMLink
    @RequestMapping(value = "/bomlink/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return bomLinkFeign.find("BOMLink", var1);
    }

    //根据source id查询BOMLink
    @RequestMapping(value = "/bomlink/findBySourceId/{id}", method = RequestMethod.POST)
    public RDMResultVO findBySourceId(@PathVariable String id) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("source.id", ConditionType.EQUAL, id);
        return bomLinkFeign.find("BOMLink", var1);
    }

    //通过id获得BOMLink（get）
//    e.g.
//    {
//        "id": 642304810036502528
//    }
    @RequestMapping(value = "/bomlink/get", method = RequestMethod.POST)
    public RDMResultVO get(@RequestBody PersistObjectIdDecryptDTO link) {
        RDMParamVO<PersistObjectIdDecryptDTO> var1 = new RDMParamVO<>();
        var1.setParams(link);
        return bomLinkFeign.get("BOMLink", var1);
    }



    //创建BOMLink, 请求体要有父项source(填的是partVersion的id)，子项target（填的是master的id）, quantity
//    e.g.
//    {
//        "source":{
//            "id":642095259282583552
//        },
//        "target":{
//            "id":642303312644812801
//         },
//        "quantity":2.0
//    }
    @RequestMapping(value = "/bomlink/create", method = RequestMethod.POST)
    public Result create(@RequestBody BOMLinkCreateDTO link) {
        try {
            Object bomLink = bomLinkService.create(link);
            return Result.success(bomLink);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //更新BOMLink, 请求体要有id和需要改的字段（只能改quantity和sequenceNumber）
//    {
//        "id": 642304810036502528,
//        "quantity": 3.0
//    }
    @RequestMapping(value = "/bomlink/update", method = RequestMethod.POST)
    public RDMResultVO update(@RequestBody BOMLinkUpdateDTO link) {
        RDMParamVO<BOMLinkUpdateDTO> var1 = new RDMParamVO<>();
        var1.setParams(link);
        return bomLinkFeign.update("BOMLink", var1);
    }



    //删除BOMLink, 请求体要有id
//    {
//        "id": 642304810036502528
//    }
    @RequestMapping(value = "/bomlink/delete", method = RequestMethod.POST)
    public RDMResultVO delete(@RequestBody PersistObjectIdModifierDTO link) {
        RDMParamVO<PersistObjectIdModifierDTO> var1 = new RDMParamVO<>();
        var1.setParams(link);
        return bomLinkFeign.delete("BOMLink", var1);
    }



    //查询父项或者子项
    //即已知父项想查询子项的话，就在objectId处填入父项的id，role中填“Source”，则可以返回其子项的信息。
    //已知子项想查询父项的话，就在objectId处填入子项的masterId，role中填“Target"，则可以返回其父项的信息。
    // 请求体要有objectId以及他的role（Source或Target），会返回与之关联的部件
//    {
//        "role": "Source",
//        "objectId": 642095259282583552
//    }
    @RequestMapping(value = "/bomlink/queryRelatedObjects", method = RequestMethod.POST)
    public RDMResultVO queryRelatedObjects(@RequestBody GenericLinkQueryDTO link) {
        RDMParamVO<GenericLinkQueryDTO> var1 = new RDMParamVO<>();
        var1.setParams(link);
        return bomLinkFeign.queryRelatedObjects("BOMLink", var1);
    }

    @RequestMapping(value = "/bomlink/findAllSourcePart", method = RequestMethod.POST)
    public RDMResultVO findAllSourcePart() {
        return bomLinkService.findAllSourcePart();
    }
}
