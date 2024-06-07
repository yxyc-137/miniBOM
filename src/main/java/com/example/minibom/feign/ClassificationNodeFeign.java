package com.example.minibom.feign;

import com.huawei.innovation.rdm.coresdk.basic.dto.QueryChildListDTO;
import com.huawei.innovation.rdm.coresdk.basic.vo.QueryRequestVo;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMParamVO;
import com.huawei.innovation.rdm.coresdk.basic.vo.RDMResultVO;
import com.huawei.innovation.rdm.xdm.dto.entity.ClassificationNodeQueryViewDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "${idme.endpoint}", name = "classification-service")
public interface ClassificationNodeFeign {
    @GetMapping("rdm/basic/api/ClassificationNode/getChildren")
    RDMResultVO getChild(@RequestParam("parentId") Long parentId,
                         @RequestParam("enbale") Boolean enable);

    @GetMapping("rdm/basic/api/ClassificationNode/getCategoryNodeInfo")
    RDMResultVO getCategoryNodeInfo(@RequestParam("linkId") Long linkId);
}
