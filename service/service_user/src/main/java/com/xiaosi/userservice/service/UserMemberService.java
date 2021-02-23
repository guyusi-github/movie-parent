package com.xiaosi.userservice.service;

import com.xiaosi.userservice.entity.UserMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaosi.userservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-24
 */
public interface UserMemberService extends IService<UserMember> {
    String login(UserMember member);

    void register(RegisterVo registerVo);

    UserMember getByOpenId(String openid);
}
