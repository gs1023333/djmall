package com.dj.mall.api.auth.user;

import com.dj.mall.model.base.BusinessException;
import com.dj.mall.model.dto.auth.user.UserDtoReq;
import com.dj.mall.model.dto.auth.user.UserDtoResp;

import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:08
 */
public interface UserApi{

    /**
     * 功能描述: 根据用户ID获取用户信息
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-24 下午 06:09
     */
    UserDtoResp getUser(Integer userId) throws Exception;

    /**
     * 功能描述: 查询用户
     * @params:  * @param null 查询条件
     * @return:
     * @author: gsss
     * @date: 2020-3-24 下午 06:12
     */
    List<UserDtoResp> queryUser(UserDtoReq userDtoReq) throws Exception;

    /**
     * 功能描述: 通过用户名密码查询用户
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 12:36
     */
    UserDtoResp getByUserName(String userName, String password) throws Exception, BusinessException;
    /**
     * 功能描述: 通过用户名获取盐
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 01:02
     */
    UserDtoResp getSalt(String userName) throws Exception;

    /**
     * 功能描述: 根据用户ID修改用户信息
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 上午 12:50
     */
    void updateUserByUserId(UserDtoReq userDtoReq) throws Exception;
    /**
     * 功能描述: 新增人员
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 04:22
     */
    void addUser(UserDtoReq userDtoReq) throws  Exception;
    /**
     * 功能描述: 判断用户名是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    List<UserDtoResp> findByName(UserDtoReq userDtoReq) throws  Exception;
    /**
     * 功能描述: 判断邮箱号是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    List<UserDtoResp> findByEmail(UserDtoReq userDtoReq) throws  Exception;
    /**
     * 功能描述: 判断手机号是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-27 下午 10:00
     */
    List<UserDtoResp> findByPhone(UserDtoReq userDtoReq) throws  Exception;
    /**
     * 功能描述: 根据用户名修改用户状态
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 03:41
     */
    void updateUserStatusByUserName(String userName) throws Exception;

    /**
     * 功能描述: 查询手机号是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 04:02
     */
    UserDtoResp findOneByPhone(String userPhone) throws Exception;

    /**
     * 功能描述: 通过查询手机号和验证码判断用户是否存在
     * @params:  * @param null
     * @return:
     * @author: gsss
     * @date: 2020-3-29 下午 04:10
     */
    UserDtoResp findByPhoneAndCode(String userPhone, String userCode) throws Exception;


}
