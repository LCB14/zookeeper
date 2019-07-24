package com.lcb.client.test;

import java.util.Locale;

/**
 * @author changbao.li
 * @Description TODO
 * @Date 2019-07-24 10:27
 */
public class Test {
    public static void main(String[] args) {
        String path = "/data/test_";
        long parentCVersion = 9999999999L+1;
        System.out.println(parentCVersion);
        path = path + String.format(Locale.ENGLISH, "%010d", parentCVersion);
        System.out.println(path);
    }
}
