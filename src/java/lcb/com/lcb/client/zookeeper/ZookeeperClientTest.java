package com.lcb.client.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author changbao.li
 * @Description zk 自带客户端
 * @Date 2019-07-07 17:57
 */
public class ZookeeperClientTest {

    public static void main(String[] args) throws Exception {
        ZooKeeper client = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("连接" + event);
            }
        });

        System.in.read();
    }
}
