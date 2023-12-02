package com.box.step8Juc.entity;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author tangsx
 * @createTime 2023/11/11 19:19
 * @description
 */
public class NetMall {
    private String netMall;


    public double getPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1L);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return ThreadLocalRandom.current().nextDouble() * 2 + productName.charAt(0);
    }


    public NetMall(String netMall) {
        this.netMall = netMall;
    }

    public String getNetMall() {
        return netMall;
    }

    public void setNetMall(String netMall) {
        this.netMall = netMall;
    }
}
