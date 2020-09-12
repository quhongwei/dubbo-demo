package com.dubbo.example.dubbodemo.impl;

import com.dubbo.example.dubbodemo.api.HelloService;

/**
 * @author: hongwei.quhw
 * @date: 2020-09-12 21:45
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String msg) {
        String ret = "hello " + msg;
        System.out.println(ret);
        return  ret;
    }
}
