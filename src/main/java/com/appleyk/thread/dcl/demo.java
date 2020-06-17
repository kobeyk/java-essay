package com.appleyk.thread.dcl;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class demo {
    public static void main(String[] args) {
        Object o = new Object();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}