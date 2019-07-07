package com.lcb.client.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
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

        NodeCache nodeCache = new NodeCache(client,"/data2");
        nodeCache.start();
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("节点数据发生变更");
            }
        });

        System.in.read();
    }
}
