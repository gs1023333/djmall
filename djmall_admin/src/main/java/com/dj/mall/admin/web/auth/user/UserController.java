package com.dj.mall.admin.web.auth.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dj.mall.admin.vo.auth.user.UserVoReq;
import com.dj.mall.admin.vo.auth.user.UserVoResp;
import com.dj.mall.api.auth.user.UserApi;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.base.SystemConstant;
import com.dj.mall.model.dto.auth.user.UserDtoReq;
import com.dj.mall.model.dto.auth.user.UserDtoResp;
import com.dj.mall.model.util.DozerUtil;
import com.dj.mall.model.util.JavaEmailUtils;
import com.dj.mall.model.util.MessageVerifyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-24 下午 06:32
 */
@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Reference
    private UserApi userApi;

    @GetMapping("/{userId}")
    public ResultModel<Object> getUser(@PathVariable Integer userId) throws Exception{
        UserDtoResp userDtoResp = userApi.getUser(userId);
        return new ResultModel<>().success(DozerUtil.map(userDtoResp, UserVoResp.class));
    }

    @GetMapping
    public ResultModel<Object> queryUser(UserVoReq userVoReq) throws Exception {
        UserDtoReq userDtoReq = DozerUtil.map(userVoReq, UserDtoReq.class);
        List<UserDtoResp> userDtoRespList = userApi.queryUser(userDtoReq);
        return new ResultModel<>().success(DozerUtil.mapList(userDtoRespList, UserVoResp.class));
    }

    @RequestMapping("login")
    public ResultModel<Object> login(UserVoReq userVoReq, HttpSession session)  throws Exception {
        // shiro登录方式
        Subject subject = SecurityUtils.getSubject();
        String userName = userVoReq.getUserName();
        String password = userVoReq.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        subject.login(token);
        return new ResultModel<>().success(true);
    }

    @RequestMapping("getSalt")
    public ResultModel<Object> getSalt(String userName)  throws Exception{
        UserDtoResp userDtoResp = userApi.getSalt(userName);
        if (userDtoResp == null) {
            return new ResultModel<>().error("用户名或者密码错误,请检查后重试!");
        }
        ResultModel  resultModel = new ResultModel();
        resultModel.setData(userDtoResp.getSalt());
        return resultModel;
    }

    @RequestMapping("difference")
    public boolean difference(String userName, String nickName)  throws Exception{
        return userName.equals(nickName) ? false : true;
    }

    @RequestMapping("findByName")
    public boolean findByName(UserVoReq userVoReq) throws Exception{
        List<UserDtoResp> list = userApi.findByName(DozerUtil.map(userVoReq, UserDtoReq.class));
        return list.size() > 0 ? false : true;
    }

    @RequestMapping("findByEmail")
    public boolean findByEmail(UserVoReq userVoReq) throws Exception{
        List<UserDtoResp> list = userApi.findByEmail(DozerUtil.map(userVoReq, UserDtoReq.class));
        return list.size() > 0 ? false : true;
    }
    @RequestMapping("findByPhone")
    public boolean findByPhone(UserVoReq userVoReq) throws Exception{
        List<UserDtoResp> list = userApi.findByPhone(DozerUtil.map(userVoReq, UserDtoReq.class));
        return list.size() > 0 ? false : true;
    }
    @RequestMapping("findOneByPhone")
    public boolean findOneByPhone(String userPhone) throws Exception{
        UserDtoResp userDtoResp = userApi.findOneByPhone(userPhone);
        return userDtoResp != null ? true : false;
    }
    @RequestMapping("add")
    public ResultModel<Object> add (UserVoReq userVoReq)  throws Exception{

        if (userVoReq.getUserLevel().equals(SystemConstant.LEVEL_SHOP)) {
            int newcode = MessageVerifyUtils.getNewcode();
            // 获取验证码有效时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MINUTE, SystemConstant.VALID_5);
            userVoReq.setCreateTime(new Date());
            userVoReq.setCodeTime(calendar.getTime());
            userVoReq.setStatus(SystemConstant.STATUS_ERROR);
            String content = "<a href='http://localhost:8081/admin/auth/user/updateUserStatusByUserName?userName=" + userVoReq.getUserName() + "'>点此验证</a><br>"
                    + "如果您无法点击以上链接，请复制以下网址到浏览器里直接打开：<br>"
                    + "localhost:8081/admin/auth/user/updateUserStatusByUserName?userName=" + userVoReq.getUserName() + "<br>"
                    + "如果您没有注册，请忽略此邮件";
            JavaEmailUtils.sendEmail(userVoReq.getUserEmail(), "商户激活", content);
        }
        userApi.addUser(DozerUtil.map(userVoReq, UserDtoReq.class));
        return new ResultModel<>().success(true);
    }

    @RequestMapping("updateUser")
    public ResultModel<Object> updateUser(UserVoReq userVoReq) throws Exception{
        userApi.updateUserByUserId(DozerUtil.map(userVoReq, UserDtoReq.class));
        return new ResultModel<>().success(true);
    }


    @RequestMapping("findByPhoneAndCode")
    public boolean findByPhoneAndCode(String userPhone, String userCode) throws Exception{
        UserDtoResp userDtoResp = userApi.findByPhoneAndCode(userPhone, userCode);
        return userDtoResp == null ? false : true;
    }

    @RequestMapping("verify")
    public ResultModel<Object> verify(UserVoReq userVoReq) throws Exception{
        UserDtoResp userDtoResp = userApi.findOneByPhone(userVoReq.getUserPhone());
        // 时间加减
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, SystemConstant.VALID_5);
        userDtoResp.setCodeTime(cal.getTime());
        int newcode = MessageVerifyUtils.getNewcode();
        userDtoResp.setUserCode(newcode);
        MessageVerifyUtils.sendSms(String.valueOf(userDtoResp.getUserPhone()), String.valueOf(newcode));
        userApi.updateUserByUserId(DozerUtil.map(userDtoResp, UserDtoReq.class));
        return new ResultModel<>().success(true);
    }

    @RequestMapping("findPwd")
    public ResultModel<Object> findPwd(UserVoReq userVoReq) throws Exception{
        UserDtoResp userDtoResp = userApi.findOneByPhone(userVoReq.getUserPhone());
        if (userDtoResp.getCodeTime().getTime() < System.currentTimeMillis()) {
            return new ResultModel<>().error("验证码已过期,请重新获取!");
        }
        userDtoResp.setPassword(userVoReq.getPassword());
        userDtoResp.setSalt(userVoReq.getSalt());
        userApi.updateUserByUserId(DozerUtil.map(userDtoResp, UserDtoReq.class));
        //想用户发送修改密码成功的邮件
        String content = "您的账户"+userDtoResp.getUserName()+"，于"+new Date()+"时进行密码修改成功";
        JavaEmailUtils.sendEmail(userDtoResp.getUserEmail(), "修改密码", content);
        return new ResultModel<>().success(true);
    }
}
