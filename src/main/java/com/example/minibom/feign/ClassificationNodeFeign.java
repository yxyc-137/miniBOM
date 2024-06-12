package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.minibom.bean.entity.User;
import com.huawei.innovation.rdm.xdm.bean.entity.ClassificationNode;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeQueryViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(url = "${idme.endpoint}", name = "classification-service")
public interface ClassificationNodeFeign {

    @GetMapping("rdm/basic/api/ClassificationNode/getChildren")
    RDMResultVO getChild(@RequestParam("parentId") Long parentId,
                         @RequestParam("enbale") Boolean enable);
    @GetMapping("rdm/basic/api/ClassificationNode/getAllChildList")
    RDMResultVO getAllChildList(@RequestBody RDMParamVO<QueryChildListDTO> var1);

    @GetMapping("rdm/basic/api/ClassificationNode/getCategoryNodeInfo")
    RDMResultVO getCategoryNodeInfo(@RequestParam("linkId") Long linkId);

    @PostMapping("rdm/common/api/{modelName}/find")
    RDMResultVO find(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<QueryRequestVo> var1);

    @PostMapping("rdm/common/api/{modelName}/get")
    RDMResultVO get(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<ClassificationNode> var1);

    @PostMapping("rdm/common/api/{modelName}/create")
    RDMResultVO create(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<ClassificationNode> var1);

    @PostMapping("rdm/common/api/{modelName}/update")
    RDMResultVO update(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<ClassificationNode> var1);

    @PostMapping("rdm/common/api/{modelName}/delete")
    RDMResultVO delete(@PathVariable("modelName") String modelName, @RequestBody RDMParamVO<ClassificationNode> var1);
}
