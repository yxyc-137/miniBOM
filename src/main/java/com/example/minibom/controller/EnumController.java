package com.example.minibom.controller;

import com.example.minibom.feign.EnumFeign;
import com.huawei.innovation.rdm.bean.interfaces.EnumDefineViewDTO;
import com.huawei.innovation.rdm.bean.interfaces.Enumerator;
import com.huawei.innovation.rdm.bean.interfaces.EnumeratorViewDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequestMapping("/feign")
@RestController
public class EnumController {

    @Autowired
    private EnumFeign enumFeign;
    @GetMapping("enum/query")
    List<EnumeratorViewDTO>  queryAllEnumInfo(@Param("enumType") String enumType) {
        List<EnumeratorViewDTO> enumValues = new ArrayList<>();
        RDMResultVO rdmResultVO = enumFeign.queryAllEnumInfo();
        //System.out.println(rdmResultVO);
        List<Object> data = rdmResultVO.getData();
        for (Object datum : data) {
            LinkedHashMap<String, Object> enumMap = (LinkedHashMap<String, Object>) datum;
            if (enumMap.get("name").equals(enumType)) {
                List<LinkedHashMap<String, Object>> values = (List<LinkedHashMap<String, Object>>) enumMap.get("enumValueList");
                for (LinkedHashMap<String, Object> value : values) {
                    EnumeratorViewDTO t = new EnumeratorViewDTO();
                    t.setCode((String) value.get("code"));
                    t.setCnName((String) value.get("cnName"));
                    t.setEnName((String) value.get("enName"));
                    t.setAlias((String) value.get("alias"));
                    enumValues.add(t);
                }
            }
        }
        return enumValues;
    }
}
