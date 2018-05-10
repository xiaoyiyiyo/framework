package com.xiaoyiyiyo.ioc.entity;

/**
 * Created by xiaoyiyiyo on 2018/3/9.
 */
public class Computer {
    private Cpu cpu;

    private Disk disk;

    public Computer() {
    }

    public Computer(Cpu cpu, Disk disk) {
        this.cpu = cpu;
        this.disk = disk;
    }

    public void work() {
        cpu.cal();
        disk.read();
    }
}
