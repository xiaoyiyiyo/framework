package com.xiaoyiyiyo;

import com.xiaoyiyiyo.core.JsonApplicationContext;
import com.xiaoyiyiyo.entity.Computer;

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
