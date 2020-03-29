package com.dj.mall.admin.web.auth.user;

import com.dj.mall.admin.vo.auth.user.UserVoResp;
import com.dj.mall.model.base.ResultModel;
import com.dj.mall.model.dto.auth.resource.ResourceDtoResp;
import com.dj.mall.model.dto.auth.user.UserDtoResp;
import com.dj.mall.model.util.DozerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Written by : GSSS
 * @创建时间 Date : 2020-3-30 上午 01:00
 */
@RestController
@RequestMapping("/auth/index")
public class IndexController {

    @RequestMapping("show")
    public ResultModel<Object> show(HttpSession session) {
        UserDtoResp userDtoResp = (UserDtoResp) session.getAttribute("user");
        List<ResourceDtoResp> authList = userDtoResp.getAuthList();
        List<UserVoResp> respList = DozerUtil.mapList(authList, UserVoResp.class);
        return new ResultModel<>().success(respList);
    }
}
