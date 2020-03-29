package com.dj.mall.api.auth.resource;

import com.dj.mall.model.dto.auth.resource.ResourceDtoReq;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:00
 */
public interface ResourceApi {

    /**
     * 功能描述: 展示资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 09:46
     */
    List<ResourceDtoResp> queryList() throws Exception;

    /**
     * 功能描述: 根据ID查资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 09:56
     */
    ResourceDtoResp getById(Integer id) throws Exception;

    /**
     * 功能描述: 根据父集ID查找用户
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-30 上午 12:01
     */
    ResourceDtoResp getByParentId(Integer parentId) throws Exception;

    /**
     * 功能描述: 新增资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:03
     */
    void addResource(ResourceDtoReq resourceDtoReq) throws Exception;

    /**
     * 功能描述: 根据ID修改资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:13
     */
    void updateResourceById(ResourceDtoReq resourceDtoReq) throws Exception;

    /**
     * 功能描述: 根据ID删除资源
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 10:16
     */
    void delResourceById(Integer resourceId) throws Exception;
}
