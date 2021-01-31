package com.appleyk.io.nio;

import java.nio.IntBuffer;

/**
 * <p>越努力，越幸运</p>
 * NIO三大核心原理示意图：https://processon.com/view/60161c3f63768925f15225b6
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:45 上午 2021/1/31
 */
public class BasicBuffer {

    public static void main(String[] args) {
        // 举例说明buffer是什么东东？（简单说明）
        // 创建一个buffer（缓存区）,大小为5，即可存放5个int类型
        final IntBuffer intBuffer = IntBuffer.allocate(5);

        // 怎么用呢？向buffer中存放数据
        //intBuffer.put(1);
        //intBuffer.put(2);
        //intBuffer.put(3);
        //intBuffer.put(4);
        //intBuffer.put(5);
        for (int i = 0; i < intBuffer.capacity() ; i++) {
            intBuffer.put( i + 1 );
        }

        // 如何从buffer读取数据呢？
        // 第一个动作很重要，flip（翻转）
        // 将buffer转换，读写切换（写的时候，position=limit，读的时候，将position重新置位0）
        intBuffer.flip();
        // 从第二个开始读
        intBuffer.position(1);
        // 最多不能超过第4个位置，所以读出来的结果就是 2 3
        intBuffer.limit(3);
        // 如果有剩余的
        while (intBuffer.hasRemaining())  {
            // 每次get，指针都会后移
            System.out.println(intBuffer.get());
        }
    }
}
