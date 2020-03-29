package com.dj.mall.admin.vo.auth.resource;

import lombok.Data;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 09:06
 */
@Data
public class ResourceVoReq implements Serializable {

    private Integer resourceId;

    /**
     * 父集ID
     */
    private Integer parentId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 地址
     */
    private String url;

    /**
     * 删除状态 1:未删除,2:删除
     */
    private Integer isDel;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源编码
     */
    private String resourceCode;
}
