package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.BOMUsesOccurrence;
import com.huawei.innovation.rdm.minibom.dto.entity.BOMUsesOccurrenceCreateDTO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${idme.endpoint}", name = "bomusersoccurrence-service")
public interface BOMUsesOccurrenceFeign {
    @PostMapping("dynamic/api/{modelName}/find")
    RDMResultVO find(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<QueryRequestVo> var1);

    @PostMapping("dynamic/api/{modelName}/get")
    RDMResultVO get(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMUsesOccurrence> var1);

    @PostMapping("dynamic/api/{modelName}/create")
    RDMResultVO create(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMUsesOccurrenceCreateDTO> var1);

    @PostMapping("dynamic/api/{modelName}/update")
    RDMResultVO update(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMUsesOccurrence> var1);

    @PostMapping("api/{modelName}/delete")
    RDMResultVO delete(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMUsesOccurrence> var1);
}
