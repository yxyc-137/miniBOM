package com.example.minibom.feign;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */
/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import com.huawei.innovation.rdm.coresdk.basic.dto.*;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;

import com.huawei.innovation.rdm.minibom.bean.entity.Part;
import com.huawei.innovation.rdm.minibom.dto.entity.PartCreateDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartUpdateByAdminDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartUpdateDTO;
import com.huawei.innovation.rdm.minibom.dto.entity.PartViewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

/**
 * 基础服务feign
 *
 * @since 2024-04-11
 */
@FeignClient(url = "${idme.endpoint}", name = "part-service")
public interface PartFeign {
    /**
     * 查询接口
     *
     * @param modelName 模型名称
     * @param var1 请求参数
     * @param pageSize 分页大小
     * @param curPage 当前页
     * @return 查询结果
     */

    @PostMapping("dynamic/api/{modelName}/find")
    RDMResultVO find(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<QueryRequestVo> var1);

    @PostMapping("dynamic/api/{modelName}/get")
    RDMResultVO get(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<Part> var1);

    @PostMapping("dynamic/api/{modelName}/create")
    RDMResultVO create(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<PartCreateDTO> var1);

    @PostMapping("dynamic/api/{modelName}/update")
    RDMResultVO update(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<PartUpdateDTO> var1);

    @PostMapping("dynamic/api/{modelName}/delete")
    RDMResultVO delete(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<MasterIdModifierDTO> var1);

    @PostMapping("dynamic/api/{modelName}/checkout")
    RDMResultVO checkout(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionCheckOutDTO> var1);

    @PostMapping("dynamic/api/{modelName}/updateAndCheckin")
    RDMResultVO updateAndCheckin(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionUpdateAndCheckinDTO<PartUpdateByAdminDTO>> var1);

    @PostMapping("dynamic/api/{modelName}/checkin")
    RDMResultVO checkin(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionCheckInDTO> var1);

    @PostMapping("dynamic/api/{modelName}/getAllVersions")
    RDMResultVO getAllVersions(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionMasterDTO> var1);

    @PostMapping("dynamic/api/{modelName}/revise")
    RDMResultVO revise(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionReviseDTO> var1);

    @PostMapping("dynamic/api/{modelName}/deleteLatestVersion")
    RDMResultVO deleteLatestVersion(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionMasterModifierDTO> var1);

    @PostMapping("dynamic/api/{modelName}/deleteBranch")
    RDMResultVO deleteBranch(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<VersionMasterModifierDTO> var1);

    @PostMapping("api/Part/getVersionByMaster")
    RDMResultVO getVersionByMaster(@RequestBody RDMParamVO<VersionMasterDTO> var1);
}

