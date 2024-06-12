package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.dto.PersistObjectIdDecryptDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import com.huawei.innovation.rdm.xdm.bean.entity.EXADefinition;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionCreateDTO;
import com.huawei.innovation.rdm.xdm.dto.entity.EXADefinitionQueryViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${idme.endpoint}", name = "exa-service")
public interface EXADefinitionFeign {
    @GetMapping("rdm/basic/api/EXADefinition/find/100/1")
    RDMResultVO find(@RequestBody RDMParamVO<QueryRequestVo> var1);

    @PostMapping("rdm/common/api/{modelName}/get")
    RDMResultVO get(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<PersistObjectIdDecryptDTO> var1);

    @PostMapping("rdm/basic/api/{modelName}/create")
    RDMResultVO create(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<EXADefinitionCreateDTO> var1);

    @PostMapping("rdm/basic/api/{modelName}/update")
    RDMResultVO update(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<EXADefinition> var1);

    @PostMapping("rdm/basic/api/{modelName}/delete")
    RDMResultVO delete(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<EXADefinition> var1);
}
