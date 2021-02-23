package com.xiaosi.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.xiaosi.msmservice.service.MsmService;
import com.xiaosi.servicebase.MovieException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 顾育司
 * @since 2020-12-04
 */
@Service
public class MsmServiceImpl implements MsmService {

    @Override
    public boolean send(String phone, Map<String, Object> param) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GHjb8VdRL85kbyGqSDN", "9WBWdy2ae8itMx7G4xW3T7VkVd09pf");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);//
        request.putQueryParameter("SignName", "培育花朵线上教育网站");//
        request.putQueryParameter("TemplateCode", "SMS_206545223");//
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));// 将map转为json

        try {
            CommonResponse response = client.getCommonResponse(request);
            //boolean success = response.getHttpResponse().isSuccess();
            System.out.println(response.getData());
            //查看是否请求成功
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            throw new MovieException(20001,"发送失败");
        }
    }
}
