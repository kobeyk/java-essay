package com.appleyk.io.netty.rpc.customer;

import com.appleyk.io.netty.rpc.netty.client.RpcClient;
import com.appleyk.io.netty.rpc.service.HelloService;

import java.util.Scanner;

/**
 * <p>越努力，越幸运</p>
 * 服务消费者调用
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:08 下午 2021/2/28
 */
public class CustomerBootstrap {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HelloService helloService = (HelloService) new RpcClient().getObject(HelloService.class);
        while (scanner.hasNextLine()){
            String param = scanner.nextLine();
            System.out.println("=== 远程调用结果："+helloService.say(param));
        }


    }

}
