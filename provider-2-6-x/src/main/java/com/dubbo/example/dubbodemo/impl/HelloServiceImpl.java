package com.dubbo.example.dubbodemo.impl;

import com.dubbo.example.dubbodemo.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: hongwei.quhw
 * @date: 2020-09-12 21:45
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger LOGGER                  = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String sayHello(String msg) {
        String ret = "hello " + msg;
        //System.out.println(ret);
        LOGGER.info(ret);
        return  ret;
    }
}
