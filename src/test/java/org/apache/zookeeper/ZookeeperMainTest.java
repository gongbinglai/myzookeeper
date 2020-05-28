package org.apache.zookeeper;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * Unit test for simple App.
 */
public class ZookeeperMainTest
{

    @Test
    public void sayHello()
    {
        System.out.println("dddddddddddd");
    }
    @Test
    public void createNode(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 100000, new Watcher() {
                public void process(WatchedEvent event) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            zooKeeper.create("/test4","hello zk".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            System.out.println("创建成功");


            System.out.println(new String(zooKeeper.getData("/test4", false, null)));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void getData(){
        CountDownLatch countDownLatch = new CountDownLatch(1);
        try{
            ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 100000, new Watcher() {
                public void process(WatchedEvent event) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();

            String data = new String(zooKeeper.getData("/test4", false, null));

            System.out.println(data);

        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
