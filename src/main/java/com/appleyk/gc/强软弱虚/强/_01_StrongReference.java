package com.appleyk.gc.强软弱虚.强;

import com.appleyk.gc.强软弱虚.M;

import java.io.IOException;

/**
 * <p>越努力，越幸运</p>
 * new 出来的都是强引用，当对象存在引用时，gc宁愿抛出OOM，也不会回收
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:05 下午 2020/12/22
 */
public class _01_StrongReference {
    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        // 手动触发gc
        System.gc();
        System.out.println(m);
        System.in.read();
    }
}
