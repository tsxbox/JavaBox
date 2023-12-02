package com.box.step8Juc.compltetable;

import com.box.step8Juc.entity.NetMall;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author tangsx
 * @createTime 2023/11/11 11:24
 * @description
 */
public class CompletableFutureMallDemo {

    static List<NetMall> malls = Arrays.asList(
            new NetMall("JD"),
            new NetMall("taobao"),
            new NetMall("dangdang"),
            new NetMall("pdd")
    );


    public static void main(String[] args) {
        genCommonPrice();
        genCommonPriceByCompletableFuture();
    }


    /**
     * 串行执行测试
     */
    public static void genCommonPrice() {
        long startTime = System.currentTimeMillis();
        List<String> mysql = getPrice(malls, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("串行----cost time is " + (endTime - startTime));
    }

    /**
     * 串行执行
     *
     * @param malls       商家
     * @param productName 产品名
     * @return List<String>
     */
    public static List<String> getPrice(List<NetMall> malls, String productName) {
        return malls.stream().map(mall -> String.format(productName + "in  %s price is %.2f", mall.getNetMall(), mall.getPrice(productName))).collect(Collectors.toList());
    }

    /**
     * 异步执行
     */
    public static void genCommonPriceByCompletableFuture() {
        long startTime = System.currentTimeMillis();
        List<String> mysql = genPriceByCompletableFuture(malls, "mysql");
        for (String s : mysql) {
            System.out.println(s);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("并发----cost time is " + (endTime - startTime));
    }

    /**
     * 并发执行
     *
     * @param malls       商家
     * @param productName 产品名
     * @return List<CompletableFuture < String>>
     */
    public static List<String> genPriceByCompletableFuture(List<NetMall> malls, String productName) {
        return malls.stream().map(mall -> CompletableFuture.supplyAsync(() ->
                String.format(productName + "in  %s price is %.2f",
                        mall.getNetMall(),
                        mall.getPrice(productName)))).collect(Collectors.toList()).stream().map(item -> item.join()).collect(Collectors.toList());
    }
}