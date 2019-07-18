package com.lcb.client.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author changbao.li
 * @Description 测试zkclient的持续监听功能
 * @Date 2019-07-18 22:17
 */
public class ZKClientUpdateNodeTest {
    private static final String ADDRESS = "localhost:";
    private static final String PORT = "2181";

    public static void main(String[] args) {
        ZkClient zk = new ZkClient(ADDRESS + PORT, 1000, 1000, new SerializableSerializer());
        zk.writeData("/data", "test4");
    }
}
