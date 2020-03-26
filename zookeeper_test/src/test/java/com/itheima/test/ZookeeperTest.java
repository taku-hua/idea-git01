package com.itheima.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.After;
import org.junit.Test;

import javax.lang.model.element.VariableElement;

public class ZookeeperTest {
    private CuratorFramework client = null ;
    RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10, 10);
    @Test
    public void createTest() throws Exception{
        client = CuratorFrameworkFactory.newClient("localhost",3000,1000, retryPolicy);
        client.start();
//        String s = client.create().forPath("/idea", "hello".getBytes());
//        client.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/idea");
//        client.create().creatingParentsIfNeeded().forPath("/helo/app","hello".getBytes());
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/el","ephemeral".getBytes());
        Thread.sleep(10000);

    }

    @Test
    public void deleteTest() throws Exception {
        client = CuratorFrameworkFactory.newClient("localhost",3000,1000,retryPolicy);
        client.start();
        client.delete().forPath("/idea");

    }


    @Test
    public void update() throws Exception {
        client = CuratorFrameworkFactory.newClient("localhost",3000,1000,retryPolicy);
        client.start();
        client.setData().forPath("/app3","good".getBytes());
        client.setData().forPath("/app3");
    }


    @Test
    public void getTest() throws Exception {
        client = CuratorFrameworkFactory.newClient("localhost",3000,1000,retryPolicy);
        client.start();

        byte[] bytes = client.getData().forPath("/app2");

        System.out.println(new String(bytes) + "结果:");
    }

    @After
    public void close() {
        if (client != null) {
            client.close();
            System.out.println("close");
        }
    }




}
