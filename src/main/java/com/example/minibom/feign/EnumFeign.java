package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${idme.endpoint}", name = "enum-service")
public interface EnumFeign {
    @GetMapping("rdm/basic/api/EXADefinition/queryAllEnumInfo")
    RDMResultVO queryAllEnumInfo();

}
