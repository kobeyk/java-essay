package com.appleyk.gc;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:19 下午 2020/9/3
 */
public class GcTest03 {

    private static final int _1MB = 1024*1024;

    /**VM options：-Xms20M -Xmx20M -Xmn10M -XX:+UseG1GC -XX:SurvivorRatio=8 -XX:+PrintGCDetails*/
    public static void main(String[] args) {
        
        byte[] b1,b2,b3,b4,b5;
        b1 = new byte[2 * _1MB];
        b2 = new byte[2 * _1MB];
        b3 = new byte[2 * _1MB];
        b4 = new byte[4 * _1MB];
        b5 = new byte[4 * _1MB];

        /**
         * 新生代：
         * Serial（单线程，复制）
         * ParNew（多线程，复制）
         * Parallel Scavenge（并行，强调"吞吐量"，复制）
         *
         * 老年代：
         * Serial Old（单线程，标记整理）
         * Parallel Old（多线程，标记整理）
         *
         * CMS（并发，标记清除） -- 在JDK9中，已被废除（原因：参数过多，维护成本高，过于复杂）
         * G1  (独立GC，低停顿)
         *
         * 收集器 G1 ，对应参数是 -XX:+UseGC
         * 收集器 ParNew + CMS + Serial old，对应参数是 -XX:+UseConcMarkSweepGC
         * 收集器 Serial + Serial Old，对应参数是 -XX:+UseSerialGC
         * 收集器 Parallel Scavenge + Serial Old，对应参数是 -XX:+UseParallelGC
         * 收集器 Parallel Scavenge + Parallel Old，对应参数是 -XX:+UseParallelOldGC
         * 收集器 ParNew  + Serial Old，对应参数是 -XX:+UseParNewGC --》 此组合已过期，后续会被移除

        [GC (Allocation Failure) [PSYoungGen: 6470K->864K(9216K)] 6470K->4968K(19456K), 0.0030114 secs] [Times: user=0.02 sys=0.00, real=0.01 secs]
        Heap
        PSYoungGen total 9216K, used 7495K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
        eden space 8192K, 80% used [0x00000007bf600000,0x00000007bfc79f18,0x00000007bfe00000)
        from space 1024K, 84% used [0x00000007bfe00000,0x00000007bfed8000,0x00000007bff00000)
        to   space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
        ParOldGen       total 10240K, used 9224K [0x00000007bec00000, 0x00000007bf600000, 0x00000007bf600000)
        object space 10240K, 90% used [0x00000007bec00000,0x00000007bf502030,0x00000007bf600000)
        Metaspace       used 3067K, capacity 4496K, committed 4864K, reserved 1056768K
        class space    used 334K, capacity 388K, committed 512K, reserved 1048576K
        */
    }

}
