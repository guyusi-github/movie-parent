package com.xiaosi.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaosi.commonutils.JwtUtils;
import com.xiaosi.commonutils.MD5;
import com.xiaosi.servicebase.GlobalExceptionHandler;
import com.xiaosi.servicebase.MovieException;
import com.xiaosi.userservice.entity.UserMember;
import com.xiaosi.userservice.entity.vo.RegisterVo;
import com.xiaosi.userservice.mapper.UserMemberMapper;
import com.xiaosi.userservice.service.UserMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jdk.nashorn.internal.objects.Global;
import org.apache.catalina.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 顾育司
 * @since 2021-02-24
 */
@Service
public class UserMemberServiceImpl extends ServiceImpl<UserMemberMapper, UserMember> implements UserMemberService {

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UserMember member) {
        //获取手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //判断手机号和密码，一个为空都不行
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new MovieException(20001,"登录失败");
        }
        //判断手机号是否正确
        QueryWrapper<UserMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UserMember member1 = baseMapper.selectOne(wrapper);
        if (member1 == null){
            throw new MovieException(20001,"登录失败");
        }
        //检验密码
        if(!MD5.encrypt(password).equals(member1.getPassword())){
            throw new MovieException(20001,"登录失败");
        }

        //判断是否被禁用
        if(member1.getIsDisabled()){
            throw new MovieException(20001,"登录失败");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToke = JwtUtils.getJwtToken(member1.getId(),member1.getNickname());
        return jwtToke;
    }

    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //判断，有一个为空都不行
        if (StringUtils.isEmpty(nickname)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)||StringUtils.isEmpty(code) ){
            throw  new MovieException(2001,"有为空的选项");
        }

        //校验校验验证码
        //从redis获取发送的验证码
        String mobileCode = redisTemplate.opsForValue().get(mobile);
        if(!code.equals(mobileCode)) {
            throw new MovieException(20001,"error");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new QueryWrapper<UserMember>().eq("mobile", mobile));

        //最后把注册的写入到数据库中
        UserMember member = new UserMember();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password)); //加密写入
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        member.setIsDisabled(false);//默认可用
        baseMapper.insert(member);
    }

    @Override
    public UserMember getByOpenId(String openid) {
        QueryWrapper<UserMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        UserMember member = baseMapper.selectOne(queryWrapper);
        return member;

    }
}
