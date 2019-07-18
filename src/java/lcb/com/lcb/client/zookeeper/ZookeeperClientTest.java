package com.lcb.client.zookeeper;

import org.apache.zookeeper.*;
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
        // 创建节点
//        client.create("/data", "node data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Stat stat = new Stat();
        // 使用自定义watch监听器
        String str = new String(client.getData("/data", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("事件类型：" + event.getType());
            }
        }, stat));

        // 使用自定义watch监听器
        client.getData("/data", true, stat);

        // 使用回调
        client.getData("/data", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("method 被回调");
            }
        }, stat);

        System.out.println("节点内容：" + str);
        System.in.read();
    }
}
