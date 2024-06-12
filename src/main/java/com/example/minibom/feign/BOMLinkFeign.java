package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.dto.*;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;

import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.relation.BOMLinkUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 基础服务feign
 *
 * @since 2024-04-11
 */
@FeignClient(url = "${idme.endpoint}", name = "bomlink-service")

@RestController
public interface BOMLinkFeign {
    /**
     * 查询接口
     *
     * @param modelName 模型名称
     * @param var1 请求参数
     * @param pageSize 分页大小
     * @param curPage 当前页
     * @return 查询结果
     */

    @PostMapping("dynamic/api/{modelName}/create")
    RDMResultVO create(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMLinkCreateDTO> var1);

    @PostMapping("dynamic/api/{modelName}/find")
    RDMResultVO find(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<QueryRequestVo> var1);

    @PostMapping("dynamic/api/{modelName}/get")
    RDMResultVO get(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<PersistObjectIdDecryptDTO> var1);

    @PostMapping("dynamic/api/{modelName}/update")
    RDMResultVO update(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<BOMLinkUpdateDTO> var1);

    @PostMapping("dynamic/api/{modelName}/delete")
    RDMResultVO delete(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<PersistObjectIdModifierDTO> var1);

    @PostMapping("dynamic/api/{modelName}/queryRelatedObjects")
    RDMResultVO queryRelatedObjects(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<GenericLinkQueryDTO> var1);
}
