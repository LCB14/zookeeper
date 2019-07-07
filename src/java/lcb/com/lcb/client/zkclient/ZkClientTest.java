package com.lcb.client.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

/**
 * @author changbao.li
 * @Description zkClient客户端测试
 * @Date 2019-07-07 20:07
 */
public class ZkClientTest {

    private static final String ADDRESS = "localhost:";
    private static final String PORT = "2181";

    public static void main(String[] args) throws Exception{

        ZkClient zk = new ZkClient(ADDRESS+PORT,1000,1000,new SerializableSerializer());

        zk.createPersistent("/data","lcb data");

        // 为节点添加监听器，类似watch
        zk.subscribeDataChanges("/data",new IZkDataListener(){
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("/data节点数据被改动了");
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("/data节点数据被删除了");
            }
        });

        System.in.read();
    }
}
