/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.dubbo.example.dubbodemo.impl;

import com.dubbo.example.dubbodemo.util.NetUtil;
import org.apache.dubbo.demo.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright (C) 2013-2018 Ant Financial Services Group
 *
 * @author quhongwei
 * @version : GreetingServiceImpl.java, v 0.1 2020年09月21日 14:02 quhongwei Exp $
 */
public class GreetingServiceImpl  implements GreetingService {
    private static final Logger LOGGER                  = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello() {
        String ret = "Greetings from provider1! " + NetUtil.getLocalAddress().toString();
        LOGGER.info(ret);
        return  ret;
    }
}