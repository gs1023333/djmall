package com.dj.mall.admin.web.auth.user;

import com.dj.mall.admin.vo.auth.resource.ResourceVoResp;
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
        Object user = session.getAttribute("user");
        UserDtoResp userDtoResp = DozerUtil.map(user, UserDtoResp.class);
        List<ResourceDtoResp> authList = userDtoResp.getAuthList();
        List<ResourceVoResp> respList = DozerUtil.mapList(authList, ResourceVoResp.class);
        for (ResourceVoResp resourceVoResp : respList) {
            resourceVoResp.setOpen(true);
        }
        return new ResultModel<>().success(respList);
    }
}
