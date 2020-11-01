package com.appleyk.jvm.GC调优实战案例;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.0.2.2-refactor
 * @company 苏州中科蓝迪
 * @date created on 9:37 2020/10/27
 */
public class G {

    public static void main(String[] args) throws IOException {
        for (;;){
            // 阻塞
            System.in.read();
            new GG().show();
        }
    }
}
