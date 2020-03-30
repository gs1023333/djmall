package com.dj.mall.pro.auth.impl.user;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.entity.auth.resource.ResourceEntity;
import com.dj.mall.entity.auth.user.UserEntity;
import com.dj.mall.entity.auth.user.UserRoleEntity;
import com.dj.mall.mapper.auth.user.UserMapper;
import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.base.SystemConstant;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.dto.auth.user.UserDtoReq;
import com.dj.mall.model.dto.auth.user.UserDtoResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.pro.auth.service.user.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:14
 */
@Service
public class UserApiImpl extends ServiceImpl<UserMapper, UserEntity> implements UserApi {

    @Autowired
    private UserRoleService userRoleService;
    /**
     * 功能描述: 根据用户ID获取用户信息
     *
     * @param userId
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-24 下午 06:09
     */
    @Override
    public UserDtoResp getUser(Integer userId) throws Exception {
        return DozerUtil.map(this.getById(userId), UserDtoResp.class);
    }

    /**
     * 功能描述: 查询用户
     *
     * @param userDtoReq
     * @params: * @param null 查询条件
     * @return:
     * @author: gsss
     * @date: 2020-3-24 下午 06:12
     */
    @Override
    public List<UserDtoResp> queryUser(UserDtoReq userDtoReq) throws Exception {
        return DozerUtil.mapList(this.list(), UserDtoResp.class);
    }

    /**
     * 功能描述: 通过用户名密码查询用户
     *
     * @param userName
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 12:36
     */
    @Override
    public UserDtoResp getByUserName(String userName, String password) throws Exception, BusinessException {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.or(i -> i.eq("user_name", userName)
                .or().eq("user_phone", userName)
                .or().eq("user_email", userName));
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity == null || !password.equals(userEntity.getPassword())) {
            throw new BusinessException("账号密码有误,请检查!!");
        }
        if (password.equals(userEntity.getResetPwd())) {
            throw new BusinessException("300");
        }

        if (userEntity.getStatus().equals(SystemConstant.STATUS_ERROR)) {
            throw new BusinessException("未激活的用户不可以登录!");
        }
        List<ResourceEntity> list = getBaseMapper().getUserResourceByUserId(userEntity.getId());
        UserDtoResp userDtoResp = DozerUtil.map(this.getOne(queryWrapper), UserDtoResp.class);
        userDtoResp.setAuthList(DozerUtil.mapList(list, ResourceDtoResp.class));
        return userDtoResp;
    }

    /**
     * 功能描述: 通过用户名获取盐
     *
     * @param userName
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 01:02
     */
    @Override
    public UserDtoResp getSalt(String userName) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.or(i -> i.eq("user_name", userName)
                .or().eq("user_phone", userName)
                .or().eq("user_email", userName));
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity == null) {
            return null;
        }
        return DozerUtil.map(userEntity, UserDtoResp.class);
    }

    /**
     * 功能描述: 根据用户ID修改用户信息
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 12:50
     */
    @Override
    public void updateUserByUserId(UserDtoReq userDtoReq) throws Exception {
        this.updateById(DozerUtil.map(userDtoReq, UserEntity.class));
    }

    /**
     * 功能描述: 新增人员
     *
     * @param userDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 04:22
     */
    @Override
    public void addUser(UserDtoReq userDtoReq) throws Exception {
        userDtoReq.setCreateTime(new Date());
        this.save(DozerUtil.map(userDtoReq, UserEntity.class));
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDtoReq.getUserName());
        UserEntity userEntity = this.getOne(queryWrapper);
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userEntity.getId()).setRoleId(userDtoReq.getUserLevel());
        userRoleService.save(userRoleEntity);
    }

    /**
     * 功能描述: 判断用户名是否存在
     *
     * @param userDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    @Override
    public List<UserDtoResp> findByName(UserDtoReq userDtoReq) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.or(i -> i.eq("user_name", userDtoReq.getUserName())
                .or().eq("user_phone", userDtoReq.getUserName())
                .or().eq("user_email", userDtoReq.getUserName()));
        List<UserEntity> list = this.list(queryWrapper);
        return  DozerUtil.mapList(this.list(queryWrapper), UserDtoResp.class);
    }

    /**
     * 功能描述: 判断邮箱号是否存在
     *
     * @param userDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    @Override
    public List<UserDtoResp> findByEmail(UserDtoReq userDtoReq) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.or(i -> i.eq("user_name", userDtoReq.getUserEmail())
                .or().eq("user_phone", userDtoReq.getUserEmail())
                .or().eq("user_email", userDtoReq.getUserEmail()));
        List<UserEntity> list = this.list(queryWrapper);
        return  DozerUtil.mapList(this.list(queryWrapper), UserDtoResp.class);
    }

    /**
     * 功能描述: 判断手机号是否存在
     *
     * @param userDtoReq
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    @Override
    public List<UserDtoResp> findByPhone(UserDtoReq userDtoReq) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.or(i -> i.eq("user_name", userDtoReq.getUserPhone())
                .or().eq("user_phone", userDtoReq.getUserPhone())
                .or().eq("user_email", userDtoReq.getUserPhone()));
        List<UserEntity> list = this.list(queryWrapper);
        return  DozerUtil.mapList(this.list(queryWrapper), UserDtoResp.class);
    }

    /**
     * 功能描述: 根据用户名修改用户状态
     *
     * @param userName
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 03:41
     */
    @Override
    public void updateUserStatusByUserName(String userName) throws Exception {
        UpdateWrapper<UserEntity> queryWrapper = new UpdateWrapper<>();
        queryWrapper.set("status", SystemConstant.STATUS_OK);
        queryWrapper.eq("user_name", userName);
        this.update(queryWrapper);
    }

    /**
     * 功能描述: 查询手机号是否存在
     *
     * @param userPhone
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 04:02
     */
    @Override
    public UserDtoResp findOneByPhone(String userPhone) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", userPhone);
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity == null) {
            return null;
        }
        return DozerUtil.map(userEntity, UserDtoResp.class);
    }

    /**
     * 功能描述: 通过查询手机号和验证码判断用户是否存在
     *
     * @param
     * @params: * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 04:10
     */
    @Override
    public UserDtoResp findByPhoneAndCode(String userPhone, String userCode) throws Exception {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", userPhone )
                .eq("user_code", userCode);
        UserEntity userEntity = this.getOne(queryWrapper);
        if (userEntity == null) {
            return null;
        }
        return DozerUtil.map(userEntity, UserDtoResp.class);
    }

}
