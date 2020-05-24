package com.lcb.client.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @author changbao.li
 * @Description zk 自带客户端测试
 * @Date 2019-07-07 17:57
 */
public class ZookeeperClientTest {

    public static void main(String[] args) throws Exception {

        /**
         * zk原生客户端的watcher只能被使用一次
         */
        ZooKeeper client = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("默认监听器：" + event);
            }
        });

        // 判断节点是否已存在
        Stat exists = client.exists("/data", true, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("节点已创建");
            }
        });
        if (exists == null) {
            client.create("/data", "Hello zookeeper".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            client.create("/data/child_node", "child mode create".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        Stat stat = new Stat();

        // 1、使用自定义watch监听器（同步获取节点数据）
        byte[] data = client.getData("/data", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("事件类型：" + event.getType());
            }
        }, stat);
        String str = new String(data);
        System.out.println("节点内容：" + str);

        // 2、使用默认的watch监听器
        client.getData("/data", true, stat);

        // 3、使用回调（异步获取节点数据）
        client.getData("/data", false, new AsyncCallback.DataCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
                System.out.println("method 被回调");
            }
        }, stat);

        // 4、获取孩子节点
        List<String> children = client.getChildren("/data", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("获取孩子节点信息成功：" + event);
            }
        });
        System.out.println(children);

        System.in.read();
    }
}
