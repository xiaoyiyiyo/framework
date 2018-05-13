package com.xiaoyiyiyo.ioc;

import com.xiaoyiyiyo.ioc.core.JsonApplicationContext;
import com.xiaoyiyiyo.ioc.entity.Computer;

/**
 * Created by xiaoyiyiyo on 2018/3/9.
 */
public class Test {

    public static void main(String[] args) throws Exception {

        JsonApplicationContext applicationContext = new JsonApplicationContext("application.json");
        applicationContext.init();
        Computer computer = (Computer) applicationContext.getBean("computer");
        computer.work();

    }
}
