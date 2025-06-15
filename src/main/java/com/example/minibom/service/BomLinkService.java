package com.example.minibom.service;

import com.example.minibom.common.Result;
import com.example.minibom.exception.CustomException;
import com.example.minibom.feign.BOMLinkFeign;
import com.example.minibom.feign.PartFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.GenericLinkQueryDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.bean.relation.BOMLink;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;


@Service
public class BomLinkService {
    @Autowired
    private BOMLinkFeign bomLinkFeign;

    @Autowired
    private PartFeign partFeign;

    public Long findMasterId(Long sourceId) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("id", ConditionType.EQUAL, sourceId);
        RDMResultVO findPart = partFeign.find("Part", var1);
        Object part = findPart.getData().get(0);
        LinkedHashMap<String, Object> partMap = (LinkedHashMap<String, Object>)part;
        LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>)partMap.get("master");
        return Long.parseLong((String) masterMap.get("id"));
    }

    public RDMResultVO findParent(Long targetId) {
        RDMParamVO<GenericLinkQueryDTO> var2 = new RDMParamVO<>();
        GenericLinkQueryDTO query = new GenericLinkQueryDTO();
        query.setObjectId(targetId);
        query.setRole("Target");
        var2.setParams(query);
        return bomLinkFeign.queryRelatedObjects("BOMLink", var2);
    }

    public RDMResultVO findBomLink(Long sourceId, Long targetId) {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("source.id", ConditionType.EQUAL, sourceId);
        params.addCondition("target.id", ConditionType.EQUAL, targetId);
        return bomLinkFeign.find("BOMLink", var1);
    }
    public Object create(BOMLinkCreateDTO link) {
        Long sourceId = link.getSource().getId();
        Long targetId = link.getTarget().getId();
        //根据sourceid查找part的masterId
        Long masterId = findMasterId(sourceId);
        System.out.println(masterId);
        System.out.println(targetId);
        if (targetId.equals(masterId)) {
            throw new CustomException("不能添加自己为子项");
        }
        //判断该bomlink是否存在
        if (!findBomLink(sourceId, targetId).getData().isEmpty()) {
            throw new CustomException("该BOMLink已存在");
        }
        //查找targetId是否是source及以上的父项
        RDMResultVO findParent = findParent(masterId);
        while (!findParent.getData().isEmpty()) {
            LinkedHashMap<String, Object> parent = (LinkedHashMap<String, Object>)findParent.getData().get(0);
            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>)parent.get("master");
            masterId = Long.parseLong((String) masterMap.get("id"));
            System.out.println(masterId);
            System.out.println(targetId);
            if (masterId.equals(targetId)) {
                throw new CustomException("不能添加自己的父项为子项");
            }
            findParent = findParent(masterId);
        }
        RDMParamVO<BOMLinkCreateDTO> var3 = new RDMParamVO<>();
        var3.setParams(link);
        return bomLinkFeign.create("BOMLink", var3).getData().get(0);
    }

    //找到所有没有父节点的项
    public RDMResultVO findAllSourcePart() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        RDMResultVO result = bomLinkFeign.find("BOMLink", var1);
        //对第一层part去重
        HashSet<Long> masterIdSet = new HashSet<>();
        List<Object> sourcelist = new ArrayList<>();
        for (Object item : result.getData()) {
            LinkedHashMap<String, Object> bomLinkMap = (LinkedHashMap<String, Object>)item;
            LinkedHashMap<String, Object> sourceMap = (LinkedHashMap<String, Object>)bomLinkMap.get("source");
            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>)sourceMap.get("master");
            Long sourceMasterId = Long.parseLong((String)masterMap.get("id"));
            RDMResultVO findParent = findParent(sourceMasterId);
            if (findParent.getData().isEmpty()) {
                if (!masterIdSet.contains(sourceMasterId)) {
                    masterIdSet.add(sourceMasterId);
                    sourcelist.add(sourceMap);
                }
            }
        }
        RDMResultVO rdmResultVO = new RDMResultVO();
        rdmResultVO.setData(sourcelist);
        rdmResultVO.setResult("SUCCESS");
        return rdmResultVO;
    }
}
