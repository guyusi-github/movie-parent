package com.xiaosi.userservice.controller;


import com.xiaosi.commonutils.JwtUtils;
import com.xiaosi.commonutils.R;
import com.xiaosi.commonutils.UcenterMemberVoOrder;
import com.xiaosi.userservice.entity.UserMember;
import com.xiaosi.userservice.entity.vo.RegisterVo;
import com.xiaosi.userservice.service.UserMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-24
 */
@RestController
@RequestMapping("/userservice/usermember")
@CrossOrigin
public class UserMemberController {
    @Autowired
    UserMemberService memberService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @ApiOperation("编写用户登录逻辑")
    @PostMapping("login")
    public R login(@RequestBody UserMember member){
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    @ApiOperation("编写用户注册逻辑")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation("根据token获取登录信息")
    @GetMapping("getMemberInfo")  //根据请求获得
    public R getLogonInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UserMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

    @ApiOperation("根据用户id获该用户的信息") //order服务调用
    @PostMapping("getMemberInfoById/{id}")
    public UcenterMemberVoOrder getMemberInfoById(@PathVariable String id ){
        //定义返回的数据类型
        UserMember member = memberService.getById(id);
        UcenterMemberVoOrder memberVoOrder = new UcenterMemberVoOrder();
        BeanUtils.copyProperties(member,memberVoOrder);
        return memberVoOrder;
    }
}

