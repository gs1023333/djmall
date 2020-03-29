package com.dj.mall.entity.auth.resource;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;
import org.dozer.Mapping;

import java.io.Serializable;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-29 下午 08:54
 */
@Data
@Accessors(chain = true)
@TableName("djmall_auth_resource")
public class ResourceEntity implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    @Mapping("resourceId")
    private Integer id;

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
