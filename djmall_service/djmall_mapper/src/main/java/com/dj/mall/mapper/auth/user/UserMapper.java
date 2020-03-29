package com.dj.mall.mapper.auth.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.user.UserEntity;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**用户mapper
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:01
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    List<ResourceEntity> getUserResourceByUserId(Integer userId) throws DataAccessException;

}
