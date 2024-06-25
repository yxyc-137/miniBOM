package com.example.minibom.service;

import com.example.minibom.feign.PartFeign;
import com.huawei.innovation.rdm.coresdk.basic.dto.VersionMasterDTO;
import com.huawei.innovation.rdm.coresdk.basic.enums.ConditionType;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PartService {
    @Autowired
    private PartFeign partFeign;

    public RDMResultVO getAllLatestVersion() {
        //新建一个List存储最新版本的part
        List<Object> result = new ArrayList<>();
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        RDMResultVO all = partFeign.find("Part", var1);
        //使用哈希表记录<masterId, Part>键值对
        LinkedHashMap<Long, Object> latestVersionMap = new LinkedHashMap<>();
        for (Object item : all.getData()) {
            LinkedHashMap<String, Object> partMap = (LinkedHashMap<String, Object>) item;
            Object master = partMap.get("master");
            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>) master;
            Long masterId = Long.parseLong(masterMap.get("id").toString());
            latestVersionMap.put(masterId, partMap);
        }
        for (Object obj : latestVersionMap.values()) {
            result.add(obj);
        }
        //记录所有masterId（不重复）
//        HashSet<String> masterIdSet = new HashSet<>();
//        for (Object item : all.getData()) {
//            LinkedHashMap<String, Object> partMap = (LinkedHashMap<String, Object>) item;
//            Object master = partMap.get("master");
//            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>) master;
//            String masterId = masterMap.get("id").toString();
//            masterIdSet.add(masterId);
//        }
//        for (String mid : masterIdSet) {
//            VersionMasterDTO versionMasterDTO = new VersionMasterDTO();
//            versionMasterDTO.setMasterId(Long.parseLong(mid));
//            versionMasterDTO.setVersion("A");
//            RDMParamVO<VersionMasterDTO> var2 = new RDMParamVO<>();
//            var2.setParams(versionMasterDTO);
//            RDMResultVO latestVersion = partFeign.getVersionByMaster(var2);
//            result.add(latestVersion.getData().get(0));
//        }
        RDMResultVO rdmResultVO = new RDMResultVO();
        rdmResultVO.setData(result);
        rdmResultVO.setResult("SUCCESS");
        return rdmResultVO;
    }

    public RDMResultVO findByName(String name) {
        List<Object> result = new ArrayList<>();
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        params.addCondition("name", ConditionType.EQUAL, name);
        RDMResultVO all = partFeign.find("Part", var1);
        LinkedHashMap<Long, Object> latestVersionMap = new LinkedHashMap<>();
        for (Object item : all.getData()) {
            LinkedHashMap<String, Object> partMap = (LinkedHashMap<String, Object>) item;
            Object master = partMap.get("master");
            LinkedHashMap<String, Object> masterMap = (LinkedHashMap<String, Object>) master;
            Long masterId = Long.parseLong(masterMap.get("id").toString());
            latestVersionMap.put(masterId, partMap);
        }
        for (Object obj : latestVersionMap.values()) {
            result.add(obj);
        }
        RDMResultVO rdmResultVO = new RDMResultVO();
        rdmResultVO.setData(result);
        rdmResultVO.setResult("SUCCESS");
        return rdmResultVO;
    }
}
