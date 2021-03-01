package com.netty.rpc.customer;

import com.appleyk.io.netty.rpc.service.HelloService;
import com.netty.rpc.client.RpcClient;

import java.util.Scanner;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:01 2021/3/1
 */
public class RpcCustomer {
    public static void main(String[] args) {
        // 初始化Client组件
        RpcClient client = new RpcClient("localhost",6666);
        Scanner scanner = new Scanner(System.in);
        String line;
        System.out.println("====请次输入你想说的话：===");
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            HelloService helloService = (HelloService) client.getObject(HelloService.class);
            String result = helloService.say(line);
            if (result == null){
                break;
            }
            System.out.println(result);
            System.out.println("====请再次输入你想说的话：===");
        }
        System.exit(1);

    }
}
