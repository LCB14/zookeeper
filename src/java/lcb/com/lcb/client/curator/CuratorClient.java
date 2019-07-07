package com.lcb.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

/**
 * @author changbao.li
 * @Description Curator 客户端测试(流逝)
 * @Date 2019-07-07 22:28
 */
public class CuratorClient {

    private static final String ADDRESS = "localhost:";
    private static final String PORT = "2181";

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory
                .newClient(ADDRESS + PORT, new RetryNTimes(3, 1000));
        client.start();

        client.create().withMode(CreateMode.EPHEMERAL).forPath("/data2", "lcb data".getBytes());
    }
}
