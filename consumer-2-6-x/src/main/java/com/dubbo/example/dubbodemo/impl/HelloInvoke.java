package com.dubbo.example.dubbodemo.impl;

import com.dubbo.example.dubbodemo.api.HelloService;
import com.dubbo.example.dubbodemo.util.NetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author: hongwei.quhw
 * @date: 2020-09-12 22:24
 */

public class HelloInvoke implements InitializingBean {
    private static final Logger LOGGER                  = LoggerFactory.getLogger(HelloInvoke.class);

    private HelloService helloService;

    @Override
    public void afterPropertiesSet() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                while(true) {
                    try {
                        String ret = helloService.sayHello("consumer-2-6-x host=" + NetUtil.getLocalAddress().toString());
                        System.out.println(ret);
                        LOGGER.info(ret);

                    } catch (Exception e) {
                        LOGGER.error("", e);
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }


    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
