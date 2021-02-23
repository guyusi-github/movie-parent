package com.xiaosi.msmservice.controller;

import com.xiaosi.commonutils.R;
import com.xiaosi.commonutils.RandomUtil;
import com.xiaosi.msmservice.service.MsmService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 顾育司
 * @since 2020-12-04
 */
@RestController
@RequestMapping("/msmservice/member")
@CrossOrigin
public class MsmController {

    @Autowired
    MsmService memberService;
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    @ApiOperation("发送阿里云短信")
    @GetMapping("/send/{phone}")
    public R code(@PathVariable String phone){
        //先从redis查看有没有短信码
        String code = redisTemplate.opsForValue().get(phone);
        //不为空则取出
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //随机产生四位随机码(工具类)
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("code",code);
        //redis存json ，要把一个map转换成json 采用以下工具类
         boolean isSend = memberService.send(phone,param);
        if(isSend){
            //发送成功写入redis并设置过时时间
            redisTemplate.opsForValue().set(phone,code,60*24*30, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error().message("发送失败");
        }
    }

}

