package com.example.minibom.controller;

import com.example.minibom.feign.EnumFeign;
import com.huawei.innovation.rdm.bean.interfaces.EnumDefineViewDTO;
import com.huawei.innovation.rdm.bean.interfaces.Enumerator;
import com.huawei.innovation.rdm.bean.interfaces.EnumeratorViewDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.enumerate.AssemblyMode;
import com.huawei.innovation.rdm.minibom.bean.enumerate.PartSource;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RequestMapping("/feign")
@RestController
public class EnumController {

    //枚举值查询(创建part下拉菜单选择）
    /*
    AssemblyMode:
    Separable("Separable", "可分离", "Separable", "Separable"),
    Inseparable("Inseparable", "不可分离", "Inseparable", "Inseparable"),
    Part("Part", "零件", "Part", "Part");
    PartSource:
    Make("Make", "制造", "Make", "Make"),
    Buy("Buy", "购买", "Buy", "Buy"),
    Buy_SingleSoource("Buy_SingleSoource", "购买—单一供应源", "Buy_SingleSoource", "Buy_SingleSoource");
    */
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

    @RequestMapping(value = "/measuringUnit/find", method = RequestMethod.POST)
    public RDMResultVO find() {
        RDMParamVO<QueryRequestVo> var1 = new RDMParamVO<>();
        QueryRequestVo params = new QueryRequestVo();
        var1.setParams(params);
        return enumFeign.findUnitType(var1);
    }

}
