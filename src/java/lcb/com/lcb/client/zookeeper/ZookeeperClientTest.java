package com.lcb.client.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * @author changbao.li
 * @Description zk 自带客户端测试
 * @Date 2019-07-07 17:57
 */
public class ZookeeperClientTest {

    public static void main(String[] args) throws Exception {

        /**
         * zk原生客户端的wacth只能被使用一次
         */
        ZooKeeper client = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("连接" + event);
            }
        });

        Stat stat = new Stat();
        String str = new String(client.getData("/data", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("事件类型：" + event.getType());
            }
        }, stat));
        System.out.println("节点内容：" + str);

        System.in.read();
    }
}
