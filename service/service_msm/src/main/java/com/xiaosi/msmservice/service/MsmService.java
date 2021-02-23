package com.xiaosi.msmservice.service;

import java.util.Map;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 顾育司
 * @since 2020-12-04
 */
public interface MsmService {

    boolean send(String phone, Map<String, Object> param);
}
